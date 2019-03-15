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

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorWebKeys;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.Collections;
import java.util.Objects;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, service = ContentPageEditorDisplayContextProvider.class
)
public class ContentPageEditorDisplayContextProvider {

	public ContentPageEditorDisplayContext getContentPageEditorDisplayContext(
		HttpServletRequest request, RenderResponse renderResponse) {

		String className = (String)request.getAttribute(
			ContentPageEditorWebKeys.CLASS_NAME);

		long classPK = GetterUtil.getLong(
			request.getAttribute(ContentPageEditorWebKeys.CLASS_PK));

		if (Objects.equals(className, Layout.class.getName())) {
			Layout draftLayout = _layoutLocalService.fetchLayout(
				_portal.getClassNameId(Layout.class), classPK);

			if (draftLayout == null) {
				try {
					Layout layout = _layoutLocalService.getLayout(classPK);

					ServiceContext serviceContext =
						ServiceContextFactory.getInstance(request);

					draftLayout = _layoutLocalService.addLayout(
						layout.getUserId(), layout.getGroupId(),
						layout.isPrivateLayout(), layout.getParentLayoutId(),
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						layout.getNameMap(), layout.getTitleMap(),
						layout.getDescriptionMap(), layout.getKeywordsMap(),
						layout.getRobotsMap(), layout.getType(),
						layout.getTypeSettings(), true, true,
						Collections.emptyMap(), serviceContext);
				}
				catch (Exception e) {
					_log.error("Unable to create draft layout", e);
				}
			}

			classPK = draftLayout.getPlid();

			return new ContentPageLayoutEditorDisplayContext(
				request, renderResponse, className, classPK);
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				classPK);

		boolean showMapping = false;

		if ((layoutPageTemplateEntry != null) &&
			(layoutPageTemplateEntry.getType() ==
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE)) {

			showMapping = true;
		}

		return new ContentPageEditorLayoutPageTemplateDisplayContext(
			request, renderResponse, className, classPK, showMapping);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentPageEditorDisplayContextProvider.class);

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private Portal _portal;

}