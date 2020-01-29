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

package com.liferay.layout.internal.util;

import com.liferay.fragment.contributor.FragmentCollectionContributor;
import com.liferay.fragment.contributor.FragmentCollectionContributorTracker;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererTracker;
import com.liferay.fragment.renderer.constants.FragmentRendererConstants;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.headless.delivery.dto.v1_0.ColumnDefinition;
import com.liferay.headless.delivery.dto.v1_0.FragmentDefinition;
import com.liferay.headless.delivery.dto.v1_0.FragmentImage;
import com.liferay.headless.delivery.dto.v1_0.LookAndFeel;
import com.liferay.headless.delivery.dto.v1_0.MasterPage;
import com.liferay.headless.delivery.dto.v1_0.PageDefinition;
import com.liferay.headless.delivery.dto.v1_0.PageElement;
import com.liferay.headless.delivery.dto.v1_0.RowDefinition;
import com.liferay.headless.delivery.dto.v1_0.SectionDefinition;
import com.liferay.headless.delivery.dto.v1_0.Settings;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.util.LayoutDataConverter;
import com.liferay.layout.util.PageDefinitionConverter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = PageDefinitionConverter.class)
public class PageDefinitionConverterImpl implements PageDefinitionConverter {

	public PageDefinition toPageDefinition(long plid, Locale locale)
		throws PortalException {

		Layout layout = _layoutLocalService.fetchLayout(plid);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(plid);

		return new PageDefinition() {
			{
				dateCreated = layout.getCreateDate();
				dateModified = layout.getModifiedDate();
				friendlyURLPath = layout.getFriendlyURL(locale);
				setName(
					() -> {
						if (layoutPageTemplateEntry == null) {
							return layout.getName(locale);
						}

						return layoutPageTemplateEntry.getName();
					});
				pageElements = _toPageElements(layout, locale);
				settings = _toSettings(layout, locale);
				setCollectionName(
					() -> {
						if (layoutPageTemplateEntry == null) {
							return null;
						}

						LayoutPageTemplateCollection
							layoutPageTemplateCollection =
								_layoutPageTemplateCollectionService.
									fetchLayoutPageTemplateCollection(
										layoutPageTemplateEntry.
											getLayoutPageTemplateCollectionId());

						return layoutPageTemplateCollection.getName();
					});
			}
		};
	}

	private String _getFragmentCollectionName(
		FragmentEntry fragmentEntry, Locale locale, String rendererKey) {

		if (fragmentEntry == null) {
			if (Validator.isNull(rendererKey)) {
				rendererKey =
					FragmentRendererConstants.
						FRAGMENT_ENTRY_FRAGMENT_RENDERER_KEY;
			}

			FragmentRenderer fragmentRenderer =
				_fragmentRendererTracker.getFragmentRenderer(rendererKey);

			return LanguageUtil.get(
				ResourceBundleUtil.getBundle(
					locale, fragmentRenderer.getClass()),
				"fragment.collection.label." +
					fragmentRenderer.getCollectionKey());
		}

		FragmentCollection fragmentCollection =
			_fragmentCollectionLocalService.fetchFragmentCollection(
				fragmentEntry.getFragmentCollectionId());

		if (fragmentCollection != null) {
			return fragmentCollection.getName();
		}

		List<FragmentCollectionContributor> fragmentCollectionContributors =
			_fragmentCollectionContributorTracker.
				getFragmentCollectionContributors();

		String[] parts = StringUtil.split(rendererKey, StringPool.DASH);

		for (FragmentCollectionContributor fragmentCollectionContributor :
				fragmentCollectionContributors) {

			if (Objects.equals(
					fragmentCollectionContributor.getFragmentCollectionKey(),
					parts[0])) {

				return fragmentCollectionContributor.getName(locale);
			}
		}

		return null;
	}

	private FragmentEntry _getFragmentEntry(
		long fragmentEntryId, Locale locale, String rendererKey) {

		FragmentEntry fragmentEntry =
			_fragmentEntryLocalService.fetchFragmentEntry(fragmentEntryId);

		if (fragmentEntry != null) {
			return fragmentEntry;
		}

		Map<String, FragmentEntry> fragmentEntries =
			_fragmentCollectionContributorTracker.getFragmentEntries(locale);

		return fragmentEntries.get(rendererKey);
	}

