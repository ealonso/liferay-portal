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

package com.liferay.layout.type.controller.asset.display.internal.access.policy;

import com.liferay.layout.type.controller.asset.display.constants.AssetDisplayLayoutTypeControllerConstants;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.impl.ModificationDeniedLayoutTypeAccessPolicyImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {"layout.type=" + AssetDisplayLayoutTypeControllerConstants.LAYOUT_TYPE_ASSET_DISPLAY},
	service = LayoutTypeAccessPolicy.class
)
public class AssetDisplayLayoutTypeAccessPolicy
	extends ModificationDeniedLayoutTypeAccessPolicyImpl {
}