import dom from 'metal-dom';

import SimpleInputModal from './SimpleInputModal.es';

/**
 * Function that implements the SimpleInputModal pattern, which allows
 * manipulating small amounts of data with a form shown inside a modal.
 *
 * As this behaviour is thinked to be bound to individual items, every component
 * attribute is obtained from the clicked element dataset (eg. dialogTitle
 * property is obtained from data-simple-input-modal-dialog-title property).
 *
 * @param {!string} elementSelector Selector used for listening to click events
 * @param {object} eventHandlers Optional event object passed to SimpleInputModal
 *
 * @see SimpleInputModal
 *
 * @description
 *
 * Execution flow
 * 	1. When the function is called, a delegated click event
 * 	   is bound to the specified elementSelector, and
 * 	   a SimpleInputModal component is created when fired.
 * 	2. When the form is submitted, a POST request is sent to
 * 	   the server and a formSubmit event is dispatched.
 * 	3. SimpleInputModal processes the response.
 * 	   3.1. If the response is successful, a formSuccess
 * 	        event is dispatched. Then, if there is a
 * 	        redirectURL, it performs a redirection.
 * 	        Finally the SimpleInputModal is disposed and
 * 	        every event listeners are detached.
 * 	   3.2. If the response is an error, a formError event
 * 	   		is dispatched. Then the given errorMessage is shown inside
 * 	        the modal and nothing happens until the user
 * 	        fixes submits it again or the modal is closed.
 *
 * Optional fields
 * - Checkbox: SimpleInputModal supports an optional checkbox field that
 *   can be added to the form.
 * - Id field: in case of editing elements instead of creating new ones,
 *   and hidden id field can be used. There are also fieldValues that can
 *   be used for having an initial value instead of an empty field.
 * - Redirect url: the common behaviour is redirecting to an existing url
 *   when the form is submitted. This URL is obtained from the request response
 *   as "redirectURL" and, if no URL is obtained, the SimpleInputModal is simply
 *   disposed.
 */
const bindSimpleInputModal = (elementSelector, eventHandlers = {}) => {
	let delegatedHandler = null;
	let simpleInputModal = null;

	const handleDestroyPortlet = () => {
		Liferay.detach('destroyPortlet', handleDestroyPortlet);

		if (delegatedHandler) {
			delegatedHandler.removeListener();
			delegatedHandler = null;
		}

		if (simpleInputModal) {
			simpleInputModal.dispose();
			simpleInputModal = null;
		}
	};

	const handleElementClick = (event) => {
		event.preventDefault();

		const events = Object.assign({}, eventHandlers, {
			disposed: () => {
				simpleInputModal = null;

				if (eventHandlers.disposed) {
					eventHandlers.disposed();
				}
			},
		});

		const data = event.delegateTarget.dataset;

		const dialogTitle = data.simpleInputModalDialogTitle;
		const formSubmitURL = data.simpleInputModalFormSubmitUrl;
		const mainFieldLabel = data.simpleInputModalMainFieldLabel;
		const mainFieldName = data.simpleInputModalMainFieldName;
		const namespace = data.simpleInputModalNamespace;
		const spritemap = data.simpleInputModalSpritemap;

		const checkboxFieldLabel = data.simpleInputModalCheckboxFieldLabel || '';
		const checkboxFieldName = data.simpleInputModalCheckboxFieldName || '';
		const checkboxFieldValue = data.simpleInputModalCheckboxFieldValue === 'true';
		const idFieldName = data.simpleInputModalIdFieldName || '';
		const idFieldValue = data.simpleInputModalIdFieldValue || '';
		const mainFieldPlaceholder = data.simpleInputModalMainFieldPlaceholder || '';
		const mainFieldValue = data.simpleInputModalMainFieldValue || '';

		simpleInputModal = simpleInputModal || new SimpleInputModal({
			checkboxFieldLabel,
			checkboxFieldName,
			checkboxFieldValue,
			dialogTitle,
			events,
			formSubmitURL,
			idFieldName,
			idFieldValue,
			mainFieldLabel,
			mainFieldName,
			mainFieldPlaceholder,
			mainFieldValue,
			namespace,
			spritemap,
		});
	};

	Liferay.on('destroyPortlet', handleDestroyPortlet);

	delegatedHandler = dom.delegate(
		document.body,
		'click',
		elementSelector,
		handleElementClick
	);
};

export { bindSimpleInputModal };
export default bindSimpleInputModal;