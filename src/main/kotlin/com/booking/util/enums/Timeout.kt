package com.booking.util.enums

enum class Timeout constructor(val value: Long) {
    TIMEOUT_LONG(4000),
    TIMEOUT_SHORT(1000)
}