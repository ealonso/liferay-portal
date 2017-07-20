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

package com.liferay.asset.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryLocalService
 * @generated
 */
@ProviderType
public class AssetEntryLocalServiceWrapper implements AssetEntryLocalService,
	ServiceWrapper<AssetEntryLocalService> {
	public AssetEntryLocalServiceWrapper(
		AssetEntryLocalService assetEntryLocalService) {
		_assetEntryLocalService = assetEntryLocalService;
	}

	@Override
	public void addAssetCategoryAssetEntries(long categoryId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		_assetEntryLocalService.addAssetCategoryAssetEntries(categoryId,
			assetEntries);
	}

	@Override
	public void addAssetCategoryAssetEntries(long categoryId, long[] entryIds) {
		_assetEntryLocalService.addAssetCategoryAssetEntries(categoryId,
			entryIds);
	}

	@Override
	public void addAssetCategoryAssetEntry(long categoryId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		_assetEntryLocalService.addAssetCategoryAssetEntry(categoryId,
			assetEntry);
	}

	@Override
	public void addAssetCategoryAssetEntry(long categoryId, long entryId) {
		_assetEntryLocalService.addAssetCategoryAssetEntry(categoryId, entryId);
	}

	/**
	* Adds the asset entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was added
	*/
	@Override
	public com.liferay.asset.model.AssetEntry addAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return _assetEntryLocalService.addAssetEntry(assetEntry);
	}

	@Override
	public void addAssetTagAssetEntries(long tagId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		_assetEntryLocalService.addAssetTagAssetEntries(tagId, assetEntries);
	}

	@Override
	public void addAssetTagAssetEntries(long tagId, long[] entryIds) {
		_assetEntryLocalService.addAssetTagAssetEntries(tagId, entryIds);
	}

	@Override
	public void addAssetTagAssetEntry(long tagId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		_assetEntryLocalService.addAssetTagAssetEntry(tagId, assetEntry);
	}

	@Override
	public void addAssetTagAssetEntry(long tagId, long entryId) {
		_assetEntryLocalService.addAssetTagAssetEntry(tagId, entryId);
	}

	@Override
	public void clearAssetCategoryAssetEntries(long categoryId) {
		_assetEntryLocalService.clearAssetCategoryAssetEntries(categoryId);
	}

	@Override
	public void clearAssetTagAssetEntries(long tagId) {
		_assetEntryLocalService.clearAssetTagAssetEntries(tagId);
	}

	/**
	* Creates a new asset entry with the primary key. Does not add the asset entry to the database.
	*
	* @param entryId the primary key for the new asset entry
	* @return the new asset entry
	*/
	@Override
	public com.liferay.asset.model.AssetEntry createAssetEntry(long entryId) {
		return _assetEntryLocalService.createAssetEntry(entryId);
	}

	@Override
	public void deleteAssetCategoryAssetEntries(long categoryId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		_assetEntryLocalService.deleteAssetCategoryAssetEntries(categoryId,
			assetEntries);
	}

	@Override
	public void deleteAssetCategoryAssetEntries(long categoryId, long[] entryIds) {
		_assetEntryLocalService.deleteAssetCategoryAssetEntries(categoryId,
			entryIds);
	}

	@Override
	public void deleteAssetCategoryAssetEntry(long categoryId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		_assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId,
			assetEntry);
	}

	@Override
	public void deleteAssetCategoryAssetEntry(long categoryId, long entryId) {
		_assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId,
			entryId);
	}

	/**
	* Deletes the asset entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was removed
	*/
	@Override
	public com.liferay.asset.model.AssetEntry deleteAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return _assetEntryLocalService.deleteAssetEntry(assetEntry);
	}

	/**
	* Deletes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry that was removed
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetEntry deleteAssetEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryLocalService.deleteAssetEntry(entryId);
	}

	@Override
	public void deleteAssetTagAssetEntries(long tagId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		_assetEntryLocalService.deleteAssetTagAssetEntries(tagId, assetEntries);
	}

	@Override
	public void deleteAssetTagAssetEntries(long tagId, long[] entryIds) {
		_assetEntryLocalService.deleteAssetTagAssetEntries(tagId, entryIds);
	}

	@Override
	public void deleteAssetTagAssetEntry(long tagId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		_assetEntryLocalService.deleteAssetTagAssetEntry(tagId, assetEntry);
	}

	@Override
	public void deleteAssetTagAssetEntry(long tagId, long entryId) {
		_assetEntryLocalService.deleteAssetTagAssetEntry(tagId, entryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _assetEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _assetEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _assetEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _assetEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _assetEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.model.AssetEntry fetchAssetEntry(long entryId) {
		return _assetEntryLocalService.fetchAssetEntry(entryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId) {
		return _assetEntryLocalService.getAssetCategoryAssetEntries(categoryId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end) {
		return _assetEntryLocalService.getAssetCategoryAssetEntries(categoryId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetEntry> orderByComparator) {
		return _assetEntryLocalService.getAssetCategoryAssetEntries(categoryId,
			start, end, orderByComparator);
	}

	@Override
	public int getAssetCategoryAssetEntriesCount(long categoryId) {
		return _assetEntryLocalService.getAssetCategoryAssetEntriesCount(categoryId);
	}

	/**
	* Returns the categoryIds of the asset categories associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the categoryIds of asset categories associated with the asset entry
	*/
	@Override
	public long[] getAssetCategoryPrimaryKeys(long entryId) {
		return _assetEntryLocalService.getAssetCategoryPrimaryKeys(entryId);
	}

	/**
	* Returns a range of all the asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entries
	* @param end the upper bound of the range of asset entries (not inclusive)
	* @return the range of asset entries
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetEntries(
		int start, int end) {
		return _assetEntryLocalService.getAssetEntries(start, end);
	}

	/**
	* Returns the number of asset entries.
	*
	* @return the number of asset entries
	*/
	@Override
	public int getAssetEntriesCount() {
		return _assetEntryLocalService.getAssetEntriesCount();
	}

	/**
	* Returns the asset entry with the primary key.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetEntry getAssetEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryLocalService.getAssetEntry(entryId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId) {
		return _assetEntryLocalService.getAssetTagAssetEntries(tagId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end) {
		return _assetEntryLocalService.getAssetTagAssetEntries(tagId, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetEntry> orderByComparator) {
		return _assetEntryLocalService.getAssetTagAssetEntries(tagId, start,
			end, orderByComparator);
	}

	@Override
	public int getAssetTagAssetEntriesCount(long tagId) {
		return _assetEntryLocalService.getAssetTagAssetEntriesCount(tagId);
	}

	/**
	* Returns the tagIds of the asset tags associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the tagIds of asset tags associated with the asset entry
	*/
	@Override
	public long[] getAssetTagPrimaryKeys(long entryId) {
		return _assetEntryLocalService.getAssetTagPrimaryKeys(entryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasAssetCategoryAssetEntries(long categoryId) {
		return _assetEntryLocalService.hasAssetCategoryAssetEntries(categoryId);
	}

	@Override
	public boolean hasAssetCategoryAssetEntry(long categoryId, long entryId) {
		return _assetEntryLocalService.hasAssetCategoryAssetEntry(categoryId,
			entryId);
	}

	@Override
	public boolean hasAssetTagAssetEntries(long tagId) {
		return _assetEntryLocalService.hasAssetTagAssetEntries(tagId);
	}

	@Override
	public boolean hasAssetTagAssetEntry(long tagId, long entryId) {
		return _assetEntryLocalService.hasAssetTagAssetEntry(tagId, entryId);
	}

	@Override
	public void setAssetCategoryAssetEntries(long categoryId, long[] entryIds) {
		_assetEntryLocalService.setAssetCategoryAssetEntries(categoryId,
			entryIds);
	}

	@Override
	public void setAssetTagAssetEntries(long tagId, long[] entryIds) {
		_assetEntryLocalService.setAssetTagAssetEntries(tagId, entryIds);
	}

	/**
	* Updates the asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was updated
	*/
	@Override
	public com.liferay.asset.model.AssetEntry updateAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return _assetEntryLocalService.updateAssetEntry(assetEntry);
	}

	@Override
	public AssetEntryLocalService getWrappedService() {
		return _assetEntryLocalService;
	}

	@Override
	public void setWrappedService(AssetEntryLocalService assetEntryLocalService) {
		_assetEntryLocalService = assetEntryLocalService;
	}

	private AssetEntryLocalService _assetEntryLocalService;
}