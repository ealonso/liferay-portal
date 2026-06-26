/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.audiences.web.internal.PoC;

import com.liferay.frontend.js.audiences.ElementVariations;
import com.liferay.frontend.js.audiences.ElementVariationsProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.frontend.hashed.files.HashedFilesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera Avellón
 */
@Component(
	property = "service.ranking:Integer=" + Integer.MAX_VALUE,
	service = ElementVariationsProvider.class
)
public class PoCElementVariationsProviderImpl
	implements ElementVariationsProvider {

	public PoCElementVariationsProviderImpl() {
		String javaScript = _read("element-variations.js.tpl");

		_elementVariations = new ElementVariations(
			javaScript, HashedFilesUtil.computeHash(javaScript));
	}

	@Override
	public ElementVariations getElementVariations(long plid) {
		return _elementVariations;
	}

	private String _read(String name) {
		try (InputStream inputStream =
				PoCElementVariationsProviderImpl.class.getResourceAsStream(
					"dependencies/" + name)) {

			return StringUtil.read(inputStream);
		}
		catch (Exception exception) {
			_log.error("Unable to read template " + name, exception);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PoCElementVariationsProviderImpl.class);

	private final ElementVariations _elementVariations;

}