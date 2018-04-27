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

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryService;
import com.liferay.html.preview.exception.InvalidHtmlPreviewEntryMimeTypeException;
import com.liferay.html.preview.processor.HtmlPreviewProcessor;
import com.liferay.html.preview.processor.HtmlPreviewProcessorTracker;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/edit_fragment_entry"
	},
	service = MVCActionCommand.class
)
public class EditFragmentEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long fragmentEntryId = ParamUtil.getLong(
			actionRequest, "fragmentEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String css = ParamUtil.getString(actionRequest, "cssContent");
		String js = ParamUtil.getString(actionRequest, "jsContent");
		String html = ParamUtil.getString(actionRequest, "htmlContent");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		try {
			FragmentEntry fragmentEntry =
				_fragmentEntryService.updateFragmentEntry(
					fragmentEntryId, name, css, html, js, status);

			File file = _previewImage(
				_getHTMLPreviewURL(
					actionRequest, fragmentEntry.getFragmentEntryId()));

			fragmentEntry = _fragmentEntryService.updateFragmentEntry(
				fragmentEntryId, file, serviceContext);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (status == WorkflowConstants.ACTION_SAVE_DRAFT) {
				redirect = _getSaveAndContinueRedirect(
					actionRequest, fragmentEntry);
			}

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (FragmentEntryContentException fece) {
			hideDefaultErrorMessage(actionRequest);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/fragment/edit_fragment_entry");
			actionResponse.setRenderParameter(
				"fragmentEntryId", String.valueOf(fragmentEntryId));
			actionResponse.setRenderParameter("cssContent", css);
			actionResponse.setRenderParameter("jsContent", js);
			actionResponse.setRenderParameter("htmlContent", html);

			SessionErrors.add(actionRequest, fece.getClass(), fece);
		}
	}

	private String _getHTMLPreviewURL(
			ActionRequest actionRequest, long fragmentEntryId)
		throws WindowStateException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		long plid = _getRenderLayoutPlid(actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, FragmentPortletKeys.FRAGMENT, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/fragment/render_fragment_entry");
		portletURL.setParameter(
			"fragmentEntryId", String.valueOf(fragmentEntryId));

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	private long _getRenderLayoutPlid(ActionRequest actionRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout renderLayout = LayoutLocalServiceUtil.fetchFirstLayout(
			themeDisplay.getScopeGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (renderLayout != null) {
			return renderLayout.getPlid();
		}

		renderLayout = LayoutLocalServiceUtil.fetchFirstLayout(
			themeDisplay.getScopeGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (renderLayout != null) {
			return renderLayout.getPlid();
		}

		return themeDisplay.getPlid();
	}

	private String _getSaveAndContinueRedirect(
			ActionRequest actionRequest, FragmentEntry fragmentEntry)
		throws Exception {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, FragmentPortletKeys.FRAGMENT,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/fragment/edit_fragment_entry");
		portletURL.setParameter(
			"fragmentCollectionId",
			String.valueOf(fragmentEntry.getFragmentCollectionId()));
		portletURL.setParameter(
			"fragmentEntryId",
			String.valueOf(fragmentEntry.getFragmentEntryId()));

		return portletURL.toString();
	}

	private File _previewImage(String url) {
		String chromeDriverPath = "/your/chromedriver/path" ;

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		ChromeOptions options = new ChromeOptions();

		options.addArguments(
			"--headless", "--disable-gpu", "--window-size=1920,1200",
			"--ignore-certificate-errors", "--silent");

		WebDriver driver = new ChromeDriver(options);

		driver.get(url);

		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;

		return takesScreenshot.getScreenshotAs(OutputType.FILE);
	}

	@Reference
	private FragmentEntryService _fragmentEntryService;

}