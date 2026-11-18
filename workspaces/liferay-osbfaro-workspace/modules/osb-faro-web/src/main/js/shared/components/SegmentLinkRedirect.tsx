import * as API from 'shared/api';
import ErrorPage from 'shared/pages/ErrorPage';
import Loading from 'shared/components/Loading';
import React, {useEffect, useState} from 'react';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

interface ISegmentLinkRedirectProps {
	children: React.ReactNode;
}

/**
 * Segment links that leave out the channel still resolve: the segment is
 * fetched to learn its channel, and the URL is replaced with the one that
 * names it. A segment without a channel has no page to land on.
 */
const SegmentLinkRedirect: React.FC<ISegmentLinkRedirectProps> = ({
	children,
}) => {
	const {channelId, groupId = ''} = config;

	const {'*': subpath, id = ''} = config.params;

	const [error, setError] = useState(false);

	const redirect = !channelId && !subpath;

	useEffect(() => {
		if (!redirect) {
			return;
		}

		let ignore = false;

		API.individualSegment
			.fetch({groupId, segmentId: id})
			.then((segment: {channelId: string | null; id: string}) => {
				if (ignore) {
					return;
				}

				if (!segment.channelId) {
					setError(true);

					return;
				}

				window.location.replace(
					setUriQueryValues(
						{
							channelId: segment.channelId,
							id: segment.id,
						},
						config.segmentURL
					)
				);
			})
			.catch(() => {
				if (!ignore) {
					setError(true);
				}
			});

		return () => {
			ignore = true;
		};
	}, [groupId, id, redirect]);

	if (!redirect) {
		return <>{children}</>;
	}

	if (error) {
		return <ErrorPage />;
	}

	return <Loading />;
};

export default SegmentLinkRedirect;
