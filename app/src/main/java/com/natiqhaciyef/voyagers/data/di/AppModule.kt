package com.natiqhaciyef.voyagers.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.natiqhaciyef.voyagers.data.local.repository.LocalRepository
import com.natiqhaciyef.voyagers.data.local.repository.TourRepository
import com.natiqhaciyef.voyagers.data.local.source.AppDataSource
import com.natiqhaciyef.voyagers.data.local.source.TourDataSource
import com.natiqhaciyef.voyagers.data.roomdb.AppDao
import com.natiqhaciyef.voyagers.data.roomdb.AppDatabase
import com.natiqhaciyef.voyagers.data.roomdb.TourDao
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
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAppDao(database: AppDatabase) = database.getDao()

    @Provides
    @Singleton
    fun provideTourDao(database: AppDatabase) = database.getTourDao()


    @Provides
    @Singleton
    fun provideDataSource(dao: AppDao) = AppDataSource(dao)

    @Provides
    @Singleton
    fun provideTourDataSource(dao: TourDao) = TourDataSource(dao)

    @Provides
    @Singleton
    fun provideLocalRepository(ds: AppDataSource) = LocalRepository(ds)

    @Provides
    @Singleton
    fun provideTourRepository(ds: TourDataSource) = TourRepository(ds)
}