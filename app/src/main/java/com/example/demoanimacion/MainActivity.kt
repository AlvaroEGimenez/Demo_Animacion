package com.example.demoanimacion

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val NAME_CUSTOMER = arrayOf("Lucas", "Juan", "Paz", "Vanesa", "Valeria", "Karla")
    val ANIMATION =
        arrayOf(
            "search-file.json",
            "icon-animation.json",
            "animacion-ted.json"
        )
    val TEXT_ITEMS = arrayOf(
        "Un horno para mejorar la cocina de tu emprendimiento.",
        "Nuevas herramientas para tu taller."
        ,
        " Una computadora para crear tu tienda online.",
        "Los mejores insumos para tu peluquería."
        ,
        "Mejorar el espacio donde realizas tu emprendimiento."
    )
    var animationRandom = ""
    var client = ""
    var timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
                timer.cancel()
                timer = Timer()
                var textValueItems = 0
                val time = (1000..30000).random()
                client = NAME_CUSTOMER.random()
                animationRandom = ANIMATION.random()
                val animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
                textview_items.startAnimation(animation)
                stopAnimation(time)
                starAnimation()
                val monitor = object : TimerTask() {
                    override fun run() {
                        if (textValueItems < 4) {
                            textValueItems++
                            runOnUiThread {
                                textview_items.text = TEXT_ITEMS[textValueItems]
                            }
                        } else if (textValueItems >= 4)
                            textValueItems = 0
                    }
                }
                timer.schedule(monitor, 3000, 3000)


        }

    }

    fun stopAnimation(time: Int) {
        Handler().postDelayed({
            animation.visibility = View.GONE
            animationWarning.visibility = View.VISIBLE
            animationWarning.setAnimation("warning-animation.json")
            textView.text = getString(R.string.disculpa)
            textviewTitle.visibility = View.GONE
            textview_items.visibility = View.GONE
            textview_items.clearAnimation()
            button.visibility = View.GONE
            animation.pauseAnimation()
        }, time.toLong())

    }


    fun starAnimation() {
        textview_items.text = TEXT_ITEMS[0]
        animation.setAnimation(animationRandom)
        animation.playAnimation()
        animation.visibility = View.VISIBLE
        textviewTitle.visibility = View.VISIBLE
        val text = "Hola $client, estamos buscando la mejor oferta para vos"
        textView.text = text
        textView.visibility = View.VISIBLE
        button.visibility = View.GONE

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}