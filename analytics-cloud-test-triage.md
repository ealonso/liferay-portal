# Analytics Cloud Test Triage Report

**Scope:** All Poshi tests tagged `@component-name = "portal-analytics-cloud"`  
**Date:** 2026-04-17  
**Method:** Enumerated all 96 `.testcase` files, parsed 691 test blocks for priority and ticket references, looked up ticket commits in git log, classified buckets by changed file paths.

## Summary

- **Total test blocks:** 691
- **Unique tickets referenced:** 76

### By Bucket

| Bucket | Count | Meaning |
|--------|-------|---------|
| integration-java | 34 | Rewrite as Java integration test against service/validator/permission layer |
| rest-integration | 1 | Rewrite as REST-layer integration test in `*-rest-test` |
| playwright | 39 | Keep as functional; migrate to Playwright (not Java-replaceable) |
| needs-review | 43 | Git log alone not conclusive; must read the test body |
| inconclusive | 25 | Ticket not in this repo's git history (often LPD-internal) |
| no-ticket | 549 | Test description has no LPS/LPD ref; must read the test body |

### By Priority

| Priority | Count |
|----------|-------|
| 5 | 228 |
| 4 | 179 |
| 3 | 256 |
| 2 | 26 |
| 1 | 2 |

## Test Details

