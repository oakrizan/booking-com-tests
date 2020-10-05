package com.booking.selenide

import com.booking.Config
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(classes = [Config::class])
class CucumberSpringConfiguration {
}