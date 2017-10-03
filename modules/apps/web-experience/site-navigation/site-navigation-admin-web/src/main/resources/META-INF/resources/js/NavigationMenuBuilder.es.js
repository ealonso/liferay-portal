import Component from 'metal-component';
import { Config } from 'metal-state';
import Soy from 'metal-soy';

import './NavigationMenuContainer.es';
import './NavigationMenuToolbox.es';

import templates from './NavigationMenuBuilder.soy';

/**
 * NavigationMenuBuilder
 *
 */
class NavigationMenuBuilder extends Component {

}

NavigationMenuBuilder.STATE = {

	/**
	 * Available menu item types to add to the menu container.
	 *
	 * @instance
	 * @memberOf NavigationMenuToolbox
	 * @type {?Array}
	 * @default []
	 */
	availableItemTypes: Config.arrayOf(
		Config.shapeOf({
			editViewHTML: Config.string().required(),
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
			type: Config.string().required()
		})
	),

	/**
	 * Selected item type to show in the menu builder toolbox.
	 *
	 * @instance
	 * @memberOf NavigationMenuBuilder
	 * @type {!String}
	 */
	selectedItemType: Config.shapeOf({
		editViewHTML: Config.string().required(),
		icon: Config.string().required(),
		type: Config.string().required()
	})
};

Soy.register(NavigationMenuBuilder, templates);

export { NavigationMenuBuilder }
export default NavigationMenuBuilder;