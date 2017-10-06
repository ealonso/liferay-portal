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

	/**
	 * This is called when user deletes the item from container.
	 *
	 * @param {!object} data
	 * @private
	 */
	_handleItemDeleted(data) {
		let menuItems = this.menuItems;

		const deleteItem = (items, id) => {
			items = items.filter((item) => item.id != id);

			items.forEach(
				(item) => {
					if (item.children) {
						item.children = deleteItem(item.children, id);
					}
			});

			return items;
		};

		menuItems = deleteItem(menuItems, data.id);

		this.menuItems = menuItems;
	}

	/**
	 * This is called when some item was added to the container.
	 *
	 * @param {!object} data
	 * @private
	 */
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

	/**
	 * This is called when user selects some item type in the toolbox.
	 *
	 * @param {!object} type Selected item type
	 * @private
	 */
	_handleItemTypeSelected(type) {
		this.selectedItemType = type;
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