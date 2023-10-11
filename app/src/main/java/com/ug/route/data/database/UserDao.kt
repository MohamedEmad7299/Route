package com.ug.route.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ug.route.data.database.entities.UserData

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userData : UserData)

    @Update
    suspend fun updateUser(userData : UserData)

    @Query("SELECT * FROM USER_TABLE WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserData?
}