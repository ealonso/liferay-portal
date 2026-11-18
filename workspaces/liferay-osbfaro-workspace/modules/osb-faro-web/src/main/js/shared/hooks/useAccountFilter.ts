import {pickBy} from 'lodash';
import {setUriQueryValues} from 'shared/util/url';
import {config} from 'shared/util/config';

type Account = {
	id: string;
	name: string;
};

interface IAccountFilter {
	accountId?: string;
	accountName?: string;
	accountQueryValues: {accountId?: string; accountName?: string};
	setAccount: (account: Account | null) => void;
}

/**
 * Reads the dashboard account filter from the URL query and writes it back
 * there. The URL is the single source of truth, so the pages rendering the
 * filter do not mirror the selection in component state.
 */

export const useAccountFilter = (): IAccountFilter => {
	const {accountId, accountName} = config.params;

	return {
		accountId,
		accountName,
		accountQueryValues: pickBy({accountId, accountName}),
		setAccount: (account) =>
			window.location.assign(
				setUriQueryValues({
					accountId: account?.id ?? null,
					accountName: account?.name ?? null,
				})
			),
	};
};
