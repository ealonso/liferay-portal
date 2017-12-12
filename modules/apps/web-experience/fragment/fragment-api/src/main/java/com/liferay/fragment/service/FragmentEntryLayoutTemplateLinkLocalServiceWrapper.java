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
 * Provides a wrapper for {@link FragmentEntryLayoutTemplateLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLayoutTemplateLinkLocalService
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkLocalServiceWrapper
	implements FragmentEntryLayoutTemplateLinkLocalService,
		ServiceWrapper<FragmentEntryLayoutTemplateLinkLocalService> {
	public FragmentEntryLayoutTemplateLinkLocalServiceWrapper(
		FragmentEntryLayoutTemplateLinkLocalService fragmentEntryLayoutTemplateLinkLocalService) {
		_fragmentEntryLayoutTemplateLinkLocalService = fragmentEntryLayoutTemplateLinkLocalService;
	}

	/**
	* Adds the fragment entry layout template link to the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	* @return the fragment entry layout template link that was added
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink addFragmentEntryLayoutTemplateLink(
		com.liferay.fragment.model.FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return _fragmentEntryLayoutTemplateLinkLocalService.addFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLink);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink addFragmentEntryLayoutTemplateLink(
		long groupId, long fragmentEntryId, long layoutPageTemplateEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.addFragmentEntryLayoutTemplateLink(groupId,
			fragmentEntryId, layoutPageTemplateEntryId);
	}

	/**
	* Creates a new fragment entry layout template link with the primary key. Does not add the fragment entry layout template link to the database.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key for the new fragment entry layout template link
	* @return the new fragment entry layout template link
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink createFragmentEntryLayoutTemplateLink(
		long fragmentEntryLayoutTemplateLinkId) {
		return _fragmentEntryLayoutTemplateLinkLocalService.createFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLinkId);
	}

	/**
	* Deletes the fragment entry layout template link from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	* @return the fragment entry layout template link that was removed
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink deleteFragmentEntryLayoutTemplateLink(
		com.liferay.fragment.model.FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return _fragmentEntryLayoutTemplateLinkLocalService.deleteFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLink);
	}

	/**
	* Deletes the fragment entry layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link that was removed
	* @throws PortalException if a fragment entry layout template link with the primary key could not be found
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink deleteFragmentEntryLayoutTemplateLink(
		long fragmentEntryLayoutTemplateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.deleteFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLinkId);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentEntryLayoutTemplateLink> deleteFragmentEntryLayoutTemplateLinks(
		long groupId, long layoutPageTemplateEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.deleteFragmentEntryLayoutTemplateLinks(groupId,
			layoutPageTemplateEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQuery();
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
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQuery(dynamicQuery,
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
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fragmentEntryLayoutTemplateLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink fetchFragmentEntryLayoutTemplateLink(
		long fragmentEntryLayoutTemplateLinkId) {
		return _fragmentEntryLayoutTemplateLinkLocalService.fetchFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLinkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fragmentEntryLayoutTemplateLinkLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the fragment entry layout template link with the primary key.
	*
	* @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	* @return the fragment entry layout template link
	* @throws PortalException if a fragment entry layout template link with the primary key could not be found
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink getFragmentEntryLayoutTemplateLink(
		long fragmentEntryLayoutTemplateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.getFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLinkId);
	}

	/**
	* Returns a range of all the fragment entry layout template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fragment entry layout template links
	* @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	* @return the range of fragment entry layout template links
	*/
	@Override
	public java.util.List<com.liferay.fragment.model.FragmentEntryLayoutTemplateLink> getFragmentEntryLayoutTemplateLinks(
		int start, int end) {
		return _fragmentEntryLayoutTemplateLinkLocalService.getFragmentEntryLayoutTemplateLinks(start,
			end);
	}

	/**
	* Returns the number of fragment entry layout template links.
	*
	* @return the number of fragment entry layout template links
	*/
	@Override
	public int getFragmentEntryLayoutTemplateLinksCount() {
		return _fragmentEntryLayoutTemplateLinkLocalService.getFragmentEntryLayoutTemplateLinksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fragmentEntryLayoutTemplateLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fragmentEntryLayoutTemplateLinkLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fragmentEntryLayoutTemplateLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the fragment entry layout template link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	* @return the fragment entry layout template link that was updated
	*/
	@Override
	public com.liferay.fragment.model.FragmentEntryLayoutTemplateLink updateFragmentEntryLayoutTemplateLink(
		com.liferay.fragment.model.FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return _fragmentEntryLayoutTemplateLinkLocalService.updateFragmentEntryLayoutTemplateLink(fragmentEntryLayoutTemplateLink);
	}

	@Override
	public FragmentEntryLayoutTemplateLinkLocalService getWrappedService() {
		return _fragmentEntryLayoutTemplateLinkLocalService;
	}

	@Override
	public void setWrappedService(
		FragmentEntryLayoutTemplateLinkLocalService fragmentEntryLayoutTemplateLinkLocalService) {
		_fragmentEntryLayoutTemplateLinkLocalService = fragmentEntryLayoutTemplateLinkLocalService;
	}

	private FragmentEntryLayoutTemplateLinkLocalService _fragmentEntryLayoutTemplateLinkLocalService;
}