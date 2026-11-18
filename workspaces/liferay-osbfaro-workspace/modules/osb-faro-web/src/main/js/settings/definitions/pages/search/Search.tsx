import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import SearchCard from './SearchCard';
import {getDefinitions} from 'shared/util/breadcrumbs';
import {usePageConfig} from 'shared/context/pageConfig';

export const Search: React.FC = () => {
	const config = usePageConfig();

	return (
		<BasePage
			breadcrumbItems={[
				getDefinitions(config),
				{active: true, label: Liferay.Language.get('search')},
			]}
			pageDescription={Liferay.Language.get(
				'collect-your-propertys-search-data-by-defining-search-query-parameters'
			)}
			pageTitle={Liferay.Language.get('search')}
		>
			<SearchCard />
		</BasePage>
	);
};

export default Search;
