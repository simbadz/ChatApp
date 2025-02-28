package com.tlc.chatapp.data.auth

data class AuthState(
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpPassword: String = "",
    val signInPhoneNumber: String = "",
    val signUpPhoneNumber: String = "",
    val signInPassword: String = "",
    val signUpName: String = ""
)
