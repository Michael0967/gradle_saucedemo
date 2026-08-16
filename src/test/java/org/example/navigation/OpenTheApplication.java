package org.example.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenTheApplication {
    public static Performable toHomePage() {
        return Task.where("{0} opens the Sauce Demo home page",
                Open.browserOn().the(SauceDemoHomePage.class));
    }
}
