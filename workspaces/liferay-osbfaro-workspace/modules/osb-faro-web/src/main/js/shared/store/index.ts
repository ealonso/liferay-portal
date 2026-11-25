import {DEVELOPER_MODE} from 'shared/util/constants';

let configureStore = require('./store-prod').default;

if (DEVELOPER_MODE) {
	configureStore = require('./store-dev').default;
}

const store = configureStore();

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
