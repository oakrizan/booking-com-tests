package com.booking

import com.booking.pages.headers.TopHeader
import com.codeborne.selenide.Selenide
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SessionManager {
    @Autowired
    lateinit var header: TopHeader

    fun setup(url: String) {
        Selenide.clearBrowserCookies()
        Selenide.open(url)
    }
}