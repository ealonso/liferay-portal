import React from 'react';
import {
	ActionType,
	UnassignedSegmentsProvider,
	unassignedSegmentsReducer,
	useUnassignedSegmentsContext
} from '../unassignedSegments';
import {WorkspacePage} from 'test/mock-page-context';
import {cleanup, fireEvent, render, screen} from '@testing-library/react';
import {mockSegment} from 'test/data';

const initialState = {
	unassignedSegments: []
};

describe('unassignedSegmentsReducer', () => {
	it('should return unassignedSegments state with added items', () => {
		const segment = mockSegment();

		const {unassignedSegments} = unassignedSegmentsReducer(initialState, {
			payload: [segment],
			type: ActionType.setSegments
		});

		expect(unassignedSegments).toContain(segment);
	});
});

describe('UnassignedSegmentsProvider', () => {
	afterEach(cleanup);

	it('should allow an initial context value to be set through the unassignedSegments prop', () => {
		const successMsg = 'has unassignedSegments intialized in context!';

		const ChildComponent = () => {
			const {unassignedSegments} = useUnassignedSegmentsContext();

			return unassignedSegments.length && successMsg;
		};

		const {container} = render(
			<WorkspacePage><UnassignedSegmentsProvider unassignedSegments={[mockSegment()]}>
				<ChildComponent />
			</UnassignedSegmentsProvider></WorkspacePage>
		);

		expect(container).toHaveTextContent(successMsg);
	});

	describe('unassigned alert', () => {
		const AlertState = () => {
			const {showUnassignedAlert, unassignedSegmentsDispatch} =
				useUnassignedSegmentsContext();

			return (
				<button
					onClick={() =>
						unassignedSegmentsDispatch({
							type: ActionType.updateShowAlert
						})
					}
				>
					{showUnassignedAlert ? 'shown' : 'hidden'}
				</button>
			);
		};

		afterEach(() => {
			jest.restoreAllMocks();
		});

		it('hides the alert when the renderer says it was dismissed', () => {
			render(
				<WorkspacePage dismissedAlerts={{unassignedSegments: true}}>
					<UnassignedSegmentsProvider>
						<AlertState />
					</UnassignedSegmentsProvider>
				</WorkspacePage>
			);

			expect(screen.getByRole('button')).toHaveTextContent('hidden');
		});

		it('stores the dismissal in the session when the alert is closed', () => {
			const setSpy = jest.spyOn(Liferay.Util.Session, 'set');

			render(
				<WorkspacePage><UnassignedSegmentsProvider>
					<AlertState />
				</UnassignedSegmentsProvider></WorkspacePage>
			);

			expect(screen.getByRole('button')).toHaveTextContent('shown');
			expect(setSpy).not.toHaveBeenCalled();

			fireEvent.click(screen.getByRole('button'));

			expect(screen.getByRole('button')).toHaveTextContent('hidden');
			expect(setSpy).toHaveBeenCalledWith(
				'osb-faro-web_dismissedAlerts_unassignedSegments',
				'true',
				{useHttpSession: true}
			);
		});
	});
});

describe('useUnassignedSegmentsContext', () => {
	afterEach(cleanup);

	it('should return context', () => {
		const successMsg = 'has context!';

		const ChildComponent = () => {
			const {unassignedSegments, unassignedSegmentsDispatch} =
				useUnassignedSegmentsContext();

			return (
				unassignedSegments && unassignedSegmentsDispatch && successMsg
			);
		};

		const {container} = render(
			<WorkspacePage><UnassignedSegmentsProvider>
				<ChildComponent />
			</UnassignedSegmentsProvider></WorkspacePage>
		);

		expect(container).toHaveTextContent(successMsg);
	});
});
