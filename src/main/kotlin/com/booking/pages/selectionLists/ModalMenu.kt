package com.booking.pages.selectionLists

import com.booking.util.ElementHelper
import com.booking.util.enums.Currency
import com.booking.util.enums.Language
import com.booking.util.enums.Timeout.TIMEOUT_LONG
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.disappear
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ModalMenu {
    @Autowired
    private lateinit var helper: ElementHelper

    private val wrapper: SelenideElement = `$`(".bui-modal__slot")
    private val closeButton: SelenideElement = `$`("[type=button]")
    private val currencies: ElementsCollection = `$$`(".bui-traveller-header__currency")
    private val languages: ElementsCollection = `$$`(".bui-traveller-header__selection-text")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        closeButton.waitUntil(visible, TIMEOUT_SHORT.value)
    }

    fun close() {
        closeButton.click()
        wrapper.waitUntil(disappear, TIMEOUT_SHORT.value)
    }

    fun selectLanguageByName(language: Language) {
        helper.findByText(languages, language.fullLangName).click()
    }

    fun selectLangByCode(language: Language) {

    }

    fun selectCurrencyByCode(currency: Currency) {
        helper.findByText(currencies, currency.code)
    }
}