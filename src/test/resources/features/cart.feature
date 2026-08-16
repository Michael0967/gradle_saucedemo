@cart
Feature: Cart

  As a SauceDemo user
  I want to review the products I am about to buy
  So that I keep control over my purchase

  Background:
    Given {actor} is signed in as "standard_user"

  @regression
  Scenario: Add a product to the cart
    Given {actor} is on the products page
    When {actor} adds the first product to the cart
    Then {actor} should see the cart badge showing 1
    And {actor} should see the first product marked as added

  @smoke @critical
  Scenario: A product added to the cart appears in it
    Given {actor} is on the products page
    When {actor} adds the first product to the cart
    And {actor} opens the cart
    Then {actor} should see 1 product in the cart
    And {actor} should see the first product in the cart

  @regression
  Scenario: Add several products to the cart
    Given {actor} is on the products page
    When {actor} adds the first product to the cart
    And {actor} adds the second product to the cart
    And {actor} adds the third product to the cart
    Then {actor} should see the cart badge showing 3

  @regression
  Scenario: Several products added all appear in the cart
    Given {actor} is on the products page
    When {actor} adds the first product to the cart
    And {actor} adds the second product to the cart
    And {actor} adds the third product to the cart
    And {actor} opens the cart
    Then {actor} should see 3 products in the cart
    And {actor} should see each product quantity as 1

  @regression
  Scenario: Remove a product from the cart
    Given {actor} is on the products page
    And {actor} adds the first product to the cart
    When {actor} removes the first product from the cart
    Then {actor} should not see the cart badge
    And {actor} should see the first product available again

  @regression
  Scenario: A product removed on the cart page disappears
    Given {actor} is on the products page
    And {actor} adds the first product to the cart
    When {actor} opens the cart
    And {actor} removes the first product on the cart page
    Then {actor} should see an empty cart

  @regression
  Scenario: The empty cart is shown correctly
    Given {actor} is on the products page
    When {actor} opens the cart
    Then {actor} should see an empty cart

  @regression
  Scenario: Continue shopping keeps the products in the cart
    Given {actor} is on the products page
    And {actor} adds the first product to the cart
    When {actor} opens the cart
    And {actor} continues shopping
    Then {actor} should be redirected to the products page
    When {actor} opens the cart
    Then {actor} should see 1 product in the cart

  @regression
  Scenario: The price of a product matches the one in the cart
    Given {actor} is on the products page
    And {actor} notes the price of the first product
    When {actor} adds the first product to the cart
    And {actor} opens the cart
    Then {actor} should see the same product price in the cart

  @security @critical
  Scenario: A product looks the same in the catalog, its details and the cart
    Given {actor} is on the products page
    And {actor} notes the name of the first product
    And {actor} notes the price of the first product
    When {actor} opens the details of the first product
    Then {actor} should see the same product name in the details
    And {actor} should see the same product price in the details
    When {actor} adds the first product to the cart
    And {actor} opens the cart
    Then {actor} should see the same product name in the cart
    And {actor} should see the same product price in the cart
