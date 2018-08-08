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

import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the layout page template setting service. This utility wraps {@link com.liferay.layout.page.template.service.persistence.impl.LayoutPageTemplateSettingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSettingPersistence
 * @see com.liferay.layout.page.template.service.persistence.impl.LayoutPageTemplateSettingPersistenceImpl
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingUtil {
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
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		getPersistence().clearCache(layoutPageTemplateSetting);
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
	public static List<LayoutPageTemplateSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutPageTemplateSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutPageTemplateSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LayoutPageTemplateSetting update(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return getPersistence().update(layoutPageTemplateSetting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LayoutPageTemplateSetting update(
		LayoutPageTemplateSetting layoutPageTemplateSetting,
		ServiceContext serviceContext) {
		return getPersistence().update(layoutPageTemplateSetting, serviceContext);
	}

	/**
	* Returns all the layout page template settings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout page template settings
	*/
	public static List<LayoutPageTemplateSetting> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByUuid_First(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUuid_First(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByUuid_Last(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUuid_Last(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the layout page template settings before and after the current layout page template setting in the ordered set where uuid = &#63;.
	*
	* @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public static LayoutPageTemplateSetting[] findByUuid_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(layoutPageTemplateSettingId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the layout page template settings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of layout page template settings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout page template settings
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByUUID_G(String uuid,
		long groupId)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the layout page template setting where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the layout page template setting that was removed
	*/
	public static LayoutPageTemplateSetting removeByUUID_G(String uuid,
		long groupId)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of layout page template settings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching layout page template settings
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout page template settings
	*/
	public static List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static LayoutPageTemplateSetting[] findByUuid_C_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid, long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(layoutPageTemplateSettingId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the layout page template settings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of layout page template settings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout page template settings
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the layout page template settings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout page template settings
	*/
	public static List<LayoutPageTemplateSetting> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByGroupId_First(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByGroupId_Last(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the layout page template settings before and after the current layout page template setting in the ordered set where groupId = &#63;.
	*
	* @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public static LayoutPageTemplateSetting[] findByGroupId_PrevAndNext(
		long layoutPageTemplateSettingId, long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(layoutPageTemplateSettingId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the layout page template settings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of layout page template settings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout page template settings
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching layout page template setting
	* @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting findByG_C_C(long groupId,
		long classNameId, long classPK)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByG_C_C(groupId, classNameId, classPK);
	}

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK) {
		return getPersistence().fetchByG_C_C(groupId, classNameId, classPK);
	}

	/**
	* Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_C_C(groupId, classNameId, classPK,
			retrieveFromCache);
	}

	/**
	* Removes the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the layout page template setting that was removed
	*/
	public static LayoutPageTemplateSetting removeByG_C_C(long groupId,
		long classNameId, long classPK)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().removeByG_C_C(groupId, classNameId, classPK);
	}

	/**
	* Returns the number of layout page template settings where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching layout page template settings
	*/
	public static int countByG_C_C(long groupId, long classNameId, long classPK) {
		return getPersistence().countByG_C_C(groupId, classNameId, classPK);
	}

	/**
	* Caches the layout page template setting in the entity cache if it is enabled.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	*/
	public static void cacheResult(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		getPersistence().cacheResult(layoutPageTemplateSetting);
	}

	/**
	* Caches the layout page template settings in the entity cache if it is enabled.
	*
	* @param layoutPageTemplateSettings the layout page template settings
	*/
	public static void cacheResult(
		List<LayoutPageTemplateSetting> layoutPageTemplateSettings) {
		getPersistence().cacheResult(layoutPageTemplateSettings);
	}

	/**
	* Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	*
	* @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	* @return the new layout page template setting
	*/
	public static LayoutPageTemplateSetting create(
		long layoutPageTemplateSettingId) {
		return getPersistence().create(layoutPageTemplateSettingId);
	}

	/**
	* Removes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting that was removed
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public static LayoutPageTemplateSetting remove(
		long layoutPageTemplateSettingId)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().remove(layoutPageTemplateSettingId);
	}

	public static LayoutPageTemplateSetting updateImpl(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return getPersistence().updateImpl(layoutPageTemplateSetting);
	}

	/**
	* Returns the layout page template setting with the primary key or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting
	* @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	*/
	public static LayoutPageTemplateSetting findByPrimaryKey(
		long layoutPageTemplateSettingId)
		throws com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException {
		return getPersistence().findByPrimaryKey(layoutPageTemplateSettingId);
	}

	/**
	* Returns the layout page template setting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting, or <code>null</code> if a layout page template setting with the primary key could not be found
	*/
	public static LayoutPageTemplateSetting fetchByPrimaryKey(
		long layoutPageTemplateSettingId) {
		return getPersistence().fetchByPrimaryKey(layoutPageTemplateSettingId);
	}

	public static java.util.Map<java.io.Serializable, LayoutPageTemplateSetting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the layout page template settings.
	*
	* @return the layout page template settings
	*/
	public static List<LayoutPageTemplateSetting> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LayoutPageTemplateSetting> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LayoutPageTemplateSetting> findAll(int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LayoutPageTemplateSetting> findAll(int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the layout page template settings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout page template settings.
	*
	* @return the number of layout page template settings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LayoutPageTemplateSettingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LayoutPageTemplateSettingPersistence, LayoutPageTemplateSettingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LayoutPageTemplateSettingPersistence.class);

		ServiceTracker<LayoutPageTemplateSettingPersistence, LayoutPageTemplateSettingPersistence> serviceTracker =
			new ServiceTracker<LayoutPageTemplateSettingPersistence, LayoutPageTemplateSettingPersistence>(bundle.getBundleContext(),
				LayoutPageTemplateSettingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}