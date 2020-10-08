package com.booking.pages.selectionLists

import com.booking.util.Language
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
//TODO remove
class LangMenuShort {
    private val wrapper: SelenideElement = `$`(".bui-modal__header")
    private val selectedLang: SelenideElement = `$`(".bui-list-item--active")
    private val closeButton: SelenideElement = `$`(".bui-modal__close")
    private val languageList: ElementsCollection = `$$`(".bui-traveller-header__selection-text")

    //TODO refactor
    fun waitWhileReady() {
        Selenide.sleep(2000)
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        closeButton.shouldBe(visible)
    }

    fun selectedLanguage(): String {
        return selectedLang.text()
    }

    fun selectLanguage(language: Language) {
        val element: SelenideElement = languageList.asSequence()
                .filter { it.text().contentEquals(language.fullLangName) }
                .first()
        element.click()
    }

    fun close() {
        closeButton.click()
    }
}