Feature: AccountCreation
  Scenario: Account creation
    Given I am in Sign Up page
    When I enter valid user email
    And click on “GET STARTED” button
    And I enter valid password
    And click on “Create Account” button
    And main page is opened
    And I click on “My Dashboard” button under account menu
    Then “My Dashboard” page is opened
    And correct value is prefilled in email verification placeholder //based on registered email