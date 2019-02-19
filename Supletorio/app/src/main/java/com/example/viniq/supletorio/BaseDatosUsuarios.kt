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
        lateinit var resp : JSONArray


        fun insertarUsuarios(usuario: Usuario) {
            ip.httpPost(
                listOf(
                    "nombre" to usuario.nombre,
                    "apellido" to usuario.apellido,
                    "fechaNacimiente" to usuario.fechaNacimiente,
                    "correo" to usuario.correo,
                    "total_oro" to usuario.total_oro,
                    "total_experiencia" to usuario.total_experiencia
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



        fun getList():ArrayList<Usuario>{
            val usuarios: ArrayList<Usuario> = ArrayList()
            ip.httpGet().responseJson {
                    request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "Datos: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp= result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                val nombre = resp.getJSONObject(i).getString("nombre")
                val apellido = resp.getJSONObject(i).getString("apellido")
                val fechaNacimiento = resp.getJSONObject(i).getString("fechaNacimiento")
                val email = resp.getJSONObject(i).getString("correo")
                val password = resp.getJSONObject(i).getString("password")
                val totalOro = resp.getJSONObject(i).getInt("total_oro")
                val totalExperiencia = resp.getJSONObject(i).getInt("total_experiencia")
                val usuario = Usuario(id,nombre,apellido,fechaNacimiento,email,password,totalOro,totalExperiencia)
                usuarios.add(usuario)
                Log.i("http-2", "Datos: ${usuarios}")
            }
            Log.i("http-2", "DatosReturn: ${usuarios}")
            return usuarios

        }



    }

}