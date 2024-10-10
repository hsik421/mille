package com.project.millie.di

import android.content.Context
import androidx.room.Room
import com.project.millie.data.AppDatabase
import com.project.millie.data.LocalConstants
import com.project.millie.data.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, LocalConstants.MY_DATABASE).build()

    @Singleton
    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()
}