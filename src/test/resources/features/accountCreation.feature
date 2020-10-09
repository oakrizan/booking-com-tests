Feature: AccountCreation
#  Background: Set up environment
#    Given launch browser url: https://booking.com
#    And close cookies banner
#    And launch browser url: https://account.booking.com/register
#    And select EN_US language on Account Page
#
#  Scenario: Account creation
#    Given I am in Account Sign Up page
#    When I enter valid user email
#    And click on “GET STARTED” button
#    And I enter valid password: this_is-secret1!
#    And click on “Create Account” button
#    And main page is opened
#    And I click on “Manage Account” button under account menu
#    Then “Account Settings” page is opened
#    And correct value is prefilled in email verification placeholder //based on registered email
#    And tear down