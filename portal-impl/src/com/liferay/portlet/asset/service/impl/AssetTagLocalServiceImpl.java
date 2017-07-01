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
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portlet.asset.service.base.AssetTagLocalServiceBaseImpl;

import java.util.List;

/**
 * Provides the local service for accessing, adding, checking, deleting,
 * merging, and updating asset tags.
 *
 * @author Brian Wing Shun Chan
 * @author Alvaro del Castillo
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.tags.model.impl.AssetTagLocalServiceImpl}
 */
@Deprecated
public class AssetTagLocalServiceImpl extends AssetTagLocalServiceBaseImpl {

	/**
	 * Adds an asset tag.
	 *
	 * @param  userId the primary key of the user adding the asset tag
	 * @param  groupId the primary key of the group in which the asset tag is to
	 *         be added
	 * @param  name the asset tag's name
	 * @param  serviceContext the service context to be applied
	 * @return the asset tag that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTag addTag(
			long userId, long groupId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags matching the group and names, creating new asset
	 * tags matching the names if the group doesn't already have them.
	 *
	 * <p>
	 * For each name, if an asset tag with the name doesn't already exist in the
	 * group, this method creates a new asset tag with the name in the group.
	 * </p>
	 *
	 * @param  userId the primary key of the user checking the asset tags
	 * @param  group the group in which to check the asset tags
	 * @param  names the asset tag names
	 * @return the asset tags matching the group and names and new asset tags
	 *         matching the names that don't already exist in the group
	 */
	@Override
	public List<AssetTag> checkTags(long userId, Group group, String[] names)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags matching the group and names, creating new asset
	 * tags matching the names if the group doesn't already have them.
	 *
	 * @param  userId the primary key of the user checking the asset tags
	 * @param  groupId the primary key of the group in which check the asset
	 *         tags
	 * @param  names the asset tag names
	 * @return the asset tags matching the group and names and new asset tags
	 *         matching the names that don't already exist in the group
	 */
	@Override
	public List<AssetTag> checkTags(long userId, long groupId, String[] names)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Decrements the number of assets to which the asset tag has been applied.
	 *
	 * @param  tagId the primary key of the asset tag
	 * @param  classNameId the class name ID of the entity to which the asset
	 *         tag had been applied
	 * @return the asset tag
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTag decrementAssetCount(long tagId, long classNameId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Deletes all asset tags in the group.
	 *
	 * @param groupId the primary key of the group in which to delete all asset
	 *        tags
	 */
	@Override
	public void deleteGroupTags(long groupId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Deletes the asset tag.
	 *
	 * @param tag the asset tag to be deleted
	 */
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteTag(AssetTag tag) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Deletes the asset tag.
	 *
	 * @param tagId the primary key of the asset tag
	 */
	@Override
	public void deleteTag(long tagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tag with the name in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  name the asset tag's name
	 * @return the asset tag with the name in the group or <code>null</code> if
	 *         it could not be found
	 */
	@Override
	public AssetTag fetchTag(long groupId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags of the asset entry.
	 *
	 * @param  entryId the primary key of the asset entry
	 * @return the asset tags of the asset entry
	 */
	@Override
	public List<AssetTag> getEntryTags(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags in the groups.
	 *
	 * @param  groupIds the primary keys of the groups
	 * @return the asset tags in the groups
	 */
	@Override
	public List<AssetTag> getGroupsTags(long[] groupIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @return the asset tags in the group
	 */
	@Override
	public List<AssetTag> getGroupTags(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns a range of all the asset tags in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  start the lower bound of the range of asset tags
	 * @param  end the upper bound of the range of asset tags (not inclusive)
	 * @return the range of matching asset tags
	 */
	@Override
	public List<AssetTag> getGroupTags(long groupId, int start, int end) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the number of asset tags in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @return the number of asset tags in the group
	 */
	@Override
	public int getGroupTagsCount(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public List<AssetTag> getSocialActivityCounterOffsetTags(
		long groupId, String socialActivityCounterName, int startOffset,
		int endOffset) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public List<AssetTag> getSocialActivityCounterPeriodTags(
		long groupId, String socialActivityCounterName, int startPeriod,
		int endPeriod) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tag with the primary key.
	 *
	 * @param  tagId the primary key of the asset tag
	 * @return the asset tag with the primary key
	 */
	@Override
	public AssetTag getTag(long tagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tag with the name in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  name the name of the asset tag
	 * @return the asset tag with the name in the group
	 */
	@Override
	public AssetTag getTag(long groupId, String name) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the primary keys of the asset tags with the names in the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  names the names of the asset tags
	 * @return the primary keys of the asset tags with the names in the group
	 */
	@Override
	public long[] getTagIds(long groupId, String[] names) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the primary keys of the asset tags with the name in the groups.
	 *
	 * @param  groupIds the primary keys of the groups
	 * @param  name the name of the asset tags
	 * @return the primary keys of the asset tags with the name in the groups
	 */
	@Override
	public long[] getTagIds(long[] groupIds, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the primary keys of the asset tags with the names in the groups.
	 *
	 * @param  groupIds the primary keys of the groups
	 * @param  names the names of the asset tags
	 * @return the primary keys of the asset tags with the names in the groups
	 */
	@Override
	public long[] getTagIds(long[] groupIds, String[] names) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the names of all the asset tags.
	 *
	 * @return the names of all the asset tags
	 */
	@Override
	public String[] getTagNames() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the names of the asset tags of the entity.
	 *
	 * @param  classNameId the class name ID of the entity
	 * @param  classPK the primary key of the entity
	 * @return the names of the asset tags of the entity
	 */
	@Override
	public String[] getTagNames(long classNameId, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the names of the asset tags of the entity
	 *
	 * @param  className the class name of the entity
	 * @param  classPK the primary key of the entity
	 * @return the names of the asset tags of the entity
	 */
	@Override
	public String[] getTagNames(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns all the asset tags.
	 *
	 * @return the asset tags
	 */
	@Override
	public List<AssetTag> getTags() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags of the entity.
	 *
	 * @param  classNameId the class name ID of the entity
	 * @param  classPK the primary key of the entity
	 * @return the asset tags of the entity
	 */
	@Override
	public List<AssetTag> getTags(long classNameId, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(long groupId, long classNameId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags of the entity.
	 *
	 * @param  className the class name of the entity
	 * @param  classPK the primary key of the entity
	 * @return the asset tags of the entity
	 */
	@Override
	@ThreadLocalCachable
	public List<AssetTag> getTags(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public int getTagsSize(long groupId, long classNameId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns <code>true</code> if the group contains an asset tag with the
	 * name.
	 *
	 * @param  groupId the primary key of the group
	 * @param  name the name of the asset tag
	 * @return <code>true</code> if the group contains an asset tag with the
	 *         name; <code>false</code> otherwise.
	 */
	@Override
	public boolean hasTag(long groupId, String name) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Increments the number of assets to which the asset tag has been applied.
	 *
	 * @param  tagId the primary key of the asset tag
	 * @param  classNameId the class name ID of the entity to which the asset
	 *         tag is being applied
	 * @return the asset tag
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTag incrementAssetCount(long tagId, long classNameId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Replaces all occurrences of the first asset tag with the second asset tag
	 * and deletes the first asset tag.
	 *
	 * @param fromTagId the primary key of the asset tag to be replaced
	 * @param toTagId the primary key of the asset tag to apply to the asset
	 *        entries of the other asset tag
	 */
	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags in the group whose names match the pattern.
	 *
	 * @param  groupId the primary key of the group
	 * @param  name the pattern to match
	 * @param  start the lower bound of the range of asset tags
	 * @param  end the upper bound of the range of asset tags (not inclusive)
	 * @return the asset tags in the group whose names match the pattern
	 */
	@Override
	public List<AssetTag> search(
		long groupId, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	/**
	 * Returns the asset tags in the groups whose names match the pattern.
	 *
	 * @param  groupIds the primary keys of the groups
	 * @param  name the pattern to match
	 * @param  start the lower bound of the range of asset tags
	 * @param  end the upper bound of the range of asset tags (not inclusive)
	 * @return the asset tags in the groups whose names match the pattern
	 */
	@Override
	public List<AssetTag> search(
		long[] groupIds, String name, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Override
	public BaseModelSearchResult<AssetTag> searchTags(
			long[] groupIds, String name, int start, int end, Sort sort)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTag updateTag(
			long userId, long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	protected SearchContext buildSearchContext(
		long companyId, long[] groupIds, String name, int start, int end,
		Sort sort) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	protected String[] getTagNames(List<AssetTag> tags) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	protected List<AssetTag> getTags(Hits hits) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	protected BaseModelSearchResult<AssetTag> searchTags(
			SearchContext searchContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

	protected void validate(String name) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagLocalServiceImpl");
	}

}