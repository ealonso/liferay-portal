/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayForm, {ClaySelectWithOption} from '@clayui/form';
import ClayModal from '@clayui/modal';
import {addParams} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useMemo, useRef, useState} from 'react';

import {useConstants} from '../contexts/ConstantsContext';

export function PreviewModal({observer}) {
	const {
		displayTemplateOptions,
		portletNamespace,
		previewSiteNavigationMenuURL,
	} = useConstants();

	const displayTemplateSelectId = `${portletNamespace}-displayTemplateSelect`;

	const [displayTemplateId, setDisplayTemplateId] = useState(
		displayTemplateOptions[0].value
	);

	const previewURL = useMemo(
		() =>
			addParams(
				{
					[`${portletNamespace}ddmTemplateKey`]: displayTemplateId,
				},
				previewSiteNavigationMenuURL
			),
		[displayTemplateId, portletNamespace, previewSiteNavigationMenuURL]
	);

	const iframeRef = useRef();

	return (
		<ClayModal observer={observer}>
			<ClayModal.Header>
				{Liferay.Language.get('preview-menu')}
			</ClayModal.Header>

			<ClayModal.Body>
				<ClayForm.Group>
					<label htmlFor={displayTemplateSelectId}>
						{Liferay.Language.get('display-template')}
					</label>

					<ClaySelectWithOption
						id={displayTemplateSelectId}
						onChange={(event) =>
							setDisplayTemplateId(event.target.value)
						}
						options={displayTemplateOptions}
						value={displayTemplateId}
					/>

					<iframe
						className="border-0 mt-4 navigation-menu-iframe w-100"
						onLoad={() => {
							iframeRef.current.contentDocument.body.addEventListener(
								'click',
								(event) => event.preventDefault()
							);
						}}
						ref={iframeRef}
						src={previewURL}
					/>
				</ClayForm.Group>
			</ClayModal.Body>
		</ClayModal>
	);
}

PreviewModal.propTypes = {
	observer: PropTypes.object.isRequired,
};
