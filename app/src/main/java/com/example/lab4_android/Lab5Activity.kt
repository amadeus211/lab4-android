package com.example.lab4_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_android.adapter.TiresAdapter
import com.example.lab4_android.data.ApiResponse
import com.example.lab4_android.data.AppDatabase
import com.example.lab4_android.data.TiresItem
import com.example.lab4_android.data.toEntity
import com.example.lab4_android.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Lab5Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TiresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab5)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TiresAdapter { tireItem ->
            deleteTireItem(tireItem)
        }
        recyclerView.adapter = adapter

        fetchTiresWheelsList()

        val lab4Button = findViewById<Button>(R.id.lab4Button)
        lab4Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteTireItem(item: TiresItem) {
        lifecycleScope.launch(Dispatchers.IO) {
            val dao = AppDatabase.getDatabase(applicationContext).tireWheelDao()
            dao.delete(item.toEntity())

            withContext(Dispatchers.Main) {
                adapter.submitList(adapter.getCurrentItems().filter { it != item })
                Toast.makeText(this@Lab5Activity, "Item deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun fetchTiresWheelsList() {
        val requestBody = mapOf(
            "apiKey" to "62127f813387b9c55376d67c36202d6d",
            "modelName" to "CommonGeneral",
            "calledMethod" to "getTiresWheelsList"
        )

        RetrofitClient.instance.getTiresWheelsList(requestBody)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
//                        Log.d("Lab5Activity", "Response: $apiResponse")

                        if (apiResponse?.success == true) {
                            val tiresList = apiResponse.data
//                            Log.d("Lab5Activity", "Tires list size: ${tiresList.size}")
//                            Log.d("Lab5Activity", "Tires list data: $tiresList")

                            adapter.submitList(tiresList)
                        } else {
                            Log.e("Lab5Activity", "API Error: Success field is false")
                            Toast.makeText(this@Lab5Activity, "Error fetching data", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("Lab5Activity", "Response failed: ${response.errorBody()?.string()}")
                        Toast.makeText(this@Lab5Activity, "Error fetching data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("Lab5Activity", "Network error: ${t.message}")
                    Toast.makeText(this@Lab5Activity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }


}

