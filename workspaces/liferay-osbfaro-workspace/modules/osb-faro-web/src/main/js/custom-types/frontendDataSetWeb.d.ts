declare module '@liferay/frontend-data-set-web' {
	import {ComponentType} from 'react';

	export enum EConfigInURLBehavior {
		OFF = 'off',
		PUSH = 'push',
		REPLACE = 'replace',
	}

	interface IEmptyState {
		description?: string;
		image?: string;
		imageReducedMotion?: string;
		symbol?: string;
		title?: string;
	}

	interface IEmptyStateConfiguration extends IEmptyState {
		filtered?: {
			filters?: IEmptyState;
			search?: IEmptyState;
			searchAndFilters?: IEmptyState;
		};
	}

	interface ILoadDataArgs {
		additionalAPIURLParameters?: string;
		apiURL: string;
		currentURL?: string;
		delta?: number;
		odataFiltersStrings?: Array<string>;
		page?: number;
		searchParam?: string;
		signal?: AbortSignal;
		sorts?: Array<any>;
	}

	interface ISnapshot {
		configuration?: any;
		erc: string;
		id?: number;
		label: string;
	}

	interface ISnapshots {
		headerVisible: boolean;
		items: Array<ISnapshot>;
		label?: string;
	}

	export interface IFrontendDataSetProps {
		additionalAPIURLParametersTransformer?: (
			loadDataArgs: ILoadDataArgs
		) => string | undefined;
		apiURL?: string;
		configInURLBehavior?: EConfigInURLBehavior;
		customDataRenderers?: any;
		emptyState?: IEmptyStateConfiguration;
		filters?: Array<any>;
		groupedFilters?: Array<any>;
		id: string;
		items?: any[];
		itemsActions?: any[];
		onItemsPropSearch?: (item: any, query: string) => boolean;
		pagination?: {
			deltas?: Array<{href?: string; label: number}>;
			initialDelta?: number;
			initialPageNumber?: number;
		};
		showManagementBar?: boolean;
		showPagination?: boolean;
		showSearch?: boolean;
		snapshots?: Array<ISnapshots>;
		snapshotsEnabled?: boolean;
		sorts?: Array<any>;
		views: Array<any>;
	}

	export const FrontendDataSet: ComponentType<IFrontendDataSetProps>;
}
