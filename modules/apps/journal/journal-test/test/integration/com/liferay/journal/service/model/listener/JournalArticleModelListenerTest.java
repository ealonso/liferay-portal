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

package com.liferay.journal.service.model.listener;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.util.servlet.filters.CacheResponseData;

import junit.framework.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Alberto Montero
 */
@RunWith(Arquillian.class)
public class JournalArticleModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testClearCacheWhenCreatingNewJournalArticle() throws Exception {

		Group group = GroupTestUtil.addGroup();

		long companyId = group.getCompanyId();
		String key = "foo";

		BufferCacheServletResponse bufferCacheServletResponse =
			new BufferCacheServletResponse(new MockHttpServletResponse());
		CacheResponseData cacheResponseData = new CacheResponseData(
			bufferCacheServletResponse);

		CacheUtil.putCacheResponseData(companyId, key, cacheResponseData);

		Assert.assertNotNull(CacheUtil.getCacheResponseData(companyId, key));

		JournalTestUtil.addArticle(
			group.getGroupId(),JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertNull(CacheUtil.getCacheResponseData(companyId, key));
	}

}
