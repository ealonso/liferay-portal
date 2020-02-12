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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.layout.admin.web.internal.display.context.LayoutsAdminDisplayContext;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.layout.admin.web.internal.configuration.LayoutConverterConfiguration;
import com.liferay.layout.admin.web.internal.configuration.LayoutEditorTypeConfiguration;
import com.liferay.layout.admin.web.internal.constants.LayoutAdminWebKeys;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.layout.page.template.util.comparator.LayoutPageTemplateCollectionNameComparator;
import com.liferay.layout.util.LayoutCopyHelper;
import com.liferay.layout.util.comparator.LayoutCreateDateComparator;
import com.liferay.layout.util.template.LayoutConverter;
import com.liferay.layout.util.template.LayoutConverterRegistry;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutType;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portal.util.LayoutListUtil;
import com.liferay.portal.util.LayoutTypeControllerTracker;
import com.liferay.portal.util.RobotsUtil;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.taglib.security.PermissionsURLTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Lancha
 */
public class LayoutsAdminReactDisplayContext extends LayoutsAdminDisplayContext {

    public LayoutsAdminReactDisplayContext(
		LayoutConverterConfiguration layoutConverterConfiguration,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		StagingGroupHelper stagingGroupHelper) {
            super(layoutConverterConfiguration, liferayPortletRequest, liferayPortletResponse, stagingGroupHelper);
    }

    @Override
    public JSONArray getLayoutColumnsJSONArray() throws Exception {
		JSONArray layoutColumnsJSONArray = JSONUtil.put(
			_getFirstLayoutColumnJSONArray());

		if (isFirstColumn()) {
			return layoutColumnsJSONArray;
		}

		JSONArray layoutSetBranchesJSONArray = getLayoutSetBranchesJSONArray();

		if (layoutSetBranchesJSONArray.length() > 0) {
			layoutColumnsJSONArray.put(layoutSetBranchesJSONArray);
		}

		Layout selLayout = getSelLayout();

		if (selLayout == null) {
			layoutColumnsJSONArray.put(
				getLayoutsJSONArray(0, isPrivateLayout()));

			return layoutColumnsJSONArray;
		}

		List<Layout> layouts = ListUtil.copy(selLayout.getAncestors());

		Collections.reverse(layouts);

		layouts.add(selLayout);

		for (Layout layout : layouts) {
			layoutColumnsJSONArray.put(
				getLayoutsJSONArray(
					layout.getParentLayoutId(), selLayout.isPrivateLayout()));
		}

		layoutColumnsJSONArray.put(
			getLayoutsJSONArray(
				selLayout.getLayoutId(), selLayout.isPrivateLayout()));

		return layoutColumnsJSONArray;
	}

    @Override
	public JSONArray getLayoutsJSONArray(
			long parentLayoutId, boolean privateLayout)
		throws Exception {

		JSONArray layoutsJSONArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			getSelGroupId(), privateLayout, parentLayoutId, true,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (Layout layout : layouts) {
			if (getActiveLayoutSetBranchId() > 0) {
				LayoutRevision layoutRevision =
					LayoutStagingUtil.getLayoutRevision(layout);

				if ((layoutRevision != null) && layoutRevision.isIncomplete()) {
					continue;
				}
			}

			JSONObject layoutJSONObject = JSONUtil.put(
				"actions", _getLayoutActionsJSONArray(layout)
			).put(
				"active", isActive(layout.getPlid())
			).put(
				"bulkActions", StringUtil.merge(getAvailableActions(layout))
			);

			LayoutTypeController layoutTypeController =
				LayoutTypeControllerTracker.getLayoutTypeController(
					layout.getType());

			ResourceBundle layoutTypeResourceBundle =
				ResourceBundleUtil.getBundle(
					"content.Language", themeDisplay.getLocale(),
					layoutTypeController.getClass());

			layoutJSONObject.put(
				"description",
				LanguageUtil.get(
					httpServletRequest, layoutTypeResourceBundle,
					"layout.types." + layout.getType()));

			int childLayoutsCount = LayoutLocalServiceUtil.getLayoutsCount(
				getSelGroup(), layout.isPrivateLayout(), layout.getLayoutId());

			layoutJSONObject.put(
				"hasChild", childLayoutsCount > 0
			).put(
				"id", layout.getPlid()
			);

			LayoutType layoutType = layout.getLayoutType();

			layoutJSONObject.put(
				"parentable", layoutType.isParentable()
			).put(
				"states", _getLayoutStatesJSONArray(layout)
			).put(
				"title", layout.getName(themeDisplay.getLocale())
			);

			PortletURL portletURL = getPortletURL();

			portletURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));
			portletURL.setParameter(
				"layoutSetBranchId",
				String.valueOf(getActiveLayoutSetBranchId()));
			portletURL.setParameter(
				"privateLayout", String.valueOf(layout.isPrivateLayout()));

			layoutJSONObject.put("url", portletURL.toString());

