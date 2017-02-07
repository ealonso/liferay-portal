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

package com.liferay.portal.upgrade.v7_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.upgrade.v7_0_3.util.GroupFriendlyURLTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Locale;

/**
 * @author Pavel Savinov
 */
public class UpgradeGroupFriendlyURL extends UpgradeProcess {

	protected void addFriendlyURL(long companyId, long groupId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"select name, friendlyURL, creatorUserId from Group_ where " +
					"companyId = ? and groupId = ?")) {

			ps.setLong(1, companyId);
			ps.setLong(2, groupId);

			StringBundler insertSql = new StringBundler();

			insertSql.append("insert into GroupFriendlyURL (");
			insertSql.append("groupFriendlyURLId, groupId, companyId, userId,");
			insertSql.append("createDate, friendlyURL, languageId) values (");
			insertSql.append("?,?,?,?,?,?,?)");

			try (ResultSet rs = ps.executeQuery(); PreparedStatement ps2 =
				connection.prepareStatement(insertSql.toString())) {

				if (rs.next()) {
					long creatorUserId = rs.getLong("creatorUserId");
					String friendlyURL = rs.getString("friendlyURL");
					String nameXml = rs.getString("name");

					Locale defaultLocale = LocaleUtil.getSiteDefault();

					String defaultLanguageId =
						LocalizationUtil.getDefaultLanguageId(
							nameXml, defaultLocale);

					ps2.setLong(1, increment());
					ps2.setLong(2, groupId);
					ps2.setLong(3, companyId);
					ps2.setLong(4, creatorUserId);
					ps2.setDate(5, new Date(System.currentTimeMillis()));
					ps2.setString(6, friendlyURL);
					ps2.setString(7, defaultLanguageId);

					ps2.execute();
				}
			}
		}
	}

	protected void checkTable() throws Exception {
		if (doHasTable("GroupFriendlyURL")) {
			return;
		}

		runSQL(GroupFriendlyURLTable.TABLE_SQL_CREATE);
	}

	@Override
	protected void doUpgrade() throws Exception {
		checkTable();

		long[] companyIds = PortalUtil.getCompanyIds();

		for (long companyId : companyIds) {
			upgradeCompanyGroups(companyId);
		}
	}

	protected void upgradeCompanyGroups(long companyId) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select groupId from Group_ where companyId = ?")) {

			ps.setLong(1, companyId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long groupId = rs.getLong(1);

					upgradeGroupFriendlyURLS(companyId, groupId);
				}
			}
		}
	}

	protected void upgradeGroupFriendlyURLS(long companyId, long groupId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"select count(*) from GroupFriendlyURL where companyId = ? " +
					"and groupId = ?")) {

			ps.setLong(1, companyId);
			ps.setLong(2, groupId);

			int count = 0;

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}

			if (count > 0) {
				return;
			}

			addFriendlyURL(companyId, groupId);
		}
	}

}