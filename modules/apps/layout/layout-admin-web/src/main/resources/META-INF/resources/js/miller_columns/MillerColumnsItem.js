/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayDropDown, {ClayDropDownWithItems} from '@clayui/drop-down';
import {ClayCheckbox} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import ClayLayout from '@clayui/layout';
import ClayLink from '@clayui/link';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import classNames from 'classnames';
import {fetch, sub} from 'frontend-js-web';
import React, {useEffect, useMemo, useRef, useState} from 'react';
import {useDrag, useDrop} from 'react-dnd';
import {getEmptyImage} from 'react-dnd-html5-backend';

import ACTIONS from '../actions';
import {ACCEPTING_TYPES, ITEM_HOVER_BORDER_LIMIT} from './constants';

const mockedData = [
	{
		items: [
			{
				
				href:
					'http://localhost:8080/53c13dd5-335f-b47b-d66c-65268d093029?p_l_back_url=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_auth%3DF3zhko2Z&p_l_mode=edit',
				icon: 'pencil',
				label: 'Edit',
				type: 'item',
			},
			{
				href:
					'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_translation_web_internal_portlet_TranslationPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_translation_web_internal_portlet_TranslationPortlet_mvcRenderCommandName=%2Ftranslation%2Ftranslate&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classNameId=20031&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classPK=7&_com_liferay_translation_web_internal_portlet_TranslationPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_v_l_s_g_id%3D20118%26p_r_p_selPlid%3D0%26p_p_auth%3DtQPwpzUi&_com_liferay_translation_web_internal_portlet_TranslationPortlet_portletResource=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&_com_liferay_translation_web_internal_portlet_TranslationPortlet_segmentsExperienceId=33597&p_p_auth=ukWu90NG',
				icon: 'automatic-translate',
				label: 'Translate',
				type: 'item',
			},
			{
				href:
					'http://localhost:8080/home?p_l_back_url=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_auth%3DF3zhko2Z',
				icon: 'view',
				label: 'View',
				type: 'item',
			},
		],
		separator: true,
		type: 'group',
	},
	{
		items: [
			{
				href:
					'http://localhost:8080/53c13dd5-335f-b47b-d66c-65268d093029',
				label: 'Preview Draft',
				symbolRight: 'shortcut',
				target: '_blank',
				type: 'item',
			},
			{
				data: {
					action: 'discardDraft',
					discardDraftURL:
						'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=1&p_p_state=maximized&p_p_mode=view&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_javax.portlet.action=%2Flayout_admin%2Fdiscard_draft_layout&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_v_l_s_g_id%3D20118%26p_r_p_selPlid%3D0%26p_p_auth%3DtQPwpzUi&p_auth=OlqDoZfm&p_p_auth=F3zhko2Z&p_r_p_selPlid=7',
				},
				label: 'Discard Draft',
				type: 'item',
			},
			{
				href:
					'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&p_r_p_selPlid=6&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_mvcPath=%2Forphan_portlets.jsp&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_backURL=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_auth%3DF3zhko2Z&p_p_auth=F3zhko2Z',
				label: 'Orphan Widgets',
				type: 'item',
			},
		],
		separator: true,
		type: 'group',
	},
	{
		items: [
			{
				icon: 'copy',
				items: [
					{
						data: {
							action: 'copyLayout',
							copyLayoutURL:
								'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&p_r_p_selPlid=0&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_mvcRenderCommandName=%2Flayout_admin%2Fadd_layout&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_copyPermissions=false&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_privateLayout=false&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_sourcePlid=6&p_p_auth=F3zhko2Z',
						},
						label: 'Page',
						type: 'item',
					},
					{
						data: {
							action: 'copyLayoutWithPermissions',
							copyLayoutURL:
								'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&p_r_p_selPlid=0&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_mvcRenderCommandName=%2Flayout_admin%2Fadd_layout&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_copyPermissions=true&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_privateLayout=false&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_sourcePlid=6&p_p_auth=F3zhko2Z',
						},
						label: 'Page With Permissions',
						type: 'item',
					},
				],
				label: 'Copy',
				type: 'contextual',
			},
			{
				href:
					'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_translation_web_internal_portlet_TranslationPortlet&p_p_lifecycle=0&_com_liferay_translation_web_internal_portlet_TranslationPortlet_mvcRenderCommandName=%2Ftranslation%2Fexport_translation&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classNameId=20031&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classPK=7&_com_liferay_translation_web_internal_portlet_TranslationPortlet_groupId=20118&_com_liferay_translation_web_internal_portlet_TranslationPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_v_l_s_g_id%3D20118%26p_r_p_selPlid%3D0%26p_p_auth%3DtQPwpzUi&_com_liferay_translation_web_internal_portlet_TranslationPortlet_portletResource=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_auth=ukWu90NG',
				icon: 'upload',
				label: 'Export for Translation',
				type: 'item',
			},
			{
				href:
					'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_translation_web_internal_portlet_TranslationPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_translation_web_internal_portlet_TranslationPortlet_mvcRenderCommandName=%2Ftranslation%2Fimport_translation&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classNameId=20031&_com_liferay_translation_web_internal_portlet_TranslationPortlet_classPK=7&_com_liferay_translation_web_internal_portlet_TranslationPortlet_groupId=20118&_com_liferay_translation_web_internal_portlet_TranslationPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_v_l_s_g_id%3D20118%26p_r_p_selPlid%3D0%26p_p_auth%3DtQPwpzUi&_com_liferay_translation_web_internal_portlet_TranslationPortlet_portletResource=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_auth=ukWu90NG',
				icon: 'download',
				label: 'Import Translation',
				type: 'item',
			},
		],
		separator: true,
		type: 'group',
	},
	{
		items: [
			{
				href:
					'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&p_r_p_selPlid=6&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_mvcRenderCommandName=%2Flayout_admin%2Fedit_layout&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_v_l_s_g_id%3D20118%26p_r_p_selPlid%3D0%26p_p_auth%3DtQPwpzUi&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_backURL=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_auth%3DF3zhko2Z&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_portletResource=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_groupId=20118&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_privateLayout=false&p_p_auth=F3zhko2Z',
				icon: 'cog',
				label: 'Configure',
				type: 'item',
			},
			{
				data: {
					action: 'permissionLayout',
					permissionLayoutURL:
						'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet&p_p_lifecycle=0&p_p_state=pop_up&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_mvcPath=%2Fedit_permissions.jsp&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_portletConfiguration=true&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_portletResource=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_modelResource=com.liferay.portal.kernel.model.Layout&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_modelResourceDescription=Home&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_resourceGroupId=20118&_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_resourcePrimKey=6',
				},
				icon: 'password-policies',
				label: 'Permissions',
				type: 'item',
			},
		],
		separator: true,
		type: 'group',
	},
	{
		items: [
			{
				data: {
					action: 'deleteLayout',
					deleteLayoutURL:
						'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=1&p_p_state=maximized&p_p_mode=view&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_javax.portlet.action=%2Flayout_admin%2Fdelete_layout&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_redirect=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_layout_admin_web_portlet_GroupPagesPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26p_r_p_selPlid%3D0%26p_r_p_layoutSetBranchId%3D0%26p_p_auth%3DF3zhko2Z&p_auth=OlqDoZfm&p_p_auth=F3zhko2Z&p_r_p_selPlid=6&p_r_p_layoutSetBranchId=0',
					message:
					'Are you sure you want to delete the page "Home"? It will be removed immediately.',
				},
				icon: 'trash',
				label: 'Delete',
				type: 'item',
			},
		],
		separator: true,
		type: 'group',
	},
];

