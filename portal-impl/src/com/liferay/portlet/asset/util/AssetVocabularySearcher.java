/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.asset.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseModelSearcher;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

/**
 * @author László Csontos
 */
public class AssetVocabularySearcher
	extends BaseModelSearcher<AssetVocabulary> {

	public static final String[] SELECTED_FIELD_NAMES =
		{Field.COMPANY_ID, Field.GROUP_ID, Field.UID, Field.VOCABULARY_ID};

	public static BaseModelSearcher<AssetVocabulary> getInstance() {
		return new AssetVocabularySearcher();
	}

	protected AssetVocabularySearcher() {
		super(AssetVocabularyIndexer.CLASS_NAMES, SELECTED_FIELD_NAMES);
	}

	@Override
	protected AssetVocabulary getModel(Document document)
		throws SystemException {

		long vocabularyId = GetterUtil.getLong(
			document.get(Field.VOCABULARY_ID));

		return AssetVocabularyLocalServiceUtil.fetchVocabulary(vocabularyId);
	}

}