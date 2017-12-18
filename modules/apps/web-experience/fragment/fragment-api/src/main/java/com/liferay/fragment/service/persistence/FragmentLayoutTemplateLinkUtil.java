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

import com.liferay.fragment.model.FragmentLayoutTemplateLink;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the fragment layout template link service. This utility wraps {@link com.liferay.fragment.service.persistence.impl.FragmentLayoutTemplateLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentLayoutTemplateLinkPersistence
 * @see com.liferay.fragment.service.persistence.impl.FragmentLayoutTemplateLinkPersistenceImpl
 * @generated
 */
@ProviderType
public class FragmentLayoutTemplateLinkUtil {
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
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		getPersistence().clearCache(fragmentLayoutTemplateLink);
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
	public static List<FragmentLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FragmentLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FragmentLayoutTemplateLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FragmentLayoutTemplateLink update(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return getPersistence().update(fragmentLayoutTemplateLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FragmentLayoutTemplateLink update(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(fragmentLayoutTemplateLink, serviceContext);
	}

	/**
	* Returns all the fragment layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the fragment layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @return the range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByGroupId_First(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByGroupId_First(
		long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByGroupId_Last(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByGroupId_Last(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(fragmentLayoutTemplateLinkId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the fragment layout template links where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of fragment layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching fragment layout template links
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId) {
		return getPersistence().findByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns a range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @return the range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end) {
		return getPersistence().findByG_F(groupId, fragmentEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_First(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_F_First(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_Last(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_F_Last(groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId, long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_F_PrevAndNext(fragmentLayoutTemplateLinkId,
			groupId, fragmentEntryId, orderByComparator);
	}

	/**
	* Removes all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	*/
	public static void removeByG_F(long groupId, long fragmentEntryId) {
		getPersistence().removeByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns the number of fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching fragment layout template links
	*/
	public static int countByG_F(long groupId, long fragmentEntryId) {
		return getPersistence().countByG_F(groupId, fragmentEntryId);
	}

	/**
	* Returns all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId) {
		return getPersistence().findByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Returns a range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @return the range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_First(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_L_First(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_Last(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence()
				   .fetchByG_L_Last(groupId, layoutPageTemplateEntryId,
			orderByComparator);
	}

	/**
	* Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence()
				   .findByG_L_PrevAndNext(fragmentLayoutTemplateLinkId,
			groupId, layoutPageTemplateEntryId, orderByComparator);
	}

	/**
	* Removes all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	*/
	public static void removeByG_L(long groupId, long layoutPageTemplateEntryId) {
		getPersistence().removeByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Returns the number of fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the number of matching fragment layout template links
	*/
	public static int countByG_L(long groupId, long layoutPageTemplateEntryId) {
		return getPersistence().countByG_L(groupId, layoutPageTemplateEntryId);
	}

	/**
	* Caches the fragment layout template link in the entity cache if it is enabled.
	*
	* @param fragmentLayoutTemplateLink the fragment layout template link
	*/
	public static void cacheResult(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		getPersistence().cacheResult(fragmentLayoutTemplateLink);
	}

	/**
	* Caches the fragment layout template links in the entity cache if it is enabled.
	*
	* @param fragmentLayoutTemplateLinks the fragment layout template links
	*/
	public static void cacheResult(
		List<FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks) {
		getPersistence().cacheResult(fragmentLayoutTemplateLinks);
	}

	/**
	* Creates a new fragment layout template link with the primary key. Does not add the fragment layout template link to the database.
	*
	* @param fragmentLayoutTemplateLinkId the primary key for the new fragment layout template link
	* @return the new fragment layout template link
	*/
	public static FragmentLayoutTemplateLink create(
		long fragmentLayoutTemplateLinkId) {
		return getPersistence().create(fragmentLayoutTemplateLinkId);
	}

	/**
	* Removes the fragment layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link that was removed
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink remove(
		long fragmentLayoutTemplateLinkId)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence().remove(fragmentLayoutTemplateLinkId);
	}

	public static FragmentLayoutTemplateLink updateImpl(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return getPersistence().updateImpl(fragmentLayoutTemplateLink);
	}

	/**
	* Returns the fragment layout template link with the primary key or throws a {@link NoSuchLayoutTemplateLinkException} if it could not be found.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink findByPrimaryKey(
		long fragmentLayoutTemplateLinkId)
		throws com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException {
		return getPersistence().findByPrimaryKey(fragmentLayoutTemplateLinkId);
	}

	/**
	* Returns the fragment layout template link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link, or <code>null</code> if a fragment layout template link with the primary key could not be found
	*/
	public static FragmentLayoutTemplateLink fetchByPrimaryKey(
		long fragmentLayoutTemplateLinkId) {
		return getPersistence().fetchByPrimaryKey(fragmentLayoutTemplateLinkId);
	}

	public static java.util.Map<java.io.Serializable, FragmentLayoutTemplateLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the fragment layout template links.
	*
	* @return the fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the fragment layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @return the range of fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the fragment layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the fragment layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of fragment layout template links
	*/
	public static List<FragmentLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the fragment layout template links from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of fragment layout template links.
	*
	* @return the number of fragment layout template links
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FragmentLayoutTemplateLinkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FragmentLayoutTemplateLinkPersistence, FragmentLayoutTemplateLinkPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FragmentLayoutTemplateLinkPersistence.class);
}