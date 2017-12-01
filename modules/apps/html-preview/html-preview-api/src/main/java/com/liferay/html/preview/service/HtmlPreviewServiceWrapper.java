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

package com.liferay.html.preview.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HtmlPreviewService}.
 *
 * @author Brian Wing Shun Chan
 * @see HtmlPreviewService
 * @generated
 */
@ProviderType
public class HtmlPreviewServiceWrapper implements HtmlPreviewService,
	ServiceWrapper<HtmlPreviewService> {
	public HtmlPreviewServiceWrapper(HtmlPreviewService htmlPreviewService) {
		_htmlPreviewService = htmlPreviewService;
	}

	@Override
	public com.liferay.html.preview.model.HtmlPreview generateHtmlPreview(
		long userId, long groupId, long classNameId, long classPK,
		java.lang.String content, java.lang.String mimeType,
		boolean asynchronous,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _htmlPreviewService.generateHtmlPreview(userId, groupId,
			classNameId, classPK, content, mimeType, asynchronous,
			serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _htmlPreviewService.getOSGiServiceIdentifier();
	}

	@Override
	public HtmlPreviewService getWrappedService() {
		return _htmlPreviewService;
	}

	@Override
	public void setWrappedService(HtmlPreviewService htmlPreviewService) {
		_htmlPreviewService = htmlPreviewService;
	}

	private HtmlPreviewService _htmlPreviewService;
}