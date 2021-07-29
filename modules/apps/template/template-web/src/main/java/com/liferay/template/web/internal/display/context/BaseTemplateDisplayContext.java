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

import com.liferay.dynamic.data.mapping.configuration.DDMWebConfiguration;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMTemplateHelper;
import com.liferay.dynamic.data.mapping.util.comparator.TemplateIdComparator;
import com.liferay.dynamic.data.mapping.util.comparator.TemplateModifiedDateComparator;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.template.constants.TemplatePortletKeys;
import com.liferay.template.web.internal.security.permissions.resource.DDMTemplatePermission;
import com.liferay.template.web.internal.util.DDMTemplateActionDropdownItemsProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 * @author Eudaldo Alonso
 */
public abstract class BaseTemplateDisplayContext
	implements TemplateDisplayContext {

	public BaseTemplateDisplayContext(
		DDMTemplateHelper ddmTemplateHelper,
		DDMWebConfiguration ddmWebConfiguration,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_ddmTemplateHelper = ddmTemplateHelper;
		_ddmWebConfiguration = ddmWebConfiguration;
		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;

		_httpServletRequest = PortalUtil.getHttpServletRequest(
			liferayPortletRequest);

		themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public abstract long[] getClassNameIds();

	public CreationMenu getCreationMenu() {
		if (!isAddDDMTemplateEnabled()) {
			return null;
		}

		return buildCreationMenu();
	}

	public List<DropdownItem> getDDMTemplateActionDropdownItems(
			DDMTemplate ddmTemplate)
		throws Exception {

		DDMTemplateActionDropdownItemsProvider
			ddmTemplateActionDropdownItemsProvider =
				new DDMTemplateActionDropdownItemsProvider(
					isAddDDMTemplateEnabled(), ddmTemplate, _httpServletRequest,
					liferayPortletResponse, getTabs1());

		return ddmTemplateActionDropdownItemsProvider.getActionDropdownItems();
	}

	public HashMap<String, Object> getDDMTemplateEditorContext()
		throws Exception {

		return HashMapBuilder.<String, Object>put(
			"editorAutocompleteData", _getAutocompleteJSONObject()
		).put(
			"editorMode", _getEditorMode()
		).put(
			"propertiesViewURL",
			() -> PortletURLBuilder.createRenderURL(
				liferayPortletResponse
			).setMVCPath(
				"/ddm_template_properties.jsp"
			).setParameter(
				"classPK", getClassPK()
			).setParameter(
				"ddmTemplateId", getDDMTemplateId()
			).setWindowState(
				LiferayWindowState.EXCLUSIVE
			).buildString()
		).put(
			"script", _getScript()
		).put(
			"showCacheableWarning", false
		).put(
			"showLanguageChangeWarning", _getShowLanguageChangeWarning()
		).put(
			"showPropertiesPanel",
			() -> {
				DDMTemplate ddmTemplate = getDDMTemplate();

				if ((ddmTemplate == null) || (ddmTemplate.getClassPK() <= 0)) {
					return true;
				}

				return false;
			}
		).put(
			"templateVariableGroups", getTemplateVariableGroupJSONArray()
		).build();
	}

	public String getDDMTemplateEditURL(DDMTemplate ddmTemplate)
		throws PortalException {

		if (!DDMTemplatePermission.contains(
				themeDisplay.getPermissionChecker(), ddmTemplate,
				ActionKeys.UPDATE)) {

			return StringPool.BLANK;
		}

		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setMVCPath(
			"/edit_ddm_template.jsp"
		).setRedirect(
			themeDisplay.getURLCurrent()
		).setTabs1(
			getTabs1()
		).setParameter(
			"ddmTemplateId", ddmTemplate.getTemplateId()
		).buildString();
	}

	public String getDDMTemplateScope(DDMTemplate ddmTemplate)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(ddmTemplate.getGroupId());

		return LanguageUtil.get(
			_httpServletRequest, group.getScopeLabel(themeDisplay));
	}

	public List<NavigationItem> getNavigationItems() {
		return NavigationItemListBuilder.add(
			navigationItem -> {
				navigationItem.setActive(
					Objects.equals(getTabs1(), "information-templates"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"information-templates");
				navigationItem.setLabel(
					LanguageUtil.get(
						_httpServletRequest, "information-templates"));
			}
		).add(
			navigationItem -> {
				navigationItem.setActive(
					Objects.equals(getTabs1(), "widget-templates"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"widget-templates");
				navigationItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "widget-templates"));
			}
		).build();
	}

	public abstract long getResourceClassNameId();

	public String getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(
			liferayPortletRequest, "tabs1", "information-templates");

		return _tabs1;
	}

	public SearchContainer<DDMTemplate> getTemplateSearchContainer() {
		if (_ddmTemplateSearchContainer != null) {
			return _ddmTemplateSearchContainer;
		}

		SearchContainer<DDMTemplate> ddmTemplateSearchContainer =
			new SearchContainer<>(
				liferayPortletRequest, _getPortletURL(), null,
				"there-are-no-templates");

		ddmTemplateSearchContainer.setOrderByCol(_getOrderByCol());
		ddmTemplateSearchContainer.setOrderByComparator(
			_getTemplateOrderByComparator());
		ddmTemplateSearchContainer.setOrderByType(_getOrderByType());
		ddmTemplateSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(liferayPortletResponse));

		ddmTemplateSearchContainer.setResults(
			DDMTemplateServiceUtil.search(
				themeDisplay.getCompanyId(),
				new long[] {themeDisplay.getScopeGroupId()}, getClassNameIds(),
				null, getResourceClassNameId(), _getKeywords(),
				StringPool.BLANK, StringPool.BLANK,
				WorkflowConstants.STATUS_ANY,
				ddmTemplateSearchContainer.getStart(),
				ddmTemplateSearchContainer.getEnd(),
				ddmTemplateSearchContainer.getOrderByComparator()));
		ddmTemplateSearchContainer.setTotal(
			DDMTemplateServiceUtil.searchCount(
				themeDisplay.getCompanyId(),
				new long[] {themeDisplay.getScopeGroupId()}, getClassNameIds(),
				null, getResourceClassNameId(), _getKeywords(),
				StringPool.BLANK, StringPool.BLANK,
				WorkflowConstants.STATUS_ANY));

		_ddmTemplateSearchContainer = ddmTemplateSearchContainer;

		return _ddmTemplateSearchContainer;
	}

	public abstract String getTemplateTypeLocalizedLabel(long classNameId);

	public JSONArray getTemplateVariableGroupJSONArray() throws Exception {
		return JSONFactoryUtil.createJSONArray();
	}

	public boolean isAddDDMTemplateEnabled() {
		if (!_ddmWebConfiguration.enableTemplateCreation()) {
			return false;
		}

		Group scopeGroup = themeDisplay.getScopeGroup();

		if (!scopeGroup.hasLocalOrRemoteStagingGroup() ||
			!scopeGroup.isStagedPortlet(TemplatePortletKeys.TEMPLATE)) {

			return true;
		}

		return false;
	}

	protected abstract CreationMenu buildCreationMenu();

	protected boolean containsAddPortletDisplayTemplatePermission(
		String resourceName, String actionId) {

		try {
			return PortletPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), themeDisplay.getLayout(),
				resourceName, actionId, false, false);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to check permission for resource name " +
						resourceName,
					portalException);
			}
		}

		return false;
	}

	protected long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		_classNameId = BeanParamUtil.getLong(
			getDDMTemplate(), _httpServletRequest, "classNameId");

		return _classNameId;
	}

	protected long getClassPK() {
		DDMTemplate ddmTemplate = getDDMTemplate();

		if (ddmTemplate != null) {
			return ddmTemplate.getClassPK();
		}

		return 0;
	}

	protected DDMTemplate getDDMTemplate() {
		if (_ddmTemplate != null) {
			return _ddmTemplate;
		}

		if (getDDMTemplateId() <= 0) {
			return _ddmTemplate;
		}

		_ddmTemplate = DDMTemplateLocalServiceUtil.fetchDDMTemplate(
			getDDMTemplateId());

		return _ddmTemplate;
	}

	protected long getDDMTemplateId() {
		if (_ddmTemplateId != null) {
			return _ddmTemplateId;
		}

		_ddmTemplateId = ParamUtil.getLong(
			_httpServletRequest, "ddmTemplateId");

		return _ddmTemplateId;
	}

	protected abstract String getDefaultScript(long classNameId);

	protected String getLanguage() {
		if (_language != null) {
			return _language;
		}

		String language = TemplateConstants.LANG_TYPE_FTL;

		DDMTemplate ddmTemplate = getDDMTemplate();

		if (ddmTemplate != null) {
			language = ddmTemplate.getLanguage();
		}

		_language = language;

		return _language;
	}

	protected abstract String[] getTemplateLanguageTypes();

	protected final LiferayPortletRequest liferayPortletRequest;
	protected final LiferayPortletResponse liferayPortletResponse;
	protected final ThemeDisplay themeDisplay;

	private JSONObject _getAutocompleteJSONObject() throws Exception {
		return JSONFactoryUtil.createJSONObject(
			_ddmTemplateHelper.getAutocompleteJSON(
				_httpServletRequest, getLanguage()));
	}

	private String _getEditorMode() {
		if (Objects.equals(getLanguage(), "ftl")) {
			return "ftl";
		}

		return "velocity";
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	private String _getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			"modified-date");

		return _orderByCol;
	}

	private String _getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			"asc");

		return _orderByType;
	}

	private PortletURL _getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setTabs1(
			getTabs1()
		).buildPortletURL();
	}

	private String _getScript() {
		if (_script != null) {
			return _script;
		}

		_language = BeanParamUtil.getString(
			getDDMTemplate(), _httpServletRequest, "language",
			TemplateConstants.LANG_TYPE_FTL);

		String script = BeanParamUtil.getString(
			getDDMTemplate(), _httpServletRequest, "script");

		if (Validator.isNull(script)) {
			script = getDefaultScript(getClassNameId());
		}

		String scriptContent = ParamUtil.getString(
			_httpServletRequest, "scriptContent");

		if (Validator.isNotNull(scriptContent)) {
			script = scriptContent;
		}

		_script = script;

		return _script;
	}

	private boolean _getShowLanguageChangeWarning() {
		DDMTemplate ddmTemplate = getDDMTemplate();

		if ((ddmTemplate != null) && (getTemplateLanguageTypes().length > 1) &&
			!Objects.equals(ddmTemplate.getLanguage(), getLanguage())) {

			return true;
		}

		return false;
	}

	private OrderByComparator<DDMTemplate> _getTemplateOrderByComparator() {
		OrderByComparator<DDMTemplate> orderByComparator = null;

		boolean orderByAsc = false;

		if (Objects.equals(_getOrderByType(), "asc")) {
			orderByAsc = true;
		}

		if (Objects.equals(_getOrderByCol(), "id")) {
			orderByComparator = new TemplateIdComparator(orderByAsc);
		}
		else if (Objects.equals(_getOrderByCol(), "modified-date")) {
			orderByComparator = new TemplateModifiedDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseTemplateDisplayContext.class);

	private Long _classNameId;
	private DDMTemplate _ddmTemplate;
	private final DDMTemplateHelper _ddmTemplateHelper;
	private Long _ddmTemplateId;
	private SearchContainer<DDMTemplate> _ddmTemplateSearchContainer;
	private final DDMWebConfiguration _ddmWebConfiguration;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _language;
	private String _orderByCol;
	private String _orderByType;
	private String _script;
	private String _tabs1;

}