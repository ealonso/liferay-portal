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

package com.liferay.asset.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetEntryAssetTagRel service. Represents a row in the &quot;AssetEntryAssetTagRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetTagRelModel
 * @see com.liferay.asset.model.impl.AssetEntryAssetTagRelImpl
 * @see com.liferay.asset.model.impl.AssetEntryAssetTagRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.asset.model.impl.AssetEntryAssetTagRelImpl")
@ProviderType
public interface AssetEntryAssetTagRel extends AssetEntryAssetTagRelModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.asset.model.impl.AssetEntryAssetTagRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntryAssetTagRel, Long> ENTRY_ID_ACCESSOR = new Accessor<AssetEntryAssetTagRel, Long>() {
			@Override
			public Long get(AssetEntryAssetTagRel assetEntryAssetTagRel) {
				return assetEntryAssetTagRel.getEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntryAssetTagRel> getTypeClass() {
				return AssetEntryAssetTagRel.class;
			}
		};

	public static final Accessor<AssetEntryAssetTagRel, Long> ASSET_ENTRY_ID_ACCESSOR =
		new Accessor<AssetEntryAssetTagRel, Long>() {
			@Override
			public Long get(AssetEntryAssetTagRel assetEntryAssetTagRel) {
				return assetEntryAssetTagRel.getAssetEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntryAssetTagRel> getTypeClass() {
				return AssetEntryAssetTagRel.class;
			}
		};

	public static final Accessor<AssetEntryAssetTagRel, Long> ASSET_TAG_ID_ACCESSOR =
		new Accessor<AssetEntryAssetTagRel, Long>() {
			@Override
			public Long get(AssetEntryAssetTagRel assetEntryAssetTagRel) {
				return assetEntryAssetTagRel.getAssetTagId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntryAssetTagRel> getTypeClass() {
				return AssetEntryAssetTagRel.class;
			}
		};
}