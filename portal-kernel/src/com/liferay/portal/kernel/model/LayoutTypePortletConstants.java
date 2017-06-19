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

package com.liferay.portal.kernel.model;

/**
 * @author Alexander Chow
 * @author Juan Fernández
 */
public class LayoutTypePortletConstants {

	// COLUMN_PREFIX should only be used when generating an arbitrary
	// typeSettings based on no particular Layout template. It should never be
	// used when reading in a pre-existing typeSettings to determine whether or
	// not an a parameter in the typeSettings is the id of a div. Use the
	// isDivId method for this instead.

	public static final String COLUMN_PREFIX = "column-";

	public static final String DEFAULT_ASSET_PUBLISHER_PORTLET_ID =
		"default-asset-publisher-portlet-id";

	public static final String LAYOUT_TEMPLATE_ID = "layout-template-id";

	public static final String MODE_ABOUT = "mode-about";

	public static final String MODE_CONFIG = "mode-config";

	public static final String MODE_EDIT = "mode-edit";

	public static final String MODE_EDIT_DEFAULTS = "mode-edit-defaults";

	public static final String MODE_EDIT_GUEST = "mode-edit-guest";

	public static final String MODE_HELP = "mode-help";

	public static final String MODE_PREVIEW = "mode-preview";

	public static final String MODE_PRINT = "mode-print";

	public static final String NESTED_COLUMN_IDS = "nested-column-ids";

	public static final String STATE_MAX = "state-max";

	public static final String STATE_MIN = "state-min";

	public static final String STATIC_PORTLET_ORGANIZATION_SELECTOR =
		"organization";

	public static final String STATIC_PORTLET_REGULAR_SITE_SELECTOR =
		"regular-site";

	public static final String STATIC_PORTLET_USER_SELECTOR = "user";

	public static boolean isDivId(String id) {
		if (id.equals(DEFAULT_ASSET_PUBLISHER_PORTLET_ID) ||
			id.equals(LAYOUT_TEMPLATE_ID) || id.equals(MODE_ABOUT) ||
			id.equals(MODE_CONFIG) || id.equals(MODE_EDIT) ||
			id.equals(MODE_EDIT_DEFAULTS) || id.equals(MODE_EDIT_GUEST) ||
			id.equals(MODE_HELP) || id.equals(MODE_PREVIEW) ||
			id.equals(MODE_PRINT) || id.equals(NESTED_COLUMN_IDS) ||
			id.equals(STATE_MAX) || id.equals(STATE_MIN) ||
			id.equals(STATIC_PORTLET_ORGANIZATION_SELECTOR) ||
			id.equals(STATIC_PORTLET_REGULAR_SITE_SELECTOR) ||
			id.equals(STATIC_PORTLET_USER_SELECTOR)) {

			return false;
		}

		return true;
	}

}