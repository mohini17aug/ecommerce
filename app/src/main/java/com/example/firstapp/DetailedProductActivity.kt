package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.utilities.InputFilterMinMax

class DetailedProductActivity : AppCompatActivity() {
    private var quantity=1
    private var productPrice: Double=0.0
    private lateinit var priceTextView: TextView
    private var toastShown=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_product)

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION")
        productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.00)
        val productImage = intent.getIntExtra("PRODUCT_IMAGE", R.drawable.oh_no)

        findViewById<TextView>(R.id.product_name).text = productName
        findViewById<TextView>(R.id.product_description).text = productDescription
        priceTextView = findViewById<TextView>(R.id.product_price)

        updatePrice()

        val quantityTextView=findViewById<TextView>(R.id.textQuantity)
        quantityTextView.setFilters(arrayOf<InputFilter>(InputFilterMinMax(1, 10)))

        quantityTextView.text=quantity.toString()

        findViewById<ImageView>(R.id.product_image).setImageResource(productImage)
        val buttonBuyNow = findViewById<Button>(R.id.buttonBuyNow)
        buttonBuyNow.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("PRODUCT_PRICE", productPrice*quantity)
            intent.putExtra("PRODUCT_NAME", productName)
            startActivity(intent)
        }
    }
    fun decreaseQuantity(view: View) {
        if (quantity > 1) {
            quantity--
            findViewById<TextView>(R.id.textQuantity).text = quantity.toString()
            updatePrice()
        }
        if(quantity == 1){
            if( !toastShown) {
                Toast.makeText(this, "Items cannot be empty.Minimum quantity is 1", Toast.LENGTH_SHORT).show()
                toastShown = true;
            }
        }
    }
    fun increaseQuantity(view: View) {
        quantity++
        if (quantity>10) {
            quantity--
        if( !toastShown) {
            Toast.makeText(this, "Maximum allowed quantity is 10", Toast.LENGTH_SHORT).show()
           toastShown = true;
        }
        }else {
            findViewById<TextView>(R.id.textQuantity).text = quantity.toString()
            updatePrice()
        }
    }

    // Method to update the price based on quantity
    private fun updatePrice() {
        // Calculate total price
        if(quantity<11) {
            val totalPrice = productPrice * quantity

            // Update price text
            priceTextView.text = String.format("Price: Rs.%.2f", totalPrice)
        }
    }

}