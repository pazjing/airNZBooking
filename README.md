# airNZBooking
Automation test example for booking flight.

# How to run
Clone the repository:
```git clone https://github.com/pazjing/airNZBooking.git```

Execute the test: 
```mvn clean verify``` 

Get the HTML test report under ```project_Location/target/cucumber-html-reports```


# Scenarios included
BDD feature description 
```Feature: To test the book flight ticket function of Air New Zealand

  @flight
  Scenario: Browser from Home page to search the return trip flights
    Given I browser to the website "https://www.airnewzealand.co.nz/"
    When I click "Book"
    Then I should see the "Book" page
    And  I click "Book now"
    Then A "Book a Flight" new page should be open

    When I fill value "Queenstown" in the "depart-to" form
    And Select day "2" of next month in the "leaveDate" form
    And Select day "20" of next month in the "returnDate" form
    And I click Search button
    Then I should see the "Select your flights" page
    And The page content contains
      | Select your flight to         | Queenstown   	|
      | Select your return flight to  | Auckland	    |
      ```
      
      
