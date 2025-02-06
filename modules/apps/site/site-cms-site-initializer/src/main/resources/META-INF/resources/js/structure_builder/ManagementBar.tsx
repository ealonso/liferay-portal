/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import React from 'react';

export default function ManagementBar() {
	return (
		<div className="align-items-center border-bottom d-flex justify-content-between px-4 py-2 structure-builder__management-bar">
			<div className="align-items-center c-gap-3 d-flex">
				<ClayButtonWithIcon
					aria-label={Liferay.Language.get('back')}
					borderless
					displayType="secondary"
					symbol="angle-left"
				/>

				<h2 className="font-weight-semi-bold m-0 text-5">
					{Liferay.Language.get('new-structure')}
				</h2>
			</div>

			<div className="c-gap-3 d-flex">
				<ClayButton borderless displayType="secondary" size="sm">
					{Liferay.Language.get('cancel')}
				</ClayButton>

				<ClayButton displayType="secondary" size="sm">
					{Liferay.Language.get('save')}
				</ClayButton>

				<ClayButton displayType="primary" size="sm">
					{Liferay.Language.get('publish')}
				</ClayButton>
			</div>
		</div>
	);
}
