package com.hosseinmohammadkarimi.mypet.util

sealed class UIEvent {

    object PopBackStack : UIEvent()
    data class Navigate(val rout: String): UIEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ) : UIEvent()
}