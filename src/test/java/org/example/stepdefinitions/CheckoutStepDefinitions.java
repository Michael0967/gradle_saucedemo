package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.CheckoutPage;
import org.example.navigation.InventoryPage;
import org.example.questions.TheCurrentUrl;
import org.example.questions.TheProductPrices;

import java.util.Locale;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class CheckoutStepDefinitions {

    private static final String INFO_URL = "https://www.saucedemo.com/checkout-step-one.html";
    private static final String OVERVIEW_URL = "https://www.saucedemo.com/checkout-step-two.html";
    private static final String COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";
    private static final String CART_URL = "https://www.saucedemo.com/cart.html";

    @Given("{actor} has a product in the cart")
    public void productInCart(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.FIRST_PRODUCT_ADD_BUTTON));
        actor.attemptsTo(Click.on(InventoryPage.CART_LINK));
    }

    @Given("{actor} is on the checkout information page")
    public void onCheckoutInfo(Actor actor) {
        actor.attemptsTo(Click.on(InventoryPage.CART_LINK));
        actor.attemptsTo(Click.on(CheckoutPage.CHECKOUT_BTN));
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(INFO_URL)));
    }

    @Given("{actor} completes the checkout information with valid data")
    public void fillValidData(Actor actor) {
        // goes through the full checkout info flow with valid data
        actor.attemptsTo(Click.on(InventoryPage.CART_LINK));
        actor.attemptsTo(Click.on(CheckoutPage.CHECKOUT_BTN));
        fillFields(actor, "Michael", "Rojas", "110141");
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(OVERVIEW_URL)));
    }

    @Given("{actor} fills the checkout information with {string} {string} {string}")
    public void fillCheckout(Actor actor, String fn, String ln, String zip) {
        fillFields(actor, fn, ln, zip);
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(OVERVIEW_URL)));
    }

    private void fillFields(Actor actor, String fn, String ln, String zip) {
        actor.attemptsTo(Enter.theValue(fn).into(CheckoutPage.FIRST_NAME));
        actor.attemptsTo(Enter.theValue(ln).into(CheckoutPage.LAST_NAME));
        actor.attemptsTo(Enter.theValue(zip).into(CheckoutPage.POSTAL_CODE));
        actor.attemptsTo(Click.on(CheckoutPage.CONTINUE_BTN));
    }

    @When("{actor} proceeds to checkout")
    public void goToCheckout(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.CHECKOUT_BTN));
    }

    @When("{actor} enters the first name {string}")
    public void typeName(Actor actor, String fn) {
        actor.attemptsTo(Enter.theValue(fn).into(CheckoutPage.FIRST_NAME));
    }

    @When("{actor} enters the last name {string}")
    public void typeLastName(Actor actor, String ln) {
        actor.attemptsTo(Enter.theValue(ln).into(CheckoutPage.LAST_NAME));
    }

    @When("{actor} enters the postal code {string}")
    public void typeZip(Actor actor, String zip) {
        actor.attemptsTo(Enter.theValue(zip).into(CheckoutPage.POSTAL_CODE));
    }

    @When("{actor} continues to the next step")
    public void clickContinue(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.CONTINUE_BTN));
    }

    @When("{actor} finishes the order")
    public void finishOrder(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.FINISH_BTN));
    }

    @When("{actor} cancels the checkout")
    public void cancelFromInfo(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.CANCEL_BTN));
    }

    @When("{actor} cancels from the checkout overview")
    public void cancelFromOverview(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.CANCEL_BTN));
    }

    @When("{actor} tries to open the checkout page directly")
    public void openDirectly(Actor actor) {
        // plant the cookie directly like LoginAs does
        String base = ConfiguredEnvironment.getConfiguration().getBaseUrl();
        actor.attemptsTo(Open.url(base + "checkout-step-one.html"));
    }

    @When("{actor} clicks the Back Home button")
    public void goBackHome(Actor actor) {
        actor.attemptsTo(Click.on(CheckoutPage.BACK_HOME));
    }

    @Then("{actor} should be on the checkout information page")
    public void assertInfoUrl(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(INFO_URL)));
    }

    @Then("{actor} should be on the checkout overview page")
    public void assertOverviewUrl(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(OVERVIEW_URL)));
    }

    @Then("{actor} should see the order confirmation page")
    public void assertCompleteUrl(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(COMPLETE_URL)));
    }

    @Then("{actor} should see a thank you message")
    public void assertThankYou(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.COMPLETE_HDR),
                containsString("Thank you for your order!")));
    }

    @Then("{actor} should be back on the cart page")
    public void assertCartUrl(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(CART_URL)));
    }

    @Then("{actor} should see a checkout error for missing first name")
    public void assertFirstNameErr(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.ERROR_MSG),
                containsString("First Name is required")));
    }

    @Then("{actor} should see a checkout error for missing last name")
    public void assertLastNameErr(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.ERROR_MSG),
                containsString("Last Name is required")));
    }

    @Then("{actor} should see a checkout error for missing postal code")
    public void assertPostalErr(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.ERROR_MSG),
                containsString("Postal Code is required")));
    }

    @Then("{actor} should see the same product name in the checkout overview")
    public void assertProductName(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.ITEM_NAMES),
                equalTo((String) actor.recall("firstProductName"))));
    }

    @Then("{actor} should see the same product price in the checkout overview")
    public void assertProductPrice(Actor actor) {
        actor.should(seeThat(Text.of(CheckoutPage.ITEM_PRICES),
                equalTo(formatPrice((Double) actor.recall("firstProductPrice")))));
    }

    private static String formatPrice(double price) {
        return String.format(Locale.ROOT, "$%.2f", price);
    }
}
