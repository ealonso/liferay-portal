import {RangeKeyTimeRanges} from 'shared/util/constants';
import {RangeSelectors} from 'shared/types';
import {usePageConfig} from 'shared/context/pageConfig';

/**
 * Used to get undefined if there is no rangeKey on query
 * @returns {RangeSelectors | undefined}
 */

export const useUnsafeQueryRangeSelectors = (): RangeSelectors | undefined => {
	const {
		params: {rangeEnd, rangeKey, rangeStart},
	} = usePageConfig();

	if (!rangeKey) return;

	return {rangeEnd, rangeKey: rangeKey as RangeKeyTimeRanges, rangeStart};
};
