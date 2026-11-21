import React from 'react';
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
