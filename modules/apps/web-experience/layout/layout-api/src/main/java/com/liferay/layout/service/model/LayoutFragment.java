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

package com.liferay.layout.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LayoutFragment service. Represents a row in the &quot;LayoutFragment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentModel
 * @see com.liferay.layout.service.model.impl.LayoutFragmentImpl
 * @see com.liferay.layout.service.model.impl.LayoutFragmentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.layout.service.model.impl.LayoutFragmentImpl")
@ProviderType
public interface LayoutFragment extends LayoutFragmentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.layout.service.model.impl.LayoutFragmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LayoutFragment, Long> LAYOUT_FRAGMENT_ID_ACCESSOR =
		new Accessor<LayoutFragment, Long>() {
			@Override
			public Long get(LayoutFragment layoutFragment) {
				return layoutFragment.getLayoutFragmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LayoutFragment> getTypeClass() {
				return LayoutFragment.class;
			}
		};
}