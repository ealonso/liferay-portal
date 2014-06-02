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

package com.liferay.portlet.documentselector.impl;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portlet.documentlibrary.util.AudioProcessorUtil;

import java.util.Set;

/**
 * @author Eudaldo Alonso
 */
public class AudioDocumentSelector extends BaseDocumentSelector {

	@Override
	protected String[] getFileEntryMimeTypes() {
		Set<String> audioMimeTypes = AudioProcessorUtil.getAudioMimeTypes();

		if (audioMimeTypes == null) {
			return null;
		}

		return ArrayUtil.toStringArray(audioMimeTypes.toArray());
	}

}