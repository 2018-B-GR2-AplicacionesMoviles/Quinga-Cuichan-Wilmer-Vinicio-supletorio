package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu_juego.*

class MenuJuegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_juego)

        btn_registrarIngredientes.setOnClickListener {

            this.irRegistrarIngrediente()
        }

    }




    fun irRegistrarIngrediente() {

        val idActualActualActual = intent.getStringExtra("idActual")
         val registrar = Intent(this, RegistrarIngredienteJuegoActivity::class.java)
        registrar.putExtra("idActual", idActualActualActual.toString());

        startActivity(registrar)
    }
}
