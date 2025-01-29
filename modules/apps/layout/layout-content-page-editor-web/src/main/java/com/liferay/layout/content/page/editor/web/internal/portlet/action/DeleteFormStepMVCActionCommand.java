/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.manager.FormItemManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureService;
import com.liferay.layout.util.constants.LayoutDataItemTypeConstants;
import com.liferay.layout.util.structure.FormStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructureItemUtil;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/delete_form_step"
	},
	service = MVCActionCommand.class
)
public class DeleteFormStepMVCActionCommand
	extends BaseContentPageEditorTransactionalMVCActionCommand {

	@Override
	protected JSONObject doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String itemId = ParamUtil.getString(actionRequest, "itemId");
		long segmentsExperienceId = ParamUtil.getLong(
			actionRequest, "segmentsExperienceId");

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					themeDisplay.getScopeGroupId(), themeDisplay.getPlid());

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getData(segmentsExperienceId));

		FormStyledLayoutStructureItem formStyledLayoutStructureItem =
			(FormStyledLayoutStructureItem)LayoutStructureItemUtil.getAncestor(
				itemId, LayoutDataItemTypeConstants.TYPE_FORM, layoutStructure);

		if (formStyledLayoutStructureItem == null) {
			return JSONUtil.put(
				"error",
				_language.get(
					themeDisplay.getLocale(), "an-unexpected-error-occurred"));
		}

		int numberOfSteps = formStyledLayoutStructureItem.getNumberOfSteps();

		if (numberOfSteps <= 1) {
			return JSONUtil.put(
				"error",
				_language.get(
					themeDisplay.getLocale(), "an-unexpected-error-occurred"));
		}

		LayoutStructureItem formStepContainerStyledLayoutStructureItem =
			_formItemManager.findFormStepContainerStyledLayoutStructureItem(
				formStyledLayoutStructureItem, layoutStructure);

		List<String> childrenItems =
			formStepContainerStyledLayoutStructureItem.getChildrenItemIds();

		if (Objects.equals(childrenItems.get(0), itemId)) {
			return JSONUtil.put(
				"error",
				_language.get(
					themeDisplay.getLocale(), "an-unexpected-error-occurred"));
		}

		List<LayoutStructureItem> removedLayoutStructureItems =
			new ArrayList<>();

		if (numberOfSteps == 2) {
			formStyledLayoutStructureItem.setFormType("simple");
			formStyledLayoutStructureItem.setNumberOfSteps(0);

			FormItemManager.LayoutStructureItemChanges
				layoutStructureItemChange =
					_formItemManager.changeToSimpleFormType(
						formStyledLayoutStructureItem, layoutStructure,
						themeDisplay.getLocale());

			removedLayoutStructureItems.addAll(
				layoutStructureItemChange.getRemovedLayoutStructureItems());
		}
		else {
			formStyledLayoutStructureItem.setNumberOfSteps(numberOfSteps - 1);

			layoutStructure.markLayoutStructureItemForDeletion(
				Collections.singletonList(itemId), Collections.emptyList());

			removedLayoutStructureItems.add(
				layoutStructure.getLayoutStructureItem(itemId));
		}

		layoutPageTemplateStructure =
			_layoutPageTemplateStructureService.
				updateLayoutPageTemplateStructureData(
					themeDisplay.getScopeGroupId(), themeDisplay.getPlid(),
					segmentsExperienceId, layoutStructure.toString());

		LayoutStructure updatedLayoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getData(segmentsExperienceId));

		return JSONUtil.put(
			"fragmentEntryLinks",
			() -> {
				long stepperFragmentEntryLinkId = ParamUtil.getLong(
					actionRequest, "stepperFragmentEntryLinkId");

				FragmentEntryLink stepperFragmentEntryLink =
					_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
						stepperFragmentEntryLinkId);

				if (stepperFragmentEntryLink == null) {
					return null;
				}

				stepperFragmentEntryLink =
					_formItemManager.updateNumberOfStepps(
						actionRequest, actionResponse, numberOfSteps - 1,
						stepperFragmentEntryLink);

				return JSONUtil.put(
					String.valueOf(
						stepperFragmentEntryLink.getFragmentEntryLinkId()),
					_fragmentEntryLinkManager.getFragmentEntryLinkJSONObject(
						stepperFragmentEntryLink,
						_portal.getHttpServletRequest(actionRequest),
						_portal.getHttpServletResponse(actionResponse),
						layoutStructure));
			}
		).put(
			"layoutData", updatedLayoutStructure.toJSONObject()
		).put(
			"removedItemIds",
			_jsonFactory.createJSONArray(
				TransformUtil.transform(
					removedLayoutStructureItems,
					LayoutStructureItem::getItemId))
		);
	}

	@Reference
	private FormItemManager _formItemManager;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLinkManager _fragmentEntryLinkManager;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private LayoutPageTemplateStructureService
		_layoutPageTemplateStructureService;

	@Reference
	private Portal _portal;

}