package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import org.example.navigation.OpenTheApplication;
import org.example.questions.TheCurrentUrl;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class SmokeStepDefinitions {

    @Given("opens the Sauce Demo application")
    public void opensTheApplication() {
        OnStage.theActorCalled("Michael").attemptsTo(OpenTheApplication.toHomePage());
    }

    @Then("the browser URL should be {string}")
    public void theBrowserUrlShouldBe(String expectedUrl) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(TheCurrentUrl.value(), equalTo(expectedUrl))
        );
    }
}
