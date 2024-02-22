package starter.pageobjects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@DefaultUrl("https://www.google.com/travel/flights")
public class SearchForm extends PageObject {
    public static final Target LOCATION_FROM_FIELD = Target.the("search from field").locatedBy("//div[contains(@aria-label, \"Enter your origin\")]/..");
    public static final Target LOCATION_FROM_INPUT = Target.the("search from field").locatedBy("//div[contains(@aria-label, \"Enter your origin\")]/descendant::input");
    public static final Target LOCATION_TO_FIELD = Target.the("search to input").locatedBy("//div[contains(@aria-label, \"Enter your destination\")]/..");
    public static final Target LOCATION_TO_INPUT = Target.the("search to input").locatedBy("//div[contains(@aria-label, \"Enter your destination\")]/descendant::input");
    public static final Target DATE_DEPARTURE_INPUT = Target.the("Departure Date").locatedBy("//input[@placeholder='Departure']");
    public static final Target DATE_RETURN_INPUT = Target.the("Return Date").locatedBy("//input[@placeholder='Return']");
    public static final Target DATE_PICKER_DONE = Target.the("Done button for datepicker").locatedBy("//div[contains(text(),'Showing prices in')]/../../descendant::button[descendant::span[text()='Done']]");
    public static final Target SEARCH_BUTTON = Target.the("Search Button").locatedBy("//button[contains(@aria-label, 'Search') and not(@tabindex)]");

    public static final Target EXPLORE_BUTTON = Target.the("Explore Button").locatedBy("//button[contains(@aria-label, 'Explore') and not(@tabindex)]");

    private void selectLocation(Target locationFromField, Target locationFromInput, String origin, String locationLabel) {
        find(locationFromField).click();
        findBy("//ul[@role='listbox' and ancestor::div[@aria-label='"+ locationLabel +"']]/li[1]").waitUntilVisible();
        find(locationFromInput).sendKeys(origin);
        findBy("//ul[@role='listbox' and ancestor::div[@aria-label='"+ locationLabel +"']]/li[descendant::div[text()='"+ origin +"']]").waitUntilVisible();
        findBy("//ul[@role='listbox' and ancestor::div[@aria-label='"+ locationLabel +"']]/li[descendant::div[text()='"+ origin +"']]").click();
    }

    public void selectFromLocation(String origin) {
        selectLocation(SearchForm.LOCATION_FROM_FIELD, SearchForm.LOCATION_FROM_INPUT, origin, "Enter your origin");
    }

    public void selectToLocation(String destination) {
        selectLocation(SearchForm.LOCATION_TO_FIELD, SearchForm.LOCATION_TO_INPUT, destination, "Enter your destination");
    }

    public void selectDates(String dateInAWeek, String dateInTwoWeeks) {
        find(SearchForm.DATE_DEPARTURE_INPUT).type(dateInAWeek);
        find(SearchForm.DATE_DEPARTURE_INPUT).sendKeys(Keys.ENTER);
        find(SearchForm.DATE_RETURN_INPUT).type(dateInTwoWeeks);
        find(SearchForm.DATE_RETURN_INPUT).sendKeys(Keys.ENTER);

        // Close the date picker
        find(SearchForm.DATE_RETURN_INPUT).sendKeys(Keys.ESCAPE);
        if (isElementVisible(By.xpath(SearchForm.DATE_PICKER_DONE.getCssOrXPathSelector()))) {
            find(SearchForm.DATE_PICKER_DONE).click();
            waitForRenderedElementsToDisappear(By.xpath(SearchForm.DATE_PICKER_DONE.getCssOrXPathSelector()));
        }
    }
}
