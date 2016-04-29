;(function() {
	AUI().applyConfig(
		{
			groups: {
				asset: {
					base: MODULE_PATH + '/js/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'liferay-asset-tags-picker': {
							path: 'asset_tags_picker.js',
							requires: [
								'aui-io-plugin-deprecated',
								'aui-live-search-deprecated',
								'aui-modal',
								'aui-template-deprecated',
								'aui-textboxlist-deprecated',
								'datasource-cache',
								'liferay-item-selector-dialog',
								'liferay-service-datasource'
							]
						},
					},
					root: MODULE_PATH + '/js/'
				}
			}
		}
	);
})();