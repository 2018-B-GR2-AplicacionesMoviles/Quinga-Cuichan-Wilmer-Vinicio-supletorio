package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3_registrarse.setOnClickListener {
            this.irRegistrar()
        }


        button4_login.setOnClickListener {
            this.irMenuPrincipal()
        }
    }


    fun irRegistrar() {
        val registar = Intent(this, RegistrarseActivity::class.java)
        startActivity(registar)
    }

    fun irMenuPrincipal() {
        val menuPrincipal = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(menuPrincipal)
    }
}
