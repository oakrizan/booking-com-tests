package com.booking.util.enums

enum class ProfileMenuCategory constructor(var text: String){
    //New UI
    ACCOUNT ("Manage account"),
    TRIPS ("Trips"),
    GENIUS ("Genius"),
    WHISHLISTS ("Wish lists"),
    OUT ("Sign out"),

    //Old UI
    DASHBOARD ("My Dashboard"),
    BOOKINGS ("Bookings"),
    GENIUS_LOYALTY ("Genius Loyalty Program"),
    REVIEWS ("My reviews"),
    GET_APP ("Get the app"),
    CUST_SERVICE ("Contact Customer Service"),
    SETTINGS ("Settings"),
    COMMUNITIES ("Travel communities"),
    SIGN_OUT ("Sign out")
}