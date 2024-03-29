Feature: Search flights using the form

  Scenario: Searching for flights 'Sydney' to 'Melbourne'
    Given Sergey is on Google Flights home page
    When he searches for flights from "Sydney" to "Melbourne"
    Then he should see "Jetstar" airline in Best Results
    Then he should see "Jetstar, Qantas, Virgin Australia" airlines in Other Results

  Scenario: Exploring flights from 'Melbourne'
    Given Sergey is on Google Flights home page
    When he explores for flights from "Melbourne"
    Then he should see "Sydney, Auckland, Brisbane" destinations in Explore map
