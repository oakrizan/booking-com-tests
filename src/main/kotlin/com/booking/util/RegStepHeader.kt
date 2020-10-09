package com.booking.util

enum class RegStepHeader constructor(var stepText: String) {
    NEW_ACC_STEP_1("Create your account"),
    NEW_ACC_STEP_2("Create password"),
    SIGN_IN_STEP_1("Sign in"),
    SIGN_IN_STEP_2("Enter your password");

    companion object {
        //TODO - refactor - no emun class needed, errorMsg to util
//        fun errorMsg(stepHeader: RegStepHeader, actual: String): String {
        fun errorMsg(expected: String, actual: String): String {
            return "Header text should be $expected, but was $actual"
        }
    }
}