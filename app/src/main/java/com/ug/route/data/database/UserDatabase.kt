package com.ug.route.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ug.route.data.database.entities.UserData

@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object{


        private const val DATABASE_NAME = "UserDatabase"

        @Volatile private var instance : UserDatabase? = null

        fun getInstance(context: Context) : UserDatabase{
            return instance ?: synchronized(this){ buildDatabase(context).also { instance = it } }
        }
        private fun buildDatabase(context: Context) : UserDatabase{
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                DATABASE_NAME).build()
        }
    }
}