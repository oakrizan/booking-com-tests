package com.booking.pages.headers

import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class GuestHeader {
    private var wrapper: SelenideElement = `$`(".guest-header")
    private var logo: SelenideElement = `$`(".icon-logo")
    private var langButton: SelenideElement = `$`(".guest-header .bui-button")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        logo.shouldBe(visible)
        langButton.shouldBe(visible)
    }

    fun selectLanguage() {
        langButton.click()
    }
}