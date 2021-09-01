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

package com.liferay.document.library.internal.service;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceWrapper;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.expando.kernel.service.ExpandoRowLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.WebDAVProps;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.repository.event.RepositoryEventTrigger;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.WebDAVPropsLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portal.util.RepositoryUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalService;
import com.liferay.trash.TrashHelper;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ServiceWrapper.class)
public class TrashDLFolderLocalServiceWrapper
	extends DLFolderLocalServiceWrapper {

	public TrashDLFolderLocalServiceWrapper() {
		super(null);
	}

	public TrashDLFolderLocalServiceWrapper(
		DLFolderLocalService dlFolderLocalService) {

		super(dlFolderLocalService);
	}

	@Override
	public DLFolder deleteFolder(
			DLFolder dlFolder, boolean includeTrashedEntries)
		throws PortalException {

		deleteSubfolders(dlFolder, includeTrashedEntries);

		deleteFolderDependencies(dlFolder, includeTrashedEntries);

		return dlFolder;
	}

	protected void deleteFolderDependencies(
			DLFolder dlFolder, boolean includeTrashedEntries)
		throws PortalException {

		// Resources

		_resourceLocalService.deleteResource(
			dlFolder.getCompanyId(), DLFolder.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, dlFolder.getFolderId());

		// WebDAVProps

		WebDAVProps webDAVProps = _webDAVPropsLocalService.getWebDAVProps(
			_classNameLocalService.getClassNameId(DLFolder.class),
			dlFolder.getFolderId());

		if (webDAVProps != null) {
			_webDAVPropsLocalService.deleteWebDAVProps(webDAVProps);
		}

		// File entries

		_dlFileEntryLocalService.deleteFileEntries(
			dlFolder.getGroupId(), dlFolder.getFolderId(),
			includeTrashedEntries);

		// File entry types

		List<Long> fileEntryTypeIds = new ArrayList<>();

		for (DLFileEntryType dlFileEntryType :
				_dLFileEntryTypeLocalService.getDLFolderDLFileEntryTypes(
					dlFolder.getFolderId())) {

			fileEntryTypeIds.add(dlFileEntryType.getFileEntryTypeId());
		}

		if (fileEntryTypeIds.isEmpty()) {
			fileEntryTypeIds.add(
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL);
		}

		_dLFileEntryTypeLocalService.unsetFolderFileEntryTypes(
			dlFolder.getFolderId());

		// File shortcuts

		_dlFileShortcutLocalService.deleteFileShortcuts(
			dlFolder.getGroupId(), dlFolder.getFolderId(),
			includeTrashedEntries);

		// Expando

		_expandoRowLocalService.deleteRows(dlFolder.getFolderId());

		// Ratings

		_ratingsStatsLocalService.deleteStats(
			DLFolder.class.getName(), dlFolder.getFolderId());

		// Folder

		_dlFolderLocalService.deleteFolder(dlFolder);

		// Directory

		if (includeTrashedEntries) {
			DLStoreUtil.deleteDirectory(
				dlFolder.getCompanyId(), dlFolder.getFolderId(),
				StringPool.BLANK);
		}

		// Workflow

		for (long fileEntryTypeId : fileEntryTypeIds) {
			WorkflowDefinitionLink workflowDefinitionLink =
				_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
					dlFolder.getCompanyId(), dlFolder.getGroupId(),
					DLFolder.class.getName(), dlFolder.getFolderId(),
					fileEntryTypeId);

			if (workflowDefinitionLink != null) {
				_workflowDefinitionLinkLocalService.
					deleteWorkflowDefinitionLink(workflowDefinitionLink);
			}
		}
	}

	protected void deleteSubfolders(
			DLFolder dlFolder, boolean includeTrashedEntries)
		throws PortalException {

		RepositoryEventTrigger repositoryEventTrigger =
			RepositoryUtil.getRepositoryEventTrigger(
				dlFolder.getRepositoryId());

		List<DLFolder> dlFolders = _dlFolderLocalService.getFolders(
			dlFolder.getGroupId(), dlFolder.getFolderId());

		for (DLFolder curDLFolder : dlFolders) {
			if (includeTrashedEntries ||
				!_trashHelper.isInTrashExplicitly(curDLFolder)) {

				repositoryEventTrigger.trigger(
					RepositoryEventType.Delete.class, Folder.class,
					new LiferayFolder(curDLFolder));

				_dlFolderLocalService.deleteFolder(
					curDLFolder, includeTrashedEntries);
			}
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileEntryTypeLocalService _dLFileEntryTypeLocalService;

	@Reference
	private DLFileShortcutLocalService _dlFileShortcutLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

	@Reference
	private ExpandoRowLocalService _expandoRowLocalService;

	@Reference
	private RatingsStatsLocalService _ratingsStatsLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private TrashHelper _trashHelper;

	@Reference
	private WebDAVPropsLocalService _webDAVPropsLocalService;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}