package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;

public class SearchResultItem {
    public static String getAirline(WebElementFacade element) {
        try {
            return element.findBy("descendant::*[starts-with(@aria-label, 'Leaves')]/../../div[2]/span").getText();
        } catch (Exception e) {
            return null;
        }
    }
}
