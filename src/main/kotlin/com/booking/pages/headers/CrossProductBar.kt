package com.booking.pages.headers

import com.booking.util.Timeout.TIMEOUT_SHORT
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.stereotype.Component

@Component
class CrossProductBar {
    private val wrapper: SelenideElement = `$`("#cross-product-bar")
    private val selectedProduct: SelenideElement = `$`(".selected")
    private val products: ElementsCollection = `$$`(".xpb__link")

    fun waitWhileReady() {
        wrapper.waitUntil(visible, TIMEOUT_SHORT.value)
        assertEquals(products.count(), equals(5))
    }

    fun selected(): SelenideElement {
        return selectedProduct
    }
}