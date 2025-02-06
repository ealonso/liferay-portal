/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLabel from '@clayui/label';
import ClayLayout from '@clayui/layout';
import React from 'react';

export default function Settings() {
	return (
		<ClayLayout.ContainerFluid view>
			<ClayLabel className="mb-3" displayType="info">
				{Liferay.Language.get('content')}
			</ClayLabel>

			<p className="font-weight-semi-bold text-7">Untitled Structure</p>
		</ClayLayout.ContainerFluid>
	);
}
