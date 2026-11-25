import * as API from 'shared/api';
import * as breadcrumbs from 'shared/util/breadcrumbs';
import autobind from 'autobind-decorator';
import BasePage from 'settings/components/base-page/BasePage';
import DeleteDataSource from 'settings/components/DeleteDataSource';
import getCN from 'classnames';
import React from 'react';
import {addAlert} from 'shared/actions/alerts';
import {Alert} from 'shared/types';
import {
	compose,
	withDataSource,
	withRequest,
	withSheet
} from 'shared/hoc';
import {connect} from 'react-redux';
import {DataSource} from 'shared/util/records';
import {deleteDataSource} from 'shared/actions/data-sources';
import {PropTypes} from 'prop-types';
import {sub} from 'shared/util/lang';
import {truncate} from 'lodash';
import withPageConfig from 'shared/hoc/WithPageConfig';

const WrappedDeleteDataSource = compose(
	withSheet(),
	withRequest(
		API.dataSource.fetchDeletePreview,
		data => ({entitiesCount: data}),
		{
			page: false
		}
	)
)(DeleteDataSource);

export class Delete extends React.Component {
	static propTypes = {
		addAlert: PropTypes.func.isRequired,
		dataSource: PropTypes.instanceOf(DataSource).isRequired,
		deleteDataSource: PropTypes.func.isRequired,
		entitiesCount: PropTypes.object,
		groupId: PropTypes.string.isRequired,
		id: PropTypes.string.isRequired
	};

	@autobind
	deleteDataSource() {
		const {
			addAlert,
			dataSource: {name},
			deleteDataSource,
			groupId,
			id
		} = this.props;

		return deleteDataSource({
			groupId,
			id
		})
			.then(() => {
				addAlert({
					alertType: Alert.Types.Default,
					message: sub(
						Liferay.Language.get(
							'x-is-currently-being-removed-from-analytics-cloud'
						),
						[truncate(name, {length: 50})]
					)
				});

				Liferay.Util.navigate(
					this.props.pageConfig.dataSourcesURL
				);
			})
			.catch(() => {
				addAlert({
					alertType: Alert.Types.Error,
					message: Liferay.Language.get('error'),
					timeout: false
				});
			});
	}

	render() {
		const {className, dataSource, entitiesCount, groupId, id} = this.props;

		return (
			<BasePage
				breadcrumbItems={[
					breadcrumbs.getDataSources(this.props.pageConfig),
					breadcrumbs.getDataSourceName({
						id,
						label: dataSource.name
					}, this.props.pageConfig),
					{
						active: true,
						label: Liferay.Language.get('delete-data-source')
					}
				]}
				className={getCN('data-source-delete-root', className)}
				documentTitle={sub(
					Liferay.Language.get('confirm-removal-of-x'),
					[dataSource.name]
				)}
				groupId={groupId}
				pageDescription={Liferay.Language.get(
					'removing-this-data-source-will-impact-the-following-data-and-can-yield-unexpected-results'
				)}
				pageTitle={sub(Liferay.Language.get('confirm-removal-of-x'), [
					dataSource.name
				])}
			>
				<div className='page-container'>
					<WrappedDeleteDataSource
						actionRequestFn={this.deleteDataSource}
						dataSource={dataSource}
						deleteMessage={Liferay.Language.get(
							'to-complete-copy-the-following-sentence-to-confirm-your-action-and-click-delete-data-source-once-you-delete-this-data-source-you-cannot-undo-this-operation'
						)}
						deletePhrase={Liferay.Language.get('remove-x')}
						entitiesCount={entitiesCount}
						groupId={groupId}
						id={id}
						pageActionConfirmationText={sub(
							Liferay.Language.get(
								'are-you-sure-you-want-to-delete-x'
							),
							[dataSource.name]
						)}
						pageActionText={Liferay.Language.get(
							'delete-data-source'
						)}
					/>
				</div>
			</BasePage>
		);
	}
}

export default compose(
	withDataSource,
	connect(null, {addAlert, deleteDataSource}),
	withPageConfig
)(Delete);
