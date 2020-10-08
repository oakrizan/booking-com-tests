package com.booking.pages.selectionLists

import com.booking.util.Currency
import com.booking.util.Language
import com.booking.util.Timeout
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class ModalMenu {
    private val wrapper: SelenideElement = `$`(".bui-modal__slot")
    private val closeButton: SelenideElement = `$`("[type=button]")
    private val currencies: ElementsCollection = `$$`(".bui-traveller-header__currency")
    private val languages: ElementsCollection = `$$`(".bui-traveller-header__selection-text")

    fun waitWhileReady() {
        wrapper.waitUntil(appear, TIMEOUT_SHORT.value)
        closeButton.waitUntil(visible, TIMEOUT_SHORT.value)
    }

    fun close() {
        closeButton.click()
        wrapper.waitUntil(disappear, TIMEOUT_SHORT.value)
    }

    //TODO - validation that lang is selected
    fun selectLanguageByName(language: Language) {
        val element: SelenideElement = languages.asSequence()
                .filter { it.text().contentEquals(language.fullLangName) }
                .first()
        element.click()
    }

    fun selectLangByCode(language: Language) {
//        val selector = String.format(this.languages, language.code)
//        wrapper.`$`(selector).click()
    }

    //TODO validation that currency is selected
    //TODO - refactor as sequence to common method
    fun selectCurrency(currency: Currency) {
        val element: SelenideElement = currencies.asSequence()
                .filter { it.text().contentEquals(currency.code) }
                .first()
        element.click()
    }
}