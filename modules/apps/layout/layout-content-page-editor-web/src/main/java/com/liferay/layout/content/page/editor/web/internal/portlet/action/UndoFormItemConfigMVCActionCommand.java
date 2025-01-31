/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.DefaultFragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorRegistry;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLinkService;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.content.page.editor.web.internal.util.layout.structure.LayoutStructureUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.FormStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Arrays;
import java.util.Collections;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/undo_form_item_config"
	},
	service = MVCActionCommand.class
)
public class UndoFormItemConfigMVCActionCommand
	extends BaseContentPageEditorTransactionalMVCActionCommand {

	@Override
	protected JSONObject doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] addedItemIds = ParamUtil.getStringValues(
			actionRequest, "addedItemIds");
		JSONObject configJSONObject = _jsonFactory.createJSONObject(
			ParamUtil.getString(actionRequest, "config"));
		String itemId = ParamUtil.getString(actionRequest, "itemId");
		JSONArray movedItemsJSONArray = _jsonFactory.createJSONArray(
			ParamUtil.getString(actionRequest, "movedItemIds"));
		String[] removedItemIds = ParamUtil.getStringValues(
			actionRequest, "removedItemIds");
		long segmentsExperienceId = ParamUtil.getLong(
			actionRequest, "segmentsExperienceId");

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					themeDisplay.getScopeGroupId(), themeDisplay.getPlid());

		FormStyledLayoutStructureItem formStyledLayoutStructureItem =
			(FormStyledLayoutStructureItem)LayoutStructure.of(
				layoutPageTemplateStructure.getData(segmentsExperienceId)
			).getLayoutStructureItem(
				itemId
			);

		long stepperFragmentEntryLinkId = ParamUtil.getLong(
			actionRequest, "stepperFragmentEntryLinkId");

		FragmentEntryLink stepperFragmentEntryLink =
			_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
				stepperFragmentEntryLinkId);

		System.out.println(formStyledLayoutStructureItem.getNumberOfSteps());

		if (stepperFragmentEntryLink != null) {
			JSONObject editableValuesJSONObject =
				_fragmentEntryLinkManager.mergeEditableValuesJSONObject(
					_jsonFactory.createJSONObject(
						stepperFragmentEntryLink.getEditableValues()),
					JSONUtil.put(
						FragmentEntryProcessorConstants.
							KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR,
						JSONUtil.put(
							"numberOfSteps",
							configJSONObject.getInt("numberOfSteps"))));

			stepperFragmentEntryLink =
				_fragmentEntryLinkLocalService.updateFragmentEntryLink(
					themeDisplay.getUserId(),
					stepperFragmentEntryLink.getFragmentEntryLinkId(),
					editableValuesJSONObject.toString());

			FragmentEntryProcessorContext fragmentEntryProcessorContext =
				new DefaultFragmentEntryProcessorContext(
					_portal.getHttpServletRequest(actionRequest),
					_portal.getHttpServletResponse(actionResponse),
					FragmentEntryLinkConstants.EDIT,
					LocaleUtil.getMostRelevantLocale());

			String processedHTML =
				_fragmentEntryProcessorRegistry.processFragmentEntryLinkHTML(
					stepperFragmentEntryLink, fragmentEntryProcessorContext);

			JSONObject newEditableValuesJSONObject =
				_fragmentEntryLinkManager.mergeEditableValuesJSONObject(
					_fragmentEntryProcessorRegistry.
						getDefaultEditableValuesJSONObject(
							processedHTML,
							stepperFragmentEntryLink.getConfiguration()),
					editableValuesJSONObject);

			stepperFragmentEntryLink =
				_fragmentEntryLinkService.updateFragmentEntryLink(
					stepperFragmentEntryLink.getFragmentEntryLinkId(),
					newEditableValuesJSONObject.toString());
		}

		FragmentEntryLink finalStepperFragmentEntryLink =
			stepperFragmentEntryLink;

		return JSONUtil.put(
			"fragmentEntryLinks",
			() -> {
				if (finalStepperFragmentEntryLink == null) {
					return null;
				}

				LayoutStructure layoutStructure = LayoutStructure.of(
					layoutPageTemplateStructure.getData(segmentsExperienceId));

				return JSONUtil.put(
					String.valueOf(
						finalStepperFragmentEntryLink.getFragmentEntryLinkId()),
					_fragmentEntryLinkManager.getFragmentEntryLinkJSONObject(
						finalStepperFragmentEntryLink,
						_portal.getHttpServletRequest(actionRequest),
						_portal.getHttpServletResponse(actionResponse),
						layoutStructure));
			}
		).put(
			"layoutData",
			LayoutStructureUtil.updateLayoutPageTemplateData(
				themeDisplay.getScopeGroupId(), segmentsExperienceId,
				themeDisplay.getPlid(),
				layoutStructure -> {
					LayoutStructureItem layoutStructureItem =
						layoutStructure.getLayoutStructureItem(itemId);

					layoutStructureItem.updateItemConfig(configJSONObject);

					for (int i = 0; i < movedItemsJSONArray.length(); i++) {
						JSONObject jsonObject =
							movedItemsJSONArray.getJSONObject(i);

						layoutStructure.moveLayoutStructureItem(
							jsonObject.getString("itemId"),
							jsonObject.getString("parentId"), -1);
					}

					layoutStructure.markLayoutStructureItemForDeletion(
						Arrays.asList(removedItemIds), Collections.emptyList());

					for (String addedItemId : addedItemIds) {
						layoutStructure.unmarkLayoutStructureItemForDeletion(
							addedItemId);
					}
				})
		);
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLinkManager _fragmentEntryLinkManager;

	@Reference
	private FragmentEntryLinkService _fragmentEntryLinkService;

	@Reference
	private FragmentEntryProcessorRegistry _fragmentEntryProcessorRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

}