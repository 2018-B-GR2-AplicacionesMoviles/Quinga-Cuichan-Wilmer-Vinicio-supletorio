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

        var correo: String = editText3_correoLogin.text.toString()
        var password: String = editText2_passwordLogin.text.toString()

        Log.i("recivido", correo.toString())

        button4_login.setOnClickListener {
            this.irMenuPrincipal(correo, password)
        }
    }


    fun irRegistrar() {
        val registar = Intent(this, RegistrarseActivity::class.java)
        startActivity(registar)
    }

    fun irMenuPrincipal(correo: String, password: String) {

        val datos = BaseDatosUsuarios.getListUsuario("a", "a")
        Log.i("recivido", datos.toString())

        if (datos == true) {

            val menuPrincipal = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(menuPrincipal)

        } else {

            Alerter.create(this)
                .setTitle("Datos Incorrectos")
                .setText("Verifica tus datos e intenta de nuevo")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()
        }
    }
}
