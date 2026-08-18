package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.InventoryPage;

public class TheDisplayedTitle implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(InventoryPage.PRODUCTS_TITLE).answeredBy(actor);
    }

    public static TheDisplayedTitle value() {
        return new TheDisplayedTitle();
    }
}
