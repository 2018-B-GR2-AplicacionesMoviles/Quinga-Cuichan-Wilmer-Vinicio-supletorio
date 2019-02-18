package com.example.viniq.supletorio

import android.content.Intent
 import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_registrarse.*

class RegistrarseActivity : AppCompatActivity() {

    var id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)


        button_RegistrarseRegistrarse.setOnClickListener {
            this.guardarDatos()

        }

        button2_RegistrarseLogin.setOnClickListener {
            this.login()
        }

    }


    fun guardarDatos() {
        if (editText_RegistrarNombre.text.toString().isEmpty() ||
            editText2_RegistrarApellido.text.toString().isEmpty() ||
            editText4_RegistrarFechaNacimiento.toString().isEmpty() ||
            editText5_RegistrarCorreo.text.toString().isEmpty() ||
            editText6_RegistrarPassword.text.toString().isEmpty()
        ) {
            Alerter.create(this).setTitle("Campos Vacios").setText("Completa la informacion de todos los campos")
                .setBackgroundColorRes(R.color.error_color_material_light).enableSwipeToDismiss().show()
        } else {
            var nombre = editText_RegistrarNombre.text.toString()
            var apellido = editText2_RegistrarApellido.text.toString()
            var fechaNacimiente = editText4_RegistrarFechaNacimiento.text.toString()
            var correo = editText5_RegistrarCorreo.text.toString()
            var password = editText6_RegistrarPassword.text.toString()


            var usuriosusuario =
                Usuario(0, nombre, apellido, fechaNacimiente, correo, password)
            Log.i("ssss", usuriosusuario.toString())


            BaseDatosUsuarios.insertarUsuarios(usuriosusuario)


            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


    fun login() {

        val regresar = Intent(this, MainActivity::class.java)
        startActivity(regresar)
        this.finish()
    }

}
