package com.example.viniq.supletorio

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject

class BaseDatosUsuarios() {

    companion object {
        var bool : Boolean = false

        var ip = "http://172.29.64.172:1337/usuario"
        var aux = JSONArray()
        lateinit var resp: JSONArray

        fun insertarUsuarios(usuario: Usuario) {
            ip.httpPost(
                listOf(
                    "nombre" to usuario.nombre,
                    "apellido" to usuario.apellido,
                    "fechaNacimiente" to usuario.fechaNacimiente,
                    "correo" to usuario.correo,
                    "password" to usuario.password

                )
            )
                .responseString { request, _, result ->
                    Log.i("http-2", request.toString())
                }
        }


        fun getListUsuario(email: String, password: String): Boolean {



            "http://172.29.64.172:1337/Usuario/login"
                .httpPost(
                    listOf(
                        "correo" to email,
                        "password" to password
                    )
                )

            return bool
        }

    }


}