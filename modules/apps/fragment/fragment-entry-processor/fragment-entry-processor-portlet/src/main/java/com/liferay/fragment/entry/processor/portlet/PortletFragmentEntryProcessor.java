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

package com.liferay.fragment.entry.processor.portlet;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.FragmentEntryProcessor;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.fragment.renderer.FragmentPortletRenderer;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.segments.util.SegmentsExperiencePortletUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalLong;
import java.util.stream.LongStream;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, property = "fragment.entry.processor.priority:Integer=6",
	service = FragmentEntryProcessor.class
)
public class PortletFragmentEntryProcessor implements FragmentEntryProcessor {

	@Override
	public String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String html,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		String widgetHTML = _renderWidgetHTML(
			fragmentEntryLink.getEditableValues(),
			fragmentEntryProcessorContext);

		if (Validator.isNotNull(widgetHTML)) {
			return widgetHTML;
		}

		return html;
	}

	@Override
	public void validateFragmentEntryHTML(String html, String configuration) {
	}

	private OptionalLong _getSegmentsExperienceIdOptional(
		long[] segmentsExperienceIds) {

		LongStream longStream = Arrays.stream(segmentsExperienceIds);

		return longStream.mapToObj(
			segmentsExperienceId ->
				_segmentsExperienceLocalService.fetchSegmentsExperience(
					segmentsExperienceId)
		).filter(
			segmentsExperience -> segmentsExperience != null
		).sorted(
			Comparator.comparingInt(
				SegmentsExperience::getPriority
			).reversed()
		).mapToLong(
			SegmentsExperience::getSegmentsExperienceId
		).findFirst();
	}

	private String _renderWidgetHTML(
			String editableValues,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			editableValues);

		String portletId = jsonObject.getString("portletId");

		if (Validator.isNull(portletId)) {
			return StringPool.BLANK;
		}

		String instanceId = jsonObject.getString("instanceId");
		Portlet portlet = _portletLocalService.getPortletById(
			SegmentsExperiencePortletUtil.decodePortletName(portletId));
		PortletPreferences portletPreferences = null;

		OptionalLong segmentsExperienceIdOptionalLong =
			_getSegmentsExperienceIdOptional(
				fragmentEntryProcessorContext.getSegmentsExperienceIds());

		if (segmentsExperienceIdOptionalLong.isPresent()) {
			String defaultPreferencesPortletId = portletId;

			if (!portlet.isInstanceable()) {
				instanceId = String.valueOf(CharPool.NUMBER_0);
			}
			else {
				defaultPreferencesPortletId = PortletIdCodec.encode(
					portletId,
					SegmentsExperiencePortletUtil.setSegmentsExperienceId(
						instanceId, SegmentsExperienceConstants.ID_DEFAULT));
			}

			instanceId = SegmentsExperiencePortletUtil.setSegmentsExperienceId(
				instanceId, segmentsExperienceIdOptionalLong.getAsLong());

			String preferencesPortletId = PortletIdCodec.encode(
				portletId, instanceId);

			HttpServletRequest httpServletRequest =
				fragmentEntryProcessorContext.getHttpServletRequest();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletPreferences defaultExperiencePortletPreferences =
				_portletPreferencesLocalService.fetchPreferences(
					themeDisplay.getCompanyId(),
					PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, themeDisplay.getPlid(),
					defaultPreferencesPortletId);

			if (defaultExperiencePortletPreferences == null) {
				defaultExperiencePortletPreferences =
					PortletPreferencesFactoryUtil.fromDefaultXML(
						portlet.getDefaultPreferences());
			}

			portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(
				fragmentEntryProcessorContext.getHttpServletRequest(),
				preferencesPortletId,
				PortletPreferencesFactoryUtil.toXML(
					defaultExperiencePortletPreferences));
		}
		else {
			portletPreferences =
				PortletPreferencesFactoryUtil.getPortletPreferences(
					fragmentEntryProcessorContext.getHttpServletRequest(),
					PortletIdCodec.encode(portletId, instanceId));
		}

		return _fragmentPortletRenderer.renderPortlet(
			fragmentEntryProcessorContext.getHttpServletRequest(),
			fragmentEntryProcessorContext.getHttpServletResponse(), portletId,
			instanceId,
			PortletPreferencesFactoryUtil.toXML(portletPreferences));
	}

	@Reference
	private FragmentPortletRenderer _fragmentPortletRenderer;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private PortletRegistry _portletRegistry;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

}