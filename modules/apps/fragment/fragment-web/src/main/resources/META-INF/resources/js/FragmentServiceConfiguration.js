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
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import ClayModal, {useModal} from '@clayui/modal';
import {navigate} from 'frontend-js-web';
import React, {useCallback, useRef, useState} from 'react';

export default function FragmentServiceConfiguration({
	alreadyPropagateContributedFragmentChanges,
	editFragmentServiceConfigurationConfigurationURL,
	isFragmentServiceConfigurationDefined,
	isPropagateChanges,
	isPropagateContributedFragmentChanges,
	namespace,
	propagateContributedFragmentEntryChangesURL,
	redirectURL,
}) {
	const formRef = useRef();
	const propagateContributedFragmentEntryChangesFormRef = useRef();

	const handleSubmit = () => {
		if (formRef.current) {
			formRef.current.submit();
		}
	};

	const [warningModalVisible, setWarningModalVisible] = useState(false);

	const {observer, onClose} = useModal({
		onClose: () => setWarningModalVisible(false),
	});

	const onCancel = useCallback(() => {
		if (redirectURL) {
			navigate(redirectURL);
		}
	}, [redirectURL]);

	return (
		<>
			<form
				action={editFragmentServiceConfigurationConfigurationURL}
				method="POST"
				useRef={formRef}
			>
				<ClayLayout.Sheet size="xl">
					<ClayLayout.SheetHeader>
						<h2>
							{Liferay.Language.get(
								'fragment-configuration-name'
							)}
						</h2>
					</ClayLayout.SheetHeader>

					<ClayLayout.SheetSection>
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
							defaultChecked={
								isPropagateContributedFragmentChanges
							}
							label={Liferay.Language.get(
								'propagate-contributed-fragment-changes-automatically'
							)}
							name={`${namespace}propagateContributedFragmentChanges`}
							onChange={() => {
								const propagateContributedFragmentChangesContainer = document.getElementById(
									`${namespace}propagateContributedFragmentChangesContainer`
								);

								propagateContributedFragmentChangesContainer.classList.toggle(
									'hide'
								);
							}}
						/>

						<div
							aria-hidden="true"
							className="form-feedback-group mb-3"
						>
							<div className="form-text text-weight-normal">
								{Liferay.Language.get(
									'propagate-contributed-fragment-changes-automatically-description'
								)}
							</div>
						</div>

						<div
							className={`${
								isPropagateContributedFragmentChanges
									? 'hide'
									: ''
							}`}
							id={`${namespace}propagateContributedFragmentChangesContainer`}
						>
							<ClayButton
								disabled={
									alreadyPropagateContributedFragmentChanges
								}
								displayType="secondary"
								onClick={() => setWarningModalVisible(true)}
							>
								{Liferay.Language.get('propagate-changes')}
							</ClayButton>

							{alreadyPropagateContributedFragmentChanges && (
								<div className="text-success">
									<ClayIcon
										className="text-success"
										symbol="check-circle-full"
									/>

									{Liferay.Language.get(
										'all-changes-are-propagated'
									)}
								</div>
							)}
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
							defaultChecked={isPropagateChanges}
							label={Liferay.Language.get(
								'propagate-fragment-changes-automatically'
							)}
							name={`${namespace}propagateChanges`}
						/>

						<div aria-hidden="true" className="form-feedback-group">
							<div className="form-text text-weight-normal">
								{Liferay.Language.get(
									'propagate-fragment-changes-automatically-description'
								)}
							</div>
						</div>
					</ClayLayout.SheetSection>

					<ClayLayout.SheetFooter>
						<div>
							{isFragmentServiceConfigurationDefined ? (
								<ClayButton displayType="primary" type="submit">
									{Liferay.Language.get('update')}
								</ClayButton>
							) : (
								<ClayButton displayType="primary" type="submit">
									{Liferay.Language.get('save')}
								</ClayButton>
							)}

							<ClayButton
								displayType="secondary"
								onClick={onCancel}
								type="cancel"
							>
								{Liferay.Language.get('cancel')}
							</ClayButton>
						</div>
					</ClayLayout.SheetFooter>
				</ClayLayout.Sheet>
			</form>

			<form
				action={propagateContributedFragmentEntryChangesURL}
				method="POST"
				ref={propagateContributedFragmentEntryChangesFormRef}
			></form>

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

								<ClayButton
									displayType="warning"
									onClick={handleSubmit}
									type="submit"
								>
									{Liferay.Language.get('continue')}
								</ClayButton>
							</ClayButton.Group>
						}
					/>
				</ClayModal>
			)}
		</>
	);
}
