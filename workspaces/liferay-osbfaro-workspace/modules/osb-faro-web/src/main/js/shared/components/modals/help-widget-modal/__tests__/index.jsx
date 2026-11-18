import HelpWidgetModal from '../index';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, screen} from '@testing-library/react';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {renderModal} from 'test/render-modal';

jest.unmock('react-dom');

const DefaultComponent = ({observer, ...props}) => (
	<Provider store={mockStore()}>
		<MockedProvider addTypename={false}>
			<HelpWidgetModal
				observer={observer}
				onClose={jest.fn()}
				{...props}
			/>
		</MockedProvider>
	</Provider>
);

describe('HelpWidgetModal', () => {
	afterEach(cleanup);

	it('Should render', async () => {
		await renderModal(observer => <DefaultComponent observer={observer} />);

		expect(document.body).toMatchSnapshot();
	});

	it('Should start on the report an issue screen', async () => {
		await renderModal(observer => <DefaultComponent observer={observer} />);

		expect(
			screen.getByRole('heading', {name: 'Report an Issue'})
		).toBeInTheDocument();
	});
});
