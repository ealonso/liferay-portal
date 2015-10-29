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

package com.liferay.portal.kernel.portlet.configuration.icon.locator;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Eudaldo Alonso
 */
public class PortletConfigurationIconLocatorTracker {

	public static List<PortletConfigurationIconLocator>
		getPortletConfigurationIconLocator() {

		return _portletConfigurationIconLocators;
	}

	public PortletConfigurationIconLocatorTracker() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			PortletConfigurationIconLocator.class,
			new PortletConfigurationIconLocatorServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final List<PortletConfigurationIconLocator>
		_portletConfigurationIconLocators = new CopyOnWriteArrayList<>();

	private final ServiceTracker
		<PortletConfigurationIconLocator, PortletConfigurationIconLocator>
			_serviceTracker;

	private static class PortletConfigurationIconLocatorServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<PortletConfigurationIconLocator, PortletConfigurationIconLocator> {

		@Override
		public PortletConfigurationIconLocator addingService(
			ServiceReference<PortletConfigurationIconLocator>
				serviceReference) {
			Registry registry = RegistryUtil.getRegistry();

			PortletConfigurationIconLocator portletConfigurationIconLocator =
				registry.getService(serviceReference);

			_portletConfigurationIconLocators.add(
				portletConfigurationIconLocator);

			return portletConfigurationIconLocator;
		}

		@Override
		public void modifiedService(
			ServiceReference<PortletConfigurationIconLocator> serviceReference,
			PortletConfigurationIconLocator portletConfigurationIconLocator) {
		}

		@Override
		public void removedService(
			ServiceReference<PortletConfigurationIconLocator> serviceReference,
			PortletConfigurationIconLocator portletConfigurationIconLocator) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_portletConfigurationIconLocators.remove(
				portletConfigurationIconLocator);
		}

	}

}