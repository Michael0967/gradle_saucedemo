package org.example.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import org.example.navigation.CartPage;
import org.example.navigation.InventoryPage;
import org.example.questions.TheCartItemCount;
import org.example.questions.TheCartPrices;
import org.example.questions.TheCartSubtotal;
import org.example.questions.TheProductNames;
import org.example.questions.TheProductPrices;

import java.util.List;
import java.util.Locale;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CartStepDefinitions {

    @When("{actor} opens the cart")
    public void openTheCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.CART_LINK));
    }

    @When("{actor} removes the first product on the cart page")
    public void removeFirstProductOnTheCartPage(Actor actor) {
        actor.attemptsTo(Click.on(CartPage.FIRST_CART_ITEM_REMOVE_BUTTON));
    }

    @When("{actor} continues shopping")
    public void continueShopping(Actor actor) {
        actor.attemptsTo(Click.on(CartPage.CONTINUE_SHOPPING));
    }

    @When("{actor} notes the name of the first product")
    public void noteTheFirstProductName(Actor actor) {
        actor.remember("firstProductName", actor.asksFor(TheProductNames.value()).get(0));
    }

    @When("{actor} notes the price of the first product")
    public void noteTheFirstProductPrice(Actor actor) {
        actor.remember("firstProductPrice", actor.asksFor(TheProductPrices.value()).get(0));
    }

    @Then("{actor} should see {int} product(s) in the cart")
    public void theCartItemCountIs(Actor actor, int expectedCount) {
        actor.should(seeThat("cart items", TheCartItemCount.value(), equalTo(expectedCount)));
    }

    @Then("{actor} should see an empty cart")
    public void theCartIsEmpty(Actor actor) {
        actor.should(
                seeThat(Visibility.of(CartPage.CART_LIST), is(true)),
                seeThat("cart items", TheCartItemCount.value(), equalTo(0))
        );
    }

    @Then("{actor} should see the first product in the cart")
    public void theFirstProductAppearsInTheCart(Actor actor) {
        actor.should(seeThat("first cart item name", Text.of(CartPage.FIRST_CART_ITEM_NAME), is(not(emptyString()))));
    }

    @Then("{actor} should see each product quantity as 1")
    public void eachProductQuantityIsOne(Actor actor) {
        actor.should(seeThat("cart item quantities", Text.ofEach(CartPage.CART_ITEM_QUANTITY), everyItem(equalTo("1"))));
    }

    @Then("{actor} should see the same product name in the details")
    public void theDetailsShowTheSameProductName(Actor actor) {
        actor.should(seeThat("detail product name", Text.of(InventoryPage.PRODUCT_DETAIL_NAME),
                equalTo((String) actor.recall("firstProductName"))));
    }

    @Then("{actor} should see the same product price in the details")
    public void theDetailsShowTheSameProductPrice(Actor actor) {
        actor.should(seeThat("detail product price", Text.of(InventoryPage.PRODUCT_DETAIL_PRICE),
                equalTo(formatPrice((Double) actor.recall("firstProductPrice")))));
    }

    @Then("{actor} should see the same product name in the cart")
    public void theCartShowsTheSameProductName(Actor actor) {
        actor.should(seeThat("cart product name", Text.of(CartPage.FIRST_CART_ITEM_NAME),
                equalTo((String) actor.recall("firstProductName"))));
    }

    @Then("{actor} should see the same product price in the cart")
    public void theCartShowsTheSameProductPrice(Actor actor) {
        actor.should(seeThat("cart product price", Text.of(CartPage.FIRST_CART_ITEM_PRICE),
                equalTo(formatPrice((Double) actor.recall("firstProductPrice")))));
    }

    @Then("{actor} should see a subtotal equal to the sum of item prices")
    public void theSubtotalMatchesTheSumOfItemPrices(Actor actor) {
        List<Double> cartPrices = actor.asksFor(TheCartPrices.value());
        double expectedSubtotal = cartPrices.stream().mapToDouble(Double::doubleValue).sum();
        actor.should(seeThat("cart subtotal", TheCartSubtotal.value(), equalTo(expectedSubtotal)));
    }

    private static String formatPrice(double price) {
        return String.format(Locale.ROOT, "$%.2f", price);
    }
}
