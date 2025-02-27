package com.tlc.chatapp.presentation.screen.state


sealed class RegisterScreenEvent {

    data class SignUpUsernameChanged(val value: String) : RegisterScreenEvent()
    data class SignUpPasswordChanged(val value: String) : RegisterScreenEvent()
    data class SignUpNameChanged(val value: String) : RegisterScreenEvent()
    object SignUp : RegisterScreenEvent()
}