	private String _getFragmentName(
		FragmentEntry fragmentEntry, Locale locale, String portletId,
		String rendererKey) {

		if (fragmentEntry != null) {
			return fragmentEntry.getName();
		}

		if (Validator.isNotNull(portletId)) {
			return _portal.getPortletTitle(portletId, locale);
		}

		if (Validator.isNull(rendererKey)) {
			rendererKey =
				FragmentRendererConstants.FRAGMENT_ENTRY_FRAGMENT_RENDERER_KEY;
		}

		FragmentRenderer fragmentRenderer =
			_fragmentRendererTracker.getFragmentRenderer(rendererKey);

		return fragmentRenderer.getLabel(locale);
	}

	private ColumnDefinition _toColumnDefinition(JSONObject configJSONObject) {
		return new ColumnDefinition() {
			{
				setSize(
					() -> {
						if (configJSONObject.isNull("size")) {
							return null;
						}

						return configJSONObject.getInt("size");
					});
			}
		};
	}

	private FragmentDefinition _toFragmentDefinition(
			JSONObject configJSONObject, Locale locale)
		throws JSONException {

		FragmentEntryLink fragmentEntryLink =
			_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
				GetterUtil.getLong(
					configJSONObject.getString("fragmentEntryLinkId")));

		String rendererKey = fragmentEntryLink.getRendererKey();

		FragmentEntry fragmentEntry = _getFragmentEntry(
			fragmentEntryLink.getFragmentEntryId(), locale, rendererKey);

