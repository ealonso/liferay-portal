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

package com.liferay.template.web.internal.info.item.capability;

import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;
import com.liferay.info.exception.CapabilityVerificationException;
import com.liferay.info.item.InfoItemServiceVerifier;
import com.liferay.info.item.capability.InfoItemCapability;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	service = {InfoItemCapability.class, TemplateInfoItemCapability.class}
)
public class TemplateInfoItemCapabilityImpl
	implements TemplateInfoItemCapability {

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return "templates";
	}

	@Override
	public void verify(String itemClassName)
		throws CapabilityVerificationException {

		List<Class<?>> missingServiceClasses =
			_infoItemServiceVerifier.getMissingServiceClasses(
				REQUIRED_INFO_ITEM_SERVICE_CLASSES, itemClassName);

		if (!missingServiceClasses.isEmpty()) {
			throw new CapabilityVerificationException(
				this, itemClassName, missingServiceClasses);
		}

		ServiceTrackerCustomizerFactory.ServiceWrapper
			<DDMTemplatePermissionSupport>
				ddmTemplatePermissionSupportServiceWrapper =
					_ddmTemplatePermissionSupportServiceTrackerMap.getService(
						itemClassName);

		if (ddmTemplatePermissionSupportServiceWrapper == null) {
			throw new CapabilityVerificationException(
				this, itemClassName,
				Arrays.asList(DDMTemplatePermissionSupport.class));
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_ddmTemplatePermissionSupportServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, DDMTemplatePermissionSupport.class,
				"model.class.name",
				ServiceTrackerCustomizerFactory.
					<DDMTemplatePermissionSupport>serviceWrapper(
						bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_ddmTemplatePermissionSupportServiceTrackerMap.close();
	}

	private ServiceTrackerMap
		<String,
		 ServiceTrackerCustomizerFactory.ServiceWrapper
			 <DDMTemplatePermissionSupport>>
				_ddmTemplatePermissionSupportServiceTrackerMap;

	@Reference
	private InfoItemServiceVerifier _infoItemServiceVerifier;

}