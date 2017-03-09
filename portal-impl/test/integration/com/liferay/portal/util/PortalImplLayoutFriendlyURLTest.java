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

package com.liferay.portal.util;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.model.impl.VirtualLayout;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.VirtualHostLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Locale;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael Bowerman
 */
public class PortalImplLayoutFriendlyURLTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_defaultLocale = LocaleUtil.getDefault();

		LocaleUtil.setDefault(
			LocaleUtil.US.getLanguage(), LocaleUtil.US.getCountry(),
			LocaleUtil.US.getVariant());
	}

	@AfterClass
	public static void tearDownClass() {
		LocaleUtil.setDefault(
			_defaultLocale.getLanguage(), _defaultLocale.getCountry(),
			_defaultLocale.getVariant());
	}

	@Before
	public void setUp() throws Exception {
		_company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		setVirtualHost("localhost");

		_group = GroupTestUtil.addGroup();

		_privateLayoutSet = _group.getPrivateLayoutSet();

		VirtualHost privateVirtualHost =
			VirtualHostLocalServiceUtil.updateVirtualHost(
				_privateLayoutSet.getCompanyId(),
				_privateLayoutSet.getLayoutSetId(), StringUtil.randomString());

		_privateHostName = privateVirtualHost.getHostname();

		long controlPanelPlid = PortalUtil.getControlPanelPlid(
			_company.getCompanyId());

		_controlPanelLayout = LayoutLocalServiceUtil.getLayout(
			controlPanelPlid);

		_groupControlPanelLayout = new VirtualLayout(
			_controlPanelLayout, _group);
	}

	@Test
	public void testCustomPortalLocaleCanonicalURLFirstLayout()
		throws Exception {

		ThemeDisplay themeDisplay = getThemeDisplay(_group);

		themeDisplay.setPortalDomain(_privateHostName + ":8080");
		themeDisplay.setPortalURL("http://" + _privateHostName + ":8080");

		String layoutFriendlyURL = PortalUtil.getLayoutFriendlyURL(
			_groupControlPanelLayout, themeDisplay);

		StringBundler sb = new StringBundler(5);

		sb.append("http://");
		sb.append(_privateHostName);
		sb.append(":8080/group");
		sb.append(_group.getFriendlyURL());
		sb.append("/~/control_panel/manage");

		Assert.assertEquals(sb.toString(), layoutFriendlyURL);
	}

	protected ThemeDisplay getThemeDisplay(Group group) throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(_company);

		themeDisplay.setLayoutSet(_privateLayoutSet);
		themeDisplay.setServerPort(80);
		themeDisplay.setServerName(_privateHostName);
		themeDisplay.setSiteGroupId(group.getGroupId());

		themeDisplay.setLocale(_defaultLocale);

		return themeDisplay;
	}

	protected void setVirtualHost(String virtualHostname) throws Exception {
		if (Validator.isNull(virtualHostname)) {
			return;
		}

		CompanyLocalServiceUtil.updateCompany(
			_company.getCompanyId(), virtualHostname, _company.getMx(),
			_company.getMaxUsers(), _company.isActive());
	}

	private static Locale _defaultLocale;

	private Company _company;
	private Layout _controlPanelLayout;

	@DeleteAfterTestRun
	private Group _group;

	private Layout _groupControlPanelLayout;
	private String _privateHostName;
	private LayoutSet _privateLayoutSet;

}