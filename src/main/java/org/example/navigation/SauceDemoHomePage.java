package org.example.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class SauceDemoHomePage extends PageObject {
    public static final Target USERNAME_FIELD = Target.the("username field").locatedBy("#user-name");
    public static final Target PASSWORD_FIELD = Target.the("password field").locatedBy("#password");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("#login-button");
    public static final Target ERROR_MESSAGE = Target.the("login error message").locatedBy(".error-message-container h3");
}
