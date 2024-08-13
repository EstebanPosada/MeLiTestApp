package com.estebanposada.testapp.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Query("SELECT * FROM Item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM Item WHERE id = :id")
    fun findById(id: String): Item

    @Query("SELECT COUNT(id) FROM Item")
    fun itemCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(movies: List<Item>)
}