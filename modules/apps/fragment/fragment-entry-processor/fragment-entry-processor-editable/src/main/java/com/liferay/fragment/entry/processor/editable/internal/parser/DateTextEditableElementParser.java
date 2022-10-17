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

package com.liferay.fragment.entry.processor.editable.internal.parser;

import com.liferay.fragment.entry.processor.editable.parser.EditableElementParser;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.jsoup.nodes.Element;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Diego Hu
 */
@Component(
	enabled = false, immediate = true, property = "type=date-time",
	service = EditableElementParser.class
)
public class DateTextEditableElementParser extends TextEditableElementParser {

	@Override
	public String getValue(Element element) {
		String html = element.html();

		if (Validator.isNull(html.trim())) {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", getClass());

			return _language.get(resourceBundle, "example-date-time");
		}

		return html;
	}

	@Override
	public void replace(
		Element element, String value, JSONObject configJSONObject) {

		JSONObject dateFormatJSONObject = configJSONObject.getJSONObject(
			"dateFormat");

		if (dateFormatJSONObject != null) {
			Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

			String dateFormatLocalized = dateFormatJSONObject.getString(
				locale.toString());

			DateFormat initialPattern = new SimpleDateFormat(
				"MM/dd/yy hh:mm a", LocaleUtil.US);

			DateFormat dateFormatPattern = null;

			if (Validator.isNull(dateFormatLocalized)) {
				try {
					Date dateFormatted = initialPattern.parse(value);

					Format dateDefaultFormat =
						FastDateFormatFactoryUtil.getDateTime(locale);

					element.html(dateDefaultFormat.format(dateFormatted));
				}
				catch (ParseException parseException) {
					if (_log.isDebugEnabled()) {
						_log.debug(parseException);
					}

					throw new IllegalArgumentException(
						"Unable to parse date from " + value, parseException);
				}
			}
			else {
				try {
					dateFormatPattern = new SimpleDateFormat(
						dateFormatLocalized);
				}
				catch (Exception exception) {
					if (_log.isDebugEnabled()) {
						_log.debug(exception);
					}

					element.html(value);
				}
			}

			if (dateFormatPattern != null) {
				try {
					Date dateFormatted = initialPattern.parse(value);

					element.html(dateFormatPattern.format(dateFormatted));
				}
				catch (ParseException parseException) {
					if (_log.isDebugEnabled()) {
						_log.debug(parseException);
					}

					throw new IllegalArgumentException(
						"Unable to parse date from " + value, parseException);
				}
			}
		}
	}

	@Override
	protected String getEditableElementType() {
		return "date-time";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DateTextEditableElementParser.class);

	@Reference
	private Language _language;

}