package com.tlc.chatapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tlc.chatapp.R
import com.tlc.chatapp.presentation.component.StyledButton
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.state.LoginScreenState
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import com.tlc.chatapp.presentation.screen.state.RegisterScreenState
import com.tlc.chatapp.presentation.screen.viewModel.RegisterScreenViewModel

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {},
    phone: String = ""
) {
    val viewModel = viewModel<RegisterScreenViewModel>()
    viewModel.onEvent(RegisterScreenEvent.SignUpUsernameChanged(phone ))
    RegisterView(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun RegisterView(
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit = {},
    onNavigateTo: (Screen) -> Unit = {}
)  {
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
            value = state.phone,
            enabled = false,
            onValueChange = { newValue ->
                onEvent(RegisterScreenEvent.SignUpUsernameChanged(""))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_phone_number))
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = state.username,
            onValueChange = { newValue ->
                onEvent(RegisterScreenEvent.SignUpNameChanged(newValue))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.name))
            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = state.password,
            onValueChange = { newValue ->
                onEvent(RegisterScreenEvent.SignUpPasswordChanged(newValue)) },
            placeholder = {
                Text(text = stringResource(id = R.string.username))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        StyledButton(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                onNavigateTo(Screen.Login)
            }
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 19.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterView()
}