<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/site-navigation" prefix="liferay-site-navigation" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.asset.display.page.item.selector.web.internal.display.context.AssetDisplayPagesItemSelectorViewDisplayContext" %><%@
page import="com.liferay.asset.display.page.item.selector.web.internal.frontend.taglib.clay.servlet.taglib.DisplayPageTemplateCollectionHorizontalCard" %><%@
page import="com.liferay.asset.display.page.item.selector.web.internal.frontend.taglib.clay.servlet.taglib.DisplayPageVerticalCard" %><%@
page import="com.liferay.layout.page.template.model.LayoutPageTemplateCollection" %><%@
page import="com.liferay.layout.page.template.model.LayoutPageTemplateEntry" %><%@
page import="com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil" %>