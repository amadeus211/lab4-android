package com.example.lab4_android.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<SimpleItem>>

    @Insert
    suspend fun insertItem(item: SimpleItem)

    @Delete
    suspend fun deleteItem(item: SimpleItem)



}
