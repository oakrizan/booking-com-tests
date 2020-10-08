package com.booking.pages.main

import com.booking.util.AccountNavigationItem
import com.booking.util.AccountNavigationItem.DASHBOARD
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.stereotype.Component

@Component
class AccountDashboard {
    private val profileNavigation: SelenideElement = `$`(".profile-area__nav")
    private val selectedItem: SelenideElement = `$`(".selected")
    private val accountCard: SelenideElement = `$`(".acc-profile-card")
    private val dealSearch: SelenideElement = `$`("#frm")
    private val emailConfirmationBanner: SelenideElement = `$`(".email-confirm-banner")
    private val emailToConfirm: SelenideElement = `$`(".email-confirm-banner__email-text")

    fun waitWhileReady() {
        accountCard.waitUntil(visible, TIMEOUT_LONG.value)
        profileNavigation.shouldBe(visible)
        assertTrue { selectedItem.text().contentEquals(DASHBOARD.itemName) }
        dealSearch.shouldBe(visible)
    }

    fun emailConfirmationBanner(): SelenideElement {
        return emailConfirmationBanner
    }

    fun emailToConfirm(): String? {
        return emailToConfirm.value
    }
}