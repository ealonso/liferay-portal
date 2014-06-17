AUI.add(
	'liferay-layouts-tree',
	function(A) {
		var Lang = A.Lang;

		var Util = Liferay.Util;

		var TreeUtil = Liferay.Util.LayoutsTreeUtil;

		var TREE_CSS_CLASSES = {
			iconCheck: 'tree-icon icon-check',
			iconCollapsed: 'icon-file',
			iconExpanded: 'icon-file',
			iconHitAreaCollapsed: 'tree-hitarea icon-plus',
			iconHitAreaExpanded: 'tree-hitarea icon-minus',
			iconLeaf: 'icon-leaf',
			iconLoading: 'icon-refresh',
			iconUncheck: 'icon-check'
		};

		var TREE_LOADING_EL_TPL = '<div class="lfr-tree-loading"><span class="icon icon-loading lfr-tree-loading-icon"></span></div>';

		var LayoutsTreeBase = function() {};

		LayoutsTreeBase.ATTRS = {
			io: {
				getter: '_getNodeIOConfig'
			},

			layouts: {
				validator: Lang.isObject
			},

			layoutURL: {
				validator: Lang.isString
			},

			maxChildren: {
				validator: Lang.isNumber,
				value: 20
			},

			root: {
				validator: Lang.isObject
			},

			selPlid: {
				validator: Lang.isString
			},

			type: {
				validator: Lang.isString,
				value: 'pages'
			}
		};

		LayoutsTreeBase.prototype = {
			initializer: function() {
				var instance = this;

				var boundingBox = instance.get('boundingBox');

				instance._treeLoadingElement = boundingBox.ancestor().insertBefore(
					A.Node.create(TREE_LOADING_EL_TPL),
					boundingBox
				);

				instance._treeId = instance.get('boundingBox').attr('data-treeid');

				instance._bindUILTBase();
			},

			renderUI: function() {
				var instance = this;

				instance._parseLayouts(instance.get('layouts'));
			},

			_afterRenderTree: function(event) {
				var instance = this;

				instance._treeLoadingElement.hide();

				var selPlid = instance.get('selPlid');

				var rootNode = instance.getChildren()[0];

				TreeUtil.restoreSelectedNode(selPlid, rootNode);

				rootNode.eachChildren(A.bind(TreeUtil.restoreSelectedNode, TreeUtil, selPlid));
			},

			_bindUILTBase: function() {
				var instance = this;

				instance.after('render', instance._afterRenderTree, instance);
				instance.on('dropAppend', instance._onDropAppend, instance);
				instance.on('dropInsert', instance._onDropInsert, instance);
			},

			_formatJSONResults: function(json) {
				var instance = this;

				var output = [];

				A.each(
					json.layouts,
					function(node) {
						output.push(instance._formatNode(node));
					}
				);

				return output;
			},

			_formatNode: function(node) {
				var instance = this;

				var childLayouts = [];
				var cssIcons = {};
				var total = 0;

				var iconCssClassName = 'icon-link';

				var hasChildren = node.hasChildren;
				var nodeChildren = node.children;
				var nodeType = node.type;

				if ((nodeType === 'embedded') ||
					(nodeType === 'link_to_layout') ||
					(nodeType === 'url')) {

					cssIcons = {
						iconCollapsed: iconCssClassName,
						iconExpanded: iconCssClassName,
						iconLeaf: iconCssClassName
					};
				}

				if (nodeChildren) {
					childLayouts = nodeChildren.layouts;
					total = nodeChildren.total;
				}

				var expanded = (total > 0);

				var maxChildren = instance.get('maxChildren');

				var newNode = {
					alwaysShowHitArea: hasChildren,
					cssClasses: {
						pages: A.merge(TREE_CSS_CLASSES, cssIcons)
					},
					draggable: node.sortable,
					expanded: expanded,
					id: TreeUtil.createListItemId(instance._treeId, node.groupId, node.layoutId, node.plid),
					io: instance._getNodeIOConfig(),
					leaf: !node.parentable,
					paginator: {
						limit: maxChildren,
						offsetParam: 'start',
						start: Math.max(childLayouts.length - maxChildren, 0),
						total: total
					},
					type: (nodeChildren && expanded) ? 'node' : 'io'
				};

				if (nodeChildren && expanded) {
					newNode.children = instance._formatJSONResults(nodeChildren);
				}

				var cssClass = '';
				var title = '';
				var name = Util.escapeHTML(node.name);

				if (node.layoutRevisionId) {
					if (!node.layoutRevisionHead) {
						title =  Liferay.Language.get('there-is-not-a-version-of-this-page-marked-as-ready-for-publication');
					}
					else if (node.layoutBranchName) {
						node.layoutBranchName = Util.escapeHTML(node.layoutBranchName);

						name += Lang.sub(' <span class="layout-branch-name" title="' + Liferay.Language.get('this-is-the-page-variation-that-is-marked-as-ready-for-publication') + '">[{layoutBranchName}]</span>', node);
					}

					if (node.incomplete) {
						cssClass = 'incomplete-layout';

						title = Liferay.Language.get('this-page-is-not-enabled-in-this-site-pages-variation,-but-is-available-in-other-variations');
					}
				}

				if (!node.sortable) {
					cssClass = 'lfr-page-locked';
				}

				newNode.label = instance._formatNodeLabel(node, cssClass, name, title);

				return newNode;
			},

			_formatNodeLabel: function(node, cssClass, label, title) {
				var instance = this;

				var label = TreeUtil.createLink(
					{
						cssClass: cssClass,
						id: TreeUtil.createLinkId(instance._treeId, node.friendlyURL),
						label: label,
						plid: node.plid,
						title: title,
						url: node.friendlyURL,
						uuid: node.uuid
					},
					instance.get('layoutURL')
				);

				return label;
			},

			_formatRootNode: function(rootConfig, children) {
				var instance = this;

				var rootLabel = TreeUtil.createLink(
					{
						label: Liferay.Util.escapeHTML(rootConfig.label),
						plid: rootConfig.defaultParentLayoutId
					},
					instance.get('layoutURL')
				);

				var maxChildren = instance.get('maxChildren');

				var layouts = instance.get('layouts');

				var rootNode = {
					alwaysShowHitArea: true,
					children: children,
					cssClasses: {
						pages: TREE_CSS_CLASSES
					},
					draggable: false,
					expanded: rootConfig.expand,
					id: TreeUtil.createListItemId(instance._treeId, rootConfig.groupId, rootConfig.defaultParentLayoutId, 0),
					label: rootLabel,
					leaf: false,
					paginator: {
						limit: maxChildren,
						offsetParam: 'start',
						start: Math.max(layouts.layouts.length - maxChildren, 0),
						total: layouts.total
					},
					type: 'io'
				};

				return rootNode;
			},

			_getNodeIOConfig: function() {
				var instance = this;

				var ioCfg = {
					cfg: {
						data: function(node) {
							return {
								cmd: 'get',
								controlPanelCategory: 'current_site.pages',
								doAsGroupId: themeDisplay.getScopeGroupId(),
								groupId: TreeUtil.extractGroupId(node),
								incomplete: instance.get('incomplete'),
								p_auth: Liferay.authToken,
								p_l_id: themeDisplay.getPlid(),
								p_p_id: '88',
								parentLayoutId: TreeUtil.extractLayoutId(node),
								privateLayout: instance.get('root').privateLayout,
								selPlid: instance.get('selPlid'),
								treeId: instance._treeId
							};
						},
						method: A.config.io.method,
						on: {
							success: function(event, id, xhr) {
								var response;

								try {
									response = A.JSON.parse(xhr.responseText);
								}
								catch (e) {
								}

								if (response) {
									//instance.get('root').paginator.total = response.total;

									instance.syncUI();
								}

								this.fire('ioSuccess');
							}
						}
					},
					formatter: A.bind(instance._formatJSONResults, instance),
					url: themeDisplay.getPathMain() + '/layouts_admin/get_layouts'
				};

				return ioCfg;
			},

			_onDropAppend: function(event) {
				var tree = event.tree;

				var index = tree.dragNode.get('parentNode').getChildrenLength() - 1;

				TreeUtil.updateLayoutParent(
					TreeUtil.extractPlid(tree.dragNode),
					TreeUtil.extractPlid(tree.dropNode),
					index
				);
			},

			_onDropInsert: function(event) {
				var tree = event.tree;

				var index = tree.dragNode.get('parentNode').indexOf(tree.dragNode);

				TreeUtil.updateLayoutParent(
					TreeUtil.extractPlid(tree.dragNode),
					TreeUtil.extractPlid(tree.dropNode.get('parentNode')),
					index
				);
			},

			_parseLayouts: function(value) {
				var instance = this;

				var children = instance._formatJSONResults(value);

				var rootConfig = instance.get('root');

				if (rootConfig) {
					children = [instance._formatRootNode(rootConfig, children)];
				}

				instance.set('children', children);

				instance.getChildren()[0].get('contentBox').addClass('lfr-root-node')

				return value;
			}
		};

		Liferay.LayoutsTree = A.Component.create(
			{
				AUGMENTS: LayoutsTreeBase,
				EXTENDS: A.TreeView,
				NAME: 'liferaylayoutstree'
			}
		);

		Liferay.LayoutsTreeDD = A.Component.create(
			{
				AUGMENTS: LayoutsTreeBase,
				EXTENDS: A.TreeViewDD,
				NAME: 'liferaylayoutstreedd'
			}
		);
	},
	'',
	{
		requires: ['aui-tree-view', 'liferay-tree-util']
	}
);