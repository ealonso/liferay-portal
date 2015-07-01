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

package com.liferay.taglib.portletext;

import com.liferay.taglib.ui.IconTag;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class IconOptionsTag extends IconTag {

	@Override
	protected String getPage() {
		if (Validator.isNull(_view)) {
			return "/html/taglib/portlet/icon_options/page.jsp";
		}

		return "/html/taglib/portlet/icon_options/" + _view + "/page.jsp";
	}

	public void setView(String view) {
		_view = view;
	}

	private String _view;
}