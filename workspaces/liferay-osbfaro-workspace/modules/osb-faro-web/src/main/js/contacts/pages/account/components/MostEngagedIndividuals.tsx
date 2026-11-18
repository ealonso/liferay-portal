import Card from 'shared/components/Card';
import ClayLink from '@clayui/link';
import IndividualsDataSet from './IndividualsDataSet';
import React from 'react';
import {DropdownRangeKey} from 'shared/components/dropdown-range-key/DropdownRangeKey';
import {isString, pickBy} from 'lodash';
import {Text} from '@clayui/core';
import {useQueryRangeSelectors} from 'shared/hooks/useQueryRangeSelectors';
import {useRangeSelectorChange} from 'shared/hooks/useRangeSelectorChange';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

const MostEngagedIndividuals: React.FC = () => {
	const {id} = config.params;

	const rangeSelectors = useQueryRangeSelectors();

	const {handleRangeSelectorChange} = useRangeSelectorChange();

	return (
		<Card minHeight={260} testId="most-engaged-individuals">
			<Card.Title className="align-items-center d-flex justify-content-between p-3">
				<Text size={5} weight="semi-bold">
					{Liferay.Language.get(
						'most-engaged-individuals'
					).toUpperCase()}
				</Text>

				<DropdownRangeKey
					legacy={false}
					onRangeSelectorChange={handleRangeSelectorChange}
					rangeSelectors={rangeSelectors}
				/>
			</Card.Title>

			<Card.Body className="p-0">
				<IndividualsDataSet preview />
			</Card.Body>

			{id && (
				<Card.Footer className="d-flex justify-content-end">
					<ClayLink
						borderless
						button
						className="button-root rounded-lg"
						displayType="primary"
						href={setUriQueryValues(
							{
								...pickBy(rangeSelectors, isString),
								id,
							},
							config.accountProfileURL
						)}
						small
					>
						{Liferay.Language.get('view-all')}
					</ClayLink>
				</Card.Footer>
			)}
		</Card>
	);
};

export default MostEngagedIndividuals;
