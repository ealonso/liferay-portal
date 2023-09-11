package com.liferay.layout.page.template.admin.web.internal.util;

import com.liferay.layout.page.template.constants.LayoutPageTemplateConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayoutPageTemplateCollectionsUtil {

	public static List<BreadcrumbEntry> getLayoutPageTemplateBreadcrumbEntries(
		HttpServletRequest httpServletRequest,
		RenderResponse renderResponse){

		List<BreadcrumbEntry> breadcrumbEntries = new ArrayList<>();

		long curLayoutPageTemplateCollectionId = ParamUtil.getLong(
			httpServletRequest, "layoutPageTemplateCollectionId",
			LayoutPageTemplateConstants.
				PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT);

		 PortletURL portletURL = PortletURLBuilder.createRenderURL(
			renderResponse
		).setMVCPath(
			"/view.jsp"
		).setNavigation(
			() -> {
				String navigation = ParamUtil.getString(
					httpServletRequest, "navigation");

				if (Validator.isNotNull(navigation)) {
					return navigation;
				}

				return null;
			}
		).setParameter(
			"layoutPageTemplateCollectionId", curLayoutPageTemplateCollectionId
		).buildPortletURL();

		List<LayoutPageTemplateCollection> ancestorsCollections = getCollectionAncestors(
			curLayoutPageTemplateCollectionId);

		for (LayoutPageTemplateCollection curCollection : ancestorsCollections) {
			BreadcrumbEntry collectionBreadcrumbEntry = new BreadcrumbEntry();

			collectionBreadcrumbEntry.setTitle(curCollection.getName());

			portletURL.setParameter(
				"layoutPageTemplateCollectionId", String.valueOf(curCollection.getLayoutPageTemplateCollectionId()));

			collectionBreadcrumbEntry.setURL(portletURL.toString());

			breadcrumbEntries.add(collectionBreadcrumbEntry);
		}

		Collections.reverse(breadcrumbEntries);

		return breadcrumbEntries;
	}

	private static List<LayoutPageTemplateCollection> getCollectionAncestors(long layoutPageTemplateCollectionId) {

		if (layoutPageTemplateCollectionId == LayoutPageTemplateConstants.
			PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT){
			return new ArrayList<>();
		}

		List<LayoutPageTemplateCollection> ancestorsCollections = new ArrayList<>();

		while (layoutPageTemplateCollectionId != LayoutPageTemplateConstants.
			PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT){

			try {
				LayoutPageTemplateCollection collection =
					LayoutPageTemplateCollectionLocalServiceUtil.getLayoutPageTemplateCollection(
						layoutPageTemplateCollectionId);

				ancestorsCollections.add(collection);

				layoutPageTemplateCollectionId =
					collection.getParentLayoutPageTemplateCollectionId();
			} catch (PortalException portalException){
				_log.error(portalException);
			}
		}

		return ancestorsCollections;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutPageTemplateCollectionsUtil.class);
}
