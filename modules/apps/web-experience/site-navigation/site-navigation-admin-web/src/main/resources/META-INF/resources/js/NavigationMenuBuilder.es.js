import Component from 'metal-component';
import { Config } from 'metal-state'
import Soy from 'metal-soy';

import './NavigationMenuContainer.es';
import './NavigationMenuToolbox.es';

import templates from './NavigationMenuBuilder.soy';

/**
 * NavigationMenuBuilder
 *
 */
class NavigationMenuBuilder extends Component {

	_handleItemSelected(data) {
		let item = {
			children: [],
			id: data.id,
			icon: this.selectedItemType.icon,
			name: data.name,
			value: data.value,
			type: this.selectedItemType.type
		};

		let menuItems = !this.menuItems ? [] : this.menuItems;

		menuItems.push(item);

		let menuItemsInput = document.querySelector(`#${this.menuItemsInput}`);

		menuItemsInput.value = JSON.stringify(menuItems);

		this.menuItems = menuItems;
	}

}

NavigationMenuBuilder.STATE = {

	/**
	 * Available menu item types to add to the menu container.
	 *
	 * @instance
	 * @memberOf NavigationMenuBuilder
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
	 * Current menu items.
	 *
	 * @instance
	 * @memberOf NavigationMenuBuilder
	 * @type {?Array}
	 * @default []
	 */
	menuItems: Config.arrayOf(
		Config.shapeOf({
			id: Config.string().required(),
			icon: Config.string().required(),
			name: Config.string().required(),
			type: Config.string().required(),
			value: Config.any().required(),
			children: Config.array()
		})
	),

	/**
	 * Input field to store serialized menu items.
	 *
	 * @instance
	 * @memberOf NavigationMenuBuilder
	 * @type {!string}
	 */
	menuItemsInput: Config.string().required(),

	/**
	 * Selected item type to show in the menu builder toolbox.
	 *
	 * @instance
	 * @memberOf NavigationMenuBuilder
	 * @type {!String}
	 */
	selectedItemType: Config.shapeOf({
		context: Config.object().required(),
		displayStyle: Config.string().required(),
		icon: Config.string().required(),
		type: Config.string().required()
	})
};

Soy.register(NavigationMenuBuilder, templates);

export { NavigationMenuBuilder }
export default NavigationMenuBuilder;