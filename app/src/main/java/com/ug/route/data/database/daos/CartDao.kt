package com.ug.route.data.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ug.route.data.database.entities.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cartEntity: CartEntity)

    @Delete
    suspend fun deleteCartItem(cartEntity: CartEntity)

    @Query("SELECT * FROM CART_TABLE")
    fun getAllCartItems(): Flow<List<CartEntity>>

}