package com.hosseinmohammadkarimi.mypet.di

import android.app.Application
import androidx.room.Room
import com.hosseinmohammadkarimi.mypet.data.database.UserDatabase
import com.hosseinmohammadkarimi.mypet.data.repository.UserRepository
import com.hosseinmohammadkarimi.mypet.data.repository.UserRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application): UserDatabase =
        Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            "users_database"
        ).build()

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository =
        UserRepositoryImp(db.userDao())
}