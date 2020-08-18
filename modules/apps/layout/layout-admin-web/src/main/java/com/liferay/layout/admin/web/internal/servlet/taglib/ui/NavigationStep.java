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

package com.liferay.layout.admin.web.internal.servlet.taglib.ui;

/**
 * @author Víctor Galán
 */
public class NavigationStep {

	public NavigationStep(boolean active, String label, String url) {
		_active = active;
		_label = label;
		_url = url;
	}

	public String getLabel() {
		return _label;
	}

	public String getUrl() {
		return _url;
	}

	public boolean isActive() {
		return _active;
	}

	private final boolean _active;
	private final String _label;
	private final String _url;

}