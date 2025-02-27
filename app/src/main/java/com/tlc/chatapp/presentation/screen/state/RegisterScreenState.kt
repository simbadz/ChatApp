package com.tlc.chatapp.presentation.screen.state


//sealed class RegisterScreenEvent {
//    data class UserNameUpdated(val newUserName: String) : RegisterScreenEvent()
//    data class PhoneUpdated(val newPhone: String) : RegisterScreenEvent()
//    data class PasswordUpdated(val newPassword: String) : RegisterScreenEvent()
//}

sealed class RegisterScreenEvent {

    data class SignUpUsernameChanged(val value: String) : RegisterScreenEvent()
    data class SignUpPasswordChanged(val value: String) : RegisterScreenEvent()
    data class SignUpNameChanged(val value: String) : RegisterScreenEvent()
    object SignUp : RegisterScreenEvent()
}

data class RegisterScreenState(
    val phone: String = "",
    val username: String = "",
    val password: String = ""
)
