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

<div class="fragment-thumbnail">
	<div class="thumbnail-container">
		<div class="aspect-ratio-item-center-middle aspect-ratio-item-fluid thumbnail-wrapper">
			<div class="loading-animation wrapper-content"></div>
		</div>
	</div>

	<div class="autofit-float autofit-row button-row">
		<div class="autofit-col">
			<button class="btn btn-default" id="<portlet:namespace/>changeThumbnailButton">
				<liferay-ui:message key="change" />
			</button>
		</div>

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

<aui:script require="metal-dom/src/all/dom as dom">
	Liferay.Util.getTop().Liferay.on(
		'<portlet:namespace/>:setThumbnailImage',
		function(data) {
			var thumbnailWrapper = document.querySelector('.thumbnail-container div');

			var defaultImage = document.createElement('img');

			defaultImage.setAttribute('class', 'wrapper-content');
			defaultImage.setAttribute('src', data.thumbnailImageSrc);

			thumbnailWrapper.replaceChild(defaultImage, thumbnailWrapper.querySelector('.wrapper-content'));
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
				Liferay.Util.getWindow('<portlet:namespace />fragmentEntryThumbnail').hide();

				removeHandlers();
			}
		)
	);

	eventHandlers.push(
		dom.delegate(
			document.body,
			'click',
			'#<portlet:namespace/>saveThumbnailButton',
			function(event) {
				Liferay.Util.getWindow('<portlet:namespace />fragmentEntryThumbnail').hide();

				Liferay.fire(
					'<portlet:namespace/>:updateFragmentEntrySettings',
					{}
				);

				removeHandlers();
			}
		)
	);
</aui:script>