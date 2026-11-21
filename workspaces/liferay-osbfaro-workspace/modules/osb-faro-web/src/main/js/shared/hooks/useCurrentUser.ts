import {useSelector} from 'react-redux';
import {User} from 'shared/util/records';

/**
 * Get currentUser from redux store.
 */
export const useCurrentUser = (): User => {
	const currentUserId = useSelector<any, any>((state) =>
		state.getIn(['currentUser', 'data'])
	);
	const data: User = useSelector<any, any>((state) =>
		state.getIn(['users', currentUserId, 'data'])
	);

	const newUser = new User({
		emailAddress: '',
		id: '',
		name: '',
		roleName: '',
		status: 1,
	});

	return data || newUser;
};
