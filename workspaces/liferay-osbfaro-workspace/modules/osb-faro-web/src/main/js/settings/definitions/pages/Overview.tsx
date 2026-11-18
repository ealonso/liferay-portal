import BasePage from 'settings/components/base-page/BasePage';
import Card from 'shared/components/Card';
import ClayLink from '@clayui/link';
import ClayList from '@clayui/list';
import React from 'react';
import {DEVELOPER_MODE} from 'shared/util/constants';
import {ENABLE_BLOCKLIST_KEYWORDS} from 'shared/util/feature-flags';
import {config} from 'shared/util/config';

type ListItem = {
	header: string;
	items: {
		description: string;
		title: string;
		url: string;
	}[];
};

// TODO: LRAC-4511 Remove developer only mode and add devItems back into items

const items = (devMode: boolean = false): (ListItem | null)[] => [
	{
		header: Liferay.Language.get('people'),
		items: [
			{
				description: Liferay.Language.get(
					'view-and-manage-the-data-model-of-your-individuals.-this-data-is-mapped-from-your-dxp,-salesforce,-or-csv-datasources'
				),
				title: Liferay.Language.get('individuals'),
				url: config.individualAttributesURL,
			},
			...(devMode
				? [
						{
							description: Liferay.Language.get(
								'view-and-manage-the-data-model-of-your-accounts.-this-data-is-automatically-mapped-from-a-salesforce-datasource'
							),
							title: Liferay.Language.get('accounts'),
							url: config.accountsURL,
						},
					]
				: []),
		],
	},
	{
		header: Liferay.Language.get('behaviors'),
		items: [
			...(devMode
				? [
						{
							description: Liferay.Language.get(
								'view-and-manage-the-tracked-behaviors-in-analytics-cloud.-you-will-also-find-instructions-for-tagging-non-liferay-assets-to-track-them-in-analytics-cloud'
							),
							title: Liferay.Language.get('behaviors'),
							url: config.behaviorsURL,
						},
					]
				: []),
			{
				description: Liferay.Language.get(
					'view-and-manage-your-default-events-custom-events-and-event-attributes'
				),
				title: Liferay.Language.get('events'),
				url: config.eventDefinitionsURL,
			},
			{
				description: Liferay.Language.get(
					'view-and-manage-the-data-model-of-your-event-attributes.-event-attributes-provide-additional-context-to-your-events'
				),
				title: Liferay.Language.get('event-attributes'),
				url: config.eventAttributesURL,
			},
			{
				description: Liferay.Language.get(
					'define-the-search-query-parameters-specific-to-your-properties'
				),
				title: Liferay.Language.get('search'),
				url: config.definitionsSearchURL,
			},
		],
	},

	ENABLE_BLOCKLIST_KEYWORDS
		? {
				header: Liferay.Language.get('derived-data'),
				items: [
					{
						description: Liferay.Language.get(
							'view-and-manage-the-blocked-keywords-for-interest-analysis.-blocked-keywords-will-affect-content-recommendations-feature-available-in-liferay-dxp'
						),
						title: Liferay.Language.get('interests'),
						url: config.interestTopicsURL,
					},
				],
			}
		: null,
];

export const Overview: React.FC = () => (
	<BasePage
		className="definitions-overview-root"
		pageDescription={Liferay.Language.get(
			'select-the-entity-to-view-its-data-model'
		)}
		pageTitle={Liferay.Language.get('definitions')}
	>
		<div className="row">
			<div className="col-xl-8">
				<Card>
					<ClayList>
						{items(DEVELOPER_MODE)
							.filter((item): item is ListItem => Boolean(item))
							.map(({header, items}) => (
								<React.Fragment key={header}>
									{header && (
										<ClayList.Header>
											{header}
										</ClayList.Header>
									)}

									{items
										.filter(Boolean)
										.map(({description, title, url}) => (
											<ClayList.Item key={title}>
												<ClayList.ItemTitle>
													<ClayLink
														decoration="none"
														href={url}
													>
														{title}
													</ClayLink>
												</ClayList.ItemTitle>

												<ClayList.ItemText>
													{description}
												</ClayList.ItemText>
											</ClayList.Item>
										))}
								</React.Fragment>
							))}
					</ClayList>
				</Card>
			</div>
		</div>
	</BasePage>
);

export default Overview;
