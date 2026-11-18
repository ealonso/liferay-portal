import * as API from 'shared/api';
import Card from 'shared/components/Card';
import ClayIcon from 'shared/components/Icon';
import ClayLink from '@clayui/link';
import getCN from 'classnames';
import ListGroup from 'shared/components/list-group';
import React from 'react';
import URLConstants from 'shared/util/url-constants';
import {compose, withEmpty, withRequest} from 'shared/hoc';
import {createOrderIOMap, NAME} from 'shared/util/pagination';
import {PropTypes} from 'prop-types';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

const ITEMS_PER_CARD = 6;

function fetchInterestData({channelId, groupId, id}) {
	return API.interests.search({
		channelId,
		contactsEntityId: id,
		delta: ITEMS_PER_CARD,
		groupId,
		orderIOMap: createOrderIOMap(NAME),
		page: 1
	});
}

export const InterestsList = ({id, interests}) => (
	<ListGroup className='results-container' noBorder>
		{interests.map(({name}) => (
			<ListGroup.Item className='interest' key={name}>
				<ListGroup.ItemTitle className='text-truncate'>
					{name ? (
						<ClayLink
							decoration='none'
							href={setUriQueryValues({
								id,
								interestId: name
							}, config.individualInterestURL)}
						>
							{name}
						</ClayLink>
					) : (
						name
					)}
				</ListGroup.ItemTitle>
			</ListGroup.Item>
		))}
	</ListGroup>
);

const ListWithInterests = compose(
	withRequest(
		fetchInterestData,
		data => ({
			interests: data.items,
			total: data.total
		}),
		{
			page: false
		}
	),
	withEmpty({
		description: (
			<>
				<span className='mr-1'>
					{Liferay.Language.get(
						'check-back-later-to-verify-if-data-has-been-received-from-your-data-sources'
					)}
				</span>

				<ClayLink
					href={URLConstants.IndividualProfilesDocumentInterests}
					key='DOCUMENTATION'
					target='_blank'
				>
					{Liferay.Language.get('learn-more-about-interests')}
				</ClayLink>
			</>
		),
		spacer: true,
		title: Liferay.Language.get('no-interests-were-found')
	})
)(InterestsList);

export default class InterestsCard extends React.PureComponent {
	static propTypes = {
		channelId: PropTypes.string,
		entity: PropTypes.object.isRequired,
		groupId: PropTypes.string.isRequired
	};

	render() {
		const {
			props: {
				channelId,
				className,
				entity: {id},
				groupId
			}
		} = this;

		const classes = getCN('individual-interests-card-root', className);

		return (
			<Card className={classes}>
				<Card.Header>
					<Card.Title>
						{Liferay.Language.get('current-interests')}
					</Card.Title>
				</Card.Header>

				<ListWithInterests
					channelId={channelId}
					groupId={groupId}
					id={id}
				/>

				<Card.Footer>
					<ClayLink
						borderless
						button
						className='button-root'
						displayType='secondary'
						href={setUriQueryValues({id}, config.individualInterestsURL)}
						small
					>
						{Liferay.Language.get('view-all-interests')}

						<ClayIcon
							className='icon-root ml-2'
							symbol='angle-right-small'
						/>
					</ClayLink>
				</Card.Footer>
			</Card>
		);
	}
}
