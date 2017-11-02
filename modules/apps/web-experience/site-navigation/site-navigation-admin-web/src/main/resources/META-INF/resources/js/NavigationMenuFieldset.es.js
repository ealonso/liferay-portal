import {Component} from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './NavigationMenuFieldset.soy';

/**
 * NavigationMenuFieldset
 */
class NavigationMenuFieldset extends Component {

	/**
	 * This is called to clear the fieldset.
	 *
	 * @private
	 */
	_clearFieldset() {
		const fields = this.fields;

		let fieldsetName = document.querySelector(
			`#${this.namespace}fieldsetName`);

		fieldsetName.value = '';

		let dataFields = [];

		fields.forEach(
			(field) => {
				let dataField = {
					description: field.description,
					label: field.label,
					name: field.name,
					value: ''
				};

				dataFields.push(dataField);

				let input = document.querySelector(
					`#${this.namespace}${field.name}`);

				input.value = '';
			}
		);

		this.emit(
			'contextChanged',
			{
				fields: dataFields
			}
		);
	}

	/**
	 * This is called when "Add to Menu" button is clicked.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleAddItemClick(event) {
		const fields = this.fields;

		let dataFields = [];

		fields.forEach(
			(field) => {
				let input = document.querySelector(
					`#${this.namespace}${field.name}`);

				let dataField = {
					description: field.description,
					label: field.label,
					name: field.name,
					value: input.value
				};

				dataFields.push(dataField);
			}
		);

		const fieldsetName = document.querySelector(
			`#${this.namespace}fieldsetName`);

		let data = {};

		data.id = this.type + "_" + new Date().getTime();
		data.name = fieldsetName.value;
		data.type = this.type;
		data.value = dataFields;

		this.emit(
			'itemSelected',
			data
		);

		this._clearFieldset();
	}

}

NavigationMenuFieldset.STATE = {

	/**
	 * Available fields to render in this fieldset.
	 *
	 * @instance
	 * @memberOf NavigationMenuFieldset
	 * @type {?Array}
	 * @default []
	 */
	fields: Config.arrayOf(
		Config.shapeOf({
			description: Config.string().value(''),
			label: Config.string().required(),
			name: Config.string().required(),
			value: Config.string().value('')
		})
	).value([]),

	/**
	 * The name of current fieldset
	 *
	 * @instance
	 * @memberOf NavigationMenuFieldset
	 * @type {!string}
	 */
	name: Config.string().required(),

	/**
	 * Namespace of the portlet being used.
	 * Necesary for getting the real inputs which interact with the server.
	 *
	 * @instance
	 * @memberOf NavigationMenuFieldset
	 * @type {!string}
	 */
	namespace: Config.string().required(),

	/**
	 * The type of current fieldset
	 *
	 * @instance
	 * @memberOf NavigationMenuFieldset
	 * @type {!string}
	 */
	type: Config.string().required(),

};

Soy.register(NavigationMenuFieldset, templates);

export { NavigationMenuFieldset }
export default NavigationMenuFieldset;