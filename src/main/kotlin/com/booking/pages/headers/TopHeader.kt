package com.booking.pages.headers

import com.booking.pages.selectionLists.ModalMenu
import com.booking.util.Currency
import com.booking.util.Language
import com.booking.util.ProfileMenuCategory
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.ex.ElementNotFound
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.By.id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class TopHeader {
    @Autowired
    private lateinit var modalMenu: ModalMenu

    //Unauthorized state
    private val wrapperBui: SelenideElement = `$`(".bui-header")
    private val logoBui: SelenideElement = `$`(".-logos-booking-logo-inv")
    private val currencyButtonBui: SelenideElement = `$`("[data-modal-id=currency-selection]")
    private val langButtonBui: SelenideElement = `$`("[data-modal-id=language-selection]")
    private val buttons: ElementsCollection = `$$`(".bui-group .bui-button__text")

    //Authorized state
    private val wrapper: SelenideElement = `$`(id("top"))
    private val logo: SelenideElement = `$`(id("logo_no_globe_new_logo"))
    private val currencyButton: SelenideElement = `$`("[data-id=currency_selector]")
    private val langButton: SelenideElement = `$`("[data-id=language_selector]")
    private val registerButton: SelenideElement = `$`(id("current_account_create"))
    private val signInButton: SelenideElement = `$`(id("current_account"))

    //Profile Menu
    private val profileMenuButton: SelenideElement = `$`("[aria-describedby^=profile-menu]")
    private val profileMenu: SelenideElement = `$`(".bui-dropdown__content")
    private val profileMenuItems: ElementsCollection = `$$`(".bui-dropdown-menu__text")

    fun waitWhileReady() {
        try {
            wrapperBui.waitUntil(visible, TIMEOUT_SHORT.value)
            logoBui.shouldBe(visible)
            currencyButtonBui.shouldBe(visible)
            langButtonBui.shouldBe(visible)
            assertTrue(buttons.size > 0)
        } catch (e: ElementNotFound) {
            wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
            logo.shouldBe(visible)
            currencyButton.shouldBe(visible)
            langButton.shouldBe(visible)
            registerButton.shouldBe(visible)
            signInButton.shouldBe(visible)
        }
    }

    //TODO refactor - common util methos to filter and click
    fun register() {
        if (wrapper.exists()) {
            registerButton.click()
        } else {
            val element: SelenideElement = buttons.asSequence()
                    .filter { it.text().contains("register", ignoreCase = true) }
                    .first()
            element.click()
        }
    }

    //TODO refactor - common util methos to filter and click
    fun signIn() {
        if (wrapper.exists()) {
            signInButton.click()
        } else {
            val element: SelenideElement = buttons.asSequence()
                    .filter { it.text().contains("sign in", ignoreCase = true) }
                    .first()
            element.click()
        }
    }

    //TODO refactor - common util methos to filter and click
    fun openProfile(category: ProfileMenuCategory) {
        profileMenuButton.click()
        profileMenu.waitUntil(visible, TIMEOUT_SHORT.value)
        val element: SelenideElement = profileMenuItems.asSequence()
                .filter { it.text().contentEquals(category.text) }
                .first()
        element.click()
    }

    fun selectCurrency(currCode: Currency) {
        if (currencyButton.exists()) {
            currencyButton.click()
            modalMenu.selectCurrency(currCode)
        } else {
            currencyButtonBui.click()
            modalMenu.selectCurrency(currCode)
        }
    }

    fun selectLanguage(langCode: Language) {
        if (langButton.exists()) {
            currencyButton.click()
            modalMenu.selectLangByCode(langCode)
        } else {
            langButtonBui.click()
            modalMenu.selectLangByCode(langCode)
        }
    }
}