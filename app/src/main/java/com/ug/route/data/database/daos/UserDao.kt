package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ug.route.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity : UserEntity)

    @Update
    suspend fun updateUser(userEntity : UserEntity)

    @Query("SELECT * FROM USER_TABLE WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
}