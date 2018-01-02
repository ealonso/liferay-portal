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

package com.liferay.asset.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.service.permission.AssetCategoryPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class AssetServiceUtil {

	public static long[] filterCategoryIds(
			PermissionChecker permissionChecker, long[] categoryIds)
		throws PortalException {

		List<Long> viewableCategoryIds = new ArrayList<>();

		for (long categoryId : categoryIds) {
			AssetCategory category =
				AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

			if ((category != null) &&
				AssetCategoryPermission.contains(
					permissionChecker, category, ActionKeys.VIEW)) {

				viewableCategoryIds.add(categoryId);
			}
		}

		return ArrayUtil.toArray(
			viewableCategoryIds.toArray(new Long[viewableCategoryIds.size()]));
	}

	public static boolean isValidWord(String word) {
		if (Validator.isBlank(word)) {
			return false;
		}

		char[] wordCharArray = word.toCharArray();

		for (char c : wordCharArray) {
			for (char invalidChar : AssetHelper.INVALID_CHARACTERS) {
				if (c == invalidChar) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							StringBundler.concat(
								"Word ", word, " is not valid because ",
								String.valueOf(c), " is not allowed"));
					}

					return false;
				}
			}
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetServiceUtil.class);

}