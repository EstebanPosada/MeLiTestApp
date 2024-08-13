package com.estebanposada.testapp.app.di

import android.app.Application
import androidx.room.Room
import com.estebanposada.testapp.app.database.ItemDatabase
import com.estebanposada.testapp.app.database.RoomDataSource
import com.estebanposada.testapp.data.source.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(db: ItemDatabase): LocalDataSource = RoomDataSource(db.itemDao())

    @Singleton
    @Provides
    fun provideDB(app: Application) = Room.databaseBuilder(app, ItemDatabase::class.java, "ItemDatabase.db").build()
}