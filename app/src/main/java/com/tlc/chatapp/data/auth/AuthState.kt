package com.tlc.chatapp.data.auth

sealed class AuthState {
    object Authorized: AuthState()


    object NotAuthorized: AuthState()


    object Initial: AuthState()

}