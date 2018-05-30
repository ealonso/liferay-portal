<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
boolean showChangeButton = ParamUtil.getBoolean(request, "showChangeButton");
%>

<div class="fragment-thumbnail">
	<div class="thumbnail-container">
		<div class="aspect-ratio-item-center-middle aspect-ratio-item-fluid thumbnail-wrapper">
			<div class="loading-animation wrapper-content"></div>
		</div>
	</div>

	<div class="autofit-float autofit-row button-row">
		<c:if test="<%= showChangeButton %>">
			<div class="autofit-col">
				<button class="btn btn-default" id="<portlet:namespace/>changeThumbnailButton">
					<liferay-ui:message key="change" />
				</button>
			</div>
		</c:if>

		<div class="autofit-col autofit-col-end">
			<button class="btn btn-default" id="<portlet:namespace/>cancelButton">
				<liferay-ui:message key="cancel" />
			</button>

			<button class="btn btn-primary" id="<portlet:namespace/>saveThumbnailButton">
				<liferay-ui:message key="ok" />
			</button>
		</div>
	</div>
</div>

<input class="hide" id="<portlet:namespace/>fileInput" type="file" />
<input class="hide" id="<portlet:namespace/>defaultImage" type="text" />

<portlet:actionURL name="/fragment/upload_fragment_entry_thumbnail" var="uploadFragmentEntryThumbnailURL">
	<portlet:param name="fragmentEntryId" value="<%= String.valueOf(fragmentDisplayContext.getFragmentEntryId()) %>" />
</portlet:actionURL>

<aui:script require="metal-dom/src/all/dom as dom">
	var addRemoveImageIcon = function(container) {
		var removeImageButton = document.createElement('span');
		removeImageButton.setAttribute('class', 'remove-image-icon');
		removeImageButton.innerHTML = '<svg class="icon-monospaced lexicon-icon"><use xlink:href="<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg" %>#times-circle"></use></svg>';

		container.appendChild(removeImageButton);

		dom.delegate(
			container,
			'click',
			'.remove-image-icon',
			function(event) {
				var image = document.querySelector('.thumbnail-container div img');
				var defaultImage = document.querySelector('#<portlet:namespace/>defaultImage');

				image.src = defaultImage.value;
				image.setAttribute('data-file-entry-id', '');

				event.delegateTarget.remove();
			}
		);
	};

	Liferay.Util.getTop().Liferay.on(
		'<portlet:namespace/>:setThumbnailImage',
		function(data) {
			var thumbnailWrapper = document.querySelector('.thumbnail-container div');

			var image = document.createElement('img');

			image.setAttribute('class', 'wrapper-content');
			image.setAttribute('src', data.thumbnailImageSrc);

			if (data.defaultImageSrc) {
				var defaultImage = document.querySelector('#<portlet:namespace/>defaultImage');

				defaultImage.value = data.defaultImageSrc;
			}

			if (data.showRemoveIcon) {
				addRemoveImageIcon(thumbnailWrapper);
			}

			thumbnailWrapper.replaceChild(image, thumbnailWrapper.querySelector('.wrapper-content'));
		}
	);

	var eventHandlers = [];

	var removeHandlers = function() {
		eventHandlers.forEach(
			function(eventHandler) {
				eventHandler.removeListener();
			}
		);
	};

	eventHandlers.push(
		dom.delegate(
			document.body,
			'click',
			'#<portlet:namespace/>cancelButton',
			function(event) {
				removeHandlers();

				Liferay.Util.getWindow('<portlet:namespace />fragmentEntryThumbnail').destroy();
			}
		)
	);

	eventHandlers.push(
		dom.delegate(
			document.body,
			'click',
			'#<portlet:namespace/>saveThumbnailButton',
			function(event) {
				var thumbnailWrapperContent = document.querySelector('.thumbnail-container .wrapper-content');

				Liferay.Util.getTop().Liferay.fire(
					'<portlet:namespace/>:updateFragmentEntryThumbnail',
					{
						fileEntryId: thumbnailWrapperContent.dataset.fileEntryId,
						src: thumbnailWrapperContent.getAttribute('src'),
						submit: <%= !showChangeButton %>
					}
				);

				removeHandlers();

				Liferay.Util.getWindow('<portlet:namespace />fragmentEntryThumbnail').destroy();
			}
		)
	);

	eventHandlers.push(
		dom.delegate(
			document.body,
			'click',
			'#<portlet:namespace/>changeThumbnailButton',
			function(event) {
				var thumbnailWrapper = document.querySelector('.thumbnail-container div');

				var fileInput = document.querySelector('#<portlet:namespace/>fileInput');

				fileInput.click();
			}
		)
	);

	eventHandlers.push(
		dom.delegate(
			document.body,
			'change',
			'#<portlet:namespace/>fileInput',
			function(event) {
				var fileInput = document.querySelector('#<portlet:namespace/>fileInput');

				var uploadUrl = '<%= uploadFragmentEntryThumbnailURL %>&<portlet:namespace/>fileName=' + event.target.files[0].name;

				var thumbnailWrapper = document.querySelector('.thumbnail-container div');

				var loading = document.createElement('div');

				loading.setAttribute('class', 'loading-animation wrapper-content');

				thumbnailWrapper.replaceChild(loading, thumbnailWrapper.querySelector('.wrapper-content'));

				fetch(
					uploadUrl,
					{
						body: fileInput.files[0],
						credentials: 'include',
						method: 'POST'
					}
				).then(
					function(response) {
						return response.json();
					}
				).then(
					function(response) {
						if (response.fileEntryId) {
							var image = document.createElement('img');

							var wrapperContent = thumbnailWrapper.querySelector('.wrapper-content');

							image.setAttribute('class', 'wrapper-content');
							image.setAttribute('data-file-entry-id', response.fileEntryId);
							image.setAttribute('src', response.imageUrl);

							thumbnailWrapper.replaceChild(image, wrapperContent);

							addRemoveImageIcon(thumbnailWrapper);
						}
					}
				);
			}
		)
	);
</aui:script>