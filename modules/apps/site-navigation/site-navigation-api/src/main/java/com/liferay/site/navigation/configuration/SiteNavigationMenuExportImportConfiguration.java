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

package com.liferay.site.navigation.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Rubén Pulido
 */
@ExtendedObjectClassDefinition(
	category = "infrastructure",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.site.navigation.configuration.SiteNavigationMenuExportImportConfiguration",
	localization = "content/Language",
	name = "site-navigation-menu-export-import-configuration-name"
)
public interface SiteNavigationMenuExportImportConfiguration {

	@Meta.AD(
		deflt = "false",
		description = "in-case-staging-is-active,-this-configuration-will-be-overridden",
		id = "export.referenced.layouts",
		name = "export-referenced-pages-(content-and-widget-pages)-along-with-their-menu-items",
		required = false
	)
	public boolean exportReferencedLayouts();

}