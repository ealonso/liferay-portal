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

package com.liferay.asset.display.template.contributor;

import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Pavel Savinov
 */
public class AssetDisplayTemplateContributorRegistryUtil {

	public static Collection<AssetDisplayTemplateContributor>
		getAssetDisplayTemplateContributors() {

		return _instance._assetDisplayTemplateContributors.values();
	}

	private AssetDisplayTemplateContributorRegistryUtil() {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetDisplayTemplateContributorRegistryUtil.class);

		_bundleContext = bundle.getBundleContext();

		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext, AssetDisplayTemplateContributor.class,
			new AssetDisplayTemplateContributorServiceTrackerCustomizer());
	}

	private static final AssetDisplayTemplateContributorRegistryUtil _instance =
		new AssetDisplayTemplateContributorRegistryUtil();

	private final Map<Long, AssetDisplayTemplateContributor>
		_assetDisplayTemplateContributors = new ConcurrentHashMap<>();
	private final BundleContext _bundleContext;
	private final ServiceTracker
		<AssetDisplayTemplateContributor, AssetDisplayTemplateContributor>
			_serviceTracker;

	private class AssetDisplayTemplateContributorServiceTrackerCustomizer
		implements
			ServiceTrackerCustomizer
				<AssetDisplayTemplateContributor,
					AssetDisplayTemplateContributor> {

		@Override
		public AssetDisplayTemplateContributor addingService(
			ServiceReference<AssetDisplayTemplateContributor>
				serviceReference) {

			AssetDisplayTemplateContributor assetDisplayTemplateContributor =
				_bundleContext.getService(serviceReference);

			long classNameId = assetDisplayTemplateContributor.getClassNameId();

			_assetDisplayTemplateContributors.put(
				classNameId, assetDisplayTemplateContributor);

			return assetDisplayTemplateContributor;
		}

		@Override
		public void modifiedService(
			ServiceReference<AssetDisplayTemplateContributor> serviceReference,
			AssetDisplayTemplateContributor assetDisplayTemplateContributor) {

			removedService(serviceReference, assetDisplayTemplateContributor);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<AssetDisplayTemplateContributor> serviceReference,
			AssetDisplayTemplateContributor assetDisplayTemplateContributor) {

			_bundleContext.ungetService(serviceReference);

			long classNameId = assetDisplayTemplateContributor.getClassNameId();

			_assetDisplayTemplateContributors.remove(classNameId);
		}

	}

}