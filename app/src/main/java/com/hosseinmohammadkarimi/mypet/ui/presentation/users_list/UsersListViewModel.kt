package com.hosseinmohammadkarimi.mypet.ui.presentation.users_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinmohammadkarimi.mypet.data.model.User
import com.hosseinmohammadkarimi.mypet.data.repository.UserRepository
import com.hosseinmohammadkarimi.mypet.util.Routes
import com.hosseinmohammadkarimi.mypet.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getUsers()

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedUser: User? = null

    fun onEvent(event: UsersListEvent) {
        when (event) {
            is UsersListEvent.OnDeleteUser -> {
                viewModelScope.launch {
                    deletedUser = event.user
                    repository.deleteUser(event.user)
                    sendUIEvent(
                        UIEvent.ShowSnackbar(
                            message = "کاربر حذف شد",
                            action = "برگرداندن"
                        )
                    )
                }
            }
            is UsersListEvent.OnRegisterUserClicked -> {
                sendUIEvent(UIEvent.Navigate(Routes.REGISTER_USER))
            }
            is UsersListEvent.OnDoneClick -> {
                viewModelScope.launch {
                    repository.registerUser(event.user)
                }
            }
            is UsersListEvent.OnUndoClicked -> {
                viewModelScope.launch {
                    deletedUser?.let { user ->
                        repository.registerUser(user)
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