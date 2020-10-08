package com.booking.pages.selectionLists

import com.booking.util.Language
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
//TODO Remove
class LangMenuLong {
    private val wrapper: SelenideElement = `$`(".bui-modal__slot")
    private val langSelector: String = "[lang=%s]"

    fun selectLanguage(language: Language) {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        val selector = String.format(langSelector, language.code)
        wrapper.`$`(selector).click()
    }
}