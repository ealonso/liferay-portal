jest.mock('shared/util/oauth', () => ({
	...jest.requireActual('shared/util/oauth'),
	getTempCredentials: jest.fn(() => Promise.resolve(null)),
}));

import {ConnectSalesforceAuth} from '../ConnectSalesforceAuth';
import React from 'react';
import {DataSource} from 'shared/util/records';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';
import {Map} from 'immutable';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('ConnectSalesforceAuth', () => {
	afterEach(() => {
		jest.restoreAllMocks();
	});

	it('opens the authorization window on a blank page', async () => {
		const openSpy = jest.spyOn(window, 'open').mockReturnValue(null);

		render(
			<WorkspacePage>
				<ConnectSalesforceAuth
					addAlert={jest.fn()}
					dataSource={
						new DataSource({
							credentials: Map({
								oAuthClientId: 'clientId',
								oAuthClientSecret: 'clientSecret',
							}),
							url: 'https://liferay.my.salesforce.com',
						})
					}
					onSubmit={jest.fn()}
				/>
			</WorkspacePage>
		);

		fireEvent.click(screen.getByRole('button', {name: 'Connect'}));

		await waitFor(() =>
			expect(openSpy).toHaveBeenCalledWith(
				'about:blank',
				'authorizeWindow',
				'width=400,height=500'
			)
		);
	});
});
