package com.booking.selenide.steps

import com.booking.SessionManager
import com.booking.dataProvider.DataProvider
import com.booking.pages.access.AccountCheckIn
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountDashboard
import com.booking.pages.main.AccountSettings
import com.booking.pages.main.StaysMainPage
import com.booking.pages.popups.RegSuccessPopup
import com.booking.util.ProfileMenuCategory.ACCOUNT
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
    @Autowired
    private lateinit var accountSettings: AccountSettings

    private var email: String = ""

    init {
        Given("^I enter valid user email$") {
            email = dataProvider.generateEmail()
            accountCheckIn.enterEmail(email)
        }

        And("^I am on Main page$") {
            staysMainPage.waitWhileReady()
            topHeader.waitWhileNoAuthReady()
        }

        And("^main page is opened$") {
//            cookieBanner.acceptCookie()
            if (regSuccessPopup.isDisplayed()) {
                regSuccessPopup.close()
            }
            staysMainPage.waitWhileReady()
            topHeader.waitWhileAuthReady()
//            topHeader.waitWhileNoAuthReady()
        }

        And("^I click on “Manage Account” button under account menu$") {
            topHeader.openProfile(ACCOUNT)
        }
        Then("^“Account Settings” page is opened$") {
           accountSettings.waitWhileReady()
        }
        And("^correct value is prefilled in email verification placeholder //based on registered email") {
//        And("^correct value is prefilled in email verification placeholder (//.*)?$$") {
            assertTrue (accountSettings.emailToConfirm().contentEquals(email))
        }

        Given("^I click Sign In button$") {
            topHeader.signIn()
        }

        Given("^I click Register button$") {
            topHeader.register()
        }
    }
}