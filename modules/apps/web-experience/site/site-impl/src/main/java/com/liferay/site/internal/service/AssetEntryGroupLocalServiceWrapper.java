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

import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.asset.service.AssetVocabularyLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryGroupLocalServiceWrapper
	extends GroupLocalServiceWrapper {

	public AssetEntryGroupLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryGroupLocalServiceWrapper(
		GroupLocalService groupLocalService) {

		super(groupLocalService);
	}

	@Override
	public Group addGroup(
			long userId, long parentGroupId, String className, long classPK,
			long liveGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int type,
			boolean manualMembership, int membershipRestriction,
			String friendlyURL, boolean site, boolean inheritContent,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		Group group = super.addGroup(
			userId, parentGroupId, className, classPK, liveGroupId, nameMap,
			descriptionMap, type, manualMembership, membershipRestriction,
			friendlyURL, site, inheritContent, active, serviceContext);

		User user = _userLocalService.getUser(userId);

		className = GetterUtil.getString(className);

		long classNameId = _classNameLocalService.getClassNameId(className);

		long groupClassNameId = _classNameLocalService.getClassNameId(
			Group.class);

		boolean staging = isStaging(serviceContext);

		if ((classNameId <= 0) || className.equals(Group.class.getName()) ||
			(className.equals(Company.class.getName()) && staging)) {

			classNameId = groupClassNameId;
		}

		if ((classNameId == groupClassNameId) && !user.isDefaultUser()) {
			if (serviceContext != null) {
				updateAsset(
					userId, group, serviceContext.getAssetCategoryIds(),
					serviceContext.getAssetTagNames());
			}
		}

		return group;
	}

	@Override
	public Group deleteGroup(Group group) throws PortalException {
		super.deleteGroup(group);

		boolean deleteInProcess = GroupThreadLocal.isDeleteInProcess();

		try {
			GroupThreadLocal.setDeleteInProcess(true);

			if (group.isRegularSite()) {
				_assetEntryLocalService.deleteEntry(
					Group.class.getName(), group.getGroupId());
			}

			_assetEntryLocalService.deleteGroupEntries(group.getGroupId());

			_assetTagLocalService.deleteGroupTags(group.getGroupId());

			_assetVocabularyLocalService.deleteVocabularies(group.getGroupId());
		}
		finally {
			GroupThreadLocal.setDeleteInProcess(deleteInProcess);
		}

		return group;
	}

	@Override
	public void updateAsset(
			long userId, Group group, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		super.updateAsset(userId, group, assetCategoryIds, assetTagNames);

		User user = _userLocalService.getUser(userId);

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		Group companyGroup = company.getGroup();

		_assetEntryLocalService.updateEntry(
			userId, companyGroup.getGroupId(), null, null,
			Group.class.getName(), group.getGroupId(), null, 0,
			assetCategoryIds, assetTagNames, true, false, null, null, null,
			null, null, group.getDescriptiveName(), group.getDescription(),
			null, null, null, 0, 0, null);
	}

	@Override
	public Group updateGroup(
			long groupId, long parentGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int type,
			boolean manualMembership, int membershipRestriction,
			String friendlyURL, boolean inheritContent, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		Group group = super.updateGroup(
			groupId, parentGroupId, nameMap, descriptionMap, type,
			manualMembership, membershipRestriction, friendlyURL,
			inheritContent, active, serviceContext);

		if ((serviceContext == null) || !group.isSite()) {
			return group;
		}

		User user = null;

		user = _userLocalService.getUser(group.getCreatorUserId());

		if (user == null) {
			user = _userLocalService.getUser(serviceContext.getUserId());
		}

		if (user == null) {
			user = _userLocalService.getDefaultUser(group.getCompanyId());
		}

		updateAsset(
			user.getUserId(), group, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return group;
	}

	protected boolean isStaging(ServiceContext serviceContext) {
		if (serviceContext != null) {
			return ParamUtil.getBoolean(serviceContext, "staging");
		}

		return false;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private UserLocalService _userLocalService;

}