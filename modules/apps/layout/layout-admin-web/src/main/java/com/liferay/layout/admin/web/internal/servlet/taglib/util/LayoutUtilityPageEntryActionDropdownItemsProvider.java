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

package com.liferay.layout.admin.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author Bárbara Cabrera
 */
public class LayoutUtilityPageEntryActionDropdownItemsProvider {

	public LayoutUtilityPageEntryActionDropdownItemsProvider(
		LayoutUtilityPageEntry layoutUtilityPageEntry,
		RenderRequest renderRequest) {

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		_layoutUtilityPageEntry = layoutUtilityPageEntry;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

	}

	public List<DropdownItem> getActionDropdownItems()
		throws Exception {

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						() ->
							_layoutUtilityPageEntry.isApproved() &&
							Objects.equals(
								_layoutUtilityPageEntry.getType(),
								LayoutPageTemplateEntryTypeConstants. //este tipo no existe aún
									TYPE_UTILITY_PAGE) &&
							(_layoutUtilityPageEntry.getClassNameId() > 0),
						_getMarkAsDefaultDisplayPageActionUnsafeConsumer()
					).build());
			}).build();
	}

	private UnsafeConsumer<DropdownItem, Exception>
	_getMarkAsDefaultDisplayPageActionUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.putData("action", "markAsDefaultDisplayPage");
			dropdownItem.putData(
				"markAsDefaultDisplayPageURL",
				PortletURLBuilder.createActionURL(
					_renderResponse
				).setActionName(
					"/layout_page_template_admin" +
					"/mark_as_default_utility_page_entry"
				).setRedirect(
					_themeDisplay.getURLCurrent()
				).setParameter(
					"defaultTemplate",
					!_layoutUtilityPageEntry.isDefaultTemplate()
				).setParameter(
					"layoutUtilityPageEntryId",
					_layoutUtilityPageEntry.getLayoutUtilityPageEntryId()
				).buildString());

			String message = StringPool.BLANK;

			LayoutUtilityPageEntry defaultLayoutUtilityPageEntry =
				layoutUtilityPageEntryServiceUtil.
					fetchDefaultayoutUtilityPageEntry(
						_layoutUtilityPageEntry.getGroupId(),
						_layoutUtilityPageEntry.getClassNameId(),
						_layoutUtilityPageEntry.getClassTypeId());

			if (defaultLayoutUtilityEntry != null) {
				long defaultLayoutUtilityPageEntryId =
					defaultLayoutUtilityPageEntry.
						getLayoutUtilityPageEntryId();
				long layoutUtilityPageEntryId =
					_layoutUtilityPageEntry.getLayoutUtilityPageEntryId();

				if (defaultLayoutUtilityPageEntryId !=
					layoutUtilityPageEntryId) {

					message = LanguageUtil.format(
						_httpServletRequest,
						"do-you-want-to-replace-x-for-x-as-the-default-" +
						"display-page-template",
						new String[] {
							_layoutUtilityPageEntry.getName(),
							defaultLayoutUtilityPageEntry.getName()
						});
				}
			}

			if (Validator.isNull(message) &&
				_layoutUtilityPageEntry.isDefaultTemplate()) {

				message = LanguageUtil.get(
					_httpServletRequest, "unmark-default-confirmation");
			}

			dropdownItem.putData("message", message);

			String label = "mark-as-default";

			if (_layoutUtilityPageEntry.isDefaultTemplate()) {
				label = "unmark-as-default";
			}

			dropdownItem.setLabel(LanguageUtil.get(_httpServletRequest, label));
		};
	}


	private static final Log _log = LogFactoryUtil.getLog(
		LayoutUtilityPageEntryActionDropdownItemsProvider.class);
	private final HttpServletRequest _httpServletRequest;
	private final ThemeDisplay _themeDisplay;
	private final LayoutUtilityPageEntry _layoutUtilityPageEntry;

}