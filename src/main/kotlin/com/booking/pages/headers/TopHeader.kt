package com.booking.pages.headers

import com.booking.pages.selectionLists.CurrencyMenu
import com.booking.util.Currency
import com.booking.util.ProfileMenuCategory
import com.booking.util.Timeout.TIMEOUT_SHORT
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopHeader {
    @Autowired
    private lateinit var currMenu: CurrencyMenu

    private val wrapper: SelenideElement = `$`("#top")
    private val logo: SelenideElement = `$`("#logo_no_globe_new_logo")
    private val currencyButton: SelenideElement = `$`("[data-id=currency_selector]")
    private val selectedCurrency: SelenideElement = `$`("[name=selected_currency]")
    private val langButton: SelenideElement = `$`("[data-id=language_selector]")
    private val addPropertyButton: SelenideElement = `$`("#add_property_topbar")
    private val selectedLang: SelenideElement = `$`("#current_language_foldout .selected_country")
    private val registerButton = `$`("#current_account_create")
    private val signInButton = `$`("#current_account")
    //Profile Menu
    private val profileMenuButton: SelenideElement = `$`("#profile-menu-trigger--content")
    private val userFirstName: SelenideElement = `$`(".user_firstname")
    private val profileMenu: SelenideElement = `$`(".fly-dropdown--profile-menu")
    private val profileMenuItems: ElementsCollection = `$$`(".profile-menu__item")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_LONG.value)
        logo.shouldBe(visible)
        currencyButton.shouldBe(visible)
        langButton.shouldBe(visible)
        addPropertyButton.shouldBe(visible)
    }

    fun register(): SelenideElement {
        return registerButton
    }

    fun signIn(): SelenideElement {
        return signInButton
    }

    fun userFirstName(): String {
        return userFirstName.text()
    }
//TODO refactor - common util methos to filter and click
    fun openProfile(category: ProfileMenuCategory) {
        profileMenuButton.click()
        profileMenu.waitUntil(visible, TIMEOUT_SHORT.value)
        val element: SelenideElement = profileMenuItems.asSequence()
                    .filter { it.text()!!.contentEquals(category.text) }
                    .first()
            element.click()
    }

    //TODO refacotr - currency & language selection from headers
    fun currentCurrency(): String {
        selectedCurrency.shouldBe(visible)
        return selectedCurrency.value
    }

    fun selectCurrency(currCode: Currency) {
        currencyButton.waitUntil(visible, TIMEOUT_SHORT.value)
        currencyButton.text()
        currencyButton.click()
        currMenu.clickCurrency(currCode)
    }
}