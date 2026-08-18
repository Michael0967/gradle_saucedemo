package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.CartPage;

import java.util.List;

public class TheCartPrices implements Question<List<Double>> {

    @Override
    public List<Double> answeredBy(Actor actor) {
        // prices come as "$9.99" from the page, drop the $
        return Text.ofEach(CartPage.CART_ITEM_PRICES).answeredBy(actor).stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    public static TheCartPrices value() {
        return new TheCartPrices();
    }
}
