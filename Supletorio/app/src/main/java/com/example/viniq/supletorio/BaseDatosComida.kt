package com.example.viniq.supletorio

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import org.json.JSONArray

class BaseDatosComida() {
    companion object {

        var ip = "http://192.168.1.9:1337/comida"
        var aux = JSONArray()
        lateinit var resp: JSONArray

        fun insertarComida(comida: Comida) {
            ip.httpPost(listOf(
                    "nombrePlato" to comida.nombrePlato,
                    "descripcionPlato" to comida.descripcionPlato,
                    "nacionalidad" to comida.nacionalidad,
                    "numeroPersonas" to comida.numeroPersonas,
                    "picante" to comida.picante)).responseString { request, _, result ->
                Log.i("http-2", request.toString())
            }
        }

        fun getList(): ArrayList<Comida> {
            val comida: ArrayList<Comida> = ArrayList()

            ip.httpGet().responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "ComidaDatos: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }

            for (i in 0 until aux.length()) {

                val idComida = resp.getJSONObject(i).getInt("id")
                val nombrePlato = resp.getJSONObject(i).getString("nombrePlato")
                val descripcionPlato = resp.getJSONObject(i).getString("descripcionPlato")
                val nacionalidad = resp.getJSONObject(i).getString("nacionalidad")
                val numeroPersonas = resp.getJSONObject(i).getInt("numeroPersonas")
                val picante = resp.getJSONObject(i).getBoolean("picante")
                val comidaComida = Comida(idComida, nombrePlato, descripcionPlato, nacionalidad, numeroPersonas, picante)
                comida.add(comidaComida)


            }
            Log.i("http-22d", "DatosReturnComida: ${comida}")
            return comida

        }

        fun eliminar(id: Int) {
            "${ip}/${id}".httpDelete().responseString { request, response, result ->
                Log.i("http-2", request.toString())
            }
        }

        fun editarComida(comida: Comida){
            "${ip}/${comida.idComida}".httpPut(listOf(
                    "nombrePlato" to comida.nombrePlato,
                    "descripcionPlato" to comida.descripcionPlato,
                    "nacionalidad" to comida.nacionalidad,
                    "numeroPersonas" to comida.numeroPersonas,
                    "picante" to comida.picante))
                    .responseString{ request, _, result ->
                        Log.i("http-2",request.toString())
                    }
        }


    }
}


