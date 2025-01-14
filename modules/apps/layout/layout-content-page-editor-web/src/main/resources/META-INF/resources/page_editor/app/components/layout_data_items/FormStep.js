/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';
import React, {useCallback, useMemo} from 'react';

import useSetRef from '../../../common/hooks/useSetRef';
import {getLayoutDataItemPropTypes} from '../../../prop_types/index';
import {config} from '../../config';
import {useSelectItem} from '../../contexts/ControlsContext';
import {useActiveStep} from '../../contexts/FormStepContext';
import {useItemLocalConfig} from '../../contexts/LocalConfigContext';
import {
	useDispatch,
	useSelector,
	useSelectorCallback,
} from '../../contexts/StoreContext';
import removeFormStep from '../../thunks/removeFormStep';
import {getFormParent} from '../../utils/getFormParent';
import getLayoutDataItemTopperUniqueClassName from '../../utils/getLayoutDataItemTopperUniqueClassName';
import getLayoutDataItemUniqueClassName from '../../utils/getLayoutDataItemUniqueClassName';
import isItemEmpty from '../../utils/isItemEmpty';
import {openConfirmModal} from '../../utils/openConfirmModal';
import {updateStepperConfiguration} from '../../utils/updateStepperConfiguration';
import TopperEmpty from '../topper/TopperEmpty';
import getParentHeight from './getParentHeight';

const FormStepWithControls = React.forwardRef(({children, item}, ref) => {
	const isEmpty = useSelectorCallback(
		(state) =>
			isItemEmpty(item, state.layoutData, state.selectedViewportSize),
		[item]
	);

	const index = useSelectorCallback(
		(state) => {
			return state.layoutData.items[item.parentId]?.children.indexOf(
				item.itemId
			);
		},
		[item]
	);

	const layoutData = useSelector((state) => state.layoutData);

	const form = useMemo(
		() => getFormParent(item, layoutData),
		[item, layoutData]
	);

	const localConfig = useItemLocalConfig(form.itemId);

	const activeStep = useActiveStep();

	const visible = index === activeStep;

	const [setRef, itemElement] = useSetRef(ref);

	const fragmentEntryLinks = useSelector((state) => state.fragmentEntryLinks);

	const dispatch = useDispatch();

	const selectItem = useSelectItem();

	const removeStep = useCallback(() => {
		const numberOfSteps = form.config.numberOfSteps;

		const executeAction = () => {
			dispatch(
				removeFormStep({
					index,
					itemId: item.itemId,
					selectItem,
				})
			).then(() =>
				updateStepperConfiguration({
					dispatch,
					formId: form.itemId,
					fragmentEntryLinks,
					layoutData,
					numberOfSteps: numberOfSteps - 1,
				})
			);
		};

		if (numberOfSteps === 2) {
			openConfirmModal({
				buttonLabel: Liferay.Language.get('remove-and-convert'),
				onConfirm: executeAction,
				status: 'info',
				text: Liferay.Language.get(
					'removing-this-step-will-convert-your-multistep-form-into-a-simple-form'
				),
				title: Liferay.Language.get(
					'remove-step-and-convert-to-simple-form'
				),
			});
		}
		else {
			executeAction();
		}

		const formElement = document.querySelector(
			`.${getLayoutDataItemUniqueClassName(form.itemId)}`
		);

		Liferay.fire('formFragment:changeStep', {
			emitter: formElement,
			step: index - 1,
		});
	}, [
		dispatch,
		form,
		fragmentEntryLinks,
		index,
		item.itemId,
		layoutData,
		selectItem,
	]);

	return (
		<TopperEmpty
			className={classNames(
				'page-editor__form-step-topper',
				getLayoutDataItemTopperUniqueClassName(item.itemId)
			)}
			item={item}
			itemElement={itemElement}
			options={
				Liferay.FeatureFlags['LPD-31772'] && index
					? [
							{
								label: Liferay.Language.get('remove-step'),
								onClick: removeStep,
								symbol: 'times-circle',
							},
						]
					: []
			}
		>
			<FormStep
				className={classNames('page-editor__form-step', {
					'd-none': !visible && !localConfig.displayAllSteps,
				})}
				ref={setRef}
			>
				{isEmpty && (
					<div
						className="d-flex flex-column page-editor__no-fragments-state"
						style={{height: getParentHeight(item, layoutData)}}
					>
						<img
							className="page-editor__no-fragments-state__image"
							src={`${config.imagesPath}/drag_and_drop.svg`}
						/>

						<p className="page-editor__no-fragments-state__message">
							{Liferay.Language.get(
								'drag-and-drop-fragments-or-widgets-here'
							)}
						</p>
					</div>
				)}

				{children}
			</FormStep>
		</TopperEmpty>
	);
});

FormStepWithControls.displayName = 'FormStepWithControls';

FormStepWithControls.propTypes = {
	item: getLayoutDataItemPropTypes().isRequired,
};

const FormStep = React.forwardRef(
	({children, className, ...otherProps}, ref) => {
		return (
			<div className={className} ref={ref} {...otherProps}>
				{children}
			</div>
		);
	}
);

FormStep.displayName = 'FormStep';

FormStep.propTypes = {
	item: getLayoutDataItemPropTypes().isRequired,
};

export {FormStep, FormStepWithControls};
