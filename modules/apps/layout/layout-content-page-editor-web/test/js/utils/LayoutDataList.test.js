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

import {containsFragmentEntryLinkId} from '../../../src/main/resources/META-INF/resources/js/utils/LayoutDataList.es';

const LAYOUT_DATA_PERSONALIZATION = [
	{
		layoutData: {
			structure: [
				{
					columns: [
						{
							fragmentEntryLinkIds: ['37212', '37213']
						}
					]
				},
				{
					columns: [
						{
							fragmentEntryLinkIds: ['37218']
						},
						{
							fragmentEntryLinkIds: ['37215']
						}
					]
				}
			]
		},
		segmentsExperienceId: 'segmentsExperienceId1'
	},
	{
		layoutData: {
			structure: [
				{
					columns: [
						{
							fragmentEntryLinkIds: ['37212', '37213']
						}
					]
				},
				{
					columns: [
						{
							fragmentEntryLinkIds: ['37214']
						},
						{
							fragmentEntryLinkIds: ['37215']
						}
					]
				}
			]
		},
		segmentsExperienceId: 'segmentsExperienceId2'
	}
];

describe('confirmFragmnetEntryLinkIdLayoutDataList ', () => {
	test('should confirm fragmentEntryLinkId presence in a LayoutData different than the one selected', () => {
		expect(
			containsFragmentEntryLinkId(
				LAYOUT_DATA_PERSONALIZATION,
				'37214',
				'segmentsExperienceId1'
			)
		).toBe(true);

		expect(
			containsFragmentEntryLinkId(
				LAYOUT_DATA_PERSONALIZATION,
				'37215',
				'segmentsExperienceId1'
			)
		).toBe(true);
	});

	test('should confirm fragmentEntryLinkId presence in a LayoutData', () => {
		expect(
			containsFragmentEntryLinkId(LAYOUT_DATA_PERSONALIZATION, '37214')
		).toBe(true);
	});

	test('should confirm fragmentEntryLinkId ausence in a LayoutData different than the one selected', () => {
		expect(
			containsFragmentEntryLinkId(
				LAYOUT_DATA_PERSONALIZATION,
				'37214',
				'segmentsExperienceId2'
			)
		).toBe(false);
	});
});
