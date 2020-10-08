package com.booking.pages.cookies

import com.booking.util.Timeout.TIMEOUT_LONG
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class CookieBanner {
    private val wrapper: SelenideElement = `$`("#onetrust-consent-sdk #onetrust-banner-sdk .ot-sdk-row")
    private val acceptButton: SelenideElement = `$`("#onetrust-button-group #onetrust-accept-btn-handler")

    fun acceptCookie() {
        //TODO refactor
        Selenide.sleep(5000)
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        acceptButton.waitUntil(exist, TIMEOUT_SHORT.value)
        acceptButton.click()
        //TODO refactor
        Selenide.sleep(3000)
        wrapper.waitUntil(disappear, TIMEOUT_SHORT.value)
    }
}