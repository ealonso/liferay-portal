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

package com.liferay.portlet.documentselector.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentselector.DocumentSelector;
import com.liferay.portlet.documentselector.impl.AudioDocumentSelector;
import com.liferay.portlet.documentselector.impl.BaseDocumentSelector;
import com.liferay.portlet.documentselector.impl.ImageDocumentSelector;
import com.liferay.portlet.documentselector.impl.VideoDocumentSelector;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Eudaldo Alonso
 */
public class ViewAction extends PortletAction {

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);

			HttpServletRequest originalServletRequest =
				PortalUtil.getOriginalServletRequest(request);

			String type = ParamUtil.getString(originalServletRequest, "Type");

			DocumentSelector documentSelector = null;

			if (StringUtil.equalsIgnoreCase(type, "image")) {
				documentSelector = new ImageDocumentSelector();
			}
			else if (StringUtil.equalsIgnoreCase(type, "audio")) {
				documentSelector = new AudioDocumentSelector();
			}
			else if (StringUtil.equalsIgnoreCase(type, "video")) {
				documentSelector = new VideoDocumentSelector();
			}
			else {
				documentSelector = new BaseDocumentSelector();
			}

			if (Validator.isNotNull(type)) {
				request.setAttribute("defaultTypes", "documents");
			}

			request.setAttribute("DOCUMENT_SELECTOR", documentSelector);
		}
		catch (Exception e) {
			if (e instanceof NoSuchFolderException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return actionMapping.findForward(
					"portlet.document_selector.error");
			}
			else {
				throw e;
			}
		}

		return actionMapping.findForward("portlet.document_selector.view");
	}

}