package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderSummaryActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 5000 // 5 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        // Retrieve passed data
        var productName = sharedPref.getString("ITEM_NAME", null)

        //var productName = intent.getStringExtra("ITEM_NAME")
        var productPrice = sharedPref.getFloat("ITEM_PRICE", 0.0F)


        val productNameTextView = findViewById<TextView>(R.id.textViewProductName)
        if (productName == null){
            productNameTextView.text = "Order Summary is blank."
        }else
        productNameTextView.text = "Product Name: $productName"

        val productPriceTextView = findViewById<TextView>(R.id.textViewProductPrice)
        if (productName == null){
            productPriceTextView.text = "Please shop first."
        }else
            productPriceTextView.text = "Price: Rs ${String.format("%.2f", productPrice)}"

        val paymentmethod = findViewById<TextView>(R.id.textViewPaymentMethod)
        if (productName == null) {
            paymentmethod.text=null
        }else{
            paymentmethod.text = "Payment Method: Prepaid Full payment done"

        }
        Handler().postDelayed({


            // Start your main activity here
            /*     startActivity(Intent(this, RegistrationActivity::class.java))
            */     startActivity(Intent(this, DashboardActivity::class.java))

            finish() // close this activity
        }, SPLASH_TIME_OUT)
    }
    }
