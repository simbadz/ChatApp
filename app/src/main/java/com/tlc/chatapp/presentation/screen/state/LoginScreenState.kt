package com.tlc.chatapp.presentation.screen.state


sealed class LoginScreenEvent {
    data class UserNameUpdated(val newUserName: String) : LoginScreenEvent()
    data class PhoneUpdated(val newPhone: String) : LoginScreenEvent()
    data class PasswordUpdated(val newPassword: String) : LoginScreenEvent()
}

data class LoginScreenState(
    val phone: String = "",
    val username: String = "",
    val password: String = ""
)
