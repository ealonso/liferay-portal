import * as API from 'shared/api';
import * as data from 'test/data';
import InterestTopics from '../InterestTopics';
import mockStore, {mockStoreData, toRD} from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const defaultProps = {
	groupId: '23'
};

const Wrapper = ({children, params = {}, store = mockStore()}) => (
	<Provider store={store}>
		<WorkspacePage params={params}>{children}</WorkspacePage>
	</Provider>
);

describe('InterestTopics', () => {
	afterEach(cleanup);

	it('should render', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [data.mockBlockedKeyword(1)], total: 1})
		);

		const {container} = render(
			<Wrapper>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should render page not found if an invalid page is provided', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(
			<Wrapper params={{page: '33'}}>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render without an "add keyword" button if the user role is member', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const memberStore = mockStore(
			mockStoreData.setIn(['currentUser'], toRD('24'))
		);

		const {container, queryByText} = render(
			<Wrapper store={memberStore}>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(queryByText('Add Keyword')).toBeNull();
	});

	it('should render with an empty state', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(
			<Wrapper params={{query: 'foo'}}>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render with a message to add keywords if there are none', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(
			<Wrapper>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render with a member-specific message to add keywords if there are none', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const memberStore = mockStore(
			mockStoreData.setIn(['currentUser'], toRD('24'))
		);

		const {container} = render(
			<Wrapper store={memberStore}>
				<InterestTopics {...defaultProps} />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});
});
