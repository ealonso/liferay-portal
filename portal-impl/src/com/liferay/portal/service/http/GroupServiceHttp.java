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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link GroupServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupServiceSoap
 * @see HttpPrincipal
 * @see GroupServiceUtil
 * @generated
 */
@ProviderType
public class GroupServiceHttp {
	public static com.liferay.portal.kernel.model.Group addGroup(
		HttpPrincipal httpPrincipal, long parentGroupId, long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		boolean site, boolean inheritContent, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"addGroup", _addGroupParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentGroupId, liveGroupId, nameMap, descriptionMap, type,
					manualMembership, membershipRestriction, friendlyURLMap,
					site, inheritContent, active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group addGroup(
		HttpPrincipal httpPrincipal, long parentGroupId, long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean inheritContent,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"addGroup", _addGroupParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentGroupId, liveGroupId, nameMap, descriptionMap, type,
					manualMembership, membershipRestriction, friendlyURL, site,
					inheritContent, active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group addGroup(
		HttpPrincipal httpPrincipal, long parentGroupId, long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"addGroup", _addGroupParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentGroupId, liveGroupId, nameMap, descriptionMap, type,
					manualMembership, membershipRestriction, friendlyURL, site,
					active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group addGroup(
		HttpPrincipal httpPrincipal, long parentGroupId, long liveGroupId,
		java.lang.String name, java.lang.String description, int type,
		boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"addGroup", _addGroupParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentGroupId, liveGroupId, name, description, type,
					manualMembership, membershipRestriction, friendlyURL, site,
					active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void addRoleGroups(HttpPrincipal httpPrincipal, long roleId,
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"addRoleGroups", _addRoleGroupsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, roleId,
					groupIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void checkRemoteStagingGroup(HttpPrincipal httpPrincipal,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"checkRemoteStagingGroup",
					_checkRemoteStagingGroupParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteGroup(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"deleteGroup", _deleteGroupParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void disableStaging(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"disableStaging", _disableStagingParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void enableStaging(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"enableStaging", _enableStagingParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group getCompanyGroup(
		HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getCompanyGroup", _getCompanyGroupParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group getGroup(
		HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getGroup", _getGroupParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group getGroup(
		HttpPrincipal httpPrincipal, long companyId, java.lang.String groupKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getGroup", _getGroupParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getGroupDisplayURL(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		boolean secureConnection)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getGroupDisplayURL", _getGroupDisplayURLParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, secureConnection);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		HttpPrincipal httpPrincipal, long companyId, long parentGroupId,
		boolean site)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getGroups", _getGroupsParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentGroupId, site);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getManageableSiteGroups(
		HttpPrincipal httpPrincipal,
		java.util.Collection<com.liferay.portal.kernel.model.Portlet> portlets,
		int max) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getManageableSiteGroups",
					_getManageableSiteGroupsParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portlets, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationsGroups(
		HttpPrincipal httpPrincipal,
		java.util.List<com.liferay.portal.kernel.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getOrganizationsGroups",
					_getOrganizationsGroupsParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizations);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group getUserGroup(
		HttpPrincipal httpPrincipal, long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserGroup", _getUserGroupParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupsGroups(
		HttpPrincipal httpPrincipal,
		java.util.List<com.liferay.portal.kernel.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserGroupsGroups", _getUserGroupsGroupsParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userGroups);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserOrganizationsGroups(
		HttpPrincipal httpPrincipal, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserOrganizationsGroups",
					_getUserOrganizationsGroupsParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserSitesGroups", _getUserSitesGroupsParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		HttpPrincipal httpPrincipal, long userId,
		java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserSitesGroups", _getUserSitesGroupsParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					classNames, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		HttpPrincipal httpPrincipal, java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserSitesGroups", _getUserSitesGroupsParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					classNames, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getUserSitesGroupsCount(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"getUserSitesGroupsCount",
					_getUserSitesGroupsCountParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasUserGroup(HttpPrincipal httpPrincipal,
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"hasUserGroup", _hasUserGroupParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		HttpPrincipal httpPrincipal, long companyId, long[] classNameIds,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"search", _searchParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, classNameIds, keywords, params, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		HttpPrincipal httpPrincipal, long companyId, long[] classNameIds,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"search", _searchParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, classNameIds, name, description, params,
					andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		HttpPrincipal httpPrincipal, long companyId, java.lang.String name,
		java.lang.String description, java.lang.String[] params, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"search", _searchParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, name, description, params, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		java.lang.String name, java.lang.String description,
		java.lang.String[] params) {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"searchCount", _searchCountParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, name, description, params);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void setRoleGroups(HttpPrincipal httpPrincipal, long roleId,
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"setRoleGroups", _setRoleGroupsParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey, roleId,
					groupIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsetRoleGroups(HttpPrincipal httpPrincipal,
		long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"unsetRoleGroups", _unsetRoleGroupsParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey, roleId,
					groupIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateFriendlyURL(
		HttpPrincipal httpPrincipal, long groupId,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateFriendlyURL", _updateFriendlyURLParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					friendlyURLMap);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateFriendlyURL(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateFriendlyURL", _updateFriendlyURLParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					friendlyURL);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateGroup(
		HttpPrincipal httpPrincipal, long groupId, long parentGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		boolean inheritContent, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateGroup", _updateGroupParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentGroupId, nameMap, descriptionMap, type,
					manualMembership, membershipRestriction, friendlyURLMap,
					inheritContent, active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateGroup(
		HttpPrincipal httpPrincipal, long groupId, long parentGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean inheritContent, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateGroup", _updateGroupParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentGroupId, nameMap, descriptionMap, type,
					manualMembership, membershipRestriction, friendlyURL,
					inheritContent, active, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateGroup(
		HttpPrincipal httpPrincipal, long groupId, long parentGroupId,
		java.lang.String name, java.lang.String description, int type,
		boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean inheritContent, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateGroup", _updateGroupParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentGroupId, name, description, type, manualMembership,
					membershipRestriction, friendlyURL, inheritContent, active,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Group updateGroup(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateGroup", _updateGroupParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					typeSettings);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Group)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateStagedPortlets(HttpPrincipal httpPrincipal,
		long groupId,
		java.util.Map<java.lang.String, java.lang.String> stagedPortletIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(GroupServiceUtil.class,
					"updateStagedPortlets",
					_updateStagedPortletsParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					stagedPortletIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GroupServiceHttp.class);
	private static final Class<?>[] _addGroupParameterTypes0 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			int.class, boolean.class, int.class, java.util.Map.class,
			boolean.class, boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addGroupParameterTypes1 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			int.class, boolean.class, int.class, java.lang.String.class,
			boolean.class, boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addGroupParameterTypes2 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			int.class, boolean.class, int.class, java.lang.String.class,
			boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addGroupParameterTypes3 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, int.class, boolean.class, int.class,
			java.lang.String.class, boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addRoleGroupsParameterTypes4 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _checkRemoteStagingGroupParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteGroupParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _disableStagingParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _enableStagingParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCompanyGroupParameterTypes9 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getGroupParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getGroupParameterTypes11 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getGroupDisplayURLParameterTypes12 = new Class[] {
			long.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _getGroupsParameterTypes13 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _getManageableSiteGroupsParameterTypes14 = new Class[] {
			java.util.Collection.class, int.class
		};
	private static final Class<?>[] _getOrganizationsGroupsParameterTypes15 = new Class[] {
			java.util.List.class
		};
	private static final Class<?>[] _getUserGroupParameterTypes16 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getUserGroupsGroupsParameterTypes17 = new Class[] {
			java.util.List.class
		};
	private static final Class<?>[] _getUserOrganizationsGroupsParameterTypes18 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getUserSitesGroupsParameterTypes19 = new Class[] {
			
		};
	private static final Class<?>[] _getUserSitesGroupsParameterTypes20 = new Class[] {
			long.class, java.lang.String[].class, int.class
		};
	private static final Class<?>[] _getUserSitesGroupsParameterTypes21 = new Class[] {
			java.lang.String[].class, int.class
		};
	private static final Class<?>[] _getUserSitesGroupsCountParameterTypes22 = new Class[] {
			
		};
	private static final Class<?>[] _hasUserGroupParameterTypes23 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _searchParameterTypes24 = new Class[] {
			long.class, long[].class, java.lang.String.class,
			java.util.LinkedHashMap.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes25 = new Class[] {
			long.class, long[].class, java.lang.String.class,
			java.lang.String.class, java.util.LinkedHashMap.class, boolean.class,
			int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes26 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String[].class, int.class, int.class
		};
	private static final Class<?>[] _searchCountParameterTypes27 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String[].class
		};
	private static final Class<?>[] _setRoleGroupsParameterTypes28 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _unsetRoleGroupsParameterTypes29 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _updateFriendlyURLParameterTypes30 = new Class[] {
			long.class, java.util.Map.class
		};
	private static final Class<?>[] _updateFriendlyURLParameterTypes31 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _updateGroupParameterTypes32 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			int.class, boolean.class, int.class, java.util.Map.class,
			boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateGroupParameterTypes33 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			int.class, boolean.class, int.class, java.lang.String.class,
			boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateGroupParameterTypes34 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, int.class, boolean.class, int.class,
			java.lang.String.class, boolean.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateGroupParameterTypes35 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _updateStagedPortletsParameterTypes36 = new Class[] {
			long.class, java.util.Map.class
		};
}