package com.booking.util

enum class Timeout constructor(val value: Long) {
    TIMEOUT_LONG(3000),
    TIMEOUT_SHORT(1000)
}