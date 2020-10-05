Feature: Navigation
  Background: Set up environment
    Given launch browser url
    And close cookies banner
    And I am on Main page

  Scenario: Navigate to Sign In page
    When I click Sign In button
    Then  Sign In page is opened

  Scenario: Navigate to Registration page
    When I click Register button
    Then  Registration page is opened