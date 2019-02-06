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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolvedPackageNameUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.servlet.taglib.aui.ScriptData;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.ParamAndPropertyAncestorTagImpl;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Chema Balsas
 */
public class ComponentTag extends ParamAndPropertyAncestorTagImpl {

	@Override
	public int doEndTag() throws JspException {
		try {
			_renderJavaScript();
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			cleanUp();
		}

		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public String getComponentId() {
		return _componentId;
	}

	public String getModule() {
		String namespace;

		if (_setServletContext) {
			namespace = NPMResolvedPackageNameUtil.get(servletContext);
		}
		else {
			HttpServletRequest httpServletRequest =
				(HttpServletRequest)pageContext.getRequest();

			namespace = NPMResolvedPackageNameUtil.get(httpServletRequest);
		}

		return namespace + "/" + _module;
	}

	@Override
	public void release() {
		super.release();

		_setServletContext = false;
	}

	public void setComponentId(String componentId) {
		_componentId = componentId;
	}

	public void setContext(Map<String, Object> context) {
		_context = context;
	}

	public void setModule(String module) {
		_module = module;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);

		_setServletContext = true;
	}

	protected void cleanUp() {
		if (!ServerDetector.isResin()) {
			_componentId = null;
			_context = null;
			_module = null;
		}
	}

	protected Map<String, Object> getContext() {
		return _context;
	}

	protected boolean isPositionInline() {
		Boolean positionInline = null;

		String fragmentId = ParamUtil.getString(request, "p_f_id");

		if (Validator.isNotNull(fragmentId)) {
			positionInline = true;
		}

		if (positionInline == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			if (themeDisplay.isIsolated() ||
				themeDisplay.isLifecycleResource() ||
				themeDisplay.isStateExclusive()) {

				positionInline = true;
			}

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String portletId = portletDisplay.getId();

			if (Validator.isNotNull(portletId) &&
				themeDisplay.isPortletEmbedded(
					themeDisplay.getScopeGroupId(), themeDisplay.getLayout(),
					portletId)) {

				positionInline = true;
			}
		}

		if (positionInline == null) {
			positionInline = false;
		}

		return positionInline;
	}

	private static String _getModuleName(String module) {
		String moduleName = StringUtil.extractLast(
			module, CharPool.FORWARD_SLASH);

		return StringUtil.strip(moduleName, _UNSAFE_MODULE_NAME_CHARS);
	}

	private void _renderJavaScript() throws IOException {
		JspWriter jspWriter = pageContext.getOut();

		String module = getModule();

		String moduleName = _getModuleName(module);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletId = portletDisplay.getId();

		Map<String, Object> context = getContext();

		if (context == null) {
			context = new HashMap();
		}

		context.put("namespace", portletDisplay.getNamespace());
		context.put(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");

		StringBundler componentJavaScriptSB = new StringBundler(12);

		componentJavaScriptSB.append("Liferay.component('");
		componentJavaScriptSB.append(getComponentId());
		componentJavaScriptSB.append("', new ");
		componentJavaScriptSB.append(moduleName);
		componentJavaScriptSB.append(".default(");
		componentJavaScriptSB.append(_jsonSerializer.serializeDeep(context));
		componentJavaScriptSB.append("), { portletId: '");
		componentJavaScriptSB.append(portletId);
		componentJavaScriptSB.append("'});");

		String componentJavaScript = componentJavaScriptSB.toString();

		StringBundler moduleDeclarationSB = new StringBundler(3);

		moduleDeclarationSB.append(module);
		moduleDeclarationSB.append(" as ");
		moduleDeclarationSB.append(moduleName);

		String moduleDeclaration = moduleDeclarationSB.toString();

		if (isPositionInline()) {
			ScriptData scriptData = new ScriptData();

			scriptData.append(
				PortalUtil.getPortletId(request), componentJavaScript,
				moduleDeclaration, ScriptData.ModulesType.ES6);

			scriptData.writeTo(jspWriter);
		}
		else {
			ScriptData scriptData = (ScriptData)request.getAttribute(
				WebKeys.AUI_SCRIPT_DATA);

			if (scriptData == null) {
				scriptData = new ScriptData();

				request.setAttribute(WebKeys.AUI_SCRIPT_DATA, scriptData);
			}

			scriptData.append(
				PortalUtil.getPortletId(request), componentJavaScript,
				moduleDeclaration, ScriptData.ModulesType.ES6);
		}
	}

	private static final char[] _UNSAFE_MODULE_NAME_CHARS = {
		CharPool.PERIOD, CharPool.DASH
	};

	private String _componentId;
	private Map<String, Object> _context;
	private final JSONSerializer _jsonSerializer =
		JSONFactoryUtil.createJSONSerializer();
	private String _module;
	private boolean _setServletContext;

}