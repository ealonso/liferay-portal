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
 * Provides the local service utility for AssetEntry. This utility wraps
 * {@link com.liferay.asset.service.impl.AssetEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryLocalService
 * @see com.liferay.asset.service.base.AssetEntryLocalServiceBaseImpl
 * @see com.liferay.asset.service.impl.AssetEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class AssetEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.asset.service.impl.AssetEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addAssetCategoryAssetEntries(long categoryId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		getService().addAssetCategoryAssetEntries(categoryId, assetEntries);
	}

	public static void addAssetCategoryAssetEntries(long categoryId,
		long[] entryIds) {
		getService().addAssetCategoryAssetEntries(categoryId, entryIds);
	}

	public static void addAssetCategoryAssetEntry(long categoryId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		getService().addAssetCategoryAssetEntry(categoryId, assetEntry);
	}

	public static void addAssetCategoryAssetEntry(long categoryId, long entryId) {
		getService().addAssetCategoryAssetEntry(categoryId, entryId);
	}

	/**
	* Adds the asset entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was added
	*/
	public static com.liferay.asset.model.AssetEntry addAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return getService().addAssetEntry(assetEntry);
	}

	public static void addAssetTagAssetEntries(long tagId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		getService().addAssetTagAssetEntries(tagId, assetEntries);
	}

	public static void addAssetTagAssetEntries(long tagId, long[] entryIds) {
		getService().addAssetTagAssetEntries(tagId, entryIds);
	}

	public static void addAssetTagAssetEntry(long tagId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		getService().addAssetTagAssetEntry(tagId, assetEntry);
	}

	public static void addAssetTagAssetEntry(long tagId, long entryId) {
		getService().addAssetTagAssetEntry(tagId, entryId);
	}

	public static void clearAssetCategoryAssetEntries(long categoryId) {
		getService().clearAssetCategoryAssetEntries(categoryId);
	}

	public static void clearAssetTagAssetEntries(long tagId) {
		getService().clearAssetTagAssetEntries(tagId);
	}

	/**
	* Creates a new asset entry with the primary key. Does not add the asset entry to the database.
	*
	* @param entryId the primary key for the new asset entry
	* @return the new asset entry
	*/
	public static com.liferay.asset.model.AssetEntry createAssetEntry(
		long entryId) {
		return getService().createAssetEntry(entryId);
	}

	public static void deleteAssetCategoryAssetEntries(long categoryId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		getService().deleteAssetCategoryAssetEntries(categoryId, assetEntries);
	}

	public static void deleteAssetCategoryAssetEntries(long categoryId,
		long[] entryIds) {
		getService().deleteAssetCategoryAssetEntries(categoryId, entryIds);
	}

	public static void deleteAssetCategoryAssetEntry(long categoryId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		getService().deleteAssetCategoryAssetEntry(categoryId, assetEntry);
	}

	public static void deleteAssetCategoryAssetEntry(long categoryId,
		long entryId) {
		getService().deleteAssetCategoryAssetEntry(categoryId, entryId);
	}

	/**
	* Deletes the asset entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was removed
	*/
	public static com.liferay.asset.model.AssetEntry deleteAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return getService().deleteAssetEntry(assetEntry);
	}

	/**
	* Deletes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry that was removed
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	public static com.liferay.asset.model.AssetEntry deleteAssetEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAssetEntry(entryId);
	}

	public static void deleteAssetTagAssetEntries(long tagId,
		java.util.List<com.liferay.asset.model.AssetEntry> assetEntries) {
		getService().deleteAssetTagAssetEntries(tagId, assetEntries);
	}

	public static void deleteAssetTagAssetEntries(long tagId, long[] entryIds) {
		getService().deleteAssetTagAssetEntries(tagId, entryIds);
	}

	public static void deleteAssetTagAssetEntry(long tagId,
		com.liferay.asset.model.AssetEntry assetEntry) {
		getService().deleteAssetTagAssetEntry(tagId, assetEntry);
	}

	public static void deleteAssetTagAssetEntry(long tagId, long entryId) {
		getService().deleteAssetTagAssetEntry(tagId, entryId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.asset.model.AssetEntry fetchAssetEntry(
		long entryId) {
		return getService().fetchAssetEntry(entryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId) {
		return getService().getAssetCategoryAssetEntries(categoryId);
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end) {
		return getService().getAssetCategoryAssetEntries(categoryId, start, end);
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetEntry> orderByComparator) {
		return getService()
				   .getAssetCategoryAssetEntries(categoryId, start, end,
			orderByComparator);
	}

	public static int getAssetCategoryAssetEntriesCount(long categoryId) {
		return getService().getAssetCategoryAssetEntriesCount(categoryId);
	}

	/**
	* Returns the categoryIds of the asset categories associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the categoryIds of asset categories associated with the asset entry
	*/
	public static long[] getAssetCategoryPrimaryKeys(long entryId) {
		return getService().getAssetCategoryPrimaryKeys(entryId);
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
	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetEntries(
		int start, int end) {
		return getService().getAssetEntries(start, end);
	}

	/**
	* Returns the number of asset entries.
	*
	* @return the number of asset entries
	*/
	public static int getAssetEntriesCount() {
		return getService().getAssetEntriesCount();
	}

	/**
	* Returns the asset entry with the primary key.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	public static com.liferay.asset.model.AssetEntry getAssetEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssetEntry(entryId);
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId) {
		return getService().getAssetTagAssetEntries(tagId);
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end) {
		return getService().getAssetTagAssetEntries(tagId, start, end);
	}

	public static java.util.List<com.liferay.asset.model.AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.model.AssetEntry> orderByComparator) {
		return getService()
				   .getAssetTagAssetEntries(tagId, start, end, orderByComparator);
	}

	public static int getAssetTagAssetEntriesCount(long tagId) {
		return getService().getAssetTagAssetEntriesCount(tagId);
	}

	/**
	* Returns the tagIds of the asset tags associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the tagIds of asset tags associated with the asset entry
	*/
	public static long[] getAssetTagPrimaryKeys(long entryId) {
		return getService().getAssetTagPrimaryKeys(entryId);
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

	public static boolean hasAssetCategoryAssetEntries(long categoryId) {
		return getService().hasAssetCategoryAssetEntries(categoryId);
	}

	public static boolean hasAssetCategoryAssetEntry(long categoryId,
		long entryId) {
		return getService().hasAssetCategoryAssetEntry(categoryId, entryId);
	}

	public static boolean hasAssetTagAssetEntries(long tagId) {
		return getService().hasAssetTagAssetEntries(tagId);
	}

	public static boolean hasAssetTagAssetEntry(long tagId, long entryId) {
		return getService().hasAssetTagAssetEntry(tagId, entryId);
	}

	public static void setAssetCategoryAssetEntries(long categoryId,
		long[] entryIds) {
		getService().setAssetCategoryAssetEntries(categoryId, entryIds);
	}

	public static void setAssetTagAssetEntries(long tagId, long[] entryIds) {
		getService().setAssetTagAssetEntries(tagId, entryIds);
	}

	/**
	* Updates the asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was updated
	*/
	public static com.liferay.asset.model.AssetEntry updateAssetEntry(
		com.liferay.asset.model.AssetEntry assetEntry) {
		return getService().updateAssetEntry(assetEntry);
	}

	public static AssetEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntryLocalService, AssetEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AssetEntryLocalService.class);
}