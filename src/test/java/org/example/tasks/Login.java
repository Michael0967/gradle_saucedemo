package org.example.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.navigation.InventoryPage;
import org.example.navigation.SauceDemoHomePage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

public class Login {
    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} logs in as " + username,
                Enter.theValue(username).into(SauceDemoHomePage.USERNAME_FIELD),
                Enter.theValue(password).into(SauceDemoHomePage.PASSWORD_FIELD),
                Click.on(SauceDemoHomePage.LOGIN_BUTTON),
                // the following step used to race the navigation, wait for the inventory title first
                WaitUntil.the(InventoryPage.PRODUCTS_TITLE, isCurrentlyVisible()).forNoMoreThan(10).seconds());
    }
}
