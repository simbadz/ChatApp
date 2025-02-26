package com.tlc.chatapp.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestinationDsl
import androidx.navigation.Navigator
import com.tlc.chatapp.R
import com.tlc.chatapp.data.auth.AuthRepository
import com.tlc.chatapp.data.auth.AuthResult
import com.tlc.chatapp.presentation.component.StyledButton
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.state.LoginScreenEvent
import com.tlc.chatapp.presentation.screen.state.LoginScreenState
import com.tlc.chatapp.presentation.screen.state.RegisterScreenEvent
import com.tlc.chatapp.presentation.screen.viewModel.LoginScreenViewModel
import com.tlc.chatapp.presentation.ui.theme.PrimaryPinkBlended
import com.tlc.chatapp.presentation.ui.theme.PrimaryYellow
import com.tlc.chatapp.presentation.ui.theme.PrimaryYellowLight

@Composable
fun LoginScreen(
//    state: LoginScreenState = LoginScreenState(),
    onNavigateTo: (Screen) -> Unit = {},
    onEvent: (LoginScreenEvent) -> Unit = {},
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    onNavigateTo(Screen.Main)
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You are not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    0f to PrimaryPinkBlended,
//                    0.6f to PrimaryYellowLight,
//                    1f to PrimaryYellow
//                )
//            )
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 120.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp
        )
        Image(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(100.dp),
            painter = painterResource(id = R.drawable.login_app_image),
            contentDescription = "Chat app login image"
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 60.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = state.signInUsername,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.SignInUsernameChanged(it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_phone_number))
            }

        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = state.signInPassword,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.SignInPasswordChanged(it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_sms))
            }
        )

        StyledButton(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = { viewModel.onEvent(LoginScreenEvent.SignIn) }
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.next),
                fontSize = 19.sp,
                color = Color.Black,
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable { onNavigateTo(Screen.Register) },
            text = stringResource(id = R.string.register),
            fontSize = 14.sp
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}