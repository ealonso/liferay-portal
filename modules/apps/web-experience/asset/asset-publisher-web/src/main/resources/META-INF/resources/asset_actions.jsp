<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
boolean showIconLabel = ((Boolean)request.getAttribute("view.jsp-showIconLabel")).booleanValue();

AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");

boolean showEditURL = ParamUtil.getBoolean(request, "showEditURL", true);

PortletURL editPortletURL = null;

if (showEditURL && assetRenderer.hasEditPermission(permissionChecker)) {
	PortletURL redirectURL = liferayPortletResponse.createLiferayPortletURL(plid, portletDisplay.getId(), PortletRequest.RENDER_PHASE, false);

	redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp");

	String fullContentRedirect = (String)request.getAttribute("view.jsp-fullContentRedirect");

	if (fullContentRedirect != null) {
		redirectURL.setParameter("redirect", fullContentRedirect);
	}
	else {
		redirectURL.setParameter("redirect", currentURL);
	}

	redirectURL.setWindowState(LiferayWindowState.POP_UP);

	editPortletURL = assetRenderer.getURLEdit(liferayPortletRequest, liferayPortletResponse, LiferayWindowState.POP_UP, redirectURL);

	editPortletURL.setParameter("hideDefaultSuccessMessage", Boolean.TRUE.toString());
	editPortletURL.setParameter("showHeader", Boolean.FALSE.toString());
}

PortletURL viewFullContentURL = renderResponse.createRenderURL();

AssetRendererFactory assetRendererFactory = assetRenderer.getAssetRendererFactory();

AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRenderer.getClassName(), assetRenderer.getClassPK());

viewFullContentURL.setParameter("mvcPath", "/view_content.jsp");
viewFullContentURL.setParameter("type", assetRendererFactory.getType());

if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
	if (assetRenderer.getGroupId() != scopeGroupId) {
		viewFullContentURL.setParameter("groupId", String.valueOf(assetRenderer.getGroupId()));
	}

	viewFullContentURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
}

String languageId = LanguageUtil.getLanguageId(request);

String title = assetRenderer.getTitle(LocaleUtil.fromLanguageId(languageId));

String socialBookmarksDisplayPosition = assetPublisherDisplayContext.getSocialBookmarksDisplayPosition();
%>

<c:if test="<%= editPortletURL != null %>">
	<c:if test='<%= assetPublisherDisplayContext.isEnableSocialBookmarks() && socialBookmarksDisplayPosition.equals("top") %>'>
		<div class="icon-monospaced visible-interaction">
			<liferay-ui:social-bookmarks
				contentId="<%= String.valueOf(assetEntry.getEntryId()) %>"
				displayStyle="<%= assetPublisherDisplayContext.getSocialBookmarksDisplayStyle() %>"
				target="_blank"
				title="<%= title %>"
				url="<%= PortalUtil.getCanonicalURL(viewFullContentURL.toString(), themeDisplay, layout) %>"
			/>
		</div>
	</c:if>

	<div class="pull-right">
		<div class="icon-monospaced visible-interaction">

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("destroyOnHide", true);
			data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
			data.put("title", LanguageUtil.format(request, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale)), false));
			%>

			<liferay-ui:icon
				data="<%= data %>"
				icon="pencil"
				label="<%= false %>"
				markupView="lexicon"
				message='<%= showIconLabel ? LanguageUtil.format(request, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}, false) : LanguageUtil.format(request, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale)), false) %>'
				method="get"
				url="<%= editPortletURL.toString() %>"
				useDialog="<%= true %>"
			/>
		</div>

		<c:if test='<%= assetPublisherDisplayContext.isEnableSocialBookmarks() && socialBookmarksDisplayPosition.equals("bottom") %>'>
			<div class="icon-monospaced visible-interaction">
				<liferay-ui:social-bookmarks
					contentId="<%= String.valueOf(assetEntry.getEntryId()) %>"
					displayStyle="<%= assetPublisherDisplayContext.getSocialBookmarksDisplayStyle() %>"
					target="_blank"
					title="<%= title %>"
					url="<%= PortalUtil.getCanonicalURL(viewFullContentURL.toString(), themeDisplay, layout) %>"
				/>
			</div>
		</c:if>
	</div>
</c:if>