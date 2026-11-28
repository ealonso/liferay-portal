import sendRequest from 'shared/util/request';
import {buildOrderByFields} from 'shared/util/pagination';

export function searchAssets({
	applicationId,
	channelId = '',
	eventId,
	groupId,
	orderIOMap,
	...otherParams
}) {
	const orderParams = orderIOMap.first();

	const orderByFields = buildOrderByFields(orderParams);

	return sendRequest({
		data: {
			...otherParams,
			applicationId,
			channelId,
			eventId,
			orderByFields,
		},
		method: 'GET',
		path: `contacts/${groupId}/activity/asset`,
	});
}
