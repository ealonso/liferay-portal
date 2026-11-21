import {createActionTypes} from 'redux-toolbox';
import {user} from '../middleware/schema';

export const actionTypes = {
	...createActionTypes('fetch', 'current_user'),
};

export function receiveCurrentUser(currentUser) {
	return {
		meta: {schema: user},
		payload: currentUser,
		type: actionTypes.FETCH_CURRENT_USER_SUCCESS,
	};
}
