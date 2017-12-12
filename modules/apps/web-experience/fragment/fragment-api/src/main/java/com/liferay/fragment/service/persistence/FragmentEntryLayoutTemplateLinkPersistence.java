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

import com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the fragment entry layout template link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.fragment.service.persistence.impl.FragmentEntryLayoutTemplateLinkPersistenceImpl
 * @see FragmentEntryLayoutTemplateLinkUtil
 * @generated
 */
@ProviderType
public interface FragmentEntryLayoutTemplateLinkPersistence
	extends BasePersistence<FragmentEntryLayoutTemplateLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FragmentEntryLayoutTemplateLinkUtil} to access the fragment entry layout template link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the fragment entry layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching fragment entry layout template links
	*/
	public java.util.List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

	/**
	* Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63;.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public FragmentEntryLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching fragment entry layout template links
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the matching fragment entry layout template links
	*/
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByG_F_First(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByG_F_First(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByG_F_Last(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public FragmentEntryLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	*/
	public void removeByG_F(long groupId, long fragmentEntryId);

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching fragment entry layout template links
	*/
	public int countByG_F(long groupId, long fragmentEntryId);

	/**
	* Returns all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the matching fragment entry layout template links
	*/
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findByG_L(
		long groupId, long layoutPageTemplateEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public FragmentEntryLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Removes all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	*/
	public void removeByG_L(long groupId, long layoutPageTemplateEntryId);

	/**
	* Returns the number of fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the number of matching fragment entry layout template links
	*/
	public int countByG_L(long groupId, long layoutPageTemplateEntryId);

	/**
	* Caches the fragment entry layout template link in the entity cache if it is enabled.
	*
	* @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	*/
	public void cacheResult(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink);

	/**
	* Caches the fragment entry layout template links in the entity cache if it is enabled.
	*
	* @param fragmentEntryLayoutTemplateLinks the fragment entry layout template links
	*/
	public void cacheResult(
		java.util.List<FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks);

	/**
	* Creates a new fragment entry layout template link with the primary key. Does not add the fragment entry layout template link to the database.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key for the new fragment entry layout template link
	* @return the new fragment entry layout template link
	*/
	public FragmentEntryLayoutTemplateLink create(
		long fragmentEntryLayoutTemplateLinkId);

	/**
	* Removes the fragment entry layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link that was removed
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public FragmentEntryLayoutTemplateLink remove(
		long fragmentEntryLayoutTemplateLinkId)
		throws NoSuchEntryLayoutTemplateLinkException;

	public FragmentEntryLayoutTemplateLink updateImpl(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink);

	/**
	* Returns the fragment entry layout template link with the primary key or throws a {@link NoSuchEntryLayoutTemplateLinkException} if it could not be found.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link
	* @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	*/
	public FragmentEntryLayoutTemplateLink findByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId)
		throws NoSuchEntryLayoutTemplateLinkException;

	/**
	* Returns the fragment entry layout template link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link, or <code>null</code> if a fragment entry layout template link with the primary key could not be found
	*/
	public FragmentEntryLayoutTemplateLink fetchByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId);

	@Override
	public java.util.Map<java.io.Serializable, FragmentEntryLayoutTemplateLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the fragment entry layout template links.
	*
	* @return the fragment entry layout template links
	*/
	public java.util.List<FragmentEntryLayoutTemplateLink> findAll();

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentEntryLayoutTemplateLink> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the fragment entry layout template links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of fragment entry layout template links.
	*
	* @return the number of fragment entry layout template links
	*/
	public int countAll();
}