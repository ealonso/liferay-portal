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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class UserVerticalCardTag extends CardTag {

	public void setSubtitle(String subtitle) {
		_subtitle = HtmlUtil.unescape(subtitle);
	}

	public void setTitle(String title) {
		_title = HtmlUtil.unescape(title);
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	protected void cleanUp() {
		_subtitle = null;
		_title = null;
		_userId = 0;
	}

	protected String getColorCssClass() {
		String colorCssClass = "user-icon-defaul";

		User user = getUser();

		if (user != null) {
			colorCssClass =
				"user-icon-color-" + (Math.abs(user.getUserId()) % 10);
		}

		return colorCssClass;
	}

	@Override
	protected String getPage() {
		return "/card/user_vertical_card/page.jsp";
	}

	protected User getUser() {
		return UserLocalServiceUtil.fetchUser(_userId);
	}

	protected String getUserInitials(User user) {
		String userName = StringPool.BLANK;

		if (user != null) {
			userName = user.getFullName();
		}
		else {
			userName = LanguageUtil.get(request, "user");
		}

		String[] userNames = StringUtil.split(userName, StringPool.SPACE);

		StringBundler sb = new StringBundler(userNames.length);

		for (String curUserName : userNames) {
			sb.append(
				StringUtil.toUpperCase(StringUtil.shorten(curUserName, 1)));
		}

		return sb.toString();
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return true;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		request.setAttribute(
			"liferay-frontend:card:colorCssClass", getColorCssClass());

		User user = getUser();

		if ((user != null) && (user.getPortraitId() > 0)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			try {
				request.setAttribute(
					"liferay-frontend:card:portraitURL",
					user.getPortraitURL(themeDisplay));
			}
			catch (PortalException pe) {
			}
		}

		request.setAttribute("liferay-frontend:card:subtitle", _subtitle);
		request.setAttribute("liferay-frontend:card:title", _title);
		request.setAttribute(
			"liferay-frontend:card:userInitials", getUserInitials(user));
	}

	private String _subtitle;
	private String _title;
	private long _userId;

}