			layoutsJSONArray.put(layoutJSONObject);
		}

		return layoutsJSONArray;
	}

    private JSONObject _getFirstLayoutColumn(
			boolean privatePages, boolean active)
		throws PortalException {

		JSONObject pagesJSONObject = JSONUtil.put(
			"actions", _getFirstLayoutColumnActionsJSONArray(privatePages)
		).put(
			"active", active
		).put(
			"hasChild", true
		).put(
			"id", LayoutConstants.DEFAULT_PLID
		).put(
			"title", getTitle(privatePages)
		);

		PortletURL pagesURL = getPortletURL();

		pagesURL.setParameter(
			"selPlid", String.valueOf(LayoutConstants.DEFAULT_PLID));
		pagesURL.setParameter("privateLayout", String.valueOf(privatePages));

		pagesJSONObject.put("url", pagesURL.toString());

		return pagesJSONObject;
	}

	private JSONArray _getFirstLayoutColumnActionsJSONArray(
			boolean privatePages)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (isShowFirstColumnConfigureAction()) {
			jsonArray.put(
				JSONUtil.put(
					"url", getFirstColumnConfigureLayoutURL(privatePages)));
		}

		if (isShowAddRootLayoutButton()) {
			jsonArray.put(
				JSONUtil.put(
					"url", getSelectLayoutPageTemplateEntryURL(privatePages)));
		}

		return jsonArray;
	}

	private JSONArray _getFirstLayoutColumnJSONArray() throws Exception {
		JSONArray firstColumnJSONArray = JSONFactoryUtil.createJSONArray();

		Layout selLayout = getSelLayout();

		if (LayoutLocalServiceUtil.hasLayouts(getSelGroup(), false) &&
			isShowPublicPages()) {

			boolean active = !isPrivateLayout();

			if (selLayout != null) {
				active = selLayout.isPublicLayout();
			}

			if (isFirstColumn()) {
				active = false;
			}

			firstColumnJSONArray.put(_getFirstLayoutColumn(false, active));
		}

		if (LayoutLocalServiceUtil.hasLayouts(getSelGroup(), true)) {
			boolean active = isPrivateLayout();

			if (selLayout != null) {
				active = selLayout.isPrivateLayout();
			}

			if (isFirstColumn()) {
				active = false;
			}

			firstColumnJSONArray.put(_getFirstLayoutColumn(true, active));
		}

		return firstColumnJSONArray;
	}

	private JSONArray _getLayoutActionsJSONArray(Layout layout)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (isShowAddChildPageAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//addURL
				getSelectLayoutPageTemplateEntryURL(
					getFirstLayoutPageTemplateCollectionId(), layout.getPlid(),
					layout.isPrivateLayout())
			));
		}

		if (isShowConfigureAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//configureURL
				getConfigureLayoutURL(layout)
			));
		}

		Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
			PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		if (isShowConvertLayoutAction(layout)) {
			if (draftLayout == null) {
				jsonArray.put(JSONUtil.put(
					"url",	//layoutConversionPreviewURL
					getLayoutConversionPreviewURL(layout)
				));
			}
			else {
				jsonArray.put(JSONUtil.put(
					"url",	//deleteLayoutConversionPreviewURL
					getDeleteLayoutURL(layout)
				));
			}
		}

		if (isShowCopyLayoutAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//copyLayoutURL
				getCopyLayoutRenderURL(layout)
			));
		}

		if (isShowDeleteAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//deleteURL
				getDeleteLayoutURL(layout)
			));
		}

		if (isConversionDraft(layout) && isShowConfigureAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//editConversionLayoutURL
				getEditLayoutURL(layout)
			));
		}
		else if (isShowConfigureAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//editLayoutURL
				getEditLayoutURL(layout)
			));
		}

		if (isShowOrphanPortletsAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//orphanPortletsURL
				getOrphanPortletsURL(layout)
			));
		}

		if (isShowPermissionsAction(layout)) {
			jsonArray.put(JSONUtil.put(
				"url",	//permissionsURL
				getPermissionsURL(layout)
			));
		}

		if (layout.isPending()) {
			jsonArray.put(JSONUtil.put(
				"url",	//previewLayoutURL
				getViewLayoutURL(layout)
			));
		}
		else {
			jsonArray.put(JSONUtil.put(
				"url",	//viewLayoutURL
				getViewLayoutURL(layout)
			));
		}

		return jsonArray;
	}

	private JSONArray _getLayoutStatesJSONArray(Layout layout)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
			PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		if (layout.isTypeContent()) {
			boolean published = GetterUtil.getBoolean(
				draftLayout.getTypeSettingsProperty("published"));

			if ((draftLayout.getStatus() == WorkflowConstants.STATUS_DRAFT) ||
				!published) {

				jsonArray.put(
					JSONUtil.put(
						"id", "draft"
					).put(
						"label", LanguageUtil.get(httpServletRequest, "draft")
					));
			}
		}
		else {
			if (draftLayout != null) {
				jsonArray.put(
					JSONUtil.put(
						"id", "conversionPreview"
					).put(
						"label",
						LanguageUtil.get(
							httpServletRequest, "conversionPreview")
					));
			}
		}

		if (layout.getStatus() == WorkflowConstants.STATUS_PENDING) {
			jsonArray.put(
				JSONUtil.put(
					"id", "pending"
				).put(
					"label", LanguageUtil.get(httpServletRequest, "pending")
				));
		}

		return jsonArray;
	}
}
