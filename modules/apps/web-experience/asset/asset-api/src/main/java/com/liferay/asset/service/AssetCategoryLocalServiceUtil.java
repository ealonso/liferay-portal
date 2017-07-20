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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AssetCategory. This utility wraps
 * {@link com.liferay.asset.service.impl.AssetCategoryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryLocalService
 * @see com.liferay.asset.service.base.AssetCategoryLocalServiceBaseImpl
 * @see com.liferay.asset.service.impl.AssetCategoryLocalServiceImpl
 * @generated
 */
@ProviderType
public class AssetCategoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.asset.service.impl.AssetCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset category to the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was added
	*/
	public static com.liferay.asset.model.AssetCategory addAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return getService().addAssetCategory(assetCategory);
	}

	public static void addAssetEntryAssetCategories(long entryId,
		java.util.List<com.liferay.asset.model.AssetCategory> assetCategories) {
		getService().addAssetEntryAssetCategories(entryId, assetCategories);
	}

	public static void addAssetEntryAssetCategories(long entryId,
		long[] categoryIds) {
		getService().addAssetEntryAssetCategories(entryId, categoryIds);
	}

	public static void addAssetEntryAssetCategory(long entryId,
		com.liferay.asset.model.AssetCategory assetCategory) {
		getService().addAssetEntryAssetCategory(entryId, assetCategory);
	}

	public static void addAssetEntryAssetCategory(long entryId, long categoryId) {
		getService().addAssetEntryAssetCategory(entryId, categoryId);
	}

	public static void clearAssetEntryAssetCategories(long entryId) {
		getService().clearAssetEntryAssetCategories(entryId);
	}

	/**
	* Creates a new asset category with the primary key. Does not add the asset category to the database.
	*
	* @param categoryId the primary key for the new asset category
	* @return the new asset category
	*/
	public static com.liferay.asset.model.AssetCategory createAssetCategory(
		long categoryId) {
		return getService().createAssetCategory(categoryId);
	}

	/**
	* Deletes the asset category from the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was removed
	*/
	public static com.liferay.asset.model.AssetCategory deleteAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return getService().deleteAssetCategory(assetCategory);
	}

	/**
	* Deletes the asset category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param categoryId the primary key of the asset category
	* @return the asset category that was removed
	* @throws PortalException if a asset category with the primary key could not be found
	*/
	public static com.liferay.asset.model.AssetCategory deleteAssetCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAssetCategory(categoryId);
	}

	public static void deleteAssetEntryAssetCategories(long entryId,
		java.util.List<com.liferay.asset.model.AssetCategory> assetCategories) {
		getService().deleteAssetEntryAssetCategories(entryId, assetCategories);
	}

	public static void deleteAssetEntryAssetCategories(long entryId,
		long[] categoryIds) {
		getService().deleteAssetEntryAssetCategories(entryId, categoryIds);
	}

	public static void deleteAssetEntryAssetCategory(long entryId,
		com.liferay.asset.model.AssetCategory assetCategory) {
		getService().deleteAssetEntryAssetCategory(entryId, assetCategory);
	}

	public static void deleteAssetEntryAssetCategory(long entryId,
		long categoryId) {
		getService().deleteAssetEntryAssetCategory(entryId, categoryId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.asset.model.AssetCategory fetchAssetCategory(
		long categoryId) {
		return getService().fetchAssetCategory(categoryId);
	}

	/**
	* Returns the asset category matching the UUID and group.
	*
	* @param uuid the asset category's UUID
	* @param groupId the primary key of the group
	* @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	*/
	public static com.liferay.asset.model.AssetCategory fetchAssetCategoryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchAssetCategoryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategories(
		int start, int end) {
		return getService().getAssetCategories(start, end);
	}

	/**
	* Returns all the asset categories matching the UUID and company.
	*
	* @param uuid the UUID of the asset categories
	* @param companyId the primary key of the company
	* @return the matching asset categories, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getAssetCategoriesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetCategory> orderByComparator) {
		return getService()
				   .getAssetCategoriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of asset categories.
	*
	* @return the number of asset categories
	*/
	public static int getAssetCategoriesCount() {
		return getService().getAssetCategoriesCount();
	}

	/**
	* Returns the asset category with the primary key.
	*
	* @param categoryId the primary key of the asset category
	* @return the asset category
	* @throws PortalException if a asset category with the primary key could not be found
	*/
	public static com.liferay.asset.model.AssetCategory getAssetCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssetCategory(categoryId);
	}

	/**
	* Returns the asset category matching the UUID and group.
	*
	* @param uuid the asset category's UUID
	* @param groupId the primary key of the group
	* @return the matching asset category
	* @throws PortalException if a matching asset category could not be found
	*/
	public static com.liferay.asset.model.AssetCategory getAssetCategoryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssetCategoryByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId) {
		return getService().getAssetEntryAssetCategories(entryId);
	}

	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId, int start, int end) {
		return getService().getAssetEntryAssetCategories(entryId, start, end);
	}

	public static java.util.List<com.liferay.asset.model.AssetCategory> getAssetEntryAssetCategories(
		long entryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetCategory> orderByComparator) {
		return getService()
				   .getAssetEntryAssetCategories(entryId, start, end,
			orderByComparator);
	}

	public static int getAssetEntryAssetCategoriesCount(long entryId) {
		return getService().getAssetEntryAssetCategoriesCount(entryId);
	}

	/**
	* Returns the entryIds of the asset entries associated with the asset category.
	*
	* @param categoryId the categoryId of the asset category
	* @return long[] the entryIds of asset entries associated with the asset category
	*/
	public static long[] getAssetEntryPrimaryKeys(long categoryId) {
		return getService().getAssetEntryPrimaryKeys(categoryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasAssetEntryAssetCategories(long entryId) {
		return getService().hasAssetEntryAssetCategories(entryId);
	}

	public static boolean hasAssetEntryAssetCategory(long entryId,
		long categoryId) {
		return getService().hasAssetEntryAssetCategory(entryId, categoryId);
	}

	public static void setAssetEntryAssetCategories(long entryId,
		long[] categoryIds) {
		getService().setAssetEntryAssetCategories(entryId, categoryIds);
	}

	/**
	* Updates the asset category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetCategory the asset category
	* @return the asset category that was updated
	*/
	public static com.liferay.asset.model.AssetCategory updateAssetCategory(
		com.liferay.asset.model.AssetCategory assetCategory) {
		return getService().updateAssetCategory(assetCategory);
	}

	public static AssetCategoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetCategoryLocalService, AssetCategoryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AssetCategoryLocalService.class);
}