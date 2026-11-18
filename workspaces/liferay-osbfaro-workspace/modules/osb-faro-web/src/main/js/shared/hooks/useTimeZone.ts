import {ITimeZone} from 'shared/util/records/TimeZone';
import {useSelector} from 'react-redux';
import {usePageConfig} from 'shared/context/pageConfig';

export const useTimeZone = (initialGroupId?: string): ITimeZone => {
	const {groupId} = usePageConfig();

	const value = useSelector<any, any>((state) =>
		state.getIn(['projects', initialGroupId || groupId, 'data', 'timeZone'])
	);

	return value.toObject();
};
