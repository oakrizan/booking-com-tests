package com.booking.pages.cookies

import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.disappear
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.id
import org.springframework.stereotype.Component

@Component
class CookieBanner {
    private val wrapper: SelenideElement = `$`(id("onetrust-banner-sdk"))
    private val acceptButton: SelenideElement = `$`("#onetrust-button-group #onetrust-accept-btn-handler")

    fun acceptCookie() {
        if (wrapper.exists()) {
            //TODO refactor
            Selenide.sleep(1000)
            acceptButton.waitUntil(visible, TIMEOUT_SHORT.value)
            acceptButton.click()
            acceptButton.waitUntil(disappear, TIMEOUT_SHORT.value)
        }
    }
}