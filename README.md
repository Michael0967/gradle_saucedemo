# Sauce Demo - Automated Tests

End to end tests for saucedemo.com using Gradle, Gherkin, Screenplay pattern and Serenity BDD with Selenium.

## Requirements

- Java 17 or higher
- Google Chrome (chromedriver gets downloaded automatically)

## Running the tests

To run the tests and generate the report in one go:

```bash
./gradlew clean test
```

The `clean` step deletes the previous report output, and the Serenity report is
generated automatically after the tests finish.

> **Tip:** if you see stale reports, try `./gradlew --stop` first (the Gradle daemon holds on to old config).

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
`ProductManagementTestSuite`, `ProductFilteringTestSuite`, `CartTestSuite`,
`CheckoutTestSuite`). Gradle runs them in parallel in separate worker processes
(`maxParallelForks = 5` in `build.gradle`), so each feature's scenarios execute
concurrently and the whole suite finishes in about half a minute.

By default the tests run in headless mode. To see the browser while the tests run:

```bash
./gradlew clean test -Dchrome.switches=
```

Handy when you want to see what the browser is doing, though the tests run slower in headed mode.

### Running a single module

Each feature has its own tag, so you can run the modules separately:

```bash
# only authentication scenarios
./gradlew clean test -Pcucumber.filter.tags="@authentication"

# only product management scenarios
./gradlew clean test -Pcucumber.filter.tags="@product-management"

# only product filtering scenarios
./gradlew clean test -Pcucumber.filter.tags="@product-filtering"

# only cart scenarios
./gradlew clean test -Pcucumber.filter.tags="@cart"

# only checkout scenarios
./gradlew clean test -Pcucumber.filter.tags="@checkout"
```

Without the filter, the whole suite runs:

```bash
./gradlew clean test
```

**Note:** the Gradle daemon sometimes caches old requirements. If a renamed feature does not show up, run `./gradlew --stop` and rebuild. Also, if the report says "No data available in table", delete the `target` folder and rerun.

## QA Analysis

### What was tested

Front-end tests on https://www.saucedemo.com/ covering the 5 main modules: authentication, product catalog, filtering, cart, and checkout. 38 scenarios that validate basic functional flows of the interface.

### Risks identified

These issues were found while testing the app:

**Authentication**
- Error messages don't look good in the UI, the error box looks broken
- No eye icon to show/hide the password
- Red highlighting on fields with errors is too invasive, the X marks are unnecessary

**General**
- No responsive tests, a fixed resolution is used

### Pending to test

Things not covered in this suite that could find problems:

- Checkout with multiple products and subtotal verification
- Product images actually loading (not just the tag existing)
- Keyboard navigation in forms
- Behavior with SauceDemo's problem_user and error_user accounts
- Prices with correct format (dollar, two decimal places)
- Whether filters persist after navigating to details and back (the expected behavior is unknown)
- Whether cart and filters persist after refreshing the page

### Answers

**a. Main challenges when implementing the functionalities:**

The React hamburger menu wouldn't respond to Selenium clicks, the click was lost and nothing happened. I had to look into it and found that Serenity has a `JavaScriptClick` that executes JS directly in the browser, that fixed it. The LoginAs cookie was also tricky, I had to use `Evaluate.javascript()` from Serenity to plant the `session-username` cookie with `document.cookie` because the app reads it to know who is logged in.

Serenity reports were another headache. Sometimes they wouldn't appear or showed "No data available in table". The fix was to stop the Gradle daemon with `./gradlew --stop` and run again, or delete the `target` folder. It also happened that I ran tests from one module and it executed another's, for example I put `@product-filtering` and authentication tests ran. The Gradle daemon cached the previous configuration and wouldn't release it. I noticed because the report showed scenarios that didn't match.

**b. Testing techniques and automation approach:**

I used BDD with Gherkin to write test scenarios in a simple and easy to understand way. The automation was built with Java 17, Gradle 9.6, and Serenity BDD, using the Screenplay pattern to better organize actions, validations, and navigation.

I also configured parallel execution to reduce test time and used tags like @smoke, @regression, @critical, and @security to run only the scenarios I needed at each moment.

**c. How did you validate the user experience during the execution of automated flows:**

I validated the experience from a real user's perspective, checking that products were displayed correctly, that prices were consistent across different pages, and that the cart reflected the added products. I also verified that error messages appeared correctly for invalid situations and that navigation options worked as expected, including Back, Continue Shopping, and Cancel.

Finally, I validated the security behavior after logout, checking that when using the browser's Back button, the user couldn't return to the inventory without re-authenticating.

**d. What was the most interesting or enjoyable part:**

The use of the cookie for login was what caught my attention the most. I had used it in other frameworks before, but in this case something curious happened and it's always good to understand how things work. I also enjoy evaluating UX because it complements the evaluation and adds value. And I like working with new technologies, in this case I had never used Gradle before and it was interesting to learn how it works and get familiar with it.
