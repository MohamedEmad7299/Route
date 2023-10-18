package com.ug.route.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ug.route.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Transaction
    suspend fun replaceCategories(categories: List<CategoryEntity>) {
        clearCategories()
        insertCategories(categories)
    }
    
    @Insert
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM CATEGORY_TABLE")
    suspend fun clearCategories()

    @Query("SELECT * FROM CATEGORY_TABLE")
    fun getCategories(): Flow<List<CategoryEntity>>
}