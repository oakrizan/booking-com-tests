package com.booking.pages.main

import com.booking.pages.headers.CrossProductBar
import com.booking.pages.headers.TopHeader
import com.booking.util.Timeout.TIMEOUT_LONG
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Stays {
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var crossProductBar: CrossProductBar

    private val searchBox: SelenideElement = `$`(".xpi__searchbox")

    fun waitWhileReady() {
        topHeader.waitWhileReady()
        crossProductBar.waitWhileReady()
        searchBox.waitUntil(visible, TIMEOUT_LONG.value)
    }

    fun selectCurrency() {
        topHeader.currentCurrency()
    }
}