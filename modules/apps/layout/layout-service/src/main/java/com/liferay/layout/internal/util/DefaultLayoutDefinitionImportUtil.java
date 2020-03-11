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

package com.liferay.layout.internal.util;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.layout.internal.service.DefaultLayoutLayoutSetPrototypeLocalServiceWrapper;
import com.liferay.layout.page.template.importer.LayoutPageTemplatesImporter;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = DefaultLayoutDefinitionImportUtil.class)
public class DefaultLayoutDefinitionImportUtil {

	public void importDefaultLayoutDefinition(
			Layout layout, ServiceContext serviceContext)
		throws PortalException {

		try {
			LayoutPageTemplateStructure layoutPageTemplateStructure =
				_layoutPageTemplateStructureLocalService.
					fetchLayoutPageTemplateStructure(
						layout.getGroupId(),
						_portal.getClassNameId(Layout.class.getName()),
						layout.getPlid(), true);

			LayoutStructure layoutStructure = LayoutStructure.of(
				layoutPageTemplateStructure.getData(
					SegmentsExperienceConstants.ID_DEFAULT));

			String layoutDefinitionJSON = StringUtil.replace(
				_DEFAULT_LAYOUT_DEFINITION, "${WELCOME_IMAGE_URL}",
				_getWelcomeImageURL(
					layout.getGroupId(), layout.getUserId(), layout.getPlid(),
					serviceContext));

			_layoutPageTemplatesImporter.importPageElement(
				layout, layoutStructure, layoutStructure.getMainItemId(),
				layoutDefinitionJSON, 0);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	private String _getWelcomeImageURL(
			long groupId, long userId, long plid, ServiceContext serviceContext)
		throws Exception {

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				groupId, Layout.class.getName());

		if (repository == null) {
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			repository = PortletFileRepositoryUtil.addPortletRepository(
				groupId, Layout.class.getName(), serviceContext);
		}

		FileEntry fileEntry = _portletFileRepository.fetchPortletFileEntry(
			groupId, repository.getDlFolderId(), _WELCOME_IMAGE_FILENAME);

		if (fileEntry == null) {
			byte[] bytes = _file.getBytes(
				DefaultLayoutLayoutSetPrototypeLocalServiceWrapper.class,
				_WELCOME_IMAGE_FILENAME);

			fileEntry = _portletFileRepository.addPortletFileEntry(
				groupId, userId, Layout.class.getName(), plid,
				Layout.class.getName(), repository.getDlFolderId(), bytes,
				_WELCOME_IMAGE_FILENAME,
				MimeTypesUtil.getContentType(_WELCOME_IMAGE_FILENAME), false);
		}

		return DLUtil.getDownloadURL(
			fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK);
	}

	private static final String _DEFAULT_LAYOUT_DEFINITION = StringUtil.read(
		DefaultLayoutLayoutSetPrototypeLocalServiceWrapper.class,
		"default-layout-definition.json");

	private static final String _WELCOME_IMAGE_FILENAME = "welcome_bg.jpg";

	@Reference
	private File _file;

	@Reference
	private LayoutPageTemplatesImporter _layoutPageTemplatesImporter;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletFileRepository _portletFileRepository;

}