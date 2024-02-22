package starter.pageobjects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

@DefaultUrl("https://www.google.com/travel/explore")
public class ExploreDestinations extends PageObject {
    public List<String> getResultDestinations() {
        findBy("//div[contains(div/text(),'About these results')]/ol").waitUntilVisible();
        List<WebElementFacade> results = findEach("//div[contains(div/text(),'About these results')]/ol", "li").toList();
        return results.stream().map(DestinationItem::getDestination).toList();
    }
}
