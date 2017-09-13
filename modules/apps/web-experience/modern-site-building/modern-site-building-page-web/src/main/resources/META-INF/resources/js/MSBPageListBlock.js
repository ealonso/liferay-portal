import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';
import 'metal-dropdown';

import {isLayout} from './validators';
import templates from './MSBPageListBlock.soy';

/**
 * Component that represents one block of layouts for <MSBPageList /> component.
 */
class MSBPageListBlock extends Component {
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
MSBPageListBlock.STATE = {
	/**
	 * Current block layouts list
	 * @instance
	 * @memberof MSBPageListBlock
	 * @type {!Array}
	 */
	layouts: Config
		.arrayOf(isLayout)
		.required(),

	/**
	 * URL for using icons
	 * @instance
	 * @memberof MSBPageList
	 * @type {!string}
	 */
	pathThemeImages: Config
		.string()
		.required(),

	/**
	 * Namespace of portlet to prefix parameters names
	 * @instance
	 * @memberof MSBPageList
	 * @type {!string}
	 */
	portletNamespace: Config
		.string()
		.required(),

	/**
	 * URL of portlet to prefix block links
	 * @instance
	 * @memberof MSBPageList
	 * @type {!string}
	 */
	portletURL: Config.string().required(),
};

Soy.register(MSBPageListBlock, templates);

export default MSBPageListBlock;