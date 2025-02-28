package com.tlc.chatapp.data.auth

import android.content.SharedPreferences
import coil.network.HttpException
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun sendPhone(phone: String): AuthResult<Unit> {
        return try {
            api.sendPhone(
                request = PhoneRequest(
                    phone = phone
                )
            )
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.hashCode() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun verifyCode(phone: String, code: String): AuthResult<Unit> {
        return try {
            val response = api.verifyCode(
                request = VerifyRequest(
                    phone = phone,
                    code = code
                )
            )
            prefs.edit()
                .putString("jwt", "Bearer ${response.refresh_token}")
                .apply()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.hashCode() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun register(phone: String, name: String, username: String): AuthResult<Unit> {
        return try {
            api.register(
                request = Register(
                    phone = phone,
                    name = name,
                    username = username
                )
            )
            verifyCode(phone, name)
        } catch (e: HttpException) {
            if (e.hashCode() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate(token)
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.hashCode() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}