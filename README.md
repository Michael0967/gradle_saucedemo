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

Scenarios run in parallel (3 concurrent threads, configured in
`src/test/resources/junit-platform.properties`).

By default the tests run in headless mode. To see the browser while the tests run:

```bash
./gradlew clean test -Dchrome.switches=
```

To force headless mode explicitly:

```bash
./gradlew clean test -Dchrome.switches=--headless=new
```

## Troubleshooting

If the report still shows a feature name that you renamed or deleted, the Gradle
daemon cached the requirements in memory. Restart it and rebuild:

```bash
./gradlew --stop
./gradlew clean test
```
