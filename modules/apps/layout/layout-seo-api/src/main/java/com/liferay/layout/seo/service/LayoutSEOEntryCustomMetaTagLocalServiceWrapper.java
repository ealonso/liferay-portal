/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service;

import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

/**
 * Provides a wrapper for {@link LayoutSEOEntryCustomMetaTagLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntryCustomMetaTagLocalService
 * @generated
 */
public class LayoutSEOEntryCustomMetaTagLocalServiceWrapper
	implements LayoutSEOEntryCustomMetaTagLocalService,
			   ServiceWrapper<LayoutSEOEntryCustomMetaTagLocalService> {

	public LayoutSEOEntryCustomMetaTagLocalServiceWrapper() {
		this(null);
	}

	public LayoutSEOEntryCustomMetaTagLocalServiceWrapper(
		LayoutSEOEntryCustomMetaTagLocalService
			layoutSEOEntryCustomMetaTagLocalService) {

		_layoutSEOEntryCustomMetaTagLocalService =
			layoutSEOEntryCustomMetaTagLocalService;
	}

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
	@Override
	public LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			addLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTag);
	}

	@Override
	public LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			java.util.Map<java.util.Locale, String> contentMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.
			addLayoutSEOEntryCustomMetaTag(
				groupId, layoutSEOEntryId, property, contentMap);
	}

	/**
	 * Creates a new layout seo entry custom meta tag with the primary key. Does not add the layout seo entry custom meta tag to the database.
	 *
	 * @param layoutSEOEntryCustomMetaTagId the primary key for the new layout seo entry custom meta tag
	 * @return the new layout seo entry custom meta tag
	 */
	@Override
	public LayoutSEOEntryCustomMetaTag createLayoutSEOEntryCustomMetaTag(
		long layoutSEOEntryCustomMetaTagId) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			createLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTagId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public LayoutSEOEntryCustomMetaTag deleteLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			deleteLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTag);
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
	@Override
	public LayoutSEOEntryCustomMetaTag deleteLayoutSEOEntryCustomMetaTag(
			long layoutSEOEntryCustomMetaTagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.
			deleteLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTagId);
	}

	@Override
	public void deleteLayoutSEOEntryCustomMetaTags(
		long groupId, long layoutSEOEntryId) {

		_layoutSEOEntryCustomMetaTagLocalService.
			deleteLayoutSEOEntryCustomMetaTags(groupId, layoutSEOEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _layoutSEOEntryCustomMetaTagLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _layoutSEOEntryCustomMetaTagLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQuery();
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

		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQuery(
			dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _layoutSEOEntryCustomMetaTagLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public LayoutSEOEntryCustomMetaTag fetchLayoutSEOEntryCustomMetaTag(
		long layoutSEOEntryCustomMetaTagId) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			fetchLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTagId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _layoutSEOEntryCustomMetaTagLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _layoutSEOEntryCustomMetaTagLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the layout seo entry custom meta tag with the primary key.
	 *
	 * @param layoutSEOEntryCustomMetaTagId the primary key of the layout seo entry custom meta tag
	 * @return the layout seo entry custom meta tag
	 * @throws PortalException if a layout seo entry custom meta tag with the primary key could not be found
	 */
	@Override
	public LayoutSEOEntryCustomMetaTag getLayoutSEOEntryCustomMetaTag(
			long layoutSEOEntryCustomMetaTagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.
			getLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTagId);
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
	@Override
	public java.util.List<LayoutSEOEntryCustomMetaTag>
		getLayoutSEOEntryCustomMetaTags(int start, int end) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			getLayoutSEOEntryCustomMetaTags(start, end);
	}

	@Override
	public java.util.List<LayoutSEOEntryCustomMetaTag>
		getLayoutSEOEntryCustomMetaTags(long groupId, long layoutSEOEntryId) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			getLayoutSEOEntryCustomMetaTags(groupId, layoutSEOEntryId);
	}

	/**
	 * Returns the number of layout seo entry custom meta tags.
	 *
	 * @return the number of layout seo entry custom meta tags
	 */
	@Override
	public int getLayoutSEOEntryCustomMetaTagsCount() {
		return _layoutSEOEntryCustomMetaTagLocalService.
			getLayoutSEOEntryCustomMetaTagsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutSEOEntryCustomMetaTagLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagLocalService.getPersistedModel(
			primaryKeyObj);
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
	@Override
	public LayoutSEOEntryCustomMetaTag updateLayoutSEOEntryCustomMetaTag(
		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag) {

		return _layoutSEOEntryCustomMetaTagLocalService.
			updateLayoutSEOEntryCustomMetaTag(layoutSEOEntryCustomMetaTag);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _layoutSEOEntryCustomMetaTagLocalService.getBasePersistence();
	}

	@Override
	public CTPersistence<LayoutSEOEntryCustomMetaTag> getCTPersistence() {
		return _layoutSEOEntryCustomMetaTagLocalService.getCTPersistence();
	}

	@Override
	public Class<LayoutSEOEntryCustomMetaTag> getModelClass() {
		return _layoutSEOEntryCustomMetaTagLocalService.getModelClass();
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<LayoutSEOEntryCustomMetaTag>, R, E>
				updateUnsafeFunction)
		throws E {

		return _layoutSEOEntryCustomMetaTagLocalService.
			updateWithUnsafeFunction(updateUnsafeFunction);
	}

	@Override
	public LayoutSEOEntryCustomMetaTagLocalService getWrappedService() {
		return _layoutSEOEntryCustomMetaTagLocalService;
	}

	@Override
	public void setWrappedService(
		LayoutSEOEntryCustomMetaTagLocalService
			layoutSEOEntryCustomMetaTagLocalService) {

		_layoutSEOEntryCustomMetaTagLocalService =
			layoutSEOEntryCustomMetaTagLocalService;
	}

	private LayoutSEOEntryCustomMetaTagLocalService
		_layoutSEOEntryCustomMetaTagLocalService;

}