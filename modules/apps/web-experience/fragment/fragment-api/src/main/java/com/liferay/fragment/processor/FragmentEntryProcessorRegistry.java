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

package com.liferay.fragment.processor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = FragmentEntryProcessorRegistry.class)
public class FragmentEntryProcessorRegistry {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		unbind = "unregisterFragmentEntryProcessor"
	)
	public void registerFragmentEntryProcessor(
		ServiceReference<FragmentEntryProcessor> serviceReference) {

		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		FragmentEntryProcessor fragmentEntryProcessor =
			bundleContext.getService(serviceReference);

		_fragmentEntryProcessors.add(fragmentEntryProcessor);

		_fragmentEntryProcessors.sort(
			(fragmentEntryProcessor1, fragmentEntryProcessor2) -> {
				ServiceReference serviceReference1 =
					bundleContext.getServiceReference(
						fragmentEntryProcessor1.getClass());

				ServiceReference serviceReference2 =
					bundleContext.getServiceReference(
						fragmentEntryProcessor2.getClass());

				Integer priority1 = GetterUtil.getInteger(
					serviceReference1.getProperty(
						"fragment.entry.processor.priority"));

				Integer priority2 = GetterUtil.getInteger(
					serviceReference2.getProperty(
						"fragment.entry.processor.priority"));

				return priority1.compareTo(priority2);
			});
	}

	public void unregisterFragmentEntryProcessor(
		FragmentEntryProcessor fragmentEntryProcessor) {

		_fragmentEntryProcessors.remove(fragmentEntryProcessor);
	}

	public void validateFragmentEntryHTML(String html) throws PortalException {
		for (FragmentEntryProcessor fragmentEntryProcessor :
				_fragmentEntryProcessors) {

			fragmentEntryProcessor.validateFragmentEntryHTML(html);
		}
	}

	private final List<FragmentEntryProcessor> _fragmentEntryProcessors =
		new CopyOnWriteArrayList<>();

}