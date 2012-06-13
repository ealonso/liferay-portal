AUI.add(
	'liferay-folders-navigation',
	function(A) {
		var AObject = A.Object;
		var History = Liferay.HistoryManager;
		var Lang = A.Lang;
		var QueryString = A.QueryString;

		var isValue = Lang.isValue;
		var owns = AObject.owns;

		var UA = A.UA;

		var WIN = A.config.win;

		var LOCATION = WIN.location;

		var ARTICLE_DRAGGABLE = '[data-draggable]';

		var ATTR_CHECKED = 'checked';

		var CSS_ACTIVE_AREA = 'active-area';

		var CSS_ACTIVE_AREA_PROXY = 'active-area-proxy';

		var CSS_ARTICLE_DISPLAY_STYLE = '.article-display-style';

		var CSS_ARTICLE_DISPLAY_STYLE_SELECTABLE = '.article-display-style.selectable';

		var CSS_ARTICLE_DISPLAY_STYLE_SELECTED = '.article-display-style.selected';

		var CSS_RESULT_ROW = '.results-row';

		var CSS_SELECTED = 'selected';

		var DATA_FOLDER_ID = 'data-folder-id';

		var DISPLAY_STYLE_LIST = 'list';

		var DISPLAY_STYLE_TOOLBAR = 'displayStyleToolbar';

		var JOURNAL_GROUP = 'journal';

		var ROWS_PER_PAGE = 'rowsPerPage';

		var STR_BLANK = '';

		var STR_DATA = 'data';

		var STR_DRAG_NODE = 'dragNode';

		var STR_ROW_IDS_JOURNAL_FOLDER_CHECKBOX = 'rowIdsJournalFolderCheckbox';

		var STR_ROW_IDS_JOURNAL_ARTICLE_CHECKBOX = 'rowIdsJournalArticleCheckbox';

		var STR_TOGGLE_ACTIONS_BUTTON = 'toggleActionsButton';

		var TOUCH = UA.touch;

		var FoldersNavigation = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'liferayfoldersnavigation',

				prototype: {
					initializer: function(config) {
						var instance = this;

						var portletContainer = instance.byId(config.portletContainerId);

						instance._config = config;

						debugger;

						instance._actions = config.actions;

						instance._allRowIds = config.allRowIds;

						instance._editEntryUrl = config.editEntryUrl;

						instance._listView = config.listView;

						instance._moveEntryRenderUrl = config.moveEntryRenderUrl;

						instance._portletContainer = portletContainer;

						instance._portletGroup = config.portletGroup;

						instance._displayStyle = instance.ns('displayStyle');

						instance._displayStyleToolbar = instance.byId(config.displayStyleToolbarId);

						instance._displayViews = config.displayViews;

						instance._entriesContainer = instance.byId('entriesContainer');

						instance._folderIdHashRegEx = config.folderIdHashRegEx;

						instance._form = config.form;

						instance._rowIds = config.rowIds;

						instance._selectAllCheckbox = instance.byId('allRowIdsCheckbox');

						if (themeDisplay.isSignedIn()) {
							instance._initDragDrop();
						}

					},

					destructor: function() {
						var instance = this;

						instance._ddHandler.destroy();

						A.Array.invoke(instance._eventHandles, 'detach');
					},

					_editArticle: function(event) {
						var instance = this;

						var action = event.action;

						var url = instance._editEntryUrl;

						if (action == instance._actions.MOVE) {
							url = instance._moveEntryRenderUrl;
						}

						instance._processArticleAction(action, url);
					},

					_getDisplayStyle: function(currentDisplayStyle, style) {
						var instance = this;

						var displayStyle = History.get(currentDisplayStyle) || instance._config.displayStyle;

						if (style) {
							displayStyle = (displayStyle == style);
						}

						return displayStyle;
					},

					_getMoveText: function(selectedItemsCount, targetAvailable) {
						var moveText = STR_BLANK;

						if (targetAvailable) {
							moveText = Liferay.Language.get('x-item-is-ready-to-be-moved-to-x');

							if (selectedItemsCount > 1) {
								moveText = Liferay.Language.get('x-items-are-ready-to-be-moved-to-x');
							}
						}
						else {
							moveText = Liferay.Language.get('x-item-is-ready-to-be-moved');

							if (selectedItemsCount > 1) {
								moveText = Liferay.Language.get('x-items-are-ready-to-be-moved');
							}
						}

						return moveText;
					},

					_getResultsStartEnd: function(paginator, page, rowsPerPage) {
						var instance = this;

						if (!Lang.isValue(page)) {
							page = 0;

							var curPage = paginator.get('page') - 1;

							if (curPage > 0) {
								page = curPage;
							}
						}

						if (!Lang.isValue(rowsPerPage)) {
							rowsPerPage = paginator.get(ROWS_PER_PAGE);
						}

						var start = page * rowsPerPage;
						var end = start + rowsPerPage;

						return [start, end];
					},

					_initDragDrop: function() {
						var instance = this;

						var ddHandler = new A.DD.Delegate(
							{
								container: instance._portletContainer,
								nodes: ARTICLE_DRAGGABLE,
								on: {
									'drag:drophit': A.bind(instance._onDragDropHit, instance),
									'drag:enter': A.bind(instance._onDragEnter, instance),
									'drag:exit': A.bind(instance._onDragExit, instance),
									'drag:start': A.bind(instance._onDragStart, instance)
								}
							}
						);

						var dd = ddHandler.dd;

						dd.set('offsetNode', false);

						dd.removeInvalid('a');

						dd.set('groups', [JOURNAL_GROUP]);

						dd.plug(
							[
								{
									cfg: {
										moveOnEnd: false
									},
									fn: A.Plugin.DDProxy
								},
								{
									cfg: {
										constrain2node: instance._portletContainer
									},
									fn: A.Plugin.DDConstrained
								}
							]
						);

						if (TOUCH) {
							instance._dragTask = A.debounce(
								function(entryLink) {
									if (entryLink) {
										entryLink.simulate('click');
									}
								},
								A.DD.DDM.get('clickTimeThresh')
							);

							dd.after(
								'afterMouseDown',
								function(event) {
									instance._dragTask(event.target.get('node').one('.article-link'));
								},
								instance
							);
						}

						instance._initDropTargets();

						instance._ddHandler = ddHandler;
					},

					_initDropTargets: function() {
						var instance = this;

						if (themeDisplay.isSignedIn()) {
							var items = instance._portletContainer.all('[data-folder="true"]');

							items.each(
								function(item, index, collection) {
									item.plug(
										A.Plugin.Drop,
										{
											groups: [instance._portletGroup],
											padding: '-1px'
										}
									);
								}
							);
						}
					},

					_moveEntries: function(folderId) {
						var instance = this;

						var form = instance._form.node;

						form.get(instance.ns('newFolderId')).val(folderId);

						var config = instance._config;

						instance._processArticleAction(config.moveConstant, config.moveEntryRenderUrl);
					},

					_onDragDropHit: function(event) {
						var instance = this;

						var proxyNode = event.target.get(STR_DRAG_NODE);

						proxyNode.removeClass(CSS_ACTIVE_AREA_PROXY);

						proxyNode.empty();

						var dropTarget = event.drop.get('node');

						var folderId = dropTarget.attr(DATA_FOLDER_ID);

						var folderContainer = dropTarget.ancestor('.article-display-style');

						var selectedItems = instance._ddHandler.dd.get(STR_DATA).selectedItems;

						if (selectedItems.indexOf(folderContainer) == -1) {
							instance._moveEntries(folderId);
						}
					},

					_onDragEnter: function(event) {
						var instance = this;

						var dragNode = event.drag.get('node');
						var dropTarget = event.drop.get('node');

						dropTarget = dropTarget.ancestor(CSS_ARTICLE_DISPLAY_STYLE) || dropTarget;

						if (!dragNode.compareTo(dropTarget)) {
							dropTarget.addClass(CSS_ACTIVE_AREA);

							var proxyNode = event.target.get(STR_DRAG_NODE);

							var dd = instance._ddHandler.dd;

							var selectedItemsCount = dd.get(STR_DATA).selectedItemsCount;

							var moveText = instance._getMoveText(selectedItemsCount, true);

							var itemTitle = Lang.trim(dropTarget.attr('data-title'));

							proxyNode.html(Lang.sub(moveText, [selectedItemsCount, itemTitle]));
						}
					},

					_onDragExit: function(event) {
						var instance = this;

						var dropTarget = event.drop.get('node');

						dropTarget = dropTarget.ancestor(CSS_ARTICLE_DISPLAY_STYLE) || dropTarget;

						dropTarget.removeClass(CSS_ACTIVE_AREA);

						var proxyNode = event.target.get(STR_DRAG_NODE);

						var selectedItemsCount = instance._ddHandler.dd.get(STR_DATA).selectedItemsCount;

						var moveText = instance._getMoveText(selectedItemsCount);

						proxyNode.html(Lang.sub(moveText, [selectedItemsCount]));
					},

					_onDragStart: function(event) {
						var instance = this;

						if (instance._dragTask) {
							instance._dragTask.cancel();
						}

						var target = event.target;

						var node = target.get('node');

						if (!node.hasClass(CSS_SELECTED)) {
							instance._unselectAllEntries();

							instance._toggleSelected(node);
						}

						var proxyNode = target.get(STR_DRAG_NODE);

						proxyNode.setStyles(
							{
								height: STR_BLANK,
								width: STR_BLANK
							}
						);

						var selectedItems = instance._entriesContainer.all(CSS_ARTICLE_DISPLAY_STYLE_SELECTED);

						var selectedItemsCount = selectedItems.size();

						var moveText = instance._getMoveText(selectedItemsCount);

						proxyNode.html(Lang.sub(moveText, [selectedItemsCount]));

						proxyNode.addClass(CSS_ACTIVE_AREA_PROXY);

						var dd = instance._ddHandler.dd;

						dd.set(
							STR_DATA,
							{
								selectedItemsCount: selectedItemsCount,
								selectedItems: selectedItems
							}
						);
					},

					_processArticleAction: function(action, url) {
						var instance = this;

						var form = instance._form.node;

						var redirectUrl = location.href;

						if (action === instance._actions.DELETE && !History.HTML5 && location.hash) {
							redirectUrl = instance._updateFolderIdRedirectUrl(redirectUrl);
						}

						form.attr('method', instance._form.method);

						form.get(instance.ns('cmd')).val(action);
						form.get(instance.ns('redirect')).val(redirectUrl);

						var allRowIds = instance._allRowIds;
						var rowIds = instance._rowIds;

						var allRowsIdCheckbox = instance.ns(allRowIds + 'Checkbox');

						var journalFolderIds = Liferay.Util.listCheckedExcept(form, allRowsIdCheckbox, instance.ns(rowIds + 'JournalFolderCheckbox'));
						var journalArticleIds = Liferay.Util.listCheckedExcept(form, allRowsIdCheckbox, instance.ns(rowIds + 'JournalArticleCheckbox'));

						form.get(instance.ns('folderIds')).val(journalFolderIds);
						form.get(instance.ns('articleIds')).val(journalArticleIds);

						submitForm(form, url);
					},

					_setBreadcrumb: function(content) {
						var instance = this;

						var breadcrumb = instance.one('#breadcrumb', content);

						if (breadcrumb) {
							var breadcrumbContainer;

							var journalBreadcrumb = breadcrumb.one('.portlet-breadcrumb ul');

							if (journalBreadcrumb) {
								breadcrumbContainer = instance.byId('breadcrumbContainer');

								breadcrumbContainer.setContent(journalBreadcrumb);
							}

							var portalBreadcrumb = breadcrumb.one('.portal-breadcrumb ul');

							if (portalBreadcrumb) {
								breadcrumbContainer = A.one('#breadcrumbs ul');

								if (breadcrumbContainer) {
									breadcrumbContainer.setContent(portalBreadcrumb.html());
								}
							}
						}
					},

					_setButtons: function(content) {
						var instance = this;

						var addButton = instance.one('#addButton', content);

						if (addButton) {
							var addButtonContainer = instance.byId('addButtonContainer');

							addButtonContainer.plug(A.Plugin.ParseContent);

							addButtonContainer.setContent(addButton);
						}

						var displayStyleButtons = instance.one('#displayStyleButtons', content);

						if (displayStyleButtons) {
							instance._displayStyleToolbar.empty();

							var displayStyleButtonsContainer = instance.byId('displayStyleButtonsContainer');

							displayStyleButtonsContainer.plug(A.Plugin.ParseContent);

							displayStyleButtonsContainer.setContent(displayStyleButtons);
						}

						var sortButton = instance.one('#sortButton', content);

						if (sortButton) {
							var sortButtonContainer = instance.byId('sortButtonContainer');

							sortButtonContainer.plug(A.Plugin.ParseContent);

							sortButtonContainer.setContent(sortButton);
						}
					},

					_setFolders: function(content) {
						var instance = this;

						var folders = instance.one('#folderContainer', content);

						if (folders) {
							var listViewDataContainer = A.one('.lfr-list-view-data-container');

							listViewDataContainer.plug(A.Plugin.ParseContent);

							instance._listView.set(STR_DATA, folders.html());
						}
					},

					_setParentFolderTitle: function(content) {
						var instance = this;

						var parentFolderTitle = instance.one('#parentFolderTitle', content);

						if (parentFolderTitle) {
							var parentFolderTitleContainer = instance.byId('parentFolderTitleContainer');

							parentFolderTitleContainer.setContent(parentFolderTitle);
						}
					},

					_syncDisplayStyleToolbar: function(content) {
						var instance = this;

						var displayViews = instance._displayViews;

						var length = displayViews.length;

						if (length > 1) {
							var displayStyleToolbar = instance._displayStyleToolbar.getData(DISPLAY_STYLE_TOOLBAR);

							var displayStyle = instance._getDisplayStyle();

							for (var i = 0; i < length; i++) {
								displayStyleToolbar.item(i).StateInteraction.set(STR_ACTIVE, displayStyle === displayViews[i]);
							}
						}
					},

					_toggleEntriesSelection: function() {
						var instance = this;

						var selectAllCheckbox = instance._selectAllCheckbox;

						Liferay.Util.checkAll(instance._portletContainer, instance.ns(STR_ROW_IDS_JOURNAL_FOLDER_CHECKBOX), selectAllCheckbox, CSS_RESULT_ROW);
						Liferay.Util.checkAll(instance._portletContainer, instance.ns(STR_ROW_IDS_JOURNAL_ARTICLE_CHECKBOX), selectAllCheckbox, CSS_RESULT_ROW);

						WIN[instance.ns(STR_TOGGLE_ACTIONS_BUTTON)]();

						if (!instance._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
							var articleDisplayStyle = A.all(CSS_ARTICLE_DISPLAY_STYLE_SELECTABLE);

							articleDisplayStyle.toggleClass(CSS_SELECTED, instance._selectAllCheckbox.attr(ATTR_CHECKED));
						}
					},

					_toggleSelected: function(node, preventUpdate) {
						var instance = this;

						if (instance._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
							if (!preventUpdate) {
								var input = node.one('input') || node;

								input.attr(ATTR_CHECKED, !node.attr(ATTR_CHECKED));
							}
						}
						else {
							node = node.ancestor(CSS_ARTICLE_DISPLAY_STYLE) || node;

							if (!preventUpdate) {
								var selectElement = node.one('.article-selector :checkbox');

								selectElement.attr(ATTR_CHECKED, !selectElement.attr(ATTR_CHECKED));

								Liferay.Util.updateCheckboxValue(selectElement);
							}
						}

						node.toggleClass(CSS_SELECTED);
					},

					_unselectAllEntries: function() {
						var instance = this;

						instance._selectAllCheckbox.attr(CSS_SELECTED, false);

						instance._toggleEntriesSelection();
					},

					_updateFolderIdRedirectUrl: function(redirectUrl) {
						var instance = this;

						var currentFolderMatch = instance._folderIdHashRegEx.exec(redirectUrl);

						if (currentFolderMatch) {
							var currentFolderId = currentFolderMatch[1];

							redirectUrl = redirectUrl.replace(
								config.folderIdRegEx,
								function(match, folderId) {
									return match.replace(folderId, currentFolderId);
								}
							);
						}

						return redirectUrl;
					}


				},

				PAIR_SEPARATOR: '&',

				VALUE_SEPARATOR: '='
			}
		);

		Liferay.FoldersNavigation = FoldersNavigation;
	},
	'',
	{
		requires: ['aui-base', 'aui-parse-content', 'dd-constrain', 'dd-delegate', 'dd-drag', 'dd-drop', 'dd-proxy', 'liferay-history-manager', 'liferay-portlet-base', 'liferay-util-list-fields']
	}
);