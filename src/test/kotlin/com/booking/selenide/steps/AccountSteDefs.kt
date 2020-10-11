package com.booking.selenide.steps

import com.booking.pages.headers.TopHeader
import com.booking.pages.main.AccountDashboard
import com.booking.pages.main.AccountSettings
import com.booking.util.enums.ProfileMenuCategory.ACCOUNT
import com.booking.util.enums.ProfileMenuCategory.DASHBOARD
import com.codeborne.selenide.ex.ElementNotFound
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class AccountSteDefs: En {
    @Autowired
    private lateinit var accountSettings: AccountSettings
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var dashboard: AccountDashboard

    init {
        And("^I click on “Manage Account” button under account menu$") {
            if (topHeader.profileButtonBui().exists()) {
                topHeader.openProfileShort(ACCOUNT)
            } else {
                topHeader.openProfileLong(DASHBOARD)
            }
        }

        Then("^“Account Settings” page is opened$") {
            try {
                accountSettings.waitWhileReady()
            } catch (e: ElementNotFound) {
                dashboard.waitWhileReady()
            }
        }
    }
}