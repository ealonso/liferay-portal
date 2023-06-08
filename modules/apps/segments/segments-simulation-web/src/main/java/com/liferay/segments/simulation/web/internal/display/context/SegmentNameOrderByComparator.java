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

package com.liferay.segments.simulation.web.internal.display.context;

import com.liferay.portal.kernel.util.CollatorUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.segments.model.SegmentsEntry;

import java.text.Collator;
import java.util.Locale;

/**
 * @author Neil Griffin
 */
public class SegmentNameOrderByComparator
	extends OrderByComparator<SegmentsEntry> {

	public SegmentNameOrderByComparator(Locale locale) {
		_locale = locale;

		_collator = CollatorUtil.getInstance(locale);
	}

	@Override
	public int compare(
		SegmentsEntry segmentsEntry1, SegmentsEntry segmentsEntry2) {

		return _collator.compare(
			segmentsEntry1.getName(_locale), segmentsEntry2.getName(_locale));
	}

	@Override
	public String getOrderBy() {
		return "name";
	}

	private Collator _collator;
	private Locale _locale;

}