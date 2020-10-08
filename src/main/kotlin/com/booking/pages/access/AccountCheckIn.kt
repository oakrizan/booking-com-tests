package com.booking.pages.access

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class AccountCheckIn {
    private val stepHeader: SelenideElement = `$`(".nw-step-header")
    private val emailField: SelenideElement = `$`("[type=email]")
    private val submitButton: SelenideElement = `$`("[type=submit]")
    private val passwordField: SelenideElement = `$`("#password")
    private val passwordConfirmField: SelenideElement = `$`("#confirmed_password")

    fun stepHeaderText(): String {
        return stepHeader.text()
    }

    fun email(): SelenideElement {
        return emailField
    }

    fun enterEmail(email: String) {
        emailField.`val`(email)
    }

    fun submit() {
        submitButton.click()
    }

    fun password(): SelenideElement {
        return passwordField
    }

    fun enterPassword(password: String) {
        passwordField.`val`(password)
    }

    fun createPassword(password: String) {
        passwordField.shouldBe(visible)
        passwordConfirmField.shouldBe(visible)
        passwordField.`val`(password)
        passwordConfirmField.`val`(password)
    }
}