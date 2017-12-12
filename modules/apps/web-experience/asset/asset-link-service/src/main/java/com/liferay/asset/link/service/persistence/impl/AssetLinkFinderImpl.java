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

package com.liferay.asset.link.service.persistence.impl;

import com.liferay.asset.link.model.AssetLink;
import com.liferay.asset.link.model.impl.AssetLinkImpl;
import com.liferay.asset.link.service.persistence.AssetLinkFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class AssetLinkFinderImpl
	extends AssetLinkFinderBaseImpl implements AssetLinkFinder {

	public static final String FIND_BY_G_C =
		AssetLinkFinder.class.getName() + ".findByG_C";

	@Override
	public List<AssetLink> findByAssetEntryGroupId(
		long groupId, int start, int end) {

		return findByG_C(groupId, null, null, start, end);
	}

	@Override
	public List<AssetLink> findByG_C(
		long groupId, Date startDate, Date endDate, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_G_C);

			sql = StringUtil.replace(
				sql, "[$CREATE_DATE_COMPARATOR$]",
				_getCreateDateComparator(startDate, endDate));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("AssetLink", AssetLinkImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(groupId);

			if (startDate != null) {
				qPos.add(startDate);
			}

			if (endDate != null) {
				qPos.add(endDate);
			}

			return (List<AssetLink>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private String _getCreateDateComparator(Date startDate, Date endDate) {
		if ((startDate == null) && (endDate == null)) {
			return StringPool.BLANK;
		}

		String createDateComparator = StringPool.BLANK;

		if (startDate != null) {
			createDateComparator = " AND (AssetLink.createDate > ?)";
		}

		if (endDate != null) {
			createDateComparator =
				createDateComparator + " AND (AssetLink.createDate < ?)";
		}

		return createDateComparator;
	}

}