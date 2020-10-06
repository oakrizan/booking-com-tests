package com.booking.selenide

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.github.serpro69.kfaker.Faker
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty"],
        features = ["src/test/resources/features/accountCreation.feature",
            "src/test/resources/features/navigation.feature"])
class RunTests {

}