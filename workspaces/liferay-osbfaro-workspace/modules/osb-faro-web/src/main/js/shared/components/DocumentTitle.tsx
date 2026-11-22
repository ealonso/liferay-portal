import React from 'react';
import {useEffect} from 'react';
import {usePageConfig} from 'shared/context/pageConfig';

interface IDocumentTitleProps {
	ldpEnabled?: boolean;
	loadingLDPEnabled?: boolean;
	title: string;
}

const DocumentTitle: React.FC<IDocumentTitleProps> = ({
	ldpEnabled,
	loadingLDPEnabled,
	title,
}) => {
	const config = usePageConfig();

	const LDPEnabled = ldpEnabled ?? config.ldpEnabled;

	useEffect(() => {
		if (loadingLDPEnabled) {
			document.title = title;

			return;
		}

		const defaultTitle = LDPEnabled
			? Liferay.Language.get('liferay-data-platform')
			: Liferay.Language.get('analytics-cloud');

		// Pages that render their own heading pass an empty title, so the
		// product name has to stand on its own rather than trail a dash.

		document.title = title ? `${title} - ${defaultTitle}` : defaultTitle;
	}, [LDPEnabled, loadingLDPEnabled, title]);

	return null;
};

export default DocumentTitle;
