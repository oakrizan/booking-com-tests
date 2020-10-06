package com.booking.util

enum class RegStepHeader constructor(var stepText: String) {
    NEW_ACC_STEP_1("Create your account"),
    NEW_ACC_STEP_2("Create password"),
    SIGN_IN_STEP_1("Sign in");
    companion object {
        fun errorMsg(stepHeader: RegStepHeader, actual: String): String {
            return "Header text should be ${ stepHeader.stepText}, but was $actual"
        }
    }
}