import {gql} from '@apollo/client';

export const EXPERIMENT_DELETE_MUTATION = gql`
	mutation ExperimentDeleteMutation($experimentId: String!) {
		deleteExperiment(experimentId: $experimentId)
	}
`;
