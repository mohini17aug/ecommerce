package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SystemRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_registration)

        val emailEditText = findViewById<EditText>(R.id.editTextEmailRegister)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordRegister)
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        val loginText = findViewById<TextView>(R.id.textViewLogin)
        val nameEditText=findViewById<EditText>(R.id.editTextName)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name= nameEditText.text.toString()
            val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("RegisteredUsername", email)
                putString("RegisteredPassword", password)
                putString("Name",name)
                apply()
            }
            val intent = Intent(this, SystemLogin::class.java)

            startActivity(intent)
        }
        loginText.setOnClickListener {
            val intent = Intent(this, SystemLogin::class.java)
            startActivity(intent)
        }
    }
}