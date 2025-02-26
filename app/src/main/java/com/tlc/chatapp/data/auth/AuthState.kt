package com.tlc.chatapp.data.auth

data class AuthState (
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpPassword: String = "",
    val signInUsername: String = "",
    val signInPassword: String = "",
    val signUpName: String = ""
)




//sealed class AuthState {
//    object Authorized: AuthState()
//    object NotAuthorized: AuthState()
//    object Initial: AuthState()
//}