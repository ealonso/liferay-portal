/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import React from 'react';

export default function StructureFields() {
	return (
		<div className="border p-4 structure-builder__structure-fields">
			<h3 className="font-weight-semi-bold text-4">
				{Liferay.Language.get('structure-fields')}
			</h3>

			<EmptyState />
		</div>
	);
}

function EmptyState() {
	return (
		<div className="align-items-center d-flex flex-column mt-7">
			<p className="font-weight-semi-bold text-4">
				{Liferay.Language.get('no-fields-added-yet')}
			</p>

			<p>
				{Liferay.Language.get(
					'add-new-fields-to-start-building-your-structure'
				)}
			</p>

			<ClayButton displayType="secondary" size="sm">
				{Liferay.Language.get('add-field')}
			</ClayButton>
		</div>
	);
}