| File | Test | Priority | Ticket | Bucket | Notes |
|------|------|----------|--------|--------|-------|
| contentperformance/ContentPerformance.testcase | CheckAllInfoDisplayed | 5 | LPS-127220 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | PublicationDatePreviousSet0 | 5 | LPS-126044 | integration-java | service/validator/permission impl |
| segmentation/SegmentationAssigningRoles.testcase | EditPageAfterEnableAssignRole | 5 | LPS-121943 | integration-java | Java-only fix |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionHostname | 5 | LPS-163095 | integration-java | Java-only fix |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserUserGroup | 5 | LPS-147454 | integration-java | service/validator/permission impl |
| segmentation/SegmentationCreateSegment.testcase | InstanceSettingsSessionPropertyVocabularyInSegmentsEditor | 5 | LPS-130917 | integration-java | Java-only fix |
| contentperformance/ContentPerformance.testcase | MetricsIconVisibleInBlogDisplayPage | 4 | LPS-127220 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | MetricsIconVisibleInContentPage | 4 | LPS-126044 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | NoNavigationWithoutIncomingTraffic | 4 | LPS-126044 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertAtSegmentsList | 4 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtSystem.testcase | CheckAssignRolesDisabledAlertAtInstanceSettings | 4 | LPS-152541 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtSystem.testcase | CheckSegmentationDisabledAlertAtInstanceSettings | 4 | LPS-152542 | integration-java | service/validator/permission impl |
| abtest/ABTesting.testcase | CheckABTestPanelInfoNotSyncAC | 3 | LPS-152323 | integration-java | Java-only fix |
| contentperformance/ContentPerformance.testcase | GraphicLineNoViewsReads | 3 | LPS-126044 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | IntervalSelectorInBlogDisplayPage | 3 | LPS-127220 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | LanguageSelectorUserLanguage | 3 | LPS-127220 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | LanguagesLabel | 3 | LPS-126044 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | PanelInformationInBlogDisplayPage | 3 | LPS-127220 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | PanelInformationInContentPage | 3 | LPS-126044 | integration-java | service/validator/permission impl |
| contentperformance/ContentPerformance.testcase | ViewsGraphInBlogDisplayPage | 3 | LPS-126044 | integration-java | service/validator/permission impl |
| segmentation/SegmentationAssigningRoles.testcase | CheckMessageSegmentationByRolesDisabledInstanceSettings | 3 | LPS-121943 | integration-java | Java-only fix |
| segmentation/SegmentationAssigningRoles.testcase | DisableAssignRolesWarningMessage | 3 | LPS-130918 | integration-java | service/validator/permission impl |
| segmentation/SegmentationCreateSegment.testcase | CheckTitleLanguageInSegmentsList | 3 | LPS-130919 | integration-java | service/validator/permission impl |
| segmentation/SegmentationCreateSegment.testcase | InstanceSettingsSessionPropertyVocabulariesBreadcrumb | 3 | LPS-130919 | integration-java | service/validator/permission impl |
| segmentation/SegmentationCreateSegment.testcase | InstanceSettingsSessionPropertyVocabulariesCancelButton | 3 | LPS-130919 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | CheckRoleDisabledAlertAtInstanceSettings | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ClickToReEnableAlertLinkAtAtExperiencesMenu | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ClickToReEnableAlertLinkAtDynamicCollectionEditor | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ClickToReEnableAlertLinkAtManualCollectionEditor | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertLinkAtDynamicCollectionEditor | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertLinkAtManualCollectionEditor | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnabledAlertAtExperienceSimulationSidepanel | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnabledAlertLinkAtExperiencesMenu | 3 | LPS-152539 | integration-java | service/validator/permission impl |
| segmentation/SegmentationDisabledAtSystem.testcase | ViewAssignRoleBySegmentDisabledWarningUserWithoutsPermissionToSystemSettings | 3 | LPS-152542 | integration-java | service/validator/permission impl |
| abtest/ABTesting.testcase | CreateABTestRunWithDeletedVariants | 4 | LPS-99421 | rest-integration | REST layer fix |
| contentperformance/ContentPerformanceWithoutAC.testcase | HideContentPerformancePanel | 5 | LPS-108856 | playwright | UI/JSP/frontend fix |
| segmentation/ACSegmentsInDXP.testcase | NonDefaultInstanceSynchronizedOnAC | 5 | LPS-94938 | playwright | UI/JSP/frontend fix |
| segmentation/ACSegmentsInDXP.testcase | RedirectToSegmentPageAC | 5 | LPS-94938 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewDisabledAlertAtExperiencesMenu | 5 | LPS-151362 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | ReviewAndStartCancel | 4 | LPS-119476 | playwright | UI/JSP/frontend fix |
| contentperformance/ContentPerformance.testcase | MetricsIconVisibleInDocumentDisplayPage | 4 | LPS-126047 | playwright | UI/JSP/frontend fix |
| segmentation/ACSegmentsInDXP.testcase | SegmentsWithoutEditingAndDeletingFromTheSite | 4 | LPS-94938 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ClickToReEnableAlertLinkAtSegmentsList | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | CloseDisabledSegmentationAlertAtDynamicCollectionEditor | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | CloseDisabledSegmentationAlertAtExperiencesMenu | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | CloseDisabledSegmentationAlertAtManualCollectionEditor | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | CloseDisabledSegmentationAlertAtSegments | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | CloseDisabledSegmentationAlertAtSimulationPanel | 4 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewDisabledSegmentationAlertAtDynamicCollectionEditor | 4 | LPS-151362 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertLinkAtSegmentsList | 4 | LPS-151362 | playwright | UI/JSP/frontend fix |
| abtest/ABTestWithoutACConnection.testcase | CheckABTestingClosedAfterLogout | 3 | LPS-101055 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | AssertChangeClickableIDBoxNotEnabled | 3 | LPS-109345 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | ChangesNotAppliedAfterCloseModal | 3 | LPS-101055 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | CheckABTestPanelAtWidgetPages | 3 | LPS-108147 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | CheckElementIdWithVariant | 3 | LPS-101167 | playwright | UI/JSP/frontend fix |
| contentperformance/ContentPerformance.testcase | AuthorNotShowInWidgetPage | 3 | LPS-126047 | playwright | UI/JSP/frontend fix |
| contentperformance/ContentPerformance.testcase | PanelInformationInDocumentDisplayPage | 3 | LPS-126047 | playwright | UI/JSP/frontend fix |
| contentperformance/ContentPerformance.testcase | PanelInformationInWidgetPage | 3 | LPS-109417 | playwright | UI/JSP/frontend fix |
| contentperformance/ContentPerformanceWithoutAC.testcase | ContentPerformanceClosed | 3 | LPS-108856 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationCreateSegment.testcase | AnonymousUserSegmentsCacheExpirationCheckText | 3 | LPS-92366 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationCreateSegment.testcase | CanCreateSegmentNameWithScriptTags | 3 | LPS-106196 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationCreateSegment.testcase | CheckEditableTranslatedInputTitle | 3 | LPS-134561 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterRefreshAtDynamicCollectionEditor | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterRefreshAtExperiencesMenu | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterRefreshAtManualCollectionEditor | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterRefreshAtSegmentsPage | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterRefreshAtSimulationPanel | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterReturnsAtDynamicCollectionEditor | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterReturnsAtExperiencesMenu | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterReturnsAtManualCollectionEditor | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterReturnsAtSegmentsPage | 3 | LPS-154019 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | DisabledAlertAfterReturnsAtSimulationPanel | 3 | LPS-151362 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertAtDynamicCollectionEditor | 3 | LPS-151362 | playwright | UI/JSP/frontend fix |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewReEnableAlertAtManualCollectionEditor | 3 | LPS-151362 | playwright | UI/JSP/frontend fix |
| abtest/ABTesting.testcase | CreateABTestByBounceRate | 5 | LPS-119475 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | CreateABTestByClick | 5 | LPS-119475 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| contentperformance/ContentPerformance.testcase | DefaultTimeRangeWeek | 5 | LPS-150536 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationHierarchyPath | 5 | LPS-130278 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationOrganization | 5 | LPS-130279 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionCookies | 5 | LPS-130319 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionLastSignInDate | 5 | LPS-130321 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionLocalDate | 5 | LPS-130322 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionReferrerURL | 5 | LPS-130323 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionRequestParameters | 5 | LPS-130324 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionSignedIn | 5 | LPS-130326 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionUserAgent | 5 | LPS-130284 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserDateModified | 5 | LPS-130285 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserDateOfBirth | 5 | LPS-130291 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserEmailAddressNotEquals | 5 | LPS-130286 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserFirstName | 5 | LPS-130287 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserJobTitle | 5 | LPS-130288 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserLastName | 5 | LPS-130292 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserName | 5 | LPS-130289 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserRole | 5 | LPS-130290 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserScreenName | 5 | LPS-132107 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserSite | 5 | LPS-132108 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserTag | 5 | LPS-132110 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | ABTestIconInExperience | 4 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| abtest/ABTesting.testcase | EditVariantContentCancel | 4 | LPS-146003 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentBySessionIPGeocoderCountry | 4 | LPS-130320 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | CheckSegmentationWithApostropheValueFilter | 4 | LPS-135495 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | SegmentationCheckMembersWithConditions | 4 | LPS-153509 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | SegmentAvailableInAnyLocale | 4 | LPS-135969 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | ABTestIconVisibleWithNoPermissions | 3 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| abtest/ABTesting.testcase | ABTestIconVisibleWithPermissions | 3 | LPS-119475 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | CanNotRenameSegmentABTestRunning | 3 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| abtest/ABTesting.testcase | ChangesAppliedAfterEdit | 3 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| abtest/ABTesting.testcase | ChangesNotAppliedAfterCancel | 3 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| abtest/ABTesting.testcase | CheckVariantEditedInAC | 3 | LPS-119475 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | SubmitElementWithNoID | 3 | LPS-97882 | needs-review | only language-keys changed in this ticket; real code fix likely in a different commit |
| contentperformance/ContentPerformance.testcase | AuthorNotShowInBlogDisplayPage | 3 | LPS-144031 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| contentperformance/ContentPerformance.testcase | AuthorNotShowInContentPage | 3 | LPS-144031 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| contentperformance/ContentPerformance.testcase | AuthorNotShowInDocumentDisplayPage | 3 | LPS-144031 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | CheckDefaultLanguageTag | 3 | LPS-135495 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | InterestTermsCacheCacheExpirationCheckText | 3 | LPS-132249 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | ValidatePropertyDisplayedInSection | 3 | LPS-135969 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| segmentation/SegmentationCreateSegment.testcase | ValidatePropertyDragAndDropToSections | 3 | LPS-135969 | needs-review | ticket only added the Poshi test itself; read the test to classify |
| abtest/ABTesting.testcase | CheckVariantAddedInAC | 5 | LPS-97195 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | EditVariantContent | 5 | LPS-99349 | inconclusive | ticket not found in git log |
| segmentation/ACSegmentsInDXP.testcase | DefaultInstanceNotSynchronizedWithAC | 5 | LPS-111465 | inconclusive | ticket not found in git log |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserUser | 5 | LPS-132112Total: 691 | inconclusive | ticket not classified |
| abtest/ABTesting.testcase | DeleteButtonWarning | 4 | LPS-104203 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | DeleteVariantCancel | 4 | LPS-96791 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | ReassignSegmentABTestRunning | 4 | LPS-99225 | inconclusive | ticket not found in git log |
| contentperformance/ContentPerformance.testcase | EmptyDataAtPieChartPublishedToday | 4 | LPS-108068 | inconclusive | ticket not found in git log |
| contentperformance/ContentPerformance.testcase | MetricsIconVisibleInWidgetPage | 4 | LPS-111042 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | CheckEmptyStateImage | 3 | LPS-97195 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | ClearIDBox | 3 | LPS-137274 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | CreateABTestByClickInvalidId | 3 | LPS-86285 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | DeleteABTestCancel | 3 | LPS-145992 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | DeleteClickableElementWarning | 3 | LPS-101341 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | DeleteSegmentABTestTerminated | 3 | LPS-97195 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | NoNotificationAfterChangesSameUser | 3 | LPS-96787 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | ReassignSegmentABTestDraft | 3 | LPS-99225 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | RenameSegmentABTestDraft | 3 | LPS-99225 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | RenameSegmentABTestTerminated | 3 | LPS-97196 | inconclusive | ticket not found in git log |
| contentperformance/ContentPerformance.testcase | DefaultLanguageOnTop | 3 | LPS-105215 | inconclusive | paths unclear |
| contentperformance/ContentPerformance.testcase | LanguagesAlphabeticallyOrdered | 3 | LPS-110920 | inconclusive | ticket not found in git log |
| segmentation/ACSegmentsInDXP.testcase | CheckNotEditableSegmentFromACAtDXP | 3 | LPS-111465 | inconclusive | ticket not found in git log |
| segmentation/SegmentationCreateSegment.testcase | ValidatePropertyNotDisplayedDifferentSection | 3 | LPS-177714 | inconclusive | ticket not found in git log |
| segmentation/SegmentationCreateSegment.testcase | ViewBackButtonTooltipForSegmentsAdmin | 3 | LPS-177714 | inconclusive | ticket not found in git log |
| segmentation/SegmentationCreateSegment.testcase | ViewBackButtonTooltipOfNewSegmentFromNewExperience | 3 | LPS-94651 | inconclusive | ticket not found in git log |
| abtest/ABTesting.testcase | EditClickElementTarget | 5 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanCreateStartABTest | 5 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanDeleteABTest | 5 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanPublishControlFromTerminatedTest | 5 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanPublishVariantFromTerminatedTestAndDeleteTest | 5 | | no-ticket | No description ticket |
| analyticscloud/ACLogin.testcase | CanSignOutAC | 5 | | no-ticket | No description ticket |
| analyticscloud/ActiveIndividuals.testcase | CanSetDataWithCustomRangeOnIndividualActivitiesCard | 5 | | no-ticket | No description ticket |
| analyticscloud/ActiveIndividuals.testcase | CanSetEmptyDataCustomRangeOnIndividualActivitiesCard | 5 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | CanNavigatesToDataSourcePage | 5 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | DataSourceListIsSearchable | 5 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudDXP.testcase | CanViewSiteReportUponLogin | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | AssertAppearsOnListShowsPagesBlogExists | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | AudienceCardShowsExpectedAmountKnowAndAnonymousIndividualsInBlogs | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | AudienceCardShowsExpectedAmountSegmentedAndUnsegmentedInBlogs | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | BlogsTechnologyCardShowsViewsByExpectedBrowser | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | BlogsTechnologyCardShowsViewsByExpectedDevice | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | CanBlogsListSearchable | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | CanNavigatesToBlogsOverviewPage | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | CanUseCustomRangeBlogsAssetsPage | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | KnownIndividualsListShowsIndividualsWhoHaveViewedBlog | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | AssertAppearsOnListShowsPagesDocumentsExists | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | AudienceCardShowsExpectedAmountKnowAndAnonymousIndividualsInDM | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | AudienceCardShowsExpectedAmountSegmentedAndUnsegmentedInDM | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DocumentsTechnologyCardShowsViewsByExpectedBrowser | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DocumentsTechnologyCardShowsViewsByExpectedDevice | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | KnownIndividualsListShowsIndividualsWhoHaveViewedDocuments | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | ViewAllDocumentsAndMediaShownInAssetList | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | AssertAppearsOnListShowsPagesFormsExists | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | AudienceCardShowsExpectedAmountKnowAndAnonymousIndividualsInForms | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | AudienceCardShowsExpectedAmountSegmentedAndUnsegmentedInForms | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | CanFormsListSearchable | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | CanNavigatesToFormsOverviewPage | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | CanTimeFilterUseCustomRangeInFormsBehaviourReport | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | FormsTechnologyCardShowsViewsByExpectedBrowser | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | FormsTechnologyCardShowsViewsByExpectedDevice | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | KnownIndividualsShowsWhichIndividualsInteractWithForm | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsForms.testcase | ViewAllFormsShownInAssetList | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | AudienceCardShowsExpectedAmountKnowAndAnonymousIndividualsInWC | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | AudienceCardShowsExpectedAmountSegmentedAndUnsegmentedInWC | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | CanNavigateToWebContentOverviewPage | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | CanSearchWebContentList | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | KnownIndividualsShowsWhichIndividualsInteractWithWebContent | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | ViewAllWebContentShownInAssetList | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | VisitorBehaviorCardShowsExpectedAmountOfViewsInWC | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | WebContentTechnologyCardShowsViewsByExpectedBrowser | 5 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | WebContentTechnologyCardShowsViewsByExpectedDevice | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogClickedWhenBlogEntryTitleIsClickedAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogDepthReachedTo25AndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogDepthReachedTo50AndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogDepthReachedTo75AndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogDepthReachedTo100AndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogViewedWhenVisitingDXPBlogAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/CommentsEvents.testcase | CheckPostedWhenCommentingContentInPageAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CanPutAnyNameInNewProperty | 5 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CheckIfPropertyCreatedAutomaticallyAfterConnection | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | CustomEventAttributeIsShownInACSettings | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockEvents.testcase | CanBlockCustomEvent | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockEvents.testcase | CheckBlockedEventsNotShownOnSegments | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockEvents.testcase | CheckEventAnalysisNotUpdatedWhenEventIsBlocked | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockedEventsList.testcase | CheckBlockedEventsListWhenMore100EventsCreated | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsChangeDataType.testcase | CanChangeDataTypeFromDescription | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsChangeDataType.testcase | CanChangeDataTypeFromFilter | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CheckDefaultEventsCountedAsDefaultWhenEventSentByConsole | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CheckTotalAndUniqueAndAverageChartDataIsShown | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CreateEventAnalysisFromAllAndDefaultAndCustomTab | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | RemoveAttribute | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AddAttributeAndAttributeFilterInEventsAnalysis | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | AssertGlobalAttributesInSettings | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | AssertSampleDataAndLastAccessInGlobalAttributes | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | EditGlobalAttributeDescription | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | RenameGlobalAttribute | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | CanHideDefaultAndCustomEvent | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | CanUnhideDefaultAndCustomEvent | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | CheckDefaultAndCustomEventIsNotVisibleOnEventAnalysisList | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | NonAdminUserCannotHideEvents | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | ViewIfCustomEventIsNotVisibleOnSegmentEventCriteriaList | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | ViewIfDefaultEventsAreHidden | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsIndividuals.testcase | AssertChartTooltipEventsAndSessions | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsIndividuals.testcase | ViewIndividualActivitiesWithSessionAttributes | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanCreateAnalysisReport | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanDeleteAnalysisReport | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanEditAnalysisReport | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CanCopySampleJavascript | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CustomEventIsShownInAC | 5 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsUnblockEvents.testcase | CanUnblockCustomEvent | 5 | | no-ticket | No description ticket |
| analyticscloud/DataControlAndPrivacy.testcase | CanCreateAccessRequest | 5 | | no-ticket | No description ticket |
| analyticscloud/DataControlAndPrivacy.testcase | CanCreateDeleteRequest | 5 | | no-ticket | No description ticket |
| analyticscloud/DataControlAndPrivacy.testcase | CanCreateSuppressRequest | 5 | | no-ticket | No description ticket |
| analyticscloud/DefinitionsSearch.testcase | CanAddSearchQueryParameters | 5 | | no-ticket | No description ticket |
| analyticscloud/DefinitionsSearch.testcase | CanDeleteSearchQueryParameters | 5 | | no-ticket | No description ticket |
| analyticscloud/DeleteSegments.testcase | CanDeleteDynamicSegment | 5 | | no-ticket | No description ticket |
| analyticscloud/DisconnectACAndDXP.testcase | DisconnectACAndDXPFromDXPSide | 5 | | no-ticket | No description ticket |
| analyticscloud/DocumentsAndMediaEvents.testcase | CheckDocumentDownloadedInSiteAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/EditSegments.testcase | CanEditDynamicSegmentCheckEditSaved | 5 | | no-ticket | No description ticket |
| analyticscloud/FormEvents.testcase | CheckFormSubmittedAfterSubmittingForm | 5 | | no-ticket | No description ticket |
| analyticscloud/FormEvents.testcase | CheckFormViewedProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckIdentityUponLogin | 5 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckIfEventSentAgainAfterLoginAndIfUserClassifiedAsKnownIndividual | 5 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | NewUserChangeEmailAddressHashed | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualDefinitions.testcase | AssertNewSourceInSourceListForIndividualAttribute | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualsAttributeBreakdown.testcase | DeleteNewBreakdown | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualsAttributeBreakdown.testcase | DistributionChartShowsSameAmountOfMembersAsSidebar | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | CanUseCustomRangeTimeFilterOnActiveIndividualsChart | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | CanViewTotalIndividualsIncreaseByOne | 5 | | no-ticket | No description ticket |
| analyticscloud/IndividualsEnrichedProfiles.testcase | IncreaseByOneWhenAnonymousIndividualConvertedToKnownIndividual | 5 | | no-ticket | No description ticket |
| analyticscloud/InviteUsers.testcase | CanInviteSingleUser | 5 | | no-ticket | No description ticket |
| analyticscloud/InviteUsers.testcase | OwnerCanInviteUsers | 5 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileActivitiesList.testcase | IndividualActivitiesListShowsAccurateActivitiesWhenSwitchedTo24HourView | 5 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileDetails.testcase | AllIndividualAttributesAppearInList | 5 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | CanCaseInsensitiveSearch | 5 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | ViewOwnerCannotBeEditedOrRemoved | 5 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | ViewOwnerCannotBeSelectedInUserList | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageDepthReachedTo25 | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageDepthReachedTo50 | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageDepthReachedTo75 | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageDepthReachedTo100 | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageLoadedWhenViewPage | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageUnLoadedWhenLeavePage | 5 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageViewedWhenViewPage | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfileAudience.testcase | ShowsKnownAnonymousIndividualsAsWellAsSegmentedUnsegmentedIndividuals | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfilePath.testcase | CanTimeFilterUseCustomRangeInPathAnalysis | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfilePath.testcase | ViewSegmentDropdownList | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfileViewsByTechnology.testcase | ShowsWhichDevicesBeingUsed | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfileVisitorBehavior.testcase | ShowNumberOfUniqueVisitorsAndViews | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfileVisitorBehavior.testcase | TimeFilterByCustomRangeInPagesOverview | 5 | | no-ticket | No description ticket |
| analyticscloud/PageProfileVisitorBehavior.testcase | TimeFilterByLast180DaysInPagesOverview | 5 | | no-ticket | No description ticket |
| analyticscloud/PagesList.testcase | CanSearchPageInPagesList | 5 | | no-ticket | No description ticket |
| analyticscloud/PagesList.testcase | CanTimeFilterUseCustomRangeInPageTable | 5 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CanNavigateToProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | OwnerCanCreateNewProperty | 5 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | PropertiesListInSettingsShowsListOfAllPropertiesInTheWorkspace | 5 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CanSearchForProperty | 5 | | no-ticket | No description ticket |
| analyticscloud/RatingEvents.testcase | CheckVoteEventWhenVotingForBlogAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/RatingEvents.testcase | CheckVoteEventWhenVotingForDMAndCheckProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateDynamicSegmentWithIndividuals | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateDynamicSegmentWithIndividualsUseUnknown | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateSegmentWithAnonymousIndividuals | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | ViewSegmentFormValidation | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentDownloadingDocument | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentSubmittingForm | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingEver | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingHasNot | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingSince | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentViewingForm | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsList.testcase | CanSearchForSegment | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsList.testcase | CanShowSegmentList | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsMembership.testcase | CanSearchMembershipList | 5 | | no-ticket | No description ticket |
| analyticscloud/SegmentsMembership.testcase | ViewMembershipShowsKnownAnonymousTotalMembers | 5 | | no-ticket | No description ticket |
| analyticscloud/SitesOverview.testcase | SiteReportPageReturnsAllCardsFilledAccordingSiteSelected | 5 | | no-ticket | No description ticket |
| analyticscloud/SitesOverview.testcase | ViewSitesReportsAsHomePageAC | 5 | | no-ticket | No description ticket |
| analyticscloud/SitesSearchTerms.testcase | ShowsKeywordsBeingSearched | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | AllOrderAttributesRequired | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | AllProductAttributesRequired | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanDeselectNonRequiredAccountAttributes | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanDeselectNoRequiredAttributesForPeople | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSelectAccountAttributes | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSelectAttributesForPeople | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CheckNotPossibleDeselectRequiredAttributesForAccounts | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CheckNotPossibleToDeselectRequiredAttributesForOrders | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CheckNotPossibleToDeselectRequiredAttributesForPeople | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllAccounts | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllContacts | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllContactsAndAccounts | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllContactsAndAccountsGroupsOnly | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllContactsByGroups | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncAllContactsByOrganizations | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSyncByAccountGroups | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | CheckChannelListCannotEditedIfToggleDisabled | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | CheckIsPossibleEnableToggleCommerceForTwoDifferentProperties | 5 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | SelectedSitesAndChannelsNumbersCorrectAfterSync | 5 | | no-ticket | No description ticket |
| analyticscloud/Usage.testcase | AnonymousIndividualsDoNotCountAsIndividualsForUsage | 5 | | no-ticket | No description ticket |
| analyticscloud/Usage.testcase | IndividualsUpdatesInRealTime | 5 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | OwnerCanAddDataSources | 5 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | OwnerCanChangeUserPermissions | 5 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | UsersListCanBeSearched | 5 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | ViewOwnerCanDeleteUser | 5 | | no-ticket | No description ticket |
| analyticscloud/UserManagementEdit.testcase | CanEditButtonToManageUserPermissions | 5 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | CannotViewInformationBannerAfterDismissedByUser | 5 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | SetTimeZoneInExistingWorkspace | 5 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | ViewOnlyOneInformationBannerShownAfterChangeTimezone | 5 | | no-ticket | No description ticket |
| contentperformance/ContentPerformance.testcase | LanguageSelector | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationCategory | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationDateModified | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationParentOrganization | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationRegion | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByOrganizationTag | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserEmailAddress | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserEmailAddressEquals | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserEmailAddressNotContains | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserSiteOrganizationRole | 5 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentByUserTeam | 5 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | CanNotEditLockedExperience | 4 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | DeleteExperienceWithDraftABTest | 4 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | EditVariantWithToolbarButton | 4 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanDeleteTerminatedTestOnDXP | 4 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CanTerminateABTestAndCreateNewOne | 4 | | no-ticket | No description ticket |
| analyticscloud/ABTestDXPSide.testcase | CheckABTestSidePanelWhenNoDeclaredWinner | 4 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | CheckIfIsPossibleToReconnectACWithDXP | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | CheckBlogCommentsShowExpectedValue | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | CheckBlogRatingShowExpectedValue | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | CanAssertDocumentsAndMediaTimeFilterCustom | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | CanDocumentsAndMediaListSearchable | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | CheckDocumentsAndMediaCommentsShowExpectedValue | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | CheckDocumentsAndMediaRatingShowExpectedValue | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DownloadDocumentByDownloadButton | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DownloadDocumentByInfoButton | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DownloadDocumentByThreeDots | 4 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | CanAssertWebContentTimeFilterCustom | 4 | | no-ticket | No description ticket |
| analyticscloud/ClientJS.testcase | CheckIdentityEventNotResendWhenEmailIsTheSame | 4 | | no-ticket | No description ticket |
| analyticscloud/CommentsEvents.testcase | CheckPostedWhenReplyingToComment | 4 | | no-ticket | No description ticket |
| analyticscloud/ConnectACWithDXP.testcase | CheckDataSourceStatusUpdateWhenSyncSitesAndContacts | 4 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | OnlyAvailablePropertiesAppearInDXP | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | CreateAttributeNameBeyond255Characters | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | RenameAttributeDisplayName | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | RenameToExistingAttributeName | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | UpdateAttributeDataType | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | UpdateAttributeDescription | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockedEventsList.testcase | CanSortBlockedEventsByNameAndLastSeen | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsChangeDataType.testcase | CanChangeDataTypeToBoolean | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsChangeDataType.testcase | CanChangeDataTypeToTime | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CanChangeChartDateRange | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | ViewEventsAreListedInAlphabeticalOrder | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AddMaxOf3AttributesToEvent | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AssertAttributeBreakdownsSortedByHighestEventCount | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AssertDataChangeWhenRearrangeBreakdownAndReturnOriginalState | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AssertRemovedEventIsNotPresent | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | AssertTheAnalysisResultIsSorted | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | CanAddDuplicateFilterInEventAnalysis | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | SortSecondOrThirdColumnInAnalysisResultNotSortFirstColumn | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | RenameGlobalAttributeWithNameAlreadyInUse | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsGlobalAttributes.testcase | SearchForGlobalAttribute | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | CanAccessHiddenEvent | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CannotSaveAnalysisReportWithExistingName | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanPaginationAnalysisReportList | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | UserWithMemberPermissionCanViewAndCreateAnalysisReport | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CanRenameCustomEventName | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CanUpdateCustomEventDescription | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CheckLimitOfCustomEventName | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CustomEventIsShownWhenPaginatingAndSearching | 4 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | RenameCustomEventToExistingName | 4 | | no-ticket | No description ticket |
| analyticscloud/DataControlAndPrivacy.testcase | CanChangeRetentionPeriod | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllBlogs | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllDocumentsAndMedia | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllExistingPages | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllForms | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllIndividuals | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllSegments | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForAllWebContent | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificChannelID | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificForm | 4 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificWebContent | 4 | | no-ticket | No description ticket |
| analyticscloud/DefinitionsSearch.testcase | CanEditSearchQueryParameters | 4 | | no-ticket | No description ticket |
| analyticscloud/DefinitionsSearch.testcase | CantSearchMoreThanFiveSearchQueryStrings | 4 | | no-ticket | No description ticket |
| analyticscloud/DefinitionsSearch.testcase | ViewKeywordSearchedNewStringParameter | 4 | | no-ticket | No description ticket |
| analyticscloud/DeleteSegments.testcase | CanDeleteSegmentViaSegmentList | 4 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CheckEmailReportSettingsDifferentUsers | 4 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | CreateNewScheduledExportProcess | 4 | | no-ticket | No description ticket |
| analyticscloud/FormEvents.testcase | CheckPageViewedIsTriggeredWithFormViewed | 4 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckLogoutContinuesToTrackUserWithSameUserId | 4 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | ViewCardsInTheOverviewTabOfIndividualsDashBoard | 4 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | ViewCardsInTheOverviewTabOfIndividualsPage | 4 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | ViewIndividualNameAtTheTopOfIndividualsPage | 4 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | SearchAndClearField | 4 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | SearchIndividualDetails | 4 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | SearchIndividualList | 4 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | SearchIndividualsActivitiesList | 4 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | TablePagination | 4 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageEventsTriggeredAgainAfterHardRefresh | 4 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageEventsTriggeredAgainAfterRefresh | 4 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageUnLoadedIsNotTriggeredWhenComesFromUntrackedPage | 4 | | no-ticket | No description ticket |
| analyticscloud/PageProfilePath.testcase | DeleteSegmentDropdownUpdated | 4 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CanCreateSamePropertyNameInDifferentWorkspaces | 4 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | ClearPropertyData | 4 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | NewPropertiesGeneratedIDsAreNotIncremental | 4 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CanPaginatePropertyList | 4 | | no-ticket | No description ticket |
| analyticscloud/ReviewConnectionInformation.testcase | CanModifyContactsSyncInPeopleReviewSidebar | 4 | | no-ticket | No description ticket |
| analyticscloud/ReviewConnectionInformation.testcase | CanModifySelectedAttributesInAttributeReviewSidebar | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateDynamicIndividualsSegmentByDate | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateDynamicSegmentWithIndividualCriteriaUsesBooleanAndDoesNotContain | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateDynamicSegmentWithIndividualCriteriaUsesIsKnownAndIsNot | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateSegmentWithNumberProperty | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CheckNotPossibleCreateDynamicSegmentWithNameAlreadyInUse | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentUsingIndividualPropertyRole | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentUsingIndividualPropertySite | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentUsingIndividualPropertyTeam | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentUsingIndividualPropertyUser | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentWithCustomFieldsForIndividuals | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | CanAddSegmentWithCustomFieldThatUsesList | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentCriteriaLeastMostTimes | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentSinceXdays | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingAfter | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingBefore | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingBetween | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentUsingOn | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentViewingBlog | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentViewingDM | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentViewingPage | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreationWebBehavior.testcase | CanCreateWebBehaviorSegmentViewingWC | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCriteriaOverview.testcase | LinkToViewAllCriteriaAllowsUserScrollDownAndSeeMore | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsList.testcase | OrderSegmentListByName | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsMembership.testcase | AssertSegmentMembershipListShowsAllKnownIndividuals | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsOrganizationAttributes.testcase | CanAddSegmentWithCustomFieldsForOrganizations | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsOrganizationAttributes.testcase | CanCreateSegmentUsingOrganizationPropertyDateModified | 4 | | no-ticket | No description ticket |
| analyticscloud/SegmentsOrganizationAttributes.testcase | CanCreateSegmentUsingOrganizationPropertyName | 4 | | no-ticket | No description ticket |
| analyticscloud/SidebarMenu.testcase | AssertPropertyMenuAfterDeleteProperty | 4 | | no-ticket | No description ticket |
| analyticscloud/SitesOverview.testcase | SitesReportPageHasEmptyStateIfNoDataSourceConnect | 4 | | no-ticket | No description ticket |
| analyticscloud/SitesPropertyAnalyticsFilter.testcase | UserLostAccess | 4 | | no-ticket | No description ticket |
| analyticscloud/SitesPropertyAnalyticsFilter.testcase | UserShouldOnlySeePropertiesAreInvitedByACAdminOrPropertiesWhichUserOwner | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSearchAttributeForAccountList | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSearchAttributeForPeopleList | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSearchAttributeInOrdersList | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSearchAttributeInProductList | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CheckAllDefaultsAttributesAreBeingCheckedForAccounts | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CheckAllDefaultsAttributesAreCheckedForPeopleAttribute | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSearchAccountGroupsInContactsModal | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSearchGroupsInContactsModal | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CanSearchOrganizationsInContactsModal | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckIsPossiblePaginateInAccountGroups | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckIsPossiblePaginateOrganizations | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | CanSearchForChannelsAndSitesOnAssignedPropertyModal | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | CheckEventNotBeingSentWhenDesynchronizedSites | 4 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | CheckSiteAndChannelOnlyBeSelectedForSingleProperty | 4 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | AdminCanAddDataSources | 4 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | MemberCannotAddDataSources | 4 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | MemberCannotChangeUserPermissions | 4 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | MemberCannotRemoveDataSources | 4 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | UpdateWorkspaceURLWithFriendlyURL | 4 | | no-ticket | No description ticket |
| segmentation/ACSegmentsInDXP.testcase | RedirectToSegmentPageErrorAC | 4 | | no-ticket | No description ticket |
| segmentation/ACSegmentsInDXP.testcase | ShoudNotRedirectToSegmentPageAC | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | AssigningBySegmentNotWorkingDisabledInstanceSettings | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | AssignSiteRolesOptionInSegmentsEditor | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | CanAssignSiteRolesToSegment | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | DisableAssignRolesBySegment | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | DisabledAssignSiteRolesOptionInSegmentsEditor | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | EnableAssignRolesBySegment | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | AddSegmentUsingCustomFields | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | CanEditSecondSegmentCreated | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | KeepInfomationOfDeleteVocabularyInSegment | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | VocabulariesListedInSystemSettingsSegments | 4 | | no-ticket | No description ticket |
| segmentation/SegmentationEditSegment.testcase | AddExperienceAfterRemovingCriteria | 4 | | no-ticket | No description ticket |
| wem/accessiblity/SegmentationWithKeyboard.testcase | AddPropertiesViaKeyboard | 4 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | DeleteExperienceWithDraftABTestCancel | 3 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | EyeButton | 3 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | NotificationLinkToContentPage | 3 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | SwitchSitePage | 3 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | TypeDifferentID | 3 | | no-ticket | No description ticket |
| abtest/ABTesting.testcase | TypeSubmitElementID | 3 | | no-ticket | No description ticket |
| analyticscloud/APIToken.testcase | CanRevokeTokenAndGenerateNewOne | 3 | | no-ticket | No description ticket |
| analyticscloud/APIToken.testcase | ErrorMessageAppearsWhenAPITokenIsRevokedOrNonexistent | 3 | | no-ticket | No description ticket |
| analyticscloud/ActiveIndividuals.testcase | AssertIndividualActivitiesOverviewClearDate | 3 | | no-ticket | No description ticket |
| analyticscloud/ActiveIndividuals.testcase | AssertIndividualActivitiesTimeFilter180Days | 3 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | AddDataSourceForOneSite | 3 | | no-ticket | No description ticket |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | CancelCreatingDxpDataSource | 3 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | AssertBlogsPageTImeFilter180Days | 3 | | no-ticket | No description ticket |
| analyticscloud/AssetsBlogs.testcase | RatingsNotGettingNegativeWhenDeleteVote | 3 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | AssertDocumentsAndMediaPageTImeFilter180Days | 3 | | no-ticket | No description ticket |
| analyticscloud/AssetsDocumentsAndMedia.testcase | DocumentsAndMediaRatingsNotGettingNegativeWhenDeleteVote | 3 | | no-ticket | No description ticket |
| analyticscloud/AssetsWebContent.testcase | AssertAssetsPageTimeFilter180Days | 3 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogClickedTriggeredWhenBlogClicked | 3 | | no-ticket | No description ticket |
| analyticscloud/ConnectACWithDXP.testcase | ConnectionStatusNotChangeWhenRenameDataSource | 3 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CanSearchForPropertiesInDXP | 3 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CanSortPropertyListInDXP | 3 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CheckDeletedPropertyInACSideNotAppearInDXP | 3 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CheckPropertyListSortedByCreationDateByDefault | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | CanDeleteAttributeDescription | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsAttributeSettings.testcase | SearchForAttribute | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockEvents.testcase | CheckCannotBlockOrUnblockCustomEventsAsNonAdmin | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsBlockedEventsList.testcase | BlockedEventsListSearchable | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsChangeDataType.testcase | ViewUndefinedResultsIsDisplayedWhenChangeDataTypeThatCannotBeInterpreted | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | AssertEventAnalysisEmptyState | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CanPaginationEventAnalysisResult | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | EditEventName | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | SearchEventAttributeInEventTab | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | SearchEventsInDifferentTabs | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | CreateEventAnalysisWithBooleanAttributeFilterTrueAndFalseCondition | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | CreateEventAnalysisWithDateAttributeFilterAllCondition | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | CreateEventAnalysisWithNumberAttributeFilterAllCondition | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | CreateEventAnalysisWithStringAttributeFilterIsAndIsNotCondition | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsFilterBreakdown.testcase | SortTheResultsAnalysisAfterRearrangingBreakdowns | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsHidden.testcase | CanSearchForAHiddenEvent | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsIndividuals.testcase | AssertEmptyIndividualEvents | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | AnalysisReportListSearchable | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanSortAnalysisReport | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CanDeleteCustomEventDescription | 3 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSettings.testcase | CustomEventListSearchable | 3 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | CreateQueryDataWithAnalyticsCloudAPI | 3 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificBlog | 3 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificDocumentsAndMedia | 3 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestToAccessDataForSpecificIndividual | 3 | | no-ticket | No description ticket |
| analyticscloud/DeleteSegments.testcase | CanDeleteCriteria | 3 | | no-ticket | No description ticket |
| analyticscloud/DocumentsAndMediaEvents.testcase | CheckDocumentPreviewedWhenViewOnPageAndCheckParameters | 3 | | no-ticket | No description ticket |
| analyticscloud/EditSegments.testcase | AssertSegmentOverviewShowsCriteria | 3 | | no-ticket | No description ticket |
| analyticscloud/EditSegments.testcase | SegmentListKebabMenuContainsEditAndDeleteButtons | 3 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CanCancelChangesToEmailReport | 3 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CanChangeEmailReportFrequency | 3 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CanDisableEmailReportAfterEnabling | 3 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CheckEmailReportEditDisabled | 3 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | ExportScheduledButNotStarted | 3 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | RequestCommandHasFromDateAfterToDate | 3 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | RequestCommandHasWrongDateFormat | 3 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | RequestCommandNotContainFromDateOrToDate | 3 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckDeleteBrowserStorageChangesUserId | 3 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckEventSentWhenUserInteractsWithSitesWithDifferentChannelId | 3 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckIdentityEventNotTriggeredAlwaysUserInteractsWithSite | 3 | | no-ticket | No description ticket |
| analyticscloud/IdentityEvents.testcase | CheckLoginSameUserKeepUserId | 3 | | no-ticket | No description ticket |
| analyticscloud/IndividualsAttributeBreakdown.testcase | SelectDifferentTabsInbreakdown | 3 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | CanExpandActivityInIndividualOverview | 3 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | CanUseLast180DaysTimeFilterOnActiveIndividualsChart | 3 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | IndividualNotDuplicatedIfUserExistsInMultipleProperties | 3 | | no-ticket | No description ticket |
| analyticscloud/IndividualsDashboard.testcase | ShowLast5ActivitiesOnIndividualsActivitiesChartWhenSetPaginationTo5 | 3 | | no-ticket | No description ticket |
| analyticscloud/InviteUsers.testcase | AdminCanInviteUsers | 3 | | no-ticket | No description ticket |
| analyticscloud/InviteUsers.testcase | CanInviteMultipleUsers | 3 | | no-ticket | No description ticket |
| analyticscloud/InviteUsers.testcase | MemberCannotInviteUsers | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileDetails.testcase | OrderIndividualDetails | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileSegments.testcase | AssertIndividualsSegmentSelected | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileSegments.testcase | OrderIndividualsAssociatedSegments | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsProfileSegments.testcase | PaginateIndividualsSegmentList | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | AssertNoIndividualsActivitiesResults | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | AssertNoIndividualsAssociatedSegmentsResults | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | AssertNoResultsInIndividualsOverviewAssociatedSegment | 3 | | no-ticket | No description ticket |
| analyticscloud/KnownIndividualsSearch.testcase | SortIndividualList | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | AdminCanBeEditedOrRemoved | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | AdminCanBeSelected | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | AssertUserManagementPage | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | MemberCanBeEditedOrRemoved | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | MemberCanBeSelected | 3 | | no-ticket | No description ticket |
| analyticscloud/ManageUsers.testcase | ViewAllUserList | 3 | | no-ticket | No description ticket |
| analyticscloud/NewProperty.testcase | CantCreateAPropertyWithNameInBlankOrNull | 3 | | no-ticket | No description ticket |
| analyticscloud/PageEvents.testcase | CheckPageDepthReachedWhenPressPageDownKey | 3 | | no-ticket | No description ticket |
| analyticscloud/PageProfilePath.testcase | CanTimeFilterUse180DaysPathAnalysis | 3 | | no-ticket | No description ticket |
| analyticscloud/PagesList.testcase | PeriodFilter180DaysPageTable | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | AutoRenamePropertyWhenDuplicateWithExistingOne | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CanCreatePropertiesWhenNoDataSourceIsCreatedYet | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CanCreatePropertyAndCancel | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CanCreatePropertyNameWithSpecialChars | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CannotAddPropertyWhenNameExceedMaximumLength | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | CreatePropertiesWhenNoDataSourceIsConnected | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | NoDuplicatePropertiesName | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | PropertiesCanBeAccessedDirectlyViaURL | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | PropertyCanHaveABigName | 3 | | no-ticket | No description ticket |
| analyticscloud/Properties.testcase | PropertyNameWithCharQuantityValidation | 3 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CanSearchForPagePropertiesList | 3 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CanUserSelectPropertyFromTheDropDownByFirstTime | 3 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | ViewSettingInfoAreGlobalAndDontDependOfProperty | 3 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | ViewTheDropdownTransition | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingAllParameters | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingDisplayLanguageId | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingGroupID | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingNoParameters | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingPage | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingSize | 3 | | no-ticket | No description ticket |
| analyticscloud/SearchAPI.testcase | RequestKeywordsUsingSort | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCancelCreatingDynamicSegment | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanCreateNestedCriteria | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanDuplicateCriteria | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanPaginateSegmentProfileInMembershipList | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CanPreviewDynamicSegmentMembers | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsCreation.testcase | CheckNotPossibleEditSegmentNameToExistsOne | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsDistribution.testcase | CanDistributionChartFilteredText | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsDistribution.testcase | OrderSegmentProfileDistribution | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsList.testcase | PaginateSegmentList | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsMembership.testcase | OrderSegmentProfileMembershipList | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsMembership.testcase | SearchDynamicSegmentsMembershipPreviewModal | 3 | | no-ticket | No description ticket |
| analyticscloud/SegmentsOverViewMembership.testcase | OrderDynamicSegmentMembershipPreview | 3 | | no-ticket | No description ticket |
| analyticscloud/SidebarMenu.testcase | AssertPropertyMenuAfterEditProperty | 3 | | no-ticket | No description ticket |
| analyticscloud/SidebarMenu.testcase | ViewLastSelectPropertyAfterRelogin | 3 | | no-ticket | No description ticket |
| analyticscloud/SitesActivities.testcase | CanUniqueVisitorsLabelShowPagesDashboards | 3 | | no-ticket | No description ticket |
| analyticscloud/SitesActivities.testcase | CanUniqueVisitorsLabelShowSitesDashboards | 3 | | no-ticket | No description ticket |
| analyticscloud/SitesOverview.testcase | CanViewSitesOverviewDashboardCards | 3 | | no-ticket | No description ticket |
| analyticscloud/SitesPropertyAnalytics.testcase | AssertSiteMetricsSelectSessionsPerVisitorCardTab | 3 | | no-ticket | No description ticket |
| analyticscloud/SitesPropertyAnalytics.testcase | AssertSiteMetricsSelectUniqueVisitorsCardTab | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanPaginateInOrdersAttributeList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanPaginateInProductAttributeList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanPaginationAttributeForAccountList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanPaginationAttributeForPeopleList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSortAttributeForAccountList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSortAttributeForPeopleList | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSortOrdersListByAttribute | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncAttributes.testcase | CanSortProductListByAttribute | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckCanSortByAccountGroupsName | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckCanSortByOrganizationsName | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckCanSortByUserGroupsName | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncContactsAndAccounts.testcase | CheckIsPossiblePaginateGroups | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | MakePaginationInChannelsAndSitesTabsOnAssignedPropertyModal | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | SelectedSiteNumberCorrectWhenCommerceToggleDisabled | 3 | | no-ticket | No description ticket |
| analyticscloud/SyncSitesAndChannels.testcase | SortInChannelsAndSitesTabOnAssignedPropertyModal | 3 | | no-ticket | No description ticket |
| analyticscloud/Usage.testcase | CheckCurrentPlanDetails | 3 | | no-ticket | No description ticket |
| analyticscloud/Usage.testcase | IndividualsAndPageViewsCardShowCurrentPlanLimitAndAddOnInfo | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | AdminCanChangeUserPermissions | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | AssertNewAdminPermissions | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | AssertNewMemberPermissions | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | ChangeMultipleUserPermissions | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | ChangeUserRoleAndAssertSuccessNotification | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | LoggedUserAcessesDirectlyInexistentWorkspace | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | OnlyAdminOrOwnerUsersCanAddPropertiesOnAC | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | SelectandChangeAnAdminAndMemberToAdmins | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | SelectAndChangeAnAdminAndMembertoMembers | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | UserLostAccessToProperty | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | UserNotLoggedAccessACURLPassingWorkspaceParameter | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | UsersListCanBeOrdered | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagement.testcase | UsersListCanBePaginated | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagementDelete.testcase | AdminCanDeleteUser | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagementDelete.testcase | CanSelectAndDeleteUsers | 3 | | no-ticket | No description ticket |
| analyticscloud/UserManagementDelete.testcase | MemberCantDeleteUser | 3 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | CancelUnsavedChangesOfWorkspaceSettings | 3 | | no-ticket | No description ticket |
| contentperformance/ContentPerformance.testcase | ChangeLanguageInWidgetPage | 3 | | no-ticket | No description ticket |
| contentperformance/ContentPerformance.testcase | ViewsGraphInDocumentDisplayPage | 3 | | no-ticket | No description ticket |
| contentperformance/ContentPerformanceWithoutAC.testcase | NotSyncedPanel | 3 | | no-ticket | No description ticket |
| segmentation/ExperienceWithContentPages.testcase | AddTwoExperiencesBelowDefault | 3 | | no-ticket | No description ticket |
| segmentation/ExperienceWithContentPages.testcase | AddTwoExperiencesOverDefault | 3 | | no-ticket | No description ticket |
| segmentation/ExperienceWithContentPages.testcase | ExperienceDropdownAddNewSegmentCancelInputs | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | CanAssignBySegmentEnabledInstanceSettings | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationAssigningRoles.testcase | EnableAssignRolesNoWarningMessage | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | CanEditConjunctionInConditionsSegments | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationCreateSegment.testcase | ViewDraggableAreaWhenDraggingPropertyItem | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewSegmentationDisabledWarningUserWithoutPermissionAtDynamicCollectionEditor | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewSegmentationDisabledWarningUserWithoutPermissionAtExperienceMenu | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewSegmentationDisabledWarningUserWithoutPermissionAtManualCollectionEditor | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationDisabledAtInstance.testcase | ViewSegmentationDisabledWarningUserWithoutPermissionAtSegmentsList | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationDisabledAtSystem.testcase | ViewSegmentationDisabledWarningUserWithoutsPermissionToSystemSettings | 3 | | no-ticket | No description ticket |
| segmentation/SegmentationEditSegment.testcase | ViewIconsOfSegmentsActions | 3 | | no-ticket | No description ticket |
| abtest/ABTestWithoutACConnection.testcase | CheckHiddenABTestPanel | 2 | | no-ticket | No description ticket |
| analyticscloud/BlogsEvents.testcase | CheckBlogViewedDoesNotTriggerWhenBlogIsVisible | 2 | | no-ticket | No description ticket |
| analyticscloud/ConnectionPropertyList.testcase | CheckPropertyListEmptyStateMessage | 2 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsEventAnalysis.testcase | CheckVoteAndPostedEventsAppearLikeDefaultEvent | 2 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CanCreateAnalysisReportWithSpecialCharacterOnName | 2 | | no-ticket | No description ticket |
| analyticscloud/CustomEventsSavingAnalysis.testcase | CheckEmptyStateOfAnalysisReport | 2 | | no-ticket | No description ticket |
| analyticscloud/DataReportAPI.testcase | RequestShowErrorMessageWhenUseInvalidType | 2 | | no-ticket | No description ticket |
| analyticscloud/DeleteSegments.testcase | DeleteSegmentInEditMode | 2 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CheckEmailReportsAppearsForAllUsers | 2 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CheckEmailReportWidgetDisabledWhenPropertyNotSyncedWithSite | 2 | | no-ticket | No description ticket |
| analyticscloud/EmailReport.testcase | CheckLinksInEmailReportAreCorrect | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckAllAssetsMessagesWhenHasPropertyAndDataSource | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckAllMessageWhenHasPropertyNoDataSource | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckAllPagesMessagesWhenHasPropertyAndDataSource | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckAllSegmentMessagesWhenHasPropertyAndDataSourceButNoData | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckAllSitesMessagesWhenHasPropertyAndDataSourceButNoData | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckMessageWhenNoPropertyNoDataSource | 2 | | no-ticket | No description ticket |
| analyticscloud/EmptyState.testcase | CheckSearchMessagesWithNoResults | 2 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | InternalProblemOccurredInExportProcess | 2 | | no-ticket | No description ticket |
| analyticscloud/ExportAPI.testcase | RequestCommandHasInvalidType | 2 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CheckMessageAppearsIfNotPutTextOrExpectedTextOnDeleteModal | 2 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | CheckSuccessfulAlertAppearsIfPropertyDeleted | 2 | | no-ticket | No description ticket |
| analyticscloud/PropertiesList.testcase | DeletedPropertiesNotAppearAsOptionInSyncSites | 2 | | no-ticket | No description ticket |
| analyticscloud/SidebarMenu.testcase | ChangeDefaultLanguage | 2 | | no-ticket | No description ticket |
| wem/accessiblity/SegmentationWithKeyboard.testcase | ViewBackButtonTooltipForSegmentsAdminViaKeyboard | 2 | | no-ticket | No description ticket |
| wem/accessiblity/SegmentationWithKeyboard.testcase | ViewBackButtonTooltipOfNewSegmentFromNewExperienceViaKeyboard | 2 | | no-ticket | No description ticket |
| analyticscloud/SidebarMenu.testcase | ViewNoResultsWhenSearchNonExistentProperty | 1 | | no-ticket | No description ticket |
| analyticscloud/Workspace.testcase | ViewAvailableTimezonesSortedByAscendingAlphabetic | 1 | | no-ticket | No description ticket |

