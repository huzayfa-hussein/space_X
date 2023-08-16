package com.hu.spacex.di

import android.content.Context
import androidx.room.Room
import com.hu.spacex.data.persistence.AppDatabase
import com.hu.spacex.data.persistence.DaoService
import com.hu.spacex.data.remote.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideServiceDao(appDatabase: AppDatabase): DaoService {
        return appDatabase.daoService()
    }


}