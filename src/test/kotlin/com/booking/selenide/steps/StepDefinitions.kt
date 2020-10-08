package com.booking.selenide.steps

import com.booking.SessionManager
import com.booking.dataProvider.DataProvider
import com.booking.pages.access.AccountCheckIn
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountDashboard
import com.booking.pages.main.StaysMainPage
import com.booking.pages.popups.RegSuccessPopup
import com.booking.util.Currency
import com.booking.util.Language
import com.booking.util.ProfileMenuCategory.DASHBOARD
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired

class StepDefinitions: En {
    @Autowired
    private lateinit var guestHeader: GuestHeader
    @Autowired
    private lateinit var accountCheckIn: AccountCheckIn
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
        //Language and Currency selection
        //Main Page Top Header
        Given("select {} language") { lang: Language ->
            topHeader.waitWhileReady()
            topHeader.selectLanguage(lang)
        }

        Given("select {} currency") { currency: Currency ->
            topHeader.waitWhileReady()
            topHeader.selectCurrency(currency)
        }

        //Account Guest Header
        Given("select {} language on Account Page") { lang: Language ->
            guestHeader.waitWhileReady()
            guestHeader.selectLanguage(lang)
        }

        Given("^I enter valid user email$") {
            email = dataProvider.generateEmail()
            accountCheckIn.enterEmail(email)
        }

        And("^I am on Main page$") {
            staysMainPage.waitWhileReady()
            topHeader.waitWhileReady()
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
            assertTrue { accountDashboard.emailToConfirm()!!.contentEquals(email) }
        }

        Given("^click Sign In button$") {
            topHeader.signIn()
        }

        Given("^click Register button$") {
            topHeader.register()
        }
    }
}