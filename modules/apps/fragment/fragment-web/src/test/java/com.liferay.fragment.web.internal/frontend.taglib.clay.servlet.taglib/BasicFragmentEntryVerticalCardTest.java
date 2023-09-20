/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.frontend.taglib.clay.servlet.taglib;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Diego Hu
 */
public class BasicFragmentEntryVerticalCardTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_setUpLanguageUtil();
		_setUpPortalUtil();
	}

	@Test
	public void testGetFragmentEntryWarningLabel() {
		RenderRequest renderRequest = Mockito.mock(RenderRequest.class);

		RenderResponse renderResponse = Mockito.mock(RenderResponse.class);

		RowChecker rowChecker = Mockito.mock(RowChecker.class);

		HttpServletRequest httpServletRequest = Mockito.mock(
			HttpServletRequest.class);

		Mockito.when(
			PortalUtil.getHttpServletRequest(renderRequest)
		).thenReturn(
			httpServletRequest
		);

		Mockito.when(
			_fragmentEntry.getStatus()
		).thenReturn(
			WorkflowConstants.STATUS_DRAFT
		);

		Mockito.when(
			LanguageUtil.get(_httpServletRequest, "warnings")
		).thenReturn(
			"warnings"
		);

		_basicFragmentEntryVerticalCard = new BasicFragmentEntryVerticalCard(
			_fragmentEntry, renderRequest, renderResponse, rowChecker);

		List<LabelItem> labels = _basicFragmentEntryVerticalCard.getLabels();
	}

	private void _setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(Mockito.mock(Language.class));
	}

	private void _setUpPortalUtil() {
		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(_portal);
	}

	private BasicFragmentEntryVerticalCard _basicFragmentEntryVerticalCard;
	private final FragmentEntry _fragmentEntry = Mockito.mock(
		FragmentEntry.class);
	private final HttpServletRequest _httpServletRequest = Mockito.mock(
		HttpServletRequest.class);
	private final Portal _portal = Mockito.mock(Portal.class);

}