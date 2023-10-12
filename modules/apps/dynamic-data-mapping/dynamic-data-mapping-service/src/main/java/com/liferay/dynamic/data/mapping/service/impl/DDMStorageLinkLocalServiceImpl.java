/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.impl;

import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService;
import com.liferay.dynamic.data.mapping.service.base.DDMStorageLinkLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
@Component(
	property = "model.class.name=com.liferay.dynamic.data.mapping.model.DDMStorageLink",
	service = AopService.class
)
public class DDMStorageLinkLocalServiceImpl
	extends DDMStorageLinkLocalServiceBaseImpl {

	@Override
	public DDMStorageLink addStorageLink(
			long classNameId, long classPK, long structureVersionId,
			ServiceContext serviceContext)
		throws PortalException {

		DDMStructureVersion ddmStructureVersion =
			_ddmStructureVersionLocalService.getDDMStructureVersion(
				structureVersionId);

		long storageLinkId = counterLocalService.increment();

		DDMStorageLink storageLink = ddmStorageLinkPersistence.create(
			storageLinkId);

		storageLink.setClassNameId(classNameId);
		storageLink.setClassPK(classPK);
		storageLink.setStructureId(ddmStructureVersion.getStructureId());
		storageLink.setStructureVersionId(structureVersionId);

		return ddmStorageLinkPersistence.update(storageLink);
	}

	@Override
	public void deleteClassStorageLink(long classPK) {
		DDMStorageLink storageLink = ddmStorageLinkPersistence.fetchByClassPK(
			classPK);

		if (storageLink != null) {
			ddmStorageLinkPersistence.remove(storageLink);
		}
	}

	@Override
	public DDMStorageLink getClassStorageLink(long classPK)
		throws PortalException {

		return ddmStorageLinkPersistence.findByClassPK(classPK);
	}

	@Override
	public int getStructureStorageLinksCount(long structureId) {
		return ddmStorageLinkPersistence.countByStructureVersionId(
			ListUtil.toLongArray(
				_ddmStructureVersionLocalService.getStructureVersions(
					structureId),
				DDMStructureVersion::getStructureVersionId));
	}

	@Reference
	private DDMStructureVersionLocalService _ddmStructureVersionLocalService;

}