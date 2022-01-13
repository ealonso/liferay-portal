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

import {
	delegate,
	fetch,
	objectToFormData,
	runScriptsInElement,
} from 'frontend-js-web';

export default function ({
	assetClassName,
	ddmStructureFieldName,
	ddmStructureFieldValue,
	eventName,
	fieldsnamespace,
	getFieldItemURL,
	namespace,
}) {
	const componentId = `${namespace}${fieldsnamespace}ddmForm`;
	const selectDDMStructureFieldForm = document.getElementById(
		`${namespace}selectDDMStructureFieldForm`
	);

	const eventDelegates = [];

	const onClickApplyButton = function () {
		const ddmForm = Liferay.component(componentId);

		ddmForm.updateDDMFormInputValue();

		const form = document.getElementById(`${namespace}fieldForm`);

		fetch(form.action, {
			body: new FormData(form),
			method: 'POST',
		})
			.then((response) => response.json())
			.then((response) => {
				const message = document.getElementById(`${namespace}message`);

				if (response.success) {
					message.classList.add('hide');

					Liferay.Util.getOpener().Liferay.fire(eventName, {
						data: {
							className: assetClassName,
							displayValue: response.displayValue,
							value: response.value,
						},
					});

					Liferay.Util.getWindow().destroy();
				}
				else {
					message.classList.remove('hide');
				}
			});
	};

	const clickSubmitForm = delegate(
		selectDDMStructureFieldForm,
		'click',
		`#${namespace}applyButton`,
		onClickApplyButton
	);

	eventDelegates.push(clickSubmitForm);

	const selectDDMStructureFieldContainer = document.getElementById(
		`${namespace}selectDDMStructureFieldContainer`
	);

	const onChangeField = () => {
		const fieldNameSelector = document.getElementById(
			`${namespace}fieldName`
		);

		if (fieldNameSelector.value !== '') {
			fetch(getFieldItemURL, {
				body: objectToFormData({
					[`${namespace}name`]: fieldNameSelector.value,
				}),
				method: 'POST',
			})
				.then((response) => response.text())
				.then((response) => {
					selectDDMStructureFieldContainer.innerHTML = response;

					runScriptsInElement(selectDDMStructureFieldContainer);

					Liferay.componentReady(componentId).then(() => {
						const initialDDMForm = Liferay.component(componentId);

						initialDDMForm.get('fields').forEach((field) => {
							if (field.get('name') === ddmStructureFieldName) {
								field.setValue(ddmStructureFieldValue);
							}
						});
					});
				});
		}
		else {
			selectDDMStructureFieldContainer.innerHTML = '';
		}
	};

	const changeField = delegate(
		selectDDMStructureFieldForm,
		'change',
		`select#${namespace}fieldName`,
		onChangeField
	);

	eventDelegates.push(changeField);

	return {
		dispose() {
			eventDelegates.forEach((eventDelegate) => eventDelegate.dispose());
		},
	};
}
