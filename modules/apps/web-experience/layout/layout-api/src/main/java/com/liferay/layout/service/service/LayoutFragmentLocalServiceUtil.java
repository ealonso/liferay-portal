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

package com.liferay.layout.service.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LayoutFragment. This utility wraps
 * {@link com.liferay.layout.service.service.impl.LayoutFragmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentLocalService
 * @see com.liferay.layout.service.service.base.LayoutFragmentLocalServiceBaseImpl
 * @see com.liferay.layout.service.service.impl.LayoutFragmentLocalServiceImpl
 * @generated
 */
@ProviderType
public class LayoutFragmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.layout.service.service.impl.LayoutFragmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the layout fragment to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFragment the layout fragment
	* @return the layout fragment that was added
	*/
	public static com.liferay.layout.service.model.LayoutFragment addLayoutFragment(
		com.liferay.layout.service.model.LayoutFragment layoutFragment) {
		return getService().addLayoutFragment(layoutFragment);
	}

	/**
	* Creates a new layout fragment with the primary key. Does not add the layout fragment to the database.
	*
	* @param layoutFragmentId the primary key for the new layout fragment
	* @return the new layout fragment
	*/
	public static com.liferay.layout.service.model.LayoutFragment createLayoutFragment(
		long layoutFragmentId) {
		return getService().createLayoutFragment(layoutFragmentId);
	}

	/**
	* Deletes the layout fragment from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFragment the layout fragment
	* @return the layout fragment that was removed
	*/
	public static com.liferay.layout.service.model.LayoutFragment deleteLayoutFragment(
		com.liferay.layout.service.model.LayoutFragment layoutFragment) {
		return getService().deleteLayoutFragment(layoutFragment);
	}

	/**
	* Deletes the layout fragment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment that was removed
	* @throws PortalException if a layout fragment with the primary key could not be found
	*/
	public static com.liferay.layout.service.model.LayoutFragment deleteLayoutFragment(
		long layoutFragmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLayoutFragment(layoutFragmentId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.service.model.impl.LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.service.model.impl.LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.layout.service.model.LayoutFragment fetchLayoutFragment(
		long layoutFragmentId) {
		return getService().fetchLayoutFragment(layoutFragmentId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the layout fragment with the primary key.
	*
	* @param layoutFragmentId the primary key of the layout fragment
	* @return the layout fragment
	* @throws PortalException if a layout fragment with the primary key could not be found
	*/
	public static com.liferay.layout.service.model.LayoutFragment getLayoutFragment(
		long layoutFragmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutFragment(layoutFragmentId);
	}

	/**
	* Returns a range of all the layout fragments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.service.model.impl.LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout fragments
	* @param end the upper bound of the range of layout fragments (not inclusive)
	* @return the range of layout fragments
	*/
	public static java.util.List<com.liferay.layout.service.model.LayoutFragment> getLayoutFragments(
		int start, int end) {
		return getService().getLayoutFragments(start, end);
	}

	/**
	* Returns the number of layout fragments.
	*
	* @return the number of layout fragments
	*/
	public static int getLayoutFragmentsCount() {
		return getService().getLayoutFragmentsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the layout fragment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutFragment the layout fragment
	* @return the layout fragment that was updated
	*/
	public static com.liferay.layout.service.model.LayoutFragment updateLayoutFragment(
		com.liferay.layout.service.model.LayoutFragment layoutFragment) {
		return getService().updateLayoutFragment(layoutFragment);
	}

	public static LayoutFragmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LayoutFragmentLocalService, LayoutFragmentLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LayoutFragmentLocalService.class);
}