@checkout
Feature: Checkout

  As a SauceDemo user
  I want to buy what I picked
  So that the stuff arrives at my door

  Background:
    Given {actor} is signed in as "standard_user"

  @smoke @critical
  Scenario: Buy a single product end-to-end
    Given {actor} is on the products page
    And {actor} notes the name of the first product
    And {actor} notes the price of the first product
    And {actor} adds the first product to the cart
    And {actor} opens the cart
    And {actor} proceeds to checkout
    And {actor} fills the checkout information with "Michael" "Rojas" "110141"
    Then {actor} should see the same product name in the checkout overview
    And {actor} should see the same product price in the checkout overview
    When {actor} finishes the order
    Then {actor} should see the order confirmation page
    And {actor} should see a thank you message

  @regression
  Scenario: Cancel from info page goes back to cart
    Given {actor} has a product in the cart
    And {actor} is on the checkout information page
    When {actor} cancels the checkout
    Then {actor} should be back on the cart page

  @regression
  Scenario: Cancel from overview goes back to products
    Given {actor} has a product in the cart
    And {actor} completes the checkout information with valid data
    When {actor} cancels from the checkout overview
    Then {actor} should be redirected to the products page

  @regression
  Scenario: Missing first name triggers error
    Given {actor} has a product in the cart
    And {actor} is on the checkout information page
    When {actor} enters the last name "Rojas"
    And {actor} enters the postal code "110141"
    And {actor} continues to the next step
    Then {actor} should see a checkout error for missing first name

  @regression
  Scenario: Missing last name triggers error
    Given {actor} has a product in the cart
    And {actor} is on the checkout information page
    When {actor} enters the first name "Michael"
    And {actor} enters the postal code "110141"
    And {actor} continues to the next step
    Then {actor} should see a checkout error for missing last name

  @regression
  Scenario: Missing postal code triggers error
    Given {actor} has a product in the cart
    And {actor} is on the checkout information page
    When {actor} enters the first name "Michael"
    And {actor} enters the last name "Rojas"
    And {actor} continues to the next step
    Then {actor} should see a checkout error for missing postal code

  @regression
  Scenario: Overview shows what I added
    Given {actor} is on the products page
    And {actor} notes the name of the first product
    And {actor} notes the price of the first product
    And {actor} adds the first product to the cart
    And {actor} completes the checkout information with valid data
    Then {actor} should see the same product name in the checkout overview
    And {actor} should see the same product price in the checkout overview

  @regression @critical
  Scenario: Back Home after order takes me to products
    Given {actor} is on the products page
    And {actor} adds the first product to the cart
    And {actor} completes the checkout information with valid data
    And {actor} finishes the order
    When {actor} clicks the Back Home button
    Then {actor} should be redirected to the products page

  @security @critical
  Scenario: Cant reach checkout without logging in
    Given {actor} logs out
    When {actor} tries to open the checkout page directly
    Then {actor} should be redirected to the login page
