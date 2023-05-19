package com.hosseinmohammadkarimi.mypet.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    @ColumnInfo("phone_number")
    val phoneNumber: String
)
