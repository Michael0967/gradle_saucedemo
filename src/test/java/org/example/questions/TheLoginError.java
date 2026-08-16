package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.example.navigation.SauceDemoHomePage;

public class TheLoginError implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(SauceDemoHomePage.ERROR_MESSAGE).answeredBy(actor);
    }

    public static TheLoginError message() {
        return new TheLoginError();
    }
}
