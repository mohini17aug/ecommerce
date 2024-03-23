package com.example.firstapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // 3 seconds delay
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Using a Handler to delay the transition
        Handler().postDelayed({
            // Start your main activity here
            startActivity(Intent(this, MainActivity::class.java))
            finish() // close this activity
        }, SPLASH_TIME_OUT)
    }
  }



