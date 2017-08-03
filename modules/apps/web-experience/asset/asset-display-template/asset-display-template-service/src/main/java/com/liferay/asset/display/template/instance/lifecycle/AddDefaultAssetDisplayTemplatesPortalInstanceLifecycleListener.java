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

package com.liferay.asset.display.template.instance.lifecycle;

import com.liferay.asset.display.template.contributor.AssetDisplayTemplateContributor;
import com.liferay.asset.display.template.contributor.AssetDisplayTemplateContributorRegistryUtil;
import com.liferay.asset.display.template.model.AssetDisplayTemplate;
import com.liferay.asset.display.template.service.AssetDisplayTemplateLocalService;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class AddDefaultAssetDisplayTemplatesPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		for (AssetDisplayTemplateContributor assetDisplayTemplateContributor :
				AssetDisplayTemplateContributorRegistryUtil.
					getAssetDisplayTemplateContributors()) {

			AssetDisplayTemplate assetDisplayTemplate =
				_assetDisplayTemplateLocalService.fetchAssetDisplayTemplate(
					company.getGroupId(),
					assetDisplayTemplateContributor.getClassNameId());

			if (assetDisplayTemplate != null) {
				continue;
			}

			User defaultUser = company.getDefaultUser();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(company.getCompanyId());
			serviceContext.setScopeGroupId(company.getGroupId());

			_assetDisplayTemplateLocalService.addAssetDisplayTemplate(
				company.getGroupId(), defaultUser.getUserId(),
				assetDisplayTemplateContributor.getName(),
				assetDisplayTemplateContributor.getClassNameId(),
				assetDisplayTemplateContributor.getLanguage(),
				assetDisplayTemplateContributor.getScriptContent(), true,
				serviceContext);
		}
	}

	@Reference
	private AssetDisplayTemplateLocalService _assetDisplayTemplateLocalService;

}