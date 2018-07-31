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

package com.liferay.journal.internal.upgrade.v1_1_3;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jürgen Kappler
 */
public class UpgradeImageContent extends UpgradeProcess {

	public UpgradeImageContent() {
	}

	protected String convertTypeImageElements(
			long groupId, String content, long resourcePrimKey)
		throws Exception {

		Document contentDocument = SAXReaderUtil.read(content);

		contentDocument = contentDocument.clone();

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='image']");

		List<Node> imageNodes = xPath.selectNodes(contentDocument);

		for (Node imageNode : imageNodes) {
			Element imageEl = (Element)imageNode;

			List<Element> dynamicContentEls = imageEl.elements(
				"dynamic-content");

			for (Element dynamicContentEl : dynamicContentEls) {
				Matcher matcher = _documentsPattern.matcher(
					dynamicContentEl.getText());

				if (!matcher.find()) {
					continue;
				}

				long fileEntryId = GetterUtil.getLong(
					dynamicContentEl.attributeValue("fileEntryId"));

				long fileEntryGroupId = Long.valueOf(matcher.group(1));

				String uuid = StringPool.BLANK;

				matcher = _uuidPattern.matcher(dynamicContentEl.getText());

				if (matcher.find()) {
					uuid = matcher.group(1);
				}

				if ((fileEntryId == 0) && Validator.isNull(uuid)) {
					continue;
				}

				FileEntry fileEntry = null;

				try {
					if (fileEntryId > 0) {
						fileEntry =
							PortletFileRepositoryUtil.getPortletFileEntry(
								fileEntryId);
					}
					else {
						fileEntry =
							PortletFileRepositoryUtil.getPortletFileEntry(
								uuid, fileEntryGroupId);
					}
				}
				catch (PortalException pe) {
					_log.error("Unable to get file entry", pe);
				}

				if (fileEntry == null) {
					continue;
				}

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("alt", StringPool.BLANK);
				jsonObject.put("groupId", fileEntry.getGroupId());
				jsonObject.put("name", fileEntry.getFileName());
				jsonObject.put("resourcePrimKey", resourcePrimKey);
				jsonObject.put("title", fileEntry.getTitle());
				jsonObject.put("type", "journal");
				jsonObject.put("uuid", fileEntry.getUuid());

				dynamicContentEl.clearContent();

				dynamicContentEl.addCDATA(jsonObject.toString());

				if (fileEntryId == 0) {
					dynamicContentEl.addAttribute(
						"fileEntryId",
						String.valueOf(fileEntry.getFileEntryId()));
				}
			}
		}

		return contentDocument.formattedString();
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateContentImages();
	}

	protected void updateContentImages() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select content, groupId, id_, resourcePrimKey, userId from " +
					"JournalArticle where content like ?")) {

			ps1.setString(1, "%type=\"image\"%");

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {
				String content = rs1.getString(1);
				long groupId = rs1.getLong(2);
				long id = rs1.getLong(3);
				long resourcePrimKey = rs1.getLong(4);

				String newContent = convertTypeImageElements(
					groupId, content, resourcePrimKey);

				try (PreparedStatement ps2 =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update JournalArticle set content = ? where id_ " +
								"= ?")) {

					ps2.setString(1, newContent);
					ps2.setLong(2, id);

					ps2.executeUpdate();
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeImageContent.class);

	private static final Pattern _documentsPattern = Pattern.compile(
		"/documents/(\\d+)/(\\d+)/([^/?]+)(?:/([-0-9a-fA-F]+))?(?:\\?t=\\d+)" +
			"\\s*");
	private static final Pattern _uuidPattern = Pattern.compile(
		"([a-fA-F0-9]{8}-(?:[a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}){1}");

}