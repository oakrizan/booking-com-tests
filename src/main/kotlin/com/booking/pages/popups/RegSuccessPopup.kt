package com.booking.pages.popups

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class RegSuccessPopup {
    private val wrapper: SelenideElement = `$`("[id$=modal-name]")
    private val closeButton: SelenideElement = `$`(".modal-mask-closeBtn")

    fun isDisplayed(): Boolean {
        return wrapper.isDisplayed
    }

    fun close() {
        closeButton.click()
    }
}