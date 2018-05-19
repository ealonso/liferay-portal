/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.frontend.theme.fjord.group.initializer.internal;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.GroupInitializer;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(
	immediate = true,
	property = "group.initializer.key=" + FjordGroupInitializer.KEY
)
public class FjordGroupInitializer implements GroupInitializer {

	public static final String KEY = "fjord-group-initializer";

	@Override
	public String getDescription(Locale locale) {
		return StringPool.BLANK;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		return "Fjord";
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() + "/images/thumbnail.png";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			ServiceContext serviceContext = _getServiceContext(groupId);

			_updateLookAndFeel(serviceContext);

			Map<String, String> dlFileEntries = _createDLFileEntries(
				serviceContext);

			long fragmentCollectionId = _createFragmentCollection(
				serviceContext);

			Map<String, Long> fragmentEntries = _createFragmentEntries(
				serviceContext, fragmentCollectionId, dlFileEntries);

			long layoutPageTemplateCollectionId =
				_createLayoutPageTemplateCollection(serviceContext);

			_createLayout(
				serviceContext, layoutPageTemplateCollectionId, "Home",
				_getFragmentEntryIds(
					Arrays.asList(
						"actions.html", "items.html", "testimony.html",
						"device.html", "features.html", "devices.html",
						"quote.html", "publications.html", "offerings.html",
						"download.html"),
					fragmentEntries));

			_createLayout(
				serviceContext, layoutPageTemplateCollectionId, "Features",
				_getFragmentEntryIds(
					Arrays.asList(
						"device.html", "features.html", "offerings.html"),
					fragmentEntries));

			_createLayout(
				serviceContext, layoutPageTemplateCollectionId, "Download",
				_getFragmentEntryIds(
					Arrays.asList("download.html"), fragmentEntries));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		return true;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundle = bundleContext.getBundle();
	}

	private Map<String, String> _createDLFileEntries(
			ServiceContext serviceContext)
		throws IOException, PortalException {

		Map<String, String> dlFileEntriesMap = new HashMap<>();

		Folder folder = _dlAppLocalService.addFolder(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Fjord",
			StringPool.BLANK, serviceContext);

		Enumeration<URL> enumeration = _bundle.findEntries(
			"com/liferay/frontend/theme/fjord/group/initializer/internal" +
				"/dependencies/images",
			"*", false);

		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();

			byte[] bytes = null;

			try (InputStream is = url.openStream()) {
				bytes = FileUtil.getBytes(is);
			}

			String fileName = FileUtil.getShortFileName(url.getPath());

			FileEntry fileEntry = _dlAppLocalService.addFileEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				folder.getFolderId(), fileName, null, fileName,
				StringPool.BLANK, StringPool.BLANK, bytes, serviceContext);

