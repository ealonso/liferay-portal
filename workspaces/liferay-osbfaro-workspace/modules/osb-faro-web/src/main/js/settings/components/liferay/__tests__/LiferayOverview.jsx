import * as data from 'test/data';
import LiferayOverview from '../LiferayOverview';
import mockStore from 'test/mock-store';
import React from 'react';
import {DataSource} from 'shared/util/records';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useCurrentUser', () => ({
	useCurrentUser: () => ({
		isAdmin: () => true
	})
}));

jest.mock('shared/hooks/useRequest', () => ({
	useRequest: () => ({
		data: {
			channelsCount: 5,
			groupsCount: 10,
			individualsCount: 100
		},
		loading: false
	})
}));

const defaultProps = {
	dataSource: new DataSource(data.mockLiferayDataSource())
};

const WrappedComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage params={{id: 'test'}}>
			<MockedProvider>
				<LiferayOverview {...defaultProps} {...props} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('LiferayOverview', () => {
	it('should render', async () => {
		const {container} = render(<WrappedComponent />);

		expect(container).toMatchSnapshot();
	});
});
