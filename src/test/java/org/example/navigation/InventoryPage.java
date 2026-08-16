package org.example.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage extends PageObject {
    public static final Target PRODUCTS_TITLE = Target.the("products title").locatedBy(".title");
    public static final Target OPEN_MENU_BUTTON = Target.the("open menu button").locatedBy("#react-burger-menu-btn");
    public static final Target LOGOUT_LINK = Target.the("logout link").locatedBy("#logout_sidebar_link");
}
