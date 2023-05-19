package com.hosseinmohammadkarimi.mypet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hosseinmohammadkarimi.mypet.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
}