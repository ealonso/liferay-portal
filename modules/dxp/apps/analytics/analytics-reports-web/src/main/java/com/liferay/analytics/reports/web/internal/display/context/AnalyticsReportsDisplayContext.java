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

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

import java.util.Map;

/**
 * @author Sarai Díaz
 */
public class AnalyticsReportsDisplayContext {

	public AnalyticsReportsDisplayContext() {
	}

	public Map<String, Object> getData() throws Exception {
		if (_data != null) {
			return _data;
		}

		_data = HashMapBuilder.<String, Object>put(
			"context", ""
		).put(
			"props", ""
		).build();

		return _data;
	}

	public String getLiferayAnalyticsURL(long companyId) {
		return PrefsPropsUtil.getString(companyId, "liferayAnalyticsURL");
	}

	private Map<String, Object> _data;

}