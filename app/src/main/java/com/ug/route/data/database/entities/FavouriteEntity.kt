package com.ug.route.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVOURITE_TABLE")
data class FavouriteEntity(

    @PrimaryKey(autoGenerate = true) val id : Long,
    val name : String,
    val imageURL : String,
    val price : Int,
    val colorValue : Int,
    val colorName: String
)