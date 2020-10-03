package com.booking.pages.cookies

import com.booking.util.Timeout.TIMEOUT_LONG
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class CookieBanner {
    private val wrapper: SelenideElement = `$`("#onetrust-banner-sdk")
    private val acceptButton: SelenideElement = `$`("#onetrust-accept-btn-handler")

    fun acceptCookie() {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        acceptButton.waitUntil(exist, TIMEOUT_SHORT.value)
        acceptButton.click()
        wrapper.waitUntil(not(visible), TIMEOUT_SHORT.value)
    }
}