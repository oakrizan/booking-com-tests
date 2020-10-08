package com.booking.pages.headers

import com.booking.pages.selectionLists.ModalMenu
import com.booking.util.Currency
import com.booking.util.Language
import com.booking.util.ProfileMenuCategory
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopHeader {
    @Autowired
    private lateinit var modalMenu: ModalMenu

    private val wrapper: SelenideElement = `$`(".bui-header")
    private val logo: SelenideElement = `$`(".-logos-booking-logo-inv")
    private val currencyButton: SelenideElement = `$`("[data-modal-id=currency-selection]")
    private val langButton: SelenideElement = `$`("[data-modal-id=language-selection]")
//    private val registerButton = `$`("#current_account_create")
//    private val signInButton = `$`("#current_account")
    private val buttons: ElementsCollection = `$$`(".bui-button__text")
    //Profile Menu
    private val profileMenuButton: SelenideElement = `$`("#profile-menu-trigger--content")
    private val userFirstName: SelenideElement = `$`(".user_firstname")
    private val profileMenu: SelenideElement = `$`(".fly-dropdown--profile-menu")
    private val profileMenuItems: ElementsCollection = `$$`(".profile-menu__item")

    fun waitWhileReady() {
        //TODO refactor
        Selenide.sleep(4000)
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        logo.should(exist)
        currencyButton.should(exist)
        langButton.should(exist)
    }

    //TODO refactor - common util methos to filter and click
    fun register() {
        val element: SelenideElement = buttons.asSequence()
                .filter { it.text().contentEquals("Register") }
                .first()
        element.click()
    }

    //TODO refactor - common util methos to filter and click
    fun signIn() {
        val element: SelenideElement = buttons.asSequence()
                .filter { it.text().contentEquals("Sign In") }
                .first()
        element.click()
    }

    fun userFirstName(): String {
        return userFirstName.text()
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
        currencyButton.waitUntil(visible, TIMEOUT_SHORT.value)
        currencyButton.click()
        modalMenu.selectCurrency(currCode)
    }

    fun selectLanguage(langCode: Language) {
        langButton.waitUntil(visible, TIMEOUT_SHORT.value)
        langButton.click()
        modalMenu.selectLanguage(langCode)
    }
}