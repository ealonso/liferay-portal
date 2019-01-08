import PortletBase from 'frontend-js-web/liferay/PortletBase.es';
import {Config} from 'metal-state';

class ElementsDefaultEventHandler extends PortletBase {
	handleItemClicked(event) {
		var itemData = event.data.item.data;

		if (itemData && itemData.action && this[itemData.action]) {
			this[itemData.action](itemData);
		}
	}

	copyArticle(itemData) {
		submitForm(document.hrefFm, itemData.copyArticleURL);
	}

	delete(itemData) {
		if (this.isTrashEnabled || confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this'))) {
			submitForm(document.hrefFm, itemData.deleteFolderURL);
		}
	}

	expireArticles(itemData) {
		submitForm(document.hrefFm, itemData.expireURL);
	}

	permissions(itemData) {
		Liferay.Util.openWindow(
			{
				dialog: {
					destroyOnHide: true,
					modal: true
				},
				title: Liferay.Language.get('permissions'),
				uri: itemData.permissionsURL
			}
		);
	}

	preview(itemData) {
		Liferay.fire(
			'previewArticle',
			{
				title: itemData.title,
				uri: itemData.previewURL
			}
		);
	}

	publishToLive(itemData) {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-publish-the-selected-web-content'))) {
			submitForm(document.hrefFm, itemData.publishArticleURL);
		}
	}

	subscribeArticle(itemData) {
		submitForm(document.hrefFm, itemData.subscribeArticleURL);
	}

	unsubscribeArticle(itemData) {
		submitForm(document.hrefFm, itemData.unsubscribeArticleURL);
	}
}

ElementsDefaultEventHandler.STATE = {
	isTrashEnabled: Config.bool(),
	namespace: Config.string(),
};


export default ElementsDefaultEventHandler;