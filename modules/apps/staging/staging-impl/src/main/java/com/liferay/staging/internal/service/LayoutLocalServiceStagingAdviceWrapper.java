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

package com.liferay.staging.internal.service;

import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.kernel.staging.MergeLayoutPrototypesThreadLocal;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutStagingHandler;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceWrapper;
import com.liferay.portal.kernel.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.ServiceWrapper;

import com.liferay.portal.kernel.service.SystemEventLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.LayoutRevisionUtil;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntry;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.exportimport.staging.ProxiedLayoutsThreadLocal;
import com.liferay.portlet.exportimport.staging.StagingAdvicesThreadLocal;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class LayoutLocalServiceStagingAdviceWrapper
	extends LayoutLocalServiceWrapper {

	public LayoutLocalServiceStagingAdviceWrapper() {
		super(null);
	}

	public LayoutLocalServiceStagingAdviceWrapper(
		LayoutLocalService layoutLocalService) {

		super(layoutLocalService);
	}

	@Override
	public Layout deleteLayout(Layout layout) throws PortalException {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return super.deleteLayout(layout);
		}

		return (Layout)wrapReturnValue(super.deleteLayout(layout), false);
	}

	@Override
	public Layout deleteLayout(long plid) throws PortalException {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return super.deleteLayout(plid);
		}

		return (Layout)wrapReturnValue(super.deleteLayout(plid), false);
	}

	protected Layout getProxiedLayout(Layout layout) {
		ObjectValuePair<ServiceContext, Map<Layout, Object>> objectValuePair =
			ProxiedLayoutsThreadLocal.getProxiedLayouts();

		ServiceContext currentServiceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (objectValuePair != null) {
			ServiceContext serviceContext = objectValuePair.getKey();

			if (serviceContext == currentServiceContext) {
				Map<Layout, Object> proxiedLayouts = objectValuePair.getValue();

				Object proxiedLayout = proxiedLayouts.get(layout);

				if (proxiedLayout != null) {
					Layout cachedProxiedLayout = (Layout)proxiedLayout;

					if (layout.getMvccVersion() ==
						cachedProxiedLayout.getMvccVersion()) {

						return cachedProxiedLayout;
					}

					proxiedLayouts.remove(layout);
				}

				proxiedLayout = ProxyUtil.newProxyInstance(
					PortalClassLoaderUtil.getClassLoader(),
					new Class<?>[] {Layout.class, ModelWrapper.class},
					new LayoutStagingHandler(layout));

				proxiedLayouts.put(layout, proxiedLayout);

				return (Layout)proxiedLayout;
			}
		}

		Object proxiedLayout = ProxyUtil.newProxyInstance(
			PortalClassLoaderUtil.getClassLoader(),
			new Class<?>[] {Layout.class, ModelWrapper.class},
			new LayoutStagingHandler(layout));

		ProxiedLayoutsThreadLocal.setProxiedLayouts(
			new ObjectValuePair<>(
				currentServiceContext,
				HashMapBuilder.<Layout, Object>put(
					layout, proxiedLayout
				).build()));

		return (Layout)proxiedLayout;
	}

	protected Layout wrapLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if (layoutStagingHandler != null) {
			return layout;
		}

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return layout;
		}

		return getProxiedLayout(layout);
	}

	@Override
	public void deleteLayout(long plid, ServiceContext serviceContext)
		throws PortalException {

		super.deleteLayout(plid, serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutLocalServiceStagingAdviceWrapper.class);

	protected List<Layout> wrapLayouts(
		List<Layout> layouts, boolean showIncomplete) {

		if (layouts.isEmpty()) {
			return layouts;
		}

		Layout firstLayout = layouts.get(0);

		Layout wrappedFirstLayout = wrapLayout(firstLayout);

		if (wrappedFirstLayout == firstLayout) {
			return layouts;
		}

		long layoutSetBranchId = 0;

		if (!showIncomplete) {
			long userId = 0;

			try {
				userId = GetterUtil.getLong(PrincipalThreadLocal.getName());

				if (userId > 0) {
					User user = UserLocalServiceUtil.getUser(userId);

					LayoutSet layoutSet = firstLayout.getLayoutSet();

					layoutSetBranchId = StagingUtil.getRecentLayoutSetBranchId(
						user, layoutSet.getLayoutSetId());
				}
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No layout set branch found for user " + userId,
						exception);
				}
			}
		}

		List<Layout> wrappedLayouts = new ArrayList<>(layouts.size());

		for (Layout layout : layouts) {
			Layout wrappedLayout = wrapLayout(layout);

			if (showIncomplete ||
				!StagingUtil.isIncomplete(wrappedLayout, layoutSetBranchId)) {

				wrappedLayouts.add(wrappedLayout);
			}
		}

		return wrappedLayouts;
	}

	protected Object wrapReturnValue(
		Object returnValue, boolean showIncomplete) {

		if (returnValue instanceof Layout) {
			returnValue = wrapLayout((Layout)returnValue);
		}
		else if (returnValue instanceof List<?>) {
			List<?> list = (List<?>)returnValue;

			if (!list.isEmpty()) {
				Object object = list.get(0);

				if (object instanceof Layout) {
					returnValue = wrapLayouts(
						(List<Layout>)returnValue, showIncomplete);
				}
			}
		}
		else if (returnValue instanceof Map<?, ?>) {
			Map<Object, Object> map = (Map<Object, Object>)returnValue;

			if (map.isEmpty()) {
				return returnValue;
			}

			map.replaceAll(
				(key, value) -> wrapReturnValue(value, showIncomplete));
		}

		return returnValue;
	}

	@Override
	public void deleteLayout(Layout layout, ServiceContext serviceContext)
		throws PortalException {

		if (!StagingAdvicesThreadLocal.isEnabled()) {
			super.deleteLayout(layout, serviceContext);
		}

		_deleteLayout(layout, serviceContext);
	}

	@Override
	public void deleteLayout(
			long groupId, boolean privateLayout, long layoutId,
			ServiceContext serviceContext)
		throws PortalException {

		if (!StagingAdvicesThreadLocal.isEnabled()) {
			super.deleteLayout(
				groupId, privateLayout, layoutId, serviceContext);
		}

		Layout layout = super.getLayout(groupId, privateLayout, layoutId);

		_doDeleteLayout(layout, serviceContext);
	}

	private void _deleteLayout(
			Layout layout, ServiceContext serviceContext)
		throws PortalException {

		long layoutSetBranchId = ParamUtil.getLong(
			serviceContext, "layoutSetBranchId");

		if (layoutSetBranchId > 0) {
			LayoutRevisionLocalServiceUtil.deleteLayoutRevisions(
				layoutSetBranchId, layout.getPlid());

			List<LayoutRevision> notIncompleteLayoutRevisions =
				LayoutRevisionUtil.findByP_NotS(
					layout.getPlid(), WorkflowConstants.STATUS_INCOMPLETE);

			if (notIncompleteLayoutRevisions.isEmpty()) {
				LayoutRevisionLocalServiceUtil.deleteLayoutLayoutRevisions(
					layout.getPlid());

				_doDeleteLayout(layout, serviceContext);
			}
		}
		else {
			_doDeleteLayout(layout, serviceContext);
		}
	}

	private void _doDeleteLayout(Layout layout, ServiceContext serviceContext)
		throws PortalException {

		boolean mergeLayoutPrototypesIsInProgress = false;

		try {
			mergeLayoutPrototypesIsInProgress =
				MergeLayoutPrototypesThreadLocal.isInProgress();

			MergeLayoutPrototypesThreadLocal.setInProgress(true);

			SystemEventHierarchyEntry systemEventHierarchyEntry =
				SystemEventHierarchyEntryThreadLocal.push(
					Layout.class, layout.getPlid());

			if (systemEventHierarchyEntry == null) {
				super.deleteLayout(layout, serviceContext);
			}
			else {
				try {
					super.deleteLayout(layout, serviceContext);

					systemEventHierarchyEntry =
						SystemEventHierarchyEntryThreadLocal.peek();

					SystemEventLocalServiceUtil.addSystemEvent(
						0, layout.getGroupId(), Layout.class.getName(),
						layout.getPlid(), layout.getUuid(), null,
						SystemEventConstants.TYPE_DELETE,
						systemEventHierarchyEntry.getExtraData());
				}
				finally {
					SystemEventHierarchyEntryThreadLocal.pop(
						Layout.class, layout.getPlid());
				}
			}
		}
		finally {
			MergeLayoutPrototypesThreadLocal.setInProgress(
				mergeLayoutPrototypesIsInProgress);
		}
	}

}