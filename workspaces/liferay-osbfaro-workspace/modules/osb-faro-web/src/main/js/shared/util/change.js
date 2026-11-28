import {isFinite, isNaN} from 'lodash';
import {toLocale} from 'shared/util/numbers';

/**
 * Get net change value for display.
 * @param {number} prev - The previous value.
 * @param {number} current - The current value.
 * @return {[string, number]} An array with the first item being
 * the string representation of net change and the second item
 * being the numerical percentage change.
 */
export function getNetChange(prev, current) {
	const change = current - prev;

	if (!change) {
		return;
	}

	const percentage = Math.abs(Math.floor((change / prev) * 100));

	return [
		change > 0 ? `+${toLocale(change)}` : `-${toLocale(Math.abs(change))}`,
		isFinite(percentage) ? percentage : isNaN(percentage) ? 0 : 100,
	];
}
