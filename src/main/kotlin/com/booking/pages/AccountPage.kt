package com.booking.pages

import com.booking.pages.cookies.TopCookieWarning
import com.booking.pages.headers.GuestHeader
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountPage {
    @Autowired
    private lateinit var topCookieWarning: TopCookieWarning
    @Autowired
    private lateinit var guestHeader: GuestHeader

    private val header: SelenideElement = `$`(".nw-step-header")
    private val email: SelenideElement = `$`("[type=email]")
    private val getStartedButton: SelenideElement = `$`("[type=submit]")
    private val signOptsHref: SelenideElement = `$`("[class*=\"nw-link\"]")

    fun waitWhileReady() {
        topCookieWarning.waitWhileReady()
        guestHeader.waitWhileReady()
        header.shouldBe(visible)
        email.shouldBe(visible)
        getStartedButton.shouldBe(visible)
    }

    fun headerText(): String {
        return header.text()
    }

    fun signOptsText(): String {
        return signOptsHref.text()
    }
}