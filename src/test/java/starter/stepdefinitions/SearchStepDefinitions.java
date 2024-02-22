package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.webdriver.DevToolsWebDriverFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v116.emulation.Emulation;
import org.openqa.selenium.remote.RemoteWebDriver;
import starter.actions.NavigateSteps;
import starter.actions.SearchSteps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchStepDefinitions {

    @Steps
    NavigateSteps navigate;

    @Steps
    SearchSteps search;

    @Given("^(?:.*) is on Google Flights home page")
    public void navigateToGoogleFlightPage() {
        navigate.opensTheHomePage();
    }

    @Given("^(?:.*) resides in \"(.*)\"")
    public void setBrowserGeoLocation(String city) {
        WebDriver driver = navigate.getDriver();

        DevTools devTools = ((DevToolsWebDriverFacade) driver).maybeGetDevTools().get();
        devTools.createSession();
        devTools.send(
                new Command<>(
                        "Emulation.setGeolocationOverride",
                        Map.of(
                        "latitude", 37.8136,
                        "longitude", 144.9631,
                        "accuracy", 100
                        )
                )
        );
    }

    @When("^(?:.*) searches for flights from \"(.*)\" to \"(.*)\"")
    public void searchesForAWeekTripInAWeek(String origin, String destination) {
        search.searchForFlight(origin, destination, 7, 7);
    }

    @Then("^(?:.*) should see \"(.*)\" airline in Best Results")
    public void shouldSeeInBestResults(String airline) {
        assertThat(search.getBestDepartingResultFlights()).anyMatch(title -> title.equals(airline));
    }

    @Then("^(?:.*) should see \"(.*)\" airlines in Other Results")
    public void shouldSeeInOtherResults(String airlines) {
        List<String> expectedAirlinesList = Arrays.stream(airlines.split(",")).map(String::trim).toList();
        List<String> foundAirlinesList = search.getOtherDepartingResultFlights();
        assertThat(expectedAirlinesList).allMatch(foundAirlinesList::contains);
    }

    @When("^(?:.*) explores for flights from \"(.*)\"")
    public void exploreFlights(String origin) {
        search.exploreFlights(origin);
    }

    @Then("^(?:.*) should see \"(.*)\" destinations in Explore map")
    public void shouldSeeDestinations(String destinations) {
        List<String> expectedDestinationsList = Arrays.stream(destinations.split(",")).map(String::trim).toList();
        List<String> foundDestinationsList = search.getExploreMapDestinations();
        assertThat(expectedDestinationsList).allMatch(foundDestinationsList::contains);
    }
}
