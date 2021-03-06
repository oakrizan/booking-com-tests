package com.booking.pages.cookies

import com.booking.util.enums.Timeout
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.not
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.id
import org.springframework.stereotype.Component

@Component
class CookieBanner {
    private val wrapper: SelenideElement = `$`(id("onetrust-banner-sdk"))
    private val acceptButton: SelenideElement = `$`(id("onetrust-accept-btn-handler"))

    fun acceptCookie() {
        acceptButton.waitUntil(visible, TIMEOUT_SHORT.value)
        acceptButton.click()
        wrapper.waitUntil(not(visible), Timeout.TIMEOUT_LONG.value)
    }
}