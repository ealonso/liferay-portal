import {setUriQueryValues} from 'shared/util/url';
import {usePageConfig} from 'shared/context/pageConfig';

type Campaign = {
	id: string;
	name: string;
};

interface ICampaignFilter {
	campaignId?: string;
	campaignName?: string;
	setCampaign: (campaign: Campaign | null) => void;
}

/**
 * Reads the campaign filter from the URL query and writes it back there. The
 * URL is the single source of truth, so the pages rendering the filter do not
 * mirror the selection in component state.
 */

export const useCampaignFilter = (): ICampaignFilter => {
	const {
		params: {campaignId, campaignName},
	} = usePageConfig();

	return {
		campaignId,
		campaignName,
		setCampaign: (campaign) =>
			Liferay.Util.navigate(
				setUriQueryValues({
					campaignId: campaign?.id ?? null,
					campaignName: campaign?.name ?? null,
				})
			),
	};
};
