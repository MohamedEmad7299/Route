package com.ug.route.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.ug.route.data.database.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user : User)

    @Update
    suspend fun updateUser(user : User)
}