package com.booking.dataProvider

import io.github.serpro69.kfaker.Faker
import org.springframework.stereotype.Component

@Component
class DataProvider {
    private val faker = Faker()

    fun generateEmail(): String {
        return faker.internet.safeEmail()
    }
}