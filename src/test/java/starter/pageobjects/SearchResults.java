package starter.pageobjects;

import net.serenitybdd.annotations.At;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@At("https://www.google.com/travel/flights/search")
public class SearchResults extends PageObject {
    public static final Target BEST_DEPARTURE_PANEL = Target.the("best departure panel").locatedBy("//h3[contains(text(), \"Best departing \")]/..");
    public static final Target OTHER_DEPARTURE_PANEL = Target.the("best departure panel").locatedBy("//h3[contains(text(), \"Other departing \")]/..");
    public static final String FLIGHT_RESULT_ITEM_XPATH = "descendant::ul/li";

    public enum ResultType {
        BEST(BEST_DEPARTURE_PANEL), OTHERS(OTHER_DEPARTURE_PANEL);
        private final Target target;
        ResultType(Target target) {
            this.target = target;
        }
        public Target getTarget() {
            return target;
        }
    }

    public List<String> getResultAirlines(@NotNull ResultType type) {
        find(type.getTarget()).waitUntilVisible();
        List<WebElementFacade> results = findEach(type.getTarget().getCssOrXPathSelector(), FLIGHT_RESULT_ITEM_XPATH).toList();
        return results.stream().map(SearchResultItem::getAirline).toList();
    }
}
