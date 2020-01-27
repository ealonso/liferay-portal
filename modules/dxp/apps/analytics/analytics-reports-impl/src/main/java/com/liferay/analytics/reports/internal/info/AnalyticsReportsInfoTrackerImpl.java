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

package com.liferay.analytics.reports.internal.info;

import com.liferay.analytics.reports.info.AnalyticsReportsInfo;
import com.liferay.analytics.reports.info.AnalyticsReportsInfoTracker;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author David Arques
 */
@Component(immediate = true, service = AnalyticsReportsInfoTracker.class)
public class AnalyticsReportsInfoTrackerImpl
	implements AnalyticsReportsInfoTracker {

	public AnalyticsReportsInfo getAnalyticsReportsInfo(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		return _analyticsReportsInfos.get(key);
	}

	public List<AnalyticsReportsInfo> getAnalyticsReportsInfos() {
		return new ArrayList<>(_analyticsReportsInfos.values());
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setInfoItemSelector(
		AnalyticsReportsInfo analyticsReportsInfo) {

		_analyticsReportsInfos.put(
			analyticsReportsInfo.getKey(), analyticsReportsInfo);
	}

	protected void unsetInfoItemSelector(
		AnalyticsReportsInfo analyticsReportsInfo) {

		_analyticsReportsInfos.remove(analyticsReportsInfo.getKey());
	}

	private final Map<String, AnalyticsReportsInfo> _analyticsReportsInfos =
		new ConcurrentHashMap<>();

}