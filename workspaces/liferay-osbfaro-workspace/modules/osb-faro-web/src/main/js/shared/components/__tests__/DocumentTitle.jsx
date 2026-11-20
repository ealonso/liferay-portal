jest.unmock('../DocumentTitle');

import DocumentTitle from '../DocumentTitle';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

const getFaviconLink = () => document.querySelector("link[rel~='icon']");

describe('DocumentTitle', () => {
	afterEach(() => {
		cleanup();

		document.head.innerHTML = '';
	});

	it('should change the document title with analytics cloud appended', () => {
		render(<WorkspacePage><DocumentTitle title='test' /></WorkspacePage>);

		expect(document.title).toEqual('test - Analytics Cloud');
	});

	it('should fall back to the product name alone for an empty title', () => {
		render(<WorkspacePage><DocumentTitle title='' /></WorkspacePage>);

		expect(document.title).toEqual('Analytics Cloud');
	});

	it('should change the document title with liferay data platform appended for an LDP workspace', () => {
		render(<WorkspacePage ldpEnabled><DocumentTitle title='test' /></WorkspacePage>);

		expect(document.title).toEqual('test - Liferay Data Platform');
	});

	it('should prefer an explicit ldpEnabled over the route-derived value', () => {
		render(<WorkspacePage><DocumentTitle ldpEnabled title='test' /></WorkspacePage>);

		expect(document.title).toEqual('test - Liferay Data Platform');
	});

	it('should prefer an explicit ldpEnabled of false over the route-derived value', () => {
		render(<WorkspacePage ldpEnabled><DocumentTitle ldpEnabled={false} title='test' /></WorkspacePage>);

		expect(document.title).toEqual('test - Analytics Cloud');
	});

	it('should not name a product while the plan check is loading', () => {
		render(<WorkspacePage><DocumentTitle loadingLDPEnabled title='test' /></WorkspacePage>);

		expect(document.title).toEqual('test');
	});

	it('should name the product once the plan check resolves', () => {
		const {rerender} = render(
			<WorkspacePage ldpEnabled><DocumentTitle loadingLDPEnabled title='test' /></WorkspacePage>
		);

		rerender(
			<WorkspacePage ldpEnabled><DocumentTitle loadingLDPEnabled={false} title='test' /></WorkspacePage>
		);

		expect(document.title).toEqual('test - Liferay Data Platform');
	});

	it('should leave the favicon to the master page', () => {
		render(<WorkspacePage><DocumentTitle ldpEnabled title='test' /></WorkspacePage>);

		expect(getFaviconLink()).toBeNull();
	});
});
