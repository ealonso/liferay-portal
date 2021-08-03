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

package com.liferay.layout.taglib.internal.struts;

import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.layout.taglib.servlet.taglib.RenderFragmentLayoutTag;
import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.struts.StrutsAction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "path=/portal/layout/render_collection",
	service = StrutsAction.class
)
public class RenderCollectionStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		httpServletRequest.setAttribute(
			FragmentActionKeys.FRAGMENT_RENDERER_CONTROLLER,
			_fragmentRendererController);

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		try {
			RenderFragmentLayoutTag renderFragmentLayoutTag =
				new RenderFragmentLayoutTag();

			/*
			renderFragmentLayoutTag.setFieldValues(fieldValues);
			renderFragmentLayoutTag.setGroupId(groupId);
			renderFragmentLayoutTag.setMainItemId(mainItemId);
			renderFragmentLayoutTag.setMode(mode);
			renderFragmentLayoutTag.setPlid(plid);
			 */
			renderFragmentLayoutTag.setShowPreview(false);

			renderFragmentLayoutTag.doTag(
				httpServletRequest, pipingServletResponse);
		}
		catch (Exception exception) {
			throw new FragmentEntryContentException(exception);
		}

		return unsyncStringWriter.toString();
	}

	@Reference(target = "(osgi.web.symbolicname=com.liferay.layout.taglib)")
	private ServletContext _servletContext;

	@Reference
	private FragmentRendererController _fragmentRendererController;

}