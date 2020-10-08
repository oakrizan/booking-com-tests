package com.booking.selenide.steps

import com.booking.SessionManager
import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.util.Currency
import com.booking.util.Language
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class SessionManagerStepDefs: En {
    @Autowired
    private lateinit var sessionManager: SessionManager
    @Autowired
    private lateinit var cookieWarning: TopCookieWarning
    @Autowired
    private lateinit var cookieBanner: CookieBanner
    @Autowired
    private lateinit var guestHeader: GuestHeader
    @Autowired
    private lateinit var topHeader: TopHeader

    init {
        //Set Up and Tear Down session
        Given("launch browser url: {}") { url: String ->
            sessionManager.setup(url)
        }

        Given("^tear down$") {
            sessionManager.tearDown()
        }
    }
}