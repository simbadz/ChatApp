package com.tlc.chatapp.presentation.screen.state


sealed class LoginScreenEvent {
    data class SignInPhoneNumberChanged(val value: String): LoginScreenEvent()
    data class SignInPasswordChanged(val value: String): LoginScreenEvent()
    object SignIn: LoginScreenEvent()
}

