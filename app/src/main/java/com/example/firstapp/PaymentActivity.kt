package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Get the passed price from the intent
        val price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val productName = intent.getStringExtra("PRODUCT_NAME")

        // Set the price in the TextView
        val priceTextView = findViewById<TextView>(R.id.textViewPrice)
        priceTextView.text = String.format("Price: Rs.%.2f", price)

        val cardNumber = findViewById<EditText>(R.id.editTextCardNumber)
        val cardExpiry = findViewById<EditText>(R.id.editTextCardExpiry)
        val cvv = findViewById<EditText>(R.id.editTextCvv)
        val cardHolderName = findViewById<EditText>(R.id.editTextCardHolderName)


        // Set up the button to simulate payment
        val payButton = findViewById<Button>(R.id.buttonPay)
        payButton.setOnClickListener {
            // Simulate payment process
            if (validatePaymentInput(cardNumber, cardExpiry, cvv, cardHolderName)) {
                Toast.makeText(this, "Payment for $price processed", Toast.LENGTH_LONG).show()
                // Proceed with payment processing
                val intent = Intent(this, OrderSummaryActivity::class.java)
                intent.putExtra("ITEM_NAME", productName)
                intent.putExtra("ITEM_PRICE", price)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid Payment Details", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validatePaymentInput(cardNumber: EditText, cardExpiry: EditText, cvv: EditText, cardHolderName: EditText): Boolean {
        // Simple validation logic. You should expand this as per your need.
        return cardNumber.text.isNotBlank() && cardExpiry.text.isNotBlank() && cvv.text.isNotBlank() && cardHolderName.text.isNotBlank()
    }
}

