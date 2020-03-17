package com.example.demoanimacion

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
        arrayOf("icon-animation.json", "test-loading.json", "animacion-ted.json", "6863-tenor.json")
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
    val timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        button.setOnClickListener {
            var textValueItems = 0
            val time = (1000..30000).random()
            client = NAME_CUSTOMER.random()
            animationRandom = ANIMATION.random()
            val animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            textview_items.startAnimation(animation)
            starAnimation()
            stopAnimation(time)
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
            timer.schedule(monitor, 4000, 4000)

        }

    }

    fun stopAnimation(time: Int) {
        Handler().postDelayed({
            textView.visibility = View.GONE
            animation.visibility = View.GONE
            textviewTitle.visibility = View.GONE
            textview_items.visibility = View.GONE
            textview_items.clearAnimation()
            button.visibility = View.VISIBLE
            animation.pauseAnimation()
            timer.purge()
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

}