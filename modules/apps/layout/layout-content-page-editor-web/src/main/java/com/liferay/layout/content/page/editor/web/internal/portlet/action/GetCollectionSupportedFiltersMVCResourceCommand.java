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

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.asset.info.display.contributor.util.ContentAccessor;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.info.collection.provider.item.selector.criterion.InfoCollectionProviderItemSelectorCriterion;
import com.liferay.info.collection.provider.item.selector.criterion.RelatedInfoItemCollectionProviderItemSelectorCriterion;
import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType;
import com.liferay.info.list.renderer.DefaultInfoListRendererContext;
import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.info.list.renderer.InfoListRendererTracker;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.type.WebImage;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.criteria.InfoListItemSelectorReturnType;
import com.liferay.item.selector.criteria.info.item.criterion.InfoListItemSelectorCriterion;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.util.LayoutObjectReferenceUtil;
import com.liferay.layout.list.retriever.DefaultLayoutListRetrieverContext;
import com.liferay.layout.list.retriever.LayoutListRetriever;
import com.liferay.layout.list.retriever.LayoutListRetrieverTracker;
import com.liferay.layout.list.retriever.ListObjectReference;
import com.liferay.layout.list.retriever.ListObjectReferenceFactory;
import com.liferay.layout.list.retriever.ListObjectReferenceFactoryTracker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/get_collection_supported_filters"
	},
	service = MVCResourceCommand.class
)
public class GetCollectionSupportedFiltersMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String collections =
			ParamUtil.getString(resourceRequest, "collections");

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(collections);

		/* Ejemplo de jsonArray que llega
		*
		* [
	{
		"collectionId": "fd85edee-e5f5-545f-faac-712724c2742a",
		"layoutObjectReference": {
			"itemType": "com.liferay.asset.kernel.model.AssetEntry",
			"title": "Most Viewed Assets",
			"type": "com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType",
			"key": "com.liferay.asset.internal.info.collection.provider.MostViewedAssetsInfoCollectionProvider"
		}
	},
	{
		"collectionId": "627d0038-0258-44aa-1bd1-cee6de63f40f",
		"layoutObjectReference": {
			"itemType": "com.liferay.asset.kernel.model.AssetEntry",
			"title": "Highest Rated Assets",
			"type": "com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType",
			"key": "com.liferay.asset.internal.info.collection.provider.HighestRatedAssetsInfoCollectionProvider"
		}
	},
	{
		"collectionId": "e846085e-e496-7ed1-77a5-22ebfb5d504e",
		"layoutObjectReference": {
			"itemType": "com.liferay.asset.kernel.model.AssetEntry",
			"title": "Recent Content",
			"type": "com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType",
			"key": "com.liferay.asset.internal.info.collection.provider.RecentContentInfoCollectionProvider"
		}
	}
]
* * */



		// Lo que deberia devolver:

		/*
		{
			"e846085e-e496-7ed1-77a5-22ebfb5d504e": ["keywords", "category"],
			"627d0038-0258-44aa-1bd1-cee6de63f40f": ["keywords"],
			"fd85edee-e5f5-545f-faac-712724c2742a": []
		 */
		System.out.println(jsonArray);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetCollectionSupportedFiltersMVCResourceCommand.class);

	@Reference
	private FragmentEntryProcessorHelper _fragmentEntryProcessorHelper;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private InfoListRendererTracker _infoListRendererTracker;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private LayoutListRetrieverTracker _layoutListRetrieverTracker;

	@Reference
	private ListObjectReferenceFactoryTracker
		_listObjectReferenceFactoryTracker;

	@Reference
	private Portal _portal;

}