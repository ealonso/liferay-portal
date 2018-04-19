import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './TranslationStatus.soy';

/**
 * TranslationStatus
 */

class TranslationStatus extends Component {
    _handleLocaleClick(event) {
        this.emit(
            'localeChanged',
            event.delegateTarget.getAttribute('data-locale')
        );
    }
}

Soy.register(TranslationStatus, templates);

export {TranslationStatus};
export default TranslationStatus;