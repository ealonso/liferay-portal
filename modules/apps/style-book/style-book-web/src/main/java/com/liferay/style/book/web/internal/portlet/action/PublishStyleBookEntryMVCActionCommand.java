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

package com.liferay.style.book.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.style.book.service.StyleBookEntryService;
import com.liferay.style.book.web.internal.constants.StyleBookPortletKeys;
import com.liferay.style.book.web.internal.handler.StyleBookEntryExceptionRequestHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StyleBookPortletKeys.STYLE_BOOK,
		"mvc.command.name=/style_book/publish_style_book_entry"
	},
	service = MVCActionCommand.class
)
public class PublishStyleBookEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long styleBookEntryId = ParamUtil.getLong(
			actionRequest, "styleBookEntryId");

		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		try {
			_styleBookEntryService.updateStyleBookEntry(
				styleBookEntryId, StringUtil.randomString());

			if (workflowAction == WorkflowConstants.ACTION_PUBLISH) {
				_styleBookEntryService.publishDraft(styleBookEntryId);
			}
		}
		catch (PortalException portalException) {
			hideDefaultErrorMessage(actionRequest);

			_styleBookEntryExceptionRequestHandler.handlePortalException(
				actionRequest, actionResponse, portalException);
		}
	}

	@Reference
	private StyleBookEntryExceptionRequestHandler
		_styleBookEntryExceptionRequestHandler;

	@Reference
	private StyleBookEntryService _styleBookEntryService;

}