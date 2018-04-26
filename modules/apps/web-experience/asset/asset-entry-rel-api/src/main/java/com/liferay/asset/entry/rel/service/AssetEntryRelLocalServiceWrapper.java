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

package com.liferay.asset.entry.rel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntryRelLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRelLocalService
 * @generated
 */
@ProviderType
public class AssetEntryRelLocalServiceWrapper
	implements AssetEntryRelLocalService,
		ServiceWrapper<AssetEntryRelLocalService> {
	public AssetEntryRelLocalServiceWrapper(
		AssetEntryRelLocalService assetEntryRelLocalService) {
		_assetEntryRelLocalService = assetEntryRelLocalService;
	}

	/**
	* Adds the asset entry rel to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was added
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel addAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return _assetEntryRelLocalService.addAssetEntryRel(assetEntryRel);
	}

	/**
	* Creates a new asset entry rel with the primary key. Does not add the asset entry rel to the database.
	*
	* @param assetEntryRelId the primary key for the new asset entry rel
	* @return the new asset entry rel
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel createAssetEntryRel(
		long assetEntryRelId) {
		return _assetEntryRelLocalService.createAssetEntryRel(assetEntryRelId);
	}

	/**
	* Deletes the asset entry rel from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was removed
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel deleteAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return _assetEntryRelLocalService.deleteAssetEntryRel(assetEntryRel);
	}

	/**
	* Deletes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel that was removed
	* @throws PortalException if a asset entry rel with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel deleteAssetEntryRel(
		long assetEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryRelLocalService.deleteAssetEntryRel(assetEntryRelId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntryRelLocalService.dynamicQuery();
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
		return _assetEntryRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntryRelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntryRelLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _assetEntryRelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetEntryRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel fetchAssetEntryRel(
		long assetEntryRelId) {
		return _assetEntryRelLocalService.fetchAssetEntryRel(assetEntryRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetEntryRelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the asset entry rel with the primary key.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel
	* @throws PortalException if a asset entry rel with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel getAssetEntryRel(
		long assetEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryRelLocalService.getAssetEntryRel(assetEntryRelId);
	}

	/**
	* Returns a range of all the asset entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @return the range of asset entry rels
	*/
	@Override
	public java.util.List<com.liferay.asset.entry.rel.model.AssetEntryRel> getAssetEntryRels(
		int start, int end) {
		return _assetEntryRelLocalService.getAssetEntryRels(start, end);
	}

	/**
	* Returns the number of asset entry rels.
	*
	* @return the number of asset entry rels
	*/
	@Override
	public int getAssetEntryRelsCount() {
		return _assetEntryRelLocalService.getAssetEntryRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetEntryRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetEntryRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the asset entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was updated
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryRel updateAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return _assetEntryRelLocalService.updateAssetEntryRel(assetEntryRel);
	}

	@Override
	public AssetEntryRelLocalService getWrappedService() {
		return _assetEntryRelLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntryRelLocalService assetEntryRelLocalService) {
		_assetEntryRelLocalService = assetEntryRelLocalService;
	}

	private AssetEntryRelLocalService _assetEntryRelLocalService;
}