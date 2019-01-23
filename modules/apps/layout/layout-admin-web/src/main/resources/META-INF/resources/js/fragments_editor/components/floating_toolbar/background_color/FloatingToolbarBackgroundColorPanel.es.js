import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './FloatingToolbarBackgroundColorPanelDelegateTemplate.soy';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import templates from './FloatingToolbarBackgroundColorPanel.soy';

/**
 * FloatingToolbarBackgroundColorPanel
 */
class FloatingToolbarBackgroundColorPanel extends Component {

	/**
	 * @inheritDoc
	 */
	disposed() {
		this._colorPalette.destroy();
	}

	/**
	 * @inheritDoc
	 */
	rendered() {
		AUI().use(
			'aui-color-palette',
			(A) => {
				this._colorPalette = new A.ColorPalette(
					{
						items: this.themeColors
					}
				);

				this._colorPalette.render(
					'#floatingToolbarBackgroundColorPanelPalette'
				);
			}
		);
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarBackgroundColorPanel.STATE = {

	/**
	 * @default undefined
	 * @memberof FloatingToolbarBackgroundColorPanel
	 * @review
	 * @type {object}
	 */
	store: Config
		.object()
		.value(null),

	/**
	 * Internal Color Palette instance
	 * @default null
	 * @memberof FloatingToolbarBackgroundColorPanel
	 * @review
	 * @type {object}
	 */
	_colorPalette: Config
		.internal()
		.value(null)
};

const ConnectedFloatingToolbarBackgroundColorPanel = getConnectedComponent(
	FloatingToolbarBackgroundColorPanel,
	['themeColors']
);

Soy.register(ConnectedFloatingToolbarBackgroundColorPanel, templates);

export {ConnectedFloatingToolbarBackgroundColorPanel, FloatingToolbarBackgroundColorPanel};
export default ConnectedFloatingToolbarBackgroundColorPanel;