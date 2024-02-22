package starter.pageobjects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

@DefaultUrl("https://www.google.com/travel/flights?.*")
public class SearchForm extends PageObject {
    public static final Target LOCATION_FROM_FIELD = Target.the("search from field").locatedBy("//div[contains(@aria-label, \"Enter your origin\")]/..");
    public static final Target LOCATION_FROM_INPUT = Target.the("search from field").locatedBy("//div[contains(@aria-label, \"Enter your origin\")]/descendant::input");
    public static final Target LOCATION_TO_FIELD = Target.the("search to input").locatedBy("//div[contains(@aria-label, \"Enter your destination\")]/..");
    public static final Target LOCATION_TO_INPUT = Target.the("search to input").locatedBy("//div[contains(@aria-label, \"Enter your destination\")]/descendant::input");
    public static final Target DATE_DEPARTURE_INPUT = Target.the("Departure Date").locatedBy("//input[@placeholder='Departure']");
    public static final Target DATE_RETURN_INPUT = Target.the("Return Date").locatedBy("//input[@placeholder='Return']");
    public static final Target DATE_PICKER_DONE = Target.the("Done button for datepicker").locatedBy("//div[contains(text(),'Showing prices in')]/../../descendant::button[descendant::span[text()='Done']]");
    public static final Target SEARCH_BUTTON = Target.the("Search Button").locatedBy("//button[contains(@aria-label, 'Search') and not(@tabindex)]");
}