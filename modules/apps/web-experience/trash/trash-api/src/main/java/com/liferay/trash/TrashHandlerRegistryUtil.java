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

package com.liferay.trash;

import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Alexander Chow
 */
@Component(immediate = true, service = TrashHandlerRegistryUtil.class)
public class TrashHandlerRegistryUtil {

	public TrashHandler getTrashHandler(String className) {
		return _trashHandlers.get(className);
	}

	public List<TrashHandler> getTrashHandlers() {
		return ListUtil.fromMapValues(_trashHandlers);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unsetTrashHandler"
	)
	protected void setTrashHandler(TrashHandler trashHandler) {
		_trashHandlers.put(trashHandler.getClassName(), trashHandler);
	}

	protected void unsetTrashHandler(TrashHandler trashHandler) {
		_trashHandlers.remove(trashHandler.getClassName());
	}

	private final Map<String, TrashHandler> _trashHandlers =
		new ConcurrentSkipListMap<>();

}