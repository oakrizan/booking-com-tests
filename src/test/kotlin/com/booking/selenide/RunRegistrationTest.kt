package com.booking.selenide

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.github.serpro69.kfaker.Faker
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty"],
//        extraGlue = ["src/test/kotlin/com/booking/selenide/steps/CommonSteps.kt"],
        features = ["src/test/resources/features/accountCreation.feature"])
//        features = ["src/test/resources/features/test.feature"])
class RunRegistrationTest {

}