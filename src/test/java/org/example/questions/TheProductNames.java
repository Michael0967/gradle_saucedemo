package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.InventoryPage;

import java.util.List;

public class TheProductNames implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return List.copyOf(Text.ofEach(InventoryPage.PRODUCT_NAMES).answeredBy(actor));
    }

    public static TheProductNames value() {
        return new TheProductNames();
    }
}
