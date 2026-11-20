import React from 'react';
import {renderHook} from '@testing-library/react';
import {useCampaignFilter} from '../useCampaignFilter';
import {WorkspacePage} from 'test/mock-page-context';

const renderFilter = (params: {[name: string]: string} = {}) =>
	renderHook(() => useCampaignFilter(), {
		wrapper: ({children}: {children?: React.ReactNode}) => (
			<WorkspacePage params={params}>{children}</WorkspacePage>
		),
	});

describe('useCampaignFilter', () => {
	const {location} = window;

	const navigate = Liferay.Util.navigate as jest.Mock;

	const mockLocation = (search: string) => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: {
				href: `http://localhost/campaigns${search}`,
			},
		});
	};

	afterEach(() => {
		navigate.mockReset();

		Object.defineProperty(window, 'location', {
			configurable: true,
			value: location,
		});
	});

	it('should read the campaign from the page params', () => {
		const {result} = renderFilter({
			campaignId: '7',
			campaignName: 'Spring',
		});

		expect(result.current.campaignId).toBe('7');
		expect(result.current.campaignName).toBe('Spring');
	});

	it('should write the selected campaign to the URL', () => {
		mockLocation('');

		const {result} = renderFilter();

		result.current.setCampaign({id: '7', name: 'Spring'});

		expect(navigate).toHaveBeenCalledWith(
			'/campaigns?campaignId=7&campaignName=Spring'
		);
	});

	it('should clear the campaign from the URL', () => {
		mockLocation('?campaignId=7&campaignName=Spring&rangeKey=30');

		const {result} = renderFilter({
			campaignId: '7',
			campaignName: 'Spring',
		});

		result.current.setCampaign(null);

		expect(navigate).toHaveBeenCalledWith('/campaigns?rangeKey=30');
	});
});
