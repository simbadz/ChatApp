package com.tlc.chatapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tlc.chatapp.R
import com.tlc.chatapp.presentation.component.StyledButton
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.viewModel.LoginScreenViewModel

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: LoginScreenViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
//            fontFamily = fontFamily.,
            modifier = Modifier
                .padding(top = 150.dp),
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
            modifier = Modifier.padding(top = 150.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = viewModel.number,
            onValueChange = viewModel::updateNumber,
            placeholder = {
                Text(text = stringResource(id = R.string.enter_phone_number))
            }
        )

        StyledButton(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = {}
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.next),
                fontSize = 19.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}