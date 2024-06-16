package com.ug.route.di

import android.content.Context
import com.ug.route.data.database.RouteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : RouteDatabase {
        return RouteDatabase.getInstance(context)
    }
}
