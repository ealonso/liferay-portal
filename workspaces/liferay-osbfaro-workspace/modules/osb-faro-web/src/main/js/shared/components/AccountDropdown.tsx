import * as API from 'shared/api';
import FilterPicker, {IFilterPickerItem} from './FilterPicker';
import React, {useMemo} from 'react';
import {getSafeDecodedURIComponent, getSafeTouchpoint} from 'shared/util/util';
import {IPaginatedDataSourceParams} from 'shared/hooks/usePaginatedRequest';
import {config} from 'shared/util/config';

interface IAccountDropdownProps {
	assetType?: string;
	campaignTouched?: boolean;
	className?: string;
	initialAccountId?: string | null;
	initialAccountName?: string | null;
	onFilterChange: (item: IFilterPickerItem | null) => void;
	rangeQueryValues?: Record<string, string | null>;
}

const AccountDropdown: React.FC<IAccountDropdownProps> = ({
	assetType,
	campaignTouched,
	className,
	initialAccountId,
	initialAccountName,
	onFilterChange,
	rangeQueryValues,
}) => {
	const {channelId, groupId} = config;

	const {assetId = '', title = '', touchpoint = ''} = config.params;

	// The account comes from the URL, which may name one that is not on the
	// fetched page, so it is passed as the selection rather than looked up.

	const selected = useMemo(
		() =>
			initialAccountId
				? {
						id: initialAccountId,
						name: initialAccountName || initialAccountId,
					}
				: null,
		[initialAccountId, initialAccountName]
	);

	// `account-names` pages from zero and reports its total as `totalCount`.

	const paginatedDataSourceFn = ({
		page,
		pageSize,
		query,
	}: IPaginatedDataSourceParams) =>
		API.accounts
			.searchAccounts({
				assetId: assetType
					? assetType === 'page'
						? getSafeTouchpoint(touchpoint)
						: getSafeDecodedURIComponent(assetId)
					: undefined,
				assetTitle: assetType
					? getSafeDecodedURIComponent(title)
					: undefined,
				assetType,
				campaignTouched,
				channelId,
				groupId,
				page: page - 1,
				pageSize,
				query,
				rangeEnd: rangeQueryValues?.rangeEnd,
				rangeKey: rangeQueryValues?.rangeKey,
				rangeStart: rangeQueryValues?.rangeStart,
			})
			.then(({items, totalCount}) => ({items, total: totalCount}));

	return (
		<FilterPicker
			className={className}
			entityLabel={Liferay.Language.get('accounts')}
			onFilterChange={onFilterChange}
			paginatedDataSourceFn={paginatedDataSourceFn}
			selected={selected}
		/>
	);
};

export default AccountDropdown;
