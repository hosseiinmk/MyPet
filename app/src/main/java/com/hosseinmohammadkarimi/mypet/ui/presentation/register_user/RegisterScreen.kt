package com.hosseinmohammadkarimi.mypet.ui.presentation.register_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hosseinmohammadkarimi.mypet.util.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onPopBackStack: () -> Unit,
    viewModel: RegisterUserViewModel = hiltViewModel()
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.PopBackStack -> onPopBackStack()
                is UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(
                        RegisterUserEvent.OnRegisterUserClick
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.username,
                onValueChange = {
                    viewModel.onEvent(
                        RegisterUserEvent.OnUsernameChange(it)
                    )
                },
                placeholder = { Text(text = "نام خود را وارد کنید") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.phoneNumber,
                onValueChange = {
                    viewModel.onEvent(
                        RegisterUserEvent.OnPhoneNumberChange(it)
                    )
                },
                placeholder = { Text(text = "شماره موبایل خود را وارد کنید") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
        }
    }
}