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
 * Provides a wrapper for {@link AssetEntryClassNameRelLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryClassNameRelLocalService
 * @generated
 */
@ProviderType
public class AssetEntryClassNameRelLocalServiceWrapper
	implements AssetEntryClassNameRelLocalService,
		ServiceWrapper<AssetEntryClassNameRelLocalService> {
	public AssetEntryClassNameRelLocalServiceWrapper(
		AssetEntryClassNameRelLocalService assetEntryClassNameRelLocalService) {
		_assetEntryClassNameRelLocalService = assetEntryClassNameRelLocalService;
	}

	/**
	* Adds the asset entry class name rel to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryClassNameRel the asset entry class name rel
	* @return the asset entry class name rel that was added
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel addAssetEntryClassNameRel(
		com.liferay.asset.entry.rel.model.AssetEntryClassNameRel assetEntryClassNameRel) {
		return _assetEntryClassNameRelLocalService.addAssetEntryClassNameRel(assetEntryClassNameRel);
	}

	/**
	* Creates a new asset entry class name rel with the primary key. Does not add the asset entry class name rel to the database.
	*
	* @param assetEntryClassNameRelId the primary key for the new asset entry class name rel
	* @return the new asset entry class name rel
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel createAssetEntryClassNameRel(
		long assetEntryClassNameRelId) {
		return _assetEntryClassNameRelLocalService.createAssetEntryClassNameRel(assetEntryClassNameRelId);
	}

	/**
	* Deletes the asset entry class name rel from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryClassNameRel the asset entry class name rel
	* @return the asset entry class name rel that was removed
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel deleteAssetEntryClassNameRel(
		com.liferay.asset.entry.rel.model.AssetEntryClassNameRel assetEntryClassNameRel) {
		return _assetEntryClassNameRelLocalService.deleteAssetEntryClassNameRel(assetEntryClassNameRel);
	}

	/**
	* Deletes the asset entry class name rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	* @return the asset entry class name rel that was removed
	* @throws PortalException if a asset entry class name rel with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel deleteAssetEntryClassNameRel(
		long assetEntryClassNameRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryClassNameRelLocalService.deleteAssetEntryClassNameRel(assetEntryClassNameRelId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryClassNameRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntryClassNameRelLocalService.dynamicQuery();
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
		return _assetEntryClassNameRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntryClassNameRelLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntryClassNameRelLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _assetEntryClassNameRelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetEntryClassNameRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel fetchAssetEntryClassNameRel(
		long assetEntryClassNameRelId) {
		return _assetEntryClassNameRelLocalService.fetchAssetEntryClassNameRel(assetEntryClassNameRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetEntryClassNameRelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the asset entry class name rel with the primary key.
	*
	* @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	* @return the asset entry class name rel
	* @throws PortalException if a asset entry class name rel with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel getAssetEntryClassNameRel(
		long assetEntryClassNameRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryClassNameRelLocalService.getAssetEntryClassNameRel(assetEntryClassNameRelId);
	}

	/**
	* Returns a range of all the asset entry class name rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @return the range of asset entry class name rels
	*/
	@Override
	public java.util.List<com.liferay.asset.entry.rel.model.AssetEntryClassNameRel> getAssetEntryClassNameRels(
		int start, int end) {
		return _assetEntryClassNameRelLocalService.getAssetEntryClassNameRels(start,
			end);
	}

	/**
	* Returns the number of asset entry class name rels.
	*
	* @return the number of asset entry class name rels
	*/
	@Override
	public int getAssetEntryClassNameRelsCount() {
		return _assetEntryClassNameRelLocalService.getAssetEntryClassNameRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetEntryClassNameRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetEntryClassNameRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetEntryClassNameRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the asset entry class name rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntryClassNameRel the asset entry class name rel
	* @return the asset entry class name rel that was updated
	*/
	@Override
	public com.liferay.asset.entry.rel.model.AssetEntryClassNameRel updateAssetEntryClassNameRel(
		com.liferay.asset.entry.rel.model.AssetEntryClassNameRel assetEntryClassNameRel) {
		return _assetEntryClassNameRelLocalService.updateAssetEntryClassNameRel(assetEntryClassNameRel);
	}

	@Override
	public AssetEntryClassNameRelLocalService getWrappedService() {
		return _assetEntryClassNameRelLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntryClassNameRelLocalService assetEntryClassNameRelLocalService) {
		_assetEntryClassNameRelLocalService = assetEntryClassNameRelLocalService;
	}

	private AssetEntryClassNameRelLocalService _assetEntryClassNameRelLocalService;
}