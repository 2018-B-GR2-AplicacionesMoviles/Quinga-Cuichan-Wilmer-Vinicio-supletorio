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

        var ip = "http://172.29.64.172:1337/comida"
        var aux = JSONArray()
        lateinit var resp: JSONArray

        fun insertarComida(comida: Comida) {
            ip.httpPost(listOf("nombrePlato" to comida.nombrePlato,
                "descripcionPlato" to comida.descripcionPlato,
                "nacionalidad" to comida.nacionalidad,
                "numeroPersonas" to comida.numeroPersonas,
                "picante" to comida.picante))
                .responseString { request, _, result ->
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

        fun editarComida(comida: Comida) {
            "${ip}/${comida.idComida}".httpPut(listOf("nombrePlato" to comida.nombrePlato, "descripcionPlato" to comida.descripcionPlato, "nacionalidad" to comida.nacionalidad, "numeroPersonas" to comida.numeroPersonas, "picante" to comida.picante)).responseString { request, _, result ->
                Log.i("http-2", request.toString())
            }
        }


        fun getListaIdIngredientes(id: Int): ArrayList<Ingredientes> {

            val ingredientes: ArrayList<Ingredientes> = ArrayList()
            "${ip}/?idComida=${id}".httpGet().responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosIngredientes: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }
            for (i in 0 until aux.length()) {

                val idIngredientes = resp.getJSONObject(i).getInt("idIngredientes")
                val nombreIngrediente = resp.getJSONObject(i).getString("nombreIngrediente")
                val cantidad = resp.getJSONObject(i).getInt("cantidad")
                val descripcionPreparacion = resp.getJSONObject(i).getString("descripcionPreparacion")
                val opcional = resp.getJSONObject(i).getBoolean("opcional")
                val tipoIngrediente = resp.getJSONObject(i).getString("tipoIngrediente")
                val necesitaRefrigeracion = resp.getJSONObject(i).getBoolean("necesitaRefrigeracion")
                val idComida = resp.getJSONObject(i).getInt("idComida")
                val ing = Ingredientes(idIngredientes, nombreIngrediente, cantidad, descripcionPreparacion, opcional, tipoIngrediente, necesitaRefrigeracion, idComida)
                ingredientes.add(ing)
                Log.i("http-2", "DatosAP-2: ${ing}")
            }
            Log.i("http-2", "DatosReturnAP: ${ingredientes}")
            return ingredientes
        }

    }
}


