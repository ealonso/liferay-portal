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

package com.liferay.fragment.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FragmentLayoutTemplateLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentLayoutTemplateLinkLocalService
 * @generated
 */
@ProviderType
public class FragmentLayoutTemplateLinkLocalServiceWrapper
	implements FragmentLayoutTemplateLinkLocalService,
		ServiceWrapper<FragmentLayoutTemplateLinkLocalService> {
	public FragmentLayoutTemplateLinkLocalServiceWrapper(
		FragmentLayoutTemplateLinkLocalService fragmentLayoutTemplateLinkLocalService) {
		_fragmentLayoutTemplateLinkLocalService = fragmentLayoutTemplateLinkLocalService;
	}

	/**
	* Adds the fragment layout template link to the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLink the fragment layout template link
	* @return the fragment layout template link that was added
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink addFragmentLayoutTemplateLink(
		com.liferay.fragment.model.FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return _fragmentLayoutTemplateLinkLocalService.addFragmentLayoutTemplateLink(fragmentLayoutTemplateLink);
	}

	/**
	* Creates a new fragment layout template link with the primary key. Does not add the fragment layout template link to the database.
	*
	* @param fragmentLayoutTemplateLinkId the primary key for the new fragment layout template link
	* @return the new fragment layout template link
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink createFragmentLayoutTemplateLink(
		long fragmentLayoutTemplateLinkId) {
		return _fragmentLayoutTemplateLinkLocalService.createFragmentLayoutTemplateLink(fragmentLayoutTemplateLinkId);
	}

	/**
	* Deletes the fragment layout template link from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLink the fragment layout template link
	* @return the fragment layout template link that was removed
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink deleteFragmentLayoutTemplateLink(
		com.liferay.fragment.model.FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return _fragmentLayoutTemplateLinkLocalService.deleteFragmentLayoutTemplateLink(fragmentLayoutTemplateLink);
	}

	/**
	* Deletes the fragment layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link that was removed
	* @throws PortalException if a fragment layout template link with the primary key could not be found
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink deleteFragmentLayoutTemplateLink(
		long fragmentLayoutTemplateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentLayoutTemplateLinkLocalService.deleteFragmentLayoutTemplateLink(fragmentLayoutTemplateLinkId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentLayoutTemplateLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fragmentLayoutTemplateLinkLocalService.dynamicQuery();
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
		return _fragmentLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fragmentLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fragmentLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery,
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
		return _fragmentLayoutTemplateLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fragmentLayoutTemplateLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink fetchFragmentLayoutTemplateLink(
		long fragmentLayoutTemplateLinkId) {
		return _fragmentLayoutTemplateLinkLocalService.fetchFragmentLayoutTemplateLink(fragmentLayoutTemplateLinkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fragmentLayoutTemplateLinkLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the fragment layout template link with the primary key.
	*
	* @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	* @return the fragment layout template link
	* @throws PortalException if a fragment layout template link with the primary key could not be found
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink getFragmentLayoutTemplateLink(
		long fragmentLayoutTemplateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentLayoutTemplateLinkLocalService.getFragmentLayoutTemplateLink(fragmentLayoutTemplateLinkId);
	}

	/**
	* Returns a range of all the fragment layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment layout template links
	* @param end the upper bound of the range of fragment layout template links (not inclusive)
	* @return the range of fragment layout template links
	*/
	@Override
	public java.util.List<com.liferay.fragment.model.FragmentLayoutTemplateLink> getFragmentLayoutTemplateLinks(
		int start, int end) {
		return _fragmentLayoutTemplateLinkLocalService.getFragmentLayoutTemplateLinks(start,
			end);
	}

	/**
	* Returns the number of fragment layout template links.
	*
	* @return the number of fragment layout template links
	*/
	@Override
	public int getFragmentLayoutTemplateLinksCount() {
		return _fragmentLayoutTemplateLinkLocalService.getFragmentLayoutTemplateLinksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fragmentLayoutTemplateLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fragmentLayoutTemplateLinkLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentLayoutTemplateLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the fragment layout template link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fragmentLayoutTemplateLink the fragment layout template link
	* @return the fragment layout template link that was updated
	*/
	@Override
	public com.liferay.fragment.model.FragmentLayoutTemplateLink updateFragmentLayoutTemplateLink(
		com.liferay.fragment.model.FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return _fragmentLayoutTemplateLinkLocalService.updateFragmentLayoutTemplateLink(fragmentLayoutTemplateLink);
	}

	@Override
	public FragmentLayoutTemplateLinkLocalService getWrappedService() {
		return _fragmentLayoutTemplateLinkLocalService;
	}

	@Override
	public void setWrappedService(
		FragmentLayoutTemplateLinkLocalService fragmentLayoutTemplateLinkLocalService) {
		_fragmentLayoutTemplateLinkLocalService = fragmentLayoutTemplateLinkLocalService;
	}

	private FragmentLayoutTemplateLinkLocalService _fragmentLayoutTemplateLinkLocalService;
}