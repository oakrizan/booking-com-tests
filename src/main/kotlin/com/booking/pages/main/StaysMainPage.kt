package com.booking.pages.main

import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class StaysMainPage {
    private val searchBox: SelenideElement = `$`(".xpi__searchbox")
    private val destination: SelenideElement = `$`("#ss")
    private val checkInDate: SelenideElement = `$`("[data-calendar2-type=checkin]")
    private val checkOutDate: SelenideElement = `$`("[data-calendar2-type=checkout]")
    private val searchButton: SelenideElement = `$`(".sb-searchbox__button")

    fun waitWhileReady() {
        searchBox.waitUntil(visible, TIMEOUT_LONG.value)
        destination.shouldBe(visible)
        checkInDate.shouldBe(visible)
        checkOutDate.shouldBe(visible)
        searchButton.shouldBe(visible)
    }
}