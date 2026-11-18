import withAction from './WithAction';
import {fetchSegment} from '../actions/segments';
import {config} from 'shared/util/config';

export const withSegment = (includeReferencedObjects = false) =>
	withAction(
		({groupId, id}) =>
			fetchSegment({groupId, includeReferencedObjects, segmentId: id}),
		(state, {id}) => state.getIn(['segments', id]),
		{
			errorPageProps: () => ({
				href: config.segmentsURL,
				linkLabel: Liferay.Language.get('go-to-segments'),
				message: Liferay.Language.get(
					'the-segment-you-are-looking-for-does-not-exist'
				),
				subtitle: Liferay.Language.get('segment-was-not-found'),
			}),
			propName: 'segment',
		}
	);

export default withSegment();
