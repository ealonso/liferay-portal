/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.rule;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.test.util.TestPropsValues;

import org.junit.runner.Description;

/**
 * @author Lourdes Fernández Besada
 */
public class PrincipalProviderTestRule extends ClassTestRule<String> {

	public static final PrincipalProviderTestRule INSTANCE =
		new PrincipalProviderTestRule();

	@Override
	protected void afterClass(Description description, String previousName) {
		PrincipalThreadLocal.setName(previousName);
	}

	@Override
	protected String beforeClass(Description description)
		throws PortalException {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(
			String.valueOf(TestPropsValues.getUserId()));

		return name;
	}

	private PrincipalProviderTestRule() {
	}

}