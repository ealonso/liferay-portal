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

import com.liferay.asset.model.AssetCategory;
import com.liferay.asset.model.AssetTag;
import com.liferay.asset.service.AssetCategoryLocalService;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicyUtil;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.OrganizationServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryOrganizationServiceWrapper
	extends OrganizationServiceWrapper {

	public AssetEntryOrganizationServiceWrapper() {
		super(null);
	}

	public AssetEntryOrganizationServiceWrapper(
		OrganizationService organizationService) {

		super(organizationService);
	}

	@Override
	public Organization updateOrganization(
			long organizationId, long parentOrganizationId, String name,
			String type, long regionId, long countryId, long statusId,
			String comments, boolean logo, byte[] logoBytes, boolean site,
			List<Address> addresses, List<EmailAddress> emailAddresses,
			List<OrgLabor> orgLabors, List<Phone> phones,
			List<Website> websites, ServiceContext serviceContext)
		throws PortalException {

		List<AssetCategory> oldAssetCategories =
			_assetCategoryLocalService.getCategories(
				Organization.class.getName(), organizationId);

		List<AssetTag> oldAssetTags = _assetTagLocalService.getTags(
			Organization.class.getName(), organizationId);

		Organization oldOrganization = getOrganization(organizationId);

		ExpandoBridge oldExpandoBridge = oldOrganization.getExpandoBridge();

		Map<String, Serializable> oldExpandoAttributes =
			oldExpandoBridge.getAttributes();

		Organization organization = getOrganization(organizationId);

		OrganizationMembershipPolicyUtil.verifyPolicy(
			organization, oldOrganization,
			ModelAdapterUtil.adapt(
				com.liferay.asset.kernel.model.AssetCategory.class,
				oldAssetCategories),
			ModelAdapterUtil.adapt(
				com.liferay.asset.kernel.model.AssetTag.class, oldAssetTags),
			oldExpandoAttributes);

		return super.updateOrganization(
			organizationId, parentOrganizationId, name, type, regionId,
			countryId, statusId, comments, logo, logoBytes, site, addresses,
			emailAddresses, orgLabors, phones, websites, serviceContext);
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

}