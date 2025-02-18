package com.tlc.chatapp.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("send-auth-code")
    suspend fun sendPhone(@Body request: PhoneRequest) : ApiResponse

    @POST("check-auth-code")
    suspend fun verifyCode(@Body request: VerifyRequest) : TokenRequest

    @POST("register")
    suspend fun register(@Body request: Register) : TokenRequest
}

data class PhoneRequest(val phone: String)
data class VerifyRequest(val phone: String, val name: String)
data class Register(val phone: String, val name: String,val username: String)

data class ApiResponse(val message: String)
data class TokenRequest(val acess_token: String, val refresh_token: String)