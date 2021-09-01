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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceWrapper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.interval.IntervalActionProcessor;
import com.liferay.portal.kernel.repository.event.RepositoryEventTrigger;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.util.RepositoryUtil;
import com.liferay.trash.TrashHelper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ServiceWrapper.class)
public class TrashDLFileEntryLocalServiceWrapper
	extends DLFileEntryLocalServiceWrapper {

	public TrashDLFileEntryLocalServiceWrapper() {
		super(null);
	}

	public TrashDLFileEntryLocalServiceWrapper(
		DLFileEntryLocalService dlFileEntryLocalService) {

		super(dlFileEntryLocalService);
	}

	@Override
	public void deleteFileEntries(
			long groupId, long folderId, boolean includeTrashedEntries)
		throws PortalException {

		RepositoryEventTrigger repositoryEventTrigger =
			getFolderRepositoryEventTrigger(groupId, folderId);

		ActionableDynamicQuery actionableDynamicQuery =
			_dlFileEntryLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property folderIdproperty = PropertyFactoryUtil.forName(
					"folderId");

				dynamicQuery.add(folderIdproperty.eq(folderId));
			});
		actionableDynamicQuery.setGroupId(groupId);
		actionableDynamicQuery.setPerformActionMethod(
			(DLFileEntry dlFileEntry) -> {
				if (includeTrashedEntries ||
					!_trashHelper.isInTrashExplicitly(dlFileEntry)) {

					repositoryEventTrigger.trigger(
						RepositoryEventType.Delete.class, FileEntry.class,
						new LiferayFileEntry(dlFileEntry));

					_dlFileEntryLocalService.deleteFileEntry(dlFileEntry);
				}
			});

		actionableDynamicQuery.performActions();
	}

	@Override
	public void deleteRepositoryFileEntries(
			long repositoryId, long folderId, boolean includeTrashedEntries)
		throws PortalException {

		RepositoryEventTrigger repositoryEventTrigger =
			RepositoryUtil.getRepositoryEventTrigger(repositoryId);

		int total = _dlFileEntryLocalService.getRepositoryFileEntriesCount(
			repositoryId, folderId);

		IntervalActionProcessor<Void> intervalActionProcessor =
			new IntervalActionProcessor<>(total);

		intervalActionProcessor.setPerformIntervalActionMethod(
			(start, end) -> {
				List<DLFileEntry> dlFileEntries =
					_dlFileEntryLocalService.getRepositoryFileEntries(
						repositoryId, folderId, start, end);

				for (DLFileEntry dlFileEntry : dlFileEntries) {
					if (includeTrashedEntries ||
						!_trashHelper.isInTrashExplicitly(dlFileEntry)) {

						repositoryEventTrigger.trigger(
							RepositoryEventType.Delete.class, FileEntry.class,
							new LiferayFileEntry(dlFileEntry));

						_dlFileEntryLocalService.deleteFileEntry(dlFileEntry);
					}
					else {
						intervalActionProcessor.incrementStart();
					}
				}

				return null;
			});

		intervalActionProcessor.performIntervalActions();
	}

	protected RepositoryEventTrigger getFolderRepositoryEventTrigger(
			long groupId, long folderId)
		throws PortalException {

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return RepositoryUtil.getFolderRepositoryEventTrigger(folderId);
		}

		return RepositoryUtil.getRepositoryEventTrigger(groupId);
	}

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private TrashHelper _trashHelper;

}