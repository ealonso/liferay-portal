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

import ClayAlert from '@clayui/alert';
import {Option, Picker, Text} from '@clayui/core';
import {ClaySelectWithOption} from '@clayui/form';
import Label from '@clayui/label';
import Layout from '@clayui/layout';
import ClayLink from '@clayui/link';
import {fetch} from 'frontend-js-web';
import React, {useCallback, useEffect, useRef, useState} from 'react';

function SegmentsAndExperiencesSelector({
	deactivateSimulationURL,
	namespace,
	segmentationEnabled,
	segmentsCompanyConfigurationURL,
	segmentsEntries,
	segmentsExperiences,
	showEmptyMessage,
	simulateSegmentsEntriesURL,
}) {
	const [alertVisible, setAlertVisible] = useState(!segmentationEnabled);
	const [selectedPreviewOption, setSelectedPreviewOption] = useState(
		'segments'
	);
	const [selectedSegment, setSelectedSegment] = useState('');
	const [selectedExperience, setSelectedExperience] = useState('');

	const formRef = useRef(null);

	const options = [
		{
			label: 'Segments',
			value: 'segments',
		},
		{
			label: 'Experiences',
			value: 'experiences',
		},
	];

	const fetchDeactivateSimulation = useCallback(() => {
		fetch(deactivateSimulationURL, {
			body: new FormData(formRef.current),
			method: 'POST',
		}).then(() => {
			const simulationElements = document.querySelectorAll(
				`#${formRef.current.id} input`
			);

			for (let i = 0; i < simulationElements.length; i++) {
				simulationElements[i].setAttribute('checked', false);
			}
		});
	}, [deactivateSimulationURL]);

	const simulateSegmentsEntries = useCallback(() => {
		fetch(simulateSegmentsEntriesURL, {
			body: new FormData(formRef.current),
			method: 'POST',
		}).then(() => {
			const iframe = document.querySelector('iframe');

			if (iframe?.contentWindow) {
				iframe.contentWindow.location.reload();
			}
		});
	}, [simulateSegmentsEntriesURL]);

	useEffect(() => {
		formRef.current.addEventListener('change', simulateSegmentsEntries);

		const deactivateSimulationEventHandler = Liferay.on(
			'SimulationMenu:closeSimulationPanel',
			fetchDeactivateSimulation
		);

		const openSimulationPanelEventHandler = Liferay.on(
			'SimulationMenu:openSimulationPanel',
			simulateSegmentsEntries
		);

		return () => {
			deactivateSimulationEventHandler.detach();
			openSimulationPanelEventHandler.detach();
			formRef.removeEventListener('change', simulateSegmentsEntries);
		};
	}, [fetchDeactivateSimulation, simulateSegmentsEntries]);

	const handleExperienceChange = (value) => {
		setSelectedExperience(value);
		simulateSegmentsEntries();
	};

	return (
		<>
			{showEmptyMessage ? (
				<p className="mb-4 mt-1 small">
					{Liferay.Language.get('no-segments-have-been-added-yet')}
				</p>
			) : (
				<form method="post" name="segmentsSimulationFm" ref={formRef}>
					{alertVisible && (
						<ClayAlert
							dismissible={true}
							displayType="warning"
							onClose={() => {
								setAlertVisible(false);
							}}
						>
							<strong>
								{Liferay.Language.get(
									'experiences-cannot-be-displayed-because-segmentation-is-disabled'
								)}
							</strong>

							{segmentsCompanyConfigurationURL ? (
								<ClayLink
									href={segmentsCompanyConfigurationURL}
								>
									{Liferay.Language.get(
										'to-enable,-go-to-instance-settings'
									)}
								</ClayLink>
							) : (
								<span>
									{Liferay.Language.get(
										'contact-your-system-administrator-to-enable-it'
									)}
								</span>
							)}
						</ClayAlert>
					)}

					{!!segmentsEntries.length &&
						segmentsExperiences.length > 1 && (
							<div className="form-group">
								<label htmlFor="segmentsOrExperiences">
									{Liferay.Language.get('preview-by')}
								</label>

								<ClaySelectWithOption
									aria-label={Liferay.Language.get(
										'preview-by'
									)}
									id="segmentsOrExperiences"
									onChange={({target}) => {
										setSelectedPreviewOption(target.value);
									}}
									options={options}
									value={selectedPreviewOption}
								/>
							</div>
						)}

					<ul className="list-unstyled">
						{selectedPreviewOption === 'segments' && (
							<div className="form-group">
								<label htmlFor={`${namespace}segmentsEntryId`}>
									{Liferay.Language.get('segments')}
								</label>

								<ClaySelectWithOption
									aria-label={Liferay.Language.get(
										'segments'
									)}
									id={`${namespace}segmentsEntryId`}
									name={`${namespace}segmentsEntryId`}
									onChange={({target}) => {
										setSelectedSegment(target.value);
									}}
									options={segmentsEntries.map((segment) => {
										return {
											label: segment.name,
											value: segment.id,
										};
									})}
									value={selectedSegment}
								/>
							</div>
						)}

						{selectedPreviewOption === 'experiences' && (
							<div className="form-group">
								<label
									htmlFor={`${namespace}segmentsExperienceId`}
									id={`${namespace}segmentsExperienceLabelId`}
								>
									{Liferay.Language.get('experience')}
								</label>

								<Picker
									aria-labelledby={`${namespace}segmentsExperienceLabelId`}
									id={`${namespace}segmentsExperienceId`}
									items={segmentsExperiences}
									onSelectionChange={handleExperienceChange}
									selectedKey={selectedExperience}
									type="button"
								>
									{(experience) => (
										<Option
											key={experience.id}
											textValue={experience.name}
										>
											<Layout.ContentRow>
												<Layout.ContentCol
													className="pl-0"
													expand
												>
													<Text
														id={`${experience.id}-title`}
														size={3}
														weight="semi-bold"
													>
														{experience.name}
													</Text>

													<Text
														aria-hidden
														color="secondary"
														id={`${experience.id}-description`}
														size={3}
													>
														{`${Liferay.Language.get(
															'segment'
														)}:
														${experience.segmentsEntryName}`}
													</Text>
												</Layout.ContentCol>

												<Layout.ContentCol className="pr-0">
													<Label
														aria-hidden
														className="mr-0"
														displayType={
															experience.active
																? 'success'
																: 'secondary'
														}
														id={`${experience.id}-status`}
													>
														{experience.statusLabel}
													</Label>
												</Layout.ContentCol>
											</Layout.ContentRow>
										</Option>
									)}
								</Picker>
							</div>
						)}
					</ul>
				</form>
			)}
		</>
	);
}

export default SegmentsAndExperiencesSelector;
