'use string';

const metal = require('gulp-metal');
const srcBlob = 'src/**/*.es.js';

metal.registerTasks({
	buildSrc: [srcBlob],
	formatGlobs: [srcBlob],
	lintGlobs: [srcBlob],
});