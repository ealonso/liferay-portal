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

package com.liferay.fragment.renderer.external.video.internal;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.video.external.shortcut.DLVideoExternalShortcut;
import com.liferay.document.library.video.external.shortcut.resolver.DLVideoExternalShortcutResolver;
import com.liferay.document.library.video.renderer.DLVideoRenderer;
import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pablo Molina
 */
@Component(enabled = true, service = FragmentRenderer.class)
public class ExternalVideoFragmentRenderer implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "basic-components";
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				StringUtil.read(
					getClass(),
					"/com/liferay/fragment/renderer/external/video/internal" +
						"/dependencies/configuration.json"));

			return _fragmentEntryConfigurationParser.translateConfiguration(
				jsonObject, resourceBundle);
		}
		catch (JSONException jsonException) {
			return StringPool.BLANK;
		}
	}

	@Override
	public String getIcon() {
		return "video";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		return LanguageUtil.get(resourceBundle, "external-video");
	}

	@Override
	public void render(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		FragmentEntryLink fragmentEntryLink =
			fragmentRendererContext.getFragmentEntryLink();

		String fragmentId = _getFragmentId(fragmentEntryLink);

		PrintWriter printWriter = httpServletResponse.getWriter();

		printWriter.write("<div id=\"" + fragmentId + "\">");

		String styles = StringUtil.replace(
			StringUtil.read(
				getClass(),
				"/com/liferay/fragment/renderer/external/video/internal" +
					"/dependencies/styles.tmpl"),
			"${", "}",
			HashMapBuilder.put(
				"fragmentId", fragmentId
			).build());

		printWriter.write(styles);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		try {
			JSONObject videoJSONObject = JSONFactoryUtil.createJSONObject(
				(String)_fragmentEntryConfigurationParser.getFieldValue(
					fragmentEntryLink.getConfiguration(),
					fragmentEntryLink.getEditableValues(),
					resourceBundle.getLocale(), "video"));

			String videoHTML = "";

			if (videoJSONObject.has("url")) {
				DLVideoExternalShortcut dlVideoExternalShortcut =
					_dlVideoExternalShortcutResolver.resolve(
						videoJSONObject.getString("url"));

				videoHTML = dlVideoExternalShortcut.renderHTML(
					httpServletRequest);
			}
			else if (videoJSONObject.has("fileEntryId")) {
				FileEntry videoFileEntry = _dlAppLocalService.getFileEntry(
					videoJSONObject.getLong("fileEntryId"));

				videoHTML = _dlVideoRenderer.renderHTML(
					videoFileEntry.getFileVersion(), httpServletRequest);
			}

			printWriter.write("<div class=\"video-wrapper");

			if (Objects.equals(
					fragmentRendererContext.getMode(),
					FragmentEntryLinkConstants.EDIT)) {

				printWriter.write(" video-wrapper--edit-mode");
			}

			printWriter.write("\">" + videoHTML + "</div>");
		}
		catch (Exception exception) {
			if (Objects.equals(
					fragmentRendererContext.getMode(),
					FragmentEntryLinkConstants.EDIT)) {

				printWriter.write(
					"<div class=\"alert alert-info error-message mb-0 pb-0\" " +
						"role=\"alert\"><p>");
				printWriter.write(
					ResourceBundleUtil.getString(
						resourceBundle, "please-enter-a-valid-video-url"));
				printWriter.write("</p><p>");
				printWriter.write(
					ResourceBundleUtil.getString(
						resourceBundle, "video-url-help"));
				printWriter.write("</p></div>");
			}
		}

		printWriter.write("</div>");
	}

	private String _getFragmentId(FragmentEntryLink fragmentEntryLink) {
		StringBundler fragmentIdSB = new StringBundler(4);

		fragmentIdSB.append("fragment-");
		fragmentIdSB.append(fragmentEntryLink.getFragmentEntryId());
		fragmentIdSB.append("-");
		fragmentIdSB.append(fragmentEntryLink.getNamespace());

		return fragmentIdSB.toString();
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLVideoExternalShortcutResolver _dlVideoExternalShortcutResolver;

	@Reference
	private DLVideoRenderer _dlVideoRenderer;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

}