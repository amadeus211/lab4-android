package com.example.lab4_android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TireWheelDao {
    @Insert
    suspend fun insert(tireWheel: TireWheel)

    @Query("SELECT * FROM tire_wheel")
    suspend fun getAllTireWheels(): List<TireWheel>

//    @Query("SELECT * FROM tire_wheel WHERE id = :id")
//    suspend fun getTireWheelById(id: Long): TireWheel?
    @Delete
    suspend fun delete(tireWheel: TireWheel)
}
