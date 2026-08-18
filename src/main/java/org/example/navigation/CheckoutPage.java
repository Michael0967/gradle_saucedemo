package org.example.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage extends PageObject {
    public static final Target CHECKOUT_BTN = Target.the("checkout button").locatedBy("#checkout");
    public static final Target FIRST_NAME = Target.the("first name input").locatedBy("#first-name");
    public static final Target LAST_NAME = Target.the("last name input").locatedBy("#last-name");
    public static final Target POSTAL_CODE = Target.the("zip code input").locatedBy("#postal-code");
    public static final Target CONTINUE_BTN = Target.the("continue button").locatedBy("#continue");
    public static final Target FINISH_BTN = Target.the("finish button").locatedBy("#finish");
    public static final Target CANCEL_BTN = Target.the("cancel button").locatedBy("#cancel");
    public static final Target ERROR_MSG = Target.the("error message").locatedBy(".error-message-container h3");
    public static final Target ITEM_NAMES = Target.the("item names").locatedBy(".cart_item .inventory_item_name");
    public static final Target ITEM_PRICES = Target.the("item prices").locatedBy(".cart_item .inventory_item_price");
    public static final Target SUBTOTAL = Target.the("subtotal label").locatedBy(".summary_subtotal_label");
    public static final Target COMPLETE_HDR = Target.the("complete header").locatedBy(".complete-header");
    public static final Target BACK_HOME = Target.the("back home btn").locatedBy("#back-to-products");
}
