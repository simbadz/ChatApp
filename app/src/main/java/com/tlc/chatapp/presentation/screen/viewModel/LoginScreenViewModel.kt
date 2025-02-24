package com.tlc.chatapp.presentation.screen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.chatapp.data.auth.PhoneRequest
import com.tlc.chatapp.data.auth.RetrofitInstance
import kotlinx.coroutines.launch


class LoginScreenViewModel: ViewModel() {
    var number by mutableStateOf("")
        private set

    var code by mutableStateOf("")
        private set

    fun updateNumber(number: String) {
        this.number = number
    }

    fun updateCode(code: String) {
        this.code = code
    }


    private val api = RetrofitInstance.api

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState





    fun sendPhone(phone: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = api.sendPhone(PhoneRequest(phone))
                if (response.phone == "SMS sent") {
                    _authState.value = AuthState.CodeSent(phone)
                } else {
                    _authState.value = AuthState.Error("Ошибка отправки: ${response.message}")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Ошибка сети: ${e.message}")
            }
        }
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class CodeSent(val phone: String) : AuthState()
        data class Error(val message: String) : AuthState()
        object Success : AuthState()
    }
}



