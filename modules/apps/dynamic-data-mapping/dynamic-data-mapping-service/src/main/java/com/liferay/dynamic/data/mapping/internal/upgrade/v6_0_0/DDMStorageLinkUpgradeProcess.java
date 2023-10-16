/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v6_0_0;

import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;

/**
 * @author Eudaldo Alonso
 */
public class DDMStorageLinkUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update DDMStorageLink set classNameId = ? where classNameId " +
					"= ?")) {

			preparedStatement.setLong(
				1, PortalUtil.getClassNameId(DDMStorageLink.class));
			preparedStatement.setLong(
				2,
				PortalUtil.getClassNameId(
					"com.liferay.dynamic.data.mapping.model.DDMContent"));

			preparedStatement.executeUpdate();
		}
	}

}