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

FragmentServiceConfigurationDisplayContext fragmentServiceConfigurationDisplayContext = (FragmentServiceConfigurationDisplayContext)request.getAttribute(FragmentServiceConfigurationDisplayContext.class.getName());
%>

<aui:form action="<%= fragmentServiceConfigurationDisplayContext.getEditFragmentServiceConfigurationConfigurationURL() %>" method="post" name="fm">
	<clay:sheet
		size="xl"
	>
		<liferay-ui:error exception="<%= ConfigurationModelListenerException.class %>" message="there-was-an-unknown-error" />

		<clay:sheet-header>
			<h2>
				<liferay-ui:message key="fragment-configuration-name" />
			</h2>
		</clay:sheet-header>

		<clay:sheet-section>
			<react:component
				module="js/FragmentServiceConfiguration"
				props='<%=
					HashMapBuilder.<String, Object>put(
						"alreadyPropagateContributedFragmentChanges", fragmentServiceConfigurationDisplayContext.isAlreadyPropagateContributedFragmentChanges()
					).put(
						"isFragmentServiceConfigurationDefined", fragmentServiceConfigurationDisplayContext.isFragmentServiceConfigurationDefined()
					).put(
						"isPropagateChanges", fragmentServiceConfigurationDisplayContext.isPropagateChanges()
					).put(
						"isPropagateContributedFragmentChanges", fragmentServiceConfigurationDisplayContext.isPropagateContributedFragmentChanges()
					).put(
						"namespace", liferayPortletResponse.getNamespace()
					).put(
						"propagateContributedFragmentEntryChangesURL", fragmentServiceConfigurationDisplayContext.getPropagateContributedFragmentEntryChangesURL()
					).build()
				%>'
			/>
		</clay:sheet-section>

		<clay:sheet-footer>
			<aui:button-row>
				<c:choose>
					<c:when test="<%= fragmentServiceConfigurationDisplayContext.isFragmentServiceConfigurationDefined() %>">
						<aui:button primary="<%= true %>" type="submit" value="update" />
					</c:when>
					<c:otherwise>
						<aui:button primary="<%= true %>" type="submit" value="save" />
					</c:otherwise>
				</c:choose>

				<aui:button href="<%= redirect %>" name="cancel" type="cancel" />
			</aui:button-row>
		</clay:sheet-footer>
	</clay:sheet>
</aui:form>