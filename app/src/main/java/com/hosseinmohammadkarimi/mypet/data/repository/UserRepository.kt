package com.hosseinmohammadkarimi.mypet.data.repository

import com.hosseinmohammadkarimi.mypet.data.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun registerUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    fun getUsers() : Flow<List<User>>
}