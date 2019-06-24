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

package com.liferay.layout.type.controller.content.internal.fragment.renderer.context;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRendererContext;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * @author Víctor Galán
 */
public class ContentPageFragmentRendererContext implements
	FragmentRendererContext  {

	@Override
	public Optional<Map<String, Object>> getFieldValuesOptional() {
		return Optional.empty();
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
		return FragmentEntryLinkConstants.VIEW ;
	}

	@Override
	public long getPreviewClassPK() {
		return _previewClassPK;
	}

	@Override
	public int getPreviewType() {
		return _previewType;
	}

	@Override
	public long[] getSegmentsExperienceIds() {
		return _segmentsExperienceIds;
	}

	public ContentPageFragmentRendererContext(
		long previewClassPK, int previewType, long[] segmentsExperienceIds) {
		_previewClassPK = previewClassPK;
		_previewType = previewType;
		_segmentsExperienceIds = segmentsExperienceIds;
	}

	private final long _previewClassPK;
	private final int _previewType;
	private final long[] _segmentsExperienceIds;
}
