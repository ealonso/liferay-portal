import {Config} from 'metal-state';
import {dom} from 'metal-dom';
import Soy from 'metal-soy';

import SelectLayout from 'layout-item-selector-web/js/SelectLayout.es';

import templates from './NavigationMenuTree.soy';

/**
 * NavigationMenuTree
 */
class NavigationMenuTree extends SelectLayout {

	/**
	 * @inheritDoc
	 */
	attached() {
		dom.on(
			'.navigation-menu-tree .card', 'keyup',
			this._handleNodeKeyUp.bind(this));

		dom.on(
			'.navigation-menu-tree .card', 'dblclick',
			this._handleNodeDblClick.bind(this));
	}

	/**
	 * This is called when one of this tree view's nodes receives a double
	 * click.
	 *
	 * @param {!Event} event
	 * @protected
	 */
	_handleNodeDblClick(event) {
		const target = event.delegateTarget.parentNode.parentNode.parentNode;
		const treeView = this.refs.treeView;

		const node = treeView.getNodeObj(
			target.dataset.treeviewPath.split('-'));

		this._selectItem(node);
	}

	/**
	 * This is called when one of this tree view's nodes receives a keypress.
	 * - ENTER : Select the current node
	 * @param {!Event} event
	 * @protected
	 */
	_handleNodeKeyUp(event) {
		const target = event.delegateTarget.parentNode.parentNode.parentNode;
		const treeView = this.refs.treeView;

		const node = treeView.getNodeObj(
			target.dataset.treeviewPath.split('-'));

		if (event.keyCode === 13) {
			this._selectItem(node);
		}
	}

	/**
	 * This is called to select an item in the tree and add selected item to
	 * the container.
	 *
	 * @param {!object} item Selected item object.
	 * @private
	 */
	_selectItem(item) {
		if (item.disabled) {
			return;
		}

		this.emit('itemSelected', item);

		this.nodes.forEach(
			(node) => {
				if (node.id == item.id) {
					node.disabled = true;
				}
			}
		);
	}

}

let nodeShape = {
	id: Config.string().value(''),
	label: Config.string().required(),
	name: Config.string().required(),
	value: Config.string().value('')
};

const nodesValidator = Config.arrayOf(Config.shapeOf(nodeShape));

nodeShape.children = nodesValidator;

NavigationMenuTree.STATE = {

	/**
	 * Enables URL following on the title click
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {?String}
	 * @default false
	 */
	followURLOnTitleClick: Config.bool().value(false),

	/**
	 * Event name to fire on node selection
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {!String}
	 */
	itemSelectorSaveEvent: Config.string().required(),

	/**
	 * List of nodes
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {Array.<Object>}
	 */
	nodes: nodesValidator.required(),

	/**
	 * Enables multiple selection of tree elements
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {?boolean}
	 * @default false
	 */
	multiSelection: Config.bool().value(false),

	/**
	 * Theme images root path
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {!String}
	 */
	pathThemeImages: Config.string().required(),

	/**
	 * Type of view to render. Accepted values are 'tree' and 'flat'
	 *
	 * @instance
	 * @memberOf NavigationMenuTree
	 * @type {?String}
	 * @default tree
	 */
	viewType: Config.string().value('tree')

};

Soy.register(NavigationMenuTree, templates);

export { NavigationMenuTree }
export default NavigationMenuTree;