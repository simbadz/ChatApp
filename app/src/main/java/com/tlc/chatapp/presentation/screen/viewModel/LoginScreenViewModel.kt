package com.tlc.chatapp.presentation.screen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.chatapp.data.auth.AuthRepository
import com.tlc.chatapp.data.auth.AuthResult
import com.tlc.chatapp.data.auth.PhoneRequest
import com.tlc.chatapp.data.auth.RetrofitInstance
import com.tlc.chatapp.presentation.screen.state.LoginScreenState
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//@HiltViewModel
//class LoginScreenViewModel @Inject constructor(
//    private val repository: AuthRepository
//) : ViewModel() {
//    var state by mutableStateOf(LoginScreenState())
//
//        private val resultChannel = Channel<AuthResult<Unit>> ()
//    val authResults = resultChannel.receiveAsFlow()
//
//    init {
//
//    }
//
//    fun onEvent(event: RegisterScreenEvent) {
//        when (event) {
//            is RegisterScreenEvent.UserNameUpdated -> {
//                state = state.copy(username = event.newUserName)
//            }
//
//            is RegisterScreenEvent.PhoneUpdated -> {
//                state = state.copy(phone = event.newPhone)
//            }
//
//            is RegisterScreenEvent.PasswordUpdated -> {
//                state = state.copy(password = event.newPassword)
//            }
//        }
//    }
//}


class LoginScreenViewModel: ViewModel() {
    var number by mutableStateOf("")
        private set

    var code by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
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




