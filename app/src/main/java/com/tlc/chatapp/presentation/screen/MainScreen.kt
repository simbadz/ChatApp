package com.tlc.chatapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tlc.chatapp.presentation.navigation.Screen
import com.tlc.chatapp.presentation.screen.viewModel.LoginScreenViewModel

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MainScreen"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onNavigateTo = TODO()
    )
}