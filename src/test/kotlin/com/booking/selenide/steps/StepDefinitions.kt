package com.booking.selenide.steps

import com.booking.SessionManager
import com.booking.dataProvider.DataProvider
import com.booking.pages.access.Registration
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountDashboard
import com.booking.pages.main.StaysMainPage
import com.booking.pages.popups.RegSuccessPopup
import com.booking.util.Currency
import com.booking.util.Currency.US_DOLLAR
import com.booking.util.Language
import com.booking.util.ProfileMenuCategory.DASHBOARD
import com.booking.util.RegStepHeader
import com.booking.util.RegStepHeader.*
import com.booking.util.RegStepHeader.Companion.errorMsg
import com.booking.util.Timeout
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired

class StepDefinitions: En {
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
    @Autowired
    private lateinit var dataProvider: DataProvider

    private var email: String = ""

    init {
        Given("launch browser url: {}") { url: String ->
            sessionManager.setup(url)
        }

        Given("^close cookie warning$") {
            cookieWarning.close()
        }

        Given("select {} language") { lang: Language ->
            guestHeader.waitWhileReady()
            guestHeader.selectLanguage(lang)
        }

        Given("select {} currency") { currency: Currency ->
            topHeader.waitWhileReady()
            topHeader.selectCurrency(currency)
        }

        Given("^I am in Sign Up page$") {
            registration.email().waitUntil(visible, TIMEOUT_SHORT.value)
            val actualHeaderText: String = registration.stepHeaderText()
            val expectedHeaderText: RegStepHeader = NEW_ACC_STEP_1
            assertTrue( actualHeaderText.contentEquals(expectedHeaderText.stepText), errorMsg(expectedHeaderText, actualHeaderText))
        }

        Given("^I enter valid user email$") {
            email = dataProvider.generateEmail()
            registration.enterEmail(email)
        }

        Given("^click on “GET STARTED” button$") {
            registration.submit()
        }

        Given("^I enter valid password$") {
            registration.password().waitUntil(visible, TIMEOUT_SHORT.value)
            //TODO refactor - same as validation for Sign In page
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
            assertTrue { topHeader.userFirstName().contentEquals("Your account") }
        }
        And("^I click on “My Dashboard” button under account menu$") {
            topHeader.openProfile(DASHBOARD)
        }
        Then("^“My Dashboard” page is opened$") {
            accountDashboard.waitWhileReady()
            accountDashboard.emailConfirmationBanner().shouldBe(visible)
        }
        And("^correct value is prefilled in email verification placeholder //based on registered email") {
//        And("^correct value is prefilled in email verification placeholder (//.*)?$$") {
            assertTrue { accountDashboard.emailToConfirm().contentEquals(email) }
        }
        And("^close cookies banner$") {
            cookieBanner.acceptCookie()
        }

        And("^I am on Main page$") {
            staysMainPage.waitWhileReady()
            topHeader.waitWhileReady()
        }

        When("^I click Sign In button$") {
            topHeader.signIn()
        }

        When("^I click Register button$") {
            topHeader.register()
        }

        Given("^I am in Sign In page$") {
            guestHeader.waitWhileReady()
            registration.email().waitUntil(visible, Timeout.TIMEOUT_LONG.value)
            assertTrue { registration.stepHeaderText().contentEquals(SIGN_IN_STEP_1.stepText) }
        }

        Given("^tear down$") {
            sessionManager.tearDown()
        }
    }
}