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

import com.liferay.asset.entry.rel.exception.NoSuchEntryRelException;
import com.liferay.asset.entry.rel.model.AssetEntryRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.entry.rel.service.persistence.impl.AssetEntryRelPersistenceImpl
 * @see AssetEntryRelUtil
 * @generated
 */
@ProviderType
public interface AssetEntryRelPersistence extends BasePersistence<AssetEntryRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntryRelUtil} to access the asset entry rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset entry rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset entry rels
	*/
	public java.util.List<AssetEntryRel> findByAssetEntry(long assetEntryId);

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
	public java.util.List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end);

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
	public java.util.List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator);

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
	public java.util.List<AssetEntryRel> findByAssetEntry(long assetEntryId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public AssetEntryRel findByAssetEntry_First(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException;

	/**
	* Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public AssetEntryRel fetchByAssetEntry_First(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator);

	/**
	* Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public AssetEntryRel findByAssetEntry_Last(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException;

	/**
	* Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public AssetEntryRel fetchByAssetEntry_Last(long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator);

	/**
	* Returns the asset entry rels before and after the current asset entry rel in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryRelId the primary key of the current asset entry rel
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry rel
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public AssetEntryRel[] findByAssetEntry_PrevAndNext(long assetEntryRelId,
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException;

	/**
	* Removes all the asset entry rels where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	*/
	public void removeByAssetEntry(long assetEntryId);

	/**
	* Returns the number of asset entry rels where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset entry rels
	*/
	public int countByAssetEntry(long assetEntryId);

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or throws a {@link NoSuchEntryRelException} if it could not be found.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry rel
	* @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	*/
	public AssetEntryRel findByA_C(long assetEntryId, long classNameId)
		throws NoSuchEntryRelException;

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public AssetEntryRel fetchByA_C(long assetEntryId, long classNameId);

	/**
	* Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	*/
	public AssetEntryRel fetchByA_C(long assetEntryId, long classNameId,
		boolean retrieveFromCache);

	/**
	* Removes the asset entry rel where assetEntryId = &#63; and classNameId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the asset entry rel that was removed
	*/
	public AssetEntryRel removeByA_C(long assetEntryId, long classNameId)
		throws NoSuchEntryRelException;

	/**
	* Returns the number of asset entry rels where assetEntryId = &#63; and classNameId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param classNameId the class name ID
	* @return the number of matching asset entry rels
	*/
	public int countByA_C(long assetEntryId, long classNameId);

	/**
	* Caches the asset entry rel in the entity cache if it is enabled.
	*
	* @param assetEntryRel the asset entry rel
	*/
	public void cacheResult(AssetEntryRel assetEntryRel);

	/**
	* Caches the asset entry rels in the entity cache if it is enabled.
	*
	* @param assetEntryRels the asset entry rels
	*/
	public void cacheResult(java.util.List<AssetEntryRel> assetEntryRels);

	/**
	* Creates a new asset entry rel with the primary key. Does not add the asset entry rel to the database.
	*
	* @param assetEntryRelId the primary key for the new asset entry rel
	* @return the new asset entry rel
	*/
	public AssetEntryRel create(long assetEntryRelId);

	/**
	* Removes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel that was removed
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public AssetEntryRel remove(long assetEntryRelId)
		throws NoSuchEntryRelException;

	public AssetEntryRel updateImpl(AssetEntryRel assetEntryRel);

	/**
	* Returns the asset entry rel with the primary key or throws a {@link NoSuchEntryRelException} if it could not be found.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel
	* @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	*/
	public AssetEntryRel findByPrimaryKey(long assetEntryRelId)
		throws NoSuchEntryRelException;

	/**
	* Returns the asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntryRelId the primary key of the asset entry rel
	* @return the asset entry rel, or <code>null</code> if a asset entry rel with the primary key could not be found
	*/
	public AssetEntryRel fetchByPrimaryKey(long assetEntryRelId);

	@Override
	public java.util.Map<java.io.Serializable, AssetEntryRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset entry rels.
	*
	* @return the asset entry rels
	*/
	public java.util.List<AssetEntryRel> findAll();

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
	public java.util.List<AssetEntryRel> findAll(int start, int end);

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
	public java.util.List<AssetEntryRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator);

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
	public java.util.List<AssetEntryRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset entry rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset entry rels.
	*
	* @return the number of asset entry rels
	*/
	public int countAll();
}