export function updateSearchParams(
	key: string,
	value: number | string | undefined
) {
	const params = new URLSearchParams(window.location.search);

	params.set(key, String(value));

	window.history.replaceState(
		null,
		'',
		`${window.location.pathname}?${params.toString()}`
	);
}
