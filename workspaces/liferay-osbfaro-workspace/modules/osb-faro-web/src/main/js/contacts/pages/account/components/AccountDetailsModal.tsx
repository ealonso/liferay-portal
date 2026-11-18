import * as API from 'shared/api';
import ClayModal, {useModal} from '@clayui/modal';
import Loading from 'shared/components/Loading';
import React from 'react';
import {columns, FrontendDataSet} from 'shared/components/FrontendDataSet';
import {sub} from 'shared/util/lang';
import {useRequest} from 'shared/hooks/useRequest';
import {config} from 'shared/util/config';

const FDS_ID = 'account-details-attributes-dataset';

interface IAccountDetailsField {
	dataSourceId?: string;
	dataSourceName?: string;
	lastModified?: string;
	name: string;
	sourceName?: string;
	value?: string;
}

interface IAccountDetailsModalProps {
	accountId: string;
	accountName?: string;
	onClose: () => void;
}

const AccountDetailsModal: React.FC<IAccountDetailsModalProps> = ({
	accountId,
	accountName,
	onClose,
}) => {
	const {channelId = '', groupId = ''} = config;
	const {observer} = useModal({onClose});

	const {data, loading} = useRequest({
		dataSourceFn: API.accounts.fetchDetails,
		variables: {accountId, channelId, groupId},
	});

	const items: IAccountDetailsField[] = data?.items ?? [];

	return (
		<ClayModal observer={observer} size="lg">
			<ClayModal.Header>
				{sub(Liferay.Language.get('xs-attributes'), [
					accountName ?? '',
				])}
			</ClayModal.Header>

			<ClayModal.Body className="px-0">
				{loading ? (
					<div
						className="align-items-center d-flex justify-content-center"
						style={{minHeight: 400}}
					>
						<Loading center={false} />
					</div>
				) : (
					<FrontendDataSet
						customDataRenderers={{
							attributeNameAndValueRenderer: ({
								itemData,
								value,
							}: {
								itemData: {value?: string};
								value: string;
							}) =>
								columns.attributeNameAndValue({
									attributeName: value,
									value: itemData.value ?? '',
								}),
							dataSourceRenderer: ({
								itemData,
								value,
							}: {
								itemData: {dataSourceId?: string};
								value: string;
							}) =>
								columns.nameAndLinkRenderer({
									itemData: {
										id: itemData.dataSourceId ?? '',
									},
									url: config.dataSourceURL,
									value,
								}),
							lastModifiedRenderer: ({value}: {value: string}) =>
								columns.dateRenderer({
									itemData: {},
									value,
								}),
						}}
						id={FDS_ID}
						items={items}
						onItemsPropSearch={(
							item: IAccountDetailsField,
							query: string
						) =>
							item.name
								.toLowerCase()
								.includes(query.toLowerCase())
						}
						views={[
							{
								contentRenderer: 'table',
								default: true,
								label: Liferay.Language.get('default-view'),
								name: 'table',
								schema: {
									fields: [
										{
											contentRenderer:
												'attributeNameAndValueRenderer',
											fieldName: 'name',
											label: `${Liferay.Language.get(
												'attribute-name'
											)} | ${Liferay.Language.get(
												'value'
											)}`,
										},
										{
											fieldName: 'sourceName',
											label: Liferay.Language.get(
												'source-name'
											),
										},
										{
											contentRenderer:
												'dataSourceRenderer',
											fieldName: 'dataSourceName',
											label: Liferay.Language.get(
												'data-source'
											),
										},
										{
											contentRenderer:
												'lastModifiedRenderer',
											fieldName: 'modifiedDate',
											label: Liferay.Language.get(
												'last-modified'
											),
										},
									],
								},
								thumbnail: 'table',
							},
						]}
					/>
				)}
			</ClayModal.Body>
		</ClayModal>
	);
};

export default AccountDetailsModal;
