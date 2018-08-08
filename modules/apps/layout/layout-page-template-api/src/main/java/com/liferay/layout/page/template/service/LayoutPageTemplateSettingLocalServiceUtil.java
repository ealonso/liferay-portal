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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LayoutPageTemplateSetting. This utility wraps
 * {@link com.liferay.layout.page.template.service.impl.LayoutPageTemplateSettingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSettingLocalService
 * @see com.liferay.layout.page.template.service.base.LayoutPageTemplateSettingLocalServiceBaseImpl
 * @see com.liferay.layout.page.template.service.impl.LayoutPageTemplateSettingLocalServiceImpl
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.layout.page.template.service.impl.LayoutPageTemplateSettingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the layout page template setting to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was added
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting addLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return getService()
				   .addLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	/**
	* Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	*
	* @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	* @return the new layout page template setting
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting createLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) {
		return getService()
				   .createLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* Deletes the layout page template setting from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was removed
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return getService()
				   .deleteLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	/**
	* Deletes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting that was removed
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId) {
		return getService()
				   .fetchLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting fetchLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchLayoutPageTemplateSettingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the layout page template setting with the primary key.
	*
	* @param layoutPageTemplateSettingId the primary key of the layout page template setting
	* @return the layout page template setting
	* @throws PortalException if a layout page template setting with the primary key could not be found
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting getLayoutPageTemplateSetting(
		long layoutPageTemplateSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLayoutPageTemplateSetting(layoutPageTemplateSettingId);
	}

	/**
	* Returns the layout page template setting matching the UUID and group.
	*
	* @param uuid the layout page template setting's UUID
	* @param groupId the primary key of the group
	* @return the matching layout page template setting
	* @throws PortalException if a matching layout page template setting could not be found
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting getLayoutPageTemplateSettingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLayoutPageTemplateSettingByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettings(
		int start, int end) {
		return getService().getLayoutPageTemplateSettings(start, end);
	}

	/**
	* Returns all the layout page template settings matching the UUID and company.
	*
	* @param uuid the UUID of the layout page template settings
	* @param companyId the primary key of the company
	* @return the matching layout page template settings, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getLayoutPageTemplateSettingsByUuidAndCompanyId(uuid,
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
	public static java.util.List<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> getLayoutPageTemplateSettingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.layout.page.template.model.LayoutPageTemplateSetting> orderByComparator) {
		return getService()
				   .getLayoutPageTemplateSettingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of layout page template settings.
	*
	* @return the number of layout page template settings
	*/
	public static int getLayoutPageTemplateSettingsCount() {
		return getService().getLayoutPageTemplateSettingsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the layout page template setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutPageTemplateSetting the layout page template setting
	* @return the layout page template setting that was updated
	*/
	public static com.liferay.layout.page.template.model.LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
		com.liferay.layout.page.template.model.LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return getService()
				   .updateLayoutPageTemplateSetting(layoutPageTemplateSetting);
	}

	public static LayoutPageTemplateSettingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LayoutPageTemplateSettingLocalService, LayoutPageTemplateSettingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LayoutPageTemplateSettingLocalService.class);

		ServiceTracker<LayoutPageTemplateSettingLocalService, LayoutPageTemplateSettingLocalService> serviceTracker =
			new ServiceTracker<LayoutPageTemplateSettingLocalService, LayoutPageTemplateSettingLocalService>(bundle.getBundleContext(),
				LayoutPageTemplateSettingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}