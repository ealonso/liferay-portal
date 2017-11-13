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

package com.liferay.portlet.asset.service.impl;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.asset.service.base.AssetVocabularyServiceBaseImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, and updating
 * asset vocabularies. Its methods include permission checks.
 *
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Jorge Ferrer
 * @author Juan Fernández
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.categories.service.impl.AssetVocabularyServiceImpl}
 */
@Deprecated
public class AssetVocabularyServiceImpl extends AssetVocabularyServiceBaseImpl {

	@Override
	public AssetVocabulary addVocabulary(
			long groupId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabulary addVocabulary(
			long groupId, String title, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> deleteVocabularies(
			long[] vocabularyIds, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public void deleteVocabulary(long vocabularyId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabulary fetchVocabulary(long vocabularyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public List<AssetVocabulary> getCompanyVocabularies(long companyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(long[] groupIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className, long classTypePK) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long groupId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, boolean createDefaultVocabulary)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, boolean createDefaultVocabulary, int start, int end,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
		long groupId, int start, int end,
		OrderByComparator<AssetVocabulary> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long[] groupIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public int getGroupVocabulariesCount(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public int getGroupVocabulariesCount(long groupId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public int getGroupVocabulariesCount(long[] groupIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabularyDisplay getGroupVocabulariesDisplay(
			long groupId, String name, int start, int end,
			boolean addDefaultVocabulary,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabularyDisplay getGroupVocabulariesDisplay(
			long groupId, String name, int start, int end,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             AssetUtil#filterVocabularyIds(PermissionChecker, long[])}
	 */
	@Deprecated
	@Override
	public List<AssetVocabulary> getVocabularies(long[] vocabularyIds)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabulary getVocabulary(long vocabularyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabularyDisplay searchVocabulariesDisplay(
			long groupId, String title, boolean addDefaultVocabulary, int start,
			int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabularyDisplay searchVocabulariesDisplay(
			long groupId, String title, boolean addDefaultVocabulary, int start,
			int end, Sort sort)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	@Override
	public AssetVocabulary updateVocabulary(
			long vocabularyId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected List<AssetVocabulary> filterVocabularies(
			List<AssetVocabulary> vocabularies)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetVocabularyServiceImpl");
	}

}