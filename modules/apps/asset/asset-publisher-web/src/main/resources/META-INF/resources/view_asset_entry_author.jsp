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
AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");
%>

<c:if test="<%= assetPublisherDisplayContext.isShowAuthor() || (assetPublisherDisplayContext.isShowCreateDate() && (assetEntry.getCreateDate() != null)) || (assetPublisherDisplayContext.isShowPublishDate() && (assetEntry.getPublishDate() != null)) || (assetPublisherDisplayContext.isShowExpirationDate() && (assetEntry.getExpirationDate() != null)) || (assetPublisherDisplayContext.isShowModifiedDate() && (assetEntry.getModifiedDate() != null)) || assetPublisherDisplayContext.isShowViewCount() %>">

	<%
	User assetRendererUser = UserLocalServiceUtil.getUser(assetRenderer.getUserId());
	%>

	<div class="autofit-row mb-4 metadata-author">
		<c:if test="<%= assetPublisherDisplayContext.isShowAuthor() %>">
			<div class="asset-avatar autofit-col inline-item-before mr-3 pt-1">
				<span class="user-avatar-image">
					<div class="sticker sticker-circle sticker-light user-icon user-icon-default <%= LexiconUtil.getUserColorCssClass(assetRendererUser) %> ">
						<c:choose>
							<c:when test="<%= assetRendererUser.getPortraitId() <= 0 %>">
								<aui:icon image="user" markupView="lexicon" />
							</c:when>
							<c:otherwise>
								<img class="sticker-img" src="<%= HtmlUtil.escape(UserConstants.getPortraitURL(themeDisplay.getPathImage(), assetRendererUser.isMale(), assetRendererUser.getPortraitId(), assetRendererUser.getUserUuid())) %>" />
							</c:otherwise>
						</c:choose>
					</div>
				</span>
			</div>
		</c:if>

		<div class="autofit-col autofit-col-expand">
			<div class="autofit-row">
				<div class="autofit-col autofit-col-expand">
					<c:if test="<%= assetPublisherDisplayContext.isShowAuthor() %>">
						<div class="text-truncate-inline">
							<span class="text-truncate user-info"><strong><%= HtmlUtil.escape(assetRendererUser.getFullName()) %></strong></span>
						</div>
					</c:if>

					<%
					StringBundler sb = new StringBundler(13);

					if (assetPublisherDisplayContext.isShowCreateDate() && (assetEntry.getCreateDate() != null)) {
						sb.append(LanguageUtil.get(request, "created"));
						sb.append(StringPool.SPACE);
						sb.append(dateFormatDate.format(assetEntry.getCreateDate()));
						sb.append(" - ");
					}

					if (assetPublisherDisplayContext.isShowPublishDate() && (assetEntry.getPublishDate() != null)) {
						sb.append(LanguageUtil.get(request, "published"));
						sb.append(StringPool.SPACE);
						sb.append(dateFormatDate.format(assetEntry.getPublishDate()));
						sb.append(" - ");
					}

					if (assetPublisherDisplayContext.isShowExpirationDate() && (assetEntry.getExpirationDate() != null)) {
						sb.append(LanguageUtil.get(request, "expired"));
						sb.append(StringPool.SPACE);
						sb.append(dateFormatDate.format(assetEntry.getExpirationDate()));
						sb.append(" - ");
					}

					if (assetPublisherDisplayContext.isShowModifiedDate() && (assetEntry.getModifiedDate() != null)) {
						Date modifiedDate = assetEntry.getModifiedDate();

						String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - modifiedDate.getTime(), true);

						sb.append(LanguageUtil.format(request, "modified-x-ago", modifiedDateDescription));
					}
					else if (sb.index() > 1) {
						sb.setIndex(sb.index() - 1);
					}
					%>

					<div class="asset-user-info text-secondary">
						<span class="date-info"><%= sb.toString() %></span>
					</div>

					<c:if test="<%= assetPublisherDisplayContext.isShowViewCount() %>">
						<div class="asset-view-count-info text-secondary">
							<span class="view-count-info"><%= assetEntry.getViewCount() %> <liferay-ui:message key='<%= (assetEntry.getViewCount() == 1) ? "view" : "views" %>' /></span>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</c:if>