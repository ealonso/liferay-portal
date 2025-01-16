/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FormStepLayoutDataItem} from '../../types/layout_data/FormStepLayoutDataItem';
import {REMOVE_FORM_STEP} from './types';

import type {LayoutData} from '../../types/layout_data/LayoutData';

export default function removeFormStep({
	itemId,
	layoutData,
}: {
	itemId: FormStepLayoutDataItem['itemId'];
	layoutData: LayoutData;
}) {
	return {
		itemId,
		layoutData,
		type: REMOVE_FORM_STEP,
	} as const;
}
