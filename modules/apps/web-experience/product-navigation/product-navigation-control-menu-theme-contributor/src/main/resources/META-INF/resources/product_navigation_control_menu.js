;(function() {
	var adjustScrollTop = function() {
		var controlMenuId = Liferay.ControlMenu._namespace + 'ControlMenu';
		var controlMenu = document.getElementById(controlMenuId);
		var scroll = (controlMenu.offsetHeight || 0);

		if (controlMenu) {
			window.scrollBy(0, -scroll);
		}
	};

	var handleFormRegistered = function(event) {
		if (event.form && event.form.formValidator) {
			AUI().Do.after(
				adjustScrollTop,
				event.form.formValidator,
				'focusInvalidField'
			);
		}
	};

	Liferay.on('form:registered', handleFormRegistered);
}());