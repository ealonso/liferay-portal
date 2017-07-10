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

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetTagDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.asset.service.base.AssetTagServiceBaseImpl;

import java.util.List;

/**
 * Provides the remote service for accessing, adding, checking, deleting,
 * merging, and updating asset tags. Its methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Bruno Farache
 * @author Juan Fernández
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.tags.model.impl.AssetTagServiceImpl}
 */
@Deprecated
public class AssetTagServiceImpl extends AssetTagServiceBaseImpl {

	@Override
	public AssetTag addTag(
			long groupId, String name, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public void deleteTag(long tagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public void deleteTags(long[] tagIds) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getGroupsTags(long[] groupIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getGroupTags(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getGroupTags(
		long groupId, int start, int end, OrderByComparator<AssetTag> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public AssetTagDisplay getGroupTagsDisplay(
		long groupId, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public AssetTag getTag(long tagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(long groupId, long classNameId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public int getTagsCount(long groupId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public int getVisibleAssetsTagsCount(long groupId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public void mergeTags(long[] fromTagIds, long toTagId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public JSONArray search(long groupId, String name, int start, int end) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public JSONArray search(long[] groupIds, String name, int start, int end) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public AssetTag updateTag(
			long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

}