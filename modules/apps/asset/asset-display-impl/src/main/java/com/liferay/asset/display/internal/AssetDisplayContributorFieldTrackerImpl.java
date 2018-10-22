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

package com.liferay.asset.display.internal;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.asset.display.contributor.AssetDisplayContributorFieldTracker;
import com.liferay.asset.display.contributor.AssetDisplayField;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true, service = AssetDisplayContributorFieldTracker.class
)
public class AssetDisplayContributorFieldTrackerImpl
	implements AssetDisplayContributorFieldTracker {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, AssetDisplayContributorField.class,
			"(model.class.name=*)",
			(serviceReference, emitter) -> emitter.emit(
				(String)serviceReference.getProperty("model.class.name")));
	}

	@Deactivate
	public void deactivate() {
		_serviceTrackerMap.close();
	}

	@Override
	public List<AssetDisplayContributorField> getAssetDisplayContributorFields(
		String className) {

		List<AssetDisplayContributorField> assetDisplayContributorFields =
			_serviceTrackerMap.getService(className);

		if (assetDisplayContributorFields != null) {
			return assetDisplayContributorFields;
		}

		return Collections.emptyList();
	}

	@Override
	public Set<AssetDisplayField> getAssetDisplayFields(
		String className, Locale locale) {

		Set<AssetDisplayField> assetDisplayFields = new LinkedHashSet<>();

		List<AssetDisplayContributorField> assetDisplayContributorFields =
			getAssetDisplayContributorFields(className);

		for (AssetDisplayContributorField assetDisplayContributorField :
				assetDisplayContributorFields) {

			assetDisplayFields.add(
				new AssetDisplayField(
					assetDisplayContributorField.getKey(),
					assetDisplayContributorField.getLabel(locale)));
		}

		return assetDisplayFields;
	}

	@Override
	public Set<AssetDisplayField> getAssetEntryAssetDisplayFields(
		Locale locale) {

		Set<AssetDisplayField> assetDisplayFields = new LinkedHashSet<>();

		List<AssetDisplayContributorField> assetDisplayContributorFields =
			getAssetDisplayContributorFields(AssetEntry.class.getName());

		for (AssetDisplayContributorField assetDisplayContributorField :
				assetDisplayContributorFields) {

			assetDisplayFields.add(
				new AssetDisplayField(
					assetDisplayContributorField.getKey(),
					assetDisplayContributorField.getLabel(locale)));
		}

		return assetDisplayFields;
	}

	@Override
	public Map<String, Object> getAssetEntryAssetDisplayFieldsValues(
		AssetEntry assetEntry, Locale locale) {

		Map<String, Object> assetDisplayFieldsValues = new HashMap<>();

		List<AssetDisplayContributorField> assetDisplayContributorFields =
			getAssetDisplayContributorFields(AssetEntry.class.getName());

		for (AssetDisplayContributorField assetDisplayContributorField :
				assetDisplayContributorFields) {

			assetDisplayFieldsValues.put(
				assetDisplayContributorField.getKey(),
				assetDisplayContributorField.getValue(assetEntry, locale));
		}

		return assetDisplayFieldsValues;
	}

	private ServiceTrackerMap<String, List<AssetDisplayContributorField>>
		_serviceTrackerMap;

}