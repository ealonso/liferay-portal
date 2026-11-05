/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.osb.faro.model.FaroChannel;
import com.liferay.osb.faro.service.FaroChannelLocalServiceUtil;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class ChannelSelectorDisplayContext {

	public ChannelSelectorDisplayContext(
		HttpServletRequest httpServletRequest) {

		_httpServletRequest = httpServletRequest;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<FaroChannel> getFaroChannels() {
		if (_faroChannels != null) {
			return _faroChannels;
		}

		_faroChannels = FaroChannelLocalServiceUtil.search(
			_themeDisplay.getScopeGroupId(), null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		return _faroChannels;
	}

	public FaroChannel getSelectedFaroChannel() {
		List<FaroChannel> faroChannels = getFaroChannels();

		if (faroChannels.isEmpty()) {
			return null;
		}

		String channelId = ParamUtil.getString(_httpServletRequest, "channelId");

		FaroChannel faroChannel = FaroChannelLocalServiceUtil.fetchFaroChannel(
			channelId, _themeDisplay.getScopeGroupId());

		if ((faroChannel != null) && faroChannels.contains(faroChannel)) {
			return faroChannel;
		}

		return faroChannels.get(0);
	}

	public List<DropdownItem> getDropdownItems() throws PortalException {
		String layoutFullURL = PortalUtil.getLayoutFullURL(
			_themeDisplay.getLayout(), _themeDisplay);

		FaroChannel selectedFaroChannel = getSelectedFaroChannel();

		return TransformUtil.transform(
			getFaroChannels(),
			faroChannel -> {
				DropdownItem dropdownItem = new DropdownItem();

				dropdownItem.setActive(
					Objects.equals(
						faroChannel.getChannelId(),
						selectedFaroChannel.getChannelId()));
				dropdownItem.setHref(
					HttpComponentsUtil.addParameter(
						layoutFullURL, "channelId",
						faroChannel.getChannelId()));
				dropdownItem.setLabel(faroChannel.getName());

				return dropdownItem;
			});
	}

	private List<FaroChannel> _faroChannels;
	private final HttpServletRequest _httpServletRequest;
	private final ThemeDisplay _themeDisplay;

}