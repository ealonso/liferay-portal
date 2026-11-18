import ClayLink from '@clayui/link';
import React from 'react';
import {Text} from '@clayui/core';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

export type IndividualHeaderData = {
	accountName: string;
	accounts?: Array<{id?: string}>;
	lastSessionCountry: string;
	properties: {email: string};
};

export const buildHeaderSubtitle = (individual: IndividualHeaderData) => {
	const {email} = individual.properties;
	const {accountName, accounts, lastSessionCountry} = individual;

	const accountId = accounts?.[0]?.id;

	const account =
		accountName && accountId ? (
			<ClayLink
				href={setUriQueryValues({id: accountId}, config.accountURL)}
			>
				{accountName}
			</ClayLink>
		) : (
			accountName
		);

	const items = [email, account, lastSessionCountry].filter(Boolean);

	return (
		<Text color="secondary" size={3}>
			{items.map((item, index) => (
				<React.Fragment key={index}>
					{index > 0 && ' | '}

					{item}
				</React.Fragment>
			))}
		</Text>
	);
};
