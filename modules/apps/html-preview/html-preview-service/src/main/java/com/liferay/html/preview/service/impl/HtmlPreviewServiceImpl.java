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

package com.liferay.html.preview.service.impl;

import com.liferay.html.preview.model.HtmlPreview;
import com.liferay.html.preview.service.base.HtmlPreviewServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Pavel Savinov
 */
public class HtmlPreviewServiceImpl extends HtmlPreviewServiceBaseImpl {

	@Override
	public HtmlPreview generateHtmlPreview(
			long userId, long groupId, long classNameId, long classPK,
			String content, String mimeType, boolean asynchronous,
			ServiceContext serviceContext)
		throws PortalException {

		return htmlPreviewLocalService.generateHtmlPreview(
			userId, groupId, classNameId, classPK, content, mimeType,
			asynchronous, serviceContext);
	}

}