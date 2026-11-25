import accounts from './accounts';
import alerts from './alerts';
import dataSources from './data-sources';
import distributions from './distributions';
import individuals from './individuals';
import interests from './interests';
import normalizer from './normalizer';
import preferences from './preferences';
import projects from './projects';
import segments from './segments';
import store from './store';
import users from './users';
import {combineReducers} from 'redux-immutable';
import {composeReducers} from 'redux-toolbox';

export default composeReducers(
	normalizer,
	store,
	combineReducers({
		accounts,
		alerts,
		dataSources,
		distributions,
		individuals,
		interests,
		preferences,
		projects,
		segments,
		users,
	})
);
