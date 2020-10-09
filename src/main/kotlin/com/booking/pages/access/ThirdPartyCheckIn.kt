package com.booking.pages.access

import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.By.id
import org.springframework.stereotype.Component

@Component
class ThirdPartyCheckIn {
    private val facebookHeader: SelenideElement = `$`(id("homelink"))
    private val facebookContent: SelenideElement = `$`(id("content"))
    private val facebookLoginForm: SelenideElement = `$`(id("login_form"))

    fun waitForFacebookForm() {
        facebookHeader.waitUntil(visible, TIMEOUT_LONG.value)
        assertTrue(facebookContent.text().contains("Log in to use your Facebook account with ", ignoreCase = true))
        facebookLoginForm.shouldBe(visible)
    }
}