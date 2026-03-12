---

argument-hint: "<component-name-or-testcase-path>"
description: Migrate a Liferay Poshi .testcase file to its modern test layer (Playwright, Jest, JUnit unit, JUnit integration). Routes each test to the right layer, consolidates compatible tests at write-time, and follows existing Playwright patterns from a configurable reference folder. Use when the user asks to migrate, port, or convert a Poshi .testcase to Playwright, Jest, or JUnit.
name: poshi-migrate

---

# Migrate Poshi Tests

A playbook for migrating a Poshi `.testcase` file to the appropriate modern test layer. Each test is routed to **Playwright**, **Jest**, **JUnit unit**, or **JUnit integration** based on what it actually validates. Compatible Playwright tests sharing setup may be consolidated into a single test at write-time.

This skill assumes the Poshi suite has already been triaged and shrunk if needed (`/poshi-shrink`). It does not run a Poshi-side consolidation pass — consolidation happens only when writing the modern test, and only when the merged form is clearly cleaner than separate tests.

## Preconditions

The working tree must be clean. Abort and ask the user to commit or stash first when dirty.

## Inputs

### Argument

`${ARGUMENTS}` is either:

1. A `@component-name` value (for example, `portal-analytics-cloud`). Scan the Poshi tests root for `.testcase` files whose `@component-name` matches, list them with their test counts, and ask the user to pick one.

1. An absolute or repo-relative path to a single `.testcase` file. Use it directly.

When `${ARGUMENTS}` is empty, ask the user which component or file to migrate. Do not guess.

### Configuration

Read `.claude/skills/poshi-migrate/config.json`. The keys are:

- **componentPlaywrightModules**: a mapping from `@component-name` to the list of Playwright modules under `playwrightTestsRoot` that own its existing specs. Phase 1 scans these modules to detect tests that already cover, or partially cover, the Poshi tests being migrated. For example, `portal-analytics-cloud` maps to `analytics-client-js`, `analytics-reports-js-components-web`, `analytics-settings-web`, `analytics-web`, `osb-faro-web`, `segment-experiment-web`, and `segments-web`.

- **patternsReference**: the Playwright folder used as the style reference. Defaults to `modules/test/playwright/tests/layout-content-page-editor-web`.

- **playwrightTestsRoot**: the root for Playwright specs. Defaults to `modules/test/playwright/tests`.

- **poshiTestsRoot**: the root for Poshi `.testcase` files. Defaults to `portal-web/test/functional/com/liferay/portalweb/tests/enduser`.

After reading the file, show the resolved values to the user via `AskUserQuestion` and offer to keep them or override any value for this run. Do not modify the file unless the user explicitly asks for a permanent change.

## Workflow

The skill runs in four phases: inventory, plan, implement, validate. Each phase is gated on user approval.

### Phase 1: Inventory

1. Read the `.testcase` file. Capture the `setUp`, `tearDown`, and every `test <Name>` block with its `@description`, `@priority`, and the macros or helper calls it invokes.

1. Scan the Playwright modules listed in `componentPlaywrightModules` for the source `@component-name`. For every Poshi `test`, classify the existing coverage as one of:

	- **Already covered** — an existing spec exercises the same behavior end to end. Record the spec path. The test will be deleted from the `.testcase` file in Phase 3 with a commit that names the covering spec; it does not become a migration.

	- **Partially covered** — an existing spec exercises part of the behavior or shares the same setup. Record the spec path so Phase 2 can plan an extension of that spec instead of a new file.

	- **Not covered** — no existing spec touches the behavior. Continue to routing.

	Compare each Poshi test against candidate specs across these signals (the more that align, the higher the confidence):

	- **Description and comments** — does the Poshi `@description` or any header comment match the spec's `test(...)` title or section comments?

	- **Functionality** — do both validate the same feature path (same panel, same flow, same final state)?

	- **Selectors** — do the Poshi macro arguments (IDs, classes, and labels passed into `Click`, `AssertTextEquals`, and friends) overlap with the spec's `getByRole`, `getByLabel`, and `getByText` arguments? Resolve the macro to its `.macro` definition when the selector is not inline.

	- **Structure** — same setup, same step order, same assertions in roughly the same place?

	This is the most fragile part of the workflow; expect to iterate on the heuristic as false positives and false negatives surface. When the signals disagree, default to **Partially covered** and let the user resolve the call in Phase 2.

	When `componentPlaywrightModules` does not list the source `@component-name`, ask the user which Playwright modules to scan before continuing. Do not skip this step.

