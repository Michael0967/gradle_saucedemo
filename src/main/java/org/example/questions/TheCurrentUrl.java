package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class TheCurrentUrl implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        // grab current URL directly from the driver
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
    }

    public static TheCurrentUrl value() {
        return new TheCurrentUrl();
    }
}
