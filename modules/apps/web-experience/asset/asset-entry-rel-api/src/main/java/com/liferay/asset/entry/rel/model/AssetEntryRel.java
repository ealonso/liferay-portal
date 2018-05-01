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

package com.liferay.asset.entry.rel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetEntryRel service. Represents a row in the &quot;AssetEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRelModel
 * @see com.liferay.asset.entry.rel.model.impl.AssetEntryRelImpl
 * @see com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.asset.entry.rel.model.impl.AssetEntryRelImpl")
@ProviderType
public interface AssetEntryRel extends AssetEntryRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.asset.entry.rel.model.impl.AssetEntryRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntryRel, Long> ASSET_ENTRY_REL_ID_ACCESSOR =
		new Accessor<AssetEntryRel, Long>() {
			@Override
			public Long get(AssetEntryRel assetEntryRel) {
				return assetEntryRel.getAssetEntryRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntryRel> getTypeClass() {
				return AssetEntryRel.class;
			}
		};
}