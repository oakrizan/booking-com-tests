package com.booking.util

import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Selenide
import org.springframework.stereotype.Component

@Component
class SessionManager {
    fun setup(url: String) {
        Selenide.clearBrowserCookies()
        Selenide.open(url)
        //TODO -set browser window size
    }

    fun tearDown() {
        Selenide.clearBrowserCookies()
        Selenide.closeWebDriver()
    }

    fun waitForPageReady() {
        Selenide.sleep(TIMEOUT_LONG.value)
    }
}