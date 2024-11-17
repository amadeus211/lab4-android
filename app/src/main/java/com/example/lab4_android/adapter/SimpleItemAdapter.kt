package com.example.lab4_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_android.R
import com.example.lab4_android.data.SimpleItem

class SimpleItemAdapter(private var itemList: List<SimpleItem>) :
    RecyclerView.Adapter<SimpleItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.itemTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.titleTextView.text = "ID: ${item.id}, Name: ${item.name}, Description: ${item.description}"
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItems(newItems: List<SimpleItem>) {
        itemList = newItems
        notifyDataSetChanged()
    }

    fun getCurrentItems(): List<SimpleItem> = itemList
}

