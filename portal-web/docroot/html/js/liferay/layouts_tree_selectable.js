AUI.add(
	'liferay-layouts-tree-selectable',
	function(A) {
		var TreeUtil = Liferay.Util.LayoutsTreeUtil;

		var STR_DEFAULT_STATE = 'defaultState';

		var STR_HOST = 'host';

		var LayoutsTreeSelectable = A.Component.create(
			{
				ATTRS: {
					defaultState: {
						validator: A.Lang.isBoolean,
						value: false
					}
				},

				EXTENDS: A.Plugin.Base,

				NAME: 'layouts-tree-selectable',

				NS: 'selectable',

				prototype: {
					initializer: function(config) {
						var instance = this;

						var eventHandles = [
							instance.afterHostEvent('*:checkedChange', instance._onNodeCheckedChange, instance),
							instance.afterHostEvent('*:childrenChange', instance._onNodeChildrenChange, instance),
							instance.afterHostEvent('append', instance._onTreeAppend, instance),
							instance.afterHostEvent('render', instance._onTreeRender, instance),
							instance.doAfter('_formatNode', instance._formatNode, instance),
							instance.doAfter('_formatNodeLabel', instance._formatNodeLabel, instance),
							instance.doAfter('_formatRootNode', instance._formatRootNode, instance)
						];

						instance._eventHandles = eventHandles;
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandles)).detach();
					},

					_formatNode: function(node) {
						var instance = this;

						var currentRetVal = A.Do.currentRetVal;

						return new A.Do.AlterReturn(
							'Modified checked and type attributes',
							A.merge(
								currentRetVal,
								{
									checked: instance.get(STR_DEFAULT_STATE),
									type: 'task'
								}
							)
						);
					},

					_formatNodeLabel: function(node, cssClass, label, title) {
						var instance = this;

						return new A.Do.AlterReturn(
							'Modified node label',
							TreeUtil.createLabel(
								{
									cssClass: cssClass,
									label: label,
									title: title
								}
							)
						);
					},

					_formatRootNode: function(rootConfig, children) {
						var instance = this;

						return new A.Do.AlterReturn(
							'Modified checked, label and type attributes',
							A.merge(
								A.Do.currentRetVal,
								{
									checked: instance.get(STR_DEFAULT_STATE),
									label: rootConfig.label,
									type: 'task'
								}
							)
						);
					},

					_onNodeCheckedChange: function(event) {
						var instance = this;

						var host = instance.get(STR_HOST);

						if (event.currentTarget === host) {
							host.fire(
								'selectableNodeCheckedChange',
								{
									checked: event.newVal,
									node: event.target
								}
							);
						}
					},

					_onNodeChildrenChange: function(event) {
						var instance = this;

						var host = instance.get(STR_HOST);

						host.fire(
							'selectableNodeChildrenChange',
							{
								node: event.target
							}
						);
					},

					_onTreeAppend: function(event) {
						var instance = this;

						var host = instance.get(STR_HOST);

						host.fire(
							'selectableTreeAppend',
							{
								node: event.tree.node
							}
						);
					}
				}
			}
		);

		A.Plugin.LayoutsTreeSelectable = LayoutsTreeSelectable;
	},
	'',
	{
		requires: ['aui-base', 'liferay-tree-util']
	}
);