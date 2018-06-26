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

package com.liferay.layout.admin.web.internal.servlet.taglib;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = DynamicInclude.class)
public class EditContentLayoutTopHeadDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String namespace = _portal.getPortletNamespace(
			LayoutAdminPortletKeys.GROUP_PAGES);

		String mvcPath = ParamUtil.getString(request, namespace + "mvcPath");

		String mvcRenderCommandName = ParamUtil.getString(
			request, namespace + "mvcRenderCommandName");

		if (mvcPath.endsWith("edit_content_layout.jsp") ||
			mvcRenderCommandName.equals(
				"/layout/edit_layout_page_template_entry")) {

			try {
				long selPlid = ParamUtil.getLong(
					request, namespace + "selPlid");

				if (selPlid == 0) {
					selPlid = _layoutLocalService.getDefaultPlid(
						themeDisplay.getScopeGroupId());
				}

				Layout selLayout = _layoutLocalService.getLayout(selPlid);

				Theme selLayoutTheme = selLayout.getTheme();

				StringBundler themeSB = new StringBundler(3);

				themeSB.append(themeDisplay.getPortalURL());
				themeSB.append(selLayoutTheme.getContextPath());
				themeSB.append(selLayoutTheme.getCssPath());

				StringBundler sb = new StringBundler(4);

				sb.append("<link class=\"lfr-css-file\" ");
				sb.append("data-senna-track=\"temporary\" href=\"");
				sb.append(
					_html.escapeAttribute(
						_portal.getStaticResourceURL(
							request, themeSB.toString() + "/main.css")));
				sb.append("\" rel=\"stylesheet\" type=\"text/css\" />");

				PrintWriter printWriter = response.getWriter();

				printWriter.println(sb.toString());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}

				throw new IOException(e);
			}
		}
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
			"/html/common/themes/top_head.jsp#post");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditContentLayoutTopHeadDynamicInclude.class);

	@Reference
	private Html _html;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}