import Component from 'metal-component';
import {dom} from 'metal-dom';
import {hasClass} from 'metal-dom';
import {Config} from 'metal-state';
import Soy from 'metal-soy';
import 'metal-dropdown';
import templates from './MSBPageListBlock.soy';

/**
 * Component that represents one block of layouts for <MSBPageList /> component.
 */
class MSBPageListBlock extends Component {
	/**
	 * This is called when one of this block's nodes is clicked.
	 * @param {!Event} event
	 * @private
	 */
	_handleNodeClicked(event) {
		let target = event.target;

		if (hasClass(target, 'action-icon')) {
			return;
		}

		const layoutId = event.delegateTarget.dataset.layoutid;
		const parentLayoutId = event.delegateTarget.dataset.parentlayoutid;
		const title = event.delegateTarget.dataset.title;

		this._selectNode(layoutId, parentLayoutId, title);
	}

	/**
	 * Selects specific node and emits associated event.
	 *
	 * @param {number} layoutId Node's layout ID
	 * @param {number} parentLayoutId Node's parent layout ID
	 * @param {string} title Node's title
	 * @private
	 */
	_selectNode(layoutId, parentLayoutId, title) {
		this.nodes.forEach(
			(node) => {
				if (node.layoutId !== layoutId) {
					node.selected = false;
				}
				else {
					node.active = true;
					node.selected = true;
				}
			},
			this
		);

		this.nodes = this.nodes;

		if (parentLayoutId >= 0) {
			this.emit(
				'nodeSelected',
				{
					layoutId: layoutId,
					loadParents: true,
					parentLayoutId: parentLayoutId,
					ref: this.getInitialConfig().ref,
					title: title,
				}
			);
		}
	}
}

MSBPageListBlock.STATE = {
	/**
	 * Current block nodes list
	 * @instance
	 * @memberof MSBPageListBlock
	 * @type {!Array}
	 */
	nodes: Config.array().required(),
};

Soy.register(MSBPageListBlock, templates);

export default MSBPageListBlock;