package com.example.lab4_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab4_android.data.AppDatabase
import com.example.lab4_android.data.ItemDao
import com.example.lab4_android.data.SimpleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: ItemDao = AppDatabase.getDatabase(application).itemDao()
    val allItems: LiveData<List<SimpleItem>> = dao.getAllItems()

    fun addItem(name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertItem(SimpleItem(name = name, description = description))
        }
    }

    fun deleteItem(item: SimpleItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteItem(item)
        }
    }
}
