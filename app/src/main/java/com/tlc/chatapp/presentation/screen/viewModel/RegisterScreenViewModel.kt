package com.tlc.chatapp.presentation.screen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.chatapp.data.auth.AuthRepository
import com.tlc.chatapp.data.auth.AuthResult
import com.tlc.chatapp.data.auth.AuthState
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.SignUpUsernameChanged -> {
                state = state.copy(signUpUsername = event.value)
            }
            is RegisterScreenEvent.SignUpPasswordChanged -> {
                state = state.copy(signUpPassword = event.value)
            }
            is RegisterScreenEvent.SignUpNameChanged -> {
                state = state.copy(signUpName = event.value)
            }
            RegisterScreenEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.register(
                phone = state.signUpUsername,
                name = state.signUpPassword,
                username = state.signUpName
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}

