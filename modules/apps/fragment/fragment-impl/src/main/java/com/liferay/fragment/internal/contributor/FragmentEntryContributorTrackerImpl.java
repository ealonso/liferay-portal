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

package com.liferay.fragment.internal.contributor;

import com.liferay.fragment.contributor.FragmentEntryContributor;
import com.liferay.fragment.contributor.FragmentEntryContributorTracker;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = FragmentEntryContributorTracker.class)
public class FragmentEntryContributorTrackerImpl
	implements FragmentEntryContributorTracker {

	@Override
	public Map<String, List<FragmentEntryContributor>>
		getFragmentEntryContributors(int type) {

		return _fragmentEntryContributor.get(type);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setFragmentEntryContributor(
		FragmentEntryContributor fragmentEntryContributor) {

		Map<String, List<FragmentEntryContributor>>
			fragmentEntryContributorMap =
				_fragmentEntryContributor.computeIfAbsent(
					fragmentEntryContributor.getType(),
					type -> new HashMap<>());

		List<FragmentEntryContributor> fragmentEntryContributorList =
			fragmentEntryContributorMap.computeIfAbsent(
				fragmentEntryContributor.getFragmentCollectionKey(),
				key -> new ArrayList<>());

		fragmentEntryContributorList.add(fragmentEntryContributor);
	}

	protected void unsetFragmentEntryContributor(
		FragmentEntryContributor fragmentEntryContributor) {

		Map<String, List<FragmentEntryContributor>>
			fragmentEntryContributorMap = _fragmentEntryContributor.get(
				fragmentEntryContributor.getType());

		fragmentEntryContributorMap.computeIfPresent(
			fragmentEntryContributor.getFragmentCollectionKey(),
			(key, fragmentEntryContributorList) -> {
				fragmentEntryContributorList.remove(fragmentEntryContributor);

				if (ListUtil.isEmpty(fragmentEntryContributorList)) {
					return null;
				}

				return fragmentEntryContributorList;
			});
	}

	private final Map<Integer, Map<String, List<FragmentEntryContributor>>>
		_fragmentEntryContributor = new ConcurrentHashMap<>();

}