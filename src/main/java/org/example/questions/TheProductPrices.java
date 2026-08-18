package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.InventoryPage;

import java.util.*;

public class TheProductPrices implements Question<List<Double>> {

    @Override
    public List<Double> answeredBy(Actor actor) {
        return Text.ofEach(InventoryPage.PRODUCT_PRICES).answeredBy(actor).stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    public static TheProductPrices value() {
        return new TheProductPrices();
    }
}
