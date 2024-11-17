package com.example.lab4_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_android.adapter.SimpleItemAdapter
import com.example.lab4_android.viewmodel.ItemViewModel
import android.widget.Button
import com.example.lab4_android.R

class MainActivity : ComponentActivity() {
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var adapter: SimpleItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = SimpleItemAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.allItems.observe(this, Observer { items ->
            adapter.updateItems(items)
        })

        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        var counter = 1

        addButton.setOnClickListener {
            val name = "Item ${System.currentTimeMillis()}"
            val description = "Created item №${counter}"
            viewModel.addItem(name, description)
            counter++

        }

        deleteButton.setOnClickListener {
            val items = adapter.getCurrentItems()
            if (items.isNotEmpty()) {
                viewModel.deleteItem(items.last())
            }
        }
    }
}


