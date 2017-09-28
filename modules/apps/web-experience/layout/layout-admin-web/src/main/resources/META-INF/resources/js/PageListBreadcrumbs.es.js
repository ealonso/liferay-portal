import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './PageListBreadcrumbs.soy';

/**
 * PageListBreadcrumbs
 */
class PageListBreadcrumbs extends Component {}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageListBreadcrumbs.STATE = {
	/**
	 * Layout blocks
	 * @default undefined
	 * @instance
	 * @memberof PageListBreadcrumbs
	 * @type {!Array}
	 */
	layoutBlocks: Config.arrayOf(
		Config.arrayOf(
			Config.shapeOf({
				active: Config.bool().required(),
				hasChild: Config.bool().required(),
				icon: Config.string().required(),
				layoutId: Config.string().required(),
				parentLayoutId: Config.string().required(),
				selected: Config.bool().required(),
				title: Config.string().required(),
			})
		)
	).required(),

	/**
	 * Namespace of portlet to prefix parameters names
	 * @default undefined
	 * @instance
	 * @memberof PageListBreadcrumbs
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * URL of portlet to prefix block links
	 * @default undefined
	 * @instance
	 * @memberof PageListBreadcrumbs
	 * @type {!string}
	 */
	portletURL: Config.string().required(),
};

Soy.register(PageListBreadcrumbs, templates);

export {PageListBreadcrumbs};
export default PageListBreadcrumbs;
