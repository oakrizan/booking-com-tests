package com.booking.pages.cookies

import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class TopCookieWarning {
    private val wrapper: SelenideElement = `$`(".cookie-warning")
    private val warnLink: SelenideElement = `$`(".cookie-warning__link")
    private val closeButton: SelenideElement = `$`(".cookie-warning__close")

    fun close() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        warnLink.shouldBe(visible)
        closeButton.shouldBe(visible)
        wrapper.shouldBe(visible)
        closeButton.click()
    }
}