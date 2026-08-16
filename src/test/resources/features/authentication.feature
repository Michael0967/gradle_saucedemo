Feature: User Authentication

  As a SauceDemo user
  I want to be able to authenticate
  So that I can access the product inventory

  Background:
    Given {actor} is on the SauceDemo login page

  @smoke @critical
  Scenario: Successful login with valid credentials
    When {actor} enters the username "standard_user"
    And {actor} enters the password "secret_sauce"
    And {actor} presses the Login button
    Then {actor} should be redirected to the products page
    And {actor} should see the "Products" title

  @regression
  Scenario Outline: Login rejected with invalid credentials
    When {actor} enters the username "<username>"
    And {actor} enters the password "<password>"
    And {actor} presses the Login button
    Then {actor} should see an authentication error message
    And {actor} should not access the products page

    Examples:
      | username       | password       |
      | wrong_user     | secret_sauce   |
      | standard_user  | wrong_password |
      | wrong_user     | wrong_password |

  @regression
  Scenario: Login without entering a username
    When {actor} enters the password "secret_sauce"
    And {actor} presses the Login button
    Then {actor} should see a message indicating that the username is required
    And {actor} should not access the products page

  @regression
  Scenario: Login without entering a password
    When {actor} enters the username "standard_user"
    And {actor} presses the Login button
    Then {actor} should see a message indicating that the password is required
    And {actor} should not access the products page

  @regression
  Scenario: Login without entering any credentials
    When {actor} presses the Login button
    Then {actor} should see a message indicating that the username is required
    And {actor} should not access the products page

  @regression @critical
  Scenario: Locked out user attempts to authenticate
    When {actor} enters the username "locked_out_user"
    And {actor} enters the password "secret_sauce"
    And {actor} presses the Login button
    Then {actor} should not access the products page
    And {actor} should see a message indicating that the user is locked out

  @security @critical
  Scenario: Unauthenticated user tries to access the inventory directly
    When {actor} tries to access the products page directly
    Then {actor} should be redirected to the login page
    And {actor} should not see the inventory

  @security
  Scenario: Authenticated user logs out
    Given {actor} is authenticated as "standard_user"
    When {actor} logs out
    Then {actor} should be redirected to the login page

  @security @critical
  Scenario: User tries to go back to the inventory after logging out
    Given {actor} is authenticated as "standard_user"
    And {actor} is on the products page
    When {actor} logs out
    And {actor} goes back to the previous page
    Then {actor} should not be able to access the inventory without authentication
