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

package com.liferay.asset.categories.admin.web.internal.exportimport.data.handler;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zsolt Berentey
 * @author Gergely Mathe
 * @author Mate Thurzo
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class AssetVocabularyStagedModelDataHandler
	extends BaseStagedModelDataHandler<AssetVocabulary> {

	public static final String[] CLASS_NAMES =
		{AssetVocabulary.class.getName()};

	@Override
	public void deleteStagedModel(AssetVocabulary vocabulary)
		throws PortalException {

		_assetVocabularyLocalService.deleteVocabulary(vocabulary);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		AssetVocabulary vocabulary = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (vocabulary != null) {
			deleteStagedModel(vocabulary);
		}
	}

	@Override
	public AssetVocabulary fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _assetVocabularyLocalService.
			fetchAssetVocabularyByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<AssetVocabulary> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _assetVocabularyLocalService.
			getAssetVocabulariesByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<AssetVocabulary>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(AssetVocabulary vocabulary) {
		return vocabulary.getTitleCurrentValue();
	}

	protected ServiceContext createServiceContext(
		PortletDataContext portletDataContext, AssetVocabulary vocabulary) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(vocabulary.getCreateDate());
		serviceContext.setModifiedDate(vocabulary.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		return serviceContext;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, AssetVocabulary vocabulary)
		throws Exception {

		Element vocabularyElement = portletDataContext.getExportDataElement(
			vocabulary);

		String vocabularyPath = ExportImportPathUtil.getModelPath(vocabulary);

		vocabularyElement.addAttribute("path", vocabularyPath);

		UnicodeProperties properties = new UnicodeProperties(true);

		properties.fastLoad(vocabulary.getSettings());

		String selectedClassNameIdsProperty = GetterUtil.getString(
			properties.remove("selectedClassNameIds"), "");

		String[] selectedClassNameIds = selectedClassNameIdsProperty.split(
			StringPool.COMMA);

		StringBundler selectedClassNames = new StringBundler(
			selectedClassNameIds.length * 4 - 1);

		for (String selectedClassNameId : selectedClassNameIds) {
			String[] types = selectedClassNameId.split(StringPool.COLON);

			if (types.length != 2) {
				continue;
			}

			if (selectedClassNames.length() > 0) {
				selectedClassNames.append(StringPool.COMMA);
			}

			long classNameId = Long.parseLong(types[0]);

			if (classNameId <= 0) {
				selectedClassNames.append("0");
				selectedClassNames.append(StringPool.COLON);
				selectedClassNames.append("-1");

				continue;
			}

			String classType = types[1];

			String className = PortalUtil.getClassName(classNameId);

			selectedClassNames.append(className);

			selectedClassNames.append(StringPool.COLON);
			selectedClassNames.append(classType);
		}

		if (selectedClassNames.length() > 0) {
			properties.setProperty(
				"selectedClassNames", selectedClassNames.toString());
		}

		String requiredClassNameIdsProperty = GetterUtil.getString(
			properties.remove("requiredClassNameIds"), "");

		String[] requiredClassNameIds = requiredClassNameIdsProperty.split(
			StringPool.COMMA);

		StringBundler requiredClassNames = new StringBundler(
			requiredClassNameIds.length * 4 - 1);

		for (String requiredClassNameId : requiredClassNameIds) {
			String[] types = requiredClassNameId.split(StringPool.COLON);

			if (types.length != 2) {
				continue;
			}

			if (requiredClassNames.length() > 0) {
				requiredClassNames.append(StringPool.COMMA);
			}

			long classNameId = Long.parseLong(types[0]);

			if (classNameId <= 0) {
				requiredClassNames.append("0");
				requiredClassNames.append(StringPool.COLON);
				requiredClassNames.append("-1");

				continue;
			}

			String classType = types[1];

			String className = PortalUtil.getClassName(classNameId);

			requiredClassNames.append(className);

			requiredClassNames.append(StringPool.COLON);
			requiredClassNames.append(classType);
		}

		if (requiredClassNames.length() > 0) {
			properties.setProperty(
				"requiredClassNames", requiredClassNames.toString());
		}

		vocabulary.setSettings(properties.toString());

		portletDataContext.addReferenceElement(
			vocabulary, vocabularyElement, vocabulary,
			PortletDataContext.REFERENCE_TYPE_DEPENDENCY, false);

		portletDataContext.addPermissions(
			AssetVocabulary.class, vocabulary.getVocabularyId());

		portletDataContext.addZipEntry(vocabularyPath, vocabulary);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long vocabularyId)
		throws Exception {

		AssetVocabulary existingVocabulary = fetchMissingReference(
			uuid, groupId);

		if (existingVocabulary == null) {
			return;
		}

		Map<Long, Long> vocabularyIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				AssetVocabulary.class);

		vocabularyIds.put(vocabularyId, existingVocabulary.getVocabularyId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, AssetVocabulary vocabulary)
		throws Exception {

		long userId = portletDataContext.getUserId(vocabulary.getUserUuid());

		ServiceContext serviceContext = createServiceContext(
			portletDataContext, vocabulary);

		AssetVocabulary importedVocabulary = null;

		AssetVocabulary existingVocabulary = fetchStagedModelByUuidAndGroupId(
			vocabulary.getUuid(), portletDataContext.getScopeGroupId());

		UnicodeProperties properties = new UnicodeProperties(true);

		properties.fastLoad(vocabulary.getSettings());

		String selectedClassNamesProperty = GetterUtil.getString(
			properties.remove("selectedClassNames"), "");

		String[] selectedClassNames = selectedClassNamesProperty.split(
			StringPool.COMMA);

		StringBundler selectedClassNameIds = new StringBundler(
			selectedClassNames.length * 4 - 1);

		for (String selectedClassName : selectedClassNames) {
			String[] types = selectedClassName.split(StringPool.COLON);

			if (types.length != 2) {
				continue;
			}

			if (selectedClassNameIds.length() > 0) {
				selectedClassNameIds.append(StringPool.COMMA);
			}

			if (types[0].equals("0")) {
				selectedClassNameIds.append("0");
				selectedClassNameIds.append(StringPool.COLON);
				selectedClassNameIds.append("-1");

				continue;
			}

			long classNameId = PortalUtil.getClassNameId(types[0]);
			String classType = types[1];

			selectedClassNameIds.append(classNameId);
			selectedClassNameIds.append(StringPool.COLON);
			selectedClassNameIds.append(classType);
		}

		if (selectedClassNameIds.length() > 0) {
			properties.put(
				"selectedClassNameIds", selectedClassNameIds.toString());
		}

		String requiredClassNamesProperty = GetterUtil.getString(
			properties.remove("requiredClassNames"), "");

		String[] requiredClassNames = requiredClassNamesProperty.split(
			StringPool.COMMA);

		StringBundler requiredClassNameIds = new StringBundler(
			requiredClassNames.length * 4 - 1);

		for (String requiredClassName : requiredClassNames) {
			String[] types = requiredClassName.split(StringPool.COLON);

			if (types.length != 2) {
				continue;
			}

			if (requiredClassNameIds.length() > 0) {
				requiredClassNameIds.append(StringPool.COMMA);
			}

			if (types[0].equals("0")) {
				requiredClassNameIds.append("0");
				requiredClassNameIds.append(StringPool.COLON);
				requiredClassNameIds.append("-1");

				continue;
			}

			long classNameId = PortalUtil.getClassNameId(types[0]);
			String classType = types[1];

			requiredClassNameIds.append(classNameId);
			requiredClassNameIds.append(StringPool.COLON);
			requiredClassNameIds.append(classType);
		}

		if (requiredClassNameIds.length() > 0) {
			properties.put(
				"requiredClassNameIds", selectedClassNameIds.toString());
		}

		vocabulary.setSettings(properties.toString());

		if (existingVocabulary == null) {
			String name = getVocabularyName(
				null, portletDataContext.getScopeGroupId(),
				vocabulary.getName(), 2);

			serviceContext.setUuid(vocabulary.getUuid());

			importedVocabulary = _assetVocabularyLocalService.addVocabulary(
				userId, portletDataContext.getScopeGroupId(), StringPool.BLANK,
				getVocabularyTitleMap(
					portletDataContext.getScopeGroupId(), vocabulary, name),
				vocabulary.getDescriptionMap(), vocabulary.getSettings(),
				serviceContext);
		}
		else {
			String name = getVocabularyName(
				vocabulary.getUuid(), portletDataContext.getScopeGroupId(),
				vocabulary.getName(), 2);

			importedVocabulary = _assetVocabularyLocalService.updateVocabulary(
				existingVocabulary.getVocabularyId(), StringPool.BLANK,
				getVocabularyTitleMap(
					portletDataContext.getScopeGroupId(), vocabulary, name),
				vocabulary.getDescriptionMap(), vocabulary.getSettings(),
				serviceContext);
		}

		Map<Long, Long> vocabularyIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				AssetVocabulary.class);

		vocabularyIds.put(
			vocabulary.getVocabularyId(), importedVocabulary.getVocabularyId());

		portletDataContext.importPermissions(
			AssetVocabulary.class, vocabulary.getVocabularyId(),
			importedVocabulary.getVocabularyId());
	}

	protected String getVocabularyName(
			String uuid, long groupId, String name, int count)
		throws Exception {

		AssetVocabulary vocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(groupId, name);

		if (vocabulary == null) {
			return name;
		}

		if (Validator.isNotNull(uuid) && uuid.equals(vocabulary.getUuid())) {
			return name;
		}

		name = StringUtil.appendParentheticalSuffix(name, count);

		return getVocabularyName(uuid, groupId, name, ++count);
	}

	protected Map<Locale, String> getVocabularyTitleMap(
			long groupId, AssetVocabulary vocabulary, String name)
		throws PortalException {

		Map<Locale, String> titleMap = vocabulary.getTitleMap();

		if (titleMap == null) {
			titleMap = new HashMap<>();
		}

		titleMap.put(PortalUtil.getSiteDefaultLocale(groupId), name);

		return titleMap;
	}

	@Reference(unbind = "-")
	protected void setAssetVocabularyLocalService(
		AssetVocabularyLocalService assetVocabularyLocalService) {

		_assetVocabularyLocalService = assetVocabularyLocalService;
	}

	private AssetVocabularyLocalService _assetVocabularyLocalService;

}