import * as data from 'test/data';
import React from 'react';
import SegmentProfileCard from '../ProfileCard';
import {render} from '@testing-library/react';
import {Segment} from 'shared/util/records';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const DefaultComponent = props => (
	<SegmentProfileCard
		channelId='123'
		groupId='23'
		id='3'
		segment={data.getImmutableMock(Segment, data.mockSegment, '3')}
		{...props}
	/>
);

describe('SegmentProfileCard', () => {
	it('should render', async () => {
		const {container} = render(<WorkspacePage><DefaultComponent /></WorkspacePage>);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
