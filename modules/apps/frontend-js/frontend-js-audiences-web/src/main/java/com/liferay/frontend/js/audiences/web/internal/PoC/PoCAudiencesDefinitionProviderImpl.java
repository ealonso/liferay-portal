/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.audiences.web.internal.PoC;

import com.liferay.frontend.js.audiences.AudiencesDefinition;
import com.liferay.frontend.js.audiences.AudiencesDefinitionProvider;
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
	service = AudiencesDefinitionProvider.class
)
public class PoCAudiencesDefinitionProviderImpl
	implements AudiencesDefinitionProvider {

	public PoCAudiencesDefinitionProviderImpl() {
		String json = _read("audiences-definition.json.tpl");

		_audiencesDefinition = new AudiencesDefinition(
			json, HashedFilesUtil.computeHash(json));
	}

	@Override
	public AudiencesDefinition getAudiencesDefinition(long companyId) {
		return _audiencesDefinition;
	}

	private String _read(String name) {
		try (InputStream inputStream =
				PoCAudiencesDefinitionProviderImpl.class.getResourceAsStream(
					"dependencies/" + name)) {

			return StringUtil.read(inputStream);
		}
		catch (Exception exception) {
			_log.error("Unable to read template " + name, exception);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PoCAudiencesDefinitionProviderImpl.class);

	private final AudiencesDefinition _audiencesDefinition;

}