package com.booking.selenide.steps

import com.booking.SessionManager
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
    }
}