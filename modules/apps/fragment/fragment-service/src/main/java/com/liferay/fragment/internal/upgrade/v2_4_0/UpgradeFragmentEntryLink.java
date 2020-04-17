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

package com.liferay.fragment.internal.upgrade.v2_4_0;

import com.liferay.fragment.internal.upgrade.v2_4_0.util.FragmentEntryLinkTable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeFragmentEntryLink extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("FragmentEntryLink", "segmentsExperienceId")) {
			alter(
				FragmentEntryLinkTable.class,
				new AlterTableAddColumn("segmentsExperienceId", "LONG"));
		}

		_populateSegmentsExperienceId();
	}

	protected String getEditableValues(
		String editableValues, long segmentsExperienceId) {

		JSONObject newEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject();

		try {
			JSONObject editableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(editableValues);

			Iterator<String> keysIterator = editableValuesJSONObject.keys();

			while (keysIterator.hasNext()) {
				String editableProcessorKey = keysIterator.next();

				Object editableProcessorObject = editableValuesJSONObject.get(
					editableProcessorKey);

				if (!(editableProcessorObject instanceof JSONObject)) {
					newEditableValuesJSONObject.put(
						editableProcessorKey, editableProcessorObject);

					continue;
				}

				JSONObject editableProcessorJSONObject =
					(JSONObject)editableProcessorObject;

				if (editableProcessorJSONObject.length() <= 0) {
					newEditableValuesJSONObject.put(
						editableProcessorKey,
						JSONFactoryUtil.createJSONObject());

					continue;
				}

				if (Objects.equals(
						editableProcessorKey,
						_KEY_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR)) {

					newEditableValuesJSONObject.put(
						editableProcessorKey,
						_getFreeMarkerFragmentEntryProcessorJSONObject(
							editableProcessorJSONObject, segmentsExperienceId));

					continue;
				}

				newEditableValuesJSONObject.put(
					editableProcessorKey,
					_getFragmentEntryProcessorJSONObject(
						editableProcessorJSONObject, segmentsExperienceId));
			}
		}
		catch (JSONException jsonException) {
			if (_log.isWarnEnabled()) {
				_log.warn(jsonException, jsonException);
			}
		}

		return newEditableValuesJSONObject.toJSONString();
	}

	protected Long[] getSegmentsExperienceIds(String editableValues) {
		try {
			Set<Long> segmentsExperienceIds = new HashSet<>();

			JSONObject editableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(editableValues);

			Iterator<String> keysIterator = editableValuesJSONObject.keys();

			while (keysIterator.hasNext()) {
				String editableProcessorKey = keysIterator.next();

				JSONObject editableProcessorJSONObject =
					editableValuesJSONObject.getJSONObject(
						editableProcessorKey);

				if (editableProcessorJSONObject == null) {
					continue;
				}

				Iterator<String> editableKeysIterator =
					editableProcessorJSONObject.keys();

				while (editableKeysIterator.hasNext()) {
					String editableKey = editableKeysIterator.next();

					if (editableKey.startsWith(_ID_PREFIX)) {
						segmentsExperienceIds.add(
							GetterUtil.getLong(
								editableKey.substring(_ID_PREFIX.length())));
					}
					else {
						JSONObject editableJSONObject =
							editableProcessorJSONObject.getJSONObject(
								editableKey);

						if (editableJSONObject != null) {
							Iterator<String> valueKeysIterator =
								editableJSONObject.keys();

							while (valueKeysIterator.hasNext()) {
								String valueKey = valueKeysIterator.next();

								if (valueKey.startsWith(_ID_PREFIX)) {
									segmentsExperienceIds.add(
										GetterUtil.getLong(
											valueKey.substring(
												_ID_PREFIX.length())));
								}
							}
						}
					}
				}
			}

			return segmentsExperienceIds.toArray(new Long[0]);
		}
		catch (JSONException jsonException) {
			if (_log.isWarnEnabled()) {
				_log.warn(jsonException, jsonException);
			}
		}

		return new Long[0];
	}

	private void _createFragmentEntryLink(
		long groupId, long companyId, long userId, String userName,
		Timestamp createDate, long originalFragmentEntryLinkId,
		long fragmentEntryId, long segmentsExperienceId, long classNameId,
		long classPK, String css, String html, String js, String configuration,
		String editableValues, String namespace, int position,
		String rendererKey, Timestamp lastPropagationDate,
		Timestamp lastPublishDate) {

		StringBundler sb = new StringBundler(9);

		sb.append("insert into FragmentEntryLink (mvccVersion, uuid_, ");
		sb.append("fragmentEntryLinkId, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate, ");
		sb.append("originalFragmentEntryLinkId, fragmentEntryId, ");
		sb.append("segmentsExperienceId, classNameId, classPK, css, html, js ");
		sb.append("configuration, editableValues, namespace, position, ");
		sb.append("rendererKey, lastPropagationDate, lastPublishDate) values ");
		sb.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?)");

		String sql = sb.toString();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, 0);
			ps.setString(2, PortalUUIDUtil.generate());
			ps.setLong(3, increment());
			ps.setLong(4, groupId);
			ps.setLong(5, companyId);
			ps.setLong(6, userId);
			ps.setString(7, userName);
			ps.setTimestamp(8, createDate);
			ps.setTimestamp(9, createDate);
			ps.setLong(10, originalFragmentEntryLinkId);
			ps.setLong(11, fragmentEntryId);
			ps.setLong(12, segmentsExperienceId);
			ps.setLong(13, classNameId);
			ps.setLong(14, classPK);
			ps.setString(15, css);
			ps.setString(16, html);
			ps.setString(17, js);
			ps.setString(18, configuration);
			ps.setString(
				19, getEditableValues(editableValues, segmentsExperienceId));
			ps.setString(20, namespace);
			ps.setInt(21, position);
			ps.setString(22, rendererKey);
			ps.setTimestamp(23, lastPropagationDate);
			ps.setTimestamp(24, lastPublishDate);

			ps.executeUpdate();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	private JSONObject _getFragmentEntryProcessorJSONObject(
		JSONObject editableProcessorJSONObject, long segmentsExperienceId) {

		JSONObject newEditableProcessorJSONObject =
			JSONFactoryUtil.createJSONObject();

		Iterator<String> editableKeysIterator =
			editableProcessorJSONObject.keys();

		while (editableKeysIterator.hasNext()) {
			String editableKey = editableKeysIterator.next();

			JSONObject editableJSONObject =
				editableProcessorJSONObject.getJSONObject(editableKey);

			if (editableJSONObject == null) {
				newEditableProcessorJSONObject.put(
					editableKey, JSONFactoryUtil.createJSONObject());

				continue;
			}

			JSONObject newEditableJSONObject =
				JSONFactoryUtil.createJSONObject();

			Iterator<String> valueKeysIterator = editableJSONObject.keys();

			while (valueKeysIterator.hasNext()) {
				String valueKey = valueKeysIterator.next();

				if (Objects.equals(
						valueKey, _ID_PREFIX + segmentsExperienceId)) {

					JSONObject valueJSONObject =
						editableJSONObject.getJSONObject(valueKey);

					Iterator<String> segmentedValueKeysIterator =
						valueJSONObject.keys();

					while (segmentedValueKeysIterator.hasNext()) {
						String segmentedValueKey =
							segmentedValueKeysIterator.next();

						newEditableJSONObject.put(
							segmentedValueKey,
							valueJSONObject.get(segmentedValueKey));
					}
				}
				else if (!valueKey.startsWith(_ID_PREFIX)) {
					newEditableJSONObject.put(
						valueKey, editableJSONObject.get(valueKey));
				}
			}

			newEditableProcessorJSONObject.put(
				editableKey, newEditableJSONObject);
		}

		return newEditableProcessorJSONObject;
	}

	private JSONObject _getFreeMarkerFragmentEntryProcessorJSONObject(
		JSONObject jsonObject, long segmentsExperienceId) {

		if (!jsonObject.has(_ID_PREFIX + segmentsExperienceId)) {
			return JSONFactoryUtil.createJSONObject();
		}

		return jsonObject.getJSONObject(_ID_PREFIX + segmentsExperienceId);
	}

	private void _populateSegmentsExperienceId() throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
				"select * from FragmentEntryLink");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Long[] segmentsExperienceIds = getSegmentsExperienceIds(
					rs.getString("editableValues"));

				if (ArrayUtil.isEmpty(segmentsExperienceIds)) {
					continue;
				}

				long fragmentEntryLinkId = rs.getLong("fragmentEntryLinkId");
				String editableValues = rs.getString("editableValues");

				if (segmentsExperienceIds.length == 1) {
					_updateFragmentEntryLink(
						fragmentEntryLinkId, editableValues,
						segmentsExperienceIds[0]);
				}

				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Timestamp createDate = rs.getTimestamp("createDate");
				long originalFragmentEntryLinkId = rs.getLong(
					"originalFragmentEntryLinkId");
				long fragmentEntryId = rs.getLong("fragmentEntryId");
				long classNameId = rs.getLong("classNameId");
				long classPK = rs.getLong("classPK");
				String css = rs.getString("css");
				String html = rs.getString("html");
				String js = rs.getString("js");
				String configuration = rs.getString("configuration");
				String namespace = rs.getString("namespace");
				int position = rs.getInt("position");
				String rendererKey = rs.getString("rendererKey");
				Timestamp lastPropagationDate = rs.getTimestamp(
					"lastPropagationDate");
				Timestamp lastPublishDate = rs.getTimestamp("lastPublishDate");

				for (long segmentsExperienceId : segmentsExperienceIds) {
					_createFragmentEntryLink(
						groupId, companyId, userId, userName, createDate,
						originalFragmentEntryLinkId, fragmentEntryId,
						segmentsExperienceId, classNameId, classPK, css, html,
						js, configuration, editableValues, namespace, position,
						rendererKey, lastPropagationDate, lastPublishDate);
				}
			}
		}
	}

	private void _updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues,
			long segmentsExperienceId)
		throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(
				"update FragmentEntryLink set segmentsExperienceId = ?, " +
					"editableValues = ? where fragmentEntryLinkId = ?")) {

			ps.setLong(1, segmentsExperienceId);
			ps.setString(
				2, getEditableValues(editableValues, segmentsExperienceId));
			ps.setLong(3, fragmentEntryLinkId);

			ps.executeUpdate();
		}
	}

	private static final String _ID_PREFIX = "segments-experience-id-";

	private static final String _KEY_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR =
		"com.liferay.fragment.entry.processor.freemarker." +
			"FreeMarkerFragmentEntryProcessor";

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeFragmentEntryLink.class);

}