package com.example.firstapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        // Retrieve passed data
        val productName = intent.getStringExtra("ITEM_NAME")
        val productPrice = intent.getDoubleExtra("ITEM_PRICE", 0.0)

        val productNameTextView = findViewById<TextView>(R.id.textViewProductName)
        productNameTextView.text = "Product Name: $productName"

        val productPriceTextView = findViewById<TextView>(R.id.textViewProductPrice)
        productPriceTextView.text = "Price: Rs ${String.format("%.2f", productPrice)}"

        val paymentmethod = findViewById<TextView>(R.id.textViewPaymentMethod)
        paymentmethod.text = "Payment Method: Prepaid Full payment done"

    }
}