package com.hosseinmohammadkarimi.mypet.ui.presentation.register_user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinmohammadkarimi.mypet.data.model.User
import com.hosseinmohammadkarimi.mypet.data.repository.UserRepository
import com.hosseinmohammadkarimi.mypet.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var username by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterUserEvent) {
        when (event) {
            is RegisterUserEvent.OnUsernameChange -> {
                username = event.username
            }
            is RegisterUserEvent.OnPhoneNumberChange -> {
                phoneNumber = event.phoneNumber
            }
            is RegisterUserEvent.OnRegisterUserClick -> {
                viewModelScope.launch {
                    if (username.isNotBlank() && phoneNumber.isNotBlank()) {
                        repository.registerUser(
                            user = User(
                                username = username,
                                phoneNumber = phoneNumber,
                            )
                        )
                        sendUIEvent(UIEvent.PopBackStack)
                    } else {
                        sendUIEvent(
                            UIEvent.ShowSnackbar(
                                message = "لطفا تمامی فیلد ها را پر کنید"
                            )
                        )
                        return@launch
                    }
                }
            }
        }
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}