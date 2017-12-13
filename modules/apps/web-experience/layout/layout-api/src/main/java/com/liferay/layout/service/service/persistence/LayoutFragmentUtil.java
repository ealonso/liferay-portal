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

import com.liferay.layout.service.model.LayoutFragment;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the layout fragment service. This utility wraps {@link com.liferay.layout.service.service.persistence.impl.LayoutFragmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentPersistence
 * @see com.liferay.layout.service.service.persistence.impl.LayoutFragmentPersistenceImpl
 * @generated
 */
@ProviderType
public class LayoutFragmentUtil {
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
	public static void clearCache(LayoutFragment layoutFragment) {
		getPersistence().clearCache(layoutFragment);
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
	public static List<LayoutFragment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutFragment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutFragment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LayoutFragment update(LayoutFragment layoutFragment) {
		return getPersistence().update(layoutFragment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LayoutFragment update(LayoutFragment layoutFragment,
		ServiceContext serviceContext) {
		return getPersistence().update(layoutFragment, serviceContext);
	}

	/**
	* Returns all the layout fragments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout fragments
	*/
	public static List<LayoutFragment> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<LayoutFragment> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<LayoutFragment> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<LayoutFragment> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public static LayoutFragment findByGroupId_First(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public static LayoutFragment findByGroupId_Last(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63;.
	*
	* @param layoutFragmentId the primary key of the current layout fragment
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public static LayoutFragment[] findByGroupId_PrevAndNext(
		long layoutFragmentId, long groupId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(layoutFragmentId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the layout fragments where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of layout fragments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout fragments
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the layout fragments where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @return the matching layout fragments
	*/
	public static List<LayoutFragment> findByG_P(long groupId, long plid) {
		return getPersistence().findByG_P(groupId, plid);
	}

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
	public static List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end) {
		return getPersistence().findByG_P(groupId, plid, start, end);
	}

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
	public static List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end, OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, plid, start, end, orderByComparator);
	}

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
	public static List<LayoutFragment> findByG_P(long groupId, long plid,
		int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, plid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public static LayoutFragment findByG_P_First(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().findByG_P_First(groupId, plid, orderByComparator);
	}

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByG_P_First(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, plid, orderByComparator);
	}

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment
	* @throws NoSuchFragmentException if a matching layout fragment could not be found
	*/
	public static LayoutFragment findByG_P_Last(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().findByG_P_Last(groupId, plid, orderByComparator);
	}

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByG_P_Last(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence().fetchByG_P_Last(groupId, plid, orderByComparator);
	}

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
	public static LayoutFragment[] findByG_P_PrevAndNext(
		long layoutFragmentId, long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence()
				   .findByG_P_PrevAndNext(layoutFragmentId, groupId, plid,
			orderByComparator);
	}

	/**
	* Removes all the layout fragments where groupId = &#63; and plid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param plid the plid
	*/
	public static void removeByG_P(long groupId, long plid) {
		getPersistence().removeByG_P(groupId, plid);
	}

	/**
	* Returns the number of layout fragments where groupId = &#63; and plid = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @return the number of matching layout fragments
	*/
	public static int countByG_P(long groupId, long plid) {
		return getPersistence().countByG_P(groupId, plid);
	}

	/**
	* Returns all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @return the matching layout fragments
	*/
	public static List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId) {
		return getPersistence().findByG_P_F(groupId, plid, fragmentEntryId);
	}

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
	public static List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end) {
		return getPersistence()
				   .findByG_P_F(groupId, plid, fragmentEntryId, start, end);
	}

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
	public static List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .findByG_P_F(groupId, plid, fragmentEntryId, start, end,
			orderByComparator);
	}

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
	public static List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_F(groupId, plid, fragmentEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static LayoutFragment findByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence()
				   .findByG_P_F_First(groupId, plid, fragmentEntryId,
			orderByComparator);
	}

	/**
	* Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_F_First(groupId, plid, fragmentEntryId,
			orderByComparator);
	}

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
	public static LayoutFragment findByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence()
				   .findByG_P_F_Last(groupId, plid, fragmentEntryId,
			orderByComparator);
	}

	/**
	* Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	*/
	public static LayoutFragment fetchByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_F_Last(groupId, plid, fragmentEntryId,
			orderByComparator);
	}

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
	public static LayoutFragment[] findByG_P_F_PrevAndNext(
		long layoutFragmentId, long groupId, long plid, long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence()
				   .findByG_P_F_PrevAndNext(layoutFragmentId, groupId, plid,
			fragmentEntryId, orderByComparator);
	}

	/**
	* Removes all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	*/
	public static void removeByG_P_F(long groupId, long plid,
		long fragmentEntryId) {
		getPersistence().removeByG_P_F(groupId, plid, fragmentEntryId);
	}

	/**
	* Returns the number of layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param plid the plid
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching layout fragments
	*/
	public static int countByG_P_F(long groupId, long plid, long fragmentEntryId) {
		return getPersistence().countByG_P_F(groupId, plid, fragmentEntryId);
	}

	/**
	* Caches the layout fragment in the entity cache if it is enabled.
	*
	* @param layoutFragment the layout fragment
	*/
	public static void cacheResult(LayoutFragment layoutFragment) {
		getPersistence().cacheResult(layoutFragment);
	}

	/**
	* Caches the layout fragments in the entity cache if it is enabled.
	*
	* @param layoutFragments the layout fragments
	*/
	public static void cacheResult(List<LayoutFragment> layoutFragments) {
		getPersistence().cacheResult(layoutFragments);
	}

	/**
	* Creates a new layout fragment with the primary key. Does not add the layout fragment to the database.
	*
	* @param layoutFragmentId the primary key for the new layout fragment
	* @return the new layout fragment
	*/
	public static LayoutFragment create(long layoutFragmentId) {
		return getPersistence().create(layoutFragmentId);
	}

	/**
	* Removes the layout fragment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment that was removed
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public static LayoutFragment remove(long layoutFragmentId)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().remove(layoutFragmentId);
	}

	public static LayoutFragment updateImpl(LayoutFragment layoutFragment) {
		return getPersistence().updateImpl(layoutFragment);
	}

	/**
	* Returns the layout fragment with the primary key or throws a {@link NoSuchFragmentException} if it could not be found.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment
	* @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	*/
	public static LayoutFragment findByPrimaryKey(long layoutFragmentId)
		throws com.liferay.layout.service.exception.NoSuchFragmentException {
		return getPersistence().findByPrimaryKey(layoutFragmentId);
	}

	/**
	* Returns the layout fragment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment, or <code>null</code> if a layout fragment with the primary key could not be found
	*/
	public static LayoutFragment fetchByPrimaryKey(long layoutFragmentId) {
		return getPersistence().fetchByPrimaryKey(layoutFragmentId);
	}

	public static java.util.Map<java.io.Serializable, LayoutFragment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the layout fragments.
	*
	* @return the layout fragments
	*/
	public static List<LayoutFragment> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LayoutFragment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LayoutFragment> findAll(int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LayoutFragment> findAll(int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the layout fragments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout fragments.
	*
	* @return the number of layout fragments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LayoutFragmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LayoutFragmentPersistence, LayoutFragmentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LayoutFragmentPersistence.class);
}