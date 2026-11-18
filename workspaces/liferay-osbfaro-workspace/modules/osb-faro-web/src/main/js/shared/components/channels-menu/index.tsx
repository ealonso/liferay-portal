import ClayIcon from 'shared/components/Icon';
import getCN from 'classnames';
import PickerTriggerButton from '../PickerTriggerButton';
import React from 'react';
import {connect} from 'react-redux';
import {Option, Picker} from '@clayui/core';
import {updateDefaultChannelId} from 'shared/actions/preferences';
import {usePageConfig} from 'shared/context/pageConfig';
import {setUriQueryValues} from 'shared/util/url';

export type Channel = {
	createTime: number;
	groupIdCount: number;
	id: string;
	name: string;
	permissionType: number;
	tokenAuth: boolean;
};

interface IChannelsMenuProps extends React.HTMLAttributes<HTMLElement> {
	defaultChannelId?: string;
	groupId: string;
	channels: Channel[];
	updateDefaultChannelId: ({
		defaultChannelId,
		groupId,
	}: {
		defaultChannelId: string;
		groupId: string;
	}) => void;
}

export const getDefaultChannel = (
	defaultChannelId: string | undefined,
	channels: Channel[]
) => {
	if (channels && !!channels.length) {
		return channels.find(({id}) => id === defaultChannelId) || channels[0];
	}

	return null;
};

export const isValidChannel = (
	channelId: string | undefined,
	channels: Channel[]
) =>
	!channelId || !channels.length || channels.some(({id}) => id === channelId);

/**
 * Built on `Picker` (`@clayui/core`) with `searchable`, the same idiomatic
 * pattern `StageConditionRow.renderFieldPicker` uses for "pick one item from
 * a searchable list" — `PickerTriggerButton` as the trigger, carrying only
 * the utility classes that lay the label out against the caret.
 */

export const ChannelsMenu: React.FC<IChannelsMenuProps> = ({
	channels = [],
	className,
	defaultChannelId,
	groupId,
	updateDefaultChannelId,
}) => {
	const {sitesURL} = usePageConfig();

	const channel = getDefaultChannel(defaultChannelId, channels);

	return (
		<div className={getCN('channels-menu-root', className)}>
			<div className="channels-menu-label">
				{Liferay.Language.get('property')}
			</div>

			<Picker
				aria-label={Liferay.Language.get('property')}
				as={PickerTriggerButton}
				block
				buttonClassName="d-flex justify-content-between align-items-center"
				disabled={!channel}
				items={channels}
				label={
					channel
						? channel.name
						: Liferay.Language.get('no-properties')
				}
				onSelectionChange={(key) => {
					const selected = channels.find(
						(item) => item.id === String(key)
					);

					if (!selected) {
						return;
					}

					updateDefaultChannelId({
						defaultChannelId: selected.id,
						groupId,
					});

					Liferay.Util.navigate(
						setUriQueryValues({channelId: selected.id}, sitesURL)
					);
				}}
				searchable
				selectedKey={channel ? channel.id : undefined}
				size="regular"
				width={160}
			>
				{(item) => (
					<Option key={item.id} textValue={item.name}>
						<span className="text-break">{item.name}</span>
					</Option>
				)}
			</Picker>

			<div className="channels-menu-icon">
				<ClayIcon className="icon-root" symbol="sites" />
			</div>
		</div>
	);
};

export default connect(null, {updateDefaultChannelId})(ChannelsMenu);
