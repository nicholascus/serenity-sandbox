package starter.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import org.openqa.selenium.Keys;
import starter.pageobjects.SearchForm;
import starter.pageobjects.SearchResults;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static starter.pageobjects.SearchResults.ResultType.BEST;
import static starter.pageobjects.SearchResults.ResultType.OTHERS;

public class SearchSteps extends UIInteractionSteps {

    SearchForm searchForm;
    SearchResults searchResults;

    @Step("User searches for flights from '{0}' to '{1}' in {2} days for a {3} days")
    public void searchForFlight(String origin, String destination, int leaveInDays, int stayForDays) {
        find(SearchForm.LOCATION_FROM_FIELD).click();
        find(SearchForm.LOCATION_FROM_INPUT).sendKeys(origin);
        find(SearchForm.LOCATION_FROM_INPUT).sendKeys(Keys.ENTER);
        find(SearchForm.LOCATION_TO_FIELD).click();
        find(SearchForm.LOCATION_TO_INPUT).sendKeys(destination);
        find(SearchForm.LOCATION_TO_INPUT).sendKeys(Keys.ENTER);

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, leaveInDays);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateInAWeek = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, stayForDays);
        String dateInTwoWeeks = dateFormat.format(calendar.getTime());

        find(SearchForm.DATE_DEPARTURE_INPUT).type(dateInAWeek);
        find(SearchForm.DATE_DEPARTURE_INPUT).sendKeys(Keys.ENTER);
        find(SearchForm.DATE_RETURN_INPUT).type(dateInTwoWeeks);
        find(SearchForm.DATE_RETURN_INPUT).sendKeys(Keys.ENTER);

        // Close the date picker
        find(SearchForm.DATE_RETURN_INPUT).sendKeys(Keys.ESCAPE);

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
}
