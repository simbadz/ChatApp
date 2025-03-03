package com.tlc.chatapp.data.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject


interface TokenManager {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String
    fun clearTokens()
    fun isLoggedIn(): Boolean
}

class TokenManagerImpl @Inject constructor(
    private val prefs: SharedPreferences
) : TokenManager {

    companion object {
        private const val ACCESS_TOKEN = "access_token"
    }

    override fun saveAccessToken(token: String) {
        prefs.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String {
        return prefs.getString(ACCESS_TOKEN, "") ?: ""
    }

    override fun clearTokens() {
        prefs.edit().remove(ACCESS_TOKEN).apply()
    }

    override fun isLoggedIn(): Boolean {
        return getAccessToken().isNotEmpty()
    }
}