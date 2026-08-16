package org.example.tasks;

import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Evaluate;
import net.serenitybdd.screenplay.actions.Open;

public class LoginAs {
    public static Performable theUser(String username) {
        String baseUrl = ConfiguredEnvironment.getConfiguration().getBaseUrl();
        return Task.where("{0} signs in as " + username,
                Open.url(baseUrl),
                // the site checks a session-username cookie that login sets, so we plant it directly
                Evaluate.javascript("document.cookie = 'session-username=" + username + "; path=/';"),
                Open.url(baseUrl + "inventory.html"));
    }
}
