import IndividualDetailsCard from '../DetailsCard';
import React from 'react';
import {fromJS} from 'immutable';
import {Individual} from 'shared/util/records';
import {mockIndividual} from 'test/data';
import {render} from '@testing-library/react';

jest.unmock('react-dom');

describe('IndividualDetailsCard', () => {
	it('should render', () => {
		const {container} = render(
			<IndividualDetailsCard
				entity={new Individual(fromJS(mockIndividual()))}
				groupId='23'
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