1. For every test whose `@description` references an LPS or LPD ticket, recover the original code change and use its diff as the primary signal for the layer decision. From the repo root:

	```bash
	git log \
		--all \
		--format="%H %s" \
		--grep="^${TICKET}"
	```

	Run `git show <sha>` on each match to read the diff, and let the changed files drive the layer choice in the next step:

	- Java `*Util`, formatter, parser, or pure-logic class with no service or runtime touch → JUnit unit.

	- `.js`, `.jsx`, `.ts`, or `.tsx` module that does not import portal-runtime globals → Jest.

	- `*LocalServiceImpl`, persistence layer, listener, or OSGi `@Component` → JUnit integration.

	- JSP, taglib output, multi-module UI wiring, or anything that only manifests in the rendered page → Playwright.

	Record the matching commit shas and the touched files in the per-test notes so Phase 2 can cite them in the plan. When `git log` returns no match (test predates the convention or the ticket never landed in this repo) record `ticket not in tree` and fall back to the heuristic table in the next step.

1. For every `test` not classified as **Already covered**, decide the routing in this order. Pick the first match. When the previous step recovered a ticket diff, treat its signal as the layer choice unless the diff and the test's actual assertions clearly disagree — in that case, record both signals and mark the test `unsure`.

	| Layer | Pick When |
	| --- | --- |
	| **JUnit unit** | The test validates pure Java logic that does not need the portal runtime, services, or persistence. Common signal: assertions over plain values produced by a utility class, or behavior already reproducible at the package level. |
	| **Jest** | The test validates frontend behavior that lives in a JavaScript or React module and can be reproduced without the portal — component rendering, state transitions, formatting, validation logic. |
	| **JUnit integration** | The test exercises services, persistence, OSGi components, listeners, or portal-runtime behavior. Anything that needs a deployed bundle but does not need a real browser. |
	| **Playwright** | Anything left: complete UI flows, multi-page navigation, browser-only behavior, JavaScript-driven interactions that a unit or integration test cannot cover. |

	If you cannot place a test confidently, mark it `unsure` and flag it for the user in the plan. Do not invent a destination.

1. For each test routed to Playwright, locate the destination spec folder under `playwrightTestsRoot`. The destination is the folder whose owning module renders the feature under test (for example, the segments-experiment panel lives under `tests/segment-experiment-web/main`). When in doubt, search the Playwright tests root for an existing spec that imports or interacts with the same UI surface.

### Phase 2: Plan

Build the plan with `EnterPlanMode`. Format:

