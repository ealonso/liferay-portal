/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service;

import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for LayoutSEOEntryCustomMetaTag. This utility wraps
 * <code>com.liferay.layout.seo.service.impl.LayoutSEOEntryCustomMetaTagLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntryCustomMetaTagLocalService
 * @generated
 */
public class LayoutSEOEntryCustomMetaTagLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.layout.seo.service.impl.LayoutSEOEntryCustomMetaTagLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the layout seo entry custom meta tag to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryCustomMetaTagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntryCustomMetaTag the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag that was added
	 */
	public static LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return getService().addLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTag);
	}

	public static LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			Map<java.util.Locale, String> contentMap)
		throws PortalException {

		return getService().addLayoutSEOEntryCustomMetaTag(
			groupId, layoutSEOEntryId, property, contentMap);
	}

	/**
	 * Creates a new layout seo entry custom meta tag with the primary key. Does not add the layout seo entry custom meta tag to the database.
	 *
	 * @param layoutSEOEntryCustomMetaTagId the primary key for the new layout seo entry custom meta tag
	 * @return the new layout seo entry custom meta tag
	 */
	public static LayoutSEOEntryCustomMetaTag createLayoutSEOEntryCustomMetaTag(
		long layoutSEOEntryCustomMetaTagId) {

		return getService().createLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTagId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the layout seo entry custom meta tag from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryCustomMetaTagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntryCustomMetaTag the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag that was removed
	 */
	public static LayoutSEOEntryCustomMetaTag deleteLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return getService().deleteLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTag);
	}

	/**
	 * Deletes the layout seo entry custom meta tag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryCustomMetaTagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntryCustomMetaTagId the primary key of the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag that was removed
	 * @throws PortalException if a layout seo entry custom meta tag with the primary key could not be found
	 */
	public static LayoutSEOEntryCustomMetaTag deleteLayoutSEOEntryCustomMetaTag(
			long layoutSEOEntryCustomMetaTagId)
		throws PortalException {

		return getService().deleteLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTagId);
	}

	public static void deleteLayoutSEOEntryCustomMetaTags(
		long groupId, long layoutSEOEntryId) {

		getService().deleteLayoutSEOEntryCustomMetaTags(
			groupId, layoutSEOEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryCustomMetaTagModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryCustomMetaTagModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static LayoutSEOEntryCustomMetaTag fetchLayoutSEOEntryCustomMetaTag(
		long layoutSEOEntryCustomMetaTagId) {

		return getService().fetchLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTagId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the layout seo entry custom meta tag with the primary key.
	 *
	 * @param layoutSEOEntryCustomMetaTagId the primary key of the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag
	 * @throws PortalException if a layout seo entry custom meta tag with the primary key could not be found
	 */
	public static LayoutSEOEntryCustomMetaTag getLayoutSEOEntryCustomMetaTag(
			long layoutSEOEntryCustomMetaTagId)
		throws PortalException {

		return getService().getLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTagId);
	}

	/**
	 * Returns a range of all the layout seo entry custom meta tags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryCustomMetaTagModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout seo entry custom meta tags
	 * @param end the upper bound of the range of layout seo entry custom meta tags (not inclusive)
	 * @return the range of layout seo entry custom meta tags
	 */
	public static List<LayoutSEOEntryCustomMetaTag>
		getLayoutSEOEntryCustomMetaTags(int start, int end) {

		return getService().getLayoutSEOEntryCustomMetaTags(start, end);
	}

	public static List<LayoutSEOEntryCustomMetaTag>
		getLayoutSEOEntryCustomMetaTags(long groupId, long layoutSEOEntryId) {

		return getService().getLayoutSEOEntryCustomMetaTags(
			groupId, layoutSEOEntryId);
	}

	/**
	 * Returns the number of layout seo entry custom meta tags.
	 *
	 * @return the number of layout seo entry custom meta tags
	 */
	public static int getLayoutSEOEntryCustomMetaTagsCount() {
		return getService().getLayoutSEOEntryCustomMetaTagsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the layout seo entry custom meta tag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryCustomMetaTagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntryCustomMetaTag the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag that was updated
	 */
	public static LayoutSEOEntryCustomMetaTag updateLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return getService().updateLayoutSEOEntryCustomMetaTag(
			layoutSEOEntryCustomMetaTag);
	}

	public static LayoutSEOEntryCustomMetaTagLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<LayoutSEOEntryCustomMetaTagLocalService>
		_serviceSnapshot = new Snapshot<>(
			LayoutSEOEntryCustomMetaTagLocalServiceUtil.class,
			LayoutSEOEntryCustomMetaTagLocalService.class);

}