const DROP_ZONES = {
	BOTTOM: 'BOTTOM',
	ELEMENT: 'ELEMENT',
	TOP: 'TOP',
};

const ITEM_HOVER_TIMEOUT = 500;

const ITEM_STATES_COLORS = {
	'conversion-draft': 'info',
	'draft': 'secondary',
	'pending': 'info',
};

const isValidTarget = (sources, target, dropZone, isPrivateLayoutsEnabled) => {
	if (sources.some((item) => item.id === target.id)) {
		return false;
	}

	if (
		sources.some(
			(source) =>
				!(
					(((isPrivateLayoutsEnabled && target.parentId) ||
						!isPrivateLayoutsEnabled) &&
						target.columnIndex <= source.columnIndex) ||
					(target.columnIndex > source.columnIndex && !source.active)
				)
		)
	) {
		return false;
	}

	if (dropZone === DROP_ZONES.TOP) {
		return !sources.some(
			(source) =>
				!(
					target.columnIndex !== source.columnIndex ||
					target.itemIndex < source.itemIndex ||
					target.itemIndex > source.itemIndex + 1
				)
		);
	}
	else if (dropZone === DROP_ZONES.BOTTOM) {
		return !sources.some(
			(source) =>
				!(
					target.columnIndex !== source.columnIndex ||
					target.itemIndex > source.itemIndex ||
					target.itemIndex < source.itemIndex - 1
				)
		);
	}
	else if (dropZone === DROP_ZONES.ELEMENT) {
		return !sources.some(
			(source) => !(target.id !== source.parentId && target.parentable)
		);
	}
};

