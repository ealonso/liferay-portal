import * as data from 'test/data';
import client from 'shared/apollo/client';
import mockStore from 'test/mock-store';
import Overview from '../Overview';
import React from 'react';
import {ApolloProvider} from '@apollo/client';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {Segment} from 'shared/util/records';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const segment = new Segment(
	data.mockSegment(0, {
		criteriaString: "(demographics/middleName/value eq 'additionalName')",
		lastMembershipUpdateDate: 1700000000000,
		referencedObjects: {
			fieldMappings: {
				individual: {
					demographics: {
						middleName: {
							context: 'demographics',
							dataSourceFieldNames: {},
							displayName: 'Middle Name',
							displayType: 'input-field',
							id: 'middleName',
							name: 'additionalName',
							ownerType: 'individual',
							rawType: 'Text',
							type: 'text'
						}
					}
				}
			}
		}
	})
);

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({
		timeZoneId: 'UTC'
	})
}));

describe('SegmentOverview', () => {
	it('should render', async () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<ApolloProvider client={client}>
					<WorkspacePage params={{id: '0'}}>
						<Overview groupId='23' segment={segment} />
					</WorkspacePage>
				</ApolloProvider>
			</Provider>
		);

		await waitForLoadingToBeRemoved(container).then(() => {
			expect(container).toMatchSnapshot();
		});
	});

	it('should render the composition counts as processing while the membership is processing', async () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<ApolloProvider client={client}>
					<WorkspacePage params={{id: '0'}}>
						<Overview
							groupId='23'
							segment={segment.set('lastMembershipUpdateDate', null)}
						/>
					</WorkspacePage>
				</ApolloProvider>
			</Provider>
		);

		await waitForLoadingToBeRemoved(container);

		const compositionCard = container.querySelector(
			'.composition-card-root'
		);

		expect(compositionCard).toHaveTextContent('Processing');
		expect(compositionCard).not.toHaveTextContent('0%');
	});
});
