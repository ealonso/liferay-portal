AUI.add(
	'liferay-portlet-journal',
	function(A) {
		var D = A.DataType;
		var JSON = A.JSON;
		var Lang = A.Lang;

		var generateInstanceId = function() {
			var instanceId = '';

			var key = Liferay.Portlet.Journal.PROXY.instanceIdKey;

			for (var i = 0; i < 8; i++) {
				var pos = Math.floor(Math.random() * key.length);

				instanceId += key.substring(pos, pos + 1);
			}

			return instanceId;
		};

		var getUID = function() {
			return (++ A.Env._uidx);
		};

		var TPL_EDITOR_ELEMENT = '<div id="{name}" name="{name}"></div>';

		var TPL_FIELD_CONTAINER = '<div><li class="structure-field {cssClass}">' +
				'<span class="journal-article-close"></span>' +
				'<span class="folder">' +
					'<div class="field-container">' +
						'<input class="journal-article-localized" type="hidden" value="false" />' +
						'<div class="journal-article-move-handler"></div>' +
						'<label for="" class="journal-article-field-label"><span>{fieldLabel}</span></label>' +
						'<div class="journal-article-component-container"></div>' +
						'<span class="aui-field aui-field-choice journal-article-localized-checkbox">' +
							'<span class="aui-field-content">' +
								'<span class="aui-field-element aui-field-label-right">' +
									'<input type="hidden" value="false" name="{portletNamespace}{instanceId}localized-checkbox">' +
									'<input type="checkbox" onclick="Liferay.Util.updateCheckboxValue(this); " name="{portletNamespace}{instanceId}localized-checkboxCheckbox" id="{portletNamespace}{instanceId}localized-checkboxCheckbox" class="aui-field-input aui-field-input-choice"> </span>' +
									'<label for="{portletNamespace}{instanceId}localized-checkboxCheckbox" class="aui-field-label">{localizedLabelLanguage}</label>' +
								'</span>' +
							'</span>' +
						'<div class="journal-article-required-message portlet-msg-error">{requiredFieldLanguage}</div>' +
						'<div class="journal-article-buttons {articleButtonsRowCSSClass}">' +
							'<span class="aui-field aui-field-inline aui-field-text journal-article-variable-name">' +
								'<span class="aui-field-content">' +
									'<label for="{portletNamespace}{instanceId}variableName" class="aui-field-label">{variableNameLanguage}</label>' +
									'<span class="aui-field-element ">' +
										'<input type="text" size="25" value="{variableName}" name="{portletNamespace}variableName" id="{portletNamespace}{instanceId}variableName" class="aui-field-input aui-field-input-text">' +
									'</span>' +
								'</span>' +
							'</span>' +
							'{editButtonTemplateHTML}' +
							'{repeatableButtonTemplateHTML}' +
						'</div>' +
					'</div>' +
					'<ul class="folder-droppable"></ul>' +
				'</span>' +
			'</li></div>';

		var TPL_INSTRUCTIONS_CONTAINER = '<div class="journal-article-instructions-container journal-article-instructions-message portlet-msg-info"></div>';

		var TPL_PLACEHOLDER = '<div class="aui-tree-placeholder aui-tree-sub-placeholder"></div>';

		var TPL_STRUCTURE_FIELD_INPUT = '<input class="aui-field-input lfr-input-text" type="text" value="" size="40"/>';

		var TPL_TOOLTIP_IMAGE = '<img align="top" class="journal-article-instructions-container" src="' + themeDisplay.getPathThemeImages() + '/portlet/help.png" />';

		var Journal = function(portletNamespace, articleId) {
			var instance = this;
			
			instance.articleId = articleId;
			instance.timers = {};
			instance.portletNamespace = portletNamespace;

			instance.acceptChildren = true;

			instance._initializeTagsSuggestionContent();
			instance._attachDelegatedEvents();
			instance._attachEvents();
		};

		Journal.prototype = {
			buildHTMLEditor: function(fieldInstance) {
				var instance = this;

				var instanceId = fieldInstance.get('instanceId');
				var name = instance.portletNamespace + 'structure_el_' + instanceId + '_content';

				var editorHTML = Lang.sub(
					TPL_EDITOR_ELEMENT,
					{
						id: name,
						name: name
					}
				);

				fieldInstance.set('innerHTML', editorHTML);

				instance._loadEditor(fieldInstance, name);

				return editorHTML;
			},

			buildHTMLEditorURL: function(fieldInstance) {
				var instance = this;

				var url = Journal.PROXY.editorURL;

				url = url.replace(/LIFERAY_NAME/, A.guid());

				var editorImpl = Journal.PROXY.editorImpl;

				url = url.replace(/LIFERAY_SKIP_EDITOR/, Liferay.Util.isEditorPresent(editorImpl));

				return url;
			},

			downloadArticleContent: function() {
				var instance = this;

				var downloadAction = themeDisplay.getPathMain() + '/journal/get_article_content';
				var auxForm = instance.getPrincipalForm('fm2');

				var articleContent = instance.getArticleContentXML();
				var xmlInput = instance.getByName(auxForm, 'xml', true);

				auxForm.attr('action', downloadAction);
				auxForm.attr('target', '_self');

				xmlInput.val(articleContent);

				submitForm(auxForm, null, false);
			},

			getArticleContentXML: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var structureXSD = A.XML.parse(instance.getByName(form, 'structureXSD').val());

				var xsd = new A.Node(structureXSD);

				var xsdRoot = xsd.one('root');

				var domRoot = A.one('.lfr-ddm-container');

				var buffer = xsdRoot.clone().empty();

				instance.getNodeContentXML(xsdRoot, domRoot, buffer);

				buffer.all('*').each(
					function(item, index, collection) {
						item.removeAttribute('id');
					}
				);

				buffer.removeAttribute('id');

				return A.XML.format(buffer.getDOM());
			},

			getFieldContentXML: function(node, item, buffer, mode) {
				var instance = this;

				var nodeContentBuffer = [];

				var form = instance.getPrincipalForm();

				var fieldName = item.getData('fieldname');
				var fieldNamespace = item.getData('fieldnamespace');

				var fieldInstance = instance.getByName(form, fieldName + fieldNamespace);

				var nodeTypeContent = instance.getNodeTypeContent();
				var typeContent = instance._createDynamicNode(nodeTypeContent, null);

				nodeContentBuffer.push(typeContent.openTag);

				var content = fieldInstance.val() || '';

				nodeContentBuffer.push('<![CDATA[' + content + ']]>');

				nodeContentBuffer.push(typeContent.closeTag);

				var nodeContent = item.one('> div.aui-field-wrapper-content');

				if (nodeContent) {
					var nodexsd = node.clone();
					node.all('dynamic-element').remove();
					instance.getNodeContentXML(nodexsd, nodeContent, node, 'prepend');
				}

				node.append(nodeContentBuffer.join(''));

				if (mode === 'prepend') {
					buffer.prepend(node);
				} else {
					buffer.append(node);
				}
			},

			getNodeContentXML: function(xsdNode, domNode, buffer, mode) {
				var instance = this;

				var elements = xsdNode.all('> dynamic-element');

				elements.each(
					function(item, index, collection) {
						var xsd = item.getDOM();

						var fields = domNode.all('> div.aui-field-wrapper[data-fieldname="' + item.attr('name') + '"]');

						fields.each(
							function(item, index, collection) {
								instance.getFieldContentXML(A.Node(xsd).clone(), item, buffer, mode);
							}
						);
					}
				);
			},

			getById: function(id, namespace) {
				var instance = this;

				return A.one(
					instance._getNamespacedId(id, namespace)
				);
			},

			getByName: function(currentForm, name, withoutNamespace) {
				var instance = this;

				var inputName = withoutNamespace ? name : instance.portletNamespace + name;

				return A.one(currentForm).one('[name=' + inputName + ']');
			},

			getDefaultLocale: function() {
				var instance = this;

				var defaultLocale = instance.getById('defaultLocale');

				if (defaultLocale) {
					defaultLocale = defaultLocale.val();
				}

				return defaultLocale;
			},

			getEditButton: function(source) {
				var instance = this;

				return source.one('.edit-button .aui-button-input');
			},

			getFieldInstance: function(source) {
				var instance = this;

				var id = source.get('id');

				return fieldsDataSet.item(id);
			},

			getGroupId: function() {
				var instance = this;

				var groupId = themeDisplay.getScopeGroupId();

				if (instance.articleId) {
					var form = instance.getPrincipalForm();

					var inputGroupId = instance.getByName(form, 'groupId');
					var inputGroupIdVal = inputGroupId.val();

					if (inputGroupIdVal) {
						groupId = inputGroupIdVal;
					}
				}

				return groupId;
			},

			getSourceByNode: function(node) {
				var instance = this;

				return node.ancestor('li', true);
			},

			getPrincipalForm: function(formName) {
				var instance = this;

				return A.one('form[name=' + instance.portletNamespace + (formName || 'fm1') + ']');
			},

			getNodeTypeContent: function() {
				var instance = this;

				return instance.hasStructure() ? 'dynamic-content' : 'static-content';
			},

			hasStructure: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var structureId = instance.getByName(form, 'structureId');

				return structureId && structureId.val();
			},

			hasTemplate: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var templateId = instance.getByName(form, 'templateId');

				return templateId && templateId.val();
			},

			loadDefaultStructure: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var structureIdInput = instance.getByName(form, 'structureId');
				var templateIdInput = instance.getByName(form, 'templateId');
				var contentInput = instance.getByName(form, 'content');

				structureIdInput.val('');
				templateIdInput.val('');
				contentInput.val('');

				submitForm(form, null, false, false);
			},

			normalizeValue: function(value) {
				var instance = this;

				if (Lang.isUndefined(value)) {
					value = '';
				}

				return value;
			},

			openPopupWindow: function(url, title, id) {
				var instance = this;

				Liferay.Util.openWindow(
					{
						dialog: {
							align: Liferay.Util.Window.ALIGN_CENTER,
							width: 680
						},
						id: instance.portletNamespace + id,
						title: title,
						uri: url
					}
				);
			},

			previewArticle: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var auxForm = instance.getPrincipalForm('fm2');
				var articleContent = instance.getArticleContentXML();

				if (instance.hasStructure() && !instance.hasTemplate() && !instance.updateStructureDefaultValues()) {
					var templateMessage = Liferay.Language.get('please-add-a-template-to-render-this-structure');

					alert(templateMessage);

					instance.showMessage(
						'#selectTemplateMessage',
						'info',
						templateMessage,
						30000
					);

					var selectTemplateButton = instance.getById('selectTemplateButton');

					if (selectTemplateButton) {
						selectTemplateButton.focus();
					}
				}
				else {
					var defaultLocale = instance.getDefaultLocale();
					var typeInput = instance.getByName(form, 'type');
					var versionInput = instance.getByName(form, 'version');
					var structureIdInput = instance.getByName(form, 'structureId');
					var templateIdInput = instance.getByName(form, 'templateId');

					var previewURL = themeDisplay.getPathMain() + '/journal/view_article_content?cmd=preview&groupId=' + instance.getGroupId() + '&articleId=' + instance.articleId + '&version=' + versionInput.val() + '&languageId=' + defaultLocale + '&type=' + typeInput.val() + '&structureId=' + structureIdInput.val() + '&templateId=' + templateIdInput.val();

					auxForm.attr('action', previewURL);
					auxForm.attr('target', '_blank');

					var titleInput = instance.getByName(form, 'title_' + defaultLocale);
					var titleAuxFormInput = instance.getByName(auxForm, 'title', true);
					var xmlAuxFormInput = instance.getByName(auxForm, 'xml', true);

					titleAuxFormInput.val(titleInput.val());
					xmlAuxFormInput.val(articleContent);

					submitForm(auxForm, null, false);
				}
			},

			saveArticle: function(cmd) {
				var instance = this;

				var form = instance.getPrincipalForm();

				if (instance.hasStructure() && !instance.hasTemplate() && !instance.updateStructureDefaultValues()) {
					var templateMessage = Liferay.Language.get('please-add-a-template-to-render-this-structure');

					alert(templateMessage);

					instance.showMessage(
						'#selectTemplateMessage',
						'info',
						templateMessage,
						30000
					);

					var selectTemplateButton = instance.getById('selectTemplateButton');

					if (selectTemplateButton) {
						selectTemplateButton.focus();
					}
				}
				else {
					if (!cmd) {
						cmd = instance.articleId ? 'update' : 'add';
					}

					var articleIdInput = instance.getByName(form, 'articleId');
					var classNameIdInput = instance.getByName(form, 'classNameId');
					var cmdInput = instance.getByName(form, 'cmd');
					var contentInput = instance.getByName(form, 'content');
					var newArticleIdInput = instance.getByName(form, 'newArticleId');
					var workflowActionInput = instance.getByName(form, 'workflowAction');

					var classNameId = Liferay.Util.toNumber(classNameIdInput.val());

					if (cmd == 'publish') {
						workflowActionInput.val(Liferay.Workflow.ACTION_PUBLISH);

						cmd = instance.articleId ? 'update' : 'add';
					}

					cmdInput.val(cmd);

					if (!instance.articleId) {
						articleIdInput.val(newArticleIdInput.val());
					}

					var content = instance.getArticleContentXML();

					contentInput.val(content);

					submitForm(form);
				}
			},

			showMessage: function(selector, type, message, delay) {
				var instance = this;

				var journalMessage = A.one(selector);
				var className = 'save-structure-message portlet-msg-' + (type || 'success');

				journalMessage.attr('className', className);
				journalMessage.show();

				if (message) {
					journalMessage.html(message);
				}

				instance.timers[selector] = A.later(
					delay || 5000,
					instance,
					function() {
						journalMessage.hide();
					}
				);
			},

			translateArticle: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var cmdInput = instance.getByName(form, 'cmd');

				cmdInput.val('translate');

				var contentInput = instance.getByName(form, 'content');

				var content = instance.getArticleContentXML();

				contentInput.val(content);

				submitForm(form);
			},

			_attachDelegatedEvents: function() {
				var instance = this;

				var container = instance.getById('journalArticleContainer');

				container.delegate(
					'click',
					function(event) {
						var checkbox = event.currentTarget;
						var source = instance.getSourceByNode(checkbox);

						instance._updateLocaleState(source, checkbox);
					},
					'.journal-article-localized-checkbox .aui-field-input-choice'
				);

				container.delegate(
					'click',
					function(event) {
						var button = event.currentTarget;
						var buttonValue = null;
						var imagePreview = button.ancestor('.journal-image-preview');
						var imageWrapper = imagePreview.one('.journal-image-wrapper');
						var imageDelete = instance.getByName(imagePreview, 'journalImageDelete');

						if (imageDelete.val() == '') {
							imageDelete.val('delete');
							imageWrapper.hide();

							buttonValue = Liferay.Language.get('cancel');
						}
						else {
							imageDelete.val('');
							imageWrapper.show();

							buttonValue = Liferay.Language.get('delete');
						}

						button.val(buttonValue);
					},
					'#' + instance.portletNamespace + 'journalImageDeleteButton'
				);

				container.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;
						var imagePreviewDiv = link.get('parentNode').get('parentNode').one('.journal-image-preview');

						var showLabel = link.one('.show-label').show();
						var hideLabel = link.one('.hide-label').show();

						var visible = imagePreviewDiv.hasClass('aui-helper-hidden');

						if (visible) {
							showLabel.hide();
							hideLabel.show();
						}
						else {
							showLabel.show();
							hideLabel.hide();
						}

						imagePreviewDiv.toggle();
					},
					'.journal-image-link'
				);

				container.delegate(
					'click',
					function(event) {
						var button = event.currentTarget;
						var input = button.ancestor('.journal-article-component-container').one('.aui-field-input');
						var selectUrl = button.attr('data-documentlibraryUrl');

						window[instance.portletNamespace + 'selectDocumentLibrary'] = function(url) {
							input.val(url);
						};

						instance.openPopupWindow(selectUrl, Liferay.Language.get('javax.portlet.title.20'), 'selectDocumentLibrary');
					},
					'.journal-documentlibrary-button .aui-button-input'
				);

				container.delegate(
					'mouseover',
					function(event) {
						var image = event.currentTarget;
						var source = instance.getSourceByNode(image);
						var fieldInstance = instance.getFieldInstance(source);

						if (fieldInstance) {
							var instructions = fieldInstance.get('instructions');

							Liferay.Portal.ToolTip.show(this, Liferay.Util.escapeHTML(instructions));
						}
					},
					'img.journal-article-instructions-container'
				);

				instance._attachDelegatedEvents = Lang.emptyFn;
			},

			_attachEvents: function() {
				var instance = this;

				var changeStructureButton = instance.getById('changeStructureButton');
				var downloadArticleContentButton = instance.getById('downloadArticleContentButton');
				var loadDefaultStructureButton = instance.getById('loadDefaultStructure');
				var previewArticleButton = instance.getById('previewArticleButton');
				var publishButton = instance.getById('publishButton');
				var saveButton = instance.getById('saveButton');
				var translateButton = instance.getById('translateButton');

				if (changeStructureButton) {
					changeStructureButton.detach('click');

					changeStructureButton.on(
						'click',
						function(event) {
							event.preventDefault();

							var url = event.currentTarget.attr('href');

							instance.openPopupWindow(url, 'ChangeStructure', 'changeStruture');
						}
					);
				}

				if (downloadArticleContentButton) {
					downloadArticleContentButton.detach('click');

					downloadArticleContentButton.on(
						'click',
						function() {
							instance.downloadArticleContent();
						}
					);
				}

				if (loadDefaultStructureButton) {
					loadDefaultStructureButton.detach('click');

					loadDefaultStructureButton.on(
						'click',
						function() {
							instance.loadDefaultStructure();
						}
					);
				}

				if (previewArticleButton) {
					previewArticleButton.detach('click');

					previewArticleButton.on(
						'click',
						function() {
							instance.previewArticle();
						}
					);
				}
			},

			_createDynamicNode: function(nodeName, attributeMap) {
				var instance = this;

				var attrs = [];
				var typeElement = [];

				if (!nodeName) {
					nodeName = 'dynamic-element';
				}

				var typeElementModel = ['<', nodeName, (attributeMap ? ' ' : ''), , '>', ,'</', nodeName, '>'];

				A.each(
					attributeMap || {},
					function(item, index, collection) {
						if (item !== undefined) {
							attrs.push([index, '="', item, '" '].join(''));
						}
					}
				);

				typeElementModel[3] = attrs.join('').replace(/[\s]+$/g, '');
				typeElement = typeElementModel.join('').replace(/></, '>><<').replace(/ +>/, '>').split(/></);

				return {
					closeTag: typeElement[1],
					openTag: typeElement[0]
				};
			},

			_createFieldHTMLTemplate: function(field) {
				var instance = this;

				var fieldContainer = field.getFieldContainer();
				var fieldElementContainer = field.getFieldElementContainer();
				var innerHTML = field.get('innerHTML');
				var type = field.get('fieldType');

				fieldElementContainer.html(innerHTML);

				return fieldContainer.html();
			},

			_getNamespacedId: function(id, namespace, prefix) {
				var instance = this;

				if (!Lang.isString(namespace)) {
					namespace = instance.portletNamespace;
				}

				if (!Lang.isString(prefix)) {
					prefix = '#';
				}

				id = id.replace(/^#/, '');

				return prefix + namespace + id;
			},

			_initializeTagsSuggestionContent: function() {
				var instance = this;

				window[instance.portletNamespace + 'getSuggestionsContent'] = function() {
					var content = [];

					instance.getFields().each(
						function(item, index, collection) {
							var fieldInstance = instance.getFieldInstance(item);
							var fieldContent = fieldInstance.getContent(item);

							content.push(fieldContent);
						}
					);

					return content.join(' ');
				};
			},

			_loadEditor: function(fieldInstance, editorId) {
				var instance = this;

				var url = instance.buildHTMLEditorURL(fieldInstance);

				A.io.request(
					url,
					{
						method: 'GET',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var editorNode = A.one('#' + editorId);

								editorNode.plug(A.Plugin.ParseContent);

								editorNode.setContent(response);
							}
						}
					}
				);
			},

			_stripComponentType: function(type) {
				return type.toLowerCase().replace(/[^a-z]+/g, '');
			},

			_translateErrorMessage: function(exception) {
				var errorText = '';

				if (exception.indexOf('StructureXsdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-xsd');
				}
				else if (exception.indexOf('DuplicateStructureElementException') > -1) {
					errorText = Liferay.Language.get('please-enter-unique-structure-field-names-(including-field-names-inherited-from-the-parent-structure)');
				}
				else if (exception.indexOf('DuplicateStructureIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-unique-id');
				}
				else if (exception.indexOf('StructureDescriptionException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-description');
				}
				else if (exception.indexOf('StructureIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('StructureInheritanceException') > -1) {
					errorText = Liferay.Language.get('this-structure-is-already-within-the-inheritance-path-of-the-selected-parent-please-select-another-parent-structure');
				}
				else if (exception.indexOf('StructureNameException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-name');
				}
				else if (exception.indexOf('NoSuchStructureException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('ArticleContentException') > -1) {
					errorText = Liferay.Language.get('please-enter-valid-content');
				}
				else if (exception.indexOf('ArticleIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('ArticleTitleException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-name');
				}
				else if (exception.indexOf('DuplicateArticleIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-unique-id');
				}

				return errorText;
			},

			_updateLocaleState: function(source, checkbox) {
				var instance = this;

				var isLocalized = checkbox.get('checked');
				var defaultLocale = instance.getDefaultLocale();
				var localizedValue = source.one('.journal-article-localized');

				var selectedLocale = defaultLocale;

				var setLocalizedValue = function(value) {
					if (localizedValue) {
						localizedValue.val(value);
					}
				};

				if (isLocalized) {
					setLocalizedValue(selectedLocale);
				}
				else if (!confirm(Liferay.Language.get('unchecking-this-field-will-remove-localized-data-for-languages-not-shown-in-this-view'))) {
					checkbox.attr('checked', true);

					setLocalizedValue(selectedLocale);
				}
				else {
					setLocalizedValue(false);
				}

				var fieldInstance = instance.getFieldInstance(source);

				fieldInstance.set('localized', checkbox.get('checked'));

				fieldInstance.setInstanceId(fieldInstance.get('instanceId'));
			}
		};

		A.augment(Journal, A.EventTarget);

		Liferay.Portlet.Journal = Journal;
	},
	'',
	{
		requires: ['aui-base', 'aui-data-schema', 'aui-data-set', 'aui-datatype', 'aui-dialog', 'aui-dialog-iframe', 'aui-io-request', 'aui-nested-list', 'aui-overlay-context-panel', 'json']
	}
);