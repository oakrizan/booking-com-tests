package com.booking.util

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class ElementHelper {
    fun findByText(coll: ElementsCollection, text: String): SelenideElement {
        return coll.asSequence()
                .filter { it.text().contains(text, ignoreCase = true) }
                .first()
    }
}