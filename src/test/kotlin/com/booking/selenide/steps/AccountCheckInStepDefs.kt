package com.booking.selenide.steps

import com.booking.pages.access.AccountCheckIn
import com.booking.pages.access.ThirdPartyCheckIn
import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.util.RegStepHeader.Companion.errorMsg
import com.booking.util.RegStepHeader.NEW_ACC_STEP_2
import com.booking.util.enums.Currency.US_DOLLAR
import com.booking.util.enums.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired

class AccountCheckInStepDefs: En {
    @Autowired
    private lateinit var guestHeader: GuestHeader
    @Autowired
    private lateinit var accountCheckIn: AccountCheckIn
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var thirdPartyCheckIn: ThirdPartyCheckIn

    init {
        val registerStrategy = mapOf(1 to "Create your account", 2 to "Create password")
        val signInStrategy = mapOf(1 to "Sign in", 2 to "Enter your password")
        val strategies = mapOf("Sign Up" to registerStrategy, "Sign In" to signInStrategy)

        Given("I am in Account {} page") { page: String ->
            accountCheckIn.email().waitUntil(visible, TIMEOUT_SHORT.value)
            val actualHeaderText: String = accountCheckIn.stepHeaderText()
            val expectedHeaderText = strategies.getValue(page).getValue(1)
            assertTrue(actualHeaderText.contentEquals(expectedHeaderText), errorMsg(expectedHeaderText, actualHeaderText))

        }

        Given("I have account created: {}") { email: String ->
            guestHeader.waitWhileReady()
            accountCheckIn.email().waitUntil(visible,TIMEOUT_SHORT.value)
            accountCheckIn.enterEmail(email)
            accountCheckIn.submit()
            accountCheckIn.enterPassword("this_is-secret1!")
            accountCheckIn.submit()
            topHeader.waitWhileReady()
            topHeader.selectCurrency(US_DOLLAR)
        }

        Given("^click on “GET STARTED” button$") {
            accountCheckIn.submit()
        }

        Given("I enter valid password: {}") { passwd: String ->
            accountCheckIn.password().waitUntil(visible, TIMEOUT_SHORT.value)
            assertTrue { accountCheckIn.stepHeaderText().contentEquals(NEW_ACC_STEP_2.stepText) }
            accountCheckIn.createPassword(passwd)
        }

        Given("^click on “Create Account” button$") {
            accountCheckIn.submit()
        }

        Given("^I go to Facebook Sign In$") {
            accountCheckIn.facebook().click()
        }

        Given("^I am in Facebook Sign In page$") {
            thirdPartyCheckIn.waitForFacebookForm()
        }
    }
}