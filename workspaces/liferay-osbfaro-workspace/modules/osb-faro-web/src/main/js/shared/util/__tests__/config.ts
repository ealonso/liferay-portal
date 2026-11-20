import * as data from 'test/data';
import {UserRoleNames} from 'shared/util/constants';
import {buildConfig} from '../config';

const CHANNEL = {
	createTime: 0,
	groupIdCount: 0,
	id: '321',
	name: 'Test Property',
	permissionType: 0,
	tokenAuth: false,
};

describe('buildConfig', () => {
	it.each([
		['/segments', 'segmentsURL'],
		['/edit-segment', 'editSegmentURL'],
		['/data-source-onboarding', 'dataSourceOnboardingURL'],
	])('exposes the URL of the %s page as config.%s', (friendlyURL, key) => {
		const config = buildConfig({
			pageURLs: {[friendlyURL]: `/web/my-workspace${friendlyURL}`},
		});

		expect(config[key as `${string}URL`]).toBe(
			`/web/my-workspace${friendlyURL}`
		);
	});

	it('keeps the params that have a value', () => {
		const config = buildConfig({
			params: {id: '123', query: '', tabId: undefined},
		});

		expect(config.params).toEqual({id: '123'});
	});

	it('keeps the values that the renderer passes', () => {
		const config = buildConfig({
			channelId: '321',
			channels: [CHANNEL],
			currentUser: data.mockUser(23, {roleName: UserRoleNames.Owner}),
			friendlyURL: '/segment',
			groupId: '23',
			ldpEnabled: true,
			navigationItems: [{label: 'Overview'}],
			signOutURL: '/c/portal/logout',
		});

		expect(config.channelId).toBe('321');
		expect(config.channels).toEqual([CHANNEL]);
		expect(config.currentUser.id).toBe(23);
		expect(config.currentUser.isOwner()).toBe(true);
		expect(config.friendlyURL).toBe('/segment');
		expect(config.groupId).toBe('23');
		expect(config.ldpEnabled).toBe(true);
		expect(config.navigationItems).toEqual([{label: 'Overview'}]);
		expect(config.signOutURL).toBe('/c/portal/logout');
	});

	it('defaults the values that the renderer does not pass', () => {
		const config = buildConfig({});

		expect(config.channelId).toBe('');
		expect(config.channels).toBeNull();
		expect(config.currentUser.id).toBeNull();
		expect(config.friendlyURL).toBe('');
		expect(config.groupId).toBe('');
		expect(config.ldpEnabled).toBe(false);
		expect(config.navigationItems).toBeNull();
		expect(config.params).toEqual({});
		expect(config.signOutURL).toBe('');
	});

	it('freezes the config and its params', () => {
		const config = buildConfig({params: {id: '123'}});

		expect(Object.isFrozen(config)).toBe(true);
		expect(Object.isFrozen(config.params)).toBe(true);
	});
});
