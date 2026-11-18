import React from 'react';
import TextTruncate from 'shared/components/TextTruncate';

interface ILinkCellProps {
	data: {id?: string; name: string};
	hrefFormatter: (data: any) => string;
}

const LinkCell = ({data, hrefFormatter}: ILinkCellProps) => (
	<td className="table-cell-expand" key={data.id}>
		<div className="table-title text-truncate">
			<a href={hrefFormatter(data)}>
				<TextTruncate title={data.name} />
			</a>
		</div>
	</td>
);

export default LinkCell;
