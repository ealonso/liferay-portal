import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './AceEditor.soy';

/**
 * Constants snippets added to the editor
 * @review
 * @type {!string}
 */

const EDITOR_SNIPPETS = `
scope javascript
snippet fragmentElement
	fragmentElement$0
`;

/**
 * Component that creates an instance of Ace editor
 * to allow code editing.
 * @review
 */

class AceEditor extends Component {

	/**
	 * @inheritDoc
	 * @review
	 */

	attached() {
		this._editorDocument = null;
		this._handleDocumentChanged = this._handleDocumentChanged.bind(this);

		this._loadAceEditor()
			.then(this._loadAceEditorPlugins)
			.then(this._initializeAceEditor)
			.then(this._loadAceEditorSnippets);
	}

	/**
	 * @inheritDoc
	 * @review
	 */

	created() {
		this._initializeAceEditor = this._initializeAceEditor.bind(this);
		this._loadAceEditor = this._loadAceEditor.bind(this);
		this._loadAceEditorPlugins = this._loadAceEditorPlugins.bind(this);
		this._loadAceEditorSnippets = this._loadAceEditorSnippets.bind(this);
	}

	/**
	 * @inheritDoc
	 * @review
	 */

	shouldUpdate() {
		return false;
	}

	/**
	 * Callback executed when the internal Ace editor has been
	 * modified. It simply propagates the event.
	 * @private
	 * @review
	 */

	_handleDocumentChanged() {
		const valid = this._editorSession.getAnnotations().reduce(
			(acc, annotation) => {
				return (!acc || (annotation.type === 'error')) ?
					false : acc;
			},
			true
		);

		this.emit(
			'contentChanged',
			{
				content: this._editorDocument.getValue(),
				valid: valid
			}
		);
	}

	/**
	 * Create AceEditor instance
	 * @param {AUI} A
	 * @private
	 * @review
	 */

	_initializeAceEditor(A) {
		const editor = new A.AceEditor(
			{
				boundingBox: this.refs.wrapper,
				highlightActiveLine: false,
				mode: this.syntax,
				tabSize: 2
			}
		);

		editor.getEditor().setOptions(
			{
				enableBasicAutocompletion: true,
				enableLiveAutocompletion: true
			}
		);

		this._overrideSetAnnotations(editor.getSession());
		this._editorSession = editor.getSession();
		this._editorDocument = editor.getSession().getDocument();

		this.refs.wrapper.style.height = '';
		this.refs.wrapper.style.width = '';

		this._editorDocument.on('change', this._handleDocumentChanged);

		editor.getSession().on('changeAnnotation', this._handleDocumentChanged);

		if (this.initialContent) {
			this._editorDocument.setValue(this.initialContent);
		}
	}

	/**
	 * Load AceEditor AUI dependency
	 * @private
	 * @return {Promise<AUI>}
	 * @review
	 */

	_loadAceEditor() {
		return new Promise(
			resolve => AUI().use('aui-ace-editor', resolve)
		);
	}

	/**
	 * Load all necessary AceEditor plugins
	 * @param {AUI} A
	 * @private
	 * @return {Promise<AUI>}
	 * @review
	 */

	_loadAceEditorPlugins(A) {
		return new Promise(
			resolve => {
				const script = document.createElement('script');

				script.src = `${this.modulePath}/frontend-js-web/aui/aui-ace-editor/ace/ext-language_tools.js`;

				script.addEventListener(
					'load',
					() => {
						ace.require('ace/ext/language_tools');
						resolve(A);
					}
				);

				document.body.appendChild(script);
			}
		);
	}

	/**
	 * Add snippets to the existing editor
	 * @private
	 * @review
	 */

	_loadAceEditorSnippets() {
		const snippetManager = ace.require('ace/snippets').snippetManager;

		snippetManager.register(
			snippetManager.parseSnippetFile(
				EDITOR_SNIPPETS
			)
		);

		if (this.snippets) {
			snippetManager.register(this.snippets);
		}
	}

	/**
	 * Override AceEditor's session setAnnotations method to avoid showing
	 * misleading messages.
	 * @param {Object} session AceEditor session
	 * @private
	 * @review
	 */

	_overrideSetAnnotations(session) {
		const setAnnotations = session.setAnnotations.bind(session);

		session.setAnnotations = annotations => {
			setAnnotations(
				annotations.filter(annotation => annotation.type !== 'info')
			);
		};
	}
}

/**
 * Available AceEditor syntax
 * @review
 * @static
 * @type {Object}
 */

AceEditor.SYNTAX = {
	css: 'css',
	html: 'html',
	javascript: 'javascript'
};

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */

AceEditor.STATE = {

	/**
	 * Initial content sent to the editor
	 * @default ''
	 * @instance
	 * @memberOf AceEditor
	 * @review
	 * @type {string}
	 */

	initialContent: Config.string().value(''),

	/**
	 * Module path for dynamic loading extra JS files
	 * @default undefined
	 * @instance
	 * @memberOf AceEditor
	 * @review
	 * @type {!string}
	 */

	modulePath: Config.string().required(),

	/**
	 * Snippets added to the editor if any
	 * @default ''
	 * @instance
	 * @memberOf AceEditor
	 * @review
	 * @see https://cloud9-sdk.readme.io/docs/snippets
	 * @type {string}
	 */

	snippets: Config.array().value(''),

	/**
	 * Syntax used for the editor.
	 * It will be used for Ace and rendered on the interface.
	 * @default undefined
	 * @instance
	 * @memberOf AceEditor
	 * @review
	 * @see AceEditor.SYNTAX
	 * @type {!string}
	 */

	syntax: Config.oneOf(Object.values(AceEditor.SYNTAX)).required()
};

Soy.register(AceEditor, templates);

export {AceEditor};
export default AceEditor;