package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.CartPage;

public class TheCartItemCount implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        // counting the name elements is easier than finding a counter element
        return Text.ofEach(CartPage.CART_ITEM_NAMES).answeredBy(actor).size();
    }

    public static TheCartItemCount value() {
        return new TheCartItemCount();
    }
}
