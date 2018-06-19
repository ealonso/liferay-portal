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

package com.liferay.frontend.theme.westeros.bank.site.initializer.internal;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.util.DefaultDDMStructureHelper;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryModel;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;

import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
	property = "site.initializer.key=" + WesterosBankSiteInitializer.KEY
)
public class WesterosBankSiteInitializer implements SiteInitializer {

	public static final String KEY = "westeros-bank-site-initializer";

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
		return _THEME_NAME;
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() + "/images/thumbnail.png";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			ServiceContext serviceContext = _createServiceContext(groupId);

			_updateLookAndFeel(serviceContext);

			Folder folder = _dlAppLocalService.addFolder(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, _THEME_NAME,
				StringPool.BLANK, serviceContext);

			List<FileEntry> fileEntries = _addFileEntries(
				folder.getFolderId(), serviceContext);

			Map<String, String> fileEntriesMap = _getFileEntriesMap(
				fileEntries);

			FragmentCollection fragmentCollection = _addFragmentCollection(
				serviceContext);

			LayoutPageTemplateCollection layoutPageTemplateCollection =
				_addLayoutPageTemplateCollection(serviceContext);

			List<FragmentEntry> personalFragmentEntries = new ArrayList<>();

			FragmentEntry carouselFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/personal", "carousel.html",
				serviceContext);

			personalFragmentEntries.add(carouselFragmentEntry);

			FragmentEntry featuresFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/personal", "features.html",
				serviceContext);

			personalFragmentEntries.add(featuresFragmentEntry);

			FragmentEntry newsFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/personal", "news.html",
				serviceContext);

			personalFragmentEntries.add(newsFragmentEntry);

			FragmentEntry offeringsFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/personal", "offerings.html",
				serviceContext);

			personalFragmentEntries.add(offeringsFragmentEntry);

			FragmentEntry linksFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments", "links.html",
				serviceContext);

			personalFragmentEntries.add(linksFragmentEntry);

			Class<?> clazz = getClass();

			_defaultDDMStructureHelper.addDDMStructures(
				serviceContext.getUserId(), groupId,
				_portal.getClassNameId(JournalArticle.class),
				clazz.getClassLoader(), "com/liferay/frontend/theme/westeros" +
										"/bank/site/initializer/internal/dependencies/ddm" +
										"/carousel.xml",
				serviceContext);

			URL carouselContentURL = _bundle.getEntry(_PATH + "/ddm/content/carousel.xml");

			String content = StringUtil.replace(
				StringUtil.read(carouselContentURL.openStream()), StringPool.DOLLAR,
				StringPool.DOLLAR, fileEntriesMap);

			JournalArticle article = _journalArticleLocalService.addArticle(
				serviceContext.getUserId(), groupId, 0,
				Collections.singletonMap(LocaleUtil.US, "Carousel"), null, content,
				"CAROUSEL", "CAROUSEL", serviceContext);


			LayoutPageTemplateEntry personalLayoutPageTemplate = _addLayoutPageTemplateEntry(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"For You", personalFragmentEntries, _PATH + "/fragments" +
					"/personal/thumbnail.jpg",	serviceContext);

			Layout personalLayout = _addLayout("For You",
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				personalLayoutPageTemplate.getLayoutPageTemplateEntryId(),
				serviceContext);

			_addLayout("Checking and Credit Cards", personalLayout.getLayoutId(), serviceContext);
			_addLayout("Savings and Investments", personalLayout.getLayoutId(), serviceContext);
			_addLayout("Loans and Mortgages", personalLayout.getLayoutId(), serviceContext);
			_addLayout("Assurance", personalLayout.getLayoutId(), serviceContext);







			List<FragmentEntry> businessFragmentEntries = new ArrayList<>();

			FragmentEntry videoFragmentEntry = _addFragmentEntry(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/business", "video.html",
				serviceContext);

			businessFragmentEntries.add(videoFragmentEntry);

			businessFragmentEntries.add(linksFragmentEntry);

			LayoutPageTemplateEntry businessLayoutPageTemplate = _addLayoutPageTemplateEntry(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"For Your Business", businessFragmentEntries, _PATH +
					"/fragments/business/thumbnail.jpg", serviceContext);

			Layout businessLayout = _addLayout("For Your Business",
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				businessLayoutPageTemplate.getLayoutPageTemplateEntryId(),
				serviceContext);

			_addLayout("Credit Cards for Business", businessLayout.getLayoutId(), serviceContext);
			_addLayout("Assurance for Business", businessLayout.getLayoutId(), serviceContext);


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

	private List<FileEntry> _addFileEntries(
			long folderId, ServiceContext serviceContext)
		throws Exception {

		List<FileEntry> fileEntries = new ArrayList<>();

		Enumeration<URL> urls = _bundle.findEntries(
			_PATH + "/images", StringPool.STAR, false);

		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();

			byte[] bytes = null;

			try (InputStream is = url.openStream()) {
				bytes = FileUtil.getBytes(is);
			}

			String fileName = FileUtil.getShortFileName(url.getPath());

			FileEntry fileEntry = _dlAppLocalService.addFileEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				folderId, fileName, null, fileName, StringPool.BLANK,
				StringPool.BLANK, bytes, serviceContext);

			fileEntries.add(fileEntry);
		}

		return fileEntries;
	}

	private FragmentCollection _addFragmentCollection(
			ServiceContext serviceContext)
		throws PortalException {

		return _fragmentCollectionLocalService.addFragmentCollection(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			_THEME_NAME, null, serviceContext);
	}

	private FragmentEntry _addFragmentEntry(
			long fragmentCollectionId, Map<String, String> fileEntriesMap,
			String path, String fileName, ServiceContext serviceContext)
		throws Exception {

		URL url = _bundle.getEntry(path + "/" + fileName);

		String html = StringUtil.replace(
			StringUtil.read(url.openStream()), StringPool.DOLLAR,
			StringPool.DOLLAR, fileEntriesMap);

		String shortFileName = FileUtil.getShortFileName(url.getPath());

		long previewFileEntryId = _getPreviewFileEntryId(
			path, shortFileName, serviceContext);

		FragmentEntry fragmentEntry =
			_fragmentEntryLocalService.addFragmentEntry(
				serviceContext.getUserId(),
				serviceContext.getScopeGroupId(), fragmentCollectionId,
				FileUtil.getShortFileName(url.getPath()), StringPool.BLANK,
				html, StringPool.BLANK, previewFileEntryId,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		return fragmentEntry;
	}

	private Layout _addLayout(String name, long parentLayoutId, Long layoutPageTemplateEntryId, UnicodeProperties typeSettingsProperties, ServiceContext serviceContext) throws Exception {
		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getSiteDefault(), name);

		if (Validator.isNotNull(layoutPageTemplateEntryId)) {
			typeSettingsProperties.put(
				"layoutPageTemplateEntryId",
				String.valueOf(layoutPageTemplateEntryId));
		}

		return _layoutLocalService.addLayout(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), false,
			parentLayoutId, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(), "content",
			typeSettingsProperties.toString(), false, new HashMap<>(),
			serviceContext);
	}

	private Layout _addLayout(String name, long parentLayoutId, Long layoutPageTemplateEntryId, ServiceContext serviceContext) throws Exception {
		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		return _addLayout(name, parentLayoutId, layoutPageTemplateEntryId, typeSettingsProperties, serviceContext);
	}

	private Layout _addLayout(String name, long parentLayoutId, ServiceContext serviceContext) throws Exception {
		return _addLayout(name, parentLayoutId, null, serviceContext);
	}

	private LayoutPageTemplateEntry _addLayoutPageTemplateEntry(
		long layoutPageTemplateCollectionId, String name,
		List<FragmentEntry> fragmentEntries, String thumbnailPath,
		ServiceContext serviceContext) throws Exception {

		long previewFileEntryId = _getPreviewFileEntryId(
			thumbnailPath, "thumbnail.jpg",
			serviceContext);

		LayoutPageTemplateEntry layoutPageTemplateEntry = _layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			layoutPageTemplateCollectionId, name,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, 0,
			previewFileEntryId, WorkflowConstants.STATUS_APPROVED,
			serviceContext);

		long[] fragmentEntryIds = ListUtil.toLongArray(
			fragmentEntries, FragmentEntryModel::getFragmentEntryId);

		return _layoutPageTemplateEntryLocalService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), name,
			fragmentEntryIds, StringPool.BLANK, serviceContext);
	}

	private LayoutPageTemplateCollection _addLayoutPageTemplateCollection(
			ServiceContext serviceContext)
		throws PortalException {

		return _layoutPageTemplateCollectionLocalService.
			addLayoutPageTemplateCollection(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				_THEME_NAME, _THEME_NAME, serviceContext);
	}

	private ServiceContext _createServiceContext(long groupId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		User user = _userLocalService.getUser(PrincipalThreadLocal.getUserId());

		Locale locale = LocaleUtil.getSiteDefault();

		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(user.getUserId());
		serviceContext.setTimeZone(user.getTimeZone());

		return serviceContext;
	}

	private Map<String, String> _getFileEntriesMap(List<FileEntry> fileEntries)
		throws PortalException {

		Map<String, String> fileEntriesMap = new HashMap<>();

		for (FileEntry fileEntry : fileEntries) {
			String fileEntryURL = DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, false);

			fileEntriesMap.put(fileEntry.getFileName(), fileEntryURL);
		}

		return fileEntriesMap;
	}

	private long _getPreviewFileEntryId(
			String path, String fileName, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(path);
		sb.append(StringPool.SLASH);
		sb.append(StringUtil.split(fileName, StringPool.PERIOD)[0]);
		sb.append(".jpg");

		URL url = _bundle.getEntry(sb.toString());

		if (url == null) {
			return 0;
		}

		Folder folder = _dlAppLocalService.getFolder(
			serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, _THEME_NAME);

		byte[] bytes = null;

		try (InputStream is = url.openStream()) {
			bytes = FileUtil.getBytes(is);
		}

		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			folder.getFolderId(), fileName, null, fileName, StringPool.BLANK,
			StringPool.BLANK, bytes, serviceContext);

		return fileEntry.getFileEntryId();
	}

	private void _updateLookAndFeel(ServiceContext serviceContext)
		throws PortalException {

		Theme theme = _themeLocalService.fetchTheme(
			serviceContext.getCompanyId(), _THEME_ID);

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info("No theme found for " + _THEME_ID);
			}

			return;
		}

		_layoutSetLocalService.updateLookAndFeel(
			serviceContext.getScopeGroupId(), false, _THEME_ID,
			StringPool.BLANK, StringPool.BLANK);
	}

	private static final String _PATH =
		"com/liferay/frontend/theme/westeros/bank/site/initializer/internal" +
			"/dependencies";

	private static final String _THEME_ID =
		"westerosbank_WAR_westerosbanktheme";

	private static final String _THEME_NAME = "Westeros Bank";

	private static final Log _log = LogFactoryUtil.getLog(
		WesterosBankSiteInitializer.class);

	private Bundle _bundle;

	@Reference
	private DefaultDDMStructureHelper _defaultDDMStructureHelper;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private FragmentCollectionLocalService _fragmentCollectionLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

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

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.theme.westeros.bank.site.initializer)"
	)
	private ServletContext _servletContext;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private UserLocalService _userLocalService;

}