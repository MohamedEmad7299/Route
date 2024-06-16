package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ug.route.data.database.entities.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavouriteProduct(favouriteEntity : FavouriteEntity)

    @Delete
    suspend fun deleteFavouriteProduct(favouriteEntity : FavouriteEntity)

    @Query("SELECT * FROM FAVOURITE_TABLE")
    fun getAllFavouriteProducts(): Flow<List<FavouriteEntity>>
}

