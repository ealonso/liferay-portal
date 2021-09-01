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

package com.liferay.trash.util;

import com.liferay.trash.handler.TrashHandler;
import com.liferay.trash.handler.TrashHandlerRegistry;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Eudaldo Alonso
 */
public class TrashHandlerRegistryUtil {

	public static TrashHandler getTrashHandler(String className) {
		TrashHandlerRegistry trashHandlerRegistry =
			_trashHandlerRegistryUtil._getTrashHandlerRegistry();

		return trashHandlerRegistry.getTrashHandler(className);
	}

	public static List<TrashHandler> getTrashHandlers() {
		TrashHandlerRegistry trashHandlerRegistry =
			_trashHandlerRegistryUtil._getTrashHandlerRegistry();

		return trashHandlerRegistry.getTrashHandlers();
	}

	private TrashHandlerRegistryUtil() {
		Bundle bundle = FrameworkUtil.getBundle(TrashHandlerRegistryUtil.class);

		_serviceTracker = new ServiceTracker<>(
			bundle.getBundleContext(), TrashHandlerRegistry.class, null);

		_serviceTracker.open();
	}

	private TrashHandlerRegistry _getTrashHandlerRegistry() {
		return _serviceTracker.getService();
	}

	private static final TrashHandlerRegistryUtil _trashHandlerRegistryUtil =
		new TrashHandlerRegistryUtil();

	private final ServiceTracker<TrashHandlerRegistry, TrashHandlerRegistry>
		_serviceTracker;

}