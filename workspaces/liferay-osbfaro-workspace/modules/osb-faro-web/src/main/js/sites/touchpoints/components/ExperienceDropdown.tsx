import FilterPicker from 'shared/components/FilterPicker';
import React from 'react';
import {fetchPageExperience} from 'shared/api/experiences';
import {config} from 'shared/util/config';

interface IExperienceDropdownProps {
	className?: string;
	onChange: (experienceId: string | null) => void;
}

const ExperienceDropdown: React.FC<IExperienceDropdownProps> = ({
	className,
	onChange,
}) => {
	const {channelId, groupId} = config;

	const {title, touchpoint} = config.params;

	return (
		<FilterPicker
			className={className}
			dataSourceFn={fetchPageExperience}
			entityLabel={Liferay.Language.get('experiences')}
			onFilterChange={(item) => onChange(item?.id ?? null)}
			variables={{
				canonicalUrl: touchpoint!,
				channelId: channelId!,
				groupId: groupId!,
				pageTitle: title!,
			}}
		/>
	);
};

export default ExperienceDropdown;
