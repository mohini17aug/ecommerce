package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_product)

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION")
        val productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 00.00)
        val productImage = intent.getIntExtra("PRODUCT_IMAGE", R.drawable.oh_no)

        findViewById<TextView>(R.id.product_name).text = productName
        findViewById<TextView>(R.id.product_description).text = productDescription
        val priceTextView = findViewById<TextView>(R.id.product_price)
        priceTextView.text = String.format("Price: Rs.%.2f", productPrice)

        findViewById<ImageView>(R.id.product_image).setImageResource(productImage)
        val buttonBuyNow = findViewById<Button>(R.id.buttonBuyNow)
        buttonBuyNow.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("PRODUCT_PRICE", productPrice)
            intent.putExtra("PRODUCT_NAME", productName)

            startActivity(intent)
        }
    }
}