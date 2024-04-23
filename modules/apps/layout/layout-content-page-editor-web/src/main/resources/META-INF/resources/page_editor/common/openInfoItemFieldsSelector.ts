/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openSelectionModal} from 'frontend-js-web';

import {config} from '../app/config/index';

type Props = {
	onCancel: () => void;
	onSave: () => void;
};

export function openInfoItemFieldsSelector({onCancel, onSave}: Props) {
	openSelectionModal({
		onClose: onCancel,
		onSelect: onSave,
		title: Liferay.Language.get('manage-form-fields'),
		url: config.infoFieldItemSelectorURL,
	});
}
