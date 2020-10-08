package com.booking.selenide.steps

import com.booking.pages.cookies.CookieBanner
import com.booking.pages.cookies.TopCookieWarning
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class CookieAndPopupStepDefs: En {
    @Autowired
    private lateinit var cookieWarning: TopCookieWarning
    @Autowired
    private lateinit var cookieBanner: CookieBanner

    init {
        //Close cookies
        Given("^close cookies warning$") {
            cookieWarning.close()
        }

        Given("^close cookies banner$") {
            cookieBanner.acceptCookie()
        }
    }
}