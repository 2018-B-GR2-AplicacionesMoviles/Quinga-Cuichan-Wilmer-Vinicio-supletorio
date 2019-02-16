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
        var bool: Boolean = false

        var ip = "http://192.168.1.9:1337/Usuario"
        var aux = JSONArray()

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

        fun getListaLogin(correo: String, password: String): JSONArray {



            "${ip}/login?correo=${correo}&password=${password}".httpPost().responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosLogin: ${aux}")
                    }
                }
            }
            return aux
        }



    }

}