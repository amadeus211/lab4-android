package com.example.lab4_android.network

import com.example.lab4_android.data.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("v2.0/json/")
    fun getTiresWheelsList(@Body request: Map<String, String>): Call<ApiResponse>
}
