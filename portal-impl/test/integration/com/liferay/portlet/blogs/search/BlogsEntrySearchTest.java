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

package com.liferay.portlet.blogs.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.search.test.BaseSearchTestCase;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.util.test.BlogsTestUtil;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Eudaldo Alonso
 */
@Sync
public class BlogsEntrySearchTest extends BaseSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Ignore()
	@Override
	@Test
	public void testLocalizedSearch() throws Exception {
	}

	@Override
	@Test
	public void testLocalizedSortByTitle() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		BaseModel<BlogsEntry> basemodel1 = (BlogsEntry)addBaseModelWithWorkflow(
			parentBaseModel, true, "bblog", serviceContext);
		BaseModel<BlogsEntry> basemodel2 = (BlogsEntry)addBaseModelWithWorkflow(
			parentBaseModel, true, "ablog", serviceContext);
		BaseModel<BlogsEntry> basemodel3 = (BlogsEntry)addBaseModelWithWorkflow(
			parentBaseModel, true, "cblog", serviceContext);

		// Test sort on default locale ("en_US")

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setAttribute(Field.TITLE, "*blog");
		searchContext.setKeywords("*blog");
		searchContext.setLocale(LocaleUtil.getDefault());

		String sortField = "localized_title_" + LocaleUtil.toLanguageId(
			LocaleUtil.getDefault()) + "_sortable";

		Sort sort = new Sort(sortField, Sort.STRING_TYPE, true);

		searchContext.setSorts(sort);

		Hits results = searchBaseModels(
			getBaseModelClass(), group.getGroupId(), searchContext);

		Assert.assertEquals(3, results.getLength());

		BlogsEntry entry1 = (BlogsEntry)basemodel1;
		BlogsEntry entry2 = (BlogsEntry)basemodel2;
		BlogsEntry entry3 = (BlogsEntry)basemodel3;

		Document document1 = results.doc(0);
		Document document2 = results.doc(1);
		Document document3 = results.doc(2);

		Assert.assertEquals(
			GetterUtil.getLong(document1.get(Field.ENTRY_CLASS_PK)),
			entry3.getPrimaryKey());
		Assert.assertEquals(
			GetterUtil.getLong(document2.get(Field.ENTRY_CLASS_PK)),
			entry1.getPrimaryKey());
		Assert.assertEquals(
			GetterUtil.getLong(document3.get(Field.ENTRY_CLASS_PK)),
			entry2.getPrimaryKey());

		// Test sort on a non-default locale.

		// Since the title is not localizable for blog entries currently, the
		// order should be the same regardless of the search locale.

		searchContext.setLocale(LocaleUtil.FRENCH);

		sortField = "localized_title_fr_FR_sortable";

		sort.setFieldName(sortField);

		searchContext.setSorts(sort);

		results = searchBaseModels(
			getBaseModelClass(), group.getGroupId(), searchContext);

		Assert.assertEquals(3, results.getLength());

		document1 = results.doc(0);
		document2 = results.doc(1);
		document3 = results.doc(2);

		Assert.assertEquals(
			GetterUtil.getLong(document1.get(Field.ENTRY_CLASS_PK)),
			entry3.getPrimaryKey());
		Assert.assertEquals(
			GetterUtil.getLong(document2.get(Field.ENTRY_CLASS_PK)),
			entry1.getPrimaryKey());
		Assert.assertEquals(
			GetterUtil.getLong(document3.get(Field.ENTRY_CLASS_PK)),
			entry2.getPrimaryKey());
	}

	@Ignore()
	@Override
	@Test
	public void testParentBaseModelUserPermissions() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchAttachments() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchByDDMStructureField() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchByKeywordsInsideParentBaseModel() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchExpireAllVersions() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchExpireLatestVersion() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchMyEntries() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchRecentEntries() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchStatus() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchVersions() throws Exception {
	}

	@Ignore()
	@Override
	@Test
	public void testSearchWithinDDMStructure() throws Exception {
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return BlogsTestUtil.addEntry(
			TestPropsValues.getUserId(), keywords, approved, serviceContext);
	}

	@Override
	protected void deleteBaseModel(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.deleteBlogsEntry(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return BlogsEntry.class;
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.moveEntryToTrash(
			TestPropsValues.getUserId(), primaryKey);
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		BlogsEntry entry = (BlogsEntry)baseModel;

		entry.setTitle(keywords);

		return BlogsTestUtil.updateEntry(entry, keywords, true);
	}

}