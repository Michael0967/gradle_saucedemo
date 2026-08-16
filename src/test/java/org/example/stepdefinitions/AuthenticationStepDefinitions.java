package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Browser;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.navigation.InventoryPage;
import org.example.navigation.OpenTheApplication;
import org.example.navigation.OpenTheInventoryPage;
import org.example.navigation.SauceDemoHomePage;
import org.example.questions.TheCurrentUrl;
import org.example.questions.TheDisplayedTitle;
import org.example.questions.TheLoginError;
import org.example.tasks.Login;
import org.example.tasks.Logout;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class AuthenticationStepDefinitions {

    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private static final String PRODUCTS_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @Given("{actor} is on the SauceDemo login page")
    public void openTheLoginPage(Actor actor) {
        actor.attemptsTo(OpenTheApplication.toHomePage());
    }

    @Given("{actor} is authenticated as {string}")
    public void logIn(Actor actor, String username) {
        actor.attemptsTo(Login.withCredentials(username, "secret_sauce"));
    }

    @Given("{actor} is on the products page")
    public void checkWereOnTheProductsPage(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(PRODUCTS_PAGE_URL)));
    }

    @When("{actor} enters the username {string}")
    public void typeTheUsername(Actor actor, String username) {
        actor.attemptsTo(Enter.theValue(username).into(SauceDemoHomePage.USERNAME_FIELD));
    }

    @When("{actor} enters the password {string}")
    public void typeThePassword(Actor actor, String password) {
        actor.attemptsTo(Enter.theValue(password).into(SauceDemoHomePage.PASSWORD_FIELD));
    }

    @When("{actor} presses the Login button")
    public void submitLogin(Actor actor) {
        actor.attemptsTo(Click.on(SauceDemoHomePage.LOGIN_BUTTON));
    }

    @When("{actor} tries to access the products page directly")
    public void bypassLogin(Actor actor) {
        actor.attemptsTo(OpenTheInventoryPage.directly());
    }

    @When("{actor} logs out")
    public void logOut(Actor actor) {
        actor.attemptsTo(Logout.fromTheApplication());
    }

    @When("{actor} goes back to the previous page")
    public void goBack(Actor actor) {
        actor.attemptsTo(Browser.navigateBack());
    }

    @Then("{actor} should be redirected to the products page")
    public void theProductsPageIsShown(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(PRODUCTS_PAGE_URL)));
    }

    @Then("{actor} should be redirected to the login page")
    public void theLoginPageIsShown(Actor actor) {
        // the redirect takes longer when scenarios run in parallel
        actor.attemptsTo(
                WaitUntil.the(SauceDemoHomePage.USERNAME_FIELD, isCurrentlyVisible()).forNoMoreThan(20).seconds()
        );
        actor.should(seeThat(TheCurrentUrl.value(), equalTo(LOGIN_PAGE_URL)));
    }

    @Then("{actor} should see the {string} title")
    public void theTitleIsVisible(Actor actor, String expectedTitle) {
        actor.should(seeThat(TheDisplayedTitle.value(), equalTo(expectedTitle)));
    }

    @Then("{actor} should see an authentication error message")
    public void anErrorIsDisplayed(Actor actor) {
        actor.should(seeThat(Visibility.of(SauceDemoHomePage.ERROR_MESSAGE), is(true)));
    }

    @Then("{actor} should not access the products page")
    public void accessToProductsIsDenied(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), not(equalTo(PRODUCTS_PAGE_URL))));
    }

    @Then("{actor} should not see the inventory")
    public void theInventoryIsNotVisible(Actor actor) {
        actor.should(seeThat(Visibility.of(InventoryPage.PRODUCTS_TITLE), is(false)));
    }

    @Then("{actor} should see a message indicating that the username is required")
    public void usernameRequiredMessageIsShown(Actor actor) {
        actor.should(seeThat(TheLoginError.message(), containsString("Username is required")));
    }

    @Then("{actor} should see a message indicating that the password is required")
    public void passwordRequiredMessageIsShown(Actor actor) {
        actor.should(seeThat(TheLoginError.message(), containsString("Password is required")));
    }

    @Then("{actor} should see a message indicating that the user is locked out")
    public void lockedOutMessageIsShown(Actor actor) {
        actor.should(seeThat(TheLoginError.message(), containsStringIgnoringCase("locked out")));
    }

    @Then("{actor} should not be able to access the inventory without authentication")
    public void inventoryIsOffLimits(Actor actor) {
        actor.should(seeThat(TheCurrentUrl.value(), not(equalTo(PRODUCTS_PAGE_URL))));
    }
}
