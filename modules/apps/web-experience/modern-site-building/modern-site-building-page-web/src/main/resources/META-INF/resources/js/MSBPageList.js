import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import './MSBPageListBlock';
import { isBreadcrumbEntry, isNode } from './validators';
import templates from './MSBPageList.soy';

/**
 * Component that allows to show layouts tree in form of three dependent blocks.
 * It integrates three <MSBPageListBlock /> components for N-th, N-th + 2 and
 * N-th + 3 levels of layouts tree.
 */
class MSBPageList extends Component {
	/**
	 * @inheritDoc
	 */
	attached() {
		const A = new AUI();

		A.use(
			'liferay-search-container',
			'liferay-search-container-select',
			(A) => {
				const plugins = [];

				plugins.push(
					{
						cfg: {
							rowSelector: '.page-list-block',
						},
						fn: A.Plugin.SearchContainerSelect,
					}
				);

				const searchContainer = new Liferay.SearchContainer(
					{
						contentBox: A.one(this.refs.msbPageList),
						id: this.getInitialConfig().portletNamespace +
							this.getInitialConfig().searchContainerId,
						plugins: plugins,
					}
				);

				this.searchContainer_ = searchContainer;

				Liferay.fire(
					'search-container:registered',
					{
						searchContainer: searchContainer,
					}
				);
			}
		);
	}

	/**
	 * @inheritDoc
	 */
	rendered() {
		this.refs.pageList.scrollLeft = this.refs.pageList.scrollWidth;
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
MSBPageList.STATE = {
	/**
	 * Block nodes
	 * @instance
	 * @memberof MSBPageList
	 * @type {!Array}
	 */
	nodeBlocks: Config
		.arrayOf(Config.arrayOf(isNode))
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

Soy.register(MSBPageList, templates);

export default MSBPageList;