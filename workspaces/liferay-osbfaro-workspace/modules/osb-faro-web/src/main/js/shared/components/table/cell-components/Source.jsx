import DataValue from 'shared/components/DataValue';
import getCN from 'classnames';
import React from 'react';
import {isNil} from 'lodash';
import {PropTypes} from 'prop-types';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

export default class SourceCell extends React.Component {
	static propTypes = {
		data: PropTypes.shape({
			dataSourceId: PropTypes.string,
			dataSourceName: PropTypes.string
		})
	};

	render() {
		const {
			className,
			data: {dataSourceId, dataSourceName}
		} = this.props;

		return (
			<td className={getCN('table-cell-expand', className)}>
				{!isNil(dataSourceId) ? (
					<a
						className='text-truncate'
						href={setUriQueryValues({id: dataSourceId}, config.dataSourceURL)}
					>
						<DataValue value={dataSourceName} />
					</a>
				) : (
					<DataValue value={dataSourceName} />
				)}
			</td>
		);
	}
}
