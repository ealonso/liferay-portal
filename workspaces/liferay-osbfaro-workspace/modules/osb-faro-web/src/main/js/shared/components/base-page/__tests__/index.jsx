import mockStore from 'test/mock-store';
import React from 'react';
import {BasePage} from '../index';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('shared/components/DocumentTitle');

describe('BasePage', () => {
	afterEach(cleanup);

	it('renders BasePage', () => {
		const {container} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<BasePage documentTitle='Test title'>
					{'Test test'}
				</BasePage>
			</Provider></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});

	it('sets the document title with Analytics Cloud for a non-LDP workspace', () => {
		render(
			<WorkspacePage><Provider store={mockStore()}>
				<BasePage documentTitle='Test title' />
			</Provider></WorkspacePage>
		);

		expect(document.title).toEqual('Test title - Analytics Cloud');
	});

	it('sets the document title with Liferay Data Platform for an LDP workspace', () => {
		render(
			<WorkspacePage ldpEnabled><Provider store={mockStore()}>
				<BasePage documentTitle='Test title' />
			</Provider></WorkspacePage>
		);

		expect(document.title).toEqual('Test title - Liferay Data Platform');
	});
});
