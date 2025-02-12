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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tlc.chatapp.R
import com.tlc.chatapp.presentation.component.StyledButton
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import com.tlc.chatapp.presentation.screen.state.RegisterScreenState
import com.tlc.chatapp.presentation.screen.viewModel.RegisterScreenViewModel
import org.w3c.dom.Text

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    val viewModel = viewModel<RegisterScreenViewModel>()
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
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = androidx.compose.ui.Modifier
                .padding(top = 150.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 150.dp),
            enabled = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = state.username,
            onValueChange = { newValue ->
                onEvent(RegisterScreenEvent.UserNameUpdated(newValue))
            },
//            placeholder = {
//                Text(text = stringResource(id = R.string.enter_sms))
//            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = state.username,
            onValueChange = { newValue ->
                onEvent(RegisterScreenEvent.UserNameUpdated(newValue))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.username))
            }
        )
        StyledButton(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = {onNavigateTo(Screen.Register)}
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.register),
                fontSize = 19.sp,
                color = Color.Black,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterView ()
}