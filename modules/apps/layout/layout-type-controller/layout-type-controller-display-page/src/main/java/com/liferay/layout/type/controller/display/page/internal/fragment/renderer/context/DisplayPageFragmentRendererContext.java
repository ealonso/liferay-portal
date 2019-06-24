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

package com.liferay.layout.type.controller.display.page.internal.fragment.renderer.context;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRendererContext;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * @author Víctor Galán
 */
public class DisplayPageFragmentRendererContext implements
	FragmentRendererContext {

	private final Map<String, Object> _fieldValues;

	@Override
	public Optional<Map<String, Object>> getFieldValuesOptional() {
		return Optional.ofNullable(_fieldValues);
	}

	@Override
	public FragmentEntryLink getFragmentEntryLink() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public String getMode() {
		return FragmentEntryLinkConstants.ASSET_DISPLAY_PAGE ;
	}

	@Override
	public long getPreviewClassPK() {
		return 0L;
	}

	@Override
	public int getPreviewType() {
		return 0;
	}

	@Override
	public long[] getSegmentsExperienceIds() {
		return _segmentsExperienceIds;
	}

	public DisplayPageFragmentRendererContext(
		Map<String, Object> fieldValues, long[] segmentsExperienceIds) {

		_fieldValues = fieldValues;
		_segmentsExperienceIds = segmentsExperienceIds;
	}

	private final long[] _segmentsExperienceIds;
}
