/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.analytics.reports.web.internal.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author David Arques
 * @author Sarai Díaz
 */
public class AnalyticsReportsDisplayContext {

	public AnalyticsReportsDisplayContext(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse,
		UserLocalService userLocalService) {

		_httpServletRequest = httpServletRequest;
		_renderResponse = renderResponse;
		_userLocalService = userLocalService;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getData() {
		if (_data != null) {
			return _data;
		}

		_data = HashMapBuilder.<String, Object>put(
			"context", getContext()
		).put(
			"props", getProps()
		).build();

		return _data;
	}

	public String getLiferayAnalyticsURL(long companyId) {
		return PrefsPropsUtil.getString(companyId, "liferayAnalyticsURL");
	}

	protected Map<String, Object> getContext() {
		return HashMapBuilder.<String, Object>put(
			"endpoints", _getEndpoints()
		).build();
	}

	protected Map<String, Object> getProps() {
		Layout layout = _themeDisplay.getLayout();

		User user = _userLocalService.fetchUser(layout.getUserId());

		String authorName = StringPool.BLANK;

		if (user != null) {
			authorName = user.getFullName();
		}

		Locale locale = _themeDisplay.getLocale();

		return HashMapBuilder.<String, Object>put(
			"authorName", authorName
		).put(
			"publishDate",
			FastDateFormatFactoryUtil.getSimpleDateFormat(
				"MMMM dd, yyyy", locale
			).format(
				layout.getPublishDate()
			)
		).put(
			"title", layout.getTitle(locale)
		).build();
	}

	private String _getAnalyticsReportsActionURL(String action) {
		PortletURL actionURL = _renderResponse.createActionURL();

		actionURL.setParameter(ActionRequest.ACTION_NAME, action);

		return HttpUtil.addParameter(
			actionURL.toString(), "p_l_mode", Constants.VIEW);
	}

	private String _getAnalyticsReportsTotalViewsURL() {
		return _getAnalyticsReportsActionURL(
			"/analytics_reports/get_total_views");
	}

	private Map<String, Object> _getEndpoints() {
		return HashMapBuilder.<String, Object>put(
			"getAnalyticsReportsTotalViewsURL",
			_getAnalyticsReportsTotalViewsURL()
		).build();
	}

	private Map<String, Object> _data;
	private final HttpServletRequest _httpServletRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;
	private final UserLocalService _userLocalService;

}