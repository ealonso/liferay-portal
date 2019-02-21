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

package com.liferay.asset.taglib.servlet.taglib.soy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.taglib.internal.util.AssetCategoryUtil;
import com.liferay.asset.taglib.internal.util.AssetVocabularyUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolverUtil;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Chema Balsas
 */
public class AssetCategoriesSelectorTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		putValue("eventName", _getEventName());
		putValue("id", "assetCategoriesSelector");
		putValue("inputName", _hiddenInput + StringPool.UNDERLINE);
		putValue("portletURL", _getPortletURL());
		putValue(
			"spritemap", themeDisplay.getPathThemeImages() + "/clay/icons.svg");
		putValue("vocabularies", _getVocabularies());

		String templateNamespace =
			"com.liferay.asset.taglib.AssetCategoriesSelector.render";

		if (Validator.isNull(_className)) {
			templateNamespace =
				"com.liferay.asset.taglib.AssetVocabularyCategoriesSelector." +
					"render";
		}

		setTemplateNamespace(templateNamespace);

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		return NPMResolverUtil.resolveModuleName(
			AssetCategoriesSelectorTag.class,
			"asset-taglib/asset_categories_selector/AssetCategoriesSelector." +
				"es");
	}

	public void setCategoryIds(String categoryIds) {
		_categoryIds = categoryIds;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setClassTypePK(long classTypePK) {
		_classTypePK = classTypePK;
	}

	public void setGroupIds(long[] groupIds) {
		_groupIds = groupIds;
	}

	public void setHiddenInput(String hiddenInput) {
		_hiddenInput = hiddenInput;
	}

	public void setIgnoreRequestValue(boolean ignoreRequestValue) {
		_ignoreRequestValue = ignoreRequestValue;
	}

	public void setShowRequiredLabel(boolean showRequiredLabel) {
		_showRequiredLabel = showRequiredLabel;
	}

	public void setSingleSelect(boolean singleSelect) {
		_singleSelect = singleSelect;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_categoryIds = null;
		_className = null;
		_classPK = 0;
		_classTypePK = AssetCategoryConstants.ALL_CLASS_TYPE_PK;
		_groupIds = null;
		_hiddenInput = "assetCategoryIds";
		_ignoreRequestValue = false;
		_showRequiredLabel = true;
		_singleSelect = false;
	}

	private List<AssetVocabulary> _getAssetVocabularies() {
		List<AssetVocabulary> vocabularies =
			AssetVocabularyServiceUtil.getGroupVocabularies(_getGroupIds());

		if (Validator.isNotNull(_className)) {
			vocabularies = AssetVocabularyUtil.filterVocabularies(
				vocabularies, _className, _classTypePK);
		}

		return ListUtil.filter(
			vocabularies,
			vocabulary -> {
				int vocabularyCategoriesCount =
					AssetCategoryServiceUtil.getVocabularyCategoriesCount(
						vocabulary.getGroupId(), vocabulary.getVocabularyId());

				if (vocabularyCategoriesCount > 0) {
					return true;
				}

				return false;
			});
	}

	private String[] _getCategoryIdsTitles(AssetVocabulary vocabulary) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String categoryIds = StringPool.BLANK;

		if (Validator.isNotNull(_categoryIds)) {
			categoryIds = _categoryIds;
		}

		if (Validator.isNull(_className)) {
			if (!_ignoreRequestValue) {
				String categoryIdsParam = request.getParameter(_hiddenInput);

				if (categoryIdsParam != null) {
					categoryIds = categoryIdsParam;
				}
			}

			return AssetCategoryUtil.getCategoryIdsTitles(
				categoryIds, StringPool.BLANK, 0, themeDisplay);
		}

		try {
			String categoryNames = StringPool.BLANK;

			if (Validator.isNotNull(_className) && (_classPK > 0)) {
				List<AssetCategory> categories =
					AssetCategoryServiceUtil.getCategories(
						_className, _classPK);

				categoryIds = ListUtil.toString(
					categories, AssetCategory.CATEGORY_ID_ACCESSOR);
				categoryNames = ListUtil.toString(
					categories, AssetCategory.NAME_ACCESSOR);
			}

			if (!_ignoreRequestValue) {
				String categoryIdsParam = request.getParameter(
					_hiddenInput + StringPool.UNDERLINE +
						vocabulary.getVocabularyId());

				if (Validator.isNotNull(categoryIdsParam)) {
					categoryIds = categoryIdsParam;
				}
			}

			return AssetCategoryUtil.getCategoryIdsTitles(
				categoryIds, categoryNames, vocabulary.getVocabularyId(),
				themeDisplay);
		}
		catch (Exception e) {
		}

		return new String[0];
	}

	private String _getEventName() {
		String portletId = PortletProviderUtil.getPortletId(
			AssetCategory.class.getName(), PortletProvider.Action.BROWSE);

		return PortalUtil.getPortletNamespace(portletId) + "selectCategory";
	}

	private long[] _getGroupIds() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (ArrayUtil.isEmpty(_groupIds)) {
				return PortalUtil.getCurrentAndAncestorSiteGroupIds(
					themeDisplay.getScopeGroupId());
			}

			return PortalUtil.getCurrentAndAncestorSiteGroupIds(_groupIds);
		}
		catch (Exception e) {
		}

		return new long[0];
	}

	private String _getPortletURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				request, AssetCategory.class.getName(),
				PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			portletURL.setParameter("eventName", _getEventName());
			portletURL.setParameter(
				"selectedCategories", "{selectedCategories}");
			portletURL.setParameter("singleSelect", "{singleSelect}");
			portletURL.setParameter("vocabularyIds", "{vocabularyIds}");
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	private List<Map<String, Object>> _getVocabularies() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Map<String, Object>> vocabularies = new ArrayList<>();

		for (AssetVocabulary vocabulary : _getAssetVocabularies()) {
			Map<String, Object> vocabularyMap = new HashMap<>();

			vocabularyMap.put(
				"group",
				_getVocabularyGroupName(
					vocabulary, themeDisplay.getSiteGroupId(),
					themeDisplay.getLocale()));
			vocabularyMap.put("id", vocabulary.getVocabularyId());
			vocabularyMap.put(
				"required",
				vocabulary.isRequired(
					PortalUtil.getClassNameId(_className), _classTypePK) &&
				_showRequiredLabel);
			vocabularyMap.put(
				"title", vocabulary.getTitle(themeDisplay.getLocale()));

			String[] categoryIdsTitles = _getCategoryIdsTitles(vocabulary);

			String selectedCategoryIds = categoryIdsTitles[0];

			vocabularyMap.put("selectedCategoryIds", selectedCategoryIds);

			if (Validator.isNotNull(selectedCategoryIds)) {
				List<HashMap<String, Object>> selectedItems = new ArrayList<>();

				String[] categoryIds = selectedCategoryIds.split(",");
				String[] categoryTitles = categoryIdsTitles[1].split(
					AssetCategoryUtil.CATEGORY_SEPARATOR);

				for (int j = 0; j < categoryIds.length; j++) {
					HashMap<String, Object> category = new HashMap<>();

					category.put("label", categoryTitles[j]);
					category.put("value", categoryIds[j]);

					selectedItems.add(category);
				}

				vocabularyMap.put("selectedItems", selectedItems);
			}

			vocabularyMap.put("singleSelect", !vocabulary.isMultiValued());

			vocabularies.add(vocabularyMap);
		}

		return vocabularies;
	}

	private String _getVocabularyGroupName(
		AssetVocabulary assetVocabulary, long groupId, Locale locale) {

		if (assetVocabulary.getGroupId() == groupId) {
			return StringPool.BLANK;
		}

		Group group = GroupLocalServiceUtil.fetchGroup(
			assetVocabulary.getGroupId());

		if (group == null) {
			return StringPool.BLANK;
		}

		try {
			return group.getDescriptiveName(locale);
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	private String _categoryIds;
	private String _className;
	private long _classPK;
	private long _classTypePK = AssetCategoryConstants.ALL_CLASS_TYPE_PK;
	private long[] _groupIds;
	private String _hiddenInput = "assetCategoryIds";
	private boolean _ignoreRequestValue;
	private boolean _showRequiredLabel = true;
	private boolean _singleSelect;

}