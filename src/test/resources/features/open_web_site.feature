Feature: Open Web Site

  Scenario: The login page loads correctly
    Given opens the Sauce Demo application
    Then the browser URL should be "https://www.saucedemo.com/"