## Files by Component & Test Count

Classification based on `property testray.main.component.name` in each `.testcase`. Sorted ascending by test count within each component so small files (easy migration wins) appear first.

### Totals by Component

| Component | Files | Tests |
|-----------|-------|-------|
| A/B Test | 2 | 45 |
| Analytics Cloud | 85 | 502 |
| Content Performance | 2 | 30 |
| Segmentation | 7 | 114 |
| **Total** | **96** | **691** |

### Quick-win candidates (1–2 tests, 25 files)

These are the best starting points for incremental migration.

| Component | File | Tests |
|-----------|------|-------|
| Analytics Cloud | analyticscloud/ACLogin.testcase | 1 |
| Analytics Cloud | analyticscloud/AnalyticsCloudDXP.testcase | 1 |
| Analytics Cloud | analyticscloud/ClientJS.testcase | 1 |
| Analytics Cloud | analyticscloud/CustomEventsUnblockEvents.testcase | 1 |
| Analytics Cloud | analyticscloud/DisconnectACAndDXP.testcase | 1 |
| Analytics Cloud | analyticscloud/IndividualDefinitions.testcase | 1 |
| Analytics Cloud | analyticscloud/IndividualsEnrichedProfiles.testcase | 1 |
| Analytics Cloud | analyticscloud/KnownIndividualsProfileActivitiesList.testcase | 1 |
| Analytics Cloud | analyticscloud/NewProperty.testcase | 1 |
| Analytics Cloud | analyticscloud/PageProfileAudience.testcase | 1 |
| Analytics Cloud | analyticscloud/PageProfileViewsByTechnology.testcase | 1 |
| Analytics Cloud | analyticscloud/SegmentsCriteriaOverview.testcase | 1 |
| Analytics Cloud | analyticscloud/SegmentsOverViewMembership.testcase | 1 |
| Analytics Cloud | analyticscloud/SitesSearchTerms.testcase | 1 |
| Analytics Cloud | analyticscloud/UserManagementEdit.testcase | 1 |
| A/B Test | abtest/ABTestWithoutACConnection.testcase | 2 |
| Analytics Cloud | analyticscloud/APIToken.testcase | 2 |
| Analytics Cloud | analyticscloud/CommentsEvents.testcase | 2 |
| Analytics Cloud | analyticscloud/ConnectACWithDXP.testcase | 2 |
| Analytics Cloud | analyticscloud/DocumentsAndMediaEvents.testcase | 2 |
| Analytics Cloud | analyticscloud/KnownIndividualsProfileDetails.testcase | 2 |
| Analytics Cloud | analyticscloud/RatingEvents.testcase | 2 |
| Analytics Cloud | analyticscloud/ReviewConnectionInformation.testcase | 2 |
| Analytics Cloud | analyticscloud/SegmentsDistribution.testcase | 2 |
| Analytics Cloud | analyticscloud/SitesActivities.testcase | 2 |
| Analytics Cloud | analyticscloud/SitesPropertyAnalytics.testcase | 2 |
| Analytics Cloud | analyticscloud/SitesPropertyAnalyticsFilter.testcase | 2 |
| Segmentation | segmentation/SegmentationEditSegment.testcase | 2 |

