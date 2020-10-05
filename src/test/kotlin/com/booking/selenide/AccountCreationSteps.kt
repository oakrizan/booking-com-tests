package com.booking.selenide

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
import com.booking.util.RegStepHeader.NEW_ACC_STEP_1
import com.booking.util.RegStepHeader.NEW_ACC_STEP_2
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

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

    private val email: String = "terl34!@gmail.com"

    init {
        Given("launch browser url: {}") { url: String ->
            sessionManager.setup(url)
        }
        And("^select English language$") {
            guestHeader.waitWhileReady()
            guestHeader.selectLanguage(EN_US)
        }
        Given("^I am in Sign Up page$") {
            registration.email().waitUntil(visible, TIMEOUT_SHORT.value)
            //TODO - validate assertion type
            assertTrue { registration.stepHeaderText().contentEquals(NEW_ACC_STEP_1.stepText) }
        }
        When("^I enter valid user email$") {
//            val faker = Faker()
            registration.enterEmail(email)
        }
        And("^click on “GET STARTED” button$") {
            registration.submit()
        }
        And("^I enter valid password$") {
            registration.password().waitUntil(visible, TIMEOUT_SHORT.value)
            assertTrue { registration.stepHeaderText().contentEquals(NEW_ACC_STEP_2.stepText) }
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
        And("^correct value is prefilled in email verification placeholder (//.*)?$$") {
            assertTrue { accountDashboard.emailToConfirm().contentEquals(email) }
        }
    }
}