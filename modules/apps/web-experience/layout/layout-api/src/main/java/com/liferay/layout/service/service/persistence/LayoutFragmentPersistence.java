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

package com.liferay.layout.service.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.service.exception.NoSuchFragmentException;
import com.liferay.layout.service.model.LayoutFragment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the layout fragment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.service.service.persistence.impl.LayoutFragmentPersistenceImpl
 * @see LayoutFragmentUtil
 * @generated
 */
@ProviderType
public interface LayoutFragmentPersistence extends BasePersistence<LayoutFragment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutFragmentUtil} to access the layout fragment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the layout fragments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByGroupId(long groupId);

	/**
	* Returns a range of all the layout fragments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @return the range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63;.
	*
	* @param layoutFragmentId the primary key of the current layout fragment
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment[] findByGroupId_PrevAndNext(long layoutFragmentId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Removes all the layout fragments where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of layout fragments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout fragments
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the layout fragments where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @return the matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P(long groupId, long plid);

	/**
	* Returns a range of all the layout fragments where groupId = &#63; and plid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @return the range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByG_P_First(long groupId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByG_P_First(long groupId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByG_P_Last(long groupId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByG_P_Last(long groupId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param layoutFragmentId the primary key of the current layout fragment
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment[] findByG_P_PrevAndNext(long layoutFragmentId,
		long groupId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Removes all the layout fragments where groupId = &#63; and plid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param plid the plid
	*/
	public void removeByG_P(long groupId, long plid);

	/**
	* Returns the number of layout fragments where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @return the number of matching layout fragments
	*/
	public int countByG_P(long groupId, long plid);

	/**
	* Returns all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @return the matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId);

	/**
	* Returns a range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @return the range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout fragments
	*/
	public java.util.List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public LayoutFragment findByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public LayoutFragment fetchByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param layoutFragmentId the primary key of the current layout fragment
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment[] findByG_P_F_PrevAndNext(long layoutFragmentId,
		long groupId, long plid, long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException;

	/**
	* Removes all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	*/
	public void removeByG_P_F(long groupId, long plid, long fragmentEntryId);

	/**
	* Returns the number of layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching layout fragments
	*/
	public int countByG_P_F(long groupId, long plid, long fragmentEntryId);

	/**
	* Caches the layout fragment in the entity cache if it is enabled.
	*
	* @param layoutFragment the layout fragment
	*/
	public void cacheResult(LayoutFragment layoutFragment);

	/**
	* Caches the layout fragments in the entity cache if it is enabled.
	*
	* @param layoutFragments the layout fragments
	*/
	public void cacheResult(java.util.List<LayoutFragment> layoutFragments);

	/**
	* Creates a new layout fragment with the primary key. Does not add the layout fragment to the database.
	*
	* @param layoutFragmentId the primary key for the new layout fragment
	* @return the new layout fragment
	*/
	public LayoutFragment create(long layoutFragmentId);

	/**
	* Removes the layout fragment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment that was removed
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment remove(long layoutFragmentId)
		throws NoSuchFragmentException;

	public LayoutFragment updateImpl(LayoutFragment layoutFragment);

	/**
	* Returns the layout fragment with the primary key or throws a {@link NoSuchFragmentException} if it could not be found.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment findByPrimaryKey(long layoutFragmentId)
		throws NoSuchFragmentException;

	/**
	* Returns the layout fragment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment, or <code>null</code> if a layout fragment with the primary key could not be found
	*/
	public LayoutFragment fetchByPrimaryKey(long layoutFragmentId);

	@Override
	public java.util.Map<java.io.Serializable, LayoutFragment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the layout fragments.
	*
	* @return the layout fragments
	*/
	public java.util.List<LayoutFragment> findAll();

	/**
	* Returns a range of all the layout fragments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @return the range of layout fragments
	*/
	public java.util.List<LayoutFragment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the layout fragments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout fragments
	*/
	public java.util.List<LayoutFragment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator);

	/**
	* Returns an ordered range of all the layout fragments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of layout fragments
	*/
	public java.util.List<LayoutFragment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the layout fragments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of layout fragments.
	*
	* @return the number of layout fragments
	*/
	public int countAll();
}