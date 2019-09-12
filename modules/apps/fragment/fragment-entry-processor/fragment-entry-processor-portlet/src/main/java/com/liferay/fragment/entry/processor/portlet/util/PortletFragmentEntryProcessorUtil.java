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

package com.liferay.fragment.entry.processor.portlet.util;

import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalServiceUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * @author Eudaldo Alonso
 */
public class PortletFragmentEntryProcessorUtil {

	public static OptionalLong getSegmentsExperienceIdOptional(
		long[] segmentsExperienceIds) {

		LongStream longStream = Arrays.stream(segmentsExperienceIds);

		return longStream.mapToObj(
			segmentsExperienceId ->
				SegmentsExperienceLocalServiceUtil.fetchSegmentsExperience(
					segmentsExperienceId)
		).filter(
			segmentsExperience -> segmentsExperience != null
		).sorted(
			Comparator.comparingInt(
				SegmentsExperience::getPriority
			).reversed()
		).mapToLong(
			SegmentsExperience::getSegmentsExperienceId
		).findFirst();
	}

}