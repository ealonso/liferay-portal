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
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryDLFolderLocalServiceWrapper
	extends DLFolderLocalServiceWrapper {

	public AssetEntryDLFolderLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryDLFolderLocalServiceWrapper(
		DLFolderLocalService dlFolderLocalService) {

		super(dlFolderLocalService);
	}

	@Override
	public DLFolder updateStatus(
			long userId, long folderId, int status,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		DLFolder dlFolder = super.updateStatus(
			userId, folderId, status, workflowContext, serviceContext);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			_assetEntryLocalService.updateVisible(
				DLFolder.class.getName(), dlFolder.getFolderId(), true);
		}
		else if (status == WorkflowConstants.STATUS_IN_TRASH) {
			_assetEntryLocalService.updateVisible(
				DLFolder.class.getName(), dlFolder.getFolderId(), false);
		}

		return dlFolder;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}