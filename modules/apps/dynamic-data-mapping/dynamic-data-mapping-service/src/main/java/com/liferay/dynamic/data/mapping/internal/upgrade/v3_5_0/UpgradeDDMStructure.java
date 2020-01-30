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

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_5_0;

import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Marcela Cunha
 */
public class UpgradeDDMStructure extends UpgradeProcess {

	public UpgradeDDMStructure(
		DDM ddm, DDMFormLayoutSerializer ddmFormLayoutSerializer,
		DDMFormDeserializer ddmFormJSONDeserializer,
		DDMFormDeserializer ddmFormXSDDeserializer) {

		_ddm = ddm;
		_ddmFormLayoutSerializer = ddmFormLayoutSerializer;
		_ddmFormJSONDeserializer = ddmFormJSONDeserializer;
		_ddmFormXSDDeserializer = ddmFormXSDDeserializer;
	}

	protected JSONObject createLocalizedValue(String value) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String languageId = UpgradeProcessUtil.getDefaultLanguageId(_companyId);

		if (Validator.isNull(value)) {
			jsonObject.put(languageId, StringPool.BLANK);
		}
		else {
			jsonObject.put(languageId, value);
		}

		return jsonObject;
	}

	protected JSONArray createOption() throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"label",
			JSONFactoryUtil.createJSONObject(
				createLocalizedValue(
					"Option"
				).toString())
		).put(
			"value", "Option"
		);

		return JSONUtil.put(jsonObject);
	}

	protected DDMForm deserialize(String content, String type) {
		DDMFormDeserializerDeserializeRequest.Builder builder =
			DDMFormDeserializerDeserializeRequest.Builder.newBuilder(content);

		DDMFormDeserializer ddmFormDeserializer = null;

		if (StringUtil.equalsIgnoreCase(type, "json")) {
			ddmFormDeserializer = _ddmFormJSONDeserializer;
		}
		else if (StringUtil.equalsIgnoreCase(type, "xsd")) {
			ddmFormDeserializer = _ddmFormXSDDeserializer;
		}

		DDMFormDeserializerDeserializeResponse
			ddmFormDeserializerDeserializeResponse =
				ddmFormDeserializer.deserialize(builder.build());

		return ddmFormDeserializerDeserializeResponse.getDDMForm();
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb1 = new StringBundler(6);

		sb1.append("insert into DDMStructureVersion (structureVersionId, ");
		sb1.append("groupId, companyId, userId, userName, createDate, ");
		sb1.append("structureId, version, parentStructureId, name, ");
		sb1.append("description, definition, storageType, type_, status, ");
		sb1.append("statusByUserId, statusByUserName, statusDate) values (?, ");
		sb1.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		StringBundler sb2 = new StringBundler(5);

		sb2.append("insert into DDMStructureLayout (uuid_, ");
		sb2.append("structureLayoutId, groupId, companyId, userId, userName, ");
		sb2.append("createDate, modifiedDate, structureLayoutKey, ");
		sb2.append("structureVersionId, definition) values (?, ?, ?, ?, ?, ");
		sb2.append("?, ?, ?, ?, ?, ?)");

		try (PreparedStatement ps1 = connection.prepareStatement(
				"select * from DDMStructure where classNameId = ? or " +
					"classNameId = ?");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructure set definition = ? where " +
						"structureId = ?");
			PreparedStatement ps3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, sb1.toString());
			PreparedStatement ps4 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, sb2.toString())) {

			ps1.setLong(
				1,
				PortalUtil.getClassNameId(
					"com.liferay.journal.model.JournalArticle"));

			ps1.setLong(
				2,
				PortalUtil.getClassNameId(
					"com.liferay.document.library.kernel.model." +
						"DLFileEntryMetadata"));

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					String definition = upgradeDefinition(
						rs.getString("definition"));

					long structureId = rs.getLong("structureId");

					ps2.setString(1, definition);

					ps2.setLong(2, structureId);

					ps2.addBatch();

					_companyId = rs.getLong("companyId");
					long groupId = rs.getLong("groupId");
					Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
					String storageType = rs.getString("storageType");
					long userId = rs.getLong("userId");
					String userName = rs.getString("userName");

					long structureVersionId = increment();

					ps3.setLong(1, structureVersionId);

					ps3.setLong(2, groupId);
					ps3.setLong(3, _companyId);
					ps3.setLong(4, userId);
					ps3.setString(5, userName);
					ps3.setTimestamp(6, modifiedDate);
					ps3.setLong(7, structureId);
					ps3.setString(8, getVersion(structureId));
					ps3.setLong(9, rs.getLong("parentStructureId"));
					ps3.setString(10, rs.getString("name"));
					ps3.setString(11, rs.getString("description"));
					ps3.setString(12, definition);
					ps3.setString(13, storageType);
					ps3.setInt(14, rs.getInt("type_"));
					ps3.setInt(15, WorkflowConstants.STATUS_APPROVED);
					ps3.setLong(16, userId);
					ps3.setString(17, userName);
					ps3.setTimestamp(18, modifiedDate);

					ps3.addBatch();

					DDMForm ddmForm = getDDMForm(definition, storageType);

					String ddmFormLayoutDefinition =
						getDefaultDDMFormLayoutDefinition(ddmForm);

					ps4.setString(1, PortalUUIDUtil.generate());
					ps4.setLong(2, increment());
					ps4.setLong(3, groupId);
					ps4.setLong(4, _companyId);
					ps4.setLong(5, userId);
					ps4.setString(6, userName);
					ps4.setTimestamp(7, modifiedDate);
					ps4.setTimestamp(8, modifiedDate);
					ps4.setString(9, rs.getString("structureKey"));
					ps4.setLong(10, structureVersionId);
					ps4.setString(11, ddmFormLayoutDefinition);

					ps4.addBatch();
				}

				ps2.executeBatch();

				ps3.executeBatch();

				ps4.executeBatch();
			}
		}
	}

	protected DDMForm getDDMForm(String definition, String storageType) {
		if (storageType.equals("expando") || storageType.equals("xml")) {
			return deserialize(definition, "xsd");
		}

		return deserialize(definition, "json");
	}

	protected String getDefaultDDMFormLayoutDefinition(DDMForm ddmForm) {
		DDMFormLayout ddmFormLayout = _ddm.getDefaultDDMFormLayout(ddmForm);

		DDMFormLayoutSerializerSerializeRequest.Builder builder =
			DDMFormLayoutSerializerSerializeRequest.Builder.newBuilder(
				ddmFormLayout);

		DDMFormLayoutSerializerSerializeResponse
			ddmFormLayoutSerializerSerializeResponse =
				_ddmFormLayoutSerializer.serialize(builder.build());

		return ddmFormLayoutSerializerSerializeResponse.getContent();
	}

	protected String getNextVersion(String version) {
		int[] versionParts = StringUtil.split(version, StringPool.PERIOD, 0);

		return versionParts[0] + StringPool.PERIOD + ++versionParts[1];
	}

	protected String getVersion(Long structureId) throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select MAX(version) from DDMStructureVersion where " +
					"structureId = ?")) {

			ps1.setLong(1, structureId);

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					String lastVersion = rs.getString(1);

					return getNextVersion(lastVersion);
				}
			}
		}

		return DDMStructureConstants.VERSION_DEFAULT;
	}

	protected void upgradeColorField(JSONObject field) {
		field.put(
			_DATATYPE, "string"
		).put(
			"visibilityExpression", StringPool.BLANK
		);
		upgradeFieldType(field, StringPool.BLANK);
	}

	protected void upgradeDateField(JSONObject field) {
		field.put(
			_DATATYPE, "string"
		).put(
			"visibilityExpression", StringPool.BLANK
		);
		upgradeFieldType(field, StringPool.BLANK);
	}

	protected void upgradeDecimalField(JSONObject field) {
		field.put(
			_DATATYPE, "decimal"
		).put(
			"visibilityExpression", StringPool.BLANK
		);
		upgradeFieldType(field, "numeric");
	}

	protected String upgradeDefinition(String definition) throws Exception {
		JSONObject definitionJSONObject = JSONFactoryUtil.createJSONObject(
			definition);

		definitionJSONObject.put(
			"fields", upgradeFields(definitionJSONObject.get("fields")));

		return definitionJSONObject.toString();
	}

	protected void upgradeDocumentLibraryField(JSONObject field) {
		field.put(
			_DATATYPE, "string"
		).put(
			"visibilityExpression", StringPool.BLANK
		);
		upgradeFieldType(field, "document_library");
	}

	protected JSONArray upgradeFields(Object fields) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (Validator.isNotNull(fields)) {
			JSONArray fieldsJSONArray = JSONFactoryUtil.createJSONArray(
				fields.toString());

			for (Object field : fieldsJSONArray) {
				JSONObject fieldJSONObject = JSONFactoryUtil.createJSONObject(
					field.toString());

				String type = fieldJSONObject.getString(_TYPE);

				if (StringUtil.equals(type, "ddm-color")) {
					upgradeColorField(fieldJSONObject);
				}
				else if (StringUtil.equals(type, "ddm-date")) {
					upgradeDateField(fieldJSONObject);
				}
				else if (type.startsWith("ddm-decimal")) {
					upgradeDecimalField(fieldJSONObject);
				}
				else if (type.startsWith("ddm-documentlibrary")) {
					upgradeDocumentLibraryField(fieldJSONObject);
				}
				else if (type.startsWith("ddm-geolocation")) {
					upgradeGeolocation(fieldJSONObject);
				}
				else if (type.startsWith("ddm-integer")) {
					upgradeIntegerField(fieldJSONObject);
				}
				else if (type.startsWith("ddm-number")) {
					upgradeNumberField(fieldJSONObject);
				}
				else if (StringUtil.equals(type, "ddm-separator")) {
					upgradeSeparatorField(fieldJSONObject);
				}
				else if (type.startsWith("ddm-")) {
					upgradeFieldType(fieldJSONObject, StringPool.BLANK);
				}
				else if (StringUtil.equals(type, "text")) {
					upgradeTextField(fieldJSONObject);
				}
				else if (StringUtil.equals(type, "textarea")) {
					upgradeTextArea(fieldJSONObject);
				}

				if (Validator.isNotNull(fieldJSONObject.get("nestedFields"))) {
					fieldJSONObject.put(
						"nestedFields",
						upgradeFields(fieldJSONObject.get("nestedFields")));
				}

				jsonArray.put(fieldJSONObject);
			}
		}

		return jsonArray;
	}

	protected void upgradeFieldType(JSONObject field, String fieldType) {
		String type = field.getString(_TYPE);

		if (fieldType.isEmpty()) {
			field.put(_TYPE, type.substring(4));
		}
		else {
			field.put(_TYPE, fieldType);
		}
	}

	protected void upgradeGeolocation(JSONObject field) {
		field.put(_DATATYPE, "string");

		upgradeFieldType(field, StringPool.BLANK);
	}

	protected void upgradeIntegerField(JSONObject field) {
		field.put("visibilityExpression", StringPool.BLANK);

		upgradeFieldType(field, "numeric");
	}

	protected void upgradeNumberField(JSONObject field) {
		field.put(
			_DATATYPE, "decimal"
		).put(
			"visibilityExpression", StringPool.BLANK
		);
		upgradeFieldType(field, "numeric");
	}

	protected void upgradeSeparatorField(JSONObject field) {
		field.put(_DATATYPE, StringPool.BLANK);
	}

	protected void upgradeTextArea(JSONObject field) {
		field.put(
			_DATATYPE, StringPool.BLANK
		).put(
			"fieldNamespace", StringPool.BLANK
		).put(
			"text", field.getJSONObject("predefinedValue")
		).put(
			"visibilityExpression", StringPool.BLANK
		);

		upgradeFieldType(field, "paragraph");
	}

	protected void upgradeTextField(JSONObject field) throws Exception {
		field.put(
			"autocomplete", false
		).put(
			"dataSourceType", "manual"
		).put(
			"ddmDataProviderInstanceId", "[]"
		).put(
			"ddmDataProviderInstanceOutput", "[]"
		).put(
			"displayStyle", "singleline"
		).put(
			"fieldNamespace", StringPool.BLANK
		).put(
			"options", createOption()
		).put(
			"placeholder", createLocalizedValue(StringPool.BLANK)
		).put(
			"tooltip", createLocalizedValue(StringPool.BLANK)
		).put(
			"visibilityExpression", StringPool.BLANK
		);
	}

	private static final String _DATATYPE = "dataType";

	private static final String _TYPE = "type";

	private long _companyId;
	private final DDM _ddm;
	private final DDMFormDeserializer _ddmFormJSONDeserializer;
	private final DDMFormLayoutSerializer _ddmFormLayoutSerializer;
	private final DDMFormDeserializer _ddmFormXSDDeserializer;

}