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

package com.liferay.fragment.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the fragment entry layout template link service. This utility wraps {@link com.liferay.fragment.service.persistence.impl.FragmentEntryLayoutTemplateLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLayoutTemplateLinkPersistence
 * @see com.liferay.fragment.service.persistence.impl.FragmentEntryLayoutTemplateLinkPersistenceImpl
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkUtil {
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
	public static void clearCache(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		getPersistence().clearCache(fragmentEntryLayoutTemplateLink);
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
	public static List<FragmentEntryLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FragmentEntryLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FragmentEntryLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FragmentEntryLayoutTemplateLink update(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return getPersistence().update(fragmentEntryLayoutTemplateLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FragmentEntryLayoutTemplateLink update(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(fragmentEntryLayoutTemplateLink, serviceContext);
	}

	/**
	* Returns all the fragment entry layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the fragment entry layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @return the range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByGroupId_First(
		long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByGroupId_First(
		long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByGroupId_Last(
		long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByGroupId_Last(
		long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(fragmentEntryLayoutTemplateLinkId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching fragment entry layout template links
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId) {
		return getPersistence().findByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns a range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @return the range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end) {
		return getPersistence().findByG_F(groupId, fragmentEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByG_F_First(
		long groupId, long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_First(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByG_F_First(
		long groupId, long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_F_First(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_Last(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByG_F_Last(
		long groupId, long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_F_Last(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_PrevAndNext(fragmentEntryLayoutTemplateLinkId,
			groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	*/
	public static void removeByG_F(long groupId, long fragmentEntryId) {
		getPersistence().removeByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching fragment entry layout template links
	*/
	public static int countByG_F(long groupId, long fragmentEntryId) {
		return getPersistence().countByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId) {
		return getPersistence().findByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Returns a range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @return the range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByG_L_First(
		long groupId, long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_First(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByG_L_First(
		long groupId, long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_L_First(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_Last(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByG_L_Last(
		long groupId, long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_L_Last(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_PrevAndNext(fragmentEntryLayoutTemplateLinkId,
			groupId, layoutPageTemplateEntryId, orderByComparator);
	}

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	*/
	public static void removeByG_L(long groupId, long layoutPageTemplateEntryId) {
		getPersistence().removeByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the number of matching fragment entry layout template links
	*/
	public static int countByG_L(long groupId, long layoutPageTemplateEntryId) {
		return getPersistence().countByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Caches the fragment entry layout template link in the entity cache if it is enabled.
	*
	* @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	*/
	public static void cacheResult(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		getPersistence().cacheResult(fragmentEntryLayoutTemplateLink);
	}

	/**
	* Caches the fragment entry layout template links in the entity cache if it is enabled.
	*
	* @param fragmentEntryLayoutTemplateLinks the fragment entry layout template links
	*/
	public static void cacheResult(
		List<FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks) {
		getPersistence().cacheResult(fragmentEntryLayoutTemplateLinks);
	}

	/**
	* Creates a new fragment entry layout template link with the primary key. Does not add the fragment entry layout template link to the database.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key for the new fragment entry layout template link
	* @return the new fragment entry layout template link
	*/
	public static FragmentEntryLayoutTemplateLink create(
		long fragmentEntryLayoutTemplateLinkId) {
		return getPersistence().create(fragmentEntryLayoutTemplateLinkId);
	}

	/**
	* Removes the fragment entry layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link that was removed
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink remove(
		long fragmentEntryLayoutTemplateLinkId)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence().remove(fragmentEntryLayoutTemplateLinkId);
	}

	public static FragmentEntryLayoutTemplateLink updateImpl(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return getPersistence().updateImpl(fragmentEntryLayoutTemplateLink);
	}

	/**
	* Returns the fragment entry layout template link with the primary key or throws a {@link NoSuchEntryLayoutTemplateLinkException} if it could not be found.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink findByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId)
		throws com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException {
		return getPersistence()
				   .findByPrimaryKey(fragmentEntryLayoutTemplateLinkId);
	}

	/**
	* Returns the fragment entry layout template link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link, or <code>null</code> if a fragment entry layout template link with the primary key could not be found
	*/
	public static FragmentEntryLayoutTemplateLink fetchByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId) {
		return getPersistence()
				   .fetchByPrimaryKey(fragmentEntryLayoutTemplateLinkId);
	}

	public static java.util.Map<java.io.Serializable, FragmentEntryLayoutTemplateLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the fragment entry layout template links.
	*
	* @return the fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the fragment entry layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @return the range of fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment entry layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of fragment entry layout template links
	*/
	public static List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the fragment entry layout template links from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of fragment entry layout template links.
	*
	* @return the number of fragment entry layout template links
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FragmentEntryLayoutTemplateLinkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FragmentEntryLayoutTemplateLinkPersistence, FragmentEntryLayoutTemplateLinkPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FragmentEntryLayoutTemplateLinkPersistence.class);
}