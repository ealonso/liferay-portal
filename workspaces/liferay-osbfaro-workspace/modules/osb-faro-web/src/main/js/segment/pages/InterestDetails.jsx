import BaseInterestDetails from 'contacts/pages/BaseInterestDetails';
import PropTypes from 'prop-types';
import React from 'react';
import {Segment} from 'shared/util/records';
import {SEGMENTS} from 'shared/util/constants';
import {config} from 'shared/util/config';

export default class InterestDetails extends React.Component {
	static propTypes = {
		segment: PropTypes.instanceOf(Segment).isRequired
	};

	render() {
		const {segment, ...otherProps} = this.props;

		return (
			<BaseInterestDetails
				{...otherProps}
				entity={segment}
				interestDetailsURL={config.segmentInterestURL}
				interestsURL={config.segmentInterestsURL}
				type={SEGMENTS}
			/>
		);
	}
}
