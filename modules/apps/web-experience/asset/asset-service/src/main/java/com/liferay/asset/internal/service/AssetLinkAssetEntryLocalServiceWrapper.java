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

package com.liferay.asset.internal.service;

import com.liferay.asset.kernel.exception.NoSuchEntryException;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceWrapper;
import com.liferay.asset.link.model.AssetLink;
import com.liferay.asset.link.model.AssetLinkConstants;
import com.liferay.asset.link.service.AssetLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetLinkAssetEntryLocalServiceWrapper
	extends AssetEntryLocalServiceWrapper {

	public AssetLinkAssetEntryLocalServiceWrapper() {
		super(null);
	}

	public AssetLinkAssetEntryLocalServiceWrapper(
		AssetEntryLocalService assetEntryLocalService) {

		super(assetEntryLocalService);
	}

	@Override
	public void deleteEntry(AssetEntry entry) throws PortalException {
		super.deleteEntry(entry);

		_assetLinkLocalService.deleteLinks(entry.getEntryId());
	}

	@Override
	public List<AssetEntry> getChildEntries(long entryId)
		throws PortalException {

		List<AssetEntry> entries = new ArrayList<>();

		List<AssetLink> links = _assetLinkLocalService.getDirectLinks(
			entryId, AssetLinkConstants.TYPE_CHILD);

		for (AssetLink link : links) {
			AssetEntry curAsset = getEntry(link.getEntryId2());

			entries.add(curAsset);
		}

		return entries;
	}

	@Override
	public AssetEntry getNextEntry(long entryId) throws PortalException {
		try {
			getParentEntry(entryId);
		}
		catch (NoSuchEntryException nsee) {
			List<AssetEntry> childEntries = getChildEntries(entryId);

			if (childEntries.isEmpty()) {
				throw nsee;
			}

			return childEntries.get(0);
		}

		List<AssetLink> links = _assetLinkLocalService.getDirectLinks(
			entryId, AssetLinkConstants.TYPE_CHILD);

		for (int i = 0; i < links.size(); i++) {
			AssetLink link = links.get(i);

			if (link.getEntryId2() == entryId) {
				if ((i + 1) >= links.size()) {
					throw new NoSuchEntryException("{entryId=" + entryId + "}");
				}
				else {
					AssetLink nextLink = links.get(i + 1);

					return getEntry(nextLink.getEntryId2());
				}
			}
		}

		throw new NoSuchEntryException("{entryId=" + entryId + "}");
	}

	@Override
	public AssetEntry getParentEntry(long entryId) throws PortalException {
		List<AssetLink> links = _assetLinkLocalService.getReverseLinks(
			entryId, AssetLinkConstants.TYPE_CHILD);

		if (links.isEmpty()) {
			throw new NoSuchEntryException("{entryId=" + entryId + "}");
		}

		AssetLink link = links.get(0);

		return getEntry(link.getEntryId1());
	}

	@Override
	public AssetEntry getPreviousEntry(long entryId) throws PortalException {
		getParentEntry(entryId);

		List<AssetLink> links = _assetLinkLocalService.getDirectLinks(
			entryId, AssetLinkConstants.TYPE_CHILD);

		for (int i = 0; i < links.size(); i++) {
			AssetLink link = links.get(i);

			if (link.getEntryId2() == entryId) {
				if (i == 0) {
					throw new NoSuchEntryException("{entryId=" + entryId + "}");
				}
				else {
					AssetLink nextAssetLink = links.get(i - 1);

					return getEntry(nextAssetLink.getEntryId2());
				}
			}
		}

		throw new NoSuchEntryException("{entryId=" + entryId + "}");
	}

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

}