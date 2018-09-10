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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutPageTemplateSettingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSettingLocalService
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingLocalServiceWrapper
	implements LayoutPageTemplateSettingLocalService,
		ServiceWrapper<LayoutPageTemplateSettingLocalService> {
	public LayoutPageTemplateSettingLocalServiceWrapper(
		LayoutPageTemplateSettingLocalService layoutPageTemplateSettingLocalService) {
		_layoutPageTemplateSettingLocalService = layoutPageTemplateSettingLocalService;
	}

	/**
	* Adds the layout page template setting to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was added
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting addLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return _layoutPageTemplateSettingLocalService.addLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting addLayoutPageTemplateSetting(
		long userId, long groupId, long classNameId, long classPK,
		String settings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.addLayoutPageTemplateSetting(userId,
			groupId, classNameId, classPK, settings, serviceContext);
	}

	/**
	* Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	*
	* @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	* @return the new layout page template setting
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting createLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) {
		return _layoutPageTemplateSettingLocalService.createLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* Deletes the layout page template setting from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was removed
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return _layoutPageTemplateSettingLocalService.deleteLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	/**
	* Deletes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting that was removed
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.deleteLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.deleteLayoutPageTemplateSetting(groupId,
			classNameId, classPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _layoutPageTemplateSettingLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _layoutPageTemplateSettingLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _layoutPageTemplateSettingLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _layoutPageTemplateSettingLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _layoutPageTemplateSettingLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _layoutPageTemplateSettingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) {
		return _layoutPageTemplateSettingLocalService.fetchLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK) {
		return _layoutPageTemplateSettingLocalService.fetchLayoutPageTemplateSetting(groupId,
			classNameId, classPK);
	}

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting fetchLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId) {
		return _layoutPageTemplateSettingLocalService.fetchLayoutPageTemplateSettingByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _layoutPageTemplateSettingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _layoutPageTemplateSettingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _layoutPageTemplateSettingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the layout page template setting with the primary key.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting getLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting
	* @throws PortalException if a matching layout page template setting could not be found
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting getLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSettingByUuidAndGroupId(uuid,
			groupId);
	}

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
	@Override
	public java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettings(
		int start, int end) {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSettings(start,
			end);
	}

	/**
	* Returns all the layout page template settings matching the UUID and company.
	*
	* @param uuid the UUID of the layout page template settings
	* @param companyId the primary key of the company
	* @return the matching layout page template settings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSettingsByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> orderByComparator) {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSettingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of layout page template settings.
	*
	* @return the number of layout page template settings
	*/
	@Override
	public int getLayoutPageTemplateSettingsCount() {
		return _layoutPageTemplateSettingLocalService.getLayoutPageTemplateSettingsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutPageTemplateSettingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the layout page template setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was updated
	*/
	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return _layoutPageTemplateSettingLocalService.updateLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK, String settings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutPageTemplateSettingLocalService.updateLayoutPageTemplateSetting(groupId,
			classNameId, classPK, settings);
	}

	@Override
	public LayoutPageTemplateSettingLocalService getWrappedService() {
		return _layoutPageTemplateSettingLocalService;
	}

	@Override
	public void setWrappedService(
		LayoutPageTemplateSettingLocalService layoutPageTemplateSettingLocalService) {
		_layoutPageTemplateSettingLocalService = layoutPageTemplateSettingLocalService;
	}

	private LayoutPageTemplateSettingLocalService _layoutPageTemplateSettingLocalService;
}