package com.booking

import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Selenide
import org.springframework.stereotype.Component

@Component
class SessionManager {
    fun setup(url: String) {
        Selenide.open(url)
    }

    fun tearDown() {
        Selenide.clearBrowserCookies()
        Selenide.closeWebDriver()
    }

    fun waitForPageReady() {
        Selenide.sleep(TIMEOUT_LONG.value)
    }
}