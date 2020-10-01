package com.booking.selenide

import com.booking.Config
import com.booking.pages.AccountPage
import com.codeborne.selenide.Selenide
import io.cucumber.java8.En
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(classes = [Config::class])
class AccountCreationStepDefs: En {
    @Autowired
    private lateinit var accountPage: AccountPage
    @Value("\${registerUrl}")
    lateinit var url: String

    init {
        Given("^I am in Sign Up page$") {
            Selenide.clearBrowserCookies()
            Selenide.open(url)
            accountPage.waitWhileReady()
            assertEquals(accountPage.headerText(), "Create your account")
        }
        When("^I enter valid user email$") {
            accountPage.enterEmail(email = "lalala@gmail.com")
        }
        And("^click on “GET STARTED” button$") {
            accountPage.getStartedButtonClick()
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