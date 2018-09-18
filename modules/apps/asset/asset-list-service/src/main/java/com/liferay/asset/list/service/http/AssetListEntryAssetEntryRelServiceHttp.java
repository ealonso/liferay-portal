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

package com.liferay.asset.list.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.list.service.AssetListEntryAssetEntryRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AssetListEntryAssetEntryRelServiceUtil} service utility. The
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
 * @see AssetListEntryAssetEntryRelServiceSoap
 * @see HttpPrincipal
 * @see AssetListEntryAssetEntryRelServiceUtil
 * @generated
 */
@ProviderType
public class AssetListEntryAssetEntryRelServiceHttp {
	public static com.liferay.asset.list.model.AssetListEntryAssetEntryRel addAssetListEntryAssetEntryRel(
		HttpPrincipal httpPrincipal, long assetListEntryId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetListEntryAssetEntryRelServiceUtil.class,
					"addAssetListEntryAssetEntryRel",
					_addAssetListEntryAssetEntryRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetListEntryId, assetEntryId);

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

			return (com.liferay.asset.list.model.AssetListEntryAssetEntryRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.asset.list.model.AssetListEntryAssetEntryRel deleteAssetListEntryAssetEntryRel(
		HttpPrincipal httpPrincipal, long assetListEntryId, int position)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetListEntryAssetEntryRelServiceUtil.class,
					"deleteAssetListEntryAssetEntryRel",
					_deleteAssetListEntryAssetEntryRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetListEntryId, position);

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

			return (com.liferay.asset.list.model.AssetListEntryAssetEntryRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.asset.list.model.AssetListEntryAssetEntryRel> getAssetListEntryAssetEntryRels(
		HttpPrincipal httpPrincipal, long assetListEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetListEntryAssetEntryRelServiceUtil.class,
					"getAssetListEntryAssetEntryRels",
					_getAssetListEntryAssetEntryRelsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetListEntryId, start, end);

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

			return (java.util.List<com.liferay.asset.list.model.AssetListEntryAssetEntryRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getAssetListEntryAssetEntryRelsCount(
		HttpPrincipal httpPrincipal, long assetListEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetListEntryAssetEntryRelServiceUtil.class,
					"getAssetListEntryAssetEntryRelsCount",
					_getAssetListEntryAssetEntryRelsCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetListEntryId);

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

	public static com.liferay.asset.list.model.AssetListEntryAssetEntryRel moveAssetEntry(
		HttpPrincipal httpPrincipal, long assetListEntryId, int position,
		int newPosition)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetListEntryAssetEntryRelServiceUtil.class,
					"moveAssetEntry", _moveAssetEntryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetListEntryId, position, newPosition);

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

			return (com.liferay.asset.list.model.AssetListEntryAssetEntryRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetListEntryAssetEntryRelServiceHttp.class);
	private static final Class<?>[] _addAssetListEntryAssetEntryRelParameterTypes0 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _deleteAssetListEntryAssetEntryRelParameterTypes1 =
		new Class[] { long.class, int.class };
	private static final Class<?>[] _getAssetListEntryAssetEntryRelsParameterTypes2 =
		new Class[] { long.class, int.class, int.class };
	private static final Class<?>[] _getAssetListEntryAssetEntryRelsCountParameterTypes3 =
		new Class[] { long.class };
	private static final Class<?>[] _moveAssetEntryParameterTypes4 = new Class[] {
			long.class, int.class, int.class
		};
}