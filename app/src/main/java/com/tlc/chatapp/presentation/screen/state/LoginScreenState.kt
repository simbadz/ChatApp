package com.tlc.chatapp.presentation.screen.state


sealed class LoginScreenEvent {
    data class SignInUsernameChanged(val value: String): LoginScreenEvent()
    data class SignInPasswordChanged(val value: String): LoginScreenEvent()
    object SignIn: LoginScreenEvent()
}

