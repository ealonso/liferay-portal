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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.trash.TrashHelper;
import com.liferay.trash.model.TrashEntry;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Eudaldo Alonso
 */
public class TrashHelperUtil {

	public static TrashEntry getTrashEntry(TrashedModel trashedModel)
		throws PortalException {

		TrashHelper trashHelper = _trashHelperUtil._getTrashHelper();

		return trashHelper.getTrashEntry(trashedModel);
	}

	public static boolean isInTrashExplicitly(TrashedModel trashedModel) {
		TrashHelper trashHelper = _trashHelperUtil._getTrashHelper();

		return trashHelper.isInTrashExplicitly(trashedModel);
	}

	private TrashHelperUtil() {
		Bundle bundle = FrameworkUtil.getBundle(TrashHelperUtil.class);

		_serviceTracker = new ServiceTracker<>(
			bundle.getBundleContext(), TrashHelper.class, null);

		_serviceTracker.open();
	}

	private TrashHelper _getTrashHelper() {
		return _serviceTracker.getService();
	}

	private static final TrashHelperUtil _trashHelperUtil =
		new TrashHelperUtil();

	private final ServiceTracker<TrashHelper, TrashHelper> _serviceTracker;

}