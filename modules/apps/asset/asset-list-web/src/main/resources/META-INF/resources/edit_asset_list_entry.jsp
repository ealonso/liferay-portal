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
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(assetListDisplayContext.getAssetListEntryTitle());

List<ScreenNavigationEntry> screenNavigationEntries = assetListDisplayContext.getScreenNavigationEntries();

ScreenNavigationEntry activeScreenNavigationEntry = assetListDisplayContext.getActiveScreenNavigationEntry();
%>

<div class="container-fluid container-fluid-max-xl container-view">
	<div class="row">
		<div class="col-lg-3">
			<nav class="menubar menubar-transparent menubar-vertical-expand-lg">
				<ul class="nav nav-nested">
					<li class="nav-item">
						<div class="autofit-row autofit-row-center">
							<div class="autofit-col autofit-col-expand">
								<strong>
									<liferay-ui:message key="asset-list" />
								</strong>
							</div>
						</div>

						<ul class="nav nav-stacked">

							<%
							for (ScreenNavigationEntry screenNavigationEntry : screenNavigationEntries) {
							%>

								<li class="nav-item">

									<%
									PortletURL screenNavigationEntryURL = renderResponse.createRenderURL();

									screenNavigationEntryURL.setParameter("screenNavigationEntryKey", screenNavigationEntry.getEntryKey());
									%>

									<a class="nav-link truncate-text <%= Objects.equals(activeScreenNavigationEntry.getEntryKey(), screenNavigationEntry.getEntryKey()) ? "active" : StringPool.BLANK %>" href="<%= screenNavigationEntryURL.toString() %>">
										<%= HtmlUtil.escape(screenNavigationEntry.getLabel(locale)) %>
									</a>
								</li>

							<%
							}
							%>

						</ul>
					</li>
				</ul>
			</nav>
		</div>

		<div class="col-lg-9">
			<div class="sheet">
				<div class="sheet-section">

					<%
					activeScreenNavigationEntry.render(request, response);
					%>

				</div>
			</div>
		</div>
	</div>
</div>