# Sauce Demo E2E Testing

E2E tests for [saucedemo.com](https://www.saucedemo.com/) built with Gradle, Gherkin, Screenplay and Selenium (via Serenity BDD).

## Requirements

- Java 17 or higher
- Google Chrome installed (the chromedriver binary is downloaded automatically)

## Running the tests

To run the tests and generate the report in one go:

```bash
./gradlew clean test
```

The `clean` step deletes the previous report output, and the Serenity report is
generated automatically after the tests finish.

## Viewing the report

Open the generated Serenity report in the browser:

```bash
open target/site/serenity/index.html
```

To regenerate the report without re-running the tests:

```bash
./gradlew aggregate
```

## Test execution options

There is one test runner per feature (`AuthenticationTestSuite`,
`ProductManagementTestSuite`, `CartTestSuite`). Gradle runs them in parallel in
separate worker processes (`maxParallelForks = 3` in `build.gradle`), so each
feature's scenarios execute concurrently and the whole suite finishes in about
half a minute.

By default the tests run in headless mode. To see the browser while the tests run:

```bash
./gradlew clean test -Dchrome.switches=
```

### Running a single module

Each feature has its own tag, so you can run the modules separately:

```bash
# only authentication scenarios
./gradlew clean test -Pcucumber.filter.tags="@authentication"

# only product management scenarios
./gradlew clean test -Pcucumber.filter.tags="@product-management"

# only cart scenarios
./gradlew clean test -Pcucumber.filter.tags="@cart"
```

Without the filter, the whole suite runs:

```bash
./gradlew clean test
```

## Troubleshooting

If you added or renamed a feature file and it still does not show up in the
run or the report, the Gradle daemon cached the requirements in memory.
Restart it and rebuild:

```bash
./gradlew --stop
./gradlew clean test
```

If the report shows "No data available in table", clear the stale test
output and rerun:

```bash
rm -rf target
./gradlew test
```
