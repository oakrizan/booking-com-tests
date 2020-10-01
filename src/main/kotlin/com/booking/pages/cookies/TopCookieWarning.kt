package com.booking.pages.cookies

import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class TopCookieWarning {
    private val wrapper: SelenideElement = `$`(".cookie-warning")
    private val warnLink: SelenideElement = `$`(".cookie-warning__link")
    private val closeButton: SelenideElement = `$`(".cookie-warning__close")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        warnLink.shouldBe(visible)
        closeButton.shouldBe(visible)
    }

    fun closeCookies() {
        wrapper.shouldBe(visible)
        closeButton.click()
    }
}