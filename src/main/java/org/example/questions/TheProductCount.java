package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.InventoryPage;

import java.util.List;

public class TheProductCount implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        // same idea as TheCartItemCount
        return Text.ofEach(InventoryPage.PRODUCT_NAMES).answeredBy(actor).size();
    }

    public static TheProductCount value() {
        return new TheProductCount();
    }
}
