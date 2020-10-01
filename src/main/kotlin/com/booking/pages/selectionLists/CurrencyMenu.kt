package com.booking.pages.selectionLists

import com.booking.util.Currency
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class CurrencyMenu {
    private val wrapper: SelenideElement = `$`("#current_currency_foldout")
    private val currencySelector: String = "[data-lang=%s]"

    fun clickCurrency(curr: Currency) {
        `$`(String.format(currencySelector, curr.code)).click()
    }
}