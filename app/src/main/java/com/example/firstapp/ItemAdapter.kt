package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.model.Item

class ItemAdapter(private val itemList: List<Item>, private val clickListener: (Item) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item, clickListener: (Item) -> Unit) {
            val itemName = view.findViewById<TextView>(R.id.itemName)
            val itemImage = view.findViewById<ImageView>(R.id.itemImage)
           /* val itemDescription=view.findViewById<TextView>(R.id.itemDescription)
            val price=view.findViewById<TextView>(R.id.itemDescription)*/

           itemName.text = item.name
           itemImage.setImageResource(item.imageResId) // Set the image resource

            view.setOnClickListener { clickListener(item) }

         /*   view.findViewById<TextView>(R.id.itemName).text = item.name
            // Set other views for item here

            view.setOnClickListener { clickListener(item)} */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_adapter , parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position], clickListener)
    }

    override fun getItemCount() = itemList.size
}
