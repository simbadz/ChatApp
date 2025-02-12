package com.tlc.chatapp.presentation.screen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {
    var number by mutableStateOf("")
        private set

    var code by mutableStateOf("")
        private set

    fun updateNumber(number: String) {
        this.number = number
    }

    fun enterCode(number: String) {
        this.code = code
    }
}