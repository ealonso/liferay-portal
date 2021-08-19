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

package com.liferay.fragment.renderer.collection.filter.internal;

import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.renderer.collection.filter.internal.configuration.FFFragmentRendererCollectionFilterConfiguration;
import com.liferay.frontend.taglib.servlet.taglib.ComponentTag;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.taglib.servlet.PageContextFactoryUtil;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pablo Molina
 */
@Component(
	configurationPid = "com.liferay.fragment.renderer.collection.filter.internal.configuration.FFFragmentRendererCollectionFilterConfiguration",
	service = FragmentRenderer.class
)
public class CollectionAppliedFiltersFragmentRenderer
	implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "content-display";
	}

	@Override
	public String getIcon() {
		return "filter";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		return LanguageUtil.get(resourceBundle, "applied-filters");
	}

	@Override
	public boolean isSelectable(HttpServletRequest httpServletRequest) {
		if (!_ffFragmentRendererCollectionFilterConfiguration.enabled()) {
			return false;
		}

		return true;
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		PageContext pageContext = PageContextFactoryUtil.create(
			httpServletRequest, httpServletResponse);

		JspWriter jspWriter = pageContext.getOut();

		Map<String, String[]> parameters = httpServletRequest.getParameterMap();

		try {
			for (String parameterName : parameters.keySet()) {
				if (parameterName.startsWith(
						_COLLECTION_FILTER_PARAMETER_PREFIX)) {

					String[] parameterData = parameterName.split("_");

					if (parameterData.length != 3) {
						continue;
					}

					String filterFragmentEntryLinkId = parameterData[2];
					String filterType = parameterData[1];

					for (String filterValue : parameters.get(parameterName)) {
						jspWriter.write("<div data-filter-type=\"");
						jspWriter.write(filterType);
						jspWriter.write(
							"\" data-filter-fragment-entry-link-id=\"");
						jspWriter.write(filterFragmentEntryLinkId);
						jspWriter.write("\" data-filter-value");
						jspWriter.write(filterValue);
						jspWriter.write("\">");
						jspWriter.write(filterValue);
						jspWriter.write("</div>");
					}
				}
			}

			ComponentTag componentTag = new ComponentTag();

			componentTag.setModule("js/CollectionAppliedFilters");

			componentTag.doTag(pageContext);
		}
		catch (Exception exception) {
			ReflectionUtil.throwException(exception);
		}
	}

	@Modified
	protected void activate(Map<String, Object> properties) {
		_ffFragmentRendererCollectionFilterConfiguration =
			ConfigurableUtil.createConfigurable(
				FFFragmentRendererCollectionFilterConfiguration.class,
				properties);
	}

	private static final String _COLLECTION_FILTER_PARAMETER_PREFIX = "filter_";

	private volatile FFFragmentRendererCollectionFilterConfiguration
		_ffFragmentRendererCollectionFilterConfiguration;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.fragment.renderer.collection.filter.impl)"
	)
	private ServletContext _servletContext;

}