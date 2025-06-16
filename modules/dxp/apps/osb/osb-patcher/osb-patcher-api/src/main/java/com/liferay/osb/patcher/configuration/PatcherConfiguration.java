/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.patcher.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Eudaldo Alonso
 */
@ExtendedObjectClassDefinition(
	generateUI = false, scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.osb.patcher.configuration.PatcherConfiguration",
	localization = "content/Language"
)
public interface PatcherConfiguration {

	@Meta.AD(deflt = "https://github.com/liferay", required = false)
	public String githubURL();

	@Meta.AD(
		deflt = "osb.accountentry/get-account-entry-by-code", required = false
	)
	public String helpCenterGetAccountApiEndpoint();

	@Meta.AD(
		deflt = "osbcustomer.ticketattachment/add-ticket-attachment",
		required = false
	)
	public String helpCenterTicketAttachmentApiEndpoint();

	@Meta.AD(deflt = "/osb/zendesk/ticket", required = false)
	public String helpCenterTokenTicketDir();

	@Meta.AD(
		deflt = "https://help.liferay.com/hc/en-us/requests", required = false
	)
	public String helpCenterURL();

	@Meta.AD(deflt = "", required = false)
	public String helpCenterFileRepoId();

	@Meta.AD(deflt = "https://lesa-files-uat.us.liferay.com", required = false)
	public String helpCenterFileRepoURL();

	@Meta.AD(
		deflt = "https://customer-uat.liferay.com/api/jsonws", required = false
	)
	public String helpCenterJsonwsURL();

	@Meta.AD(deflt = "", required = false)
	public String helpCenterApiPassword();

	@Meta.AD(deflt = "", required = false)
	public String helpCenterApiUserName();

	@Meta.AD(deflt = "", required = false)
	public String hotfixMountPath();

	@Meta.AD(
		deflt = "https://liferay.atlassian.net/wiki/spaces/SUPPORT/pages/2117700185/Why+does+Patcher+modify+the+ticket+list+automatically",
		required = false
	)
	public String infoModifyTicketsListURL();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsToken();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsAdminUserName();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsAdminUserToken();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsBuildWithParametersPath();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsLoadBalancerBaseInvocationURL();

	@Meta.AD(deflt = "", required = false)
	public String jenkinsURL();

	@Meta.AD(deflt = "false", required = false)
	public boolean jenkinsLoadBalancerEnabled();

	@Meta.AD(deflt = "https://liferay.atlassian.net/browse", required = false)
	public String jiraURL();

	@Meta.AD(
		deflt = "https://www.liferay.com/group/customer/support/-/support/ticket",
		required = false
	)
	public String lesaURL();

	@Meta.AD(
		deflt = "http://www.liferay.com/web/${liferay:screenName}/profile",
		required = false
	)
	public String liferayUsersProfileURL();

	@Meta.AD(deflt = "liferay,liferaysecurity,patchertest", required = false)
	public String[] patcherAccountWhitelist();

	@Meta.AD(deflt = "", required = false)
	public String patcherAgentJenkinsURL();

	@Meta.AD(deflt = "", required = false)
	public String patcherBuildDownloadURL();

	@Meta.AD(deflt = "fix-pack-fix-", required = false)
	public String patcherGitTagPrefix();

	@Meta.AD(deflt = "true", required = false)
	public boolean patcherJenkinsRequestsEnabled();

	@Meta.AD(deflt = "liferay-portal-ee", required = false)
	public String patcherLiferayPortalRepository();

	@Meta.AD(deflt = "", required = false)
	public String patcherPubsubCredentialFilePath();

	@Meta.AD(deflt = "", required = false)
	public String patcherPubsubProjectId();

	@Meta.AD(deflt = "", required = false)
	public String patcherPubsubSubscriptionId();

	@Meta.AD(deflt = "fix", required = false)
	public String patcherSharedRequestAddFixPatcherType();

	@Meta.AD(deflt = "build", required = false)
	public String patcherSharedRequestBuildPatchPatcherType();

	@Meta.AD(deflt = "false", required = false)
	public boolean patcherScanningEnabled();

	@Meta.AD(deflt = "/build/jenkins", required = false)
	public String patcherStatusBuildJenkinsPath();

	@Meta.AD(deflt = "/build/jenkins/test", required = false)
	public String patcherStatusBuildJenkinsTestPath();

	@Meta.AD(deflt = "/build", required = false)
	public String patcherStatusBuildPath();

	@Meta.AD(deflt = "/fix", required = false)
	public String patcherStatusFixPath();

	@Meta.AD(deflt = "C:/osbPatcherStatus", required = false)
	public String patcherStatusPath();

	@Meta.AD(deflt = "true", required = false)
	public boolean patcherTestsEnabled();

	@Meta.AD(
		deflt = "https://liferay.atlassian.net/wiki/spaces/SUPPORT/pages/1956577726/Patcher+Troubleshooting+Guide",
		required = false
	)
	public String troubleshootingURL();

}