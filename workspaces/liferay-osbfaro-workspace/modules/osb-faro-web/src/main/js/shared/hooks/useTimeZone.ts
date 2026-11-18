import {ITimeZone} from 'shared/util/records/TimeZone';
import {useSelector} from 'react-redux';
import {config} from 'shared/util/config';

export const useTimeZone = (initialGroupId?: string): ITimeZone => {
	const {groupId} = config;

	const value = useSelector<any, any>((state) =>
		state.getIn(['projects', initialGroupId || groupId, 'data', 'timeZone'])
	);

	return value.toObject();
};
