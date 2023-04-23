package com.natiqhaciyef.voyagers.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.natiqhaciyef.voyagers.data.local.repository.PaymentRepository
import com.natiqhaciyef.voyagers.data.local.repository.TourRepository
import com.natiqhaciyef.voyagers.data.local.source.PaymentDataSource
import com.natiqhaciyef.voyagers.data.local.source.TourDataSource
import com.natiqhaciyef.voyagers.data.roomdb.AppDao
import com.natiqhaciyef.voyagers.data.roomdb.AppDatabase
import com.natiqhaciyef.voyagers.data.roomdb.PaymentDao
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
    fun provideTourDao(database: AppDatabase) = database.getTourDao()

    @Provides
    @Singleton
    fun providePaymentDao(database: AppDatabase) = database.getPaymentDao()


    @Provides
    @Singleton
    fun provideTourDataSource(dao: TourDao) = TourDataSource(dao)

    @Provides
    @Singleton
    fun provideTourRepository(ds: TourDataSource) = TourRepository(ds)


    @Provides
    @Singleton
    fun providePaymentDataSource(dao: PaymentDao) = PaymentDataSource(dao)

    @Provides
    @Singleton
    fun providePaymentRepository(ds: PaymentDataSource) = PaymentRepository(ds)

}