### A/B Test (2 files, 45 tests)

| File | Tests |
|------|-------|
| abtest/ABTestWithoutACConnection.testcase | 2 |
| abtest/ABTesting.testcase | 43 |

### Content Performance (2 files, 30 tests)

| File | Tests |
|------|-------|
| contentperformance/ContentPerformanceWithoutAC.testcase | 3 |
| contentperformance/ContentPerformance.testcase | 27 |

### Segmentation (7 files, 114 tests)

| File | Tests |
|------|-------|
| segmentation/SegmentationEditSegment.testcase | 2 |
| segmentation/ExperienceWithContentPages.testcase | 3 |
| wem/accessiblity/SegmentationWithKeyboard.testcase | 3 |
| segmentation/SegmentationDisabledAtSystem.testcase | 4 |
| segmentation/SegmentationAssigningRoles.testcase | 11 |
| segmentation/SegmentationDisabledAtInstance.testcase | 34 |
| segmentation/SegmentationCreateSegment.testcase | 57 |

### Analytics Cloud (85 files, 502 tests)

| File | Tests |
|------|-------|
| analyticscloud/ACLogin.testcase | 1 |
| analyticscloud/AnalyticsCloudDXP.testcase | 1 |
| analyticscloud/ClientJS.testcase | 1 |
| analyticscloud/CustomEventsUnblockEvents.testcase | 1 |
| analyticscloud/DisconnectACAndDXP.testcase | 1 |
| analyticscloud/IndividualDefinitions.testcase | 1 |
| analyticscloud/IndividualsEnrichedProfiles.testcase | 1 |
| analyticscloud/KnownIndividualsProfileActivitiesList.testcase | 1 |
| analyticscloud/NewProperty.testcase | 1 |
| analyticscloud/PageProfileAudience.testcase | 1 |
| analyticscloud/PageProfileViewsByTechnology.testcase | 1 |
| analyticscloud/SegmentsCriteriaOverview.testcase | 1 |
| analyticscloud/SegmentsOverViewMembership.testcase | 1 |
| analyticscloud/SitesSearchTerms.testcase | 1 |
| analyticscloud/UserManagementEdit.testcase | 1 |
| analyticscloud/APIToken.testcase | 2 |
| analyticscloud/CommentsEvents.testcase | 2 |
| analyticscloud/ConnectACWithDXP.testcase | 2 |
| analyticscloud/DocumentsAndMediaEvents.testcase | 2 |
| analyticscloud/KnownIndividualsProfileDetails.testcase | 2 |
| analyticscloud/RatingEvents.testcase | 2 |
| analyticscloud/ReviewConnectionInformation.testcase | 2 |
| analyticscloud/SegmentsDistribution.testcase | 2 |
| analyticscloud/SitesActivities.testcase | 2 |
| analyticscloud/SitesPropertyAnalytics.testcase | 2 |
| analyticscloud/SitesPropertyAnalyticsFilter.testcase | 2 |
| analyticscloud/CustomEventsBlockedEventsList.testcase | 3 |
| analyticscloud/CustomEventsIndividuals.testcase | 3 |
| analyticscloud/EditSegments.testcase | 3 |
| analyticscloud/FormEvents.testcase | 3 |
| analyticscloud/IndividualsAttributeBreakdown.testcase | 3 |
| analyticscloud/KnownIndividualsProfileSegments.testcase | 3 |
| analyticscloud/PageProfileVisitorBehavior.testcase | 3 |
| analyticscloud/PagesList.testcase | 3 |
| analyticscloud/SegmentsOrganizationAttributes.testcase | 3 |
| analyticscloud/UserManagementDelete.testcase | 3 |
| analyticscloud/ActiveIndividuals.testcase | 4 |
| analyticscloud/CustomEventsBlockEvents.testcase | 4 |
| analyticscloud/DataControlAndPrivacy.testcase | 4 |
| analyticscloud/DeleteSegments.testcase | 4 |
| analyticscloud/PageProfilePath.testcase | 4 |
| analyticscloud/SegmentsList.testcase | 4 |
| analyticscloud/SitesOverview.testcase | 4 |
| analyticscloud/Usage.testcase | 4 |
| analyticscloud/AnalyticsCloudConnectionTest.testcase | 5 |
| analyticscloud/CustomEventsChangeDataType.testcase | 5 |
| analyticscloud/DefinitionsSearch.testcase | 5 |
| analyticscloud/InviteUsers.testcase | 5 |
| analyticscloud/SegmentsMembership.testcase | 5 |
| analyticscloud/SidebarMenu.testcase | 5 |
| analyticscloud/CustomEventsGlobalAttributes.testcase | 6 |
| analyticscloud/SegmentsCreationIndividualAttributes.testcase | 6 |
| analyticscloud/Workspace.testcase | 6 |
| analyticscloud/ABTestDXPSide.testcase | 7 |
| analyticscloud/EmptyState.testcase | 7 |
| analyticscloud/ExportAPI.testcase | 7 |
| analyticscloud/SearchAPI.testcase | 7 |
| segmentation/ACSegmentsInDXP.testcase | 7 |
| analyticscloud/BlogsEvents.testcase | 8 |
| analyticscloud/ConnectionPropertyList.testcase | 8 |
| analyticscloud/CustomEventsAttributeSettings.testcase | 8 |
| analyticscloud/CustomEventsHidden.testcase | 8 |
| analyticscloud/EmailReport.testcase | 8 |
| analyticscloud/IdentityEvents.testcase | 8 |
| analyticscloud/ManageUsers.testcase | 8 |
| analyticscloud/CustomEventsSettings.testcase | 9 |
| analyticscloud/IndividualsDashboard.testcase | 9 |
| analyticscloud/PropertiesList.testcase | 9 |
| analyticscloud/SyncSitesAndChannels.testcase | 9 |
| analyticscloud/AssetsForms.testcase | 10 |
| analyticscloud/CustomEventsSavingAnalysis.testcase | 10 |
| analyticscloud/KnownIndividualsSearch.testcase | 10 |
| analyticscloud/AssetsWebContent.testcase | 11 |
| analyticscloud/PageEvents.testcase | 11 |
| analyticscloud/CustomEventsEventAnalysis.testcase | 12 |
| analyticscloud/AssetsBlogs.testcase | 13 |
| analyticscloud/CustomEventsFilterBreakdown.testcase | 13 |
| analyticscloud/DataReportAPI.testcase | 15 |
| analyticscloud/SegmentsCreation.testcase | 15 |
| analyticscloud/AssetsDocumentsAndMedia.testcase | 16 |
| analyticscloud/Properties.testcase | 16 |
| analyticscloud/SegmentsCreationWebBehavior.testcase | 16 |
| analyticscloud/SyncContactsAndAccounts.testcase | 16 |
| analyticscloud/UserManagement.testcase | 21 |
| analyticscloud/SyncAttributes.testcase | 23 |

> **Note on file locations:** all `analyticscloud/*`, `abtest/*`, `contentperformance/*`, `segmentation/*` and `wem/accessiblity/*` paths are under `portal-web/test/functional/com/liferay/portalweb/tests/enduser/`.