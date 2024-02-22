package starter.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pageobjects.SearchForm;

public class NavigateSteps extends UIInteractionSteps {

    SearchForm searchForm;

    @Step("User opens the Google Flights home page")
    public void opensTheHomePage() {
        searchForm.open();
    }
}
