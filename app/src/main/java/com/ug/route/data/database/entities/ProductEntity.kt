package com.ug.route.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCT_TABLE")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true) val id : Long,
    val isFavourite: Boolean,
    val imageResource: Int,
    val name: String,
    val price: Int,
    val review: String,
)