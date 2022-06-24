/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

const MOCK_URL =
	'http://localhost:8080/group/guest/~/control_panel/manage/-/select/infoitem/_com_liferay_site_navigation_admin_web_portlet_SiteNavigationAdminPortlet_selectInfoItem?_com_liferay_item_selector_web_portlet_ItemSelectorPortlet_0_json=%7B%22desiredItemSelectorReturnTypes%22%3A%22com.liferay.item.selector.criteria.InfoItemItemSelectorReturnType%22%2C%22itemSubtype%22%3Anull%2C%22itemType%22%3A%22com.liferay.portal.kernel.repository.model.FileEntry%22%2C%22mimeTypes%22%3Anull%2C%22multiSelection%22%3Afalse%2C%22status%22%3A0%7D&p_p_auth=IRVXzaPX';

const MOCK_DM_ENABLED = true;

const wrapper = fragmentElement;

const input = document.getElementById(`${fragmentNamespace}-file-upload`);
const fileName = wrapper.querySelector('.forms-file-upload-file-name');
const removeButton = wrapper.querySelector("[type='button']");
const selectButton = wrapper.querySelector('.btn-secondary');

if (layoutMode === 'edit') {
	if (selectButton) {
		selectButton.classList.add('disabled');
	}
}

function onInputChange() {
	fileName.innerText = input.files[0].name;

	removeButton.classList.remove('d-none');
	removeButton.addEventListener('click', onRemoveFile);
}

function onRemoveFile() {
	input.value = '';
	fileName.innerText = '';

	removeButton.classList.add('d-none');
	removeButton.removeEventListener('click', onRemoveFile);
}

function onSelectFile(event) {
	if (MOCK_DM_ENABLED) {
		event.preventDefault();

		Liferay.Util.openSelectionModal({
			customSelectEvent: true,
			multiple: true,
			onSelect(selectedItem) {
				console.log(selectedItem);
			},
			selectEventName: 'MOCK_SELECT_EVENT_NAME',
			title: 'Select Document',
			url: MOCK_URL,
		});
	}
}

input.addEventListener('change', onInputChange);
selectButton.addEventListener('click', onSelectFile);
