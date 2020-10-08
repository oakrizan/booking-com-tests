package com.booking.pages.selectionLists

import com.booking.util.Currency
import com.booking.util.Language
import com.booking.util.Timeout
import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class ModalMenu {
    private val wrapper: SelenideElement = `$`(".bui-modal__slot")
    private val currencies: ElementsCollection = `$$`(".bui-traveller-header__currency")
    private val selectedCurrency: SelenideElement = `$`(".bui-traveller-header__currency--active")
    private val language: String = "[lang=%s]"

    //TODO - validation that lang is selected
    fun selectLanguage(language: Language) {
        wrapper.waitUntil(Condition.visible, Timeout.TIMEOUT_SHORT.value)
        val selector = String.format(this.language, language.code)
        wrapper.`$`(selector).click()
    }

    //TODO validation that currency is selected
    //TODO - refactor as sequence to common method
    fun selectCurrency(currency: Currency) {
        wrapper.waitUntil(Condition.visible, Timeout.TIMEOUT_SHORT.value)
        val element: SelenideElement = currencies.asSequence()
                .filter { it.text().contentEquals(currency.code) }
                .first()
        element.click()
    }
}