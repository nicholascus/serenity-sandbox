Feature: Search flights using the form

  Scenario: Searching for flights 'Sydney' to 'Melbourne'
    Given Sergey is on Google Flights home page
    When he searches for flights from "Sydney" to "Melbourne"
    Then he should see "Jetstar" airline in Best Results
    Then he should see "Jetstar, Qantas, Virgin Australia, S3" airlines in Other Results

