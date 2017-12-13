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

import com.liferay.layout.service.model.LayoutFragment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for LayoutFragment. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentServiceUtil
 * @see com.liferay.layout.service.service.base.LayoutFragmentServiceBaseImpl
 * @see com.liferay.layout.service.service.impl.LayoutFragmentServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=layout", "json.web.service.context.path=LayoutFragment"}, service = LayoutFragmentService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LayoutFragmentService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutFragmentServiceUtil} to access the layout fragment remote service. Add custom service methods to {@link com.liferay.layout.service.service.impl.LayoutFragmentServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Layout addContentLayout(long groupId, boolean privateLayout,
		long parentLayoutId, long layoutPageTemplateEntryId,
		Map<Locale, java.lang.String> localeNamesMap,
		Map<Locale, java.lang.String> localeTitlesMap,
		Map<Locale, java.lang.String> descriptionMap,
		Map<Locale, java.lang.String> keywordsMap,
		Map<Locale, java.lang.String> robotsMap, java.lang.String type,
		Map<Locale, java.lang.String> friendlyURLMap,
		ServiceContext serviceContext) throws PortalException;

	public void deleteContentLayout(long groupId, long plid,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutFragment> getLayoutFragments(long groupId, long plid);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}