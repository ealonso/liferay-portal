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
 * Provides a wrapper for {@link AssetCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryLocalService
 * @generated
 */
@ProviderType
public class AssetCategoryLocalServiceWrapper
	implements AssetCategoryLocalService,
		ServiceWrapper<AssetCategoryLocalService> {
	public AssetCategoryLocalServiceWrapper(
		AssetCategoryLocalService assetCategoryLocalService) {
		_assetCategoryLocalService = assetCategoryLocalService;
	}

	/**
	* Adds the asset category to the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was added
	*/
	@Override
	public com.liferay.asset.model.AssetCategory addAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return _assetCategoryLocalService.addAssetCategory(assetCategory);
	}

	@Override
	public void addAssetEntryAssetCategories(long entryId,
		java.util.List<com.liferay.asset.model.AssetCategory> assetCategories) {
		_assetCategoryLocalService.addAssetEntryAssetCategories(entryId,
			assetCategories);
	}

	@Override
	public void addAssetEntryAssetCategories(long entryId, long[] categoryIds) {
		_assetCategoryLocalService.addAssetEntryAssetCategories(entryId,
			categoryIds);
	}

	@Override
	public void addAssetEntryAssetCategory(long entryId,
		com.liferay.asset.model.AssetCategory assetCategory) {
		_assetCategoryLocalService.addAssetEntryAssetCategory(entryId,
			assetCategory);
	}

	@Override
	public void addAssetEntryAssetCategory(long entryId, long categoryId) {
		_assetCategoryLocalService.addAssetEntryAssetCategory(entryId,
			categoryId);
	}

	@Override
	public void clearAssetEntryAssetCategories(long entryId) {
		_assetCategoryLocalService.clearAssetEntryAssetCategories(entryId);
	}

	/**
	* Creates a new asset category with the primary key. Does not add the asset category to the database.
	*
	* @param categoryId the primary key for the new asset category
	* @return the new asset category
	*/
	@Override
	public com.liferay.asset.model.AssetCategory createAssetCategory(
		long categoryId) {
		return _assetCategoryLocalService.createAssetCategory(categoryId);
	}

	/**
	* Deletes the asset category from the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was removed
	*/
	@Override
	public com.liferay.asset.model.AssetCategory deleteAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return _assetCategoryLocalService.deleteAssetCategory(assetCategory);
	}

	/**
	* Deletes the asset category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param categoryId the primary key of the asset category
	* @return the asset category that was removed
	* @throws PortalException if a asset category with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetCategory deleteAssetCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryLocalService.deleteAssetCategory(categoryId);
	}

	@Override
	public void deleteAssetEntryAssetCategories(long entryId,
		java.util.List<com.liferay.asset.model.AssetCategory> assetCategories) {
		_assetCategoryLocalService.deleteAssetEntryAssetCategories(entryId,
			assetCategories);
	}

	@Override
	public void deleteAssetEntryAssetCategories(long entryId, long[] categoryIds) {
		_assetCategoryLocalService.deleteAssetEntryAssetCategories(entryId,
			categoryIds);
	}

	@Override
	public void deleteAssetEntryAssetCategory(long entryId,
		com.liferay.asset.model.AssetCategory assetCategory) {
		_assetCategoryLocalService.deleteAssetEntryAssetCategory(entryId,
			assetCategory);
	}

	@Override
	public void deleteAssetEntryAssetCategory(long entryId, long categoryId) {
		_assetCategoryLocalService.deleteAssetEntryAssetCategory(entryId,
			categoryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetCategoryLocalService.dynamicQuery();
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
		return _assetCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetCategoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetCategoryLocalService.dynamicQuery(dynamicQuery, start,
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
		return _assetCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetCategoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.model.AssetCategory fetchAssetCategory(
		long categoryId) {
		return _assetCategoryLocalService.fetchAssetCategory(categoryId);
	}

	/**
	* Returns the asset category matching the UUID and group.
	*
	* @param uuid the asset category's UUID
	* @param groupId the primary key of the group
	* @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetCategory fetchAssetCategoryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _assetCategoryLocalService.fetchAssetCategoryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetCategoryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the asset categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset categories
	* @param end the upper bound of the range of asset categories (not inclusive)
	* @return the range of asset categories
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategories(
		int start, int end) {
		return _assetCategoryLocalService.getAssetCategories(start, end);
	}

	/**
	* Returns all the asset categories matching the UUID and company.
	*
	* @param uuid the UUID of the asset categories
	* @param companyId the primary key of the company
	* @return the matching asset categories, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _assetCategoryLocalService.getAssetCategoriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of asset categories matching the UUID and company.
	*
	* @param uuid the UUID of the asset categories
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of asset categories
	* @param end the upper bound of the range of asset categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching asset categories, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetCategory> orderByComparator) {
		return _assetCategoryLocalService.getAssetCategoriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of asset categories.
	*
	* @return the number of asset categories
	*/
	@Override
	public int getAssetCategoriesCount() {
		return _assetCategoryLocalService.getAssetCategoriesCount();
	}

	/**
	* Returns the asset category with the primary key.
	*
	* @param categoryId the primary key of the asset category
	* @return the asset category
	* @throws PortalException if a asset category with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetCategory getAssetCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryLocalService.getAssetCategory(categoryId);
	}

	/**
	* Returns the asset category matching the UUID and group.
	*
	* @param uuid the asset category's UUID
	* @param groupId the primary key of the group
	* @return the matching asset category
	* @throws PortalException if a matching asset category could not be found
	*/
	@Override
	public com.liferay.asset.model.AssetCategory getAssetCategoryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryLocalService.getAssetCategoryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId) {
		return _assetCategoryLocalService.getAssetEntryAssetCategories(entryId);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId, int start, int end) {
		return _assetCategoryLocalService.getAssetEntryAssetCategories(entryId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetCategory> orderByComparator) {
		return _assetCategoryLocalService.getAssetEntryAssetCategories(entryId,
			start, end, orderByComparator);
	}

	@Override
	public int getAssetEntryAssetCategoriesCount(long entryId) {
		return _assetCategoryLocalService.getAssetEntryAssetCategoriesCount(entryId);
	}

	/**
	* Returns the entryIds of the asset entries associated with the asset category.
	*
	* @param categoryId the categoryId of the asset category
	* @return long[] the entryIds of asset entries associated with the asset category
	*/
	@Override
	public long[] getAssetEntryPrimaryKeys(long categoryId) {
		return _assetCategoryLocalService.getAssetEntryPrimaryKeys(categoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _assetCategoryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetCategoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetCategoryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasAssetEntryAssetCategories(long entryId) {
		return _assetCategoryLocalService.hasAssetEntryAssetCategories(entryId);
	}

	@Override
	public boolean hasAssetEntryAssetCategory(long entryId, long categoryId) {
		return _assetCategoryLocalService.hasAssetEntryAssetCategory(entryId,
			categoryId);
	}

	@Override
	public void setAssetEntryAssetCategories(long entryId, long[] categoryIds) {
		_assetCategoryLocalService.setAssetEntryAssetCategories(entryId,
			categoryIds);
	}

	/**
	* Updates the asset category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was updated
	*/
	@Override
	public com.liferay.asset.model.AssetCategory updateAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return _assetCategoryLocalService.updateAssetCategory(assetCategory);
	}

	@Override
	public AssetCategoryLocalService getWrappedService() {
		return _assetCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		AssetCategoryLocalService assetCategoryLocalService) {
		_assetCategoryLocalService = assetCategoryLocalService;
	}

	private AssetCategoryLocalService _assetCategoryLocalService;
}