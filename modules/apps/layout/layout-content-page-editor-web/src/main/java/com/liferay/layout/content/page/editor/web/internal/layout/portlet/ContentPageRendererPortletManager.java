/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.layout.portlet;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.portlet.PortletManager;
import com.liferay.portal.kernel.model.Layout;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_RENDERER_PORTLET,
	service = PortletManager.class
)
public class ContentPageRendererPortletManager implements PortletManager {

	@Override
	public boolean isVisible(Layout layout) {
		if (layout.isTypeAssetDisplay() || layout.isTypeContent()) {
			return false;
		}

		return true;
	}

}