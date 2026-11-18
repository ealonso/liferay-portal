jest.unmock('react-dom');

import CreateMappingModal from '../CreateMappingModal';
import mockStore from 'test/mock-store';
import React from 'react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {noop} from 'lodash';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {useModal} from '@clayui/modal';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {waitForModal} from 'test/render-modal';

const TestModal = props => {
	const {observer} = useModal({defaultOpen: true});

	return <CreateMappingModal observer={observer} {...props} />;
};

describe('CreateMappingModal', () => {
	it('should render', async () => {
		render(
			<Provider store={mockStore()}>
				<MockedProvider
					cache={
						new InMemoryCache({
							addTypename: false
						})
					}
					mocks={[]}
				>
					<TestModal groupId='23' onClose={noop} />
				</MockedProvider>
			</Provider>
		);

		await waitForModal();

		await waitForLoadingToBeRemoved(document.body);

		expect(document.body).toMatchSnapshot();
	});
});
