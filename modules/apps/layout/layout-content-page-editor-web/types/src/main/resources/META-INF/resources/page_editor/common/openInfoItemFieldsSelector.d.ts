/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

declare type Props = {
	onCancel: () => void;
	onSave: () => void;
};
export declare function openInfoItemFieldsSelector({
	onCancel,
	onSave,
}: Props): void;
export {};
