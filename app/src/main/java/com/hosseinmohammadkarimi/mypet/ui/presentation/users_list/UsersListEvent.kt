package com.hosseinmohammadkarimi.mypet.ui.presentation.users_list

import com.hosseinmohammadkarimi.mypet.data.model.User

sealed class UsersListEvent {

    data class OnDeleteUser(val user: User) : UsersListEvent()
    data class OnDoneClick(val user: User, val isDone: Boolean) : UsersListEvent()
    object OnRegisterUserClicked : UsersListEvent()
    object OnUndoClicked : UsersListEvent()
}