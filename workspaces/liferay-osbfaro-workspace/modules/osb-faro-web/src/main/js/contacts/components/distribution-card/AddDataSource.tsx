import Card from 'shared/components/Card';
import ClayLink from '@clayui/link';
import NoResultsDisplay from 'shared/components/NoResultsDisplay';
import React from 'react';
import {usePageConfig} from 'shared/context/pageConfig';

const AddDataSource: React.FC = () => {
	const {dataSourcesURL} = usePageConfig();

	return (
		<>
			<Card.Header>
				<Card.Title>{Liferay.Language.get('distribution')}</Card.Title>
			</Card.Header>

			<Card.Body>
				<NoResultsDisplay
					description={Liferay.Language.get(
						'convert-and-enrich-anonymous-individuals-to-known-individuals-with-attributes-from-another-data-source'
					)}
					primary
					title=""
				>
					<ClayLink
						button
						className="button-root"
						displayType="primary"
						href={dataSourcesURL}
					>
						{Liferay.Language.get('add-data-source')}
					</ClayLink>
				</NoResultsDisplay>
			</Card.Body>
		</>
	);
};

export default AddDataSource;
