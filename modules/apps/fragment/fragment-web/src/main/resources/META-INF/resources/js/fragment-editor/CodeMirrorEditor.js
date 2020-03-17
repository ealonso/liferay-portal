/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import 'codemirror/addon/display/autorefresh';

import 'codemirror/addon/edit/closebrackets';

import 'codemirror/addon/edit/closetag';

import 'codemirror/addon/edit/matchbrackets';

import 'codemirror/addon/fold/brace-fold';

import 'codemirror/addon/fold/comment-fold';

import 'codemirror/addon/fold/foldcode';

import 'codemirror/addon/fold/foldgutter.css';

import 'codemirror/addon/fold/foldgutter';

import 'codemirror/addon/fold/indent-fold';

import 'codemirror/addon/fold/xml-fold';

import 'codemirror/addon/hint/css-hint';

import 'codemirror/addon/hint/html-hint';

import 'codemirror/addon/hint/javascript-hint';

import 'codemirror/addon/hint/show-hint.css';

import 'codemirror/addon/hint/show-hint';

import 'codemirror/addon/hint/xml-hint';

import 'codemirror/lib/codemirror.css';

import 'codemirror/mode/css/css';

import 'codemirror/mode/htmlmixed/htmlmixed';

import 'codemirror/mode/javascript/javascript';

import 'codemirror/mode/xml/xml';
import ClayIcon from '@clayui/icon';
import CodeMirror from 'codemirror';
import React, {useEffect, useMemo, useRef} from 'react';

const AUTOCOMPLETE_EXCLUDED_KEY_CODES = new Set(
	Object.values({
		ALT: 18,
		ARROW_DOWN: 40,
		ARROW_LEFT: 37,
		ARROW_RIGHT: 39,
		ARROW_UP: 38,
		BACKSPACE: 8,
		CONTROL: 17,
		ESCAPE: 27,
		META: 91,
		RETURN: 13,
		SHIFT: 16,
		SPACE: 32,
	})
);

const MODES = {
	css: {
		name: 'CSS',
		type: 'text/css',
	},
	html: {
		hint: (cm, options) => {
			const {
				customEntities,
				customEntitiesSymbolsRegex,
				customTags,
			} = options;

			const cursor = cm.getCursor();
			const token = cm.getTokenAt(cursor);

			let htmlCompletion;

			if (token.type) {
				const content = token.string;

				htmlCompletion = CodeMirror.hint.html(cm, options);

				if (!htmlCompletion) {
					return;
				}

				const resultSet = new Set(htmlCompletion.list);

				customTags.forEach(item => {
					if (
						item.name.startsWith(content) &&
						!resultSet.has(item.content)
					) {
						resultSet.add({
							displayText: item.name,
							text: item.content,
						});
					}
				});

				return {
					...htmlCompletion,
					list: Array.from(resultSet),
				};
			}
			else if (customEntities && customEntitiesSymbolsRegex) {
				const match = cm
					.getLine(cursor.line)
					.slice(0, cursor.ch)
					.match(new RegExp(customEntitiesSymbolsRegex));

				if (!match) {
					return;
				}

				const [content, start, contentName] = match;

				const customEntity = customEntities.find(
					entity => entity.start === start
				);

				const results = customEntity.content
					.filter(element => element.startsWith(contentName))
					.map(element => `${start}${element}`);

				return {
					from: CodeMirror.Pos(
						cursor.line,
						cursor.ch - content.length
					),
					list: results,
					to: CodeMirror.Pos(cursor.line, cursor.ch),
				};
			}
		},
		name: 'HTML',
		type: 'text/html',
	},
	javascript: {
		name: 'JavaScript',
		type: 'text/javascript',
	},
	json: {
		name: 'JSON',
		type: 'application/json',
	},
};

const escapeChars = string => string.replace(/[.*+\-?^${}()|[\]\\]/g, '\\$&');

const noop = () => {};

const FixedText = ({helpText, text = ''}) => {
	return (
		<div class="source-editor__fixed-text">
			<code class="source-editor__fixed-text__content">{text}</code>

			{helpText && (
				<span
					class="float-right source-editor__fixed-text__help"
					data-title={helpText}
				>
					<ClayIcon
						className="icon-monospaced"
						symbol="question-circle-full"
					/>
				</span>
			)}
		</div>
	);
};

const CodeMirrorEditor = ({
	customEntities,
	customTags,
	onChange = noop,
	mode = 'html',
	codeFooterText,
	codeHeaderText,
	codeHeaderHelpText,
	content = '',
	readOnly,
}) => {
	const editor = useRef();
	const ref = useRef();

	const customEntitiesSymbolsRegex = useMemo(() => {
		if (!customEntities) {
			return;
		}

		const start = `(${customEntities
			.map(entity => escapeChars(entity.start))
			.join('|')})`;

		const end = `([^\\s${customEntities
			.map(entity => escapeChars(entity.end))
			.join()}]*)$`;

		return `${start}${end}`;
	}, [customEntities]);

	useEffect(() => {
		if (ref.current) {
			const codeMirror = CodeMirror(ref.current, {
				autoCloseTags: true,
				autoRefresh: true,
				extraKeys: {
					'Ctrl-Space': 'autocomplete',
				},
				foldGutter: true,
				gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
				hintOptions: {
					completeSingle: false,
					customEntities,
					customEntitiesSymbolsRegex,
					customTags,
					hint: MODES[mode].hint,
				},
				indentWithTabs: true,
				inputStyle: 'contenteditable',
				lineNumbers: true,
				matchBrackets: true,
				mode: {globalVars: true, name: MODES[mode].type},
				readOnly,
				showHint: true,
				tabSize: 2,
				value: content,
				viewportMargin: Infinity,
			});

			codeMirror.on('change', cm => {
				onChange(cm.getValue());
			});

			codeMirror.on('keyup', (cm, event) => {
				if (
					!cm.state.completionActive &&
					!AUTOCOMPLETE_EXCLUDED_KEY_CODES.has(event.keyCode)
				) {
					codeMirror.showHint();
				}
			});

			editor.current = codeMirror;
		}
	}, [ref]); // eslint-disable-line

	useEffect(() => {
		if (editor.current) {
			editor.current.setOption('mode', {
				globalVars: true,
				name: MODES[mode].type,
			});

			editor.current.setOption('readOnly', readOnly);

			editor.current.setOption('hintOptions', {
				...editor.current.getOption('hintOptions'),
				customEntities,
				customEntitiesSymbolsRegex,
				customTags,
			});
		}
	}, [
		customEntities,
		customEntitiesSymbolsRegex,
		customTags,
		mode,
		readOnly,
	]);

	useEffect(() => {
		if (editor.current) {
			editor.current.setValue(content);
		}
	}, [content]);

	return (
		<>
			<nav class="source-editor-toolbar tbar">
				<ul class="tbar-nav">
					<li class="source-editor-toolbar__syntax tbar-item tbar-item-expand text-center">
						{MODES[mode].name}
					</li>
				</ul>
			</nav>

			{(codeHeaderHelpText || codeHeaderText) && (
				<FixedText
					helpText={codeHeaderHelpText}
					text={codeHeaderText}
				/>
			)}

			<div className="codemirror-editor-wrapper" ref={ref}></div>

			{codeFooterText && <FixedText text={codeFooterText} />}
		</>
	);
};

export default CodeMirrorEditor;
