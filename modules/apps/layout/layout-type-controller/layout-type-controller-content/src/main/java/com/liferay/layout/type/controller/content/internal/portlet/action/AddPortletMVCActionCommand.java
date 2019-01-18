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

package com.liferay.layout.type.controller.content.internal.portlet.action;

import com.liferay.fragment.exception.NoSuchEntryException;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.fragment.util.FragmentEntryRenderUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.type.controller.content.internal.constants.ContentLayoutPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.jsoup.nodes.Element;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentLayoutPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/add_portlet"
	},
	service = MVCActionCommand.class
)
public class AddPortletMVCActionCommand extends BaseMVCActionCommand {

	protected FragmentEntryLink addFragmentEntryLink(
			ActionRequest actionRequest)
		throws PortalException {

		String portletId = ParamUtil.getString(actionRequest, "portletId");

		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		FragmentEntryLink fragmentEntryLink =
			_fragmentEntryLinkLocalService.addFragmentEntryLink(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(), 0,
				classNameId, classPK, StringPool.BLANK,
				_getPortletFragmentEntryLinkHTML(portletId), StringPool.BLANK,
				null, 0, serviceContext);

		return fragmentEntryLink;
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Callable<FragmentEntryLink> callable = new AddFragmentEntryLinkCallable(
			actionRequest);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String portletId = ParamUtil.getString(actionRequest, "portletId");

		try {
			FragmentEntryLink fragmentEntryLink = TransactionInvokerUtil.invoke(
				_transactionConfig, callable);

			jsonObject.put(
				"content",
				FragmentEntryRenderUtil.renderFragmentEntryLink(
					fragmentEntryLink,
					_portal.getHttpServletRequest(actionRequest),
					_portal.getHttpServletResponse(actionResponse)));
			jsonObject.put(
				"editableValues", fragmentEntryLink.getEditableValues());
			jsonObject.put(
				"fragmentEntryLinkId",
				fragmentEntryLink.getFragmentEntryLinkId());

			jsonObject.put(
				"name",
				_portal.getPortletTitle(portletId, themeDisplay.getLocale()));

			SessionMessages.add(actionRequest, "fragmentEntryLinkAdded");
		}
		catch (Throwable t) {
			_log.error(t, t);

			String errorMessage = "an-unexpected-error-occurred";

			if (t.getCause() instanceof NoSuchEntryException) {
				errorMessage =
					"the-fragment-can-no-longer-be-added-because-it-has-been-" +
						"deleted";
			}

			jsonObject.put(
				"error",
				LanguageUtil.get(themeDisplay.getRequest(), errorMessage));
		}

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private String _getPortletFragmentEntryLinkHTML(String portletId) {
		Element runtimeTagElement = new Element(
			"@liferay_portlet.runtime", true);

		Portlet portlet = _portletLocalService.getPortletById(portletId);

		runtimeTagElement.attr(
			"defaultPreferences", portlet.getDefaultPreferences());

		if (portlet.isInstanceable()) {
			runtimeTagElement.attr(
				"instanceId", PortletIdCodec.generateInstanceId());
		}

		runtimeTagElement.attr("persistSettings=false", true);
		runtimeTagElement.attr("portletName", portletId);

		return runtimeTagElement.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddPortletMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	private class AddFragmentEntryLinkCallable
		implements Callable<FragmentEntryLink> {

		@Override
		public FragmentEntryLink call() throws Exception {
			return addFragmentEntryLink(_actionRequest);
		}

		private AddFragmentEntryLinkCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}