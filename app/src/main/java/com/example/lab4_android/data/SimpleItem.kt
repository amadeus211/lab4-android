package com.example.lab4_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class SimpleItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String
)

