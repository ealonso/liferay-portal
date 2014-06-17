AUI.add(
	'liferay-layouts-tree-util',
	function(A) {
		var AArray = A.Array;

		var Lang = A.Lang;

		var Util = Liferay.Util;

		var PREFIX_GROUP_ID = '_groupId_';

		var PREFIX_LAYOUT = '_layout_';

		var PREFIX_LAYOUT_ID = '_layoutId_';

		var PREFIX_PLID = '_plid_';

		var LABEL_TPL = '<span class="{cssClass}" title="{title}">{label}</span>';

		var LayoutsTreeUtil = {
			compareItemId: function(item, id) {
				return (LayoutsTreeUtil.extractPlid(item) == id);
			},

			createLabel: function(data) {
				return Lang.sub(LABEL_TPL, data);
			},

			createLink: function(data, hrefTemplate) {
				var className = 'layout-tree';

				if (data.cssClass) {
					className += ' ' + data.cssClass;
				}

				if (!data.uuid) {
					data.uuid = '';
				}

				if (!data.id) {
					data.id = '';
				}

				if (!data.url) {
					data.url = '';
				}

				var href = A.Lang.sub(
					hrefTemplate,
					{
						historyKey: data.historyKey,
						selPlid: data.plid
					}
				);

				return '<a class="' + className + '" data-url="' + Util.escapeHTML(data.url) + '" data-uuid="' + Util.escapeHTML(data.uuid) + '" href="' + href + '" id="' + Util.escapeHTML(data.id) + '" title="' + data.title + '">' + data.label + '</a>';
			},

			createLinkId: function(treeId, friendlyURL) {
				return treeId + PREFIX_LAYOUT + friendlyURL.substring(1);
			},

			createListItemId: function(treeId, groupId, layoutId, plid) {
				return treeId + PREFIX_LAYOUT_ID + layoutId + PREFIX_PLID + plid + PREFIX_GROUP_ID + groupId;
			},

			extractGroupId: function(node) {
				return node.get('id').match(/groupId_(\d+)/)[1];
			},

			extractLayoutId: function(node) {
				return node.get('id').match(/layoutId_(\d+)/)[1];
			},

			extractPlid: function(node) {
				return node.get('id').match(/plid_(\d+)/)[1];
			},

			findNodeByPlid: function(node, plid, treeView) {
				var foundItem = null;

				if (node) {
					if (LayoutsTreeUtil.compareItemId(node, plid)) {
						foundItem = node;
					}
				}

				if (!foundItem) {
					var children = (node || treeView).get('children');

					var length = children.length;

					for (var i = 0; i < length; i++) {
						var item = children[i];

						if (item.isLeaf()) {
							if (LayoutsTreeUtil.compareItemId(item, plid)) {
								foundItem = item;
							}
						}
						else {
							foundItem = LayoutsTreeUtil.findNodeByPlid(item, plid, treeView);
						}

						if (foundItem) {
							break;
						}
					}
				}

				return foundItem;
			},

			restoreSelectedNode: function(selPlid, node) {
				var instance = this;

				var plid = LayoutsTreeUtil.extractPlid(node);

				if (plid === selPlid) {
					node.select();
				}
				else {
					node.unselect();
				}
			},

			updateLayout: function(data) {
				A.io.request(
					themeDisplay.getPathMain() + '/layouts_admin/update_page',
					{
						data: A.mix(
							data,
							{
								controlPanelCategory: 'current_site.pages',
								doAsGroupId: themeDisplay.getScopeGroupId(),
								p_auth: Liferay.authToken,
								p_l_id: themeDisplay.getPlid(),
								p_p_id: '88'
							}
						)
					}
				);
			},

			updateLayoutParent: function(dragPlid, dropPlid, index) {
				LayoutsTreeUtil.updateLayout(
					{
						cmd: 'parent_layout_id',
						parentPlid: dropPlid,
						plid: dragPlid,
						priority: index
					}
				);
			}
		};

		Util.LayoutsTreeUtil = LayoutsTreeUtil;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-request']
	}
);