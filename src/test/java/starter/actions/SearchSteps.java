package starter.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pageobjects.ExploreDestinations;
import starter.pageobjects.SearchForm;
import starter.pageobjects.SearchResults;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static starter.pageobjects.SearchResults.ResultType.BEST;
import static starter.pageobjects.SearchResults.ResultType.OTHERS;

public class SearchSteps extends UIInteractionSteps {

    SearchForm searchForm;
    SearchResults searchResults;

    ExploreDestinations exploreDestinations;

    @Step("User searches for flights from '{0}' to '{1}' in {2} days for a {3} days")
    public void searchForFlight(String origin, String destination, int leaveInDays, int stayForDays) {
        searchForm.selectFromLocation(origin);
        searchForm.selectToLocation(destination);

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, leaveInDays);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateInAWeek = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, stayForDays);
        String dateInTwoWeeks = dateFormat.format(calendar.getTime());

        searchForm.selectDates(dateInAWeek, dateInTwoWeeks);

        find(SearchForm.SEARCH_BUTTON).click();
        find(BEST.getTarget()).withTimeoutOf(Duration.ofSeconds(100)).waitUntilVisible();
    }

    @Step("Check best options results")
    public List<String> getBestDepartingResultFlights() {
        return searchResults.getResultAirlines(BEST);
    }

    @Step("Check other results")
    public List<String> getOtherDepartingResultFlights() {
        return searchResults.getResultAirlines(OTHERS);
    }

    @Step("Get suggested destinations for '{0}'")
    public List<String> getExploreMapDestinations() {
        return exploreDestinations.getResultDestinations();
    }

    @Step("Searches for for possible destinations for '{0}'")
    public void exploreFlights(String origin) {
        //TODO(Niko): for some reason origin selection here is flaky
        searchForm.selectFromLocation(origin);
        find(SearchForm.EXPLORE_BUTTON).click();
    }
}
