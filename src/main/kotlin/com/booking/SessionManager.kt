package com.booking

import com.codeborne.selenide.Selenide
import org.springframework.stereotype.Component

@Component
class SessionManager {
    fun setup(url: String) {
        Selenide.clearBrowserCookies()
        Selenide.open(url)
    }
}