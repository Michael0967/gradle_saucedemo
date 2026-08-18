package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.CheckoutPage;

import java.util.Locale;

public class TheCartSubtotal implements Question<Double> {

    @Override
    public Double answeredBy(Actor actor) {
        String text = Text.of(CheckoutPage.SUBTOTAL).answeredBy(actor);
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public static TheCartSubtotal value() {
        return new TheCartSubtotal();
    }

    public static String formattedAs(double amount) {
        return String.format(Locale.ROOT, "Item total: $%.2f", amount);
    }
}
