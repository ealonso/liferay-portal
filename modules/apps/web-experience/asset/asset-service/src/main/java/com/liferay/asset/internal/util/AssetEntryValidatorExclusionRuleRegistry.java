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

package com.liferay.asset.internal.util;

import com.liferay.asset.kernel.validator.AssetEntryValidatorExclusionRule;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.osgi.util.StringPlus;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, service = AssetEntryValidatorExclusionRuleRegistry.class
)
public class AssetEntryValidatorExclusionRuleRegistry {

	public List<AssetEntryValidatorExclusionRule>
		getAssetEntryValidatorExclusionRules(String modelClassName) {

		return _assetEntryValidatorExclusionRulesMap.getService(modelClassName);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_assetEntryValidatorExclusionRulesMap =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, AssetEntryValidatorExclusionRule.class,
				"(model.class.name=*)",
				(serviceReference, emitter) -> {
					List<String> modelClassNames = StringPlus.asList(
						serviceReference.getProperty("model.class.name"));

					for (String modelClassName : modelClassNames) {
						emitter.emit(modelClassName);
					}
				});
	}

	@Deactivate
	protected void deactivate() {
		_assetEntryValidatorExclusionRulesMap.close();
	}

	private ServiceTrackerMap<String, List<AssetEntryValidatorExclusionRule>>
		_assetEntryValidatorExclusionRulesMap;

}