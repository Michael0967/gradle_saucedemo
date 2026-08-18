@product-filtering
Feature: Product Filtering

  As a SauceDemo user
  I want to sort the product catalog
  So that I can find products more easily

  Background:
    Given {actor} is signed in as "standard_user"

  @smoke @critical
  Scenario: Sort products by name from A to Z
    Given {actor} is on the products page
    When {actor} sorts the products by "Name (A to Z)"
    Then {actor} should see the products sorted by name ascending

  @regression
  Scenario: Sort products by name from Z to A
    Given {actor} is on the products page
    When {actor} sorts the products by "Name (Z to A)"
    Then {actor} should see the products sorted by name descending

  @regression
  Scenario: Sort products by price from low to high
    Given {actor} is on the products page
    When {actor} sorts the products by "Price (low to high)"
    Then {actor} should see the products sorted by price ascending

  @regression @critical
  Scenario: Sort products by price from high to low
    Given {actor} is on the products page
    When {actor} sorts the products by "Price (high to low)"
    Then {actor} should see the products sorted by price descending
