package com.booking.pages.main

import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.By.id
import org.springframework.stereotype.Component

@Component
class AccountSettings {
    private val settingNavBar: SelenideElement = `$`(".settings-navbar")

    //Your Account
    private val aboutYouBlock: SelenideElement = `$`(id("about-you"))
    private val accountHeader: SelenideElement = `$`(".settings-head__title__h1")

    //When You Book
    private val whenYouBookBlock: SelenideElement = `$`(id("when-you-book"))
    private val bookHeader: SelenideElement = `$`(".settings-head__title__h2")
    private val emailField: SelenideElement = `$`(id("email"))

    fun waitWhileReady() {
        settingNavBar.waitUntil(visible, TIMEOUT_LONG.value)
        aboutYouBlock.waitUntil(visible, TIMEOUT_SHORT.value)
        whenYouBookBlock.waitUntil(visible, TIMEOUT_SHORT.value)
        assertTrue(accountHeader.text().contentEquals("Your Booking.com Account"))
        assertTrue(bookHeader.text().contentEquals("For When You Book"))
    }

    fun emailToConfirm(): String {
        return emailField.value!!
    }
}