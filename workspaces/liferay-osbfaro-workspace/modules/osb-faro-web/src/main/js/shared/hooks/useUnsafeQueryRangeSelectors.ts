import {RangeKeyTimeRanges} from 'shared/util/constants';
import {RangeSelectors} from 'shared/types';
import {config} from 'shared/util/config';

/**
 * Used to get undefined if there is no rangeKey on query
 * @returns {RangeSelectors | undefined}
 */

export const useUnsafeQueryRangeSelectors = (): RangeSelectors | undefined => {
	const {rangeEnd, rangeKey, rangeStart} = config.params;

	if (!rangeKey) return;

	return {rangeEnd, rangeKey: rangeKey as RangeKeyTimeRanges, rangeStart};
};
