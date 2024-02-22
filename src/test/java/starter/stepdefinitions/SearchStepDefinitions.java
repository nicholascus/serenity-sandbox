package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.actions.NavigateSteps;
import starter.actions.SearchSteps;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchStepDefinitions {

    @Steps
    NavigateSteps navigate;

    @Steps
    SearchSteps search;

    @Given("^(?:.*) is on Google Flights home page")
    public void researchingThings() {
        navigate.opensTheHomePage();
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

}
