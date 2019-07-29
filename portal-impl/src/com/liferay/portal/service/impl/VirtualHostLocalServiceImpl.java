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

package com.liferay.portal.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.VirtualHostPriorityComparator;
import com.liferay.portal.model.impl.LayoutSetImpl;
import com.liferay.portal.model.impl.LayoutSetModelImpl;
import com.liferay.portal.service.base.VirtualHostLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class VirtualHostLocalServiceImpl
	extends VirtualHostLocalServiceBaseImpl {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #getVirtualHosts(long, long)}
	 */
	@Deprecated
	@Override
	public VirtualHost fetchVirtualHost(long companyId, long layoutSetId) {
		return virtualHostPersistence.fetchByC_L_First(
			companyId, layoutSetId, new VirtualHostPriorityComparator());
	}

	@Override
	public VirtualHost fetchVirtualHost(String hostname) {
		return virtualHostPersistence.fetchByHostname(hostname);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #getVirtualHosts(long, long)}
	 */
	@Deprecated
	@Override
	public VirtualHost getVirtualHost(long companyId, long layoutSetId)
		throws PortalException {

		return virtualHostPersistence.findByC_L_First(
			companyId, layoutSetId, new VirtualHostPriorityComparator());
	}

	@Override
	public VirtualHost getVirtualHost(String hostname) throws PortalException {
		return virtualHostPersistence.findByHostname(hostname);
	}

	@Override
	public List<VirtualHost> getVirtualHosts(long companyId, long layoutSetId)
		throws PortalException {

		return virtualHostPersistence.findByC_L(companyId, layoutSetId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #updateVirtualHosts(long, long, TreeMap)}
	 */
	@Deprecated
	@Override
	public VirtualHost updateVirtualHost(
		long companyId, final long layoutSetId, String hostname) {

		TreeMap<String, String> hostnameMap = new TreeMap<>();

		hostnameMap.put(hostname, StringPool.BLANK);

		List<VirtualHost> virtualHosts = updateVirtualHosts(
			companyId, layoutSetId, hostnameMap);

		if (virtualHosts.isEmpty()) {
			return null;
		}

		return virtualHosts.get(0);
	}

	@Override
	public List<VirtualHost> updateVirtualHosts(
		long companyId, final long layoutSetId,
		TreeMap<String, String> hostnameMap) {

		List<VirtualHost> virtualHosts = new ArrayList<>(
			virtualHostPersistence.findByC_L(companyId, layoutSetId));

		List<String> hostnames = new ArrayList<>(hostnameMap.navigableKeySet());

		for (int i = 0; i < hostnames.size(); i++) {
			String curHostname = hostnames.get(i);

			VirtualHost virtualHost = null;

			for (VirtualHost curVirtualHost : virtualHosts) {
				if (curHostname.equals(curVirtualHost.getHostname())) {
					virtualHost = curVirtualHost;

					break;
				}
			}

			if (virtualHost == null) {
				long virtualHostId = counterLocalService.increment();

				virtualHost = virtualHostPersistence.create(virtualHostId);

				virtualHost.setCompanyId(companyId);
				virtualHost.setLayoutSetId(layoutSetId);
				virtualHost.setHostname(curHostname);

				String languageId = hostnameMap.get(curHostname);

				virtualHost.setLanguageId(languageId);

				virtualHosts.add(virtualHost);
			}

			virtualHost.setPriority(-i);

			virtualHostPersistence.update(virtualHost);
		}

		Iterator<VirtualHost> itr = virtualHosts.iterator();

		while (itr.hasNext()) {
			VirtualHost virtualHost = itr.next();

			if (!hostnames.contains(virtualHost.getHostname())) {
				itr.remove();

				virtualHostPersistence.remove(virtualHost);
			}
		}

		virtualHosts.sort(new VirtualHostPriorityComparator());

		virtualHostPersistence.cacheResult(virtualHosts);

		final Company company = companyPersistence.fetchByPrimaryKey(companyId);

		if (company != null) {
			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						EntityCacheUtil.removeResult(
							company.isEntityCacheEnabled(), company.getClass(),
							company.getPrimaryKeyObj());

						return null;
					}

				});

			companyPersistence.clearCache(company);
		}

		LayoutSet layoutSet = layoutSetPersistence.fetchByPrimaryKey(
			layoutSetId);

		if ((layoutSet == null) &&
			Validator.isNotNull(PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME)) {

			Group group = groupPersistence.fetchByC_GK(
				companyId, PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

			if (group != null) {
				layoutSet = layoutSetPersistence.fetchByG_P(
					group.getGroupId(), false);
			}
		}

		if (layoutSet != null) {
			layoutSetPersistence.clearCache(layoutSet);

			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() {
						EntityCacheUtil.removeResult(
							LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
							LayoutSetImpl.class, layoutSetId);

						return null;
					}

				});
		}

		return virtualHosts;
	}

}