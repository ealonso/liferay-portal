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

package com.liferay.client.extension.internal.events;

import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.model.ClientExtensionEntry;
import com.liferay.client.extension.model.ClientExtensionEntryRel;
import com.liferay.client.extension.service.ClientExtensionEntryLocalService;
import com.liferay.client.extension.service.ClientExtensionEntryRelLocalService;
import com.liferay.client.extension.type.CETThemeFavicon;
import com.liferay.client.extension.type.factory.CETFactory;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "key=servlet.service.events.pre",
	service = LifecycleAction.class
)
public class ClientExtensionsServicePreAction extends Action {

	@Override
	public void run(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws ActionException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout != null) {
			themeDisplay.setFavicon(_getFaviconURL(layout));
		}
	}

	private String _getCETThemeFaviconURL(long classNameId, long classPK) {
		ClientExtensionEntry clientExtensionEntry = _getClientExtensionEntry(
			classNameId, classPK,
			ClientExtensionEntryConstants.TYPE_THEME_FAVICON);

		if (clientExtensionEntry == null) {
			return null;
		}

		CETThemeFavicon cetThemeFavicon = _cetFactory.cetThemeFavicon(
			clientExtensionEntry);

		return cetThemeFavicon.getURL();
	}

	private ClientExtensionEntry _getClientExtensionEntry(
		long classNameId, long classPK, String type) {

		ClientExtensionEntryRel clientExtensionEntryRel =
			_clientExtensionEntryRelLocalService.fetchClientExtensionEntryRel(
				classNameId, classPK, type);

		if (clientExtensionEntryRel == null) {
			return null;
		}

		return _clientExtensionEntryLocalService.fetchClientExtensionEntry(
			clientExtensionEntryRel.getClientExtensionEntryId());
	}

	private String _getFaviconURL(Layout layout) {
		String faviconURL = _getCETThemeFaviconURL(
			_portal.getClassNameId(Layout.class), layout.getPlid());

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		faviconURL = layout.getFavicon();

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		Layout masterLayout = _layoutLocalService.fetchLayout(
			layout.getMasterLayoutPlid());

		faviconURL = _getCETThemeFaviconURL(
			_portal.getClassNameId(Layout.class), masterLayout.getPlid());

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		faviconURL = masterLayout.getFavicon();

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		LayoutSet layoutSet = layout.getLayoutSet();

		faviconURL = _getCETThemeFaviconURL(
			_portal.getClassNameId(LayoutSet.class),
			layoutSet.getLayoutSetId());

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		faviconURL = layoutSet.getFavicon();

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		return null;
	}

	@Reference
	private CETFactory _cetFactory;

	@Reference
	private ClientExtensionEntryLocalService _clientExtensionEntryLocalService;

	@Reference
	private ClientExtensionEntryRelLocalService
		_clientExtensionEntryRelLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}