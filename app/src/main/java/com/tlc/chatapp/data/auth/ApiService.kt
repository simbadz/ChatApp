package com.tlc.chatapp.data.auth

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("send-auth-code")
    suspend fun sendPhone(@Body request: PhoneRequest)  //Отправка номера

    @POST("check-auth-code")
    suspend fun verifyCode(@Body request: VerifyRequest) : TokenRequest // отправка номера и кода

    @POST("register")
    suspend fun register(@Body request: Register) : TokenRequest

    @GET("check-jwt")
    suspend fun authenticate(@Header ("authenticate") token: String)

}

data class PhoneRequest(val phone: String)
data class VerifyRequest(val phone: String, val code: String)
data class Register(val phone: String, val name: String,val username: String)

data class ApiResponse(val message: String)
data class TokenRequest(
    val access_token: String,
    val refresh_token: String,
    val user_id: String
)
//data class TokenRequest(val token: String)