import Card from 'shared/components/Card';
import ClayIcon from 'shared/components/Icon';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import InterestPagesList from 'contacts/components/InterestPagesList';
import React from 'react';
import {EntityTypes} from 'shared/util/constants';
import {getSafeDecodedURIComponent} from 'shared/util/util';
import {Individual} from 'shared/util/records';
import {PropTypes} from 'prop-types';
import {sub} from 'shared/util/lang';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

export default class InterestDetails extends React.Component {
	static defaultProps = {
		active: 'true'
	};

	static propTypes = {
		active: PropTypes.string,
		channelId: PropTypes.string,
		groupId: PropTypes.string.isRequired,
		id: PropTypes.string.isRequired,
		individual: PropTypes.instanceOf(Individual).isRequired,
		interestId: PropTypes.string.isRequired
	};

	getNavigationItems() {
		const {id, interestId} = this.props;

		const active = this.props.active === 'true';

		return [
			{
				active,
				href: setUriQueryValues({
					active: 'true',
					id,
					interestId
				}, config.individualInterestURL),
				label: Liferay.Language.get('active-pages')
			},
			{
				active: !active,
				href: setUriQueryValues({
					active: 'false',
					id,
					interestId
				}, config.individualInterestURL),
				label: Liferay.Language.get('inactive-pages')
			}
		];
	}

	render() {
		const {
			props: {channelId, groupId, id, interestId}
		} = this;

		const interestName = getSafeDecodedURIComponent(interestId);

		return (
			<>
				<div className='back-button-root mb-2'>
					<ClayLink
						borderless
						button
						displayType='secondary'
						href={setUriQueryValues({id}, config.individualInterestsURL)}
					>
						<ClayIcon
							className='icon-root mr-2'
							symbol='angle-left-small'
						/>

						{Liferay.Language.get('back-to-interests')}
					</ClayLink>
				</div>

				<Card pageDisplay>
					<Card.Header>
						<Card.Title>
							{sub(
								Liferay.Language.get('pages-containing-x'),
								[
									<span
										className='interest-name'
										key='INTEREST_NAME'
									>
										{`"${interestName}"`}
									</span>
								],
								false
							)}
						</Card.Title>
					</Card.Header>

					<Card.Header>
						<ClayNavigationBar
							className='page-subnav'
							triggerLabel='label'
						>
							{this.getNavigationItems().map(
								({active, href, label}) => (
									<ClayNavigationBar.Item
										active={active}
										key={label}
									>
										<ClayLink href={href}>{label}</ClayLink>
									</ClayNavigationBar.Item>
								)
							)}
						</ClayNavigationBar>
					</Card.Header>

					<InterestPagesList
						channelId={channelId}
						className='interest-history-table'
						dataSourceParams={{
							active: this.props.active === 'true',
							channelId,
							contactsEntityId: id,
							contactsEntityType: EntityTypes.Individual,
							groupId,
							interestName
						}}
						groupId={groupId}
					/>
				</Card>
			</>
		);
	}
}
