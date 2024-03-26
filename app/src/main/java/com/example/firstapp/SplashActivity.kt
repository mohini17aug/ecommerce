package com.example.firstapp
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.firstapp.SystemLogin

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_anime)
        fadeInAnimation.startOffset = 800 // Delay of 800 ms
        val logo = findViewById<ImageView>(R.id.logo)
        logo.startAnimation(fadeInAnimation)


        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Starting the activity
                startActivity(Intent(this@SplashActivity, SystemLogin::class.java))
                finish() // Finish current activity
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

    }
}
