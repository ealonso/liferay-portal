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

package com.liferay.journal.web.internal.portlet.action.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.data.engine.rest.resource.exception.DataDefinitionValidationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.InputStream;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Rodrigo Paulino
 */
@RunWith(Arquillian.class)
public class ImportDataDefinitionMVCActionCommandTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test(expected = NullPointerException.class)
	public void testProcessActionWithInvalidDataDefinition() throws Exception {
		ReflectionTestUtil.invoke(
			_mvcActionCommand, "importDataDefinition",
			new Class<?>[] {String.class, String.class, ThemeDisplay.class},
			_read("invalid_data_definition.json"), "Imported Structure",
			_getThemeDisplay());
	}

	@Test(expected = DataDefinitionValidationException.MustSetValidName.class)
	public void testProcessActionWithoutName() throws Exception {
		ReflectionTestUtil.invoke(
			_mvcActionCommand, "importDataDefinition",
			new Class<?>[] {String.class, String.class, ThemeDisplay.class},
			_read("valid_data_definition.json"), null, _getThemeDisplay());
	}

	@Test
	public void testProcessActionWithValidDataDefinitionAndName()
		throws Exception {

		ReflectionTestUtil.invoke(
			_mvcActionCommand, "importDataDefinition",
			new Class<?>[] {String.class, String.class, ThemeDisplay.class},
			_read("valid_data_definition.json"), "Imported Structure",
			_getThemeDisplay());
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setScopeGroupId(TestPropsValues.getGroupId());
		themeDisplay.setSiteDefaultLocale(LocaleUtil.US);
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	private String _read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	@Inject(filter = "mvc.command.name=/journal/import_data_definition")
	private MVCActionCommand _mvcActionCommand;

}