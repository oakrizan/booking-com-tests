package com.booking.selenide

import com.booking.Config
import com.booking.SessionManager
import com.booking.pages.access.Registration
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountDashboard
import com.booking.pages.main.StaysMainPage
import com.booking.pages.popups.RegSuccessPopup
import com.booking.util.Currency.US_DOLLAR
import com.booking.util.Language.EN_US
import com.booking.util.ProfileMenuCategory.DASHBOARD
import com.booking.util.RegStepHeader.STEP_1
import com.booking.util.RegStepHeader.STEP_2
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import io.cucumber.java8.En
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(classes = [Config::class])
class AccountCreationSteps: En {
    @Autowired
    private lateinit var guestHeader: GuestHeader
    @Autowired
    private lateinit var registration: Registration
    @Autowired
    private lateinit var cookieWarning: TopCookieWarning
    @Autowired
    private lateinit var sessionManager: SessionManager
    @Autowired
    private lateinit var cookieBanner: CookieBanner
    @Autowired
    private lateinit var regSuccessPopup: RegSuccessPopup
    @Autowired
    private lateinit var staysMainPage: StaysMainPage
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var accountDashboard: AccountDashboard

    @Value("\${registerUrl}")
    lateinit var url: String

//    private val faker: Faker = Faker()

    //    private val email: String = faker.internet.email()
    private val email: String = "terl34!@gmail.com"

    init {
        Given("^launch browser url: https://account.booking.com/register$") {
            sessionManager.setup(url)
        }
        And("^close cookies banner$") {
            cookieWarning.waitWhileReady()
            cookieWarning.close()
        }
        And("^select English language$") {
            guestHeader.waitWhileReady()
            guestHeader.selectLanguage(EN_US)
        }
        Given("^I am in Sign Up page$") {
            registration.email().waitUntil(visible, TIMEOUT_SHORT.value)
            assertTrue { registration.stepHeaderText().contentEquals(STEP_1.stepText) }
        }
        When("^I enter valid user email$") {
            registration.enterEmail(email)
        }
        And("^click on “GET STARTED” button$") {
            registration.submit()
        }
        And("^I enter valid password$") {
            registration.password().waitUntil(visible, TIMEOUT_SHORT.value)
            assertTrue { registration.stepHeaderText().contentEquals(STEP_2.stepText) }
            registration.createPassword("this_is-secret1!")
        }
        And("^click on “Create Account” button$") {
            registration.submit()
        }
        And("^main page is opened$") {
            cookieBanner.acceptCookie()
            regSuccessPopup.close()
            staysMainPage.waitWhileReady()
            topHeader.waitWhileReady()
            topHeader.selectCurrency(US_DOLLAR)
            assertTrue { topHeader.userFirstName().contentEquals("Your account") }
        }
        And("^I click on “My Dashboard” button under account menu$") {
            topHeader.openProfile(DASHBOARD)
        }
        Then("^“My Dashboard” page is opened$") {
            accountDashboard.waitWhileReady()
            accountDashboard.emailConfirmationBanner().shouldBe(visible)
        }
        And("^correct value is prefilled in email verification placeholder //based on registered email$") {
            assertTrue { accountDashboard.emailToConfirm().contentEquals(email) }
        }
    }
}