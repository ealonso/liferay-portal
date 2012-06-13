AUI.add(
	'liferay-journal-navigation',
	function(A) {
		var AObject = A.Object;
		var History = Liferay.HistoryManager;
		var FoldersNavigation = Liferay.FoldersNavigation;
		var Lang = A.Lang;
		var QueryString = A.QueryString;

		var formatSelectorNS = A.Node.formatSelectorNS;

		var owns = AObject.owns;

		var UA = A.UA;

		var PAIR_SEPARATOR = History.PAIR_SEPARATOR;

		var VALUE_SEPARATOR = History.VALUE_SEPARATOR;

		var ATTR_CHECKED = 'checked';

		var CSS_ACTIVE_AREA = 'active-area';

		var CSS_ACTIVE_AREA_PROXY = 'active-area-proxy';

		var CSS_ARTICLE_DISPLAY_STYLE = '.article-display-style';

		var CSS_ARTICLE_DISPLAY_STYLE_SELECTABLE = '.article-display-style.selectable';

		var CSS_ARTICLE_DISPLAY_STYLE_SELECTED = '.article-display-style.selected';

		var CSS_RESULT_ROW = '.results-row';

		var CSS_SELECTED = 'selected';

		var DATA_DIRECTION_RIGHT = 'data-direction-right';

		var DATA_FOLDER_ID = 'data-folder-id';

		var DATA_VIEW_ENTRIES = 'data-view-entries';

		var DATA_VIEW_FOLDERS = 'data-view-folders';

		var DEFAULT_FOLDER_ID = 0;

		var DISPLAY_STYLE_LIST = 'list';

		var DISPLAY_STYLE_TOOLBAR = 'displayStyleToolbar';

		var ARTICLE_DRAGGABLE = '[data-draggable]';

		var JOURNAL_GROUP = 'journal';

		var EXPAND_FOLDER = 'expandFolder';

		var MESSAGE_TYPE_ERROR = 'error';

		var ROWS_PER_PAGE = 'rowsPerPage';

		var SEARCH_TYPE = 'searchType';

		var SEARCH_TYPE_SINGLE = 1;

		var STR_ACTIVE = 'active';

		var STR_AJAX_REQUEST = 'ajax';

		var STR_BLANK = '';

		var STR_CLICK = 'click';

		var STR_DATA = 'data';

		var STR_DRAG_NODE = 'dragNode';

		var STR_ENTRY_END = 'entryEnd';

		var STR_ENTRY_START = 'entryStart';

		var STR_FOCUS = 'focus';

		var STR_FOLDER_CONTAINER = 'folderContainer';

		var STR_FOLDER_END = 'folderEnd';

		var STR_FOLDER_ID = 'folderId';

		var STR_FOLDER_START = 'folderStart';

		var STR_KEYWORDS = 'keywords';

		var STR_TOGGLE_ACTIONS_BUTTON = 'toggleActionsButton';

		var STR_ROW_IDS_JOURNAL_FOLDER_CHECKBOX = 'rowIdsJournalFolderCheckbox';

		var STR_ROW_IDS_JOURNAL_ARTICLE_CHECKBOX = 'rowIdsJournalArticleCheckbox';

		var STR_SEARCH_FOLDER_ID = 'searchFolderId';

		var STR_SEARCH_RESULTS_CONTAINER = 'searchResultsContainer';

		var STR_SHOW_SEARCH_INFO = 'showSearchInfo';

		var STRUTS_ACTION = 'struts_action';

		var SRC_DISPLAY_STYLE_BUTTONS = 0;

		var SRC_ENTRIES_PAGINATOR = 1;

		var SRC_HISTORY = 2;

		var SRC_RESTORE_STATE = 4;

		var SRC_SEARCH = 3;

		var SRC_SEARCH_FRAGMENT = 2;

		var TOUCH = UA.touch;

		var TPL_MESSAGE_RESPONSE = '<div class="lfr-message-response" />';

		var TPL_MESSAGE_SEARCHING = '<div class="portlet-msg-info">{0}</div><div class="loading-animation" />';

		var VIEW_ENTRIES = 'viewEntries';

		var VIEW_ENTRIES_PAGE = 'viewEntriesPage';

		var VIEW_FOLDERS = 'viewFolders';

		var WIN = A.config.win;

		Liferay.JOURNAL_DISPLAY_STYLE_BUTTONS = SRC_DISPLAY_STYLE_BUTTONS;

		Liferay.JOURNAL_ENTRIES_PAGINATOR = SRC_ENTRIES_PAGINATOR;

		Liferay.JOURNAL_HISTORY = SRC_HISTORY;

		Liferay.JOURNAL_SEARCH = SRC_SEARCH;

		Liferay.JOURNAL_SEARCH_FRAGMENT = SRC_SEARCH_FRAGMENT;

		var JournalNavigation = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'journalnavigation',

				prototype: {
					initializer: function(config) {
						var instance = this;

						var journalContainer = instance.byId('journalContainer');

						instance._journalContainer = journalContainer;

						instance._dataRetrieveFailure = instance.ns('dataRetrieveFailure');
						instance._eventDataRequest = instance.ns('dataRequest');
						instance._eventDataRetrieveSuccess = instance.ns('dataRetrieveSuccess');
						instance._eventEditArticle = instance.ns('editArticle');
						instance._eventOpenAdvancedSearch = instance.ns('openAdvancedSearch');
						instance._eventPageLoaded = instance.ns('pageLoaded');
						instance._eventChangeSearchFolder = instance.ns('changeSearchFolder');

						instance._displayStyleToolbarNode = instance.byId(DISPLAY_STYLE_TOOLBAR);
						instance._entriesContainer = instance.byId('entriesContainer');

						instance._selectAllCheckbox = instance.byId('allRowIdsCheckbox');

						instance._portletMessageContainer = A.Node.create(TPL_MESSAGE_RESPONSE);

						instance._displayStyle = instance.ns('displayStyle');
						instance._folderId = instance.ns(STR_FOLDER_ID);

						instance._keywordsNode = instance.byId(STR_KEYWORDS);

						var entryPage = 0;

						if (config.entriesTotal > 0) {
							entryPage = config.entryEnd / config.entryRowsPerPage;
						}

						var entryPaginator = new A.Paginator(
							{
								circular: false,
								containers: '.article-entries-paginator',
								firstPageLinkLabel: '&lt;&lt;',
								lastPageLinkLabel: '&gt;&gt;',
								nextPageLinkLabel: '&gt;',
								page: entryPage,
								prevPageLinkLabel: '&lt;',
								rowsPerPage: config.entryRowsPerPage,
								rowsPerPageOptions: config.entryRowsPerPageOptions,
								total: config.entriesTotal
							}
						).render();

						entryPaginator.on('changeRequest', instance._onEntryPaginatorChangeRequest, instance);

						var folderPage = 0;

						if (config.foldersTotal > 0) {
							folderPage = config.folderEnd / config.folderRowsPerPage;
						}

						var folderPaginator = new A.Paginator(
							{
								alwaysVisible: false,
								circular: false,
								containers: '.folder-paginator',
								firstPageLinkLabel: '&lt;&lt;',
								lastPageLinkLabel: '&gt;&gt;',
								nextPageLinkLabel: '&gt;',
								page: folderPage,
								prevPageLinkLabel: '&lt;',
								rowsPerPage: config.folderRowsPerPage,
								rowsPerPageOptions: config.folderRowsPerPageOptions,
								total: config.foldersTotal
							}
						).render();

						var folderContainer = instance.byId(STR_FOLDER_CONTAINER);

						instance._listView = new Liferay.ListView(
							{
								boundingBox: formatSelectorNS(instance.NS, '#listViewContainer'),
								cssClass: 'folder-display-style lfr-list-view-content',
								itemSelector: '.folder a.browse-folder, .folder a.expand-folder',
								contentBox: folderContainer,
								srcNode: folderContainer
							}
						).render();

						instance._displayViews = config.displayViews;

debugger;

						var foldersNavigation = new FoldersNavigation(
							{
								'actions' : config.actions,
								'allRowIds' : config.allRowIds,
								'displayStyle' : config.displayStyle,
								'displayStyleToolbarId' : DISPLAY_STYLE_TOOLBAR,
								'displayViews' : instance._displayViews,
								'editEntryUrl' : config._editEntryUrl,
								'folderIdHashRegEx' : config.folderIdHashRegEx,
								'form' : config.form,
								'listView' : instance._listView,
								'moveConstant': config.moveConstant,
								'moveEntryRenderUrl': config.moveEntryRenderUrl,
								'namespace': instance.NS,
								'portletContainerId': 'journalContainer',
								'portletGroup' : 'journal',
								'rowIds' : config.rowIds
							}
						);

						instance._foldersNavigation = foldersNavigation;
						folderPaginator.on('changeRequest', instance._onFolderPaginatorChangeRequest, instance);

						journalContainer.delegate(
							STR_CLICK,
							A.bind(instance._onOpenAdvancedSearch, instance),
							'.article-advanced-search-icon'
						);

						var eventHandles = [
							Liferay.after(instance._eventDataRequest, instance._afterDataRequest, instance),
							Liferay.on(instance._dataRetrieveFailure, instance._onDataRetrieveFailure, instance),
							Liferay.on(instance._eventDataRequest, instance._onDataRequest, instance),
							Liferay.on(instance._eventDataRetrieveSuccess, instance._onDataRetrieveSuccess, instance),
							Liferay.on(instance._eventEditArticle, instance._foldersNavigation._editArticle, instance._foldersNavigation),
							Liferay.on(instance._eventPageLoaded, instance._onPageLoaded, instance),
							Liferay.on(instance._eventChangeSearchFolder, instance._onChangeSearchFolder, instance)
						];

						instance._listView.after('transitionComplete', A.bind(instance._foldersNavigation._initDropTargets, instance._foldersNavigation), instance);

						instance._listView.after('itemChange', instance._afterListViewItemChange, instance);

						journalContainer.delegate(
							STR_CLICK,
							A.bind(instance._onJournalContainerClick, instance),
							formatSelectorNS(instance.NS, '#entriesContainer a[data-folder=true], #breadcrumbContainer a')
						);

						eventHandles.push(
							History.after('stateChange', instance._afterStateChange, instance)
						);

						journalContainer.plug(A.LoadingMask);

						instance._config = config;

						instance._folderContainer = folderContainer;

						instance._entryPaginator = entryPaginator;
						instance._folderPaginator = folderPaginator;

						instance._eventHandles = eventHandles;

						instance._initHover();

						if (themeDisplay.isSignedIn()) {
							instance._initSelectAllCheckbox();

							instance._initToggleSelect();
						}

						eventHandles.push(Liferay.on(config.portletId + ':portletRefreshed', A.bind(instance.destructor, instance)));

						var searchFormNode = instance.one('#fm1');

						if (searchFormNode) {
							searchFormNode.on('submit', instance._onSearchFormSubmit, instance);
						}

						instance._restoreState();
					},

					destructor: function() {
						var instance = this;

						instance._entryPaginator.destroy();
						instance._folderPaginator.destroy();
						instance._listView.destroy();

						A.Array.invoke(instance._eventHandles, 'detach');

						instance._journalContainer.purge(true);
					},

					_addHistoryState: function(data) {
						var instance = this;

						var historyState = A.clone(data);

						var currentHistoryState = History.get();

						var defaultParams = instance._config.defaultParams;

						AObject.each(
							currentHistoryState,
							function(index, item, collection) {
								if (!owns(historyState, item) && !owns(defaultParams, item)) {
									historyState[item] = null;
								}
							}
						);

						if (!AObject.isEmpty(historyState)) {
							History.add(historyState);
						}
					},

					_afterDataRequest: function(event) {
						var instance = this;

						var requestParams = event.requestParams;

						var config = instance._config;

						var data = {};

						var displayStyle = instance._displayStyle;

						data[instance._folderId] = config.defaultParentFolderId;

						data[displayStyle] = History.get(displayStyle) || config.displayStyle;

						data[instance.ns(VIEW_ENTRIES)] = true;

						data[instance.ns(VIEW_FOLDERS)] = true;

						A.mix(data, requestParams, true);

						var src = event.src;

						if (src !== SRC_HISTORY) {
							instance._addHistoryState(data);
						}

						if (src !== SRC_RESTORE_STATE) {
							data[STR_AJAX_REQUEST] = true;
						}

						var ioRequest = A.io.request(
							instance._config.mainUrl,
							{
								autoLoad: false,
								method: 'get'
							}
						);

						var sendIOResponse = A.bind(instance._sendIOResponse, instance, ioRequest);

						ioRequest.after(['failure', 'success'], sendIOResponse);

						ioRequest.set(STR_DATA, data);

						instance._journalContainer.loadingmask.show();

						ioRequest.start();

						delete data[STR_AJAX_REQUEST];

						instance._lastDataRequest = data;
					},

					_afterStateChange: function(event) {
						var instance = this;

						var namespace = instance.NS;

						var requestParams = {};

						var state = History.get();

						AObject.each(
							state,
							function(item, index, collection) {
								if (index.indexOf(namespace) === 0) {
									requestParams[index] = item;
								}
							}
						);

						if (AObject.isEmpty(requestParams)) {
							requestParams = instance._getDefaultHistoryState();
						}

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams,
								src: SRC_HISTORY
							}
						);
					},

					_afterListViewItemChange: function(event) {
						var instance = this;

						var selFolder = A.one('.folder.selected');

						if (selFolder) {
							selFolder.removeClass(CSS_SELECTED);
						}

						var item = event.newVal;

						item.ancestor('.folder').addClass(CSS_SELECTED);

						var dataExpandFolder = item.attr('data-expand-folder');
						var dataStructureId = item.attr('data-structure-id');
						var dataFolderId = item.attr(DATA_FOLDER_ID);
						var dataNavigation = item.attr('data-navigation');
						var dataViewEntries = item.attr(DATA_VIEW_ENTRIES);
						var dataViewFolders = item.attr(DATA_VIEW_FOLDERS);

						var direction = 'left';

						if (item.attr(DATA_DIRECTION_RIGHT)) {
							direction = 'right';
						}

						instance._listView.set('direction', direction);

						var config = instance._config;

						var requestParams = {};

						requestParams[instance.ns(STRUTS_ACTION)] = config.strutsAction;
						requestParams[instance.ns(STR_ENTRY_END)] = config.entryRowsPerPage || instance._entryPaginator.get(ROWS_PER_PAGE);
						requestParams[instance.ns(STR_ENTRY_START)] = 0;
						requestParams[instance.ns(STR_FOLDER_END)] = config.folderRowsPerPage || instance._folderPaginator.get(ROWS_PER_PAGE);
						requestParams[instance.ns(STR_FOLDER_START)] = 0;

						if (dataExpandFolder) {
							requestParams[instance.ns(EXPAND_FOLDER)] = dataExpandFolder;
						}

						if (dataFolderId) {
							requestParams[instance._folderId] = dataFolderId;
						}

						if (dataNavigation) {
							requestParams[instance.ns('navigation')] = dataNavigation;
						}

						if (dataViewEntries) {
							requestParams[instance.ns(VIEW_ENTRIES)] = dataViewEntries;
						}

						if (dataStructureId) {
							requestParams[instance.ns('structureId')] = dataStructureId;
						}

						if (dataViewFolders) {
							requestParams[instance.ns(VIEW_FOLDERS)] = dataViewFolders;
						}

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams
							}
						);
					},

					_getDefaultHistoryState: function() {
						var instance = this;

						var initialState = History.get();

						if (AObject.isEmpty(initialState)) {
							initialState = instance._getDefaultParams();
						}

						return initialState;
					},

					_getDefaultParams: function() {
						var instance = this;

						var config = instance._config;

						var params = {};

						params[instance.ns(STR_ENTRY_END)] = config[STR_ENTRY_END];
						params[instance.ns(STR_ENTRY_START)] = config[STR_ENTRY_START];
						params[instance.ns(STR_FOLDER_END)] = config[STR_FOLDER_END];
						params[instance.ns(STR_FOLDER_START)] = config[STR_FOLDER_START];
						params[instance.ns(STR_FOLDER_ID)] = config[STR_FOLDER_ID];

						var namespace = instance.NS;

						var tmpParams = QueryString.parse(location.search, PAIR_SEPARATOR, VALUE_SEPARATOR);

						A.mix(tmpParams, QueryString.parse(location.hash, PAIR_SEPARATOR, VALUE_SEPARATOR));

						for (var paramName in tmpParams) {
							if (owns(tmpParams, paramName) && paramName.indexOf(namespace) == 0) {
								params[paramName] = tmpParams[paramName];
							}
						}

						return params;
					},

					_getSelectedFolder: function() {
						var instance = this;

						var selectedFolderNode = instance._folderContainer.one('.selected .browse-folder');

						var selectedFolderId = 0;

						if (selectedFolderNode) {
							selectedFolderId = selectedFolderNode.attr(DATA_FOLDER_ID);
						}

						return {
							id: selectedFolderId
						};
					},

					_initHover: function() {
						var instance = this;

						instance._entriesContainer.on([STR_FOCUS, 'blur'], instance._toggleHovered, instance);
					},

					_initSelectAllCheckbox: function() {
						var instance = this;

						instance._selectAllCheckbox.on(STR_CLICK, instance._onSelectAllCheckboxChange, instance);
					},

					_initToggleSelect: function() {
						var instance = this;

						instance._entriesContainer.delegate(
							'change',
							instance._onArticleSelectorChange,
							'.article-selector',
							instance
						);
					},

					_onChangeSearchFolder: function(event) {
						var instance = this;

						var selectedFolder = instance._getSelectedFolder();

						var searchData = {
							folderId: selectedFolder.id,
							keywords: instance._keywordsNode.get('value'),
							showSearchInfo: true
						};

						if (event.searchEverywhere) {
							searchData[STR_SEARCH_FOLDER_ID] = -1;
						}
						else {
							searchData[STR_SEARCH_FOLDER_ID] = selectedFolder.id;
						}

						instance._searchArticle(searchData);
					},

					_onDataRetrieveSuccess: function(event) {
						var instance = this;

						var responseData = event.responseData;

						instance._journalContainer.loadingmask.hide();

						var content = A.Node.create(responseData);

						if (content) {
							instance._foldersNavigation._setBreadcrumb(content);
							instance._foldersNavigation._setButtons(content);
							instance._setEntries(content);
							instance._foldersNavigation._setFolders(content);
							instance._foldersNavigation._setParentFolderTitle(content);
							instance._syncDisplayStyleToolbar(content);
							instance._setSearchResults(content);
						}
					},

					_onDataRequest: function(event) {
						var instance = this;

						var src = event.src;

						if (src === SRC_DISPLAY_STYLE_BUTTONS || src === SRC_ENTRIES_PAGINATOR) {
							var selectedEntries;

							var entriesSelector = CSS_ARTICLE_DISPLAY_STYLE_SELECTED + ' :checkbox';

							if (instance._foldersNavigation._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
								entriesSelector = 'td > :checkbox:checked';
							}

							selectedEntries = instance._entriesContainer.all(entriesSelector);

							if (selectedEntries.size()) {
								instance._selectedEntries = selectedEntries.val();
							}
						}
						else if (src === SRC_SEARCH) {
							instance._entryPaginator.setState(
								{
									page: 1
								}
							);
						}

						instance._processDefaultParams(event);

						instance._updatePaginatorValues(event);
					},

					_onDataRetrieveFailure: function(event) {
						var instance = this;

						instance._journalContainer.loadingmask.hide();

						instance._sendMessage(MESSAGE_TYPE_ERROR, Liferay.Language.get('your-request-failed-to-complete'));
					},

					_onJournalContainerClick: function(event) {
						var instance = this;

						event.preventDefault();

						var config = instance._config;

						var requestParams = {};

						requestParams[instance.ns(STRUTS_ACTION)] = config.strutsAction;
						requestParams[instance.ns('action')] = 'browseFolder';
						requestParams[instance.ns(STR_ENTRY_END)] = instance._entryPaginator.get(ROWS_PER_PAGE);
						requestParams[instance.ns(STR_FOLDER_END)] = instance._folderPaginator.get(ROWS_PER_PAGE);
						requestParams[instance._folderId] = event.currentTarget.attr(DATA_FOLDER_ID);
						requestParams[instance.ns(EXPAND_FOLDER)] = false;
						requestParams[instance.ns(STR_ENTRY_START)] = 0;
						requestParams[instance.ns(STR_FOLDER_START)] = 0;

						var viewEntries = event.currentTarget.attr(DATA_VIEW_ENTRIES);

						if (viewEntries) {
							requestParams[instance.ns(VIEW_ENTRIES)] = viewEntries;
						}

						var viewFolders = event.currentTarget.attr(DATA_VIEW_FOLDERS);

						if (viewFolders) {
							requestParams[instance.ns(VIEW_FOLDERS)] = viewFolders;
						}

						var direction = 'left';

						if (event.currentTarget.attr(DATA_DIRECTION_RIGHT)) {
							direction = 'right';
						}

						instance._listView.set('direction', direction);

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams
							}
						);
					},

					_onArticleSelectorChange: function(event) {
						var instance = this;

						instance._foldersNavigation._toggleSelected(event.currentTarget, true);

						WIN[instance.ns(STR_TOGGLE_ACTIONS_BUTTON)]();

						Liferay.Util.checkAllBox(
							instance._entriesContainer,
							[
								instance.ns(STR_ROW_IDS_JOURNAL_ARTICLE_CHECKBOX),
								instance.ns(STR_ROW_IDS_JOURNAL_FOLDER_CHECKBOX)
							],
							instance._selectAllCheckbox
						);
					},

					_onEntryPaginatorChangeRequest: function(event) {
						var instance = this;

						var startEndParams = instance._foldersNavigation._getResultsStartEnd(instance._entryPaginator);

						var requestParams = instance._lastDataRequest || instance._getDefaultParams();

						var customParams = {};

						customParams[instance.ns(STR_ENTRY_START)] = startEndParams[0];
						customParams[instance.ns(STR_ENTRY_END)] = startEndParams[1];
						customParams[instance.ns(VIEW_ENTRIES)] = false;
						customParams[instance.ns(VIEW_ENTRIES_PAGE)] = true;
						customParams[instance.ns(VIEW_FOLDERS)] = false;

						if (AObject.owns(requestParams, instance.ns(SEARCH_TYPE))) {
							customParams[instance.ns(SEARCH_TYPE)] = SRC_SEARCH_FRAGMENT;
						}

						A.mix(requestParams, customParams, true);

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams,
								src: SRC_ENTRIES_PAGINATOR
							}
						);
					},

					_onFolderPaginatorChangeRequest: function(event) {
						var instance = this;

						var startEndParams = instance._foldersNavigation._getResultsStartEnd(instance._folderPaginator);

						var requestParams = instance._lastDataRequest || {};

						var customParams = {};

						customParams[instance.ns(STR_FOLDER_START)] = startEndParams[0];
						customParams[instance.ns(STR_FOLDER_END)] = startEndParams[1];
						customParams[instance.ns(VIEW_ENTRIES)] = false;
						customParams[instance.ns(VIEW_FOLDERS)] = true;

						A.mix(requestParams, customParams, true);

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams
							}
						);
					},

					_onOpenAdvancedSearch: function(event) {
						var instance = this;

						var advancedSearch = instance.byId('advancedSearch');

						var showAdvancedSearch = instance.byId('showAdvancedSearch');

						var advancedSearchClosed = !showAdvancedSearch.hasClass('close-advanced-search');

						showAdvancedSearch.toggleClass('close-advanced-search', advancedSearchClosed);

						advancedSearch.toggle(advancedSearchClosed);
					},

					_onPageLoaded: function(event) {
						var instance = this;

						var paginatorData = event.paginator;

						if (paginatorData) {
							instance._setPaginatorData(paginatorData);
						}
					},

					_onSearchFormSubmit: function(event) {
						var instance = this;

						event.preventDefault();

						var selectedFolder = instance._getSelectedFolder();

						var showTabs = (selectedFolder.id == DEFAULT_FOLDER_ID);

						var searchData = {
							folderId: selectedFolder.id,
							keywords: instance._keywordsNode.get('value'),
							searchFolderId: selectedFolder.id,
							showSearchInfo: true
						};

						instance._searchArticle(searchData);
					},

					_onSelectAllCheckboxChange: function() {
						var instance = this;

						instance._toggleEntriesSelection();
					},

					_processDefaultParams: function(event) {
						var instance = this;

						var requestParams = event.requestParams;

						AObject.each(
							instance._config.defaultParams,
							function(item, index, collection) {
								if (!Lang.isValue(History.get(index))) {
									requestParams[index] = item;
								}
							}
						);
					},

					_restoreState: function() {
						var instance = this;

						if (!History.HTML5) {
							var initialState = History.get();

							if (!AObject.isEmpty(initialState)) {
								var namespace = instance.NS;

								var requestParams = {};

								AObject.each(
									initialState,
									function(item, index, collection) {
										if (index.indexOf(namespace) === 0) {
											requestParams[index] = item;
										}
									}
								);

								Liferay.fire(
									instance._eventDataRequest,
									{
										requestParams: requestParams,
										src: SRC_RESTORE_STATE
									}
								);
							}
						}
					},

					_searchArticle: function(searchData) {
						var instance = this;

						if (searchData.showSearchInfo) {
							var entriesContainer = instance._entriesContainer;

							entriesContainer.empty();

							var searchingTPL = Lang.sub(TPL_MESSAGE_SEARCHING, [Liferay.Language.get('searching,-please-wait')]);

							entriesContainer.html(searchingTPL);
						}

						instance._journalContainer.all('.article-entries-paginator').hide();

						var requestParams = {};

						requestParams[instance.ns(STRUTS_ACTION)] = '/journal/search';
						requestParams[instance.ns(STR_FOLDER_ID)] = searchData.folderId;
						requestParams[instance.ns(STR_SEARCH_FOLDER_ID)] = searchData.searchFolderId;
						requestParams[instance.ns(SEARCH_TYPE)] = SEARCH_TYPE_SINGLE;
						requestParams[instance.ns(STR_KEYWORDS)] = searchData.keywords;
						requestParams[instance.ns(STR_SHOW_SEARCH_INFO)] = searchData.showSearchInfo;

						Liferay.fire(
							instance._eventDataRequest,
							{
								requestParams: requestParams,
								src: Liferay.JOURNAL_SEARCH
							}
						);
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
							instance._displayStyleToolbarNode.empty();

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

					_setEntries: function(content) {
						var instance = this;

						var entries = instance.one('#entries', content);

						if (entries) {
							var entriesContainer = instance._entriesContainer;

							entriesContainer.empty();

							entriesContainer.plug(A.Plugin.ParseContent);

							entriesContainer.setContent(entries);

							instance._foldersNavigation._initDropTargets();

							instance._updateSelectedEntriesStatus();
						}
					},

					_setPaginatorData: function(paginatorData) {
						var instance = this;

						var paginator = instance['_' + paginatorData.name];

						if (A.instanceOf(paginator, A.Paginator)) {
							paginator.setState(paginatorData.state);
						}
					},

					_setSearchResults: function(content) {
						var instance = this;

						var searchInfo = instance.one('#' + instance.ns('searchInfo'), content);

						var entriesContainer = instance._entriesContainer;

						if (searchInfo) {
							entriesContainer.empty();

							entriesContainer.plug(A.Plugin.ParseContent);

							entriesContainer.setContent(searchInfo);
						}

						var fragmentSearchResults = instance.one('#fragmentSearchResults', content);

						var searchResults;

						if (fragmentSearchResults) {
							searchResults = instance.one('#' + STR_SEARCH_RESULTS_CONTAINER, entriesContainer);

							if (searchResults) {
								searchResults.empty();

								searchResults.plug(A.Plugin.ParseContent);

								searchResults.setContent(fragmentSearchResults.html());
							}
						}

						var searchResultsContainer = instance.one('#' + STR_SEARCH_RESULTS_CONTAINER, content);

						if (searchResultsContainer) {
							if (!searchInfo) {
								entriesContainer.empty();
							}

							entriesContainer.plug(A.Plugin.ParseContent);

							entriesContainer.append(searchResultsContainer);
						}
					},

					_sendIOResponse: function(ioRequest, event) {
						var instance = this;

						var data = ioRequest.get(STR_DATA);
						var responseData = ioRequest.get('responseData');

						var eventType = instance._eventDataRetrieveSuccess;

						if (event.type.indexOf('success') == -1) {
							eventType = instance._dataRetrieveFailure;
						}

						Liferay.fire(
							eventType,
							{
								data: data,
								responseData: responseData
							}
						);
					},

					_sendMessage: function(type, message) {
						var instance = this;

						var output = instance._portletMessageContainer;

						output.removeClass('portlet-msg-error').removeClass('portlet-msg-success');

						output.addClass('portlet-msg-' + type);

						output.html(message);

						output.show();

						instance._entriesContainer.setContent(output);
					},

					_syncDisplayStyleToolbar: function(content) {
						var instance = this;

						var displayViews = instance._displayViews;

						var length = displayViews.length;

						if (length > 1) {
							var displayStyleToolbar = instance._displayStyleToolbarNode.getData(DISPLAY_STYLE_TOOLBAR);

							var displayStyle = instance._foldersNavigation._getDisplayStyle(instance._displayStyle);

							for (var i = 0; i < length; i++) {
								displayStyleToolbar.item(i).StateInteraction.set(STR_ACTIVE, displayStyle === displayViews[i]);
							}
						}
					},

					_toggleEntriesSelection: function() {
						var instance = this;

						var journalContainer = A.one('.journal-container');

						var selectAllCheckbox = instance._selectAllCheckbox;

						Liferay.Util.checkAll(journalContainer, instance.ns(STR_ROW_IDS_JOURNAL_FOLDER_CHECKBOX), selectAllCheckbox, CSS_RESULT_ROW);
						Liferay.Util.checkAll(journalContainer, instance.ns(STR_ROW_IDS_JOURNAL_ARTICLE_CHECKBOX), selectAllCheckbox, CSS_RESULT_ROW);

						WIN[instance.ns(STR_TOGGLE_ACTIONS_BUTTON)]();

						if (!instance._foldersNavigation._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
							var articleDisplayStyle = A.all(CSS_ARTICLE_DISPLAY_STYLE_SELECTABLE);

							articleDisplayStyle.toggleClass(CSS_SELECTED, instance._selectAllCheckbox.attr(ATTR_CHECKED));
						}
					},

					_toggleHovered: function(event) {
						var instance = this;

						if (!instance._foldersNavigation._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
							var articleDisplayStyle = event.target.ancestor(CSS_ARTICLE_DISPLAY_STYLE);

							if (articleDisplayStyle) {
								articleDisplayStyle.toggleClass('hover', (event.type == STR_FOCUS));
							}
						}
					},

					_toggleSelected: function(node, preventUpdate) {
						var instance = this;

						if (instance._foldersNavigation._getDisplayStyle(instance._displayStyle, DISPLAY_STYLE_LIST)) {
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

					_updateSelectedEntriesStatus: function() {
						var instance = this;

						var selectedEntries = instance._selectedEntries;

						if (selectedEntries && selectedEntries.length) {
							var entriesContainer = instance._entriesContainer;

							A.each(
								selectedEntries,
								function(item, index, collection) {
									var entry = entriesContainer.one('input[value="' + item + '"]');

									if (entry) {
										instance._foldersNavigation._toggleSelected(entry);
									}
								}
							);

							selectedEntries.length = 0;
						}
					},

					_updatePaginatorValues: function(event) {
						var instance = this;

						var requestParams = event.requestParams;

						var entryStartEndParams = instance._foldersNavigation._getResultsStartEnd(instance._entryPaginator);
						var folderStartEndParams = instance._foldersNavigation._getResultsStartEnd(instance._folderPaginator);

						var customParams = {};

						if (requestParams) {
							if (!owns(requestParams, instance.ns(STR_ENTRY_START)) && !owns(requestParams, instance.ns(STR_ENTRY_END))) {
								customParams[instance.ns(STR_ENTRY_START)] = entryStartEndParams[0];
								customParams[instance.ns(STR_ENTRY_END)] = entryStartEndParams[1];
							}

							if (!owns(requestParams, instance.ns(STR_FOLDER_START)) && !owns(requestParams, instance.ns(STR_FOLDER_END))) {
								customParams[instance.ns(STR_FOLDER_START)] = folderStartEndParams[0];
								customParams[instance.ns(STR_FOLDER_END)] = folderStartEndParams[1];
							}

							if (!AObject.isEmpty(customParams)) {
								A.mix(requestParams, customParams, true);
							}
						}
					}
				}
			}
		);

		Liferay.Portlet.JournalNavigation = JournalNavigation;
	},
	'',
	{
		requires: ['aui-loading-mask', 'aui-paginator', 'aui-parse-content', 'event-simulate', 'liferay-folders-navigation', 'liferay-history-manager', 'liferay-list-view', 'liferay-message', 'liferay-portlet-base', 'liferay-util-list-fields', 'querystring-parse-simple']
	}
);