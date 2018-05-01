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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AssetEntryRel. This utility wraps
 * {@link com.liferay.asset.entry.rel.service.impl.AssetEntryRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRelLocalService
 * @see com.liferay.asset.entry.rel.service.base.AssetEntryRelLocalServiceBaseImpl
 * @see com.liferay.asset.entry.rel.service.impl.AssetEntryRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class AssetEntryRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.asset.entry.rel.service.impl.AssetEntryRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset entry rel to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was added
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel addAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return getService().addAssetEntryRel(assetEntryRel);
	}

	public static com.liferay.asset.entry.rel.model.AssetEntryRel addAssetEntryRel(
		long assetEntryId, long classNameId, long classPK) {
		return getService().addAssetEntryRel(assetEntryId, classNameId, classPK);
	}

	/**
	* Creates a new asset entry rel with the primary key. Does not add the asset entry rel to the database.
	*
	* @param assetEntryRelId the primary key for the new asset entry rel
	* @return the new asset entry rel
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel createAssetEntryRel(
		long assetEntryRelId) {
		return getService().createAssetEntryRel(assetEntryRelId);
	}

	/**
	* Deletes the asset entry rel from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was removed
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel deleteAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return getService().deleteAssetEntryRel(assetEntryRel);
	}

	/**
	* Deletes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel that was removed
	* @throws PortalException if a asset entry rel with the primary key could not be found
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel deleteAssetEntryRel(
		long assetEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAssetEntryRel(assetEntryRelId);
	}

	public static void deleteAssetEntryRel(long assetEntryId, long classNameId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		getService().deleteAssetEntryRel(assetEntryId, classNameId);
	}

	public static void deleteAssetEntryRelByAssetEntryId(long assetEntryId) {
		getService().deleteAssetEntryRelByAssetEntryId(assetEntryId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.asset.entry.rel.model.AssetEntryRel fetchAssetEntryRel(
		long assetEntryRelId) {
		return getService().fetchAssetEntryRel(assetEntryRelId);
	}

	public static com.liferay.asset.entry.rel.model.AssetEntryRel fetchAssetEntryRel(
		long assetEntryId, long classNameId) {
		return getService().fetchAssetEntryRel(assetEntryId, classNameId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the asset entry rel with the primary key.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel
	* @throws PortalException if a asset entry rel with the primary key could not be found
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel getAssetEntryRel(
		long assetEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssetEntryRel(assetEntryRelId);
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
	public static java.util.List<com.liferay.asset.entry.rel.model.AssetEntryRel> getAssetEntryRels(
		int start, int end) {
		return getService().getAssetEntryRels(start, end);
	}

	/**
	* Returns the number of asset entry rels.
	*
	* @return the number of asset entry rels
	*/
	public static int getAssetEntryRelsCount() {
		return getService().getAssetEntryRelsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the asset entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRel the asset entry rel
	* @return the asset entry rel that was updated
	*/
	public static com.liferay.asset.entry.rel.model.AssetEntryRel updateAssetEntryRel(
		com.liferay.asset.entry.rel.model.AssetEntryRel assetEntryRel) {
		return getService().updateAssetEntryRel(assetEntryRel);
	}

	public static AssetEntryRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntryRelLocalService, AssetEntryRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetEntryRelLocalService.class);

		ServiceTracker<AssetEntryRelLocalService, AssetEntryRelLocalService> serviceTracker =
			new ServiceTracker<AssetEntryRelLocalService, AssetEntryRelLocalService>(bundle.getBundleContext(),
				AssetEntryRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}