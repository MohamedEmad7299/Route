package com.ug.route.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CART_TABLE")
data class CartEntity(

    @PrimaryKey val id : Long,
    val name : String,
    val imageResource : Int,
    val price : Int,
    val colorValue : Int,
    val colorName: String,
    val count : Int
)
