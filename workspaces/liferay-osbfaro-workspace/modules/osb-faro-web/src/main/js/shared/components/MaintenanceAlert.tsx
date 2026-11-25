import Alert, {AlertTypes} from 'shared/components/Alert';
import getCN from 'classnames';
import moment from 'moment';
import React from 'react';
import {connect} from 'react-redux';
import {formatDate, getCustomDateFormat, TIME_FORMAT} from 'shared/util/date';
import {Project} from 'shared/util/records';
import {ProjectStates} from 'shared/util/constants';
import {sub} from 'shared/util/lang';
import {usePageConfig} from 'shared/context/pageConfig';

interface IMaintenanceAlertProps {
	alertDismissed: boolean;
	className: string;
	groupId: string;
	project: Project;
	stripe: boolean;
}

export class MaintenanceAlert extends React.Component<IMaintenanceAlertProps> {
	static defaultProps = {
		stripe: false,
	};

	state = {
		dismissed: false,
	};

	constructor(props: IMaintenanceAlertProps) {
		super(props);
		this.handleDismissClick = this.handleDismissClick.bind(this);
	}

	handleDismissClick() {
		const {
			groupId,
			project: {stateStartDate},
		} = this.props;

		this.setState({dismissed: true});

		Liferay.Util.Session.set(
			`osb-faro-web_dismissedAlerts_maintenanceDate_${groupId}`,
			String(stateStartDate)
		);
	}

	render() {
		const {
			alertDismissed,
			className,
			project: {state, stateStartDate},
			stripe,
		} = this.props;

		const showAlert =
			state === ProjectStates.Scheduled &&
			!alertDismissed &&
			!this.state.dismissed;

		return (
			<div className={getCN('maintenance-alert-root', className)}>
				{showAlert && (
					<Alert
						iconSymbol="warning-full"
						onClose={this.handleDismissClick}
						stripe={stripe}
						title={Liferay.Language.get('scheduled-maintenance')}
						type={AlertTypes.Warning}
					>
						{sub(
							Liferay.Language.get(
								'a-system-wide-maintenance-has-been-scheduled-to-take-place-on-x-at-x'
							),
							[
								formatDate(
									moment(stateStartDate),
									getCustomDateFormat()
								),
								formatDate(moment(stateStartDate), TIME_FORMAT),
							]
						)}
					</Alert>
				)}
			</div>
		);
	}
}

export const mapState = (store: any, {dismissedAlerts, groupId}: any) => {
	const project = store.getIn(['projects', groupId, 'data'], new Project());

	return {
		alertDismissed:
			dismissedAlerts?.maintenanceDate === String(project.stateStartDate),
		groupId,
		project,
	};
};

const ConnectedMaintenanceAlert = connect(mapState)(MaintenanceAlert);

const MaintenanceAlertWithParams = (props: any) => {
	const {dismissedAlerts, groupId} = usePageConfig();

	return (
		<ConnectedMaintenanceAlert
			{...props}
			dismissedAlerts={dismissedAlerts}
			groupId={groupId}
		/>
	);
};

export default MaintenanceAlertWithParams;
