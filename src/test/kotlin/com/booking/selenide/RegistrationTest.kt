package com.booking.selenide

import com.booking.Config
import com.booking.SessionManager
import com.booking.pages.headers.TopHeader
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Config::class])
class RegistrationTest {
    @Autowired
    lateinit var session: SessionManager
    @Autowired
    lateinit var topHeader: TopHeader
    @Value("\${url}")
    lateinit var url: String

   @Test
    fun createNewAccount() {
        session.setup(url)
        topHeader.waitWhileReady()
        val wtf = topHeader.getSelectedCurrency()
    }
}