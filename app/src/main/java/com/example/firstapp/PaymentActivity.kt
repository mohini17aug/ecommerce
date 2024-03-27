package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

import com.example.firstapp.utilities.ExpiryDateTextWatcher
import com.example.firstapp.utilities.InputFilterMinMax

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
        var isCardNumberValid=false

        /*cardNumber.setFilters(arrayOf<InputFilter>(InputFilterMinMax(1000000000000000, 9999999999999999)))
*/
        cardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 16) {
                    isCardNumberValid = true
                } else
                    isCardNumberValid = false
            }
        })

        val cardExpiry = findViewById<EditText>(R.id.editTextCardExpiry)
        val cvv = findViewById<EditText>(R.id.editTextCvv)

        //cardExpiry.addTextChangedListener(ExpiryDateTextWatcher())
       /* cardExpiry.addTextChangedListener(object : TextWatcher {
            private var previousInput = ""

            override fun afterTextChanged(s: Editable) {
                val input = s.toString()

                if (input.length == 2 && previousInput.length < 2) {
                    // Insert slash after MM when user is typing
                    s.insert(input.length, "/")
                } else if (input.length == 3 && previousInput.length == 4) {
                    // Remove slash if user is deleting characters
                    s.delete(2, 3)
                }

                previousInput = input
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}


        })*/
        val cardHolderName = findViewById<EditText>(R.id.editTextCardHolderName)


        // Set up the button to simulate payment
        val payButton = findViewById<Button>(R.id.buttonPay)
        payButton.setOnClickListener {
            // Simulate payment process
            if (validatePaymentInput(isCardNumberValid, cardNumber, cardExpiry, cvv, cardHolderName)) {
                Toast.makeText(this, "Payment for $price processing", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Payment for $price processed", Toast.LENGTH_SHORT).show()
                        // Delay for the length of the LONG toast duration
                        Handler(Looper.getMainLooper()).postDelayed({

                            // Proceed with opening OrderSummaryActivity
                            val intent = Intent(this, OrderSummaryActivity::class.java)
                            val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putString("ITEM_NAME", productName)
                                putFloat("ITEM_PRICE", price.toFloat())
                                 apply()
                            }

                           /* intent.putExtra("ITEM_NAME", productName)
                            intent.putExtra("ITEM_PRICE", price)*/
                            startActivity(intent)
                        },5000)
                    } else {
                        Toast.makeText(this, "Invalid Payment Details", Toast.LENGTH_LONG).show()
                    }

        }
    }

    private fun validatePaymentInput(
        isCardNumberValid:Boolean, cardNumber: EditText, cardExpiry: EditText, cvv: EditText, cardHolderName: EditText): Boolean {
        // Simple validation logic. You should expand this as per your need.
        return isCardNumberValid && cardNumber.text.isNotBlank() && cardExpiry.text.isNotBlank() && cvv.text.isNotBlank() && cardHolderName.text.isNotBlank()
    }
}

