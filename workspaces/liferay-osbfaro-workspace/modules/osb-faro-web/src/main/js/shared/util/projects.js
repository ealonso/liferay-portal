import {PLANS} from 'shared/util/subscriptions';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

/**
 * Returns only the basic tier Projects from an array of Projects.
 * @param {Array} projects - The array of Projects.
 * @param {boolean} unconfiguredOnly - Filter flag to return only unconfigured basic tier Projects.
 * @returns {Array} - the array of basic tier Projects
 */
export function getBasicProjects(projects, unconfiguredOnly = false) {
	return projects.filter(({faroSubscription, groupId}) => {
		const subscriptionName = faroSubscription.get('name');

		return unconfiguredOnly
			? subscriptionName === PLANS.basic.name && !groupId
			: subscriptionName === PLANS.basic.name;
	});
}

/**
 * Returns the route for a single Project as a string.
 * @param {Record} Project - The Project record.
 * @param {boolean} Project.corpProjectUuid - The corpProjectUuid of the project.
 * @param {number} Project.groupId - The groupId of the project. This value will
 * be 0 if the workspace does not exist.
 * @param {string} Project.url - The URL of the workspace site.
 * @returns {string} - The route for the project as a string.
 */
export function getSingleProjectRoute({corpProjectUuid, groupId, url}) {
	return groupId
		? url
		: setUriQueryValues({corpProjectUuid}, config.addWorkspaceURL);
}
