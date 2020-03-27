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
        arrayOf(
            "search-file.json",
            "icon-animation.json",
            "animacion-ted.json"
        )
    var text = arrayOf("")
    var animationRandom = ""
    var client = ""
    var timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle? = intent.extras
        val type: Int? = bundle?.getInt("type")
        type?.let { bindArrayText(it) }

        setSupportActionBar(toolbar)

        // Now get the support action bar
        val actionBar = supportActionBar


        // Set action bar elevation
        if (actionBar != null) {
            actionBar.elevation = 4.0F
            actionBar.setDisplayShowHomeEnabled(true)
        }


        when (type) {
            1 -> actionBar!!.title = "Adelanto de Sueldo"
            2 -> actionBar!!.title = "Express"
            3 -> actionBar!!.title = "Prestamo Personal"
        }


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
                if (textValueItems < text.size -1) {
                    textValueItems++
                    runOnUiThread {
                        textview_items.text = text[textValueItems]
                    }
                } else if (textValueItems >= 4)
                    textValueItems = 0
            }
        }
        timer.schedule(monitor, 4000, 4000)


    }

    fun stopAnimation(time: Int) {
        Handler().postDelayed({
            progressBar.visibility = View.GONE
            animationWarning.visibility = View.VISIBLE
            animationWarning.setAnimation("warning-animation.json")
            textView.text = getString(R.string.disculpa)
            textview_items.visibility = View.GONE
            textview_items.clearAnimation()
            button.visibility = View.GONE

        }, time.toLong())

    }


    fun starAnimation() {
        textview_items.text = text[0]
        progressBar.visibility = View.VISIBLE
        val text = "$client, estamos buscando la mejor oferta para vos"
        textView.text = text
        textView.visibility = View.VISIBLE
        button.visibility = View.GONE

    }

    fun bindArrayText(int: Int): Array<String> {
        when (int) {
            1 -> text = arrayOf(
                "100% online y sin papeleo",
                "En pocos minutos se acredita el dinero en tu cuenta.",
                "Llegá a fin de mes más fácil.",
                "Devolvé tu adelanto en hasta 45 días.",
                "No pagás comisión por cancelación anticipada."
            )
            2 -> text = arrayOf(
                "100% online y sin papeleo.",
                "Date ese gusto que tanto querés.",
                "¿Soñás con cumplir metas y alcanzar lo que siempre quisiste? Ahora hacerlo realidad es más fácil.",
                "Devolvelo en hasta 45 días.",
                "No pagas comisión por cancelación anticipada."
            )
            3 -> text = arrayOf(
                "¿Sabías que si acreditás tus haberes con nosotros tenés una tasa preferencial?",
                "Devolvé tu préstamo en hasta 72 meses.",
                "Pedí tu préstamo hasta $1.000.000 de pesos.",
                "100% online y sin papeleo.",
                "¿Ya pensaste tu proyecto? Nosotros lo hacemos realidad."
            )
        }
        return text
    }

}