const getDropZone = (ref, monitor) => {
	if (!ref.current) {
		return;
	}

	const clientOffset = monitor.getClientOffset();
	const dropItemBoundingRect = ref.current.getBoundingClientRect();
	const hoverTopLimit = ITEM_HOVER_BORDER_LIMIT;
	const hoverBottomLimit =
		dropItemBoundingRect.height - ITEM_HOVER_BORDER_LIMIT;
	const hoverClientY = clientOffset.y - dropItemBoundingRect.top;

	let dropZone = DROP_ZONES.ELEMENT;

	if (hoverClientY < hoverTopLimit) {
		dropZone = DROP_ZONES.TOP;
	}
	else if (hoverClientY > hoverBottomLimit) {
		dropZone = DROP_ZONES.BOTTOM;
	}

	return dropZone;
};

const getItemIndex = (item = {}, items) => {
	const siblings = Array.from(items.values()).filter(
		(_item) => _item.columnIndex === item.columnIndex
	);

	return siblings.indexOf(item);
};

function addSeparators(items) {
	if (items.length < 2) {
		return items;
	}

	const separatedItems = [items[0]];

	for (let i = 1; i < items.length; i++) {
		const item = items[i];

		if (item.type === 'group' && item.separator) {
			separatedItems.push({type: 'divider'});
		}

		separatedItems.push(item);
	}

	return separatedItems.map((item) => {
		if (item.type === 'group') {
			return {
				...item,
				items: addSeparators(item.items),
			};
		}

		return item;
	});
}

function filterEmptyGroups(items) {
	return items
		.filter(
			(item) =>
				item.type !== 'group' ||
				(Array.isArray(item.items) && item.items.length)
		)
		.map((item) =>
			item.type === 'group'
				? {...item, items: filterEmptyGroups(item.items)}
				: item
		);
}

const noop = () => {};

const defaultDropdownActions = [
	{
		'aria-label': Liferay.Language.get('loading'),
		'aria-valuemax': 100,
		'aria-valuemin': 0,
		'label': <ClayLoadingIndicator />,
		'roleItem': 'progressbar',
	},
];

