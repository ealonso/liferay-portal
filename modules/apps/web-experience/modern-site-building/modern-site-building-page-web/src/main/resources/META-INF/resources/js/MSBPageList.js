import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';
import './MSBPageListBlock';
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
	 * Returns last selected node for specified page list block.
	 *
	 * @param {string} ref Page list block name.
	 * @return {object} Last selected node or undefined if there is no node
	 * 		   selected.
	 * @protected
	 */
	_getLastSelectedNode(ref) {
		const node = this[ref].reduce(
			(value, node) => (!value.layoutId && node.selected ? node : value),
			{}
		);

		node.ref = ref;

		return node;
	}

	/**
	 * This is called when one of breadcrumb's entries is clicked.
	 * @param {!Event} event
	 * @protected
	 */
	_handleBreadcrumbEntryClicked(event) {
		const index = event.delegateTarget.dataset.index;
		const layoutId = event.delegateTarget.dataset.layoutid;
		const parentLayoutId = event.delegateTarget.dataset.parentlayoutid;
		const title = event.delegateTarget.dataset.title;

		this._loadParents(
			{
				layoutId: parentLayoutId,
				selectedLayoutId: layoutId,
			},
			false, false
		);

		if (index > 1) {
			const previousEntry = this.breadcrumbEntries[index - 1];

			previousEntry.selectedLayoutId = layoutId;

			this.handleFirstBlockNodeSelected(previousEntry);

			this.refs.secondLevelNodes.selectNode(layoutId, parentLayoutId, title);
		}
		else if (index == 1) {
			this.refs.firstLevelNodes.selectNode(layoutId, parentLayoutId, title);
		}
		else {
			this.secondLevelNodes = [];
			this.thirdLevelNodes = [];

			this._updateBreadcrumb(
				{
					layoutId: layoutId,
					parentLayoutId: -1,
				}
			);
		}
	}

	/**
	 * Loads children nodes for selected node in the first block.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @protected
	 */
	_handleFirstBlockNodeSelected(event) {
		if (event.loadParents && event.parentLayoutId > 0) {
			this._loadParents({
				layoutId: event.parentLayoutId,
			});

			return;
		}

		this._loadLayouts(
			event.layoutId,
			false,
			event.selectedLayoutId,
			(layouts) => {
				this.secondLevelNodes = layouts;
				this.thirdLevelNodes = [];
			}
		);

		this._updateBreadcrumb(event);

		this._resetActive(event);
	}

	/**
	 * Loads children nodes for selected node in the second block.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @protected
	 */
	_handleSecondBlockNodeSelected(event) {
		this._loadLayouts(
			event.layoutId,
			false,
			event.selectedLayoutId,
			(layouts) => {
				this.thirdLevelNodes = layouts;
			}
		);

		this._updateBreadcrumb(event);

		this._resetActive(event);
	}

	/**
	 * Switches page blocks when user selects a node in the third block.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @protected
	 */
	_handleThirdBlockNodeSelected(event) {
		const secondLevelNodes = this.secondLevelNodes;
		const thirdLevelNodes = this.thirdLevelNodes;

		this._loadLayouts(
			event.layoutId,
			false,
			event.selectedLayoutId,
			(layouts) => {
				if (layouts.length > 0) {
					this.thirdLevelNodes = layouts;

					this.firstLevelNodes = secondLevelNodes;
					this.secondLevelNodes = thirdLevelNodes;
				}
			}
		);

		this._updateBreadcrumb(event);

		this._resetActive(event);
	}

	/**
	 * Loads parent or children layouts for specified node.
	 *
	 * @param {number} parentLayoutId Parent layout ID.
	 * @param {boolean} loadParentLayouts If true when loads parents for the
	 *        selected node.
	 * @param {number} selectedLayoutId Layout ID to be selected.
	 * @param {!Function} callback Callback function to be executed after the
	 *        AJAX call.
	 * @protected
	 */
	_loadLayouts(parentLayoutId, loadParentLayouts, selectedLayoutId, callback) {
		const orderBy = this.getInitialConfig().orderBy;

		const body = new URLSearchParams();

		body.append(`${this.portletNamespace}parentLayoutId`, parentLayoutId);
		body.append(`${this.portletNamespace}loadParentLayouts`, loadParentLayouts);
		body.append(`${this.portletNamespace}selectedLayoutId`, selectedLayoutId);
		body.append(`${this.portletNamespace}orderByCol`, orderBy.orderByCol);
		body.append(`${this.portletNamespace}orderByType`, orderBy.orderByType);

		const data = {
			cache: 'no-cache',
			credentials: 'same-origin',
			method: 'GET',

		};

		const queryString = this.getLayoutsURL + '&' + body.toString();

		const request = new Request(queryString);

		fetch(request, data).then(
			(response) => response.json().then(callback)
		);
	}

	/**
	 * Loads parent layouts for selected node.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @param {boolean} switchBlocks If true when MSBPageList switches blocks as
	 * 	      the first block node was clicked.
	 * @param {boolean} updateBreadcrumb If true when MSBPageList updates
	 *        breadcrumb entrues.
	 * @protected
	 */
	_loadParents(event, switchBlocks = true, updateBreadcrumb = true) {
		const firstLevelNodes = this.firstLevelNodes;

		this._loadLayouts(
			event.layoutId,
			true,
			event.selectedLayoutId,
			(layouts) => {
				this.firstLevelNodes = layouts;

				if (switchBlocks) {
					this.secondLevelNodes = firstLevelNodes;
					this.thirdLevelNodes = [];

					const selectedNode = this._getLastSelectedNode('secondLevelNodes');

					if (selectedNode.layoutId > 0) {
						this.handleSecondBlockNodeSelected(selectedNode);
					}

					if (updateBreadcrumb) {
						this._updateBreadcrumb(selectedNode);
					}
				}
			}
		);
	}

	/**
	 * Resets all active selected nodes except the last selected.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @protected
	 */
	_resetActive(event) {
		const ref = event.ref;

		for (let key in this.refs) {
			if (key != ref && Array.isArray(this[key])) {
				this[key].forEach(
					(node) => {
						node.active = false;
					}
				);

				this[key] = this[key];
			}
		}
	}

	/**
	 * Updates breadcrumb when some node was clicked.
	 *
	 * @param {Event} event Contains layout ID, parent layout ID and title of
	 * 		  the node. Also contains reference name of current active block and
	 * 		  selected layout ID.
	 * @protected
	 */
	_updateBreadcrumb(event) {
		const layoutId = event.layoutId;
		const parentLayoutId = event.parentLayoutId;
		const title = event.title;

		const breadcrumbEntries = [];

		breadcrumbEntries.push(this.breadcrumbEntries[0]);

		let index = 1;

		this.breadcrumbEntries.forEach(
			(entry) => {
				if (entry.parentLayoutId < parentLayoutId && entry.layoutId >= 0) {
					entry.index = index++;

					breadcrumbEntries.push(entry);
				}
			}
		);

		if (layoutId > 0) {
			breadcrumbEntries.push(
				{
					index: index,
					layoutId: layoutId,
					parentLayoutId: parentLayoutId,
					title: title,
				}
			);
		}

		this.breadcrumbEntries = breadcrumbEntries;
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
MSBPageList.STATE = {
	/**
	 * Breadcrumb entries
	 * @instance
	 * @memberof MSBPageList
	 * @type {!Array}
	 */
	breadcrumbEntries: Config.array().required(),

	/**
	 * First block nodes
	 * @instance
	 * @memberof MSBPageList
	 * @type {!Array}
	 */
	firstLevelNodes: Config.array().required(),

	/**
	 * URL to get nodes via AJAX
	 * @instance
	 * @memberof MSBPageList
	 * @type {?string}
	 * @default undefined
	 */
	getLayoutsURL: Config.string(),

	/**
	 * Namespace of portlet to prefix parameters names
	 * @instance
	 * @memberof MSBPageList
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * Second block nodes
	 * @instance
	 * @memberof MSBPageList
	 * @type {?Array}
	 * @default []
	 */
	secondLevelNodes: Config.array().value([]),

	/**
	 * Third block nodes
	 * @instance
	 * @memberof MSBPageList
	 * @type {?Array}
	 * @default []
	 */
	thirdLevelNodes: Config.array().value([]),
};

Soy.register(MSBPageList, templates);

export default MSBPageList;