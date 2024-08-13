package com.estebanposada.testapp.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Item::class], version = 1, exportSchema = false)
@TypeConverters(ClassConverter::class)
abstract class ItemDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao
}