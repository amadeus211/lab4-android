package com.example.lab4_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tire_wheel")
data class TireWheel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tireSize: String,
    val wheelType: String,
    val brand: String
)

fun TiresItem.toEntity(): TireWheel {
    return TireWheel(
        tireSize = this.Description,
        wheelType = this.DescriptionType,
        brand = this.Ref
    )
}

