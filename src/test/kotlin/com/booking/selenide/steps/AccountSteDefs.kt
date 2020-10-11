package com.booking.selenide.steps

import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountSettings
import com.booking.util.SessionManager
import com.booking.util.enums.ProfileMenuCategory
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class AccountSteDefs: En {
    @Autowired
    private lateinit var sessionManager: SessionManager
    @Autowired
    private lateinit var accountSettings: AccountSettings
    @Autowired
    private lateinit var topHeader: TopHeader

    init {
        And("^I click on “Manage Account” button under account menu$") {
            topHeader.openProfile(ProfileMenuCategory.ACCOUNT)
        }

        Then("^“Account Settings” page is opened$") {
            accountSettings.waitWhileReady()
        }
    }
}