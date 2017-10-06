import {dom} from 'metal-dom';
import Soy from 'metal-soy';

import SelectLayout from 'layout-item-selector-web/js/SelectLayout.es';

import templates from './NavigationMenuTree.soy';

/**
 * NavigationMenuTree
 *
 */
class NavigationMenuTree extends SelectLayout {

	/**
	 * @inheritDoc
	 */
	attached() {
		dom.on('.navigation-menu-tree .card', 'keyup', this._handleNodeKeyUp.bind(this))
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

		const node = treeView.getNodeObj(target.dataset.treeviewPath.split('-'));

		if (event.keyCode === 13) {
			this.emit(
				'itemSelected',
				node
			);
		}
	}

}

NavigationMenuTree.STATE = {
};

Soy.register(NavigationMenuTree, templates);

export { NavigationMenuTree }
export default NavigationMenuTree;