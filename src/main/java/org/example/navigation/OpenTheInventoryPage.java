package org.example.navigation;

import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenTheInventoryPage {
    public static Performable directly() {
        return Task.where("{0} tries to open the inventory page directly",
                Open.url(ConfiguredEnvironment.getConfiguration().getBaseUrl() + "inventory.html"));
    }
}
