import { Config } from 'metal-state';

const isNode = Config
	.shapeOf({
		active: Config.bool().required(),
		hasChild: Config.bool().required(),
		icon: Config.string().required(),
		layoutId: Config.string().required(),
		parentLayoutId: Config.string().required(),
		selected: Config.bool().required(),
		title: Config.string().required(),
	});

export {
	isNode,
};