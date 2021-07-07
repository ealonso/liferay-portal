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

package com.liferay.template.web.internal.display.context;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenuBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.template.web.internal.util.TemplatesUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class TemplateManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public TemplateManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, String tabs1,
		SearchContainer<DDMTemplate> templatesSearchContainer) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			templatesSearchContainer);

		_tabs1 = tabs1;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.putData("action", _DELETE_SELECTED_TEMPLATES);
				dropdownItem.setIcon("times-circle");
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "delete"));
				dropdownItem.setQuickAction(true);
			}
		).build();
	}

	public String getAvailableActions(DDMTemplate ddmTemplate) {
		return _DELETE_SELECTED_TEMPLATES;
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).setTabs1(
			_tabs1
		).buildString();
	}

	@Override
	public String getComponentId() {
		return "templateManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		CreationMenu creationMenu = CreationMenuBuilder.addDropdownItem(
			dropdownItem -> {
				dropdownItem.setData(
					HashMapBuilder.<String, Object>put(
						"action", "addTemplate"
					).put(
						"addTemplatsURL",
						PortletURLBuilder.createActionURL(
							liferayPortletResponse
						).setActionName(
							"/template/add_template"
						).setBackURL(
							_themeDisplay.getURLCurrent()
						).setParameter(
							"_tabs1", _tabs1
						).buildString()
					).build());

				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "add"));
			}
		).build();

		creationMenu.put(
			"mappingTypes",
			TemplatesUtil.getMappingTypesJSONArray(
				_tabs1, _themeDisplay.getScopeGroupId(),
				_themeDisplay.getLocale()));

		return creationMenu;
	}

	@Override
	public String getDefaultEventHandler() {
		return "TEMPLATE_MANAGEMENT_TOOLBAR_DEFAULT_EVENT_HANDLER";
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "ddmTemplates";
	}

	private static final String _DELETE_SELECTED_TEMPLATES =
		"deleteSelectedTemplates";

	private final String _tabs1;
	private final ThemeDisplay _themeDisplay;

}