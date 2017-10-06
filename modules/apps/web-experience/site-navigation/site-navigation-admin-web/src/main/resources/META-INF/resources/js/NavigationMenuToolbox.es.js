import Component from 'metal-component';
import { Config } from 'metal-state';
import Soy from 'metal-soy';

import 'metal-dropdown';

import './NavigationMenuTree.es'

import templates from './NavigationMenuToolbox.soy';

/**
 * NavigationMenuToolbox
 *
 */
class NavigationMenuToolbox extends Component {

	_handleItemSelected(data) {
		this.emit(
			'itemSelected',
			data
		);
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