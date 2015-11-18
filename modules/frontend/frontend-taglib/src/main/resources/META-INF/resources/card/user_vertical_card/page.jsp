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

<%@ include file="/card/user_vertical_card/init.jsp" %>

<div class="taglib-vertical-card <%= Validator.isNotNull(cssClass) ? cssClass : StringPool.BLANK %> <%= showCheckbox ? "selectable" : StringPool.BLANK %>" <%= AUIUtil.buildData(data) %>>
	<div class="<%= showCheckbox ? "checkbox checkbox-default toggle-card-dm" : StringPool.BLANK %>">
		<c:choose>
			<c:when test="<%= (rowChecker != null) && (resultRow != null) %>">
				<%= rowChecker.getRowCheckBox(request, rowChecker.isChecked(resultRow.getObject()), rowChecker.isDisabled(resultRow.getObject()), resultRow.getPrimaryKey()) %>
			</c:when>
			<c:when test="<%= showCheckbox %>">
				<aui:input checked="<%= checkboxChecked %>" cssClass="<%= checkboxCSSClass %>" data="<%= checkboxData %>" disabled="<%= checkboxDisabled %>" id="<%= checkboxId %>" label="" name="<%= checkboxName %>" title='<%= LanguageUtil.format(request, "select-x", new Object[] {HtmlUtil.escape(title)}) %>' type="checkbox" useNamespace="<%= false %>" value="<%= checkboxValue %>" wrappedField="<%= true %>" />
			</c:when>
		</c:choose>

		<div class="card card-dm <%= showCheckbox ? "toggle-card-container" : StringPool.BLANK %>">
			<c:choose>
				<c:when test="<%= Validator.isNotNull(portraitURL) %>">
					<div class="aspect-ratio aspect-ratio-bg-center aspect-ratio-bg-cover" style="background-image: url('<%= portraitURL %>')">
						<aui:a href="<%= url %>">
							<img alt="" class="sr-only" src="<%= portraitURL %>" />
						</aui:a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="aspect-ratio aspect-ratio-bg-center aspect-ratio-bg-cover <%= colorCssClass %>">
						<span class="initials"><%= userInitials %></span>
					</div>
				</c:otherwise>
			</c:choose>

			<c:if test="<%= Validator.isNotNull(actionJsp) || Validator.isNotNull(subtitle) || Validator.isNotNull(title) %>">
				<div class="card-footer">
					<div class="card-dm-more-options">
						<liferay-util:include page="<%= actionJsp %>" servletContext="<%= actionJspServletContext %>" />
					</div>

					<div class="card-dm-details">
						<c:if test="<%= Validator.isNotNull(title) %>">
							<aui:a href="<%= url %>">
								<div class="card-dm-text-large"><%= HtmlUtil.escape(title) %></div>
							</aui:a>
						</c:if>

						<c:if test="<%= Validator.isNotNull(subtitle) %>">
							<div class="card-dm-text">
								<%= HtmlUtil.escape(subtitle) %>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>