package com.example.demoanimacion

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)


        button_ads.setOnClickListener {
            intent.putExtra("type", 1)
            startActivity(intent)
        }
        button_express.setOnClickListener {
            intent.putExtra("type", 2)
            startActivity(intent)
        }
        button_pp.setOnClickListener {
            intent.putExtra("type", 3)
            startActivity(intent)
        }
    }
}
