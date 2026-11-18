import NameCell from './Name';
import React from 'react';
import {usePageConfig} from 'shared/context/pageConfig';
import {setUriQueryValues} from 'shared/util/url';

interface IIndividualLinksProps {
	className?: string;
	data: {
		id: string;
		individualDeleted: boolean;
		individualId: string;
		individualName: string;
		name: string;
		ownerId?: string;
	};
	disabled?: boolean;
}

const IndividualLinkCell: React.FC<IIndividualLinksProps> = ({
	className,
	data,
	disabled,
}) => {
	const {individualURL} = usePageConfig();

	const id = data.individualId || data.ownerId || data.id;

	const name = data.name || data.individualName;

	return (
		<NameCell
			className={className}
			data={{...data, id, name}}
			disabled={data.individualDeleted || disabled}
			routeFn={({data: {id}}: {data: {id: string}}) =>
				setUriQueryValues({id}, individualURL)
			}
		/>
	);
};

export default IndividualLinkCell;
