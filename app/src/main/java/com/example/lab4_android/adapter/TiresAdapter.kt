package com.example.lab4_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_android.R
import com.example.lab4_android.data.TiresItem

class TiresAdapter(
    private val onDeleteClick: (TiresItem) -> Unit
) : RecyclerView.Adapter<TiresAdapter.TiresViewHolder>() {

    private val items = mutableListOf<TiresItem>()

    // Оновлення списку елементів
    fun submitList(newItems: List<TiresItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    // Метод для отримання поточного списку елементів
    fun getCurrentItems(): List<TiresItem> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tires, parent, false)
        return TiresViewHolder(view)
    }

    override fun onBindViewHolder(holder: TiresViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        // Обробник кліку для кнопки видалення
        holder.deleteButton.setOnClickListener {
            onDeleteClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    // ViewHolder для елемента RecyclerView
    class TiresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val description: TextView = itemView.findViewById(R.id.description)
        private val weight: TextView = itemView.findViewById(R.id.weight)
        private val ref: TextView = itemView.findViewById(R.id.ref)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        // Заповнення даних у вигляд
        fun bind(item: TiresItem) {
            ref.text = "Ref: ${item.Ref}"
            description.text = item.Description
            weight.text = "Weight: ${item.Weight}"
        }
    }
}