```markdown
## Source

`<.testcase path>` — `@component-name = <value>`, `testray.main.component.name = <value>`, <N> tests.

## Existing Coverage

| Test | LPS/LPD | Status | Covered By | Action |
| --- | --- | --- | --- | --- |
| `<TestName>` | `<TICKET>` | Already covered / Partially covered / Not covered | `<repo-relative spec path or —>` | Delete from `.testcase` / Extend `<spec>` / Migrate |

When every test is **Not covered**, write `None — no overlap with existing Playwright specs`.

## Per-Test Routing

Only includes tests classified as **Partially covered** or **Not covered**.

| Test | LPS/LPD | Layer | Destination | Notes |
| --- | --- | --- | --- | --- |
| `<TestName>` | `<TICKET>` | Playwright / Jest / JUnit unit / JUnit integration | `<repo-relative path>` | <Consolidates with X / extends `<spec>` / new fixture needed / etc> |

## Consolidations (Playwright)

> **`<final-spec-name>.spec.ts`** (consolidates `<TestA>` + `<TestB>`):
>
> - Shared setup: <site, page, user>.
> - Sequence: <step list>, ending with assertions for both LPS tickets.
> - Tags: `@<TICKET-A>`, `@<TICKET-B>`.

When no consolidation applies, write `None — one spec per test`.

## New Helpers, Utils, or Fixtures

List any new file under `helpers/`, `utils/`, or `fixtures/` you plan to add or extend, and the reason. When existing files cover the need, write `None — reuse existing`.

## Cleanup

- Remove the **Already covered** `test` blocks from `<.testcase path>` in their own commits, before any migration commits.
- Remove the migrated `test` blocks from `<.testcase path>`.
- Delete the `.testcase` file when it ends up empty.

## Commits

In order:

1. One commit per **Already covered** test (or per group sharing a covering spec), removing the test from the `.testcase` file. The commit message names the spec that already covers it.

1. New helper, util, or fixture commits, when any.

1. One commit per migrated test or consolidated group.
```

The plan must be exhaustive: every `test` from Phase 1 appears in either the **Existing Coverage** table or the **Per-Test Routing** table (or both, when partially covered), and every consolidation lists every source test it covers. Keep tests marked `unsure` in their own row and ask the user to confirm a layer before continuing.

### Phase 3: Implement

After `ExitPlanMode` returns the user's approval, execute the plan in this order:

1. **Already-covered cleanup commits** first. For every test classified as **Already covered** in Phase 1, remove the `test` block from the source `.testcase` file (delete the file when it ends up empty), run `/format-source` on every changed file, and commit. The commit message must name the spec that already covers the test (for example, `LPD-X Remove test FooBar — already covered by tests/segments-web/main/foo-bar.spec.ts`). One commit per already-covered test, or per group of already-covered tests sharing a single covering spec.

1. **New helper, util, or fixture commits**, one per artifact. Verify the artifact does not already exist by scanning `modules/test/playwright/{helpers,fixtures,utils}` before creating it.

1. **Migration commits**, one per row in the routing table or per consolidation group. Each commit:

	1. Writes the new test file at the planned destination.

	1. Removes the migrated `test` blocks from the `.testcase` file. When the file ends up empty after the removals, delete the file in the same commit.

	1. Runs `/format-source` on every changed file before staging.

	1. Uses `/commit` to write the commit message.

When implementing each layer, follow the rules below.

#### Playwright

Follow `.claude/rules/playwright.md` for the general conventions (layout, fixtures, page classes, locators, setup, cleanup, flake avoidance, feature flags). The reference folder for style is `patternsReference` (default `modules/test/playwright/tests/layout-content-page-editor-web`). Read at least one spec from that folder before writing the first migrated spec to internalize the local idiom.

The conventions below apply specifically to specs migrated from Poshi.

##### Setup Mappings

Map Poshi setup actions to API helpers:

- **Content page** (Poshi `JSONLayout.addLayout` or UI page creation) → `apiHelpers.headlessDelivery.createSitePage`. Pass a `pageDefinition` from `tests/layout-content-page-editor-web/main/utils/getPageDefinition` when fragments are needed.

- **Mid-test login** (Poshi `User.logoutPG` plus `User.loginPG`) → `performLogout` followed by `performLogin` from `utils/performLogin`. Use a fresh user when the test mutates a per-user preference that would leak across runs.

- **Site** (Poshi `JSONGroup.addGroup` or `Site.add`) → `apiHelpers.headlessAdminSite.postSite`. Prefer the `isolatedSiteTest` fixture, which provides a `site` with auto-cleanup.

