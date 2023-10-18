package com.ug.route.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORY_TABLE")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true) val id : Long,
    val name : String,
    val image : String
)