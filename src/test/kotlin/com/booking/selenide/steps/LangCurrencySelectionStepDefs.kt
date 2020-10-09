package com.booking.selenide.steps

import com.booking.pages.headers.GuestHeader
import com.booking.pages.headers.TopHeader
import com.booking.util.enums.Currency
import com.booking.util.enums.Language
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired

class LangCurrencySelectionStepDefs: En {
    @Autowired
    private lateinit var topHeader: TopHeader
    @Autowired
    private lateinit var guestHeader: GuestHeader
    
    init {
        //Main Page Top Header
        Given("select {} language") { lang: Language ->
            topHeader.waitWhileReady()
            topHeader.selectLanguage(lang)
        }

        Given("select {} currency") { currency: Currency ->
            topHeader.waitWhileReady()
            topHeader.selectCurrency(currency)
        }

        //Account Guest Header
        Given("select {} language on Account Page") { lang: Language ->
            guestHeader.waitWhileReady()
            guestHeader.selectLanguage(lang)
        }
    }
}