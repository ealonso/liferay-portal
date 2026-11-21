import ErrorPage from 'shared/pages/ErrorPage';
import React from 'react';
import {Outlet} from 'react-router-dom';
import {usePageConfig} from 'shared/context/pageConfig';

interface ILDPElementProps {
	children: React.ReactNode;
	fallback: React.ReactNode;
}

export const LDPElement: React.FC<ILDPElementProps> = ({
	children,
	fallback,
}) => {
	const {ldpEnabled} = usePageConfig();

	return <>{ldpEnabled ? children : fallback}</>;
};

const LDPLayout: React.FC = () => (
	<LDPElement fallback={<ErrorPage />}>
		<Outlet />
	</LDPElement>
);

export default LDPLayout;
