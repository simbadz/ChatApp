package com.tlc.chatapp.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tlc.chatapp.R
import com.tlc.chatapp.data.auth.AuthResult
import com.tlc.chatapp.presentation.component.StyledButton
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import com.tlc.chatapp.presentation.screen.viewModel.RegisterScreenViewModel

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    phone: String = ""
) {
    val state = viewModel.state
    val context = LocalContext.current

    // Set phone number first
    LaunchedEffect(Unit) {
        viewModel.onEvent(RegisterScreenEvent.SignUpPhoneNumber(phone))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 150.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 150.dp),
            value = state.signUpPhoneNumber,
            enabled = false,
            onValueChange = { },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_phone_number))
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = state.signUpName,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.SignUpName(it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.name))
            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = state.signUpUsername,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.SignUpUsername(it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.username))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        StyledButton(
            modifier = Modifier.padding(top = 20.dp),
            onClick = { viewModel.onEvent(RegisterScreenEvent.SignUp) }
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 19.sp,
                color = Color.Black
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    RegisterView()
//}