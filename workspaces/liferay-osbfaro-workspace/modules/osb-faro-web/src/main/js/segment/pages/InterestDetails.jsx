import BaseInterestDetails from 'contacts/pages/BaseInterestDetails';
import PropTypes from 'prop-types';
import React from 'react';
import {Segment} from 'shared/util/records';
import {SEGMENTS} from 'shared/util/constants';
import withPageConfig from 'shared/hoc/WithPageConfig';

class InterestDetails extends React.Component {
	static propTypes = {
		segment: PropTypes.instanceOf(Segment).isRequired
	};

	render() {
		const {segment, ...otherProps} = this.props;

		return (
			<BaseInterestDetails
				{...otherProps}
				entity={segment}
				interestDetailsURL={this.props.pageConfig.segmentInterestURL}
				interestsURL={this.props.pageConfig.segmentInterestsURL}
				type={SEGMENTS}
			/>
		);
	}
}

export default withPageConfig(InterestDetails);
