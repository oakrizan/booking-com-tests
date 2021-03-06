package com.booking.pages.headers

import com.booking.pages.selectionLists.ModalMenu
import com.booking.util.ElementHelper
import com.booking.util.enums.Currency
import com.booking.util.enums.ProfileMenuCategory
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
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
    @Autowired
    private lateinit var helper: ElementHelper

    //New UI
    private val wrapperBui: SelenideElement = `$`(".bui-header")
    private val logoBui: SelenideElement = `$`(".-logos-booking-logo-inv")
    private val currencyButtonBui: SelenideElement = `$`("[data-modal-id=currency-selection]")
    private val langButtonBui: SelenideElement = `$`("[data-modal-id=language-selection]")
    private val buttons: ElementsCollection = `$$`(".bui-group .bui-button__text")

    //Old UI
    private val wrapper: SelenideElement = `$`(id("top"))
    private val logo: SelenideElement = `$`(id("logo_no_globe_new_logo"))
    private val currencyButton: SelenideElement = `$`("[data-id=currency_selector]")
    private val langButton: SelenideElement = `$`("[data-id=language_selector]")
    private val registerButton: SelenideElement = `$`(id("current_account_create"))
    private val signInButton: SelenideElement = `$`(id("current_account"))

    //Profile Menu New UI
    private val profileMenuButtonBui: SelenideElement = `$`("[aria-describedby^=profile-menu]")
    private val profileMenuBui: SelenideElement = `$`(".bui-dropdown__content")
    private val profileMenuItemsBui: ElementsCollection = `$$`(".bui-dropdown-menu__text")
    //Profile Menu Old UI
    private val profileMenuButton: SelenideElement = `$`("#profile-menu-trigger--content")
    private val profileMenu: SelenideElement = `$`(".fly-dropdown--profile-menu")
    private val profileMenuItems: ElementsCollection = `$$`(".profile-menu__item")

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
        }
    }

    fun register() {
        if (wrapper.exists()) {
            registerButton.click()
        } else {
            helper.findByText(buttons, "register").click()
        }
    }

    fun signIn() {
        if (wrapper.exists()) {
            signInButton.click()
        } else {
            helper.findByText(buttons, "sign in").click()
        }
    }

    fun profileButtonBui(): SelenideElement {
        return profileMenuButtonBui
    }

    fun openProfileShort(category: ProfileMenuCategory) {
        profileMenuButtonBui.click()
        profileMenuBui.waitUntil(visible, TIMEOUT_SHORT.value)
        helper.findByText(profileMenuItemsBui, category.text).click()
    }

    fun openProfileLong(category: ProfileMenuCategory) {
        profileMenuButton.click()
        profileMenu.waitUntil(visible, TIMEOUT_SHORT.value)
        helper.findByText(profileMenuItems, category.text).click()
    }

    fun selectCurrency(currCode: Currency) {
        if (currencyButton.exists()) {
            currencyButton.click()
            modalMenu.selectCurrencyByCode(currCode)
        } else {
            currencyButtonBui.click()
            modalMenu.selectCurrencyByCode(currCode)
        }
    }
}