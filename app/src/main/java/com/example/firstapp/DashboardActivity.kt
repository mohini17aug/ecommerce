package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.model.Item

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private var itemList = listOf<Item>() // Populate this list with your items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val name=sharedPref.getString("Name","user")
        recyclerView = findViewById(R.id.recyclerView)
        val nameView=findViewById<TextView>(R.id.nameTextView)
        nameView.text="Hello $name ! "
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemList = listOf( // Dummy data
            Item(1, "Female Fashion", "Description 1", R.drawable.femaleclothing),
            Item(2, "Male Fashion", "Description 2",R.drawable.maleclothing),
            Item(3, "Kids Fashion", "Fabric 2", R.drawable.childrenclothing),
            Item(4, "Misc", "Fabric 2", R.drawable.fabric1)

        // Add more items
        )

        itemAdapter = ItemAdapter(itemList) { item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("ITEM_Name", item.name)
            startActivity(intent)
        }
        recyclerView.adapter = itemAdapter

        // Implement search functionality here
    }
}