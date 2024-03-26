package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderSummaryActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 10000 // 3 seconds delay

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

        Handler().postDelayed({
            // Start your main activity here
            /*     startActivity(Intent(this, RegistrationActivity::class.java))
            */     startActivity(Intent(this, DashboardActivity::class.java))

            finish() // close this activity
        }, SPLASH_TIME_OUT)
    }
    }
