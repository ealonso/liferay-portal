import reducer from '../store';
import {actionTypes} from '../../actions/store';
import {Map} from 'immutable';

describe('store', () => {
	it('should be a function', () => {
		expect(typeof reducer).toBe('function');
	});

	it('should clear the store to its initial state', () => {
		const action = {
			type: actionTypes.CLEAR_STORE,
		};

		const state = reducer(
			new Map({
				segment: new Map({123: new Map()}),
			}),
			action
		);

		expect(state.size).toBe(0);
	});
});
