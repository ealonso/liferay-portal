import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './Fragment.soy';

/**
 * Fragment
 * @review
 */
class Fragment extends Component {
	/**
	 * @inheritDoc
	 * @review
	 */
	created() {
		this._fetchFragmentContent(this.fragmentEntryId, this.index);
	}

	/**
	 * After each render, script tags need to be reapended to the DOM
	 * in order to trigger an execution (content changes do not trigger it).
	 * @inheritDoc
	 * @review
	 */
	rendered() {
		if (this.refs.content) {
			this.refs.content.querySelectorAll('script').forEach(script => {
				const parentNode = script.parentNode;
				const newScript = document.createElement('script');

				newScript.innerHTML = script.innerHTML;
				parentNode.removeChild(script);
				parentNode.appendChild(newScript);
			});
		}
	}

	/**
	 * @inheritDoc
	 * @param {!Object} changes
	 * @review
	 */
	willUpdate(changes) {
		if (changes.fragmentEntryId || changes.index) {
			const fragmentEntryId = changes.fragmentEntryId
				? changes.fragmentEntryId.newVal
				: this.fragmentEntryId;
			const position = changes.index ? changes.index.newVal : this.index;

			this._fetchFragmentContent(fragmentEntryId, position);
		}
	}

	/**
	 * Fetches a fragment entry from the given ID, and stores the HTML,
	 * CSS and JS result into component properties.
	 * @param {!string} fragmentEntryId
	 * @param {!string} position
	 * @private
	 * @review
	 */
	_fetchFragmentContent(fragmentEntryId, position) {
		const formData = new FormData();

		formData.append(
			`${this.portletNamespace}fragmentEntryId`,
			fragmentEntryId
		);
		formData.append(`${this.portletNamespace}position`, position);

		this._loading = true;

		fetch(this.renderFragmentEntryURL, {
			body: formData,
			credentials: 'include',
			method: 'POST',
		})
			.then(response => response.json())
			.then(response => {
				this._content = Soy.toIncDom(response.content);
				this._loading = false;
			});
	}

	/**
	 * Callback executed when the fragment remove button is clicked.
	 * It emits a 'fragmentRemoveButtonClick' event with the fragment index.
	 * @private
	 * @review
	 */
	_handleFragmentRemoveButtonClick() {
		this.emit('fragmentRemoveButtonClick', {
			fragmentIndex: this.index,
		});
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
Fragment.STATE = {
	/**
	 * Fragment entry ID
	 * @default undefined
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {!string}
	 */
	fragmentEntryId: Config.string().required(),

	/**
	 * Fragment index
	 * @default undefined
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {!number}
	 */
	index: Config.number().required(),

	/**
	 * Fragment name
	 * @default ''
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {string}
	 */
	name: Config.string().value(''),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * URL for getting a fragment render result.
	 * @default undefined
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {!string}
	 */
	renderFragmentEntryURL: Config.string().required(),

	/**
	 * When true, it will show the controls that are displayed in the fragment
	 * topper element.
	 * @default true
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {boolean}
	 */
	showFragmentControls: Config.bool().value(true),

	/**
	 * Fragment spritemap
	 * @default undefined
	 * @instance
	 * @memberOf Fragment
	 * @review
	 * @type {!string}
	 */
	spritemap: Config.string().required(),

	/**
	 * Fragment content to be rendered
	 * @default Soy.toIncDom('')
	 * @instance
	 * @memberOf Fragment
	 * @private
	 * @review
	 * @type {function}
	 */
	_content: Config.func()
		.internal()
		.value(Soy.toIncDom('')),

	/**
	 * Flag indicating that fragment information is being loaded
	 * @default false
	 * @instance
	 * @memberOf Fragment
	 * @private
	 * @review
	 * @type {boolean}
	 */
	_loading: Config.bool().value(false),
};

Soy.register(Fragment, templates);

export {Fragment};
export default Fragment;
