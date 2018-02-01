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

package com.liferay.fragment.util;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pablo Molina
 */
public class FragmentEntryRenderUtil {

	public static String renderFragmentEntry(FragmentEntry fragmentEntry) {
		return renderFragmentEntry(
			fragmentEntry.getFragmentEntryId(), 0, fragmentEntry.getCss(),
			fragmentEntry.getHtml(), fragmentEntry.getJs());
	}

	public static String renderFragmentEntry(
		long fragmentEntryId, long fragmentEntryInstanceId) {

		FragmentEntry fragmentEntry =
			FragmentEntryLocalServiceUtil.fetchFragmentEntry(fragmentEntryId);

		return renderFragmentEntry(
			fragmentEntryId, fragmentEntryInstanceId, fragmentEntry.getCss(),
			fragmentEntry.getHtml(), fragmentEntry.getJs());
	}

	public static String renderFragmentEntry(
		long fragmentEntryId, long fragmentEntryInstanceId, String css,
		String html, String js) {

		try {
			StringBundler sb = new StringBundler(12);

			String fragmentEntryClassName = String.join(
				"-", "fragment", String.valueOf(fragmentEntryId));

			String fragmentEntryInstanceClassName = String.join(
				"-", "fragment", String.valueOf(fragmentEntryId),
				String.valueOf(fragmentEntryInstanceId));

			sb.append("<div class=\"");
			sb.append(fragmentEntryClassName);
			sb.append(CharPool.SPACE);
			sb.append(fragmentEntryInstanceClassName);
			sb.append("\">");
			sb.append("<style>");
			sb.append(_getNamespacedCss(fragmentEntryClassName, css));
			sb.append("</style>");

			Optional<ServiceContext> serviceContextOptional =
				Optional.ofNullable(
					ServiceContextThreadLocal.getServiceContext());

			ServiceContext serviceContext = serviceContextOptional.orElse(
				new ServiceContext());

			String sanitizedHTML = SanitizerUtil.sanitize(
				serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
				serviceContext.getUserId(), FragmentEntry.class.getName(),
				fragmentEntryId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
				html, null);

			sb.append(sanitizedHTML);

			sb.append("<script>(function(){");
			sb.append(js);
			sb.append(";}());</script></div>");

			return sb.toString();
		}
		catch (SanitizerException se) {
			throw new SystemException(se);
		}
	}

	public static String renderFragmentEntry(
		long fragmentEntryId, String css, String html, String js) {

		return renderFragmentEntry(fragmentEntryId, 0, css, html, js);
	}

	private static String _getNamespacedCss(String namespace, String css) {
		Matcher matcher = _cssPattern.matcher(css);

		return matcher.replaceAll(CharPool.PERIOD + namespace + " $1");
	}

	private static final Pattern _cssPattern = Pattern.compile(
		"^([^\\{]+[^\\}]+\\})");

}