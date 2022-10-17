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

package com.liferay.info.internal.formatter;

import com.liferay.info.formatter.InfoTextFormatter;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jorge Ferrer
 */
@Component(service = InfoTextFormatter.class)
public class DateInfoTextFormatter implements InfoTextFormatter<Date> {

	@Override
	public String format(Date date, Locale locale) {
		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			locale);

		return dateFormatDateTime.format(date);
	}

	@Override
	public String format(
		Date date, Map<String, Object> options, Locale locale) {

		JSONObject dateFormatJSONObject = (JSONObject)options.get("dateFormat");

		String dateFormatLocalized = dateFormatJSONObject.getString(
			locale.toString());

		DateFormat dateFormatPattern;

		if (Validator.isNull(dateFormatLocalized)) {
			return format(date, locale);
		}

		try {
			dateFormatPattern = new SimpleDateFormat(dateFormatLocalized);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return format(date, locale);
		}

		return dateFormatPattern.format(date);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DateInfoTextFormatter.class);

}