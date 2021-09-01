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

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLAppServiceWrapper;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.InvalidRepositoryIdException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryProvider;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.trash.service.TrashEntryService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ServiceWrapper.class)
public class TrashDLAppServiceWrapper extends DLAppServiceWrapper {

	public TrashDLAppServiceWrapper() {
		super(null);
	}

	public TrashDLAppServiceWrapper(DLAppService dlAppService) {
		super(dlAppService);
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
		Repository repository = _repositoryProvider.getFolderRepository(
			folderId);

		Folder folder = repository.getFolder(folderId);

		if (repository.isCapabilityProvided(TrashCapability.class)) {
			TrashCapability trashCapability = repository.getCapability(
				TrashCapability.class);

			if (trashCapability.isInTrash(folder)) {
				_trashEntryService.deleteEntry(
					DLFolderConstants.getClassName(), folder.getFolderId());

				return;
			}
		}

		List<FileEntry> fileEntries = repository.getRepositoryFileEntries(
			0, folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (FileEntry fileEntry : fileEntries) {
			_dlAppHelperLocalService.deleteFileEntry(fileEntry);
		}

		repository.deleteFolder(folderId);

		_dlAppHelperLocalService.deleteFolder(folder);
	}

	@Override
	public void deleteFolder(
			long repositoryId, long parentFolderId, String name)
		throws PortalException {

		Repository repository = getRepository(repositoryId);

		Folder folder = repository.getFolder(parentFolderId, name);

		if (repository.isCapabilityProvided(TrashCapability.class)) {
			TrashCapability trashCapability = repository.getCapability(
				TrashCapability.class);

			if (trashCapability.isInTrash(folder)) {
				_trashEntryService.deleteEntry(
					DLFolderConstants.getClassName(), folder.getFolderId());

				return;
			}
		}

		repository.deleteFolder(parentFolderId, name);
	}

	protected Repository getRepository(long repositoryId)
		throws PortalException {

		try {
			return _repositoryProvider.getRepository(repositoryId);
		}
		catch (InvalidRepositoryIdException invalidRepositoryIdException) {
			throw new NoSuchGroupException(
				StringBundler.concat(
					"No Group exists with the key {repositoryId=", repositoryId,
					"}"),
				invalidRepositoryIdException);
		}
	}

	@Reference
	private DLAppHelperLocalService _dlAppHelperLocalService;

	@Reference
	private RepositoryProvider _repositoryProvider;

	@Reference
	private TrashEntryService _trashEntryService;

}