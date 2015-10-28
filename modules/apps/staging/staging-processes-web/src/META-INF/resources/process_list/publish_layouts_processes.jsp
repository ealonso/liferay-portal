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
String cmd = ParamUtil.getString(request, Constants.CMD);

if (Validator.isNull(cmd)) {
	cmd = ParamUtil.getString(request, "originalCmd", Constants.PUBLISH_TO_LIVE);
}

String closeRedirect = ParamUtil.getString(request, "closeRedirect");
long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");
String layoutSetBranchName = ParamUtil.getString(request, "layoutSetBranchName");
boolean localPublishing = true;

if ((liveGroup.isStaged() && liveGroup.isStagedRemotely()) || cmd.equals(Constants.PUBLISH_TO_REMOTE)) {
	localPublishing = false;
}

boolean quickPublish = ParamUtil.getBoolean(request, "quickPublish");

PortletURL renderURL = liferayPortletResponse.createRenderURL();

renderURL.setParameter("mvcRenderCommandName", "publishLayouts");
renderURL.setParameter("tabs2", "current-and-previous");
renderURL.setParameter("closeRedirect", closeRedirect);
renderURL.setParameter("groupId", String.valueOf(groupId));
renderURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranchId));
renderURL.setParameter("layoutSetBranchName", layoutSetBranchName);
renderURL.setParameter("localPublishing", String.valueOf(localPublishing));
renderURL.setParameter("quickPublish", String.valueOf(quickPublish));

String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");

if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
	portalPreferences.setValue(PortletKeys.BACKGROUND_TASK, "entries-order-by-col", orderByCol);
	portalPreferences.setValue(PortletKeys.BACKGROUND_TASK, "entries-order-by-type", orderByType);
}
else {
	orderByCol = portalPreferences.getValue(PortletKeys.BACKGROUND_TASK, "entries-order-by-col", "create-date");
	orderByType = portalPreferences.getValue(PortletKeys.BACKGROUND_TASK, "entries-order-by-type", "desc");
}

String taskExecutorClassName = localPublishing ? BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR : BackgroundTaskExecutorNames.LAYOUT_REMOTE_STAGING_BACKGROUND_TASK_EXECUTOR;
%>

<div class="process-list" id="<portlet:namespace />publishProcessesSearchContainer">
	<ul class="tabular-list-group">
		<liferay-ui:search-container
			emptyResultsMessage="no-publication-processes-were-found"
			iteratorURL="<%= renderURL %>"
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
		>

			<liferay-ui:search-container-results>

				<%
				List<BackgroundTask> backgroundTasks = BackgroundTaskManagerUtil.getBackgroundTasks(groupId, taskExecutorClassName);

				results.addAll(backgroundTasks);

				if (localPublishing) {
					results.addAll(BackgroundTaskManagerUtil.getBackgroundTasks(liveGroupId, taskExecutorClassName));
				}

				searchContainer.setTotal(results.size());

				results = ListUtil.subList(results, searchContainer.getStart(), searchContainer.getEnd());

				searchContainer.setResults(results);
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.backgroundtask.BackgroundTask"
				cssClass="list-group-item"
				keyProperty="backgroundTaskId"
				modelVar="backgroundTask"
			>

				<liferay-ui:search-container-column-jsp
					path="/process_list/process_entry.jsp"
				/>
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />
		</liferay-ui:search-container>
	</ul>
</div>

<%
	int incompleteBackgroundTaskCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(groupId, taskExecutorClassName, false);

	if (localPublishing) {
		incompleteBackgroundTaskCount += BackgroundTaskManagerUtil.getBackgroundTasksCount(liveGroupId, taskExecutorClassName, false);
	}
%>

<div class="hide incomplete-process-message">
	<liferay-util:include page="/process_list/incomplete_processes_message.jsp" servletContext="<%= application %>">
		<liferay-util:param name="incompleteBackgroundTaskCount" value="<%= String.valueOf(incompleteBackgroundTaskCount) %>" />
	</liferay-util:include>
</div>