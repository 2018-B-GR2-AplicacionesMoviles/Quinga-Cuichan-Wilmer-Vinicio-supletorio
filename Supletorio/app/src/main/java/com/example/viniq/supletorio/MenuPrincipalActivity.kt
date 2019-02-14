package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        button_PrincipalComida.setOnClickListener {
            this.irCrearComida()
        }

        button2_PrincipallistarComida.setOnClickListener {
            this.irListarComida()


        }


    }


    fun irCrearComida() {
        val comida = Intent(this, CrearComidaActivity::class.java)
        startActivity(comida)

    }

    fun irListarComida(){

        val listar = Intent(this, ListarComidaActivity::class.java)
        startActivity(listar)
    }
}
