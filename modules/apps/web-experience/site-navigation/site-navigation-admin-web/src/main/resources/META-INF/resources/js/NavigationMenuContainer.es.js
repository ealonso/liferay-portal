import Component from 'metal-component';
import { Config } from 'metal-state';
import { Drag, DragDrop } from 'metal-drag-drop';
import Soy from 'metal-soy';

import templates from './NavigationMenuContainer.soy';

/**
 * NavigationMenuContainer
 */
class NavigationMenuContainer extends Component {

	/**
	 * @inheritDoc
	 */
	attached() {
		const dragDrop = new DragDrop(
			{
				constrain: '.navigation-menu-container',
				dragPlaceholder: Drag.Placeholder.CLONE,
				handles: '.draggable',
				sources: '.navigation-menu-container .card',
				targets: '.navigation-menu-container, ' +
						 '.navigation-menu-container .card'
			}
		);

		dragDrop.on(
			DragDrop.Events.END,
			(data, event) => {
				event.preventDefault();

				this._handleFocus(data);
				this._handleDropItem(data);
			}
		);
	}

	/**
	 * @inheritDoc
	 */
	rendered() {
		if (this.selectedId) {
			const selectedElement = document.querySelector(
				`a[data-id="${this.selectedId}"]`);

			if (!selectedElement) {
				return;
			}

			selectedElement.focus();
		}
	}

	/**
	 *
	 * Checks if item or any of it's children contains an element with
	 * specified id.
	 *
	 * @param item Item to start search from.
	 * @param id ID to check.
	 * @returns {*} Parent item object in case when child exists and false
	 * otherwise.
	 * @private
	 */
	_getParent(item, id) {
		if (this._hasChild(item, id)) {
			return item;
		}

		if (item.children) {
			return item.children.reduce(
				(prev, next) => {
					const parent = this._getParent(next, id);

					if (!prev && parent) {
						return parent;
					}

					return prev;
				}, false
			);
		}
	};

	/**
	 * This is called when user drops the item on the container.
	 *
	 * @param {!object} data Drop event data
	 * @private
	 */
	_handleDropItem(data) {
		if (!data.target || data.source === data.target) {
			return;
		}

		let sourceId = data.source.querySelector("a").dataset.id;
		let targetId = 0;

		if (!data.target.classList.contains("navigation-menu-container")) {
			targetId = data.target.querySelector("a").dataset.id;
		}

		let items = this.items;

		let currentParent = items.reduce(
			(result, item) => {
				let parent = this._getParent(item, sourceId);

				if (!result && parent) {
					return parent;
				}

				return result;
			}, false
		);

		let parentItems = items;

		if (currentParent) {
			parentItems = currentParent.children;
		}

		let currentIndex = parentItems.reduce(
			(result, item, index) => item.id == sourceId ? index : result, -1
		);

		let item = parentItems.splice(currentIndex, 1)[0];

		let newParent = items.reduce(
			(result, item) => {
				let parent = this._getParent(item, targetId);

				if (!result && parent) {
					return parent.children.reduce(
						(prev, next) => next.id == targetId ? next : prev
					);
				}

				return result;
			}, false
		);

		if (!newParent) {
			newParent = items.reduce(
				(result, item) => item.id == targetId ? item : result, false);
		}

		if (newParent) {
			newParent.children.push(item);
		}
		else {
			items.push(item);
		}

		this.items = this.items;
		this.selectedId = sourceId;

		this.emit('itemMoved');
	}

	/**
	 * This is called when user deletes the item from container.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleDeleteItem(event) {
		const id = event.delegateTarget.dataset.id;

		this.emit(
			'itemDeleted',
			{
				id: id
			}
		);
	}

	/**
	 * This is called when user selects some item in the container.
	 * @private
	 */
	_handleFocus(event) {
		let id;

		if (event.source) {
			id = event.source.querySelector("a").dataset.id;
		}

		if (!id && event.target.classList.contains("card-focus-link")) {
			id = event.delegateTarget.dataset.id;
		}

		if (id) {
			this.selectedId = id;
		}

	}

