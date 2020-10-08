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
import org.openqa.selenium.By
import org.openqa.selenium.By.id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopHeader {
    @Autowired
    private lateinit var modalMenu: ModalMenu

    //Unauthorized state
    private val wrapper: SelenideElement = `$`(".bui-header")
//    private val wrapperNoAuth: SelenideElement = `$`(".bui-header")
    private val logo: SelenideElement = `$`(".-logos-booking-logo-inv")
//    private val logoNoAuth: SelenideElement = `$`(".-logos-booking-logo-inv")
    private val currencyButtonNoAuth: SelenideElement = `$`("[data-modal-id=currency-selection]")
    private val langButtonNoAuth: SelenideElement = `$`("[data-modal-id=language-selection]")
    private val buttons: ElementsCollection = `$$`(".bui-button")

    //Authorized state
//    private val wrapperAuth: SelenideElement = `$`(id("top"))
    private val logoAuth: SelenideElement = `$`(id("logo_no_globe_new_logo"))

    //Profile Menu
    private val profileMenuButton: SelenideElement = `$`("[aria-describedby^=profile-menu]")
    private val profileMenu: SelenideElement = `$`(".bui-dropdown__content")
    private val profileMenuItems: ElementsCollection = `$$`(".bui-dropdown-menu__text")

    fun waitWhileNoAuthReady() {
        //TODO refactor
        Selenide.sleep(4000)
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        logo.should(exist)
        currencyButtonNoAuth.should(exist)
        langButtonNoAuth.should(exist)
    }

    fun waitWhileAuthReady() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        logo.should(exist)
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
        currencyButtonNoAuth.waitUntil(visible, TIMEOUT_SHORT.value)
        currencyButtonNoAuth.click()
        modalMenu.selectCurrency(currCode)
    }

    fun selectLanguage(langCode: Language) {
        langButtonNoAuth.waitUntil(visible, TIMEOUT_SHORT.value)
        langButtonNoAuth.click()
        modalMenu.selectLanguageByName(langCode)
    }
}