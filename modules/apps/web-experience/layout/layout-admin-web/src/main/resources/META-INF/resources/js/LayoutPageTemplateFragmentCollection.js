import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './LayoutPageTemplateFragmentCollection.soy';

/**
 * LayoutPageTemplateFragmentCollection
 */
class LayoutPageTemplateFragmentCollection extends Component {
	/**
	 * Callback that is executed when a fragment entry is clicked.
	 * It propagates a collectionEntryClick event with the fragment information.
	 * @param {Event} event
	 * @private
	 */
	_handleEntryClick(event) {
		const fragmentId = event.delegateTarget.dataset.fragmentId;
		const fragmentName = this.collection.entries
			.find((entry) => entry.id === fragmentId)
			.name;

		this.emit('collectionEntryClick', {
			fragmentId,
			fragmentName
		});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
LayoutPageTemplateFragmentCollection.STATE = {
	/**
	 * Available entries that can be dragged inside the existing Page Template,
	 * organized by fragment categories.
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!Array<object>}
	 */
	collection: Config.shapeOf({
		id: Config.string().required(),
		name: Config.string().required(),
		entries: Config.arrayOf(
			Config.shapeOf({
				id: Config.string().required(),
				name: Config.string().required()
			})
		).required()
	}),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!string}
	 */
	spritemap: Config.string().required(),
};

Soy.register(LayoutPageTemplateFragmentCollection, templates);

export {LayoutPageTemplateFragmentCollection};
export default LayoutPageTemplateFragmentCollection;