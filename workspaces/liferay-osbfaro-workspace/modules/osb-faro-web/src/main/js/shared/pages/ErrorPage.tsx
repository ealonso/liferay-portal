import ClayLink from '@clayui/link';
import getCN from 'classnames';
import React from 'react';
import {usePageConfig} from 'shared/context/pageConfig';

interface IErrorPageProps {
	className?: string;
	href?: string;
	linkLabel?: string;
	message?: string;
	subtitle?: string;
	title?: string;
}

const ErrorPage: React.FC<IErrorPageProps> = ({
	className,
	href,
	linkLabel = Liferay.Language.get('go-to-home'),
	message = Liferay.Language.get(
		'the-page-you-are-looking-for-does-not-exist'
	),
	subtitle = Liferay.Language.get('page-was-not-found'),
	title = '404',
}) => {
	const {workspacesURL} = usePageConfig();

	return (
		<div className={getCN('error-page-root', 'page-container', className)}>
			<h1>{title}</h1>

			<h3>{subtitle}</h3>

			<p>{message}</p>

			<ClayLink
				button
				className="button-root"
				displayType="secondary"
				href={href ?? workspacesURL}
			>
				{linkLabel}
			</ClayLink>
		</div>
	);
};

export default ErrorPage;
