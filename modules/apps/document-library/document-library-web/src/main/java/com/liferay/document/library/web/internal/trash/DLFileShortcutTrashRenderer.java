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

package com.liferay.document.library.web.internal.trash;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.trash.TrashHelper;
import com.liferay.trash.handler.TrashHandler;
import com.liferay.trash.handler.TrashHandlerRegistry;
import com.liferay.trash.renderer.TrashRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class DLFileShortcutTrashRenderer implements TrashRenderer {

	public DLFileShortcutTrashRenderer(
		DLFileShortcut dlFileShortcut,
		TrashHandlerRegistry trashHandlerRegistryUtil,
		TrashHelper trashHelper) {

		_dlFileShortcut = dlFileShortcut;
		_trashHelper = trashHelper;

		_trashHandlerRegistry = trashHandlerRegistryUtil;
	}

	@Override
	public String getClassName() {
		return DLFileShortcutConstants.getClassName();
	}

	@Override
	public long getClassPK() {
		return _dlFileShortcut.getFileShortcutId();
	}

	@Override
	public String getIconCssClass() throws PortalException {
		return StringPool.BLANK;
	}

	@Override
	public String getNewName(String oldName, String token) {
		return StringBundler.concat(oldName, StringPool.SPACE, token);
	}

	@Override
	public String getPortletId() {
		return DLPortletKeys.DOCUMENT_LIBRARY;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return getTitle(null);
	}

	@Override
	public String getTitle(Locale locale) {
		return _trashHelper.getOriginalTitle(_dlFileShortcut.getToTitle());
	}

	@Override
	public String getType() {
		return "shortcut";
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		TrashHandler trashHandler = _trashHandlerRegistry.getTrashHandler(
			DLFileEntryConstants.getClassName());

		TrashRenderer trashRenderer = trashHandler.getTrashRenderer(
			_dlFileShortcut.getToFileEntryId());

		return trashRenderer.include(
			httpServletRequest, httpServletResponse, template);
	}

	private final DLFileShortcut _dlFileShortcut;
	private final TrashHandlerRegistry _trashHandlerRegistry;
	private final TrashHelper _trashHelper;

}