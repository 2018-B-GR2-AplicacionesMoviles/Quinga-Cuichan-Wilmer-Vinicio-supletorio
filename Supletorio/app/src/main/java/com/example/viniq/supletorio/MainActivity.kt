package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tapadoo.alerter.Alerter
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

        val correo = correoLogin.text.toString()
        val password = passwordLogin.text.toString()

         val datos = BaseDatosUsuarios.getListaLogin(correo, password)
        Log.i("recividoffff", datos.toString())

        if (datos.length() == 0) {

            Alerter.create(this)
                .setTitle("Datos Incorrectos")
                .setText("Verifica tus datos e intenta de nuevo")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()


        } else {

            val menuPrincipal = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(menuPrincipal)
            correoLogin.setText("")
            passwordLogin.setText("")

        }


    }
}
