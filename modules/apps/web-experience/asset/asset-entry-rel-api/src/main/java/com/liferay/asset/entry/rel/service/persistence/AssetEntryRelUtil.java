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

package com.liferay.asset.entry.rel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.entry.rel.model.AssetEntryRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the asset entry rel service. This utility wraps {@link com.liferay.asset.entry.rel.service.persistence.impl.AssetEntryRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRelPersistence
 * @see com.liferay.asset.entry.rel.service.persistence.impl.AssetEntryRelPersistenceImpl
 * @generated
 */
@ProviderType
public class AssetEntryRelUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AssetEntryRel assetEntryRel) {
		getPersistence().clearCache(assetEntryRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntryRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetEntryRel update(AssetEntryRel assetEntryRel) {
		return getPersistence().update(assetEntryRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetEntryRel update(AssetEntryRel assetEntryRel,
		ServiceContext serviceContext) {
		return getPersistence().update(assetEntryRel, serviceContext);
	}

	/**
	* Returns all the asset entry rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset entry rels
	*/
	public static List<AssetEntryRel> findByAssetEntry(long assetEntryId) {
		return getPersistence().findByAssetEntry(assetEntryId);
	}

	/**
	* Returns a range of all the asset entry rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @return the range of matching asset entry rels
	*/
	public static List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end) {
		return getPersistence().findByAssetEntry(assetEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry rels
	*/
	public static List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end, OrderByComparator<AssetEntryRel> orderByComparator) {
		return getPersistence()
				   .findByAssetEntry(assetEntryId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry rels
	*/
	public static List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end, OrderByComparator<AssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetEntry(assetEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel findByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence()
				   .findByAssetEntry_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel fetchByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntry_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel findByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence()
				   .findByAssetEntry_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel fetchByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntry_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the asset entry rels before and after the current asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryRelId the primary key of the current asset entry rel
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry rel
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public static AssetEntryRel[] findByAssetEntry_PrevAndNext(
		long assetEntryRelId, long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence()
				   .findByAssetEntry_PrevAndNext(assetEntryRelId, assetEntryId,
			orderByComparator);
	}

	/**
	* Removes all the asset entry rels where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	*/
	public static void removeByAssetEntry(long assetEntryId) {
		getPersistence().removeByAssetEntry(assetEntryId);
	}

	/**
	* Returns the number of asset entry rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset entry rels
	*/
	public static int countByAssetEntry(long assetEntryId) {
		return getPersistence().countByAssetEntry(assetEntryId);
	}

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or throws a {@link NoSuchEntryRelException} if it could not be found.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel findByA_C(long assetEntryId, long classNameId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence().findByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel fetchByA_C(long assetEntryId, long classNameId) {
		return getPersistence().fetchByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public static AssetEntryRel fetchByA_C(long assetEntryId, long classNameId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByA_C(assetEntryId, classNameId, retrieveFromCache);
	}

	/**
	* Removes the asset entry rel where assetEntryId = &#63; and classNameId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the asset entry rel that was removed
	*/
	public static AssetEntryRel removeByA_C(long assetEntryId, long classNameId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence().removeByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the number of asset entry rels where assetEntryId = &#63; and classNameId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the number of matching asset entry rels
	*/
	public static int countByA_C(long assetEntryId, long classNameId) {
		return getPersistence().countByA_C(assetEntryId, classNameId);
	}

	/**
	* Caches the asset entry rel in the entity cache if it is enabled.
	*
	* @param assetEntryRel the asset entry rel
	*/
	public static void cacheResult(AssetEntryRel assetEntryRel) {
		getPersistence().cacheResult(assetEntryRel);
	}

	/**
	* Caches the asset entry rels in the entity cache if it is enabled.
	*
	* @param assetEntryRels the asset entry rels
	*/
	public static void cacheResult(List<AssetEntryRel> assetEntryRels) {
		getPersistence().cacheResult(assetEntryRels);
	}

	/**
	* Creates a new asset entry rel with the primary key. Does not add the asset entry rel to the database.
	*
	* @param assetEntryRelId the primary key for the new asset entry rel
	* @return the new asset entry rel
	*/
	public static AssetEntryRel create(long assetEntryRelId) {
		return getPersistence().create(assetEntryRelId);
	}

	/**
	* Removes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel that was removed
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public static AssetEntryRel remove(long assetEntryRelId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence().remove(assetEntryRelId);
	}

	public static AssetEntryRel updateImpl(AssetEntryRel assetEntryRel) {
		return getPersistence().updateImpl(assetEntryRel);
	}

	/**
	* Returns the asset entry rel with the primary key or throws a {@link NoSuchEntryRelException} if it could not be found.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public static AssetEntryRel findByPrimaryKey(long assetEntryRelId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryRelException {
		return getPersistence().findByPrimaryKey(assetEntryRelId);
	}

	/**
	* Returns the asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel, or <code>null</code> if a asset entry rel with the primary key could not be found
	*/
	public static AssetEntryRel fetchByPrimaryKey(long assetEntryRelId) {
		return getPersistence().fetchByPrimaryKey(assetEntryRelId);
	}

	public static java.util.Map<java.io.Serializable, AssetEntryRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the asset entry rels.
	*
	* @return the asset entry rels
	*/
	public static List<AssetEntryRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @return the range of asset entry rels
	*/
	public static List<AssetEntryRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry rels
	*/
	public static List<AssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry rels
	* @param end the upper bound of the range of asset entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset entry rels
	*/
	public static List<AssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset entry rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset entry rels.
	*
	* @return the number of asset entry rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssetEntryRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntryRelPersistence, AssetEntryRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetEntryRelPersistence.class);

		ServiceTracker<AssetEntryRelPersistence, AssetEntryRelPersistence> serviceTracker =
			new ServiceTracker<AssetEntryRelPersistence, AssetEntryRelPersistence>(bundle.getBundleContext(),
				AssetEntryRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}