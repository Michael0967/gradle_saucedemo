@product-management
Feature: Product Management

  As a SauceDemo user
  I want to browse and sort the product catalog
  So that I can find and pick the products I need

  Background:
    Given {actor} is signed in as "standard_user"

  @smoke @critical
  Scenario: View the product list
    Given {actor} is on the products page
    Then {actor} should see the product list
    And {actor} should see 6 products

  @regression
  Scenario: Products show their name, image, description and price
    Given {actor} is on the products page
    Then {actor} should see the name of each product
    And {actor} should see the image of each product
    And {actor} should see the description of each product
    And {actor} should see the price of each product

  @regression
  Scenario: Sort products by name from A to Z
    When {actor} sorts the products by "Name (A to Z)"
    Then {actor} should see the products sorted by name ascending

  @regression
  Scenario: Sort products by name from Z to A
    When {actor} sorts the products by "Name (Z to A)"
    Then {actor} should see the products sorted by name descending

  @regression
  Scenario: Sort products by price from low to high
    When {actor} sorts the products by "Price (low to high)"
    Then {actor} should see the products sorted by price ascending

  @regression
  Scenario: Sort products by price from high to low
    When {actor} sorts the products by "Price (high to low)"
    Then {actor} should see the products sorted by price descending

  @regression
  Scenario: View the details of a product
    Given {actor} is on the products page
    When {actor} opens the details of the first product
    Then {actor} should see the product details

  @regression
  Scenario: Go back from the product details to the catalog
    Given {actor} is on the products page
    When {actor} opens the details of the first product
    And {actor} goes back to the products page
    Then {actor} should see the product list
