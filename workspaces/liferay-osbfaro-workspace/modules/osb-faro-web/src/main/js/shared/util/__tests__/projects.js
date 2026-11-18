import * as data from 'test/data';
import {fromJS} from 'immutable';
import {getBasicProjects, getSingleProjectRoute} from '../projects';
import {PLANS, SubscriptionNames} from 'shared/util/subscriptions';
import {Project} from 'shared/util/records';

const corpProjectUuid = 'corpProjectUuid';

const mockProjects = [
	new Project(
		fromJS(
			data.mockProject(0, {
				corpProjectUuid,
				faroSubscription: data.mockSubscription({
					name: SubscriptionNames.LiferayAnalyticsCloudBasic,
				}),
				name: '',
			})
		)
	),
	new Project(
		fromJS(
			data.mockProject(124, {
				faroSubscription: data.mockSubscription({
					name: SubscriptionNames.LiferayAnalyticsCloudBusiness,
				}),
				name: 'Project B',
			})
		)
	),
	new Project(
		fromJS(
			data.mockProject(125, {
				faroSubscription: data.mockSubscription({
					name: SubscriptionNames.LiferayAnalyticsCloudBasic,
				}),
				name: 'Project C',
				url: '/web/project-c',
			})
		)
	),
];

describe('projects', () => {
	describe('getBasicProjects', () => {
		it('should return only basic projects given a list of projects', () => {
			const projects = getBasicProjects(mockProjects);

			projects.map(({faroSubscription}) =>
				expect(faroSubscription.get('name')).toEqual(PLANS.basic.name)
			);
		});

		it('should return only basic unconfigured projects given a list of projects and if the unconfigured argument is true', () => {
			const projects = getBasicProjects(mockProjects, true);

			expect(projects.length).toBe(1);

			expect(projects[0].corpProjectUuid).toEqual(corpProjectUuid);
		});
	});

	describe('getSingleProjectRoute', () => {
		it('should return the homepage route for a single project that is configured', () => {
			const route = getSingleProjectRoute(mockProjects[2]);

			expect(route).toEqual('/web/project-c');
		});

		it('should return the create workspace route for a single project that is unconfigured', () => {
			const route = getSingleProjectRoute(mockProjects[0]);

			expect(route).toEqual(
				'/add-workspace?corpProjectUuid=corpProjectUuid'
			);
		});
	});
});
