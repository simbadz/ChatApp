package com.tlc.chatapp.presentation.screen.state


sealed class RegisterScreenEvent {

    data class SignUpPhoneNumber(val value: String) : RegisterScreenEvent()
    data class SignUpUsername(val value: String) : RegisterScreenEvent()
    data class SignUpName(val value: String) : RegisterScreenEvent()
    object SignUp : RegisterScreenEvent()
}
