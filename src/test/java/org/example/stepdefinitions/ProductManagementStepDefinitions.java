package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import org.example.navigation.InventoryPage;
import org.example.questions.TheProductCount;
import org.example.questions.TheProductDescriptions;
import org.example.questions.TheProductNames;
import org.example.questions.TheProductPrices;
import org.example.tasks.LoginAs;
import org.example.tasks.SortProducts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ProductManagementStepDefinitions {

    @Given("{actor} is signed in as {string}")
    public void signIn(Actor actor, String username) {
        actor.attemptsTo(LoginAs.theUser(username));
    }

    @When("{actor} adds the first product to the cart")
    public void addFirstProductToTheCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.FIRST_PRODUCT_ADD_BUTTON));
    }

    @When("{actor} adds the second product to the cart")
    public void addSecondProductToTheCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.SECOND_PRODUCT_ADD_BUTTON));
    }

    @When("{actor} adds the third product to the cart")
    public void addThirdProductToTheCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.THIRD_PRODUCT_ADD_BUTTON));
    }

    @When("{actor} removes the first product from the cart")
    public void removeFirstProductFromTheCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.FIRST_PRODUCT_REMOVE_BUTTON));
    }

    @Then("{actor} should see the cart badge showing {int}")
    public void theCartBadgeShows(Actor actor, int expectedCount) {
        actor.should(seeThat("cart badge", Text.of(InventoryPage.CART_BADGE), equalTo(String.valueOf(expectedCount))));
    }

    @Then("{actor} should not see the cart badge")
    public void noCartBadgeIsShown(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.CART_BADGE), is(false)));
    }

    @Then("{actor} should see the first product marked as added")
    public void theFirstProductShowsRemove(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.FIRST_PRODUCT_REMOVE_BUTTON), is(true)));
    }

    @Then("{actor} should see the first product available again")
    public void theFirstProductShowsAddToCart(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.FIRST_PRODUCT_ADD_BUTTON), is(true)));
    }

    @When("{actor} sorts the products by {string}")
    public void sortTheProducts(Actor actor, String option) {
        actor.attemptsTo(SortProducts.byVisibleText(option));
    }

    @Then("{actor} should see the products sorted by name ascending")
    public void productsAreSortedByNameAscending(Actor actor) {
        List<String> names = actor.asksFor(TheProductNames.value());
        List<String> expected = new ArrayList<>(names);
        expected.sort(Comparator.naturalOrder());
        actor.should(seeThat("product names", TheProductNames.value(), equalTo(expected)));
    }

    @Then("{actor} should see the products sorted by name descending")
    public void productsAreSortedByNameDescending(Actor actor) {
        List<String> names = actor.asksFor(TheProductNames.value());
        List<String> expected = new ArrayList<>(names);
        expected.sort(Comparator.reverseOrder());
        actor.should(seeThat("product names", TheProductNames.value(), equalTo(expected)));
    }

    @Then("{actor} should see the products sorted by price ascending")
    public void productsAreSortedByPriceAscending(Actor actor) {
        List<Double> prices = actor.asksFor(TheProductPrices.value());
        List<Double> expected = new ArrayList<>(prices);
        expected.sort(Comparator.naturalOrder());
        actor.should(seeThat("product prices", TheProductPrices.value(), equalTo(expected)));
    }

    @Then("{actor} should see the products sorted by price descending")
    public void productsAreSortedByPriceDescending(Actor actor) {
        List<Double> prices = actor.asksFor(TheProductPrices.value());
        List<Double> expected = new ArrayList<>(prices);
        expected.sort(Comparator.reverseOrder());
        actor.should(seeThat("product prices", TheProductPrices.value(), equalTo(expected)));
    }

    @When("{actor} opens the details of the first product")
    public void openTheFirstProduct(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.FIRST_PRODUCT_NAME));
    }

    @When("{actor} goes back to the products page")
    public void goBackToTheProductsPage(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.BACK_TO_PRODUCTS));
    }

    @Then("{actor} should see the product list")
    public void theProductListIsShown(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.PRODUCT_LIST), is(true)));
    }

    @Then("{actor} should see {int} products")
    public void theProductCountIs(Actor actor, int expectedCount) {
        actor.should(seeThat("product count", TheProductCount.value(), equalTo(expectedCount)));
    }

    @Then("{actor} should see the name of each product")
    public void allProductsHaveAName(Actor actor) {
        actor.should(
                seeThat("product names", TheProductNames.value(), hasSize(6)),
                seeThat("product names are not empty", TheProductNames.value(), everyItem(is(not(emptyString()))))
        );
    }

    @Then("{actor} should see the image of each product")
    public void allProductsHaveAnImage(Actor actor) {
        actor.should(seeThat("product images", Visibility.ofEach(InventoryPage.PRODUCT_IMAGES), everyItem(is(true))));
    }

    @Then("{actor} should see the description of each product")
    public void allProductsHaveADescription(Actor actor) {
        actor.should(
                seeThat("product descriptions", TheProductDescriptions.value(), hasSize(6)),
                seeThat("product descriptions are not empty", TheProductDescriptions.value(), everyItem(is(not(emptyString()))))
        );
    }

    @Then("{actor} should see the price of each product")
    public void allProductsHaveAPrice(Actor actor) {
        actor.should(
                seeThat("product prices", TheProductPrices.value(), hasSize(6)),
                seeThat("product prices are positive", TheProductPrices.value(), everyItem(greaterThan(0.0)))
        );
    }

    @Then("{actor} should see the product details")
    public void theProductDetailsAreShown(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.PRODUCT_DETAILS), is(true)));
    }
}
