package com.booking.pages.headers

import com.booking.pages.selectionLists.CurrencyMenu
import com.booking.util.Currency
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopHeader {
    @Autowired
    private lateinit var currMenu: CurrencyMenu

    private val wrapper: SelenideElement = `$`("#top")
    private val logo: SelenideElement = `$`("#logo_no_globe_new_logo")
    private val currButton: SelenideElement = `$`("[data-id=currency_selector]")
    private val selectedCurrency: SelenideElement = `$`("[name=selected_currency]")
    private val langButton: SelenideElement = `$`("[data-id=language_selector]")
    private val selectedLang: SelenideElement = `$`("#current_language_foldout .selected_country")
    private val registerButton = `$`("#current_account_create")
    private val signInButton = `$`("#current_account")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        logo.shouldBe(visible)
        currButton.shouldBe(visible)
        langButton.shouldBe(visible)
        signInButton.shouldBe(visible)
        registerButton.shouldBe(visible)
    }

    fun currentCurrency(): String {
        selectedCurrency.shouldBe(visible)
        return selectedCurrency.value
    }

    fun selectCurrency(currCode: Currency) {
        currButton.waitUntil(visible, TIMEOUT_SHORT.value)
        currButton.text()
        currButton.click()
        currMenu.clickCurrency(currCode)
    }
}