package com.estebanposada.testapp.app.database

import androidx.room.Database

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase {
    abstract fun itemDao(): ItemDao
}