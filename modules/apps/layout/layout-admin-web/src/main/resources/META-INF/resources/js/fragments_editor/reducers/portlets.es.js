import {ADD_PORTLET} from '../actions/actions.es';
import {add, setIn, updateIn, updateLayoutData} from '../utils/FragmentsEditorUpdateUtils.es';
import {DROP_TARGET_ITEM_TYPES} from './placeholders.es';
import {getColumn, getWidget} from '../utils/FragmentsEditorGetUtils.es';

/**
 * @param {!object} state
 * @param {!string} actionType
 * @param {!object} payload
 * @param {!boolean} payload.instanceable
 * @param {!string} payload.portletId
 * @return {object}
 * @review
 */
function addPortletReducer(state, actionType, payload) {
	return new Promise(
		resolve => {
			let nextState = state;

			if (actionType === ADD_PORTLET) {
				let fragmentEntryLink = null;
				let nextData = null;

				_addPortlet(
					state.addPortletURL,
					payload.portletId,
					state.classNameId,
					state.classPK,
					state.portletNamespace
				)
					.then(
						response => {
							fragmentEntryLink = response;

							nextData = _addPortletElement(
								fragmentEntryLink.fragmentEntryLinkId,
								state.dropTargetBorder,
								state.dropTargetItemId,
								state.dropTargetItemType,
								state.layoutData
							);

							return updateLayoutData(
								state.updateLayoutPageTemplateDataURL,
								state.portletNamespace,
								state.classNameId,
								state.classPK,
								nextData
							);
						}
					)
					.then(
						() => _getFragmentEntryLinkContent(
							state.renderFragmentEntryURL,
							fragmentEntryLink,
							state.portletNamespace
						)
					)
					.then(
						response => {
							fragmentEntryLink = response;

							fragmentEntryLink.portletId = payload.portletId;

							nextState = setIn(
								nextState,
								[
									'fragmentEntryLinks',
									fragmentEntryLink.fragmentEntryLinkId
								],
								fragmentEntryLink
							);

							if (!payload.instanceable) {
								const widget = getWidget(state.widgets, payload.portletId);

								widget.portletObject.used = true;

								nextState = setIn(
									nextState,
									widget.path,
									widget.portletObject
								);
							}

							nextState = setIn(
								nextState,
								['layoutData'],
								nextData
							);

							resolve(nextState);
						}
					)
					.catch(
						() => {
							resolve(nextState);
						}
					);
			}
			else {
				resolve(nextState);
			}
		}
	);
}

/**
 * @param {string} addPortletURL
 * @param {string} portletId
 * @param {string} classNameId
 * @param {string} classPK
 * @param {string} portletNamespace
 * @return {object}
 * @review
 */
function _addPortlet(
	addPortletURL,
	portletId,
	classNameId,
	classPK,
	portletNamespace
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}portletId`, portletId);
	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);

	return fetch(
		addPortletURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	)
		.then(
			response => response.json()
		)
		.then(
			response => {
				if (!response.fragmentEntryLinkId) {
					throw new Error();
				}

				return {
					config: {},
					content: response.content,
					editableValues: JSON.parse(response.editableValues),
					fragmentEntryLinkId: response.fragmentEntryLinkId,
					name: response.name
				};
			}
		);
}

/**
 * Adds a portlet fragment at the corresponding container in the layout
 * @param {string} fragmentEntryLinkId
 * @param {string} dropTargetBorder
 * @param {string} dropTargetItemId
 * @param {string} dropTargetItemType
 * @param {object} layoutData
 * @private
 * @return {object}
 * @review
 */
function _addPortletElement(
	fragmentEntryLinkId,
	dropTargetBorder,
	dropTargetItemId,
	dropTargetItemType,
	layoutData
) {
	let nextData = layoutData;

	if (dropTargetItemType === DROP_TARGET_ITEM_TYPES.column) {
		const fragmentColumn = getColumn(
			layoutData.structure,
			dropTargetItemId
		);

		nextData = _addPortletToColumn(
			layoutData,
			fragmentEntryLinkId,
			dropTargetItemId,
			fragmentColumn.fragmentEntryLinkIds.length
		);
	}

	return nextData;
}

/**
 * Returns a new layoutData with the given portlet element inserted
 * into a given column at the given position
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @param {string} targetColumnId
 * @param {number} position
 * @return {object}
 */
function _addPortletToColumn(
	layoutData,
	fragmentEntryLinkId,
	targetColumnId,
	position
) {
	const {structure} = layoutData;

	const column = getColumn(structure, targetColumnId);
	const section = structure.find(
		_section => _section.columns.find(
			_column => column === _column
		)
	);

	const columnIndex = section.columns.indexOf(column);
	const sectionIndex = structure.indexOf(section);

	return updateIn(
		layoutData,
		[
			'structure',
			sectionIndex,
			'columns',
			columnIndex,
			'fragmentEntryLinkIds'
		],
		fragmentEntryLinkIds => add(
			fragmentEntryLinkIds,
			fragmentEntryLinkId,
			position
		)
	);
}

/**
 * @param {string} renderFragmentEntryURL
 * @param {{fragmentEntryLinkId: string}} fragmentEntryLink
 * @param {string} portletNamespace
 * @return {Promise<object>}
 * @review
 */
function _getFragmentEntryLinkContent(
	renderFragmentEntryURL,
	fragmentEntryLink,
	portletNamespace
) {
	const formData = new FormData();

	formData.append(
		`${portletNamespace}fragmentEntryLinkId`,
		fragmentEntryLink.fragmentEntryLinkId
	);

	return fetch(
		renderFragmentEntryURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	)
		.then(
			response => response.json()
		)
		.then(
			response => {
				if (!response.content) {
					throw new Error();
				}

				return Object.assign(
					{},
					fragmentEntryLink,
					{
						content: response.content
					}
				);
			}
		);
}

export {addPortletReducer};