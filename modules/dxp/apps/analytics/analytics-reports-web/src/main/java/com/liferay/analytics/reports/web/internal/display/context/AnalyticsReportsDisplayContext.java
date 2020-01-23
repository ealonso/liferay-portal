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

import com.liferay.analytics.reports.web.internal.constants.AnalyticsReportsPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sarai Díaz
 */
public class AnalyticsReportsDisplayContext {

	public AnalyticsReportsDisplayContext(
		long classNameId, ClassNameLocalService classNameLocalService,
		Object displayObject, HttpServletRequest httpServletRequest,
		Portal portal, UserLocalService userLocalService) {

		_classNameId = classNameId;
		_classNameLocalService = classNameLocalService;
		_displayObject = displayObject;

		_httpServletRequest = httpServletRequest;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_portal = portal;
		_userLocalService = userLocalService;
	}

	public Map<String, Object> getData() {
		if (_data != null) {
			return _data;
		}

		_data = HashMapBuilder.<String, Object>put(
			"context", _getContext()
		).put(
			"props",
			getProps(
				_classNameId, _displayObject, _themeDisplay.getLayout(),
				_themeDisplay.getLocale())
		).build();

		return _data;
	}

	public String getLiferayAnalyticsURL(long companyId) {
		return PrefsPropsUtil.getString(companyId, "liferayAnalyticsURL");
	}

	protected Map<String, Object> getProps(
		long classNameId, Object displayObject, Layout layout, Locale locale) {

		if (classNameId == _classNameLocalService.getClassNameId(
				BlogsEntry.class)) {

			return _getProps((BlogsEntry)displayObject, layout, locale);
		}
		else if (classNameId == _classNameLocalService.getClassNameId(
					FileEntry.class)) {

			return _getProps((FileEntry)displayObject, layout, locale);
		}
		else if (classNameId == _classNameLocalService.getClassNameId(
					JournalArticle.class)) {

			return _getProps((JournalArticle)displayObject, layout, locale);
		}
		else {
			return _getProps(layout, locale);
		}
	}

	private HashMap<String, Object> _createProps(
		String authorName, Locale locale, Date publishDate, String title) {

		return HashMapBuilder.<String, Object>put(
			"authorName", authorName
		).put(
			"publishDate",
			FastDateFormatFactoryUtil.getSimpleDateFormat(
				"MMMM dd, yyyy", locale
			).format(
				publishDate
			)
		).put(
			"title", title
		).build();
	}

	private String _getAnalyticsReportsPortletNamespace() {
		return _portal.getPortletNamespace(
			AnalyticsReportsPortletKeys.ANALYTICS_REPORTS);
	}

	private Map<String, Object> _getContext() {
		return HashMapBuilder.<String, Object>put(
			"namespace", _getAnalyticsReportsPortletNamespace()
		).build();
	}

	private Map<String, Object> _getProps(
		BlogsEntry blogsEntry, Layout layout, Locale locale) {

		String authorName = StringPool.BLANK;

		User user = _userLocalService.fetchUser(blogsEntry.getUserId());

		if (user != null) {
			authorName = user.getFullName();
		}

		return _createProps(
			authorName, locale,
			_maxDate(blogsEntry.getDisplayDate(), layout.getPublishDate()),
			blogsEntry.getTitle());
	}

	private Map<String, Object> _getProps(
		FileEntry fileEntry, Layout layout, Locale locale) {

		String authorName = StringPool.BLANK;

		User user = _userLocalService.fetchUser(layout.getUserId());

		if (user != null) {
			authorName = user.getFullName();
		}

		return _createProps(
			authorName, locale,
			_maxDate(fileEntry.getModifiedDate(), layout.getPublishDate()),
			fileEntry.getTitle());
	}

	private Map<String, Object> _getProps(
		JournalArticle article, Layout layout, Locale locale) {

		String authorName = StringPool.BLANK;

		User user = _userLocalService.fetchUser(layout.getUserId());

		if (user != null) {
			authorName = user.getFullName();
		}

		return _createProps(
			authorName, locale,
			_maxDate(article.getDisplayDate(), layout.getPublishDate()),
			article.getTitle(locale));
	}

	private Map<String, Object> _getProps(Layout layout, Locale locale) {
		String authorName = StringPool.BLANK;
		User user = _userLocalService.fetchUser(layout.getUserId());

		if (user != null) {
			authorName = user.getFullName();
		}

		return _createProps(
			authorName, locale, layout.getPublishDate(),
			layout.getTitle(locale));
	}

	private Date _maxDate(Date date1, Date date2) {
		if (date1.after(date2)) {
			return date1;
		}

		return date2;
	}

	private final long _classNameId;
	private final ClassNameLocalService _classNameLocalService;
	private Map<String, Object> _data;
	private final Object _displayObject;
	private final HttpServletRequest _httpServletRequest;
	private final Portal _portal;
	private final ThemeDisplay _themeDisplay;
	private final UserLocalService _userLocalService;

}