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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ScreenNavigationHelper.class)
public class ScreenNavigationHelper {

	public List<ScreenNavigationCategory> getScreenNavigationCategories(
		String screenNavigationId) {

		return _screenNavigationCategories.get(screenNavigationId);
	}

	public List<ScreenNavigationEntry> getScreenNavigationEntries(
		ScreenNavigationCategory screenNavigationCategory) {

		String key = _getKey(
			screenNavigationCategory.getScreenNavigationKey(),
			screenNavigationCategory.getCategoryKey());

		return _screenNavigationEntries.get(key);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setScreenNavigationCategory(
		ScreenNavigationCategory screenNavigationCategory) {

		List<ScreenNavigationCategory> screenNavigationCategories =
			_screenNavigationCategories.get(
				screenNavigationCategory.getScreenNavigationKey());

		if (ListUtil.isEmpty(screenNavigationCategories)) {
			screenNavigationCategories = new ArrayList<>();

			_screenNavigationCategories.put(
				screenNavigationCategory.getScreenNavigationKey(),
				screenNavigationCategories);
		}

		screenNavigationCategories.add(screenNavigationCategory);

		Collections.sort(
			screenNavigationCategories,
			new PropertyServiceReferenceComparator(
				"screen.navigation.category.order"));
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setScreenNavigationEntry(
		ScreenNavigationEntry screenNavigationEntry) {

		String key = _getKey(
			screenNavigationEntry.getScreenNavigationKey(),
			screenNavigationEntry.getCategoryKey());

		List<ScreenNavigationEntry> screenNavigationEntries =
			_screenNavigationEntries.get(key);

		if (ListUtil.isEmpty(screenNavigationEntries)) {
			screenNavigationEntries = new ArrayList<>();

			_screenNavigationEntries.put(key, screenNavigationEntries);
		}

		screenNavigationEntries.add(screenNavigationEntry);

		Collections.sort(
			screenNavigationEntries,
			new PropertyServiceReferenceComparator(
				"screen.navigation.entry.order"));
	}

	protected void unsetScreenNavigationCategory(
		ScreenNavigationCategory screenNavigationCategory) {

		List<ScreenNavigationCategory> screenNavigationCategories =
			_screenNavigationCategories.get(
				screenNavigationCategory.getScreenNavigationKey());

		if (ListUtil.isEmpty(screenNavigationCategories)) {
			return;
		}

		screenNavigationCategories.remove(screenNavigationCategory);
	}

	protected void unsetScreenNavigationEntry(
		ScreenNavigationEntry screenNavigationEntry) {

		String key = _getKey(
			screenNavigationEntry.getScreenNavigationKey(),
			screenNavigationEntry.getCategoryKey());

		List<ScreenNavigationEntry> screenNavigationEntries =
			_screenNavigationEntries.get(key);

		if (ListUtil.isEmpty(screenNavigationEntries)) {
			return;
		}

		screenNavigationEntries.remove(screenNavigationEntry);
	}

	private String _getKey(
		String screenNavigationId, String screenCategoryKey) {

		return screenNavigationId + StringPool.PERIOD + screenCategoryKey;
	}

	private final ConcurrentMap<String, List<ScreenNavigationCategory>>
		_screenNavigationCategories = new ConcurrentHashMap<>();
	private final ConcurrentMap<String, List<ScreenNavigationEntry>>
		_screenNavigationEntries = new ConcurrentHashMap<>();

}