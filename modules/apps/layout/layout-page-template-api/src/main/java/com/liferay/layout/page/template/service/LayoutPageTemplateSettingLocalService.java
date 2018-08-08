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

package com.liferay.layout.page.template.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for LayoutPageTemplateSetting. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSettingLocalServiceUtil
 * @see com.liferay.layout.page.template.service.base.LayoutPageTemplateSettingLocalServiceBaseImpl
 * @see com.liferay.layout.page.template.service.impl.LayoutPageTemplateSettingLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LayoutPageTemplateSettingLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutPageTemplateSettingLocalServiceUtil} to access the layout page template setting local service. Add custom service methods to {@link com.liferay.layout.page.template.service.impl.LayoutPageTemplateSettingLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the layout page template setting to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LayoutPageTemplateSetting addLayoutPageTemplateSetting(
		LayoutPageTemplateSetting layoutPageTemplateSetting);

	public LayoutPageTemplateSetting addLayoutPageTemplateSetting(long userId,
		long groupId, long classNameId, long classPK, String settings,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	*
	* @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	* @return the new layout page template setting
	*/
	@Transactional(enabled = false)
	public LayoutPageTemplateSetting createLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId);

	/**
	* Deletes the layout page template setting from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		LayoutPageTemplateSetting layoutPageTemplateSetting);

	/**
	* Deletes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting that was removed
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) throws PortalException;

	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK);

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutPageTemplateSetting fetchLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the layout page template setting with the primary key.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutPageTemplateSetting getLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) throws PortalException;

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting
	* @throws PortalException if a matching layout page template setting could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutPageTemplateSetting getLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	/**
	* Returns a range of all the layout page template settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @return the range of layout page template settings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutPageTemplateSetting> getLayoutPageTemplateSettings(
		int start, int end);

	/**
	* Returns all the layout page template settings matching the UUID and company.
	*
	* @param uuid the UUID of the layout page template settings
	* @param companyId the primary key of the company
	* @return the matching layout page template settings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of layout page template settings matching the UUID and company.
	*
	* @param uuid the UUID of the layout page template settings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of layout page template settings
	* @param end the upper bound of the range of layout page template settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching layout page template settings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator);

	/**
	* Returns the number of layout page template settings.
	*
	* @return the number of layout page template settings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLayoutPageTemplateSettingsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the layout page template setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
		LayoutPageTemplateSetting layoutPageTemplateSetting);

	public LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK, String settings)
		throws PortalException;
}