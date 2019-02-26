import { createExperienceReducer, startCreateExperience, endCreateExperience } from '../../../src/main/resources/META-INF/resources/js/reducers/experiences.es';
import { CREATE_EXPERIENCE, START_CREATE_EXPERIENCE, END_CREATE_EXPERIENCE } from '../../../src/main/resources/META-INF/resources/js/actions/actions.es';

describe('experiences reducers', () => {
	test('createExperienceReducer communicates with API and updates the state', () => {
		const availableExperiences = {};
		const classNameId = 'test-class-name-id';
		const classPK = 'test-class-p-k';
		const spy = jest.spyOn(global.Liferay, 'Service');

		const prevState = {
			defaultLanguageId: 'en_US',
			classNameId,
			classPK,
			availableExperiences
		};

		const payload = {
			experienceLabel: 'test experience label',
			segmentId: 'test-segment-id'
		};

		const nextState = {
			...prevState,
			availableExperiences: {
					[EXPERIENCE_ID]: {
						active: true,
						experienceId: EXPERIENCE_ID,
						experienceLabel: payload.experienceLabel,
						priority: 0,
						segmentId: payload.segmentId
					}
				},
			experienceId: EXPERIENCE_ID
		};

		const liferayServiceParams = {
			classNameId: prevState.classNameId,
			classPK: prevState.classPK,
			segmentsEntryId: payload.segmentId,
			nameMap: JSON.stringify({ en_US: payload.experienceLabel }),
			active: true,
			priority: 0
		};


		 expect.assertions(4);

		createExperienceReducer(prevState, CREATE_EXPERIENCE, payload)
			.then(response => {
				expect(response).toEqual(nextState);
			});

		expect(spy).toHaveBeenCalledWith(
			expect.stringContaining(''), // don't care about url
			liferayServiceParams,
			expect.objectContaining({}), // don't care about success callbacck
			expect.objectContaining({}) // don't care about error callbacck
		);

		const secondPayload = {
			experienceLabel: 'second test experience label',
			segmentId: 'test-segment-id'
		};

		const secondNextState = {
			...nextState,
			availableExperiences: {
				...nextState.availableExperiences,
				[SECOND_EXPERIENCE_ID]: {
					active: true,
					experienceId: SECOND_EXPERIENCE_ID,
					experienceLabel: secondPayload.experienceLabel,
					priority: 1,
					segmentId: secondPayload.segmentId
				}
			},
			experienceId: SECOND_EXPERIENCE_ID
		}

		const secondLiferayServiceParams = {
			classNameId: prevState.classNameId,
			classPK: prevState.classPK,
			segmentsEntryId: secondPayload.segmentId,
			nameMap: JSON.stringify({ en_US: secondPayload.experienceLabel }),
			active: true,
			priority: 1
		};


		createExperienceReducer(nextState, CREATE_EXPERIENCE, secondPayload)
			.then(response => {
				expect(response).toEqual(secondNextState);
			});
		
		expect(spy).toHaveBeenLastCalledWith(
			expect.stringContaining(''), // don't care about url
			secondLiferayServiceParams,
			expect.objectContaining({}), // don't care about success callback
			expect.objectContaining({}) // don't care about error callbacck
		);
	});

	test('startCreateExperience and endCreateExperience switch states', () => {
		const prevState = {};
		const creatingExperienceState = startCreateExperience(prevState, START_CREATE_EXPERIENCE);
		expect(creatingExperienceState).toMatchObject({
			experienceCreation: {
				creatingExperience: true,
				error: null
			}
		});
		const notEdtingState = endCreateExperience(creatingExperienceState, END_CREATE_EXPERIENCE);

		expect(notEdtingState).toMatchObject({
			experienceCreation: {
				creatingExperience: false,
				error: null
			}
		});
	});

	beforeAll(() => {
		global.Liferay = {
			Service(URL, {
				classNameId,
				classPK,
				segmentsEntryId,
				nameMap,
				active,
				priority
			},
			callbackFunc,
			errorFunc
			) {
				return callbackFunc(
					{
						active,
						nameCurrentValue: JSON.parse(nameMap).en_US,
						priority,
						segmentsEntryId,
						segmentsExperienceId: (experiencesCount++, EXPERIENCES_LIST[experiencesCount])
					}
				);
			}
		};
	});

	afterAll(() => {
		global.Liferay = undefiined;
	});
});

const EXPERIENCE_ID = 'EXPERIENCE_ID';
const SECOND_EXPERIENCE_ID = 'SECOND_EXPERIENCE_ID';

const EXPERIENCES_LIST = [EXPERIENCE_ID, SECOND_EXPERIENCE_ID];
let experiencesCount = -1;