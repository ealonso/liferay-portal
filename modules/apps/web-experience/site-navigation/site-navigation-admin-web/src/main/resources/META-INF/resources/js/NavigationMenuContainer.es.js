import Component from 'metal-component';
import { Config } from 'metal-state';
import Soy from 'metal-soy';

import templates from './NavigationMenuContainer.soy';

/**
 * NavigationMenuContainer
 *
 */
class NavigationMenuContainer extends Component {

}

NavigationMenuContainer.STATE = {

	/**
	 * Current menu items.
	 *
	 * @instance
	 * @memberOf NavigationMenuContainer
	 * @type {?Array}
	 * @default []
	 */
	items: Config.arrayOf(
		Config.shapeOf({
			type: Config.string().required()
		})
	)

};

Soy.register(NavigationMenuContainer, templates);

export { NavigationMenuContainer }
export default NavigationMenuContainer;