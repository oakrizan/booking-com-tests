package com.booking.pages.popups

import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class RegSuccessPopup {
    private val wrapper: SelenideElement = `$`("[id$=modal-name]")
    private val closeButton: SelenideElement = `$`(".modal-mask-closeBtn")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
    }

    fun isDisplayed(): Boolean {
        return wrapper.isDisplayed
    }

    fun close() {
        closeButton.click()
    }
}