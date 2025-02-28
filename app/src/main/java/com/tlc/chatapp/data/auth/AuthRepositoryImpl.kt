package com.tlc.chatapp.data.auth

import android.content.SharedPreferences


class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {

    override suspend fun sendPhone(phone: String): AuthResult<Unit> {
        return try {
            api.sendPhone(PhoneRequest(phone = phone))
            AuthResult.Authorized()
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun verifyCode(phone: String, code: String): AuthResult<Unit> {
        return try {
            val response = api.verifyCode(VerifyRequest(phone = phone, code = code))
            prefs.edit()
                .putString("jwt", "Bearer ${response.token}")
                .apply()
            AuthResult.Authorized()
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun register(phone: String, name: String, username: String): AuthResult<Unit> {
        return try {
            api.register(Register(phone = phone, name = name, username = username))
            verifyCode(phone, "")
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}