		JSONObject editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
			fragmentEntryLink.getEditableValues());

		String portletId = editableValuesJSONObject.getString("portletId");

		return new FragmentDefinition() {
			{
				fragmentCollectionName = _getFragmentCollectionName(
					fragmentEntry, locale, rendererKey);
				fragmentConfig = _toMap(
					_fragmentEntryConfigurationParser.
						getConfigurationJSONObject(
							fragmentEntryLink.getConfiguration(),
							fragmentEntryLink.getEditableValues(),
							new long[] {0L}));
				fragmentName = _getFragmentName(
					fragmentEntry, locale, portletId, rendererKey);
			}
		};
	}

	private LookAndFeel _toLookAndFeel(UnicodeProperties unicodeProperties) {
		UnicodeProperties themeSettingsUnicodeProperties =
			new UnicodeProperties();

		for (Map.Entry<String, String> entry : unicodeProperties.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith("lfr-theme:")) {
				themeSettingsUnicodeProperties.setProperty(
					key, entry.getValue());
			}
		}

		return new LookAndFeel() {
			{
				themeSettings = themeSettingsUnicodeProperties;
			}
		};
	}

	private Map<String, Object> _toMap(JSONObject jsonObject) {
		return new HashMap<String, Object>() {
			{
				Set<String> keys = jsonObject.keySet();

				Iterator<String> iterator = keys.iterator();

				while (iterator.hasNext()) {
					String key = iterator.next();

					put(key, jsonObject.get(key));
				}
			}
		};
	}

	private MasterPage _toMasterPage(long masterLayoutPlid, Locale locale) {
		Layout layout = _layoutLocalService.fetchLayout(masterLayoutPlid);

		return new MasterPage() {
			{
				setName(
					() -> {
						if (layout == null) {
							return null;
						}

						return layout.getName(locale);
					});
			}
		};
	}

	private PageElement _toPageElement(
			JSONObject itemsJSONObject, JSONObject jsonObject, Locale locale)
		throws JSONException {

		List<PageElement> childrenPageElements = new ArrayList<>();

		JSONArray childrenJSONArray = jsonObject.getJSONArray("children");

		for (int i = 0; i < childrenJSONArray.length(); i++) {
			String childUUID = childrenJSONArray.getString(i);

			JSONObject childJSONObject = itemsJSONObject.getJSONObject(
				childUUID);

			JSONArray grandChildrenJSONArray = childJSONObject.getJSONArray(
				"children");

			if (grandChildrenJSONArray.length() == 0) {
				childrenPageElements.add(
					_toPageElement(
						childJSONObject.getJSONObject("config"), locale,
						childJSONObject.getString("type")));
			}
			else {
				childrenPageElements.add(
					_toPageElement(itemsJSONObject, childJSONObject, locale));
			}
		}

		PageElement pageElement = _toPageElement(
			jsonObject.getJSONObject("config"), locale,
			jsonObject.getString("type"));

		pageElement.setPageElements(
			childrenPageElements.toArray(new PageElement[0]));

		return pageElement;
	}

	private PageElement _toPageElement(
			JSONObject configJSONObject, Locale locale, String type)
		throws JSONException {

		if (type.equals("column")) {
			return new PageElement() {
				{
					definition = _toColumnDefinition(configJSONObject);
					type = PageElement.Type.COLUMN;
				}
			};
		}
		else if (type.equals("container")) {
			return new PageElement() {
				{
					definition = _toSectionDefinition(configJSONObject);
					type = PageElement.Type.SECTION;
				}
			};
		}
		else if (type.equals("fragment")) {
			return new PageElement() {
				{
					definition = _toFragmentDefinition(
						configJSONObject, locale);
					type = PageElement.Type.FRAGMENT;
				}
			};
		}
		else if (type.equals("row")) {
			return new PageElement() {
				{
					definition = _toRowDefinition(configJSONObject);
					type = PageElement.Type.ROW;
				}
			};
		}

		return null;
	}

	private PageElement[] _toPageElements(Layout layout, Locale locale)
		throws JSONException {

		List<PageElement> pageElements = new ArrayList<>();

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					layout.getGroupId(), _portal.getClassNameId(Layout.class),
					layout.getPlid());

		String layoutData = LayoutDataConverter.convert(
			layoutPageTemplateStructure.getData(0L));

		JSONObject layoutDataJSONObject = JSONFactoryUtil.createJSONObject(
			layoutData);

		JSONObject rootItemsJSONObject = layoutDataJSONObject.getJSONObject(
			"rootItems");

		String mainUUID = rootItemsJSONObject.getString("main");

		JSONObject itemsJSONObject = layoutDataJSONObject.getJSONObject(
			"items");

		JSONObject mainJSONObject = itemsJSONObject.getJSONObject(mainUUID);

		JSONArray childrenJSONArray = mainJSONObject.getJSONArray("children");

		for (int i = 0; i < childrenJSONArray.length(); i++) {
			String childUUID = childrenJSONArray.getString(i);

			pageElements.add(
				_toPageElement(
					itemsJSONObject, itemsJSONObject.getJSONObject(childUUID),
					locale));
		}

		return pageElements.toArray(new PageElement[0]);
	}

	private RowDefinition _toRowDefinition(JSONObject configJSONObject) {
		return new RowDefinition() {
			{
				setGutters(
					() -> {
						if (configJSONObject.isNull("gutters")) {
							return null;
						}

						return configJSONObject.getBoolean("gutters");
					});
				setNumberOfColumns(
					() -> {
						if (configJSONObject.isNull("numberOfColumns")) {
							return null;
						}

						return configJSONObject.getInt("numberOfColumns");
					});
			}
		};
	}

	private SectionDefinition _toSectionDefinition(
		JSONObject configJSONObject) {

		return new SectionDefinition() {
			{
				backgroundColorCssClass = configJSONObject.getString(
					"backgroundColorCssClass", null);

				containerType = ContainerType.valueOf(
					StringUtil.toUpperCase(configJSONObject.getString("type")));

				setBackgroundImage(
					() -> {
						JSONObject backgroundImageJSONObject =
							configJSONObject.getJSONObject("backgroundImage");

						if ((backgroundImageJSONObject == null) ||
							(backgroundImageJSONObject.length() == 0)) {

							return null;
						}

						return new FragmentImage() {
							{
								setTitle(
									_toValueMap(
										backgroundImageJSONObject, "title"));
								setUrl(
									_toValueMap(
										backgroundImageJSONObject, "url"));
							}
						};
					});

				setPaddingBottom(
					() -> {
						if (configJSONObject.isNull("paddingBottom")) {
							return null;
						}

						return configJSONObject.getInt("paddingBottom");
					});

				setPaddingHorizontal(
					() -> {
						if (configJSONObject.isNull("paddingHorizontal")) {
							return null;
						}

						return configJSONObject.getInt("paddingHorizontal");
					});

				setPaddingTop(
					() -> {
						if (configJSONObject.isNull("paddingTop")) {
							return null;
						}

						return configJSONObject.getInt("paddingTop");
					});
			}
		};
	}

	private Settings _toSettings(Layout layout, Locale locale) {
		return new Settings() {
			{
				lookAndFeel = _toLookAndFeel(
					layout.getTypeSettingsProperties());
				masterPage = _toMasterPage(
					layout.getMasterLayoutPlid(), locale);
			}
		};
	}

	private Map<String, String> _toValueMap(
		JSONObject backgroundImageJSONObject, String name) {

		if (backgroundImageJSONObject == null) {
			return null;
		}

		return HashMapBuilder.put(
			"value", backgroundImageJSONObject.getString(name)
		).build();
	}

	@Reference
	private FragmentCollectionContributorTracker
		_fragmentCollectionContributorTracker;

	@Reference
	private FragmentCollectionLocalService _fragmentCollectionLocalService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private FragmentRendererTracker _fragmentRendererTracker;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateCollectionService
		_layoutPageTemplateCollectionService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

}