			String fileEntryURL = DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, false);

			dlFileEntriesMap.put(fileName, fileEntryURL);
		}

		return dlFileEntriesMap;
	}

	private long _createFragmentCollection(ServiceContext serviceContext)
		throws PortalException {

		FragmentCollection fragmentCollection =
			_fragmentCollectionLocalService.addFragmentCollection(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				"Fjord", null, serviceContext);

		return fragmentCollection.getFragmentCollectionId();
	}

	private Map<String, Long> _createFragmentEntries(
			ServiceContext serviceContext, long fragmentCollectionId,
			Map<String, String> dlFileEntries)
		throws IOException, PortalException {

		Map<String, Long> fragmentEntries = new HashMap<>();

		Enumeration<URL> enumeration = _bundle.findEntries(
			"com/liferay/frontend/theme/fjord/group/initializer/internal" +
				"/dependencies/fragments",
			"*.html", false);

		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();

			String fragmentName = FileUtil.getShortFileName(url.getPath());
			String fragmenHTML = StringUtil.read(url.openStream());

			fragmenHTML = StringUtil.replace(
				fragmenHTML, StringPool.DOLLAR, StringPool.DOLLAR,
				dlFileEntries);

			FragmentEntry fragmentEntry =
				_fragmentEntryLocalService.addFragmentEntry(
					serviceContext.getUserId(),
					serviceContext.getScopeGroupId(), fragmentCollectionId,
					fragmentName, StringPool.BLANK, fragmenHTML,
					StringPool.BLANK, WorkflowConstants.STATUS_APPROVED,
					serviceContext);

			fragmentEntries.put(
				fragmentName, fragmentEntry.getFragmentEntryId());
		}

		return fragmentEntries;
	}

	private void _createLayout(
			ServiceContext serviceContext, long layoutPageTemplateCollectionId,
			String layoutName, long[] fragmentEntryIds)
		throws PortalException {

		long layoutPageTemplateEntryId = _createLayoutPageTemplateEntry(
			serviceContext, layoutPageTemplateCollectionId, layoutName);

		_layoutPageTemplateEntryLocalService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntryId, layoutName, fragmentEntryIds, "",
			serviceContext);

		boolean privateLayout = false;
		long parentLayoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getSiteDefault(), layoutName);

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put(
			"layoutPageTemplateEntryId",
			String.valueOf(layoutPageTemplateEntryId));

		_layoutLocalService.addLayout(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			privateLayout, parentLayoutId, nameMap,
			new HashMap<Locale, String>(), new HashMap<Locale, String>(),
			new HashMap<Locale, String>(), new HashMap<Locale, String>(),
			"content", typeSettingsProperties.toString(), false,
			new HashMap<Locale, String>(), serviceContext);
	}

	private long _createLayoutPageTemplateCollection(
			ServiceContext serviceContext)
		throws PortalException {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			_layoutPageTemplateCollectionLocalService.
				addLayoutPageTemplateCollection(
					serviceContext.getUserId(),
					serviceContext.getScopeGroupId(), "Fjord", "Fjord",
					serviceContext);

		return layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();
	}

	private long _createLayoutPageTemplateEntry(
			ServiceContext serviceContext, long layoutPageTemplateCollectionId,
			String layoutName)
		throws PortalException {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				layoutPageTemplateCollectionId, layoutName,
				LayoutPageTemplateEntryTypeConstants.TYPE_BASIC,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		return layoutPageTemplateEntry.getLayoutPageTemplateEntryId();
	}

	private long[] _getFragmentEntryIds(
		List<String> fragmentEntryNames, Map<String, Long> fragmentEntries) {

		Stream<String> fragmentEntryNameStream = fragmentEntryNames.stream();

		return fragmentEntryNameStream.mapToLong(
			fragmentEntries::get
		).toArray();
	}

	private ServiceContext _getServiceContext(long groupId)
		throws PortalException {

		User user = _userLocalService.getUser(PrincipalThreadLocal.getUserId());

		Locale locale = LocaleUtil.getSiteDefault();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(user.getUserId());
		serviceContext.setTimeZone(user.getTimeZone());

		return serviceContext;
	}

	private void _updateLookAndFeel(ServiceContext serviceContext)
		throws PortalException {

		Theme theme = _themeLocalService.fetchTheme(
			serviceContext.getCompanyId(), _FJORD_THEME_ID);

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"No Theme registered with themeId: " + _FJORD_THEME_ID);
			}

			return;
		}

		_layoutSetLocalService.updateLookAndFeel(
			serviceContext.getScopeGroupId(), false, _FJORD_THEME_ID,
			StringPool.BLANK, StringPool.BLANK);
	}

	private static final String _FJORD_THEME_ID = "fjord_WAR_fjordtheme";

	private static final Log _log = LogFactoryUtil.getLog(
		FjordGroupInitializer.class);

	private Bundle _bundle;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private FragmentCollectionLocalService _fragmentCollectionLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateCollectionLocalService
		_layoutPageTemplateCollectionLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.theme.fjord.group.initializer)"
	)
	private ServletContext _servletContext;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private UserLocalService _userLocalService;

}