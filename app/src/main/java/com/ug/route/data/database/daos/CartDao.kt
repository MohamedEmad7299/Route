package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ug.route.data.database.entities.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cartEntity: CartEntity)

    @Delete
    suspend fun deleteCartItem(cartEntity: CartEntity)

    @Query("SELECT * FROM CART_TABLE")
    fun getAllCartItems(): Flow<List<CartEntity>>

    @Update
    suspend fun updateCartItem(cartEntity: CartEntity)

    @Query("DELETE FROM CART_TABLE")
    suspend fun deleteAllCartItems()

    @Query("SELECT * FROM CART_TABLE WHERE id = :itemId")
    fun getCartItemById(itemId: Long): Flow<CartEntity>
}