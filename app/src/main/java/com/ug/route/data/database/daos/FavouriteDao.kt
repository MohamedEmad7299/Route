package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.ug.route.data.database.entities.FavouriteEntity

@Dao
interface FavouriteDao {

    @Insert
    suspend fun insertFavouriteProduct(favouriteEntity : FavouriteEntity)

    @Delete
    suspend fun deleteFavouriteProduct(favouriteEntity : FavouriteEntity)
}