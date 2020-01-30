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

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Marcela Cunha
 */
public class UpgradeDDMStructure extends UpgradeProcess {

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

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select * from DDMStructure where classNameId = ? or " +
					"classNameId = ?");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructure set definition = ? where " +
						"structureId = ?")) {

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
				}

				ps2.executeBatch();
			}
		}
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

}