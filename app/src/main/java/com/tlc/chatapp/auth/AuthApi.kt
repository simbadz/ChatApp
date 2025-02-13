package com.tlc.chatapp.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/phone")
    suspend fun sendPhone(@Body request: PhoneRequest) : ApiResponse

    @POST("auth/phone")
    suspend fun verifyCode(@Body request: VerifyRequest) : TokenRequest
}

data class PhoneRequest(val phone: String)
data class VerifyRequest(val phone: String, val code: String)
data class ApiResponse(val message: String)
data class TokenRequest(val acess_token: String, val refresh_token: String)