package com.tlc.chatapp.data.auth

interface AuthRepository  {
    suspend fun sendPhone(phone: String): AuthResult<Unit>
    suspend fun verifyCode(phone: String, code: String): AuthResult<Unit>
    suspend fun register(phone: String, name: String, username: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}