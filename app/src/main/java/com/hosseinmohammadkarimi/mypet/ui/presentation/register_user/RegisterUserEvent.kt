package com.hosseinmohammadkarimi.mypet.ui.presentation.register_user

sealed class RegisterUserEvent {

    data class OnUsernameChange(val username: String) : RegisterUserEvent()
    data class OnPhoneNumberChange(val phoneNumber: String) : RegisterUserEvent()
    object OnRegisterUserClick : RegisterUserEvent()
}