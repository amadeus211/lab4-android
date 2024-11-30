package com.example.lab4_android.data

data class TiresItem(
    val Ref: String,
    val Description: String,
    val DescriptionRu: String,
    val Weight: String,
    val DescriptionType: String
)

data class ApiResponse(
    val success: Boolean,
    val data: List<TiresItem>
)