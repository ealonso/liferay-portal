import withAction from './WithAction';
import {fetchDataSource} from '../actions/data-sources';
import {config} from 'shared/util/config';

export default withAction(
	({groupId, id}) => fetchDataSource({groupId, id}),
	(state, {id}) => state.getIn(['dataSources', id]),
	{
		errorPageProps: () => ({
			href: config.dataSourcesURL,
			linkLabel: Liferay.Language.get('go-to-data-sources'),
			message: Liferay.Language.get(
				'the-data-source-you-are-looking-for-does-not-exist'
			),
			subtitle: Liferay.Language.get('data-source-was-not-found'),
		}),
		propName: 'dataSource',
	}
);
