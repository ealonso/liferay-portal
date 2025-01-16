/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FormLayoutDataItem} from '../../types/layout_data/FormLayoutDataItem';
import {LayoutData} from '../../types/layout_data/LayoutData';
import {FragmentEntryLinkMap} from '../actions/addFragmentEntryLinks';
import {FREEMARKER_FRAGMENT_ENTRY_PROCESSOR} from '../config/constants/freemarkerFragmentEntryProcessor';
import updateFragmentConfiguration from '../thunks/updateFragmentConfiguration';
import {getStepperChild} from './getStepperChild';

export function updateStepperConfiguration({
	dispatch,
	formId,
	fragmentEntryLinks,
	layoutData,
	numberOfSteps,
}: {
	dispatch: (action: ReturnType<typeof updateFragmentConfiguration>) => void;
	formId: FormLayoutDataItem['itemId'];
	fragmentEntryLinks: FragmentEntryLinkMap;
	layoutData: LayoutData;
	numberOfSteps: number;
}) {
	const form = layoutData.items[formId] as FormLayoutDataItem;

	const stepper = getStepperChild(form, layoutData, fragmentEntryLinks);

	if (stepper) {
		const fragmentEntryLink =
			fragmentEntryLinks[stepper.config.fragmentEntryLinkId];

		const configurationValues = {
			...fragmentEntryLink.editableValues[
				FREEMARKER_FRAGMENT_ENTRY_PROCESSOR
			],
			numberOfSteps,
		};

		dispatch(
			updateFragmentConfiguration({
				configurationValues,
				fragmentEntryLink,
			})
		);
	}
}
