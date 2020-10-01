package com.booking.util

enum class Timeout constructor(val value: Long) {
    TIMEOUT_LONG(10000),
    TIMEOUT_SHORT(2000)
}