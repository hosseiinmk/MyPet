package com.hosseinmohammadkarimi.mypet.data.repository

import com.hosseinmohammadkarimi.mypet.data.database.UserDao
import com.hosseinmohammadkarimi.mypet.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImp(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun getUsers(): Flow<List<User>> =
        userDao.getUsers()
}