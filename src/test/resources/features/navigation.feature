Feature: Navigation
  Background: Set up environment
    Given launch browser url: https://booking.com
    And close cookies banner
    And I am on Main page

  Scenario: Navigate to Sign In page
    When I click Sign In button
    Then  I am in Account Sign In page
    And tear down

  Scenario: Navigate to Registration page
    When I click Register button
    Then I am in Account Sign Up page
    And tear down

  Scenario: Sign In via Facebook through new tab in browser
    When  launch browser url: https://account.booking.com/sign-in
    Then I am in Account Sign In page
    Then I go to Facebook Sign In
    Then I switch to tab 1
    Then I am in Facebook Sign In page
    Then I close current tab
    Then I switch to tab 0
    And I am in Account Sign In page
    And tear down
