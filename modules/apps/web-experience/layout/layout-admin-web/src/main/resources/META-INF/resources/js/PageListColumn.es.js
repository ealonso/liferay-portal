import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './PageListColumn.soy';

/**
 * PageListColumn
 */
class PageListColumn extends Component {}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageListColumn.STATE = {
	/**
	 * List of layouts in the current column
	 * @default undefined
	 * @instance
	 * @memberof PageListColumn
	 * @type {!Array}
	 */
	layouts: Config.arrayOf(
		Config.shapeOf({
			active: Config.bool().required(),
			hasChild: Config.bool().required(),
			icon: Config.string().required(),
			layoutId: Config.string().required(),
			parentLayoutId: Config.string().required(),
			selected: Config.bool().required(),
			title: Config.string().required(),
		})
	).required(),

	/**
	 * URL for using icons
	 * @default undefined
	 * @instance
	 * @memberof PageListColumn
	 * @type {!string}
	 */
	pathThemeImages: Config.string().required(),

	/**
	 * Namespace of portlet to prefix parameters names
	 * @default undefined
	 * @instance
	 * @memberof PageListColumn
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * URL of portlet to prefix block links
	 * @default undefined
	 * @instance
	 * @memberof PageListColumn
	 * @type {!string}
	 */
	portletURL: Config.string().required(),
};

Soy.register(PageListColumn, templates);

export {PageListColumn};
export default PageListColumn;
