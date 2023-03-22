package com.natiqhaciyef.voyagers.data.di

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.voyagers.data.local.repository.LocalRepository
import com.natiqhaciyef.voyagers.data.local.source.AppDataSource
import com.natiqhaciyef.voyagers.data.roomdb.AppDao
import com.natiqhaciyef.voyagers.data.roomdb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDao(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
            .getDao()

    @Provides
    @Singleton
    fun provideDataSource(dao: AppDao) = AppDataSource(dao)

    @Provides
    @Singleton
    fun provideLocalRepository(ds: AppDataSource) = LocalRepository(ds)


}