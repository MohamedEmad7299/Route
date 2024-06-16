package com.ug.route.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "USER_TABLE")
data class UserEntity(

    @PrimaryKey(autoGenerate = true) val id : Long,
    val name : String,
    val email : String,
    val password : String,
    val phone : String,
    val address : String
)