- **User** (Poshi `JSONUser.addUser`) → `apiHelpers.headlessAdminUser.postUserAccount`, then `assignUserToSite` with the role from `getRoleByName('Site Administrator')`. Register the user in `userData` so `performLogin` can sign in as that user.

##### New Helpers, Utils, Fixtures

When the migration requires a new artifact under `modules/test/playwright/{helpers,fixtures,utils}`, scan the existing files for one that already covers the need before adding it. Extend an existing file rather than forking a new one. When a new artifact is genuinely needed, place it in the same folder structure and ship it in its own commit before the migration commit that uses it.

##### Comments

Use short, single-line block comments to mark the structural sections of the test (`// Create a content page`, `// Open the AB Test panel`, `// Hide the AB Test panel permanently`). The comment must fit on one line. Do not paraphrase what the next line does, and do not repeat the LPS or LPD ticket inside comments — the test-level `tag` already records that.

##### Inline Locators

Repeat the locator at every call site instead of binding it to a `const` at the top of the test. Inline locators keep each step self-contained and readable; an extracted constant only pays off when the locator is reused dozens of times across a long test, which is rare for a migrated Poshi spec.

#### Jest

1. Place the new test under `<module-root>/test`, mirroring the path of the source file under test.

1. Run with `cd <module-root> && yarn test <relative-path>`.

1. Mock only what the JavaScript module under test cannot exercise directly. Avoid mocking the portal runtime — if you need it, the test belongs in Playwright or integration.

1. Frontend conventions: `*.test.js`, `*.test.ts`, or `*.test.tsx` matching the module's existing pattern.

#### JUnit Unit

1. Place the new test under `<module-root>/src/test/java`, in the same package as the class under test.

1. Run with `cd <module-root> && <gradlew> test --tests <TestClassName>`.

1. The test must not require the portal runtime, services, or persistence.

#### JUnit Integration

1. Place the new test under `<module-root>/src/testIntegration/java`, in the same package as the class under test.

1. Run with `cd <module-root> && <gradlew> testIntegration --tests <TestClassName>`.

1. Follow the team conventions for Liferay integration tests: auto-cleanup via test rules and annotations rather than `@After` or manual deletion, `@FeatureFlags` per method when needed, trigger artifacts through real listeners (for example, `DSRTestUtil` plus `addObjectEntry` instead of a hand-rolled `addGroup`).

1. Reuse `*TestUtil` classes (`AccountEntryTestUtil`, `ObjectEntryTestUtil`, and the like) for fixtures.

1. Use `TransformUtil.transformToArray` plus `ArrayUtil.contains` for "is `X` in list" assertions instead of manual loops.

### Phase 4: Validate

Tests against the runtime are not done until they pass against a live Liferay (and a live Analytics Cloud when the feature requires it).

For Playwright, run from the repo root:

```bash
cd modules/test/playwright && yarn test <relative-spec-path>
```

For JUnit and Jest, use the commands listed in the per-layer sections above.

When a spec fails, fix the spec or the underlying issue before claiming the migration done. Do not skip, mark `xfail`, or comment out a failing assertion to land the commit.

#### Flake Check (Playwright Only)

After the first green run, repeat each migrated Playwright spec ten times to surface flakiness from timing, viewport, or network races:

```bash
cd modules/test/playwright && yarn test --repeat-each=10 <relative-spec-path>
```

All ten runs must pass. When any run fails, fix the underlying race before declaring the migration done. Do not apply the flake check to Jest, JUnit unit, or JUnit integration tests — they are deterministic enough that the extra runs only burn time.

## Output

After the last commit, report:

- The source `.testcase` (deleted or surviving with a tail of remaining tests).

- The list of new files added, by layer, with their repo-relative paths and the LPS or LPD ticket they cover.

- The list of new helpers, utils, or fixtures introduced, with one line each describing what they expose.

- Total commits made, in order.

- Any test marked `unsure` that the user routed manually, plus the reasoning recorded in the plan.