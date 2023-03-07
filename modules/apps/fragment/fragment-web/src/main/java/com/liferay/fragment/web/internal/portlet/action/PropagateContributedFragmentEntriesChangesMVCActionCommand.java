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

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.contributor.FragmentCollectionContributorRegistry;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/propagate_contributed_fragment_entries_changes"
	},
	service = AopService.class
)
public class PropagateContributedFragmentEntriesChangesMVCActionCommand
	extends BaseMVCActionCommand implements AopService, MVCActionCommand {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		return super.processAction(actionRequest, actionResponse);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, FragmentEntry> fragmentCollectionContributorEntries =
			_fragmentCollectionContributorRegistry.getFragmentEntries();

		for (Map.Entry<String, FragmentEntry> entry :
				fragmentCollectionContributorEntries.entrySet()) {

			FragmentEntry fragmentEntry = entry.getValue();

			ActionableDynamicQuery actionableDynamicQuery =
				_fragmentEntryLinkLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property fragmentEntryKeyProperty =
						PropertyFactoryUtil.forName("fragmentEntryKey");

					dynamicQuery.add(
						fragmentEntryKeyProperty.eq(
							fragmentEntry.getFragmentEntryKey()));
				});
			actionableDynamicQuery.setCompanyId(themeDisplay.getCompanyId());
			actionableDynamicQuery.setPerformActionMethod(
				(FragmentEntryLink fragmentEntryLink) ->
					_fragmentEntryLinkLocalService.updateLatestChanges(
						fragmentEntryLink.getFragmentEntryLinkId()));

			actionableDynamicQuery.performActions();
		}

		PortletPreferences portletPreferences =
			_portalPreferencesLocalService.getPreferences(
				themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY);

		portletPreferences.setValue(
			"propagateContributedFragmentChanges", Boolean.TRUE.toString());

		portletPreferences.store();

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	@Reference
	private FragmentCollectionContributorRegistry
		_fragmentCollectionContributorRegistry;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private PortalPreferencesLocalService _portalPreferencesLocalService;

}