/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.documentlibrary.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.TrashActionKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.util.DLAppHelperThreadLocal;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.trash.DuplicateEntryException;
import com.liferay.portlet.trash.TrashEntryConstants;
import com.liferay.portlet.trash.model.TrashEntry;
import com.liferay.portlet.trash.util.TrashUtil;

import javax.portlet.PortletRequest;

/**
 * Implements trash handling for the file entry entity.
 *
 * @author Alexander Chow
 * @author Manuel de la Peña
 * @author Zsolt Berentey
 */
public class DLFileEntryTrashHandler extends DLBaseTrashHandler {

	public static final String CLASS_NAME = DLFileEntry.class.getName();

	@Override
	public void checkDuplicateTrashEntry(
			TrashEntry trashEntry, long containerModelId, String newName)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(trashEntry.getClassPK());

		if (containerModelId == TrashEntryConstants.DEFAULT_CONTAINER_ID) {
			containerModelId = dlFileEntry.getFolderId();
		}

		String restoredTitle = dlFileEntry.getTitle();

		if (Validator.isNotNull(newName)) {
			restoredTitle = newName;
		}

		String originalTitle = TrashUtil.stripTrashNamespace(restoredTitle);

		DLFileEntry duplicateDLFileEntry =
			DLFileEntryLocalServiceUtil.fetchFileEntry(
				dlFileEntry.getGroupId(), containerModelId, originalTitle);

		if (duplicateDLFileEntry != null) {
			DuplicateEntryException dee = new DuplicateEntryException();

			dee.setDuplicateEntryId(duplicateDLFileEntry.getFileEntryId());
			dee.setOldName(duplicateDLFileEntry.getTitle());
			dee.setTrashEntryId(trashEntry.getEntryId());

			throw dee;
		}
	}

	public void deleteTrashEntries(long[] classPKs, boolean checkPermission)
		throws PortalException, SystemException {

		for (long classPK : classPKs) {
			if (checkPermission) {
				DLAppServiceUtil.deleteFileEntry(classPK);
			}
			else {
				DLAppLocalServiceUtil.deleteFileEntry(classPK);
			}
		}
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getRestoreLink(PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		return DLUtil.getDLControlPanelLink(
			portletRequest, dlFileEntry.getFolderId());
	}

	@Override
	public String getRestoreMessage(PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		return DLUtil.getAbsolutePath(
			portletRequest, dlFileEntry.getFolderId());
	}

	@Override
	public boolean hasTrashPermission(
			PermissionChecker permissionChecker, long groupId, long classPK,
			String trashActionId)
		throws PortalException, SystemException {

		if (trashActionId.equals(TrashActionKeys.MOVE)) {
			return DLFolderPermission.contains(
				permissionChecker, groupId, classPK, ActionKeys.ADD_DOCUMENT);
		}

		return super.hasTrashPermission(
			permissionChecker, groupId, classPK, trashActionId);
	}

	public boolean isInTrash(long classPK)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

		if (dlFileEntry.isInTrashFolder() || dlFileVersion.isInTrash()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		return !dlFileEntry.isInTrashFolder();
	}

	@Override
	public void moveTrashEntry(
			long classPK, long containerId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLAppServiceUtil.moveFileEntryFromTrash(
			classPK, containerId, serviceContext);
	}

	public void restoreTrashEntries(long[] classPKs)
		throws PortalException, SystemException {

		for (long classPK : classPKs) {
			boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

			try {
				DLFileEntry dlFileEntry = getDLFileEntry(classPK);

				if (dlFileEntry.isInHiddenFolder()) {
					DLAppHelperThreadLocal.setEnabled(false);
				}

				DLAppServiceUtil.restoreFileEntryFromTrash(classPK);
			}
			finally {
				DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
			}
		}
	}

	@Override
	public void updateTitle(long classPK, String name)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		dlFileEntry.setTitle(name);

		DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);

		DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

		dlFileVersion.setTitle(name);

		DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion, false);
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getDLFileEntry(classPK);

		if (dlFileEntry.isInHiddenFolder()) {
			return false;
		}

		return DLFileEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

}