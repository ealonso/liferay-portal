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

package com.liferay.site.internal.service;

import com.liferay.asset.model.AssetCategory;
import com.liferay.asset.model.AssetTag;
import com.liferay.asset.service.AssetCategoryLocalService;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.GroupServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryGroupServiceWrapper extends GroupServiceWrapper {

	public AssetEntryGroupServiceWrapper() {
		super(null);
	}

	public AssetEntryGroupServiceWrapper(GroupService groupService) {
		super(groupService);
	}

	@Override
	public Group updateGroup(
			long groupId, long parentGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int type,
			boolean manualMembership, int membershipRestriction,
			String friendlyURL, boolean inheritContent, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		Group oldGroup = getGroup(groupId);

		Group group = super.updateGroup(
			groupId, parentGroupId, nameMap, descriptionMap, type,
			manualMembership, membershipRestriction, friendlyURL,
			inheritContent, active, serviceContext);

		if (!group.isSite()) {
			return group;
		}

		List<AssetCategory> oldAssetCategories =
			_assetCategoryLocalService.getCategories(
				Group.class.getName(), groupId);

		List<AssetTag> oldAssetTags = _assetTagLocalService.getTags(
			Group.class.getName(), groupId);

		ExpandoBridge oldExpandoBridge = oldGroup.getExpandoBridge();

		Map<String, Serializable> oldExpandoAttributes =
			oldExpandoBridge.getAttributes();

		SiteMembershipPolicyUtil.verifyPolicy(
			group, oldGroup,
			ModelAdapterUtil.adapt(
				com.liferay.asset.kernel.model.AssetCategory.class,
				oldAssetCategories),
			ModelAdapterUtil.adapt(
				com.liferay.asset.kernel.model.AssetTag.class, oldAssetTags),
			oldExpandoAttributes, null);

		return group;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

}