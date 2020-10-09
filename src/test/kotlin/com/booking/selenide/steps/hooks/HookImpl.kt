package com.booking.selenide.steps.hooks

import com.codeborne.selenide.logevents.SelenideLogger
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import io.qameta.allure.Allure
import io.qameta.allure.AllureLifecycle
import io.qameta.allure.selenide.AllureSelenide

class HookImpl: En {
    private var lifecycle: AllureLifecycle = Allure.getLifecycle()
    init {
        Before { _: Scenario ->
            SelenideLogger.addListener("AllureSelenide",
                    AllureSelenide()
                            .screenshots(true)
                            .savePageSource(false)
                            .includeSelenideSteps(true))
        }
    }
}