package com.ug.route.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ug.route.data.database.daos.CartDao
import com.ug.route.data.database.daos.FavouriteDao
import com.ug.route.data.database.daos.UserDao
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.FavouriteEntity
import com.ug.route.data.database.entities.UserEntity

@Database(entities = [UserEntity::class, FavouriteEntity::class , CartEntity::class], version = 1)
abstract class RouteDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun favouriteDao() : FavouriteDao
    abstract fun cartDao() : CartDao

    companion object{

        private const val DATABASE_NAME = "RouteDatabase"

        @Volatile private var instance : RouteDatabase? = null

        fun getInstance(context: Context) : RouteDatabase{
            return instance ?: synchronized(this){ buildDatabase(context).also { instance = it } }
        }
        private fun buildDatabase(context: Context) : RouteDatabase{
            return Room.databaseBuilder(
                context,
                RouteDatabase::class.java,
                DATABASE_NAME).build()
        }
    }
}