package com.booking.selenide

import com.booking.SessionManager
import com.booking.pages.access.Registration
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.pages.main.StaysMainPage
import com.booking.util.RegStepHeader.NEW_ACC_STEP_1
import com.booking.util.RegStepHeader.SIGN_IN_STEP_1
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

class NavigationSteps: En {
    @Autowired
    private lateinit var sessionManager: SessionManager
    @Autowired
    private lateinit var cookieBanner: CookieBanner
    @Autowired
    private lateinit var staysMainPage: StaysMainPage
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var registration: Registration
    @Autowired
    private lateinit var guestHeader: GuestHeader

    @Value("\${url}")
    lateinit var url: String

    init {
//        Given("^launch browser url: https://account.booking.com/register$") {
//            sessionManager.setup(url)
//        }
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
        Then("^Sign In page is opened$") {
            guestHeader.waitWhileReady()
            registration.email().waitUntil(visible, TIMEOUT_LONG.value)
            assertTrue { registration.stepHeaderText().contentEquals(SIGN_IN_STEP_1.stepText) }
        }

        When("^I click Register button$") {
            topHeader.register()
        }
        Then("^Registration page is opened$") {
            guestHeader.waitWhileReady()
            registration.email().waitUntil(visible, TIMEOUT_LONG.value)
            assertTrue { registration.stepHeaderText().contentEquals(NEW_ACC_STEP_1.stepText) }
        }
    }
}