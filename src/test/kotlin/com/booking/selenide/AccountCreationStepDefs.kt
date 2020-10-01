package com.booking.selenide

import com.booking.pages.AccountPage
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired

class AccountCreationStepDefs @Autowired constructor(accountPage: AccountPage): En {
    init {
        Given("^I am in Sign Up page$") {
            accountPage.waitWhileReady()
            assertEquals(accountPage.headerText(), "Create your account")
        }
        When("^I enter valid user email$") {

        }
        And("^click on “GET STARTED” button$") {

        }
        And("^I enter valid password$") {

        }
        And("^click on “Create Account” button$") {

        }
        And("^main page is opened$") {

        }
        And("^I click on “My Dashboard” button under account menu$") {

        }
        Then("^“My Dashboard” page is opened$") {
    }
        And("^correct value is prefilled in email verification placeholder //based on registered email$") {

        }
    }
}