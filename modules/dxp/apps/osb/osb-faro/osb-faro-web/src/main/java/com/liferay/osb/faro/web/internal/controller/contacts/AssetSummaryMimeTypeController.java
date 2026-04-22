/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.controller.contacts;

import com.liferay.osb.faro.engine.client.model.AssetSummaryMimeType;
import com.liferay.osb.faro.engine.client.model.Results;
import com.liferay.osb.faro.model.FaroProject;
import com.liferay.osb.faro.web.internal.controller.BaseFaroController;
import com.liferay.osb.faro.web.internal.model.display.FaroFDSResultsDisplay;
import com.liferay.osb.faro.web.internal.model.display.contacts.AssetSummaryMimeTypeDisplay;

import java.util.function.Function;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Nilton Vieira
 */
@Component(service = AssetSummaryMimeTypeController.class)
@Path("/{groupId}/asset-summary-mime-types")
@Produces(MediaType.APPLICATION_JSON)
public class AssetSummaryMimeTypeController extends BaseFaroController {

	@GET
	public FaroFDSResultsDisplay getAssetSummaryMimeTypes(
			@PathParam("groupId") long groupId,
			@QueryParam("channelId") long channelId,
			@QueryParam("rangeEnd") String rangeEnd,
			@DefaultValue("30") @QueryParam("rangeKey") int rangeKey,
			@QueryParam("rangeStart") String rangeStart,
			@QueryParam("cur") int cur,
			@DefaultValue("20") @QueryParam("delta") int delta)
		throws Exception {

		FaroProject faroProject =
			faroProjectLocalService.getFaroProjectByGroupId(groupId);

		Results<AssetSummaryMimeType> results =
			contactsEngineClient.getAssetSummaryMimeTypes(
				faroProject, channelId, rangeEnd, rangeKey, rangeStart, cur,
				delta);

		Function<AssetSummaryMimeType, AssetSummaryMimeTypeDisplay> function =
			AssetSummaryMimeTypeDisplay::new;

		return new FaroFDSResultsDisplay(results, function, cur, delta);
	}

}