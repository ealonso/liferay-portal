import {init, destroy} from './EditableImageFragmentProcessor.es';

/**
 * @param {HTMLElement} element
 * @param {string} [backgroundImageURL='']
 */
function render(element, backgroundImageURL = '') {
	element.style.backgroundImage = backgroundImageURL ?
		`url("${backgroundImageURL}")` :
		'';
}

export default {
	destroy,
	init,
	render
};