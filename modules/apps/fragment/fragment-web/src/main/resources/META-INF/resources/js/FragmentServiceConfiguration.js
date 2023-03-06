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
import ClayButton from '@clayui/button';
import {ClayCheckbox} from '@clayui/form';
import ClayModal, {useModal} from '@clayui/modal';
import React, {useState} from 'react';

export default function FragmentServiceConfiguration({
	isFragmentServiceConfigurationDefined,
	isPropagateChanges,
	isPropagateContributedFragmentChanges,
	namespace,
}) {
	const [propagateChanges, setPropagateChanges] = useState(
		isPropagateChanges
	);
	const [
		propagateContributedFragmentChanges,
		setPropagateContributedFragmentChanges,
	] = useState(isPropagateContributedFragmentChanges);

	const [warningModalVisible, setWarningModalVisible] = useState(false);

	const {observer, onClose} = useModal({
		onClose: () => setWarningModalVisible(false),
	});

	return (
		<>
			{!isFragmentServiceConfigurationDefined && (
				<ClayAlert
					title={Liferay.Language.get(
						'this-configuration-is-not-saved-yet.-the-values-shown-are-the-default'
					)}
				/>
			)}

			<div className="sheet-subtitle">
				{Liferay.Language.get('default-fragments')}
			</div>

			<p className="text-secondary">
				{Liferay.Language.get(
					'default-fragments-are-provided-by-liferay-and-they-are-part-of-the-product-code.-here-you-can-define-their-behavior'
				)}
			</p>

			<ClayCheckbox
				checked={propagateContributedFragmentChanges}
				label={Liferay.Language.get(
					'propagate-contributed-fragment-changes-automatically'
				)}
				name={`${namespace}propagateContributedFragmentChanges`}
				onChange={({target: {checked}}) => {
					const propagateContributedFragmentChangesContainer = document.getElementById(
						`${namespace}propagateContributedFragmentChangesContainer`
					);

					propagateContributedFragmentChangesContainer.classList.toggle(
						'hide'
					);

					setPropagateContributedFragmentChanges(checked);
				}}
			/>

			<div aria-hidden="true" className="form-feedback-group mb-3">
				<div className="form-text text-weight-normal">
					{Liferay.Language.get(
						'propagate-contributed-fragment-changes-automatically-description'
					)}
				</div>
			</div>

			<div
				className={`${
					isPropagateContributedFragmentChanges ? 'hide' : ''
				}`}
				id={`${namespace}propagateContributedFragmentChangesContainer`}
			>
				<ClayButton
					displayType="secondary"
					onClick={() => setWarningModalVisible(true)}
				>
					{Liferay.Language.get('propagate-changes')}
				</ClayButton>
			</div>

			<div className="mt-3 sheet-subtitle">
				{Liferay.Language.get('custom-fragments')}
			</div>

			<p className="text-secondary">
				{Liferay.Language.get(
					'custom-fragments-are-those-that-are-created-by-the-user.-here-you-can-define-their-behavior'
				)}
			</p>

			<ClayCheckbox
				checked={propagateChanges}
				id={`${namespace}propagateChanges`}
				label={Liferay.Language.get(
					'propagate-fragment-changes-automatically'
				)}
				name={`${namespace}propagateChanges`}
				onChange={({target: {checked}}) => setPropagateChanges(checked)}
			/>

			<div aria-hidden="true" className="form-feedback-group">
				<div className="form-text text-weight-normal">
					{Liferay.Language.get(
						'propagate-fragment-changes-automatically-description'
					)}
				</div>
			</div>

			{warningModalVisible && (
				<ClayModal
					observer={observer}
					role="alertdialog"
					size="md"
					status="warning"
				>
					<ClayModal.Header>
						{Liferay.Language.get('propagate-changes')}
					</ClayModal.Header>

					<ClayModal.Body>
						<p>
							{Liferay.Language.get(
								'please-be-aware-that-if-any-content-creator-is-editing-a-page,-some-changes-may-not-be-saved.-performance-issues-can-also-result-from-this-action'
							)}
						</p>

						<p>
							{Liferay.Language.get(
								'are-you-sure-you-want-to-continue'
							)}
						</p>
					</ClayModal.Body>

					<ClayModal.Footer
						last={
							<ClayButton.Group spaced>
								<ClayButton
									displayType="secondary"
									onClick={onClose}
								>
									{Liferay.Language.get('cancel')}
								</ClayButton>

								<ClayButton displayType="primary" type="submit">
									{Liferay.Language.get('ok')}
								</ClayButton>
							</ClayButton.Group>
						}
					/>
				</ClayModal>
			)}
		</>
	);
}
