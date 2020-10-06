Feature: Navigation
  Background: Set up environment
    Given launch browser url: https://booking.com
    And close cookies banner
    And I am on Main page

  Scenario: Navigate to Sign In page
    When I click Sign In button
    Then  I am in Sign In page
    Then tear down

  Scenario: Navigate to Registration page
    When I click Register button
    Then  I am in Sign Up page
    Then tear down