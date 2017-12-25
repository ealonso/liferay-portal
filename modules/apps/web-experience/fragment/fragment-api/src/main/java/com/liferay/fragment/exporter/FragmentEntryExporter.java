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

package com.liferay.fragment.exporter;

import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;

import java.io.File;

/**
 * Provides a service for exporting Fragment entries.
 *
 * @author Pavel Savinov
 */
public interface FragmentEntryExporter {

	/**
	 * Exports fragment entries as a byte array.
	 *
	 * @param  fragmentEntries Fragment entries to export
	 * @return the output export File
	 *
	 * @throws Exception if an unexpected exception occurred
	 */
	public File export(FragmentEntry... fragmentEntries) throws Exception;

}
