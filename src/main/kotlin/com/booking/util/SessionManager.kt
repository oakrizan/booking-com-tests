package com.booking.util

import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Selenide
import org.springframework.stereotype.Component

@Component
class SessionManager {
    fun setup(url: String) {
        Selenide.open(url)
    }

    fun tearDown() {
        Selenide.closeWebDriver()
    }

    fun waitForPageReady() {
        //Sleep needed since pages doesn't have state ready.
        //Some elements (like popups) and its related JS logic may not be ready,
        //even if element is already visible
        Selenide.sleep(TIMEOUT_LONG.value)
    }
}