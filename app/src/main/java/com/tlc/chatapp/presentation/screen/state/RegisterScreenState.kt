package com.tlc.chatapp.presentation.screen.state


sealed class RegisterScreenEvent {
    data class UserNameUpdated(val newUserName: String) : RegisterScreenEvent()
    data class PhoneUpdated(val newPhone: String) : RegisterScreenEvent()
    data class PasswordUpdated(val newPassword: String) : RegisterScreenEvent()
}

data class RegisterScreenState(
    val phone: String = "",
    val username: String = "",
    val password: String = ""
)
