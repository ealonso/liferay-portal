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

package com.liferay.html.preview.processor.impl;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.html.preview.processor.HtmlPreviewProcessor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TempFileEntryUtil;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, property = {"preview.mime.type=image/png"},
	service = HtmlPreviewProcessor.class
)
public class ImageHtmlPreviewProcessor implements HtmlPreviewProcessor {

	@Override
	public String generateHtmlPreviewURL(
			String content, ThemeDisplay themeDisplay)
		throws PortalException {

		File fragmentFile = FileUtil.createTempFile();

		String previewUrl = StringPool.BLANK;

		try {
			FileUtil.write(fragmentFile, content);

			Java2DRenderer renderer = new Java2DRenderer(fragmentFile, 1024);

			renderer.setBufferedImageType(BufferedImage.TYPE_INT_RGB);

			BufferedImage image = renderer.getImage();

			FSImageWriter imageWriter = new FSImageWriter();

			File outputFile = FileUtil.createTempFile("png");

			imageWriter.write(image, outputFile.getAbsolutePath());

			FileEntry fileEntry = TempFileEntryUtil.addTempFileEntry(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				_TEMP_FOLDER_NAME, outputFile.getName(), outputFile,
				ContentTypes.IMAGE_PNG);

			previewUrl = DLUtil.getImagePreviewURL(fileEntry, themeDisplay);
		}
		catch (Exception e) {
			_log.error("Unable to generate a preview image", e);

			throw new PortalException(e);
		}

		return previewUrl;
	}

	@Override
	public String generateHtmlPreviewURL(URL url, ThemeDisplay themeDisplay)
		throws PortalException {

		try {
			String content = _http.URLtoString(url);

			return generateHtmlPreviewURL(content, themeDisplay);
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
	}

	private static final String _TEMP_FOLDER_NAME =
		ImageHtmlPreviewProcessor.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(
		ImageHtmlPreviewProcessor.class);

	@Reference
	private Http _http;

}