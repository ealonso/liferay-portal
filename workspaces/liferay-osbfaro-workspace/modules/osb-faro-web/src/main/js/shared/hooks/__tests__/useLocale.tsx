import {DEFAULT_LOCALE} from 'shared/util/locale';
import {LanguageIds} from 'shared/util/constants';
import {mockCurrentUser, WorkspacePage} from 'test/mock-page-context';
import {renderHook} from '@testing-library/react';
import {useLocale} from 'shared/hooks/useLocale';

describe('useLocale', () => {
	it('wires the current user languageId into resolveLocale', () => {
		mockCurrentUser({languageId: LanguageIds.Portuguese});

		const {result} = renderHook(useLocale, {wrapper: WorkspacePage});

		expect(result.current).toBe('pt-BR');
	});

	it('falls back to the default locale when the current user has no languageId set', () => {
		mockCurrentUser({languageId: null});

		const {result} = renderHook(useLocale, {wrapper: WorkspacePage});

		expect(result.current).toBe(DEFAULT_LOCALE);
	});
});
