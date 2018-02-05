import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import './contextual_sidebar/ContextualSidebar.es';
import './Fragment.es';
import templates from './ContentLayoutEditor.soy';

/**
 * Component that allows editing Content Layouts
 * @review
 */
class ContentLayoutEditor extends Component {
	/**
	 * Callback executed when the sidebar should be hidden
	 * @private
	 * @review
	 */
	_handleHideContextualSidebar() {
		this._contextualSidebarVisible = false;
	}

	/**
	 * Updates _sidebarSelectedTab according to the clicked element
	 * @param {!Event} event
	 * @private
	 * @review
	 */
	_handleSidebarTabClick(event) {
		this._sidebarSelectedTab = event.delegateTarget.dataset.tabName;
	}

	/**
	 * Callback executed when the sidebar visible state should be toggled
	 * @private
	 * @review
	 */
	_handleToggleContextualSidebarButtonClick() {
		this._contextualSidebarVisible = !this._contextualSidebarVisible;
	}
}

/**
 * Tabs that can appear inside the sidebar
 * @review
 * @see ContentLayoutEditor._sidebarTabs
 */
const SIDEBAR_TABS = [
	{
		id: 'configuration',
		name: Liferay.Language.get('configuration'),
		visible: true,
	},
];

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
ContentLayoutEditor.STATE = {
	/**
	 * Optional ID provided by the template system.
	 * @default ''
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @review
	 * @type {string}
	 */
	id: Config.string().value(''),

	/**
	 * List of fragment instances part of the Layout Page Template, the order
	 * of the elements in this array defines their position.
	 * @default []
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @review
	 * @type {Array<string>}
	 */
	fragments: Config.arrayOf(
		Config.shapeOf({
			fragmentEntryId: Config.string().required(),
			config: Config.object().value({}),
		})
	).value([]),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @review
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * URL for getting a fragment content.
	 * @default undefined
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @review
	 * @type {!string}
	 */
	renderFragmentEntryURL: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @review
	 * @type {!string}
	 */
	spritemap: Config.string().required(),

	/**
	 * Allow opening/closing contextual sidebar
	 * @default true
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @private
	 * @review
	 * @type {boolean}
	 */
	_contextualSidebarVisible: Config.bool()
		.internal()
		.value(true),

	/**
	 * Tabs being shown in sidebar
	 * @default SIDEBAR_TABS
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @private
	 * @review
	 * @type {Array<{
	 * 	 id:string,
	 * 	 name:string,
	 * 	 visible:boolean
	 * }>}
	 */
	_sidebarTabs: Config.arrayOf(
		Config.shapeOf({
			id: Config.string(),
			name: Config.string(),
			visible: Config.bool(),
		})
	)
		.internal()
		.value(SIDEBAR_TABS),

	/**
	 * Tab selected inside sidebar
	 * @default SIDEBAR_TABS[0].id
	 * @instance
	 * @memberOf ContentLayoutEditor
	 * @private
	 * @review
	 * @type {string}
	 */
	_sidebarSelectedTab: Config.oneOf(SIDEBAR_TABS.map(tab => tab.id))
		.internal()
		.value(SIDEBAR_TABS[0].id),
};

Soy.register(ContentLayoutEditor, templates);

export {ContentLayoutEditor};
export default ContentLayoutEditor;
