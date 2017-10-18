;(function() {
	var adjustScrollTop = function() {
		var controlMenu;
		var controlMenuId;
		var scroll;

		if (Liferay.ControlMenu) {
			controlMenuId = Liferay.ControlMenu._namespace + 'ControlMenu';
			controlMenu = document.getElementById(controlMenuId);

			if (controlMenu) {
				scroll = (controlMenu.offsetHeight || 0);

				window.scrollBy(0, -scroll);
			}
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