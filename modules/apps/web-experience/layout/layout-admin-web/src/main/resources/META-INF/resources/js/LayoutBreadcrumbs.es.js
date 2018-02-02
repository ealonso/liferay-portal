import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './LayoutBreadcrumbs.soy';

/**
 * LayoutBreadcrumbs
 * @review
 */
class LayoutBreadcrumbs extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
LayoutBreadcrumbs.STATE = {
	/**
	 * Breadcrumb entries
	 * @default undefined
	 * @instance
	 * @memberOf LayoutBreadcrumbs
	 * @review
	 * @type {!Array}
	 */
	breadcrumbEntries: Config.arrayOf(
		Config.shapeOf({
			title: Config.string().required(),
			url: Config.string().required(),
		})
	).required(),
};

Soy.register(LayoutBreadcrumbs, templates);

export {LayoutBreadcrumbs};
export default LayoutBreadcrumbs;
