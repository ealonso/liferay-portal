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
 * Provides a wrapper for {@link AssetTagLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagLocalService
 * @generated
 */
@ProviderType
public class AssetTagLocalServiceWrapper implements AssetTagLocalService,
	ServiceWrapper<AssetTagLocalService> {
	public AssetTagLocalServiceWrapper(
		AssetTagLocalService assetTagLocalService) {
		_assetTagLocalService = assetTagLocalService;
	}

	@Override
	public void addAssetEntryAssetTag(long entryId,
		com.liferay.asset.model.AssetTag assetTag) {
		_assetTagLocalService.addAssetEntryAssetTag(entryId, assetTag);
	}

	@Override
	public void addAssetEntryAssetTag(long entryId, long tagId) {
		_assetTagLocalService.addAssetEntryAssetTag(entryId, tagId);
	}

	@Override
	public void addAssetEntryAssetTags(long entryId,
		java.util.List<com.liferay.asset.model.AssetTag> assetTags) {
		_assetTagLocalService.addAssetEntryAssetTags(entryId, assetTags);
	}

	@Override
	public void addAssetEntryAssetTags(long entryId, long[] tagIds) {
		_assetTagLocalService.addAssetEntryAssetTags(entryId, tagIds);
	}

	/**
	* Adds the asset tag to the database. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @return the asset tag that was added
	*/
	@Override
	public com.liferay.asset.model.AssetTag addAssetTag(
		com.liferay.asset.model.AssetTag assetTag) {
		return _assetTagLocalService.addAssetTag(assetTag);
	}

	@Override
	public void clearAssetEntryAssetTags(long entryId) {
		_assetTagLocalService.clearAssetEntryAssetTags(entryId);
	}

	/**
	* Creates a new asset tag with the primary key. Does not add the asset tag to the database.
	*
	* @param tagId the primary key for the new asset tag
	* @return the new asset tag
	*/
	@Override
	public com.liferay.asset.model.AssetTag createAssetTag(long tagId) {
		return _assetTagLocalService.createAssetTag(tagId);
	}

	@Override
	public void deleteAssetEntryAssetTag(long entryId,
		com.liferay.asset.model.AssetTag assetTag) {
		_assetTagLocalService.deleteAssetEntryAssetTag(entryId, assetTag);
	}

	@Override
	public void deleteAssetEntryAssetTag(long entryId, long tagId) {
		_assetTagLocalService.deleteAssetEntryAssetTag(entryId, tagId);
	}

	@Override
	public void deleteAssetEntryAssetTags(long entryId,
		java.util.List<com.liferay.asset.model.AssetTag> assetTags) {
		_assetTagLocalService.deleteAssetEntryAssetTags(entryId, assetTags);
	}

	@Override
	public void deleteAssetEntryAssetTags(long entryId, long[] tagIds) {
		_assetTagLocalService.deleteAssetEntryAssetTags(entryId, tagIds);
	}

	/**
	* Deletes the asset tag from the database. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @return the asset tag that was removed
	*/
	@Override
	public com.liferay.asset.model.AssetTag deleteAssetTag(
		com.liferay.asset.model.AssetTag assetTag) {
		return _assetTagLocalService.deleteAssetTag(assetTag);
	}

	/**
	* Deletes the asset tag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tagId the primary key of the asset tag
	* @return the asset tag that was removed
	* @throws PortalException if a asset tag with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetTag deleteAssetTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagLocalService.deleteAssetTag(tagId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetTagLocalService.dynamicQuery();
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
		return _assetTagLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetTagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetTagLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetTagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetTagLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _assetTagLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetTagLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.asset.model.AssetTag fetchAssetTag(long tagId) {
		return _assetTagLocalService.fetchAssetTag(tagId);
	}

	/**
	* Returns the asset tag matching the UUID and group.
	*
	* @param uuid the asset tag's UUID
	* @param groupId the primary key of the group
	* @return the matching asset tag, or <code>null</code> if a matching asset tag could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetTag fetchAssetTagByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _assetTagLocalService.fetchAssetTagByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetTagLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetEntryAssetTags(
		long entryId) {
		return _assetTagLocalService.getAssetEntryAssetTags(entryId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetEntryAssetTags(
		long entryId, int start, int end) {
		return _assetTagLocalService.getAssetEntryAssetTags(entryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetEntryAssetTags(
		long entryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetTag> orderByComparator) {
		return _assetTagLocalService.getAssetEntryAssetTags(entryId, start,
			end, orderByComparator);
	}

	@Override
	public int getAssetEntryAssetTagsCount(long entryId) {
		return _assetTagLocalService.getAssetEntryAssetTagsCount(entryId);
	}

	/**
	* Returns the entryIds of the asset entries associated with the asset tag.
	*
	* @param tagId the tagId of the asset tag
	* @return long[] the entryIds of asset entries associated with the asset tag
	*/
	@Override
	public long[] getAssetEntryPrimaryKeys(long tagId) {
		return _assetTagLocalService.getAssetEntryPrimaryKeys(tagId);
	}

	/**
	* Returns the asset tag with the primary key.
	*
	* @param tagId the primary key of the asset tag
	* @return the asset tag
	* @throws PortalException if a asset tag with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetTag getAssetTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagLocalService.getAssetTag(tagId);
	}

	/**
	* Returns the asset tag matching the UUID and group.
	*
	* @param uuid the asset tag's UUID
	* @param groupId the primary key of the group
	* @return the matching asset tag
	* @throws PortalException if a matching asset tag could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetTag getAssetTagByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagLocalService.getAssetTagByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the asset tags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetTagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset tags
	* @param end the upper bound of the range of asset tags (not inclusive)
	* @return the range of asset tags
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetTags(
		int start, int end) {
		return _assetTagLocalService.getAssetTags(start, end);
	}

	/**
	* Returns all the asset tags matching the UUID and company.
	*
	* @param uuid the UUID of the asset tags
	* @param companyId the primary key of the company
	* @return the matching asset tags, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetTagsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _assetTagLocalService.getAssetTagsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of asset tags matching the UUID and company.
	*
	* @param uuid the UUID of the asset tags
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of asset tags
	* @param end the upper bound of the range of asset tags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching asset tags, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetTag> getAssetTagsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetTag> orderByComparator) {
		return _assetTagLocalService.getAssetTagsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of asset tags.
	*
	* @return the number of asset tags
	*/
	@Override
	public int getAssetTagsCount() {
		return _assetTagLocalService.getAssetTagsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _assetTagLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetTagLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetTagLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasAssetEntryAssetTag(long entryId, long tagId) {
		return _assetTagLocalService.hasAssetEntryAssetTag(entryId, tagId);
	}

	@Override
	public boolean hasAssetEntryAssetTags(long entryId) {
		return _assetTagLocalService.hasAssetEntryAssetTags(entryId);
	}

	@Override
	public void setAssetEntryAssetTags(long entryId, long[] tagIds) {
		_assetTagLocalService.setAssetEntryAssetTags(entryId, tagIds);
	}

	/**
	* Updates the asset tag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @return the asset tag that was updated
	*/
	@Override
	public com.liferay.asset.model.AssetTag updateAssetTag(
		com.liferay.asset.model.AssetTag assetTag) {
		return _assetTagLocalService.updateAssetTag(assetTag);
	}

	@Override
	public AssetTagLocalService getWrappedService() {
		return _assetTagLocalService;
	}

	@Override
	public void setWrappedService(AssetTagLocalService assetTagLocalService) {
		_assetTagLocalService = assetTagLocalService;
	}

	private AssetTagLocalService _assetTagLocalService;
}