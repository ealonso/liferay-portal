import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';
import 'clay-multi-select';

import templates from './TagSelector.soy';

/**
 *
 */
class TagSelector extends Component {
	attached(...args) {
		super.attached(...args);

		this.refs.multiSelect.dataSource = this._handleQuery.bind(this);
	}

	_handleButtonClicked() {
		const selectedTagNames = this.selectedItems.map(
			item => item.value
		).join();

		AUI().use(
			'liferay-item-selector-dialog',
			function(A) {
				const uri = A.Lang.sub(
					decodeURIComponent(this.portletURL),
					{
						selectedTagNames: selectedTagNames
					}
				);

				const itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: this.eventName,
						on: {
							selectedItemChange: function(event) {
								const selectedItems = event.newVal;

								if (selectedItems) {
									this.selectedItems = selectedItems.items.split(',').map(
										item => {
											return {
												label: item,
												value: item
											};
										}
									);
								}
							}.bind(this)
						},
						'strings.add': Liferay.Language.get('done'),
						title: Liferay.Language.get('tags'),
						url: uri
					}
				);

				itemSelectorDialog.open();
			}.bind(this)
		);
	}

	_handleItemAdded(event) {
		this._updateSelectedItemsFallback();

		if (this.addCallback) {
			window[this.addCallback](event.data.item);
		}
	}

	_handleItemRemoved(event) {
		this._updateSelectedItemsFallback();

		if (this.removeCallback) {
			window[this.removeCallback](event.data.item);
		}
	}

	_handleQuery(query) {
		return new Promise(
			(resolve, reject) => {
				Liferay.Service(
					'/assettag/search',
					{
						end: 20,
						groupIds: [themeDisplay.getScopeGroupId()],
						name: `%${query === '*' ? '' : query}%`,
						start: 0,
						tagProperties: ''
					},
					tags => resolve(
						tags.map(tag => tag.value)
					)
				);
			}
		);
	}

	_updateSelectedItemsFallback() {
		document.getElementById(this.inputName).setAttribute(
			'value',
			this.selectedItems.map(selectedItem => selectedItem.value)
		);
	}
}

TagSelector.STATE = {

	/**
	 *
	 * @default undefined
	 * @instance
	 * @memberof TagSelector
	 * @review
	 * @type {?string}
	 */

	addCallback: Config.string(),

	/**
	 *
	 * @default undefined
	 * @instance
	 * @memberof TagSelector
	 * @review
	 * @type {?string}
	 */

	eventName: Config.string(),

	/**
	 *
	 * @default undefined
	 * @instance
	 * @memberof TagSelector
	 * @review
	 * @type {?string}
	 */

	portletURL: Config.string(),

	/**
	 *
	 * @default undefined
	 * @instance
	 * @memberof TagSelector
	 * @review
	 * @type {?string}
	 */

	removeCallback: Config.string()
};

Soy.register(TagSelector, templates);

export {TagSelector};
export default TagSelector;