package org.example.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage extends PageObject {
    public static final Target PRODUCTS_TITLE = Target.the("products title").locatedBy(".title");
    public static final Target OPEN_MENU_BUTTON = Target.the("open menu button").locatedBy("#react-burger-menu-btn");
    public static final Target LOGOUT_LINK = Target.the("logout link").locatedBy("#logout_sidebar_link");
    public static final Target PRODUCT_LIST = Target.the("product list").locatedBy(".inventory_list");
    public static final Target SORT_DROPDOWN = Target.the("sort dropdown").locatedBy(".product_sort_container");
    public static final Target PRODUCT_NAMES = Target.the("product names").locatedBy(".inventory_item_name");
    public static final Target PRODUCT_IMAGES = Target.the("product images").locatedBy(".inventory_item_img");
    public static final Target PRODUCT_DESCRIPTIONS = Target.the("product descriptions").locatedBy(".inventory_item_desc");
    public static final Target PRODUCT_PRICES = Target.the("product prices").locatedBy(".inventory_item_price");
    public static final Target FIRST_PRODUCT_NAME = Target.the("first product name").locatedBy(".inventory_item_name");
    public static final Target PRODUCT_DETAILS = Target.the("product details").locatedBy(".inventory_details");
    public static final Target PRODUCT_DETAIL_NAME = Target.the("product detail name").locatedBy(".inventory_details_name");
    public static final Target PRODUCT_DETAIL_PRICE = Target.the("product detail price").locatedBy(".inventory_details_price");
    public static final Target BACK_TO_PRODUCTS = Target.the("back to products button").locatedBy("#back-to-products");
    public static final Target CART_LINK = Target.the("cart link").locatedBy(".shopping_cart_link");
    public static final Target CART_BADGE = Target.the("cart badge").locatedBy(".shopping_cart_badge");
    public static final Target FIRST_PRODUCT_ADD_BUTTON = Target.the("first add to cart button").locatedBy("(//button[contains(@id,'add-to-cart')])[1]");
    public static final Target SECOND_PRODUCT_ADD_BUTTON = Target.the("second add to cart button").locatedBy("(//button[contains(@id,'add-to-cart')])[2]");
    public static final Target THIRD_PRODUCT_ADD_BUTTON = Target.the("third add to cart button").locatedBy("(//button[contains(@id,'add-to-cart')])[3]");
    public static final Target FIRST_PRODUCT_REMOVE_BUTTON = Target.the("first remove from cart button").locatedBy("(//button[contains(@id,'remove')])[1]");
}
