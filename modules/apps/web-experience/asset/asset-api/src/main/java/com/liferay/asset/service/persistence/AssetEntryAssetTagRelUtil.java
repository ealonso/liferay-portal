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

package com.liferay.asset.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.model.AssetEntryAssetTagRel;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the asset entry asset tag rel service. This utility wraps {@link com.liferay.asset.service.persistence.impl.AssetEntryAssetTagRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetTagRelPersistence
 * @see com.liferay.asset.service.persistence.impl.AssetEntryAssetTagRelPersistenceImpl
 * @generated
 */
@ProviderType
public class AssetEntryAssetTagRelUtil {
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
	public static void clearCache(AssetEntryAssetTagRel assetEntryAssetTagRel) {
		getPersistence().clearCache(assetEntryAssetTagRel);
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
	public static List<AssetEntryAssetTagRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntryAssetTagRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntryAssetTagRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetEntryAssetTagRel update(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		return getPersistence().update(assetEntryAssetTagRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetEntryAssetTagRel update(
		AssetEntryAssetTagRel assetEntryAssetTagRel,
		ServiceContext serviceContext) {
		return getPersistence().update(assetEntryAssetTagRel, serviceContext);
	}

	/**
	* Returns all the asset entry asset tag rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId) {
		return getPersistence().findByAssetEntryId(assetEntryId);
	}

	/**
	* Returns a range of all the asset entry asset tag rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @return the range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end) {
		return getPersistence().findByAssetEntryId(assetEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel findByAssetEntryId_First(
		long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel fetchByAssetEntryId_First(
		long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel findByAssetEntryId_Last(
		long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel fetchByAssetEntryId_Last(
		long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param entryId the primary key of the current asset entry asset tag rel
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public static AssetEntryAssetTagRel[] findByAssetEntryId_PrevAndNext(
		long entryId, long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetEntryId_PrevAndNext(entryId, assetEntryId,
			orderByComparator);
	}

	/**
	* Removes all the asset entry asset tag rels where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	*/
	public static void removeByAssetEntryId(long assetEntryId) {
		getPersistence().removeByAssetEntryId(assetEntryId);
	}

	/**
	* Returns the number of asset entry asset tag rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset entry asset tag rels
	*/
	public static int countByAssetEntryId(long assetEntryId) {
		return getPersistence().countByAssetEntryId(assetEntryId);
	}

	/**
	* Returns all the asset entry asset tag rels where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @return the matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetTagId(long assetTagId) {
		return getPersistence().findByAssetTagId(assetTagId);
	}

	/**
	* Returns a range of all the asset entry asset tag rels where assetTagId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetTagId the asset tag ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @return the range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end) {
		return getPersistence().findByAssetTagId(assetTagId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels where assetTagId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetTagId the asset tag ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .findByAssetTagId(assetTagId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels where assetTagId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetTagId the asset tag ID
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetTagId(assetTagId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel findByAssetTagId_First(
		long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetTagId_First(assetTagId, orderByComparator);
	}

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel fetchByAssetTagId_First(
		long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetTagId_First(assetTagId, orderByComparator);
	}

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel findByAssetTagId_Last(long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetTagId_Last(assetTagId, orderByComparator);
	}

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public static AssetEntryAssetTagRel fetchByAssetTagId_Last(
		long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence()
				   .fetchByAssetTagId_Last(assetTagId, orderByComparator);
	}

	/**
	* Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param entryId the primary key of the current asset entry asset tag rel
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public static AssetEntryAssetTagRel[] findByAssetTagId_PrevAndNext(
		long entryId, long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence()
				   .findByAssetTagId_PrevAndNext(entryId, assetTagId,
			orderByComparator);
	}

	/**
	* Removes all the asset entry asset tag rels where assetTagId = &#63; from the database.
	*
	* @param assetTagId the asset tag ID
	*/
	public static void removeByAssetTagId(long assetTagId) {
		getPersistence().removeByAssetTagId(assetTagId);
	}

	/**
	* Returns the number of asset entry asset tag rels where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @return the number of matching asset entry asset tag rels
	*/
	public static int countByAssetTagId(long assetTagId) {
		return getPersistence().countByAssetTagId(assetTagId);
	}

	/**
	* Caches the asset entry asset tag rel in the entity cache if it is enabled.
	*
	* @param assetEntryAssetTagRel the asset entry asset tag rel
	*/
	public static void cacheResult(AssetEntryAssetTagRel assetEntryAssetTagRel) {
		getPersistence().cacheResult(assetEntryAssetTagRel);
	}

	/**
	* Caches the asset entry asset tag rels in the entity cache if it is enabled.
	*
	* @param assetEntryAssetTagRels the asset entry asset tag rels
	*/
	public static void cacheResult(
		List<AssetEntryAssetTagRel> assetEntryAssetTagRels) {
		getPersistence().cacheResult(assetEntryAssetTagRels);
	}

	/**
	* Creates a new asset entry asset tag rel with the primary key. Does not add the asset entry asset tag rel to the database.
	*
	* @param entryId the primary key for the new asset entry asset tag rel
	* @return the new asset entry asset tag rel
	*/
	public static AssetEntryAssetTagRel create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the asset entry asset tag rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel that was removed
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public static AssetEntryAssetTagRel remove(long entryId)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence().remove(entryId);
	}

	public static AssetEntryAssetTagRel updateImpl(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		return getPersistence().updateImpl(assetEntryAssetTagRel);
	}

	/**
	* Returns the asset entry asset tag rel with the primary key or throws a {@link NoSuchEntryAssetTagRelException} if it could not be found.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public static AssetEntryAssetTagRel findByPrimaryKey(long entryId)
		throws com.liferay.asset.exception.NoSuchEntryAssetTagRelException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the asset entry asset tag rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel, or <code>null</code> if a asset entry asset tag rel with the primary key could not be found
	*/
	public static AssetEntryAssetTagRel fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.Map<java.io.Serializable, AssetEntryAssetTagRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the asset entry asset tag rels.
	*
	* @return the asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset entry asset tag rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @return the range of asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findAll(int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset entry asset tag rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry asset tag rels
	* @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset entry asset tag rels
	*/
	public static List<AssetEntryAssetTagRel> findAll(int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset entry asset tag rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset entry asset tag rels.
	*
	* @return the number of asset entry asset tag rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssetEntryAssetTagRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntryAssetTagRelPersistence, AssetEntryAssetTagRelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AssetEntryAssetTagRelPersistence.class);
}