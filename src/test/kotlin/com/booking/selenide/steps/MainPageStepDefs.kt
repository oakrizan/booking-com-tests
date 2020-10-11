package com.booking.selenide.steps

import com.booking.pages.headers.TopHeader
import com.booking.pages.main.StaysMainPage
import com.booking.pages.popups.RegSuccessPopup
import com.booking.util.SessionManager
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class MainPageStepDefs: En {
    @Autowired
    private lateinit var sessionManager: SessionManager
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var staysMainPage: StaysMainPage
    @Autowired
    private lateinit var regSuccessPopup: RegSuccessPopup

    init {
        Given("^I click Sign In button$") {
            topHeader.signIn()
        }

        Given("^I click Register button$") {
            topHeader.register()
        }

        And("^I am on Main page$") {
            staysMainPage.waitWhileReady()
            topHeader.waitWhileReady()
        }

        And("^main page is opened$") {
            sessionManager.waitForPageReady()
            if (regSuccessPopup.isDisplayed()) {
                regSuccessPopup.close()
            }
            staysMainPage.waitWhileReady()
            topHeader.waitWhileReady()
        }

    }
}