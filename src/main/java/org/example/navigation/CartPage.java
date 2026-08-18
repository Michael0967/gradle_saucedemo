package org.example.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CartPage extends PageObject {
    public static final Target CART_LIST = Target.the("cart list").locatedBy(".cart_list");
    public static final Target CART_ITEM_NAMES = Target.the("cart item names").locatedBy(".cart_item .inventory_item_name");
    public static final Target CART_ITEM_QUANTITY = Target.the("cart item quantities").locatedBy(".cart_item .item_quantity");
    public static final Target CART_ITEM_PRICES = Target.the("cart item prices").locatedBy(".cart_item .inventory_item_price");
    public static final Target FIRST_CART_ITEM_NAME = Target.the("first cart item name").locatedBy(".cart_item .inventory_item_name");
    public static final Target FIRST_CART_ITEM_PRICE = Target.the("first cart item price").locatedBy(".cart_item .inventory_item_price");
    public static final Target FIRST_CART_ITEM_REMOVE_BUTTON = Target.the("first remove button in the cart").locatedBy("(//button[contains(@class,'cart_button')])[1]");
    public static final Target CONTINUE_SHOPPING = Target.the("continue shopping button").locatedBy("#continue-shopping");
}
