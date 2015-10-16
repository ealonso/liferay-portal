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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

BackgroundTask backgroundTask = (BackgroundTask)row.getObject();

User backgroundTaskUser = UserLocalServiceUtil.getUser(backgroundTask.getUserId());
%>

<li class="list-group-item">
	<div class="list-group-item-field">
		<label class="checkbox-default">
			<input type="checkbox" />
		</label>
	</div>

	<div class="list-group-item-field">
		<liferay-ui:user-display
			displayStyle="3"
			showUserDetails="<%= false %>"
			showUserName="<%= false %>"
			userId="<%= backgroundTaskUser.getUserId() %>"
		/>
	</div>

	<div class="list-group-item-content">
		<small>
			<liferay-ui:message arguments="<%= new Object[] {backgroundTaskUser.getFullName(), LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - backgroundTask.getCreateDate().getTime(), true)} %>" key="x,-created-x-ago" />
		</small>
		<h5>
			<liferay-ui:message key="<%= backgroundTask.getName() %>" />
		</h5>

		<c:if test="<%= backgroundTask.isInProgress() %>">

			<%
			BackgroundTaskStatus backgroundTaskStatus = BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(backgroundTask.getBackgroundTaskId());
			%>

			<c:if test="<%= backgroundTaskStatus != null %>">

				<%
				Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

				String cmd = (String)taskContextMap.get(Constants.CMD);

				int percentage = 100;

				long allModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("allModelAdditionCountersTotal"));
				long allPortletAdditionCounter = GetterUtil.getLong(backgroundTaskStatus.getAttribute("allPortletAdditionCounter"));
				long currentModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("currentModelAdditionCountersTotal"));
				long currentPortletAdditionCounter = GetterUtil.getLong(backgroundTaskStatus.getAttribute("currentPortletAdditionCounter"));

				long allProgressBarCountersTotal = allModelAdditionCountersTotal + allPortletAdditionCounter;
				long currentProgressBarCountersTotal = currentModelAdditionCountersTotal + currentPortletAdditionCounter;

				if (allProgressBarCountersTotal > 0) {
					int base = 100;

					String phase = GetterUtil.getString(backgroundTaskStatus.getAttribute("phase"));

					if (phase.equals(Constants.EXPORT) && !Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE)) {
						base = 50;
					}

					percentage = Math.round((float)currentProgressBarCountersTotal / allProgressBarCountersTotal * base);
				}
				%>

				<div class="row">
					<div class="col-sm-12">
						<div class="progress">
							<div aria-valuemax="100" aria-valuemin="0" aria-valuenow="<%= percentage %>>" class="progress-bar" role="progressbar" style="width: <%= percentage %>%;">
								<c:if test="<%= (allProgressBarCountersTotal > 0) && (!Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE) || (percentage < 100)) %>">
									<%= percentage + StringPool.PERCENT %>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<small>
					<strong class="background-task-status-<%= BackgroundTaskConstants.getStatusLabel(backgroundTask.getStatus()) %> <%= BackgroundTaskConstants.getStatusCssClass(backgroundTask.getStatus()) %> label">
						<liferay-ui:message key="<%= backgroundTask.getStatusLabel() %>" />
					</strong>
				</small>
			</c:if>
		</c:if>
	</div>

	<div class="list-group-item-field">
		<div class="dropdown">
			<a class="dropdown-toggle icon-ellipsis-vertical icon-lg icon-monospaced" data-toggle="dropdown" href="#1"></a>

			<ul class="dropdown-menu dropdown-menu-left-side">
				<li>
					<c:if test="<%= backgroundTask.getGroupId() != liveGroupId %>">
						<portlet:actionURL name="editPublishConfiguration" var="relaunchURL">
							<portlet:param name="mvcRenderCommandName" value="editPublishConfiguration" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RELAUNCH %>" />
							<portlet:param name="redirect" value="<%= currentURL.toString() %>" />
							<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
							<portlet:param name="quickPublish" value="<%= String.valueOf(false) %>" />
						</portlet:actionURL>

						<a href="<%= relaunchURL %>">
							<liferay-ui:message key="relaunch" />
						</a>
					</c:if>
				</li>
				<li>
					<portlet:actionURL name="deleteBackgroundTask" var="deleteBackgroundTaskURL">
						<portlet:param name="redirect" value="<%= currentURL.toString() %>" />
						<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
						<portlet:param name="quickPublish" value="<%= String.valueOf(false) %>" />
					</portlet:actionURL>

					<%
					Date completionDate = backgroundTask.getCompletionDate();
					%>

					<a href="<%= deleteBackgroundTaskURL %>">
						<liferay-ui:message key="<%= ((completionDate != null) && completionDate.before(new Date())) ? "clear" : "cancel" %>" />
					</a>
				</li>
			</ul>
		</div>
	</div>
</li>