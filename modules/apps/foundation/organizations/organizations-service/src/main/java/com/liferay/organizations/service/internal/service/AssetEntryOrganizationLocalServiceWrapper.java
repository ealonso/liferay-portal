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

package com.liferay.organizations.service.internal.service;

import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryOrganizationLocalServiceWrapper
	extends OrganizationLocalServiceWrapper {

	public AssetEntryOrganizationLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryOrganizationLocalServiceWrapper(
		OrganizationLocalService organizationLocalService) {

		super(organizationLocalService);
	}

	@Override
	public Organization addOrganization(
			long userId, long parentOrganizationId, String name, String type,
			long regionId, long countryId, long statusId, String comments,
			boolean site, ServiceContext serviceContext)
		throws PortalException {

		Organization organization = super.addOrganization(
			userId, parentOrganizationId, name, type, regionId, countryId,
			statusId, comments, site, serviceContext);

		if (serviceContext != null) {
			updateAsset(
				userId, organization, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		return organization;
	}

	@Override
	public Organization deleteOrganization(Organization organization)
		throws PortalException {

		super.deleteOrganization(organization);

		_assetEntryLocalService.deleteEntry(
			Organization.class.getName(), organization.getOrganizationId());

		return organization;
	}

	@Override
	public void updateAsset(
			long userId, Organization organization, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		super.updateAsset(
			userId, organization, assetCategoryIds, assetTagNames);

		User user = _userLocalService.getUser(userId);

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		Group companyGroup = company.getGroup();

		_assetEntryLocalService.updateEntry(
			userId, companyGroup.getGroupId(), null, null,
			Organization.class.getName(), organization.getOrganizationId(),
			organization.getUuid(), 0, assetCategoryIds, assetTagNames, true,
			false, null, null, null, null, null, organization.getName(),
			StringPool.BLANK, null, null, null, 0, 0, null);
	}

	@Override
	public Organization updateOrganization(
			long companyId, long organizationId, long parentOrganizationId,
			String name, String type, long regionId, long countryId,
			long statusId, String comments, boolean logo, byte[] logoBytes,
			boolean site, ServiceContext serviceContext)
		throws PortalException {

		Organization organization = super.updateOrganization(
			companyId, organizationId, parentOrganizationId, name, type,
			regionId, countryId, statusId, comments, logo, logoBytes, site,
			serviceContext);

		if (serviceContext != null) {
			updateAsset(
				serviceContext.getUserId(), organization,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		return organization;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private UserLocalService _userLocalService;

}