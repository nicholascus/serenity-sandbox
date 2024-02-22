package starter.pageobjects;

import net.serenitybdd.core.pages.WebElementFacade;

public class DestinationItem {
    public static String getDestination(WebElementFacade element) {
        try {
            return element.findBy("descendant::h3").getText();
        } catch (Exception e) {
            return null;
        }
    }
}
