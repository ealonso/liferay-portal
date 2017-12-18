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

import com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentLayoutTemplateLink;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the fragment layout template link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.fragment.service.persistence.impl.FragmentLayoutTemplateLinkPersistenceImpl
 * @see FragmentLayoutTemplateLinkUtil
 * @generated
 */
@ProviderType
public interface FragmentLayoutTemplateLinkPersistence extends BasePersistence<FragmentLayoutTemplateLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FragmentLayoutTemplateLinkUtil} to access the fragment layout template link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the fragment layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching fragment layout template links
	*/
	public java.util.List<FragmentLayoutTemplateLink> findByGroupId(
		long groupId);

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
	public java.util.List<FragmentLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FragmentLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentLayoutTemplateLink> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

	/**
	* Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63;.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public FragmentLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Removes all the fragment layout template links where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of fragment layout template links where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching fragment layout template links
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the matching fragment layout template links
	*/
	public java.util.List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByG_F_First(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByG_F_First(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByG_F_Last(long groupId,
		long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public FragmentLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId, long fragmentEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Removes all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	*/
	public void removeByG_F(long groupId, long fragmentEntryId);

	/**
	* Returns the number of fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param fragmentEntryId the fragment entry ID
	* @return the number of matching fragment layout template links
	*/
	public int countByG_F(long groupId, long fragmentEntryId);

	/**
	* Returns all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the matching fragment layout template links
	*/
	public java.util.List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	*/
	public FragmentLayoutTemplateLink fetchByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public FragmentLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Removes all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	*/
	public void removeByG_L(long groupId, long layoutPageTemplateEntryId);

	/**
	* Returns the number of fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	*
	* @param groupId the group ID
	* @param layoutPageTemplateEntryId the layout page template entry ID
	* @return the number of matching fragment layout template links
	*/
	public int countByG_L(long groupId, long layoutPageTemplateEntryId);

	/**
	* Caches the fragment layout template link in the entity cache if it is enabled.
	*
	* @param fragmentLayoutTemplateLink the fragment layout template link
	*/
	public void cacheResult(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink);

	/**
	* Caches the fragment layout template links in the entity cache if it is enabled.
	*
	* @param fragmentLayoutTemplateLinks the fragment layout template links
	*/
	public void cacheResult(
		java.util.List<FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks);

	/**
	* Creates a new fragment layout template link with the primary key. Does not add the fragment layout template link to the database.
	*
	* @param fragmentLayoutTemplateLinkId the primary key for the new fragment layout template link
	* @return the new fragment layout template link
	*/
	public FragmentLayoutTemplateLink create(long fragmentLayoutTemplateLinkId);

	/**
	* Removes the fragment layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link that was removed
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public FragmentLayoutTemplateLink remove(long fragmentLayoutTemplateLinkId)
		throws NoSuchLayoutTemplateLinkException;

	public FragmentLayoutTemplateLink updateImpl(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink);

	/**
	* Returns the fragment layout template link with the primary key or throws a {@link NoSuchLayoutTemplateLinkException} if it could not be found.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link
	* @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	*/
	public FragmentLayoutTemplateLink findByPrimaryKey(
		long fragmentLayoutTemplateLinkId)
		throws NoSuchLayoutTemplateLinkException;

	/**
	* Returns the fragment layout template link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link, or <code>null</code> if a fragment layout template link with the primary key could not be found
	*/
	public FragmentLayoutTemplateLink fetchByPrimaryKey(
		long fragmentLayoutTemplateLinkId);

	@Override
	public java.util.Map<java.io.Serializable, FragmentLayoutTemplateLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the fragment layout template links.
	*
	* @return the fragment layout template links
	*/
	public java.util.List<FragmentLayoutTemplateLink> findAll();

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
	public java.util.List<FragmentLayoutTemplateLink> findAll(int start, int end);

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
	public java.util.List<FragmentLayoutTemplateLink> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator);

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
	public java.util.List<FragmentLayoutTemplateLink> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the fragment layout template links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of fragment layout template links.
	*
	* @return the number of fragment layout template links
	*/
	public int countAll();
}