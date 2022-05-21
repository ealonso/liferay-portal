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

package com.liferay.fragment.internal.util.configuration.provider;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.contributor.FragmentCollectionContributorTracker;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.fragment.util.configuration.provider.FragmentEntryConfigurationProvider;
import com.liferay.fragment.util.configuration.provider.FragmentEntryConfigurationProviderTracker;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = FragmentEntryConfigurationProviderTracker.class)
public class FragmentEntryConfigurationProviderTrackerImpl
	implements FragmentEntryConfigurationProviderTracker {

	@Override
	public FragmentEntryConfigurationProvider
		getFragmentEntryConfigurationProvider(
			long fragmentEntryId, String fragmentEntryKey) {

		FragmentEntry fragmentEntry = null;

		if (Validator.isNotNull(fragmentEntryKey)) {
			fragmentEntry =
				_fragmentCollectionContributorTracker.getFragmentEntry(
					fragmentEntryKey);
		}

		if (fragmentEntry == null) {
			fragmentEntry = _fragmentEntryLocalService.fetchFragmentEntry(
				fragmentEntryId);
		}

		if (fragmentEntry == null) {
			return null;
		}

		return getFragmentEntryConfigurationProvider(
			FragmentConstants.getTypeLabel(fragmentEntry.getType()));
	}

	@Override
	public FragmentEntryConfigurationProvider
		getFragmentEntryConfigurationProvider(String type) {

		return _fragmentEntryConfigurationProviderServiceTrackerMap.getService(
			type);
	}

	@Override
	public List<FragmentEntryConfigurationProvider>
		getFragmentEntryConfigurationProviders() {

		return new ArrayList(
			_fragmentEntryConfigurationProviderServiceTrackerMap.values());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_fragmentEntryConfigurationProviderServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext,
				(Class<FragmentEntryConfigurationProvider>)
					(Class<?>)FragmentEntryConfigurationProvider.class,
				null,
				(serviceReference, emitter) -> {
					FragmentEntryConfigurationProvider
						fragmentEntryConfigurationProvider =
							bundleContext.getService(serviceReference);

					try {
						emitter.emit(
							fragmentEntryConfigurationProvider.getType());
					}
					finally {
						bundleContext.ungetService(serviceReference);
					}
				},
				new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Reference
	private FragmentCollectionContributorTracker
		_fragmentCollectionContributorTracker;

	private ServiceTrackerMap<String, FragmentEntryConfigurationProvider>
		_fragmentEntryConfigurationProviderServiceTrackerMap;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

}