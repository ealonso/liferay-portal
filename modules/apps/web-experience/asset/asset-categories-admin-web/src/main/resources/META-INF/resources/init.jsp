<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.asset.categories.admin.web.constants.AssetCategoriesConstants" %><%@
page import="com.liferay.asset.categories.admin.web.internal.display.context.AssetCategoriesDisplayContext" %><%@
page import="com.liferay.asset.categories.admin.web.internal.util.AssetCategoryUtil" %><%@
page import="com.liferay.asset.exception.AssetCategoryNameException" %><%@
page import="com.liferay.asset.exception.CategoryPropertyKeyException" %><%@
page import="com.liferay.asset.exception.CategoryPropertyValueException" %><%@
page import="com.liferay.asset.exception.DuplicateCategoryException" %><%@
page import="com.liferay.asset.exception.DuplicateCategoryPropertyException" %><%@
page import="com.liferay.asset.exception.DuplicateVocabularyException" %><%@
page import="com.liferay.asset.exception.NoSuchCategoryException" %><%@
page import="com.liferay.asset.exception.NoSuchVocabularyException" %><%@
page import="com.liferay.asset.exception.VocabularyNameException" %><%@
page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.asset.kernel.NoSuchClassTypeException" %><%@
page import="com.liferay.asset.kernel.model.AssetRendererFactory" %><%@
page import="com.liferay.asset.kernel.model.ClassType" %><%@
page import="com.liferay.asset.kernel.model.ClassTypeReader" %><%@
page import="com.liferay.asset.model.AssetCategory" %><%@
page import="com.liferay.asset.model.AssetCategoryConstants" %><%@
page import="com.liferay.asset.model.AssetCategoryProperty" %><%@
page import="com.liferay.asset.model.AssetVocabulary" %><%@
page import="com.liferay.asset.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.asset.service.AssetCategoryPropertyLocalServiceUtil" %><%@
page import="com.liferay.asset.service.AssetCategoryPropertyServiceUtil" %><%@
page import="com.liferay.asset.service.AssetVocabularyServiceUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PredicateFilter" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portlet.asset.util.AssetVocabularySettingsHelper" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Collections" %><%@
page import="java.util.List" %><%@
page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
AssetCategoriesDisplayContext assetCategoriesDisplayContext = new AssetCategoriesDisplayContext(renderRequest, renderResponse, request);
%>

<%@ include file="/init-ext.jsp" %>