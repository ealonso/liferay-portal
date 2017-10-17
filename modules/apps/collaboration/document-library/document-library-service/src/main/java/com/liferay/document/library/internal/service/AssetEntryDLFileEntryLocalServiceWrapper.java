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

import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceWrapper;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.document.library.kernel.util.DLFileVersionPolicy;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryDLFileEntryLocalServiceWrapper
	extends DLFileEntryLocalServiceWrapper {

	public AssetEntryDLFileEntryLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryDLFileEntryLocalServiceWrapper(
		DLFileEntryLocalService dlFileEntryLocalService) {

		super(dlFileEntryLocalService);
	}

	@Override
	public DLFileVersion cancelCheckOut(long userId, long fileEntryId)
		throws PortalException {

		DLFileVersion dlFileVersion = super.cancelCheckOut(userId, fileEntryId);

		if (!isFileEntryCheckedOut(fileEntryId)) {
			return null;
		}

		_assetEntryLocalService.deleteEntry(
			DLFileEntryConstants.getClassName(), dlFileVersion.getPrimaryKey());

		return dlFileVersion;
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, boolean majorVersion,
			String changeLog, ServiceContext serviceContext)
		throws PortalException {

		super.checkInFileEntry(
			userId, fileEntryId, majorVersion, changeLog, serviceContext);

		if (!isFileEntryCheckedOut(fileEntryId)) {
			return;
		}

		DLFileEntry dlFileEntry = getDLFileEntry(fileEntryId);

		DLFileVersion lastDLFileVersion =
			_dlFileVersionLocalService.getFileVersion(
				dlFileEntry.getFileEntryId(), dlFileEntry.getVersion());

		DLFileVersion latestDLFileVersion =
			_dlFileVersionLocalService.getLatestFileVersion(fileEntryId, false);

		if (_dlFileVersionPolicy.isKeepFileVersionLabel(
				lastDLFileVersion, latestDLFileVersion, majorVersion,
				serviceContext)) {

			_assetEntryLocalService.deleteEntry(
				DLFileEntryConstants.getClassName(),
				latestDLFileVersion.getPrimaryKey());
		}
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private DLFileVersionLocalService _dlFileVersionLocalService;

	@Reference
	private DLFileVersionPolicy _dlFileVersionPolicy;

}