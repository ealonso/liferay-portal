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

package com.liferay.template.web.internal.display.context;

import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class InformationTemplatesTemplateDisplayContext
	extends TemplateDisplayContext {

	public InformationTemplatesTemplateDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		super(liferayPortletRequest, liferayPortletResponse);
	}

	@Override
	protected long[] getClassNameIds() {
		InfoItemServiceTracker infoItemServiceTracker =
			(InfoItemServiceTracker)liferayPortletRequest.getAttribute(
				InfoItemServiceTracker.class.getName());

		List<String> infoItemClassNames =
			infoItemServiceTracker.getInfoItemClassNames(
				InfoItemFormProvider.class);

		long[] classNameIds = new long[infoItemClassNames.size()];

		for (int i = 0; i < infoItemClassNames.size(); i++) {
			classNameIds[i] = PortalUtil.getClassNameId(
				infoItemClassNames.get(i));
		}

		return classNameIds;
	}

	@Override
	protected long getResourceClassNameId() {
		return PortalUtil.getClassNameId(InfoItemFormProvider.class);
	}

}