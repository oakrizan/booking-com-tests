package com.booking.selenide.steps

import com.booking.util.SessionManager
import com.codeborne.selenide.Selenide
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class SessionManagerStepDefs: En {
    @Autowired
    private lateinit var sessionManager: SessionManager


    init {
        //Set Up and Tear Down session
        Given("launch browser url: {}") { url: String ->
            sessionManager.setup(url)
            sessionManager.waitForPageReady()
        }

        Given("^tear down$") {
            sessionManager.tearDown()
        }

        //Switching between Browser Tabs
        Given("I switch to tab {}") { tabId: Int ->
            Selenide.switchTo().window(tabId)
        }

        Given("I close current tab") {
            Selenide.closeWindow()
        }
    }
}