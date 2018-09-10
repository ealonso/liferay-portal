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

package com.liferay.layout.page.template.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException;
import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the layout page template setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.page.template.service.persistence.impl.LayoutPageTemplateSettingPersistenceImpl
 * @see LayoutPageTemplateSettingUtil
 * @generated
 */
@ProviderType
public interface LayoutPageTemplateSettingPersistence extends BasePersistence<LayoutPageTemplateSetting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutPageTemplateSettingUtil} to access the layout page template setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the layout page template settings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid(String uuid);

	/**
	* Returns a range of all the layout page template settings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @return the range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the layout page template settings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns an ordered range of all the layout page template settings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the layout page template settings before and after the current layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting[] findByUuid_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Removes all the layout page template settings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of layout page template settings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout page template settings
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the layout page template setting where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the layout page template setting that was removed
	*/
	public LayoutPageTemplateSetting removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the number of layout page template settings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching layout page template settings
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @return the range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns an ordered range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the layout page template settings before and after the current layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting[] findByUuid_C_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Removes all the layout page template settings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout page template settings
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the layout page template settings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByGroupId(long groupId);

	/**
	* Returns a range of all the layout page template settings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @return the range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the layout page template settings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns an ordered range of all the layout page template settings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the first layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the last layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the last layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the layout page template settings before and after the current layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting[] findByGroupId_PrevAndNext(
		long layoutPageTemplateSettingId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException;

	/**
	* Removes all the layout page template settings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of layout page template settings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout page template settings
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting findByG_C_C(long groupId,
		long classNameId, long classPK)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK);

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK, boolean retrieveFromCache);

	/**
	* Removes the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the layout page template setting that was removed
	*/
	public LayoutPageTemplateSetting removeByG_C_C(long groupId,
		long classNameId, long classPK)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the number of layout page template settings where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching layout page template settings
	*/
	public int countByG_C_C(long groupId, long classNameId, long classPK);

	/**
	* Caches the layout page template setting in the entity cache if it is enabled.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	*/
	public void cacheResult(LayoutPageTemplateSetting layoutPageTemplateSetting);

	/**
	* Caches the layout page template settings in the entity cache if it is enabled.
	*
	* @param layoutPageTemplateSettings the layout page template settings
	*/
	public void cacheResult(
		java.util.List<LayoutPageTemplateSetting> layoutPageTemplateSettings);

	/**
	* Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	*
	* @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	* @return the new layout page template setting
	*/
	public LayoutPageTemplateSetting create(long layoutPageTemplateSettingId);

	/**
	* Removes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting that was removed
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting remove(long layoutPageTemplateSettingId)
		throws NoSuchPageTemplateSettingException;

	public LayoutPageTemplateSetting updateImpl(
		LayoutPageTemplateSetting layoutPageTemplateSetting);

	/**
	* Returns the layout page template setting with the primary key or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting findByPrimaryKey(
		long layoutPageTemplateSettingId)
		throws NoSuchPageTemplateSettingException;

	/**
	* Returns the layout page template setting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting, or <code>null</code> if a layout page template setting with the primary key could not be found
	*/
	public LayoutPageTemplateSetting fetchByPrimaryKey(
		long layoutPageTemplateSettingId);

	@Override
	public java.util.Map<java.io.Serializable, LayoutPageTemplateSetting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the layout page template settings.
	*
	* @return the layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findAll();

	/**
	* Returns a range of all the layout page template settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @return the range of layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findAll(int start, int end);

	/**
	* Returns an ordered range of all the layout page template settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns an ordered range of all the layout page template settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of layout page template settings
	*/
	public java.util.List<LayoutPageTemplateSetting> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the layout page template settings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of layout page template settings.
	*
	* @return the number of layout page template settings
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}