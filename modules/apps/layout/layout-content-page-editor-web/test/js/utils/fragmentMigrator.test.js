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

import editableValuesMigrator from '../../../src/main/resources/META-INF/resources/js/utils/fragmentMigrator.es';

describe('fragmentMigrator', () => {
	test('should migrate single editable fragment', () => {
		const inputData = JSON.stringify(singleEditableFragmentInput);

		expect(
			editableValuesMigrator(inputData, DEFAULT_SEGMENTS_EXPERIENCE_ID)
		).toEqual(singleEditableFragmentExpected);
	});

	test('should migrate double editable fragment', () => {
		const inputData = JSON.stringify(doubleEditableFragmentInput);

		expect(
			editableValuesMigrator(inputData, DEFAULT_SEGMENTS_EXPERIENCE_ID)
		).toEqual(doubleEditableFragmentExpected);
	});

	test('should leave already migrated segments as they are', () => {
		const doubleInputData = JSON.stringify(doubleEditableFragmentExpected);
		const singleInputData = JSON.stringify(singleEditableFragmentExpected);

		expect(editableValuesMigrator(doubleInputData)).toEqual(
			doubleEditableFragmentExpected
		);

		expect(editableValuesMigrator(singleInputData)).toEqual(
			singleEditableFragmentExpected
		);
	});
});

const DEFAULT_SEGMENTS_EXPERIENCE_ID = 'defaultSegmentsExperienceId';

const singleEditableFragmentInput = {
	'com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor': {
		'editable-fragment-id': {
			defaultValue: 'Livingstone Hotels and Resorts',
			en_US: 'English Variation',
			es_ES: 'Spanish variation',
			pt_BR: 'Portuguese variation'
		}
	}
};

const singleEditableFragmentExpected = {
	'com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor': {
		'editable-fragment-id': {
			[DEFAULT_SEGMENTS_EXPERIENCE_ID]: {
				en_US: 'English Variation',
				es_ES: 'Spanish variation',
				pt_BR: 'Portuguese variation'
			},
			defaultValue: 'Livingstone Hotels and Resorts'
		}
	}
};

const doubleEditableFragmentInput = {
	'com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor': {
		'editable-fragment-id': {
			defaultValue: 'Livingstone Hotels and Resorts',
			en_US: 'English Variation',
			es_ES: 'Spanish variation',
			pt_BR: 'Portuguese variation'
		},
		'editable-fragment-id-2': {
			defaultValue: 'Livingstone Hotels and Resorts',
			en_US: 'Livingstone Hotels and Resorts English Variation',
			es_ES: 'Livingstone Hotels and Resorts Spanish variation',
			pt_BR: 'Livingstone Hotels and Resorts Portuguese variation'
		}
	}
};

const doubleEditableFragmentExpected = {
	'com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor': {
		'editable-fragment-id': {
			[DEFAULT_SEGMENTS_EXPERIENCE_ID]: {
				en_US: 'English Variation',
				es_ES: 'Spanish variation',
				pt_BR: 'Portuguese variation'
			},
			defaultValue: 'Livingstone Hotels and Resorts'
		},
		'editable-fragment-id-2': {
			[DEFAULT_SEGMENTS_EXPERIENCE_ID]: {
				en_US: 'Livingstone Hotels and Resorts English Variation',
				es_ES: 'Livingstone Hotels and Resorts Spanish variation',
				pt_BR: 'Livingstone Hotels and Resorts Portuguese variation'
			},
			defaultValue: 'Livingstone Hotels and Resorts'
		}
	}
};
