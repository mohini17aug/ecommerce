package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SystemLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_login)


        val emailEditText = findViewById<EditText>(R.id.editTextEmailLogin)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordLogin)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val registerTextView = findViewById<TextView>(R.id.textViewRegister)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
             validateLogin(email, password)
           /* val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)*/
            // Implement your login logic here
        }

        registerTextView.setOnClickListener {
            // Navigate to Register Activity
            val intent = Intent(this, SystemRegistration::class.java)
            startActivity(intent)
        }
    }

    private fun validateLogin(email: String, password: String) {
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val registeredUsername = sharedPref.getString("RegisteredUsername", null)
        val registeredPassword = sharedPref.getString("RegisteredPassword", null)
        Log.d("SystemLogin", "registeredUsername : $registeredUsername")

        if (email == registeredUsername && password == registeredPassword) {
            // Credentials match, go to Dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            if (registeredUsername == null) {
                Toast.makeText(this, "Please register first.", Toast.LENGTH_SHORT).show()

            }else
            // Credentials don't match, show error
            Toast.makeText(this, "Credentials didn't match, Use registered email id", Toast.LENGTH_SHORT).show()
        }
    }
}