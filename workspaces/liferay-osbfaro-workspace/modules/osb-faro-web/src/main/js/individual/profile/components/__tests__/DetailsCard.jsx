import IndividualDetailsCard from '../DetailsCard';
import React from 'react';
import {fromJS} from 'immutable';
import {Individual} from 'shared/util/records';
import {mockIndividual} from 'test/data';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('IndividualDetailsCard', () => {
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><IndividualDetailsCard
				entity={new Individual(fromJS(mockIndividual()))}
				groupId='23'
			/></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});
});
