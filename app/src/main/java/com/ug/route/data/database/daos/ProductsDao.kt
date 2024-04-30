package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ug.route.data.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao{

    @Insert
    suspend fun insertProducts(products : List<ProductEntity>)
    @Update
    suspend fun updateProduct(productEntity: ProductEntity)
    @Query("SELECT * FROM PRODUCT_TABLE")
    fun getAllProducts(): Flow<List<ProductEntity>>
}