import {gql} from '@apollo/client';

export interface EventAttributeValuesData {
	eventAttributeValues: string[];
	total: number;
}

export default gql`
	query EventAttributeValues(
		$channelId: String!
		$eventAttributeDefinitionId: String!
		$eventDefinitionId: String!
		$keywords: String!
		$size: Int!
		$start: Int!
	) {
		eventAttributeValues(
			channelId: $channelId
			eventAttributeDefinitionId: $eventAttributeDefinitionId
			eventDefinitionId: $eventDefinitionId
			keywords: $keywords
			size: $size
			start: $start
		) {
			eventAttributeValues
			total
		}
	}
`;
