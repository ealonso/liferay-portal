import reducer from '../store';
import {actionTypes} from '../../actions/store';
import {Map} from 'immutable';

describe('store', () => {
	afterAll(() => {
		global.localStorage.removeItem('maintenanceSeen');
	});

	beforeAll(() => {
		global.localStorage.setItem(
			'maintenanceSeen',
			btoa(JSON.stringify({321: true}))
		);
	});

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

		expect(state.get('segment')).toBeUndefined();
		expect(state.get('maintenanceSeen').size).toBe(1);
	});
});
