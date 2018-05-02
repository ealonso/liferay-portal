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

import com.liferay.asset.entry.rel.model.AssetEntryClassNameRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the asset entry class name rel service. This utility wraps {@link com.liferay.asset.entry.rel.service.persistence.impl.AssetEntryClassNameRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryClassNameRelPersistence
 * @see com.liferay.asset.entry.rel.service.persistence.impl.AssetEntryClassNameRelPersistenceImpl
 * @generated
 */
@ProviderType
public class AssetEntryClassNameRelUtil {
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
	public static void clearCache(AssetEntryClassNameRel assetEntryClassNameRel) {
		getPersistence().clearCache(assetEntryClassNameRel);
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
	public static List<AssetEntryClassNameRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntryClassNameRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntryClassNameRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetEntryClassNameRel update(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		return getPersistence().update(assetEntryClassNameRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetEntryClassNameRel update(
		AssetEntryClassNameRel assetEntryClassNameRel,
		ServiceContext serviceContext) {
		return getPersistence().update(assetEntryClassNameRel, serviceContext);
	}

	/**
	* Returns all the asset entry class name rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findByAssetEntry(
		long assetEntryId) {
		return getPersistence().findByAssetEntry(assetEntryId);
	}

	/**
	* Returns a range of all the asset entry class name rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @return the range of matching asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findByAssetEntry(
		long assetEntryId, int start, int end) {
		return getPersistence().findByAssetEntry(assetEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry class name rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findByAssetEntry(
		long assetEntryId, int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return getPersistence()
				   .findByAssetEntry(assetEntryId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry class name rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findByAssetEntry(
		long assetEntryId, int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetEntry(assetEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset entry class name rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry class name rel
	* @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel findByAssetEntry_First(
		long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence()
				   .findByAssetEntry_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the first asset entry class name rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel fetchByAssetEntry_First(
		long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntry_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry class name rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry class name rel
	* @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel findByAssetEntry_Last(
		long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence()
				   .findByAssetEntry_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry class name rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel fetchByAssetEntry_Last(
		long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntry_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the asset entry class name rels before and after the current asset entry class name rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryClassNameRelId the primary key of the current asset entry class name rel
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry class name rel
	* @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	*/
	public static AssetEntryClassNameRel[] findByAssetEntry_PrevAndNext(
		long assetEntryClassNameRelId, long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence()
				   .findByAssetEntry_PrevAndNext(assetEntryClassNameRelId,
			assetEntryId, orderByComparator);
	}

	/**
	* Removes all the asset entry class name rels where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	*/
	public static void removeByAssetEntry(long assetEntryId) {
		getPersistence().removeByAssetEntry(assetEntryId);
	}

	/**
	* Returns the number of asset entry class name rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset entry class name rels
	*/
	public static int countByAssetEntry(long assetEntryId) {
		return getPersistence().countByAssetEntry(assetEntryId);
	}

	/**
	* Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or throws a {@link NoSuchEntryClassNameRelException} if it could not be found.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry class name rel
	* @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel findByA_C(long assetEntryId,
		long classNameId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence().findByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel fetchByA_C(long assetEntryId,
		long classNameId) {
		return getPersistence().fetchByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	*/
	public static AssetEntryClassNameRel fetchByA_C(long assetEntryId,
		long classNameId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByA_C(assetEntryId, classNameId, retrieveFromCache);
	}

	/**
	* Removes the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the asset entry class name rel that was removed
	*/
	public static AssetEntryClassNameRel removeByA_C(long assetEntryId,
		long classNameId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence().removeByA_C(assetEntryId, classNameId);
	}

	/**
	* Returns the number of asset entry class name rels where assetEntryId = &#63; and classNameId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the number of matching asset entry class name rels
	*/
	public static int countByA_C(long assetEntryId, long classNameId) {
		return getPersistence().countByA_C(assetEntryId, classNameId);
	}

	/**
	* Caches the asset entry class name rel in the entity cache if it is enabled.
	*
	* @param assetEntryClassNameRel the asset entry class name rel
	*/
	public static void cacheResult(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		getPersistence().cacheResult(assetEntryClassNameRel);
	}

	/**
	* Caches the asset entry class name rels in the entity cache if it is enabled.
	*
	* @param assetEntryClassNameRels the asset entry class name rels
	*/
	public static void cacheResult(
		List<AssetEntryClassNameRel> assetEntryClassNameRels) {
		getPersistence().cacheResult(assetEntryClassNameRels);
	}

	/**
	* Creates a new asset entry class name rel with the primary key. Does not add the asset entry class name rel to the database.
	*
	* @param assetEntryClassNameRelId the primary key for the new asset entry class name rel
	* @return the new asset entry class name rel
	*/
	public static AssetEntryClassNameRel create(long assetEntryClassNameRelId) {
		return getPersistence().create(assetEntryClassNameRelId);
	}

	/**
	* Removes the asset entry class name rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	* @return the asset entry class name rel that was removed
	* @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	*/
	public static AssetEntryClassNameRel remove(long assetEntryClassNameRelId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence().remove(assetEntryClassNameRelId);
	}

	public static AssetEntryClassNameRel updateImpl(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		return getPersistence().updateImpl(assetEntryClassNameRel);
	}

	/**
	* Returns the asset entry class name rel with the primary key or throws a {@link NoSuchEntryClassNameRelException} if it could not be found.
	*
	* @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	* @return the asset entry class name rel
	* @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	*/
	public static AssetEntryClassNameRel findByPrimaryKey(
		long assetEntryClassNameRelId)
		throws com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException {
		return getPersistence().findByPrimaryKey(assetEntryClassNameRelId);
	}

	/**
	* Returns the asset entry class name rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	* @return the asset entry class name rel, or <code>null</code> if a asset entry class name rel with the primary key could not be found
	*/
	public static AssetEntryClassNameRel fetchByPrimaryKey(
		long assetEntryClassNameRelId) {
		return getPersistence().fetchByPrimaryKey(assetEntryClassNameRelId);
	}

	public static java.util.Map<java.io.Serializable, AssetEntryClassNameRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the asset entry class name rels.
	*
	* @return the asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset entry class name rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @return the range of asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset entry class name rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findAll(int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry class name rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry class name rels
	* @param end the upper bound of the range of asset entry class name rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset entry class name rels
	*/
	public static List<AssetEntryClassNameRel> findAll(int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset entry class name rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset entry class name rels.
	*
	* @return the number of asset entry class name rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssetEntryClassNameRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntryClassNameRelPersistence, AssetEntryClassNameRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetEntryClassNameRelPersistence.class);

		ServiceTracker<AssetEntryClassNameRelPersistence, AssetEntryClassNameRelPersistence> serviceTracker =
			new ServiceTracker<AssetEntryClassNameRelPersistence, AssetEntryClassNameRelPersistence>(bundle.getBundleContext(),
				AssetEntryClassNameRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}