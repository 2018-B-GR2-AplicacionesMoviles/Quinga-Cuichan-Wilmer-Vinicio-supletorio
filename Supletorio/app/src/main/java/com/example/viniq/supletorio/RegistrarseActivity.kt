package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registrarse.*
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class RegistrarseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)


        button_RegistrarseRegistrarse.setOnClickListener {
            this.registrarse()
        }

        button2_RegistrarseLogin.setOnClickListener {
            this.login()
        }

    }


    fun guardarRegistro() {


        val url = "http://172.29.65.7:1337/Usuario"
        val usuario = Usuario(nombre = editText_RegistrarNombre.text.toString(), apellido = editText2_RegistrarApellido.text.toString(), fechaNacimiente = editText4_RegistrarFechaNacimiento.text.toString(), correo = editText5_RegistrarCorreo.text.toString(), password = editText6_RegistrarPassword.text.toString())

        val parametros = listOf("nombreUsuario" to usuario.nombre, "apellidoUsuario" to usuario.apellido, "fechaNacimientoUsuario" to usuario.fechaNacimiente, "correoUsuario" to usuario.correo, "passwordUsuario" to usuario.password)

        url.httpPost(parametros).responseString { resquest, response, result ->
            when (result) {
                is Result.Failure -> {

                    val exepcion = result.getException()
                    Toast.makeText(this, "Error:${exepcion}", Toast.LENGTH_SHORT).show()


                }
                is Result.Success -> {


                }

            }

        }


    }

    fun registrarse() {

        val registro = Intent(this, MainActivity::class.java)
        startActivity(registro)
        this.finish()
    }


    fun login() {

        val regresar = Intent(this, MainActivity::class.java)
        startActivity(regresar)
        this.finish()
    }

}
