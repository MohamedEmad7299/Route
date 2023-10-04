package com.ug.route.di

import com.ug.route.data.database.UserDatabase
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.RouteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(routeApiService: RouteApiService , userDatabase: UserDatabase) : Repository{
        return Repository(routeApiService,userDatabase)
    }
}