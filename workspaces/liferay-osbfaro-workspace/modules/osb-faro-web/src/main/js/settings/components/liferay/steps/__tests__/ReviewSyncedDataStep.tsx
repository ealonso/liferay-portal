import DataSourceQuery from 'shared/queries/DataSourceQuery';
import React from 'react';
import {DataSourceTypes, OrderByDirections} from 'shared/util/constants';
import {MockedProvider, MockedResponse} from '@apollo/client/testing';
import {render} from '@testing-library/react';
import {ReviewSyncedDataStep} from '../ReviewSyncedDataStep';

jest.unmock('react-dom');

const defaultMock = {
	request: {
		query: DataSourceQuery,
		variables: {
			size: 1,
			sort: {column: 'createDate', type: OrderByDirections.Descending},
			type: DataSourceTypes.Liferay,
		},
	},
	result: {
		data: {
			dataSources: [
				{
					contactsSyncDetails: {selected: true},
					id: '123',
					sitesSyncDetails: {selected: false},
				},
			],
		},
	},
};

const Wrapper = ({
	children,
	mocks = [defaultMock],
}: {
	children: React.ReactNode;
	mocks?: MockedResponse[];
}) => (
	<MockedProvider addTypename={false} mocks={mocks}>
		{children}
	</MockedProvider>
);

describe('ReviewSyncedDataStep', () => {
	it('should render', () => {
		const {container} = render(
			<Wrapper>
				<ReviewSyncedDataStep onNext={jest.fn()} onPrev={jest.fn()} />
			</Wrapper>
		);

		expect(container).toMatchSnapshot();
	});
});
