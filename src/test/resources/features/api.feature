Feature: API Testing Feature

  Scenario: Validate GET API response
    Given I call GET API endpoint "https://reqres.in/api/users/2"
    Then I should receive status code 200