const MillerColumnsItem = ({
	getItemActionsURL,
	isLayoutSetPrototype,
	isPrivateLayoutsEnabled,
	item: {
		active,
		bulkActions = [],
		checked,
		columnIndex,
		description,
		draggable,
		hasChild,
		hasDuplicatedFriendlyURL = false,
		id: itemId,
		itemIndex,
		parentId,
		parentable,
		quickActions = [],
		selectable,
		states = [],
		target,
		title,
		url,
		viewUrl,
	},
	items,
	namespace,
	onDragEnd,
	onItemDrop = noop,
	onItemStayHover = noop,
	rtl,
}) => {
	const ref = useRef();
	const timeoutRef = useRef();

	const [dropZone, setDropZone] = useState();

	const [layoutActionsActive, setLayoutActionsActive] = useState(false);

	const [dropdownActions, setDropdownActions] = useState(
		defaultDropdownActions
	);

	const loadPromiseRef = useRef();

	function loadDropdownActions() {
		if (!loadPromiseRef.current) {
			const url = new URL(getItemActionsURL);
			url.searchParams.append('plid', itemId);

			loadPromiseRef.current = fetch(url, {
				method: 'GET',
			})
				.then((response) => {
					response.json();
				})
				.then((responseItems) => {
					const updateItem = (item) => {
						const newItem = {
							...item,
							onClick(event) {
								const action = item.data?.action;

								if (action) {
									event.preventDefault();

									ACTIONS[action]?.(item.data);
								}
							},
							symbolLeft: item.icon,
						};

						if (Array.isArray(item.items)) {
							newItem.items = item.items.map(updateItem);
						}

						return newItem;
					};

					// const dropdownActions = responseItems.map((action) => {

					const dropdownActions = mockedData.map((action) => {
						return {
							...action,
							items: action.items?.map(updateItem),
						};
					});

					setDropdownActions(
						addSeparators(filterEmptyGroups(dropdownActions))
					);
				});
		}
	}

	const layoutActions = useMemo(() => {
		return quickActions.filter(
			(action) => action.layoutAction && action.url
		);
	}, [quickActions]);

	const normalizedQuickActions = useMemo(() => {
		return quickActions.filter(
			(action) => action.quickAction && action.url
		);
	}, [quickActions]);

	const [{isDragging}, drag, previewRef] = useDrag({
		collect: (monitor) => ({
			isDragging: !!monitor.isDragging(),
		}),
		end: onDragEnd,
		isDragging: (monitor) => {
			const movedItems = monitor.getItem().items;

			return (
				(movedItems.some((item) => item.checked) && checked) ||
				movedItems.some((item) => item.id === itemId)
			);
		},
		item: {
			items: checked
				? Array.from(items.values())
						.filter((item) => item.checked)
						.map((item) => ({
							...item,
							itemIndex: getItemIndex(item, items),
						}))
				: [
						{
							...items.get(itemId),
							itemIndex: getItemIndex(items.get(itemId), items),
						},
				  ],
			type: ACCEPTING_TYPES.ITEM,
		},
	});

	const [{isOver}, drop] = useDrop({
		accept: ACCEPTING_TYPES.ITEM,
		canDrop(source, monitor) {
			const dropZone = getDropZone(ref, monitor);

			return isValidTarget(
				source.items,
				{columnIndex, id: itemId, itemIndex, parentId, parentable},
				dropZone,
				isPrivateLayoutsEnabled
			);
		},
		collect: (monitor) => ({
			isOver: !!monitor.isOver(),
		}),
		drop(source, monitor) {
			if (monitor.canDrop()) {
				if (dropZone === DROP_ZONES.ELEMENT) {
					const newIndex = Array.from(items.values()).filter(
						(item) => item.parentId === itemId
					).length;

					onItemDrop(source.items, itemId, newIndex);
				}
				else {
					let newIndex = itemIndex;

					if (dropZone === DROP_ZONES.BOTTOM) {
						newIndex = itemIndex + 1;
					}

					onItemDrop(source.items, parentId, newIndex);
				}
			}
		},
		hover(source, monitor) {
			let dropZone;

			if (isOver && monitor.canDrop()) {
				dropZone = getDropZone(ref, monitor);
			}

			setDropZone(dropZone);
		},
	});

	useEffect(() => {
		drag(drop(ref));
	}, [drag, drop]);

	useEffect(() => {
		previewRef(getEmptyImage(), {captureDraggingState: true});
	}, [previewRef]);

	useEffect(() => {
		if (!active && dropZone === DROP_ZONES.ELEMENT && !timeoutRef.current) {
			timeoutRef.current = setTimeout(() => {
				if (isOver) {
					onItemStayHover(itemId);
				}
			}, ITEM_HOVER_TIMEOUT);
		}
		else if (
			!isOver ||
			(dropZone !== DROP_ZONES.ELEMENT && timeoutRef.current)
		) {
			clearTimeout(timeoutRef.current);
			timeoutRef.current = null;
		}
	}, [active, dropZone, isOver, itemId, onItemStayHover]);

	const warningMessage = isLayoutSetPrototype
		? Liferay.Language.get(
				'there-is-a-page-with-the-same-friendly-url-in-a-site-using-this-site-template'
		  )
		: Liferay.Language.get(
				'there-is-a-page-with-the-same-friendly-url-in-the-site-template'
		  );

	return (
		<ClayLayout.ContentRow
			className={classNames('list-group-item-flex miller-columns-item', {
				'dragging': isDragging,
				'drop-bottom': isOver && dropZone === DROP_ZONES.BOTTOM,
				'drop-element': isOver && dropZone === DROP_ZONES.ELEMENT,
				'drop-top': isOver && dropZone === DROP_ZONES.TOP,
				'miller-columns-item--active': active,
			})}
			containerElement="li"
			data-actions={bulkActions}
			ref={ref}
			verticalAlign="center"
		>
			<a className="miller-columns-item-mask" href={url} role="button">
				<span className="c-inner sr-only">{title}</span>
			</a>

			{draggable && (
				<ClayLayout.ContentCol className="c-pl-0 miller-columns-item-drag-handler">
					<ClayIcon symbol="drag" />
				</ClayLayout.ContentCol>
			)}

			{selectable && (
				<ClayLayout.ContentCol>
					<ClayCheckbox
						aria-label={sub(
							Liferay.Language.get('select-x'),
							title
						)}
						className="c-mb-0"
						defaultChecked={checked}
						name={`${namespace}rowIds`}
						value={itemId}
					/>
				</ClayLayout.ContentCol>
			)}

			<ClayLayout.ContentCol className="c-pl-1" expand>
				<div className="list-group-title text-truncate-inline">
					{viewUrl ? (
						<ClayLink
							aria-label={
								Liferay.FeatureFlags['LPS-174417'] &&
								hasDuplicatedFriendlyURL
									? `${title}. ${warningMessage}`
									: title
							}
							className="text-truncate"
							href={viewUrl}
							target={target}
						>
							{title}
						</ClayLink>
					) : (
						<span className="text-truncate">{title}</span>
					)}

					{Liferay.FeatureFlags['LPS-174417'] &&
					hasDuplicatedFriendlyURL ? (
						<ClayIcon
							className="align-self-center c-ml-2 flex-shrink-0 icon-warning lfr-portal-tooltip text-warning"
							data-title={warningMessage}
							symbol="warning-full"
						/>
					) : null}
				</div>

				{description && (
					<div className="d-flex h5 list-group-subtitle small">
						<span className="text-truncate">{description}</span>

						{states.map((state) => (
							<ClayLabel
								className="inline-item-after text-truncate"
								displayType={ITEM_STATES_COLORS[state.id]}
								key={state.id}
							>
								{state.label}
							</ClayLabel>
						))}
					</div>
				)}
			</ClayLayout.ContentCol>

			{!!layoutActions.length && (
				<ClayLayout.ContentCol className="miller-columns-item-actions">
					<ClayDropDown
						active={layoutActionsActive}
						onActiveChange={setLayoutActionsActive}
						renderMenuOnClick
						trigger={
							<ClayButtonWithIcon
								borderless
								displayType="secondary"
								size="sm"
								symbol="plus"
								title={Liferay.Language.get('add-child-page')}
							/>
						}
					>
						<ClayDropDown.ItemList>
							{layoutActions.map((action) => (
								<ClayDropDown.Item
									disabled={!action.url}
									href={action.url}
									id={action.id}
									key={action.id}
									onClick={action.handler}
								>
									{action.label}
								</ClayDropDown.Item>
							))}
						</ClayDropDown.ItemList>
					</ClayDropDown>
				</ClayLayout.ContentCol>
			)}

			{normalizedQuickActions.map((action) => (
				<ClayLayout.ContentCol
					className="miller-columns-item-quick-action"
					key={action.id}
				>
					<ClayLink
						borderless
						displayType="secondary"
						href={action.url}
						monospaced
						outline
					>
						<ClayIcon symbol={action.icon} />
					</ClayLink>
				</ClayLayout.ContentCol>
			))}

			{!!getItemActionsURL && (
				<ClayLayout.ContentCol className="miller-columns-item-actions">
					<ClayDropDownWithItems
						items={dropdownActions}
						trigger={
							<ClayButtonWithIcon
								borderless
								displayType="secondary"
								onClick={loadDropdownActions}
								size="sm"
								symbol="ellipsis-v"
								title={Liferay.Language.get(
									'open-page-options-menu'
								)}
							/>
						}
					/>
				</ClayLayout.ContentCol>
			)}

			{hasChild && (
				<ClayLayout.ContentCol className="miller-columns-item-child-indicator text-secondary">
					<ClayIcon symbol={rtl ? 'caret-left' : 'caret-right'} />
				</ClayLayout.ContentCol>
			)}
		</ClayLayout.ContentRow>
	);
};

export default MillerColumnsItem;
