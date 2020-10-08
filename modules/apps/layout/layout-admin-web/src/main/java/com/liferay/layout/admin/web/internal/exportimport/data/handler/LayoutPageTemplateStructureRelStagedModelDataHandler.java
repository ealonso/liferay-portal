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

package com.liferay.layout.admin.web.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.layout.util.structure.StyledLayoutStructureItem;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class LayoutPageTemplateStructureRelStagedModelDataHandler
	extends BaseStagedModelDataHandler<LayoutPageTemplateStructureRel> {

	public static final String[] CLASS_NAMES = {
		LayoutPageTemplateStructureRel.class.getName()
	};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws Exception {

		Element layoutPageTemplateStructureRelElement =
			portletDataContext.getExportDataElement(
				layoutPageTemplateStructureRel);

		if (layoutPageTemplateStructureRel.getSegmentsExperienceId() !=
				SegmentsExperienceConstants.ID_DEFAULT) {

			SegmentsExperience segmentsExperience =
				_segmentsExperienceLocalService.fetchSegmentsExperience(
					layoutPageTemplateStructureRel.getSegmentsExperienceId());

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, layoutPageTemplateStructureRel,
				segmentsExperience, PortletDataContext.REFERENCE_TYPE_STRONG);
		}

		_exportBackgroundImages(
			portletDataContext, layoutPageTemplateStructureRel);

		portletDataContext.addClassedModel(
			layoutPageTemplateStructureRelElement,
			ExportImportPathUtil.getModelPath(layoutPageTemplateStructureRel),
			layoutPageTemplateStructureRel);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws Exception {

		Map<Long, Long> layoutPageTemplateStructureIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				LayoutPageTemplateStructure.class);

		long layoutPageTemplateStructureId = MapUtil.getLong(
			layoutPageTemplateStructureIds,
			layoutPageTemplateStructureRel.getLayoutPageTemplateStructureId(),
			layoutPageTemplateStructureRel.getLayoutPageTemplateStructureId());

		Map<Long, Long> segmentsExperienceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				SegmentsExperience.class);

		long segmentsExperienceId = MapUtil.getLong(
			segmentsExperienceIds,
			layoutPageTemplateStructureRel.getSegmentsExperienceId(),
			layoutPageTemplateStructureRel.getSegmentsExperienceId());

		LayoutPageTemplateStructureRel importedLayoutPageTemplateStructureRel =
			(LayoutPageTemplateStructureRel)
				layoutPageTemplateStructureRel.clone();

		importedLayoutPageTemplateStructureRel.setGroupId(
			portletDataContext.getScopeGroupId());
		importedLayoutPageTemplateStructureRel.setLayoutPageTemplateStructureId(
			layoutPageTemplateStructureId);
		importedLayoutPageTemplateStructureRel.setSegmentsExperienceId(
			segmentsExperienceId);

		String data = _importBackgroundImages(
			portletDataContext, layoutPageTemplateStructureRel);

		importedLayoutPageTemplateStructureRel.setData(data);

		LayoutPageTemplateStructureRel existingLayoutPageTemplateStructureRel =
			_stagedModelRepository.fetchStagedModelByUuidAndGroupId(
				layoutPageTemplateStructureRel.getUuid(),
				portletDataContext.getScopeGroupId());

		if ((existingLayoutPageTemplateStructureRel == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			importedLayoutPageTemplateStructureRel =
				_stagedModelRepository.addStagedModel(
					portletDataContext, importedLayoutPageTemplateStructureRel);
		}
		else {
			importedLayoutPageTemplateStructureRel.setMvccVersion(
				existingLayoutPageTemplateStructureRel.getMvccVersion());
			importedLayoutPageTemplateStructureRel.
				setLayoutPageTemplateStructureRelId(
					existingLayoutPageTemplateStructureRel.
						getLayoutPageTemplateStructureRelId());

			importedLayoutPageTemplateStructureRel =
				_stagedModelRepository.updateStagedModel(
					portletDataContext, importedLayoutPageTemplateStructureRel);
		}

		portletDataContext.importClassedModel(
			layoutPageTemplateStructureRel,
			importedLayoutPageTemplateStructureRel);
	}

	@Override
	protected StagedModelRepository<LayoutPageTemplateStructureRel>
		getStagedModelRepository() {

		return _stagedModelRepository;
	}

	private void _exportBackgroundImages(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws Exception {

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructureRel.getData());

		for (LayoutStructureItem layoutStructureItem :
				layoutStructure.getLayoutStructureItems()) {

			JSONObject backgroundImageJSONObject =
				_getBackgroundImageJSONObject(layoutStructureItem);

			if (backgroundImageJSONObject == null) {
				continue;
			}

			long fileEntryId = backgroundImageJSONObject.getLong("fileEntryId");

			if (fileEntryId <= 0) {
				continue;
			}

			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			if (!fileEntry.isInTrash()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, layoutPageTemplateStructureRel,
					fileEntry, PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
			}
		}
	}

	private JSONObject _getBackgroundImageJSONObject(
		LayoutStructureItem layoutStructureItem) {

		if (!(layoutStructureItem instanceof StyledLayoutStructureItem)) {
			return null;
		}

		StyledLayoutStructureItem styledLayoutStructureItem =
			(StyledLayoutStructureItem)layoutStructureItem;

		return styledLayoutStructureItem.getBackgroundImageJSONObject();
	}

	private String _importBackgroundImages(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws Exception {

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructureRel.getData());

		for (LayoutStructureItem layoutStructureItem :
				layoutStructure.getLayoutStructureItems()) {

			JSONObject backgroundImageJSONObject =
				_getBackgroundImageJSONObject(layoutStructureItem);

			if (backgroundImageJSONObject == null) {
				continue;
			}

			long fileEntryId = backgroundImageJSONObject.getLong("fileEntryId");

			if (fileEntryId <= 0) {
				continue;
			}

			Map<Long, Long> primaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					FileEntry.class);

			fileEntryId = MapUtil.getLong(
				primaryKeys, fileEntryId, fileEntryId);

			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			backgroundImageJSONObject.put(
				"fileEntryId", fileEntryId
			).put(
				"url", _dlURLHelper.getImagePreviewURL(fileEntry, null)
			);
		}

		return layoutStructure.toString();
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference(target = "(content.processor.type=DLReferences)")
	private ExportImportContentProcessor<String>
		_dlReferencesExportImportContentProcessor;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel)",
		unbind = "-"
	)
	private StagedModelRepository<LayoutPageTemplateStructureRel>
		_stagedModelRepository;

}