package org.example.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.navigation.InventoryPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class Logout {
    public static Performable fromTheApplication() {
        return Task.where("{0} logs out of the application",
                // React silently swallows a plain Selenium click on the menu toggle
                JavaScriptClick.on(InventoryPage.OPEN_MENU_BUTTON),
                WaitUntil.the(InventoryPage.LOGOUT_LINK, isClickable()).forNoMoreThan(10).seconds(),
                JavaScriptClick.on(InventoryPage.LOGOUT_LINK));
    }
}
