import React from 'react';
import ReactDOM from 'react-dom';
import TypeTrendCard from '../TypeTrendCard';
import {cleanup, render} from '@testing-library/react';
import {MockedProvider} from '@apollo/client/testing';
import {mockIndividualMetricsReq} from 'test/graphql-data';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

ReactDOM.createPortal = jest.fn();

describe('TypeTrendCard', () => {
	afterEach(cleanup);

	xit('should render', () => {
		const {container} = render(
			<MockedProvider mocks={[mockIndividualMetricsReq()]}>
				<WorkspacePage channelId='123123'>
					<TypeTrendCard channelId='123123' />
				</WorkspacePage>
			</MockedProvider>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});
});
