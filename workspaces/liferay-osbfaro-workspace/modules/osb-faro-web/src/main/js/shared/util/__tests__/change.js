import {getNetChange} from '../change';

describe('change', () => {
	describe('getNetChange', () => {
		it.each`
			prev    | cur     | expected
			${0}    | ${100}  | ${['+100', 100]}
			${null} | ${100}  | ${['+100', 100]}
			${0}    | ${null} | ${undefined}
			${80}   | ${100}  | ${['+20', 25]}
			${80}   | ${40}   | ${['-40', 50]}
		`(
			'should calculate the net change of $prev to $cur and return $expected',
			({cur, expected, prev}) => {
				expect(getNetChange(prev, cur)).toEqual(expected);
			}
		);
	});
});
