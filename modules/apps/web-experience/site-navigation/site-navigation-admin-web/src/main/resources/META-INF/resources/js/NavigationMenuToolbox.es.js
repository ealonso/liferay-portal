import Component from 'metal-component';
import { Config } from 'metal-state';
import Soy from 'metal-soy';

import 'metal-dropdown';

import './NavigationMenuFieldset.es'
import './NavigationMenuTree.es'

import templates from './NavigationMenuToolbox.soy';

/**
 * NavigationMenuToolbox
 *
 */
class NavigationMenuToolbox extends Component {

	/**
	 * This is called when user changes context of the selected item type.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleContextChanged(data) {
		let selectedItemType = this.selectedItemType;

		for (let property in data) {
			selectedItemType.context[property] = data[property];
		}

		this.emit('itemTypeSelected', selectedItemType);
	}

	/**
	 * This is called when user wants to add a fieldset to the container.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleItemSelected(data) {
		this.emit(
			'itemSelected',
			data
		);
	}

	/**
	 * This is called when user selects menu item type in the dropdown.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleItemTypeChanged(event) {
		const node = event.delegateTarget;
		const type = node.dataset.type;

		let selectedItemType = this.availableItemTypes.filter(
			(availableItemType) => availableItemType.type === type)[0];

		this.emit('itemTypeSelected', selectedItemType);
	}

}

NavigationMenuToolbox.STATE = {

	/**
	 * Available menu items to add to the menu container.
	 *
	 * @instance
	 * @memberOf NavigationMenuToolbox
	 * @type {?Array}
	 * @default []
	 */
	availableItemTypes: Config.arrayOf(
		Config.shapeOf({
			context: Config.object().required(),
			displayStyle: Config.string().required(),
			icon: Config.string().required(),
			type: Config.string().required()
		})
	),

	/**
	 * Selected item type.
	 *
	 * @instance
	 * @memberOf NavigationMenuToolbox
	 * @type {!String}
	 */
	selectedItemType: Config.shapeOf({
		context: Config.object().required(),
		displayStyle: Config.string().required(),
		icon: Config.string().required(),
		type: Config.string().required()
	})
};

Soy.register(NavigationMenuToolbox, templates);

export { NavigationMenuToolbox }
export default NavigationMenuToolbox;