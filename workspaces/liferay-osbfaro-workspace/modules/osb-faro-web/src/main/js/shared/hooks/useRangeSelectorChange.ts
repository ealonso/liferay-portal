import {pickBy} from 'lodash';
import {RangeSelectors} from 'shared/types';
import {removeUriQueryParam, setUriQueryValues} from 'shared/util/url';

export const useRangeSelectorChange = () => {
	const handleRangeSelectorChange = ({
		rangeEnd,
		rangeKey,
		rangeStart,
	}: RangeSelectors) => {
		window.location.assign(
			setUriQueryValues(
				pickBy({rangeEnd, rangeKey, rangeStart}),
				removeUriQueryParam(
					window.location.href,
					'rangeEnd',
					'rangeStart'
				)
			)
		);
	};

	return {handleRangeSelectorChange};
};
