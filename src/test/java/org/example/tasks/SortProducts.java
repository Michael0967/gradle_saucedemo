package org.example.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.example.navigation.InventoryPage;

public class SortProducts {
    public static Performable byVisibleText(String option) {
        return Task.where("{0} sorts the products by " + option,
                SelectFromOptions.byVisibleText(option).from(InventoryPage.SORT_DROPDOWN));
    }
}
