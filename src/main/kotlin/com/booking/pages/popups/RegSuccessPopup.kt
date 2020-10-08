package com.booking.pages.popups

import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class RegSuccessPopup {
    private val wrapper: SelenideElement = `$`("[id$=modal-name]")
    private val closeButton: SelenideElement = `$`(".modal-mask-closeBtn")

    fun isDisplayed(): Boolean {
        //TODO refactor
        Selenide.sleep(2000)
        return wrapper.isDisplayed
    }

    fun close() {
        closeButton.click()
    }
}