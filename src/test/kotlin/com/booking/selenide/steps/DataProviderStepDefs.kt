package com.booking.selenide.steps

import com.booking.dataProvider.DataProvider
import com.booking.pages.access.AccountCheckIn
import com.booking.pages.main.AccountSettings
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired

class DataProviderStepDefs: En {
    @Autowired
    private lateinit var accountCheckIn: AccountCheckIn
    @Autowired
    private lateinit var dataProvider: DataProvider
    @Autowired
    private lateinit var accountSettings: AccountSettings

    private var email: String = ""

    init {
        Given("^I enter valid user email$") {
            email = dataProvider.generateEmail()
            accountCheckIn.enterEmail(email)
        }

        And("^correct value is prefilled in email verification placeholder //based on registered email") {
            assertTrue(accountSettings.emailToConfirm().contentEquals(email))
        }
    }
}