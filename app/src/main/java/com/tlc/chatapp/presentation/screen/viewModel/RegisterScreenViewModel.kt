package com.tlc.chatapp.presentation.screen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import com.tlc.chatapp.presentation.screen.state.RegisterScreenState

class RegisterScreenViewModel : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        when(event) {
            is RegisterScreenEvent.UserNameUpdated -> {
                state = state.copy(username = event.newUserName)
            }

            is RegisterScreenEvent.PhoneUpdated -> TODO()
        }
    }
}