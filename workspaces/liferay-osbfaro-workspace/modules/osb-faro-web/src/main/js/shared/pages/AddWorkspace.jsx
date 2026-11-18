import AddWorkspaceForm from 'shared/components/workspaces/AddWorkspaceForm';
import autobind from 'autobind-decorator';
import getCN from 'classnames';
import React from 'react';
import WorkspacesBasePage from 'shared/components/workspaces/BasePage';
import {addAlert} from 'shared/actions/alerts';
import {Alert} from 'shared/types';
import {compose, optional, withProject} from 'shared/hoc';
import {
	configureProject,
	createProject,
	createTrialProject
} from 'shared/actions/projects';
import {connect} from 'react-redux';
import {DataSourceStates} from 'shared/util/constants';
import {Project} from '../util/records';
import {PropTypes} from 'prop-types';

export const routingFn = ({project}) => {
	if (
		project &&
		project.groupId &&
		project.state !== DataSourceStates.Unconfigured
	) {
		return project.url;
	} else {
		return null;
	}
};

export class AddWorkspace extends React.Component {
	static propTypes = {
		addAlert: PropTypes.func,
		createProject: PropTypes.func,
		project: PropTypes.instanceOf(Project)
	};

	componentDidMount() {
		const route = routingFn(this.props);

		if (route) {
			window.location.replace(route);
		}
	}

	@autobind
	handleSubmit({
		emailAddressDomains,
		friendlyURL,
		incidentReportEmailAddresses,
		name,
		serverLocation,
		timeZoneId
	}) {
		const {
			addAlert,
			configureProject,
			corpProjectUuid,
			createProject,
			createTrialProject,
			project: {groupId, state} = {}
		} = this.props;

		const params = {
			emailAddressDomains,
			friendlyURL: friendlyURL && `/${friendlyURL}`,
			incidentReportEmailAddresses,
			name,
			timeZoneId,
			...(state === DataSourceStates.Unconfigured
				? {groupId}
				: {corpProjectUuid, serverLocation})
		};

		const createFn =
			state === DataSourceStates.Unconfigured
				? configureProject
				: corpProjectUuid
				? createProject
				: createTrialProject;

		return createFn(params)
			.then(({payload: {url}}) => {
				addAlert({
					alertType: Alert.Types.Success,
					message: Liferay.Language.get('success')
				});

				window.location.replace(url);
			})
			.catch(error => {
				if (!error.field) {
					addAlert({
						alertType: Alert.Types.Error,
						message: error.message,
						timeout: false
					});
				}

				return Promise.reject(error);
			});
	}

	render() {
		const {className, project} = this.props;

		if (routingFn(this.props)) {
			return null;
		}

		return (
			<div
				className={getCN('add-workspace-root', className)}
				key='AddWorkspace'
			>
				<WorkspacesBasePage
					title={Liferay.Language.get('create-workspace')}
				>
					<AddWorkspaceForm
						onSubmit={this.handleSubmit}
						project={project}
					/>
				</WorkspacesBasePage>
			</div>
		);
	}
}

export default compose(
	connect(null, {
		addAlert,
		configureProject,
		createProject,
		createTrialProject
	}),
	optional(withProject, {idPropName: 'corpProjectUuid'})
)(AddWorkspace);
