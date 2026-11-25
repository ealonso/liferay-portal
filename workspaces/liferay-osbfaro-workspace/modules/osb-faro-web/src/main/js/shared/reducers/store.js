import {actionTypes} from '../actions/store';
import {createReducer} from 'redux-toolbox';
import {Map} from 'immutable';

const actionHandlers = {
	[actionTypes.CLEAR_STORE]: () => new Map(),
};

export default createReducer(new Map(), actionHandlers);
