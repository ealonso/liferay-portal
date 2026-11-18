jest.mock('shared/pages/ErrorPage', () => () => 'ErrorPage');

import * as API from 'shared/api';
import React from 'react';
import SegmentLinkRedirect from '../SegmentLinkRedirect';
import {act, render, screen, waitFor} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const renderSegment = ({
	channelId = '',
	params = {},
}: {
	channelId?: string;
	params?: {[name: string]: string};
} = {}) =>
	render(
		<WorkspacePage
			channelId={channelId}
			groupId="23"
			params={{id: '456', ...params}}
		>
			<SegmentLinkRedirect>{'segment profile'}</SegmentLinkRedirect>
		</WorkspacePage>
	);

describe('SegmentLinkRedirect', () => {
	const {location} = window;

	const replace = jest.fn();

	beforeEach(() => {
		jest.clearAllMocks();

		Object.defineProperty(window, 'location', {
			configurable: true,
			value: {...location, replace},
		});
	});

	afterEach(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: location,
		});
	});

	it('renders the segment when the page has a channel', () => {
		renderSegment({channelId: '789'});

		expect(screen.getByText('segment profile')).toBeInTheDocument();
		expect(API.individualSegment.fetch).not.toHaveBeenCalled();
	});

	it('replaces a link without a channel with the channel of the segment', async () => {
		(API.individualSegment.fetch as jest.Mock).mockReturnValueOnce(
			Promise.resolve({channelId: '123', id: '456'})
		);

		renderSegment();

		await waitFor(() =>
			expect(replace).toHaveBeenCalledWith(
				'/segment?channelId=123&id=456'
			)
		);
		expect(API.individualSegment.fetch).toHaveBeenCalledWith({
			groupId: '23',
			segmentId: '456',
		});
	});

	it('leaves the segment subpages alone', () => {
		renderSegment({params: {'*': 'membership'}});

		expect(screen.getByText('segment profile')).toBeInTheDocument();
		expect(API.individualSegment.fetch).not.toHaveBeenCalled();
	});

	it('renders the error page when the segment cannot be loaded', async () => {
		(API.individualSegment.fetch as jest.Mock).mockReturnValueOnce(
			Promise.reject({})
		);

		renderSegment();

		expect(await screen.findByText('ErrorPage')).toBeInTheDocument();
		expect(replace).not.toHaveBeenCalled();
	});

	it('renders the error page for a segment without a channel', async () => {
		(API.individualSegment.fetch as jest.Mock).mockReturnValueOnce(
			Promise.resolve({channelId: null, id: '456'})
		);

		renderSegment();

		expect(await screen.findByText('ErrorPage')).toBeInTheDocument();
		expect(replace).not.toHaveBeenCalled();
	});

	it('does not redirect once the user has left the page while the segment loads', async () => {
		let resolveSegment: (segment: {
			channelId: string;
			id: string;
		}) => void = () => undefined;

		(API.individualSegment.fetch as jest.Mock).mockReturnValueOnce(
			new Promise((resolve) => {
				resolveSegment = resolve;
			})
		);

		const {unmount} = renderSegment();

		unmount();

		await act(async () => {
			resolveSegment({channelId: '123', id: '456'});
		});

		expect(replace).not.toHaveBeenCalled();
	});
});
