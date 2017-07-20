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

import com.liferay.asset.exception.NoSuchEntryAssetTagRelException;
import com.liferay.asset.model.AssetEntryAssetTagRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry asset tag rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.service.persistence.impl.AssetEntryAssetTagRelPersistenceImpl
 * @see AssetEntryAssetTagRelUtil
 * @generated
 */
@ProviderType
public interface AssetEntryAssetTagRelPersistence extends BasePersistence<AssetEntryAssetTagRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntryAssetTagRelUtil} to access the asset entry asset tag rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset entry asset tag rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset entry asset tag rels
	*/
	public java.util.List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel findByAssetEntryId_First(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel fetchByAssetEntryId_First(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel findByAssetEntryId_Last(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel fetchByAssetEntryId_Last(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

	/**
	* Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	*
	* @param entryId the primary key of the current asset entry asset tag rel
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public AssetEntryAssetTagRel[] findByAssetEntryId_PrevAndNext(
		long entryId, long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Removes all the asset entry asset tag rels where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	*/
	public void removeByAssetEntryId(long assetEntryId);

	/**
	* Returns the number of asset entry asset tag rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset entry asset tag rels
	*/
	public int countByAssetEntryId(long assetEntryId);

	/**
	* Returns all the asset entry asset tag rels where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @return the matching asset entry asset tag rels
	*/
	public java.util.List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

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
	public java.util.List<AssetEntryAssetTagRel> findByAssetTagId(
		long assetTagId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel findByAssetTagId_First(long assetTagId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel fetchByAssetTagId_First(long assetTagId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel findByAssetTagId_Last(long assetTagId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	*/
	public AssetEntryAssetTagRel fetchByAssetTagId_Last(long assetTagId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

	/**
	* Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	*
	* @param entryId the primary key of the current asset entry asset tag rel
	* @param assetTagId the asset tag ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public AssetEntryAssetTagRel[] findByAssetTagId_PrevAndNext(long entryId,
		long assetTagId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Removes all the asset entry asset tag rels where assetTagId = &#63; from the database.
	*
	* @param assetTagId the asset tag ID
	*/
	public void removeByAssetTagId(long assetTagId);

	/**
	* Returns the number of asset entry asset tag rels where assetTagId = &#63;.
	*
	* @param assetTagId the asset tag ID
	* @return the number of matching asset entry asset tag rels
	*/
	public int countByAssetTagId(long assetTagId);

	/**
	* Caches the asset entry asset tag rel in the entity cache if it is enabled.
	*
	* @param assetEntryAssetTagRel the asset entry asset tag rel
	*/
	public void cacheResult(AssetEntryAssetTagRel assetEntryAssetTagRel);

	/**
	* Caches the asset entry asset tag rels in the entity cache if it is enabled.
	*
	* @param assetEntryAssetTagRels the asset entry asset tag rels
	*/
	public void cacheResult(
		java.util.List<AssetEntryAssetTagRel> assetEntryAssetTagRels);

	/**
	* Creates a new asset entry asset tag rel with the primary key. Does not add the asset entry asset tag rel to the database.
	*
	* @param entryId the primary key for the new asset entry asset tag rel
	* @return the new asset entry asset tag rel
	*/
	public AssetEntryAssetTagRel create(long entryId);

	/**
	* Removes the asset entry asset tag rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel that was removed
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public AssetEntryAssetTagRel remove(long entryId)
		throws NoSuchEntryAssetTagRelException;

	public AssetEntryAssetTagRel updateImpl(
		AssetEntryAssetTagRel assetEntryAssetTagRel);

	/**
	* Returns the asset entry asset tag rel with the primary key or throws a {@link NoSuchEntryAssetTagRelException} if it could not be found.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel
	* @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	*/
	public AssetEntryAssetTagRel findByPrimaryKey(long entryId)
		throws NoSuchEntryAssetTagRelException;

	/**
	* Returns the asset entry asset tag rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the asset entry asset tag rel
	* @return the asset entry asset tag rel, or <code>null</code> if a asset entry asset tag rel with the primary key could not be found
	*/
	public AssetEntryAssetTagRel fetchByPrimaryKey(long entryId);

	@Override
	public java.util.Map<java.io.Serializable, AssetEntryAssetTagRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset entry asset tag rels.
	*
	* @return the asset entry asset tag rels
	*/
	public java.util.List<AssetEntryAssetTagRel> findAll();

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
	public java.util.List<AssetEntryAssetTagRel> findAll(int start, int end);

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
	public java.util.List<AssetEntryAssetTagRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator);

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
	public java.util.List<AssetEntryAssetTagRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset entry asset tag rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset entry asset tag rels.
	*
	* @return the number of asset entry asset tag rels
	*/
	public int countAll();
}