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

package com.liferay.object.web.internal.util;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.BooleanInfoFieldType;
import com.liferay.info.field.type.DateInfoFieldType;
import com.liferay.info.field.type.ImageInfoFieldType;
import com.liferay.info.field.type.InfoFieldType;
import com.liferay.info.field.type.NumberInfoFieldType;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.localized.bundle.FunctionInfoLocalizedValue;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.rest.context.path.RESTContextPathResolver;
import com.liferay.object.rest.context.path.RESTContextPathResolverRegistry;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectRelationshipLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.object.service.ObjectFieldSettingLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ObjectFieldDBTypeUtil {

	public static InfoField<?> addAttributes(
		InfoField.FinalStep finalStep, ObjectField objectField,
		ObjectScopeProviderRegistry objectScopeProviderRegistry,
		RESTContextPathResolverRegistry restContextPathResolverRegistry) {

		if (Objects.equals(
				objectField.getBusinessType(),
				ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

			finalStep.attribute(
				ImageInfoFieldType.ALLOWED_FILE_EXTENSIONS,
				_getAcceptedFileExtensions(objectField));

			finalStep.attribute(
				ImageInfoFieldType.MAX_FILE_SIZE,
				_getMaximumFileSize(objectField));

			finalStep.attribute(
				ImageInfoFieldType.SELECT_FROM_DOCUMENT_LIBRARY,
				_isSelectFromDocumentLibrary(objectField));
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_DECIMAL) ||
				 Objects.equals(
					 objectField.getBusinessType(),
					 ObjectFieldConstants.BUSINESS_TYPE_PRECISION_DECIMAL)) {

			finalStep.attribute(NumberInfoFieldType.DECIMAL, true);
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_PICKLIST)) {

			finalStep.attribute(
				SelectInfoFieldType.OPTIONS, _getOptions(objectField));
		}

		if (Objects.equals(
				objectField.getBusinessType(),
				ObjectFieldConstants.BUSINESS_TYPE_RELATIONSHIP)) {

			finalStep.attribute(
				SelectInfoFieldType.AUTOCOMPLETE_URL,
				_getAPIURL(
					objectField, objectScopeProviderRegistry,
					restContextPathResolverRegistry));
		}

		return finalStep.build();
	}

	public static InfoFieldType getInfoFieldType(ObjectField objectField) {
		if (Validator.isNotNull(objectField.getRelationshipType())) {
			return TextInfoFieldType.INSTANCE;
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_BOOLEAN)) {

			return BooleanInfoFieldType.INSTANCE;
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_DECIMAL) ||
				 Objects.equals(
					 objectField.getBusinessType(),
					 ObjectFieldConstants.BUSINESS_TYPE_INTEGER) ||
				 Objects.equals(
					 objectField.getBusinessType(),
					 ObjectFieldConstants.BUSINESS_TYPE_LONG_INTEGER) ||
				 Objects.equals(
					 objectField.getBusinessType(),
					 ObjectFieldConstants.BUSINESS_TYPE_PRECISION_DECIMAL)) {

			return NumberInfoFieldType.INSTANCE;
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

			return ImageInfoFieldType.INSTANCE;
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_DATE)) {

			return DateInfoFieldType.INSTANCE;
		}
		else if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_PICKLIST) ||
				 Objects.equals(
					 objectField.getBusinessType(),
					 ObjectFieldConstants.BUSINESS_TYPE_RELATIONSHIP)) {

			return SelectInfoFieldType.INSTANCE;
		}

		return TextInfoFieldType.INSTANCE;
	}

	private static String _getAcceptedFileExtensions(ObjectField objectField) {
		ObjectFieldSetting acceptedFileExtensionsObjectFieldSetting =
			ObjectFieldSettingLocalServiceUtil.fetchObjectFieldSetting(
				objectField.getObjectFieldId(), "acceptedFileExtensions");

		if (acceptedFileExtensionsObjectFieldSetting == null) {
			return StringPool.BLANK;
		}

		return acceptedFileExtensionsObjectFieldSetting.getValue();
	}

	private static String _getAPIURL(
		ObjectField objectField,
		ObjectScopeProviderRegistry objectScopeProviderRegistry,
		RESTContextPathResolverRegistry restContextPathResolverRegistry) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return StringPool.BLANK;
		}

		ObjectRelationship objectRelationship =
			ObjectRelationshipLocalServiceUtil.
				fetchObjectRelationshipByObjectFieldId2(
					objectField.getObjectFieldId());

		ObjectDefinition relatedObjectDefinition =
			ObjectDefinitionLocalServiceUtil.fetchObjectDefinition(
				objectRelationship.getObjectDefinitionId1());

		if (relatedObjectDefinition == null) {
			return StringPool.BLANK;
		}

		RESTContextPathResolver restContextPathResolver =
			restContextPathResolverRegistry.getRESTContextPathResolver(
				relatedObjectDefinition.getClassName());

		String restContextPath = restContextPathResolver.getRESTContextPath(
			_getGroupId(
				serviceContext.getRequest(), relatedObjectDefinition,
				objectScopeProviderRegistry));

		return PortalUtil.getPortalURL(serviceContext.getRequest()) +
			restContextPath;
	}

	private static long _getGroupId(
		HttpServletRequest httpServletRequest,
		ObjectDefinition objectDefinition,
		ObjectScopeProviderRegistry objectScopeProviderRegistry) {

		try {
			ObjectScopeProvider objectScopeProvider =
				objectScopeProviderRegistry.getObjectScopeProvider(
					objectDefinition.getScope());

			return objectScopeProvider.getGroupId(httpServletRequest);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return 0L;
		}
	}

	private static long _getMaximumFileSize(ObjectField objectField) {
		ObjectFieldSetting objectFieldSetting =
			ObjectFieldSettingLocalServiceUtil.fetchObjectFieldSetting(
				objectField.getObjectFieldId(), "maximumFileSize");

		if (objectFieldSetting == null) {
			return 0L;
		}

		return GetterUtil.getLong(objectFieldSetting.getValue());
	}

	private static List<SelectInfoFieldType.Option> _getOptions(
		ObjectField objectField) {

		List<SelectInfoFieldType.Option> options = new ArrayList<>();

		List<ListTypeEntry> listTypeEntries =
			ListTypeEntryLocalServiceUtil.getListTypeEntries(
				objectField.getListTypeDefinitionId());

		for (ListTypeEntry listTypeEntry : listTypeEntries) {
			options.add(
				new SelectInfoFieldType.Option(
					new FunctionInfoLocalizedValue<>(listTypeEntry::getName),
					listTypeEntry.getKey()));
		}

		return options;
	}

	private static boolean _isSelectFromDocumentLibrary(
		ObjectField objectField) {

		ObjectFieldSetting objectFieldSetting =
			ObjectFieldSettingLocalServiceUtil.fetchObjectFieldSetting(
				objectField.getObjectFieldId(), "fileSource");

		if (objectFieldSetting == null) {
			return false;
		}

		return Objects.equals(
			objectFieldSetting.getValue(), "documentsAndMedia");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectFieldDBTypeUtil.class);

}