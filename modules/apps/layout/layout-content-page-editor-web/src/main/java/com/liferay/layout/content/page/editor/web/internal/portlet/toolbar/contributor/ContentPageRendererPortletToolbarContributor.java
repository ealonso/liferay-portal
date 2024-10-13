/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.toolbar.contributor;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.toolbar.contributor.BasePortletToolbarContributor;
import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_RENDERER_PORTLET,
		"mvc.path=-", "mvc.path=/view_renderer.jsp"
	},
	service = PortletToolbarContributor.class
)
public class ContentPageRendererPortletToolbarContributor
	extends BasePortletToolbarContributor {

	@Override
	protected List<MenuItem> getPortletTitleMenuItems(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		try {
			URLMenuItem urlMenuItem = new URLMenuItem();

			urlMenuItem.setIcon("edit");
			urlMenuItem.setLabel("Edit Content");
			urlMenuItem.setURL(
				HttpComponentsUtil.addParameters(
					_portal.getLayoutFullURL(
						layout.fetchDraftLayout(), themeDisplay),
					"p_l_back_url", themeDisplay.getURLCurrent(),
					"p_l_back_url_title",
					layout.getName(themeDisplay.getLocale()), "p_l_mode",
					Constants.EDIT));

			return Collections.singletonList(urlMenuItem);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.error("Unable to add edit layout", exception);
			}
		}

		return Collections.emptyList();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentPageRendererPortletToolbarContributor.class);

	@Reference
	private Portal _portal;

}