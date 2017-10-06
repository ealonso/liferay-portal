import {Component} from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './NavigationMenuFieldset.soy';

/**
 * NavigationMenuFieldset
 *
 */
class NavigationMenuFieldset extends Component {

	_clearFieldset() {
		let fields = this.fields;

		let fieldsetName = document.querySelector(
			`#${this.namespace}fieldsetName`);

		fieldsetName.value = '';

		fields.forEach(
			(field) => {
				field.value = ''

				let input = document.querySelector(
					`#${this.namespace}${field.name}`);

				input.value = '';
			}
		);

		this.emit(
			'contextChanged',
			{
				fields: fields
			}
		);
	}

	_handleClick(event) {
		let fields = this.fields;

		fields.forEach(
			(field) => {
				let input = document.querySelector(
					`#${this.namespace}${field.name}`);

				field.value = input.value;
			}
		);

		let fieldsetName = document.querySelector(
			`#${this.namespace}fieldsetName`);

		let data = {};

		data.id = this.type + "_" + new Date().getTime();
		data.icon = this.icon;
		data.name = fieldsetName.value;
		data.type = this.type;
		data.value = this.fields;

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
	),

	/**
	 * The icon of current fieldset
	 *
	 * @instance
	 * @memberOf NavigationMenuFieldset
	 * @type {!string}
	 */
	icon: Config.string().required(),

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