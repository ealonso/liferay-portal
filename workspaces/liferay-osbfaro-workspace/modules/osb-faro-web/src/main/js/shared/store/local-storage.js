import {fromJS} from 'immutable';

export function loadState() {
	try {
		return fromJS({
			maintenanceSeen: JSON.parse(
				atob(localStorage.getItem('maintenanceSeen'))
			),
		});
	}
	catch (err) {
		return undefined;
	}
}

export function saveState(state) {
	try {
		localStorage.setItem(
			'maintenanceSeen',
			btoa(JSON.stringify(state.get('maintenanceSeen')))
		);
	}
	catch (err) {}
}
