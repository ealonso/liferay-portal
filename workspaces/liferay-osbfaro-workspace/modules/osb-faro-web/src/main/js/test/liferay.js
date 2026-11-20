import lang from './lang';

global.Liferay = {
	detach: jest.fn(),
	FeatureFlags: {},
	Language: {
		available: {
			en_US: 'English (United States)',
			es_ES: 'español (España)',
			ja_JP: '日本語 (日本)',
			pt_BR: 'português (Brasil)',
		},
		get: lang,
	},
	on: jest.fn(),
	Util: {
		fetch: () =>
			Promise.resolve({
				json: () => Promise.resolve({items: []}),
			}),
		navigate: jest.fn(),
		Session: {
			set: () => Promise.resolve(),
		},
	},
};