	/**
	 * This is called when user presses some of the arrow keys in order to move
	 * the item across or along the container.
	 *
	 * @param {!Event} event
	 * @private
	 */
	_handleKeyUp(event) {
		let id = event.delegateTarget.dataset.id;

		if (event.keyCode === 37) {
			this._moveItemAcross(this.items, id, false);
		}
		else if (event.keyCode === 38) {
			this._moveItemAlong(this.items, id, false);
		}
		else if (event.keyCode === 39) {
			this._moveItemAcross(this.items, id, true);
		}
		else if (event.keyCode === 40) {
			this._moveItemAlong(this.items, id, true);
		}

		if (event.keyCode > 36 && event.keyCode < 50) {
			this.items = this.items;
			this.selectedId = id;

			this.emit('itemMoved');
		}
	}

	/**
	 * Checks if item contains a child element with specified id.
	 *
	 * @param item Item to start search from.
	 * @param id ID to check.
	 * @returns {boolean} True if item has child with specified id, false
	 * otherwise.
	 * @private
	 */
	_hasChild(item, id) {
		if (!item.children) {
			return false;
		}

		return item.children.reduce(
			(prev, next) => !prev && next.id == id ? true : prev, false
		)
	};

	/**
	 * Moves item across the container
	 *
	 * @param {!Array} items Items to move through
	 * @param {!Number} id Id of the item to move
	 * @param {!boolean} forward True if item should be moved forward
	 * @private
	 */
	_moveItemAcross(items, id, forward) {
		if (forward) {
			let oldIndex = items.reduce(
				(acc, item, index) => item.id === id ? index : acc, -1
			);

			if (oldIndex == -1) {
				items.forEach(
					(item) => {
						if (item.children) {
							this._moveItemAcross(item.children, id, forward);
						}
					}
				)
			}

			if (oldIndex <= 0) {
				return;
			}

			let item = items[oldIndex];

			items.splice(oldIndex, 1);

			let parent = items[oldIndex - 1];

			if (!parent) {
				return;
			}

			parent.children = parent.children ? parent.children : [];

			parent.children.push(item);

			items.splice(oldIndex - 1, 1, parent);
		}
		else {
			let currentParent;
			let newParent;

			currentParent = items.reduce(
				(result, item) => {
					let parent = this._getParent(item, id);

					if (!result && parent) {
						return parent;
					}

					return result;
				}, false
			);

			if (!currentParent) {
				return;
			}

			newParent = items.reduce(
				(result, item) => {
					let parent = this._getParent(item, currentParent.id);

					if (!result && parent) {
						return parent;
					}

					return result;
				}, false
			);

			let currentIndex = currentParent.children.reduce(
				(result, item, index) => item.id == id ? index : result, -1
			);

			if (currentIndex < 0) {
				return;
			}

			let item = currentParent.children.splice(currentIndex, 1)[0];

			if (newParent) {
				newParent.children.push(item);
			}
			else {
				items.push(item);
			}

		}
	}

	/**
	 * Moves item along the container
	 *
	 * @param {!Array} items Items to move through
	 * @param {!Number} id Id of the item to move
	 * @param {!boolean} forward True if item should be moved forward
	 * @private
	 */
	_moveItemAlong(items, id, forward) {
		let oldIndex = items.reduce(
			(acc, item, index) => item.id === id ? index : acc, -1
		);

		if (oldIndex == -1) {
			items.forEach(
				(item) => {
					if (item.children) {
						this._moveItemAlong(item.children, id, forward);
					}
				}
			)
		}

		let newIndex = forward ? oldIndex + 1 : oldIndex - 1;

		if (oldIndex < 0 || newIndex < 0 || newIndex > items.length - 1) {
			return;
		}

		items.splice(newIndex, 0, items.splice(oldIndex, 1)[0]);
	}
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
			id: Config.string().required(),
			icon: Config.string().required(),
			name: Config.string().required(),
			type: Config.string().required(),
			typeLabel: Config.string().required(),
			value: Config.any().required(),
			children: Config.array()
		})
	),

	/**
	 * Selected element id.
	 *
	 * @instance
	 * @memberOf NavigationMenuContainer
	 * @type {?string}
	 * @default undefined
	 */
	selectedId: Config.string()

};

Soy.register(NavigationMenuContainer, templates);

export { NavigationMenuContainer }
export default NavigationMenuContainer;