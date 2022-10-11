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

package com.liferay.object.web.internal.info.item.provider;

import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;

import java.util.Collections;
import java.util.Locale;

/**
 * @author Guilherme Camacho
 */
public class ObjectEntryInfoItemObjectProvider
	implements InfoItemObjectProvider<ObjectEntry> {

	public ObjectEntryInfoItemObjectProvider(
		ObjectDefinition objectDefinition,
		ObjectEntryManager objectEntryManager) {

		_objectDefinition = objectDefinition;
		_objectEntryManager = objectEntryManager;
	}

	@Override
	public ObjectEntry getInfoItem(InfoItemIdentifier infoItemIdentifier)
		throws NoSuchInfoItemException {

		if (!(infoItemIdentifier instanceof ClassPKInfoItemIdentifier)) {
			throw new NoSuchInfoItemException(
				"Unsupported info item identifier type " + infoItemIdentifier);
		}

		ClassPKInfoItemIdentifier classPKInfoItemIdentifier =
			(ClassPKInfoItemIdentifier)infoItemIdentifier;

		ObjectEntry objectEntry = null;

		try {
			objectEntry = _objectEntryManager.fetchObjectEntry(
				_getDTOConverterContext(
					null, null, LocaleUtil.getSiteDefault()),
				_objectDefinition, classPKInfoItemIdentifier.getClassPK());
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		if (objectEntry == null) {
			throw new NoSuchInfoItemException(
				"Unable to get object entry " +
					classPKInfoItemIdentifier.getClassPK());
		}

		return objectEntry;
	}

	//	private DTOConverterContext _getDTOConverterContext() throws Exception {
	//		return new DefaultDTOConverterContext(
	//			false, Collections.emptyMap(), _dtoConverterRegistry, null,
	//			LocaleUtil.getDefault(), null, _user);
	//	}
	//
	//	private DTOConverterContext _getDTOConverterContext() {
	//		return new DefaultDTOConverterContext(
	//			false, null, null, _objectRequestHelper.getRequest(), null,
	//			_themeDisplay.getLocale(), null, _themeDisplay.getUser());
	//	}

	@Override
	public ObjectEntry getInfoItem(long classPK)
		throws NoSuchInfoItemException {

		ClassPKInfoItemIdentifier classPKInfoItemIdentifier =
			new ClassPKInfoItemIdentifier(classPK);

		return getInfoItem(classPKInfoItemIdentifier);
	}

	private DefaultDTOConverterContext _getDTOConverterContext(
		Long objectEntryId, User user, Locale locale) {

		return new DefaultDTOConverterContext(
			false, Collections.emptyMap(), null, null, objectEntryId, locale,
			null, user);
	}

	private final ObjectDefinition _objectDefinition;
	private final ObjectEntryManager _objectEntryManager;

}