package com.booking.pages.headers

import com.booking.pages.selectionLists.ModalMenu
import com.booking.util.enums.Language
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GuestHeader {
    @Autowired
    private lateinit var menu: ModalMenu
    private var wrapper: SelenideElement = `$`(".guest-header")
    private var logo: SelenideElement = `$`(".icon-logo")
    private var langButton: SelenideElement = `$`(".guest-header .bui-button")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        logo.shouldBe(visible)
        langButton.shouldBe(visible)
    }

    fun selectLanguage(lang: Language) {
        langButton.click()
        menu.waitWhileReady()
        menu.selectLanguageByName(lang)
    }
}