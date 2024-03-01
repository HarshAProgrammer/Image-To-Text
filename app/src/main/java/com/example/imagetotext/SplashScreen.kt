package com.example.imagetotext

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val animationView = findViewById<LottieAnimationView>(R.id.animation_view)
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { animation ->
            animationView.progress = animation.animatedValue as Float
        }
        animator.start()

        animationView.addAnimatorUpdateListener {
            // Do something.
        }
        animationView.playAnimation()

        if (animationView.isAnimating) {
            // Do something.
        }



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,IntroScreen::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}