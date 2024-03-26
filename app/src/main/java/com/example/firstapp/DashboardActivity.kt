package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.model.Item
import android.util.Log
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
        nameView.text="Welcome $name ! "
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemList = listOf( // Dummy data
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

        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterItems(newText)
                return false
            }
        })
    }

    private fun filterItems(text: String?) {
         val filteredList = itemList.filter {
            it.name.contains(text ?: "", ignoreCase = true)
        }
       // Log.d("DashboardActivity", "Filtering for query: $text  filtered count: ${filteredList.size}")

        itemAdapter.filterList(filteredList)
    }
}