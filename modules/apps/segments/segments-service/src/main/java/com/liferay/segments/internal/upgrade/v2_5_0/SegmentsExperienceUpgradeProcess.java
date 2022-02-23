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

package com.liferay.segments.internal.upgrade.v2_5_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class SegmentsExperienceUpgradeProcess extends UpgradeProcess {

	public SegmentsExperienceUpgradeProcess(
		ClassNameLocalService classNameLocalService,
		CounterLocalService counterLocalService,
		ResourceLocalService resourceLocalService) {

		_classNameLocalService = classNameLocalService;
		_counterLocalService = counterLocalService;
		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addDefaultSegmentsExperience();
	}

	private void _addDefaultSegmentsExperience() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select companyId, groupId, plid, userId, userName from " +
					"Layout where classPK > 0 and classNameId = ? and (type_ " +
						"= ? or type_ = ? or type_ = ?)");
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"insert into SegmentsExperience(uuid_,",
						"segmentsExperienceId, groupId, companyId, userId, ",
						"userName, createDate, modifiedDate, segmentsEntryId, ",
						"segmentsExperienceKey, classNameId, classPK, name, ",
						"priority, active_) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ",
						"?, ?, ?, ?, ?, ?)"));
			PreparedStatement preparedStatement3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update SegmentsExperiment set segmentsExperienceId = ",
						"?, segmentsExperienceKey = ? where ",
						"segmentsExperienceId = 0 and classNameId = ? and ",
						"classPK = ?"));
			PreparedStatement preparedStatement4 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update SegmentsExperimentRel set segmentsExperienceId",
						"= ? where segmentsExperienceId = 0 and ",
						"segmentsExperimentId = (select ",
						"SegmentsExperiment.segmentsExperimentId from",
						"SegmentsExperiment where classNameId = ? and classPK ",
						"= ?)"));
			PreparedStatement preparedStatement5 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update FragmentEntryLink set segmentsExperienceId = ?" +
						"where segmentsExperienceId = 0 and plid = ?");
			PreparedStatement preparedStatement6 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update LayoutPageTemplateStructureRel set",
						"segmentsExperienceId = ? where segmentsExperienceId ",
						"= 0 and layoutPageTemplateStructureId = (select ",
						"LayoutPageTemplateStructure.",
						"layoutPageTemplateStructureId from ",
						"LayoutPageTemplateStructure where plid = ?)"))) {

			long classNameId = _classNameLocalService.getClassNameId(
				Layout.class);

			preparedStatement1.setLong(1, classNameId);

			preparedStatement1.setString(2, LayoutConstants.TYPE_ASSET_DISPLAY);
			preparedStatement1.setString(3, LayoutConstants.TYPE_COLLECTION);
			preparedStatement1.setString(4, LayoutConstants.TYPE_CONTENT);

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				while (resultSet.next()) {
					long companyId = resultSet.getLong("companyId");
					long groupId = resultSet.getLong("groupId");
					long plid = resultSet.getLong("plid");
					long userId = resultSet.getLong("userId");
					String userName = resultSet.getString("userName");

					Timestamp now = new Timestamp(System.currentTimeMillis());

					preparedStatement2.setString(1, PortalUUIDUtil.generate());

					long segmentExperienceId = _counterLocalService.increment();

					preparedStatement2.setLong(2, segmentExperienceId);

					preparedStatement2.setLong(3, groupId);
					preparedStatement2.setLong(4, companyId);
					preparedStatement2.setLong(5, userId);
					preparedStatement2.setString(6, userName);
					preparedStatement2.setTimestamp(7, now);
					preparedStatement2.setTimestamp(8, now);
					preparedStatement2.setLong(
						9, SegmentsEntryConstants.ID_DEFAULT);
					preparedStatement2.setString(
						10, SegmentsExperienceConstants.KEY_DEFAULT);
					preparedStatement2.setLong(11, classNameId);
					preparedStatement2.setLong(12, plid);
					preparedStatement2.setString(
						13,
						LanguageUtil.get(
							_getDefaultLocale(groupId),
							"default-experience-name"));
					preparedStatement2.setInt(14, 0);
					preparedStatement2.setBoolean(15, true);

					preparedStatement2.addBatch();

					preparedStatement3.setLong(1, segmentExperienceId);
					preparedStatement3.setString(
						2, SegmentsExperienceConstants.KEY_DEFAULT);
					preparedStatement3.setLong(3, classNameId);
					preparedStatement3.setLong(4, plid);

					preparedStatement3.addBatch();

					preparedStatement4.setLong(1, segmentExperienceId);
					preparedStatement4.setLong(2, classNameId);
					preparedStatement4.setLong(3, plid);

					preparedStatement4.addBatch();

					preparedStatement5.setLong(1, segmentExperienceId);
					preparedStatement5.setLong(2, plid);

					preparedStatement5.addBatch();

					preparedStatement6.setLong(1, segmentExperienceId);
					preparedStatement6.setLong(2, plid);

					preparedStatement6.addBatch();
				}
			}

			preparedStatement2.executeBatch();
			preparedStatement3.executeBatch();
			preparedStatement4.executeBatch();
			preparedStatement5.executeBatch();
			preparedStatement6.executeBatch();
		}
	}

	private Locale _getDefaultLocale(long groupId) throws Exception {
		Locale defaultLocale = _defaultLanguageIds.get(groupId);

		if (defaultLocale == null) {
			_defaultLanguageIds.put(
				groupId, PortalUtil.getSiteDefaultLocale(groupId));
		}

		return defaultLocale;
	}

	private final ClassNameLocalService _classNameLocalService;
	private final CounterLocalService _counterLocalService;
	private final Map<Long, Locale> _defaultLanguageIds = new HashMap<>();
	private final ResourceLocalService _resourceLocalService;

}